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

public class WinListDeliveryRequestedAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		WinDAO winDAO = new WinDAO();
		Criteria criteria = new Criteria();
		criteria.addEqualTo( "deliveryRequested", true);
		criteria.addEqualTo( "itemSent", false);
		
		List<Win> winList = winDAO.getWinListByCriteria( criteria, "created");
		
		request.setAttribute( "winList", winList);
		
		return mapping.findForward( "ok");
	}

}		
