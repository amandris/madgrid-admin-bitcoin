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

public class PromoCodeSaveAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		PromoCodeDAO promoCodeDAO = new PromoCodeDAO();
		PromoCode promoCode = null;
		PromoCodeForm promoCodeForm = (PromoCodeForm) form;
		
		if( promoCodeForm.getId() == 0){
			promoCode = new PromoCode();
			promoCode.setId( null);
		} else{
			promoCode = promoCodeDAO.getPromoCodeById( promoCodeForm.getId());
		}
		
		promoCode.setCode( promoCodeForm.getCode());
		promoCode.setType( promoCodeForm.getType());
		promoCode.setCount(promoCodeForm.getCount());
		
		promoCodeDAO.setPromoCode(promoCode);
		return mapping.findForward( "ok");
	}
}		
