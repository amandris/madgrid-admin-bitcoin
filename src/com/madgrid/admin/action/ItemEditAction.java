package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.ItemForm;
import com.madgrid.dao.ItemDAO;
import com.madgrid.model.Item;


public class ItemEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		ItemDAO itemDAO = new ItemDAO();
		Item item = null; 
		String id = (String)request.getParameter("id");

		if( id !=  null){
			item = itemDAO.getItemById( Integer.parseInt( id));
			ItemForm itemForm = new ItemForm();
			
			itemForm.setId( item.getId());
			itemForm.setName( item.getName());
			itemForm.setCreated( item.getCreated());
			itemForm.setModified( item.getModified());
			itemForm.setHtmlDescription( item.getHtmlDescription());
			itemForm.setVirtualPath( item.getVirtualPath());
			itemForm.setCredits(item.getCredits());
			itemForm.setBitcoins(item.getBitcoins());
			itemForm.setType(item.getType());

			request.setAttribute( "itemForm", itemForm);
			request.setAttribute( "item", item);
		}		
		return mapping.findForward( "ok");
	}

}		
