package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.PromotionDAO;
import com.madgrid.model.Promotion;

public class PromotionDeleteAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		PromotionDAO promotionDAO = new PromotionDAO();

		String id=request.getParameter( "id");
		
		if( id != null){
			Promotion promotion = promotionDAO.getPromotionById( Integer.parseInt( id));
			promotionDAO.deletePromotion( promotion);
		}
		
		return mapping.findForward( "ok");
	}

}		
