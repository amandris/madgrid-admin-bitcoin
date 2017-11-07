package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.WinForm;
import com.madgrid.dao.WinDAO;
import com.madgrid.model.Win;


public class WinEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		WinDAO winDAO = new WinDAO();
		Win win = null; 
		String id = (String)request.getParameter("id");

		if( id !=  null){
			win = winDAO.getWinById( Integer.parseInt( id));
			
			WinForm winForm = new WinForm();
			BeanUtils.copyProperties(winForm, win);
			
			request.setAttribute( "winForm", winForm);
			request.setAttribute( "win", win);
		}		
		return mapping.findForward( "ok");
	}

}		
