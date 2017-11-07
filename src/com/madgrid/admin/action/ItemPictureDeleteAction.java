package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RedirectingActionForward;

import com.madgrid.dao.ItemDAO;
import com.madgrid.model.Item;

public class ItemPictureDeleteAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		ItemDAO itemDAO = new ItemDAO();

		String id=request.getParameter( "id");
		String picture=request.getParameter( "picture");
		
		if( id != null){
			Item item = itemDAO.getItemById( Integer.parseInt( id));
			
			if( picture.equals("1")){
				item.setPicture1Url( null);
			}
			
			itemDAO.setItem( item);
			
			return new RedirectingActionForward("/do/item/edit?id=" + item.getId());
		}
		
		
		
		return mapping.findForward( "ok");
	}

}		
