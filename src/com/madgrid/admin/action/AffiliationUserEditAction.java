package com.madgrid.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.AffiliationUserForm;
import com.madgrid.dao.AffiliationActivityDAO;
import com.madgrid.dao.AffiliationContactDAO;
import com.madgrid.dao.AffiliationPaymentDAO;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.dao.AffiliationUserMessageDAO;
import com.madgrid.model.AffiliationActivity;
import com.madgrid.model.AffiliationContact;
import com.madgrid.model.AffiliationPayment;
import com.madgrid.model.AffiliationUser;
import com.madgrid.model.AffiliationUserMessage;


public class AffiliationUserEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		AffiliationPaymentDAO affiliationPaymentDAO = new AffiliationPaymentDAO();
		AffiliationUser affiliationUser = null; 
		String id = (String)request.getParameter("id");

		if( id !=  null){
			affiliationUser = affiliationUserDAO.getAffiliationUserById( Integer.parseInt( id));
			
			AffiliationUserForm affiliationUserForm = new AffiliationUserForm();
			BeanUtils.copyProperties(affiliationUserForm, affiliationUser);
			
			affiliationUserForm.setPassword( null);
			request.setAttribute( "affiliationUserForm", affiliationUserForm);
			
			
			AffiliationActivityDAO affiliationActivityDAO = new AffiliationActivityDAO();
			Criteria criteria = new Criteria();
			criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
			List<AffiliationActivity> affiliationActivityList = affiliationActivityDAO.getAffiliationActivityListByCriteria(criteria);
			
			request.setAttribute( "affiliationActivityList", affiliationActivityList);
			
			criteria = new Criteria();
			criteria.addEqualTo( "affiliationUserId", affiliationUser.getId());
			
			AffiliationUserMessageDAO affiliationUserMessageDAO = new AffiliationUserMessageDAO();
			List<AffiliationUserMessage> affiliationUserMessageList = affiliationUserMessageDAO.getAffiliationUserMessageListByCriteria(criteria);
			request.setAttribute( "affiliationUserMessageList", affiliationUserMessageList);
			
			
			AffiliationContactDAO affiliationContactDAO = new AffiliationContactDAO();
			List<AffiliationContact> affiliationContactList = affiliationContactDAO.getAffiliationContactListByCriteria(criteria);
			request.setAttribute( "affiliationContactList", affiliationContactList);
			
			List<AffiliationPayment> affiliationPaymentList = affiliationPaymentDAO.getAffiliationPaymentListByCriteria(criteria);
			request.setAttribute( "affiliationPaymentList", affiliationPaymentList);
			
		}		
		return mapping.findForward( "ok");
	}

}		
