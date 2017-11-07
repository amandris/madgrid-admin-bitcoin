package com.madgrid.admin.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.UserMessageForm;
import com.madgrid.admin.util.Utils;
import com.madgrid.admin.util.mail.UserMessageMailObject;
import com.madgrid.dao.UserDAO;
import com.madgrid.dao.UserMessageDAO;
import com.madgrid.model.User;
import com.madgrid.model.UserMessage;
import com.madgrid.admin.util.Mail;

public class UserMessageAllSendAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		UserDAO userDAO = new UserDAO();
		UserMessageDAO userMessageDAO = new UserMessageDAO();
		UserMessageForm userMessageForm = (UserMessageForm) form;
		ActionErrors errors	= new ActionErrors();
		
		
		if( Utils.nullOrBlank( userMessageForm.getSubject())){
			errors.add( "subject",new ActionError( "errors.required"));
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}
		if( Utils.nullOrBlank( userMessageForm.getMessage())){
			errors.add( "message",new ActionError( "errors.required"));
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}
		
		Criteria criteria = new Criteria();
		criteria.addEqualTo( "active", true);
		criteria.addEqualTo( "isFraudulent", false);
		criteria.addEqualTo( "validated", true);
		criteria.addEqualTo( "isSubscribed", true);
		criteria.addNotEqualTo( "email", "javiergomezdequero@gmail.com");
	
		List<User> userList = userDAO.getUserListByCriteria(criteria, "lastLogin");
		
		int i = 0;
		
		for(User user:userList){
			try{
				i++;
				UserMessage userMessage = new UserMessage();
				userMessage.setCreated( Utils.today());
				userMessage.setSubject( userMessageForm.getSubject());
				userMessage.setMessage( userMessageForm.getMessage());
				userMessage.setUser(user);
				userMessage.setUserId(user.getId());
				
				userMessageDAO.setUserMessage(userMessage);
				
				UserMessageMailObject userMessageMailObject = new UserMessageMailObject( userMessageForm.getMessage(), user.getEmail(), user.getLogin(), Utils.getBaseUrl());
				Mail mail = new Mail( user.getEmail(), userMessageForm.getSubject(),  userMessageMailObject);
				mail.start();
				
				System.out.println("-----Enviado mensaje a " + user.getEmail() + " (" + i + "/" + userList.size()+")");
				
			
				Thread.sleep(18500);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		
		
			
		return mapping.findForward( "ok");
	}

}		
