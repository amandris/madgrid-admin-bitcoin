package com.madgrid.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.TweetDAO;
import com.madgrid.dao.WinDAO;
import com.madgrid.model.Tweet;
import com.madgrid.model.Win;

public class TweetListAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		TweetDAO tweetDAO = new TweetDAO();
		
		Criteria criteria = new Criteria();
		
		List<Tweet> tweetList = tweetDAO.getTweetListByCriteria( criteria, "created");
		
		request.setAttribute( "tweetList", tweetList);
		
		return mapping.findForward( "ok");
	}

}		
