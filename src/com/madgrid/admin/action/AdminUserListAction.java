package com.madgrid.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.AdminUserDAO;
import com.madgrid.model.AdminUser;

public class AdminUserListAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AdminUserDAO adminUserDAO = new AdminUserDAO();
		
		List<AdminUser> adminUserList = adminUserDAO.getAdminUserListByCriteria( new Criteria());
		
		request.setAttribute( "adminUserList", adminUserList);
		
		return mapping.findForward( "ok");
	}

}		
