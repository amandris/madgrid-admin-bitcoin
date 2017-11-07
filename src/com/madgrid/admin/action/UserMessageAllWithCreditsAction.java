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

public class UserMessageAllWithCreditsAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		UserDAO userDAO = new UserDAO();
		UserMessageDAO userMessageDAO = new UserMessageDAO();
		
		Date date = Utils.today();
		date.setTime( Utils.today().getTime() - 864000000); //10 días
		
		Criteria criteria = new Criteria();
		criteria.addEqualTo( "active", true);
		criteria.addEqualTo( "isFraudulent", false);
		criteria.addEqualTo( "validated", true);
		criteria.addEqualTo( "isSubscribed", true);
		criteria.addNotEqualTo( "email", "javiergomezdequero@gmail.com");
		criteria.addGreaterOrEqualThan("credits", 1);
		criteria.addLessOrEqualThan("lastLogin", date);
		
	
		List<User> userList = userDAO.getUserListByCriteria(criteria, "lastLogin");
		
		int i = 0;
		
		
		
		for(User user:userList){
			if( user.getCredits()>=1){
				try{
					i++;
					
					String message = "";
					
					if( user.getCredits() == 1){
						message = "Hi " + user.getLogin() + ". You still have 1 credit in your account. Don't worry, this credit will never expire but why don't you try to open a box with it? You can win some Bitcoins, more credits or even both. Good luck.";
					} else{
						message = "Hi " + user.getLogin() + ". You still have " + user.getCredits() + " credits in your account. Don't worry, these credits will never expire but why don't you try to open some boxes with them? You can win Bitcoins, more credits or even both. Good luck.";
					}
					
					UserMessage userMessage = new UserMessage();
					userMessage.setCreated( Utils.today());
					userMessage.setSubject( "You have unused credits in Instantri.ch");
					userMessage.setMessage( message);
					userMessage.setUser(user);
					userMessage.setUserId(user.getId());
					
					userMessageDAO.setUserMessage(userMessage);
					
					UserMessageMailObject userMessageMailObject = new UserMessageMailObject( message, user.getEmail(), user.getLogin(), Utils.getBaseUrl());
					Mail mail = new Mail( user.getEmail(), "You have unused credits in Instantri.ch",  userMessageMailObject);
					mail.start();
					
					System.out.println("-----Enviado mensaje a " + user.getEmail() + " (" + i + "/" + userList.size()+")");
					
					Thread.sleep(15000);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		
		
		
			
		return mapping.findForward( "ok");
	}

}		
