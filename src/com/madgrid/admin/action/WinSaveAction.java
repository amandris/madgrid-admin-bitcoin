package com.madgrid.admin.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.WinForm;
import com.madgrid.admin.util.Utils;
import com.madgrid.dao.WinDAO;
import com.madgrid.model.Win;

public class WinSaveAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		WinDAO winDAO = new WinDAO();
		Win win = null;

		WinForm winForm = (WinForm) form;
		
		win = winDAO.getWinById( winForm.getId());
		
		BeanUtils.copyProperties(win, winForm);
		win.setModified( Utils.today());
		
		winDAO.setWin(win);
		
		return mapping.findForward( "ok");
	}

}		
