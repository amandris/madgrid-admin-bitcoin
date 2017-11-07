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

public class TweetSaveAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		TweetDAO tweetDAO = new TweetDAO();
		Tweet tweet = null;

		TweetForm tweetForm = (TweetForm) form;
		
		tweet = tweetDAO.getTweetById( tweetForm.getId());
		
		BeanUtils.copyProperties(tweet, tweetForm);

		
		tweetDAO.setTweet(tweet);
		
		return mapping.findForward( "ok");
	}

}		
