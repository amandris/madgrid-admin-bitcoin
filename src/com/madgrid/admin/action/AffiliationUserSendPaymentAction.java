package com.madgrid.admin.action;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.util.Mail;
import com.madgrid.admin.util.Utils;
import com.madgrid.admin.util.mail.AffiliationUserMessageMailObject;
import com.madgrid.dao.AffiliationActivityDAO;
import com.madgrid.dao.AffiliationPaymentDAO;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.dao.AffiliationUserMessageDAO;
import com.madgrid.model.AffiliationActivity;
import com.madgrid.model.AffiliationPayment;
import com.madgrid.model.AffiliationUser;
import com.madgrid.model.AffiliationUserMessage;

public class AffiliationUserSendPaymentAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationActivityDAO affiliationActivityDAO = new AffiliationActivityDAO();
		AffiliationPaymentDAO affiliationPaymentDAO = new AffiliationPaymentDAO();
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		AffiliationUserMessageDAO affiliationUserMessageDAO = new AffiliationUserMessageDAO();
		
		String idString = request.getParameter("id");
		String transactionId = request.getParameter("transactionId");
		
		int affiliationUserId = Integer.parseInt( idString);
		
		AffiliationUser affiliationUser = affiliationUserDAO.getAffiliationUserById( affiliationUserId);
		
		if( affiliationUser != null 
			&& !Utils.nullOrBlank(affiliationUser.getBitcoinAddress()) 
			&& !affiliationUser.getBitcoinAddress().equalsIgnoreCase("Not assigned")
			&& !Utils.nullOrBlank(transactionId)){
		
			Criteria criteria = new Criteria();
			criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
			criteria.addEqualTo("type", 2);
			criteria.addEqualTo("alreadyPayed", false);
			List<AffiliationActivity> affiliationActivityList = affiliationActivityDAO.getAffiliationActivityListByCriteria(criteria);
			
			double bitcoins = 0d;
			
			for( AffiliationActivity affiliationActivity:affiliationActivityList){
				bitcoins = bitcoins + affiliationActivity.getAssignedBitcoins();
				affiliationActivity.setAlreadyPayed( true);
				affiliationActivityDAO.setAffiliationActivity(affiliationActivity);
			}
			
			AffiliationPayment affiliationPayment = new AffiliationPayment();
			affiliationPayment.setAffiliationUser(affiliationUser);
			affiliationPayment.setAffiliationUserId(affiliationUser.getId());
			affiliationPayment.setBitcoins(bitcoins);
			affiliationPayment.setTransactionId(transactionId);
			affiliationPayment.setCreated(Utils.today());

			affiliationPaymentDAO.setAffiliationPayment(affiliationPayment);
			
			affiliationUser.setAskedForTransfer(false);
			affiliationUser.setPaymentRequestValidated(false);
			affiliationUserDAO.setAffiliationUser(affiliationUser);
			
			
			DecimalFormat numberFormat = new DecimalFormat("#.########");
			
			AffiliationUserMessage affiliationUserMessage = new AffiliationUserMessage();
			affiliationUserMessage.setCreated( Utils.today());
			affiliationUserMessage.setSubject( numberFormat.format(bitcoins) + " BTC sent");
			affiliationUserMessage.setMessage( "Hi " + affiliationUser.getLogin()+". We've just sent the " + numberFormat.format(bitcoins) + " BTC you earned with you affiliation activity in Instantri.ch. The destination address is "+affiliationUser.getBitcoinAddress()+" and the transaction id is "+affiliationPayment.getTransactionId()+". Thank you very much for your great work.");
			affiliationUserMessage.setAffiliationUser(affiliationUser);
			affiliationUserMessage.setAffiliationUserId(affiliationUser.getId());
			
			affiliationUserMessageDAO.setAffiliationUserMessage(affiliationUserMessage);
			
			
			AffiliationUserMessageMailObject affiliationUserMessageMailObject = new AffiliationUserMessageMailObject( affiliationUserMessage.getMessage(), Utils.getBaseUrl());
			Mail mail = new Mail( affiliationUser.getEmail(), affiliationUserMessage.getSubject(),  affiliationUserMessageMailObject);
			mail.start();
			return mapping.findForward( "ok");
			
		}
		
		response.getWriter().println( "ERROR");
		
		return null; 
	}

}		
