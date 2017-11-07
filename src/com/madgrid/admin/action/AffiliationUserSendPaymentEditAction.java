package com.madgrid.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.AffiliationUserForm;
import com.madgrid.dao.AffiliationActivityDAO;
import com.madgrid.dao.AffiliationContactDAO;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.dao.AffiliationUserMessageDAO;
import com.madgrid.model.AffiliationActivity;
import com.madgrid.model.AffiliationContact;
import com.madgrid.model.AffiliationUser;
import com.madgrid.model.AffiliationUserMessage;


public class AffiliationUserSendPaymentEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
				
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		AffiliationActivityDAO affiliationActivityDAO = new AffiliationActivityDAO();
		
		AffiliationUser affiliationUser = affiliationUserDAO.getAffiliationUserById(id);
		
		if( affiliationUser != null){
			Criteria criteria = new Criteria();
			criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
			criteria.addEqualTo("type", 2);
			criteria.addEqualTo("alreadyPayed", false);
			List<AffiliationActivity> affiliationActivityList = affiliationActivityDAO.getAffiliationActivityListByCriteria(criteria);
			
			double bitcoins = 0d;
			
			for( AffiliationActivity affiliationActivity:affiliationActivityList){
				bitcoins = bitcoins + affiliationActivity.getAssignedBitcoins();
			}
			
			request.setAttribute("affiliationUserId", affiliationUser.getId());
			request.setAttribute("affiliationUserLogin", affiliationUser.getLogin());
			request.setAttribute("affiliationUserWalletAddress", affiliationUser.getBitcoinAddress());
			request.setAttribute("bitcoins", bitcoins);
			
		}
			
		
		return mapping.findForward( "ok");
	}

}		
