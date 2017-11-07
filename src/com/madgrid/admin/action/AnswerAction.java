package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.AnswerForm;
import com.madgrid.dao.MessageDAO;
import com.madgrid.model.Message;



public class AnswerAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		MessageDAO messageDAO = new MessageDAO();
		Message message = null; 
		String id = (String)request.getParameter("id");

		if( id !=  null){
			message = messageDAO.getMessageById( Integer.parseInt( id));
			
			AnswerForm answerForm = new AnswerForm();
			
			answerForm.setId( message.getId());
			request.setAttribute( "answerForm", answerForm);
			request.setAttribute( "message", message);
		}		
		return mapping.findForward( "ok");
	}

}		
