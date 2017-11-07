package com.madgrid.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.WinDAO;
import com.madgrid.model.Win;

public class WinPostedListAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		WinDAO winDAO = new WinDAO();
		
		Criteria criteria = new Criteria();
		
		criteria.addEqualTo( "itemSent", true);
		
		List<Win> winList = winDAO.getWinListByCriteria( criteria, "created");
		
		request.setAttribute( "winList", winList);
		request.setAttribute( "isSent", true);
		request.setAttribute( "isPosted", true);
		
		return mapping.findForward( "ok");
	}

}		
