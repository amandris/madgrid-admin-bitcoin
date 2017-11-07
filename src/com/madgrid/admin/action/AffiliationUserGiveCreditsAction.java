package com.madgrid.admin.action;


import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.madgrid.admin.util.Utils;
import com.madgrid.admin.util.mail.AffiliationUserMessageMailObject;
import com.madgrid.dao.AffiliationActivityDAO;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.dao.AffiliationUserMessageDAO;
import com.madgrid.dao.UserDAO;
import com.madgrid.dao.UserHistoricDAO;
import com.madgrid.model.AffiliationUser;
import com.madgrid.model.AffiliationUserMessage;
import com.madgrid.model.User;
import com.madgrid.model.UserHistoric;
import com.madgrid.admin.util.Mail;

public class AffiliationUserGiveCreditsAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		AffiliationActivityDAO affiliationActivityDAO = new AffiliationActivityDAO();
		AffiliationUserMessageDAO affiliationUserMessageDAO = new AffiliationUserMessageDAO();
		UserDAO userDAO = new UserDAO();
		UserHistoricDAO userHistoricDAO = new UserHistoricDAO();

		GregorianCalendar startTime = new GregorianCalendar();
		startTime.set( GregorianCalendar.DAY_OF_MONTH, 22 );
		startTime.set( GregorianCalendar.MONTH, GregorianCalendar.OCTOBER);
		startTime.set( GregorianCalendar.YEAR, 2014 );
		startTime.set( GregorianCalendar.HOUR, 0 );
		startTime.set( GregorianCalendar.MINUTE, 0 );
		startTime.set( GregorianCalendar.SECOND, 0);
		startTime.set( GregorianCalendar.MILLISECOND, 0);
		
		
		List<AffiliationUser> affiliationUserList = affiliationUserDAO.getAffiliationUserListByCriteria(new Criteria());
		
		for( AffiliationUser affiliationUser: affiliationUserList){
		
			Criteria criteria = new Criteria();
			criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
			criteria.addEqualTo("type", 1);
			criteria.addGreaterOrEqualThan("created", startTime.getTime());
			int userRegistered = affiliationActivityDAO.getAffiliationActivityCountByCriteria(criteria);
			int credits = 0;
			if( userRegistered == 0){
				credits = 0;
			} else if( userRegistered >=1 && userRegistered <=9){
				credits = 1;
			} else if( userRegistered >=10 && userRegistered <=19){
				credits = 2;
			} else {
				credits = (userRegistered / 10) + 1;
			}
			
			if( credits > 0){
				criteria = new Criteria();
				criteria.addEqualTo("login", affiliationUser.getLogin());
				User user = userDAO.getUserByCriteria(criteria);
				if( user != null){
					user.setCredits(user.getCredits() + credits);
					userDAO.setUser(user);
					
					UserHistoric userHistoric = new UserHistoric();
					userHistoric.setCreated( Utils.today());
					userHistoric.setType( UserHistoric.GET_CREDIT_BY_ADMIN_MORE);
					userHistoric.setUser(user);
					userHistoric.setUserId(user.getId());
					userHistoric.setValue1(new Double(credits));
					userHistoricDAO.setUserHistoric(userHistoric);
					
					String message = "Hi " + user.getLogin() + ". It's been 6 months since our last Instantri.ch credit giveaway (October 22th 2014) so we're rewarding our affiliation users for their great job. We are giving credits according their activity. If you got from 1 to 9 users since October 22th you'll get 1 credit. If you got from 10 to 19 you'll get 2 credits, and so on.<br/><br/>";
					message = message + "As an affiliation user you helped us to get " + userRegistered + " "+(userRegistered == 1 ? "user":"users")+" so you'll receive " + credits + " "+(credits == 1 ? "credit":"credits")+" in your Instantri.ch account. You can use them right now to play in any game.<br/><br/>Thank you very much and good luck."; 
					
					AffiliationUserMessage affiliationUserMessage = new AffiliationUserMessage();
					affiliationUserMessage.setCreated( Utils.today());
					affiliationUserMessage.setSubject( "We gave you " + credits + " "+(credits == 1 ? "free credit":"free credits")+" for your affiliation activity in Instantri.ch");
					affiliationUserMessage.setMessage( message);
					affiliationUserMessage.setAffiliationUser(affiliationUser);
					affiliationUserMessage.setAffiliationUserId(affiliationUser.getId());
					
					
					affiliationUserMessageDAO.setAffiliationUserMessage(affiliationUserMessage);
					
					AffiliationUserMessageMailObject affiliationUserMessageMailObject = new AffiliationUserMessageMailObject( message, Utils.getBaseUrl());
					Mail mail = new Mail( affiliationUser.getEmail(), "We gave you " + credits + " "+(credits == 1 ? "free credit":"free credits")+" for your affiliation activity in Instantri.ch",  affiliationUserMessageMailObject);
					mail.start();
					System.out.println("-----Enviando a " + user.getEmail() + " el mensaje: " + message);
					
					Thread.sleep(14000);
				}	
			}
		}
		
		return mapping.findForward( "ok");
	}

}		
