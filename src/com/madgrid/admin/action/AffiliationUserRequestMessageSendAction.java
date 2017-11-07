package com.madgrid.admin.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.madgrid.admin.util.Utils;
import com.madgrid.admin.util.mail.AffiliationUserRequestMailObject;

import com.madgrid.dao.AffiliationUserDAO;

import com.madgrid.model.AffiliationUser;
import com.madgrid.admin.util.Mail;

public class AffiliationUserRequestMessageSendAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		
		String login = request.getParameter("login");
		String email = request.getParameter("email");

		
		if( !Utils.nullOrBlank(login) && !Utils.nullOrBlank( email)){
			
			Criteria criteria = new Criteria();
			criteria.addEqualTo("email", email);
			
			if( affiliationUserDAO.getAffiliationUserByCriteria(criteria) != null){
				response.getWriter().println( "Ya existe ese email");
				
				return null;
			}
			
			criteria = new Criteria();
			criteria.addEqualTo("login", login);
			
			if( affiliationUserDAO.getAffiliationUserByCriteria(criteria) != null){
				response.getWriter().println( "Ya existe ese login");
				
				return null;
			}
			
			
			try{
				AffiliationUser affiliationUser = new AffiliationUser();
				affiliationUser.setAskedForTransfer( false);
				affiliationUser.setBitcoinAddress("Not assigned");
				affiliationUser.setCreated(Utils.today());
				affiliationUser.setLastLogin(Utils.today());
				affiliationUser.setEmail(email);
				affiliationUser.setLogin(login);
				affiliationUser.setModified( Utils.today());
				affiliationUser.setName( login);
				affiliationUser.setPassword(Utils.digest("abc123"));
				affiliationUser.setPercentage(15d);
				affiliationUser.setUrl( "Not assigned");
				
				affiliationUserDAO.setAffiliationUser(affiliationUser);
				
				AffiliationUserRequestMailObject affiliationuserRequestMailObject = new AffiliationUserRequestMailObject(login, Utils.getBaseUrl());
				Mail mail = new Mail( email, "Affiliation request",  affiliationuserRequestMailObject);
				mail.start();
				Thread.sleep(1000);
				response.getWriter().println( "ok");
				
				return null;

			}catch (Exception e){
				e.printStackTrace();
				response.getWriter().println( "Error");
				
				return null;
			}
		}
		response.getWriter().println( "Error");
		
		return null;
		
		
	}

}		
