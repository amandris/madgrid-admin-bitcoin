package com.madgrid.admin.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

public class UserMessageSendAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		UserDAO userDAO = new UserDAO();
		UserMessageDAO userMessageDAO = new UserMessageDAO();
		User user = null;
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
	
		
		user = userDAO.getUserById( userMessageForm.getId());
		
		UserMessage userMessage = new UserMessage();
		userMessage.setCreated( Utils.today());
		userMessage.setSubject( userMessageForm.getSubject());
		userMessage.setMessage( userMessageForm.getMessage());
		userMessage.setUser(user);
		userMessage.setUserId(user.getId());
		
		userMessageDAO.setUserMessage(userMessage);
		
		
		UserMessageMailObject userMessageMailObject = new UserMessageMailObject( userMessageForm.getMessage(), user.getEmail(), user.getLogin(),  Utils.getBaseUrl());
		Mail mail = new Mail( user.getEmail(), userMessageForm.getSubject(),  userMessageMailObject);
		mail.start();
			
		return mapping.findForward( "ok");
	}

}		
