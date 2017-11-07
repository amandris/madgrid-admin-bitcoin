package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.madgrid.admin.util.Utils;
import com.madgrid.admin.util.mail.TweetMailObject;
import com.madgrid.dao.UserDAO;
import com.madgrid.dao.UserHistoricDAO;
import com.madgrid.dao.UserMessageDAO;
import com.madgrid.dao.TweetDAO;
import com.madgrid.model.User;
import com.madgrid.model.UserHistoric;
import com.madgrid.model.UserMessage;
import com.madgrid.model.Tweet;
import com.madgrid.admin.util.Mail;

public class TweetMessageSendAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		TweetDAO tweetDAO = new TweetDAO();
		UserDAO userDAO = new UserDAO();
		UserHistoricDAO userHistoricDAO = new UserHistoricDAO();
		
		
		String idString = request.getParameter("id");
		
		int id = Integer.parseInt( idString);
		
		Tweet tweet = tweetDAO.getTweetById(id);
		
		if( tweet != null && !tweet.getPaid() ){
			try{
				User user = userDAO.getUserById( tweet.getUser().getId());
				
				user.setCredits(user.getCredits() + 1);
				userDAO.setUser(user);
				
				TweetMailObject tweetMailMailObject = new TweetMailObject( user.getLogin(),  Utils.getBaseUrl());
				Mail mail = new Mail( tweet.getUser().getEmail(), "We've just gave you one credit for your tweet.",  tweetMailMailObject);
				mail.start();
				
				UserHistoric userHistoric = new UserHistoric();
				userHistoric.setCreated( Utils.today());
				userHistoric.setType( UserHistoric.GET_CREDIT_FOR_TWEET);
				userHistoric.setUser(user);
				userHistoric.setUserId(user.getId());
				
				userHistoricDAO.setUserHistoric(userHistoric);
				
				Thread.sleep(1000);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		response.getWriter().println( "ok");
			
		return null;
	}

}		
