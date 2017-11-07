package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.PromotionForm;
import com.madgrid.admin.util.Utils;
import com.madgrid.dao.PromotionDAO;
import com.madgrid.model.Promotion;


public class PromotionEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		PromotionDAO promotionDAO = new PromotionDAO();
		Promotion promotion = null; 
		String id = (String)request.getParameter("id");

		if( id !=  null){
			promotion = promotionDAO.getPromotionById( Integer.parseInt( id));
			PromotionForm promotionForm = new PromotionForm();
			
			promotionForm.setId( promotion.getId());
			promotionForm.setName( promotion.getName());
			promotionForm.setDescription( promotion.getDescription());
			promotionForm.setType( promotion.getType());
			promotionForm.setStartDate( Utils.getDate(promotion.getStartDate()) + " " + Utils.getTime(promotion.getStartDate()));
			promotionForm.setEndDate( Utils.getDate(promotion.getEndDate()) + " " + Utils.getTime(promotion.getEndDate()));

			request.setAttribute( "promotionForm", promotionForm);
			request.setAttribute( "promotion", promotion);
		}		
		return mapping.findForward( "ok");
	}

}		
