package com.madgrid.admin.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.PromotionForm;
import com.madgrid.admin.util.Utils;

import com.madgrid.dao.PromotionDAO;
import com.madgrid.model.Promotion;

public class PromotionSaveAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		PromotionDAO promotionDAO = new PromotionDAO();
		Promotion promotion = null;
		PromotionForm promotionForm = (PromotionForm) form;
		ActionErrors errors	= new ActionErrors();
		
		Date startDate = Utils.parseDateAndTime( promotionForm.getStartDate());
		Date endDate = Utils.parseDateAndTime( promotionForm.getEndDate());
		
		if( startDate == null){
			errors.add( "startDate",new ActionError( "errors.invalid"));
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}
		
		if( endDate == null){
			errors.add( "endDate",new ActionError( "errors.invalid"));
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}
		
		if( promotionForm.getId() == 0){
			promotion = new Promotion();
			promotion.setId( null);
			promotion.setCreated( Utils.today());
		} else{
			promotion = promotionDAO.getPromotionById( promotionForm.getId());
		}
		
		promotion.setModified( Utils.today());
		promotion.setDescription( promotionForm.getDescription());
		promotion.setType( promotionForm.getType());
		promotion.setName( promotionForm.getName());
		promotion.setStartDate(startDate);
		promotion.setEndDate(endDate);
		
		promotionDAO.setPromotion(promotion);
		return mapping.findForward( "ok");
	}
}		
