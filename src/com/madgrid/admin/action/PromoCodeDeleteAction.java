package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.PromoCodeDAO;
import com.madgrid.model.PromoCode;

public class PromoCodeDeleteAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		PromoCodeDAO promoCodeDAO = new PromoCodeDAO();

		String id=request.getParameter( "id");
		
		if( id != null){
			PromoCode promoCode = promoCodeDAO.getPromoCodeById( Integer.parseInt( id));
			promoCodeDAO.deletePromoCode( promoCode);
		}
		
		return mapping.findForward( "ok");
	}

}		
