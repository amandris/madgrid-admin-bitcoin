package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.AnswerForm;

import com.madgrid.admin.util.Utils;
import com.madgrid.admin.util.mail.AnswerMailObject;
import com.madgrid.dao.MessageDAO;
import com.madgrid.dao.UserDAO;
import com.madgrid.dao.UserMessageDAO;
import com.madgrid.model.Message;
import com.madgrid.model.User;
import com.madgrid.model.UserMessage;
import com.madgrid.admin.util.Mail;

public class AnswerSendAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		MessageDAO messageDAO = new MessageDAO();
		UserMessageDAO userMessageDAO = new UserMessageDAO();
		UserDAO userDAO = new UserDAO();
		AnswerForm answerForm = (AnswerForm) form;
		ActionErrors errors	= new ActionErrors();
		User user = null;
		
		if( Utils.nullOrBlank( answerForm.getMessage())){
			errors.add( "message",new ActionError( "errors.required"));
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}
	
		Message message = messageDAO.getMessageById( answerForm.getId());

		if( message != null){
			if( message.getUser() != null){
				user = userDAO.getUserById( message.getUserId());
			}
			
			AnswerMailObject answerMailObject = new AnswerMailObject( message.getText(), answerForm.getMessage(), (user != null ? user.getLogin() : null), Utils.getBaseUrl());
			Mail mail = new Mail( (user != null ? user.getEmail() : message.getEmail()), "Re: "+message.getSubject(),  answerMailObject);
			mail.start();
			
			UserMessage userMessage = new UserMessage();
			userMessage.setCreated( Utils.today());
			userMessage.setSubject( "Re: "+message.getSubject());
			userMessage.setMessage("Hi " + (user != null ? user.getLogin() : "") + ". " + answerForm.getMessage());
			userMessage.setUser(user);
			userMessage.setUserId(user.getId());
			userMessageDAO.setUserMessage(userMessage);
			
		} 
			
		return mapping.findForward( "ok");
	}

}		
