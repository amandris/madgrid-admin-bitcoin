package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.PromoCodeForm;
import com.madgrid.dao.PromoCodeDAO;
import com.madgrid.model.PromoCode;


public class PromoCodeEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		PromoCodeDAO promoCodeDAO = new PromoCodeDAO();
		PromoCode promoCode = null; 
		String id = (String)request.getParameter("id");

		if( id !=  null){
			promoCode = promoCodeDAO.getPromoCodeById( Integer.parseInt( id));
			PromoCodeForm promoCodeForm = new PromoCodeForm();
			
			promoCodeForm.setId( promoCode.getId());
			promoCodeForm.setCode( promoCode.getCode());
			promoCodeForm.setType( promoCode.getType());
			promoCodeForm.setCount( promoCode.getCount());

			request.setAttribute( "promoCodeForm", promoCodeForm);
		}		
		return mapping.findForward( "ok");
	}

}		
