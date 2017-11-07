package com.madgrid.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.MessageDAO;
import com.madgrid.model.Message;

public class MessageListAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		MessageDAO messageDAO = new MessageDAO();
		
		List<Message> messageList = messageDAO.getMessageListByCriteria( new Criteria(), "created");
		
		request.setAttribute( "messageList", messageList);
		
		return mapping.findForward( "ok");
	}

}		
