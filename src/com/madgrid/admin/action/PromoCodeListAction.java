package com.madgrid.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.PromoCodeDAO;
import com.madgrid.model.PromoCode;

public class PromoCodeListAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		PromoCodeDAO promoCodeDAO = new PromoCodeDAO();
		
		List<PromoCode> promoCodeList = promoCodeDAO.getPromoCodeListByCriteria( new Criteria(), "code");
		
		request.setAttribute( "promoCodeList", promoCodeList);
		
		return mapping.findForward( "ok");
	}

}		
