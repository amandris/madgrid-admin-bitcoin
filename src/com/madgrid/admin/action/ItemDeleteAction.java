package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.ItemDAO;
import com.madgrid.model.Item;

public class ItemDeleteAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		ItemDAO itemDAO = new ItemDAO();

		String id=request.getParameter( "id");
		
		if( id != null){
			Item item = itemDAO.getItemById( Integer.parseInt( id));
			itemDAO.deleteItem( item);
		}
		
		return mapping.findForward( "ok");
	}

}		
