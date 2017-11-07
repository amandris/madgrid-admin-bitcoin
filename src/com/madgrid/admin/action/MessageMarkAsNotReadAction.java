package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.MessageDAO;
import com.madgrid.model.Message;

public class MessageMarkAsNotReadAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		MessageDAO messageDAO = new MessageDAO();

		String id=request.getParameter( "id");
		
		if( id != null){
			Message message = messageDAO.getMessageById( Integer.parseInt( id));
			message.setIsRead( false);
			messageDAO.setMessage( message);
		}
		
		return mapping.findForward( "ok");
	}

}		
