package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.TweetForm;
import com.madgrid.dao.TweetDAO;
import com.madgrid.model.Tweet;



public class TweetEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		TweetDAO tweetDAO = new TweetDAO();
		Tweet tweet = null; 
		String id = (String)request.getParameter("id");

		if( id !=  null){
			tweet = tweetDAO.getTweetById( Integer.parseInt( id));
			
			TweetForm tweetForm = new TweetForm();
			BeanUtils.copyProperties(tweetForm, tweet);
			
			request.setAttribute( "tweetForm", tweetForm);
			request.setAttribute( "tweet", tweet);
		}		
		return mapping.findForward( "ok");
	}

}		
