package com.madgrid.admin.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.AffiliationUserForm;
import com.madgrid.admin.util.Utils;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.dao.UserDAO;
import com.madgrid.model.AffiliationUser;
import com.madgrid.model.User;

public class AffiliationUserSaveAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		UserDAO userDAO = new UserDAO();
		AffiliationUser affiliationUser = null;
		AffiliationUserForm affiliationUserForm = (AffiliationUserForm) form;
		ActionErrors errors	= new ActionErrors();
		
		if( affiliationUserForm.getId() == 0){
			if( Utils.nullOrBlank( affiliationUserForm.getPassword())){
				errors.add( "password",new ActionError( "errors.required"));
			}
			
			Criteria criteria = new Criteria();
			criteria.addEqualTo("login", affiliationUserForm.getLogin());
			AffiliationUser affiliationUserToCheck = affiliationUserDAO.getAffiliationUserByCriteria(criteria);
			
			if( affiliationUserToCheck != null) {
				errors.add( "login",new ActionError( "errors.userexist"));
			}
			
			if( !errors.isEmpty()){
				saveErrors	( request, errors);
				return mapping.getInputForward();
			}
			
			affiliationUser = new AffiliationUser();
			BeanUtils.copyProperties(affiliationUser, affiliationUserForm);
			affiliationUser.setId( null);
			affiliationUser.setCreated( Utils.today());
			affiliationUser.setLastLogin( Utils.today());
			affiliationUser.setAskedForTransfer( false);
			affiliationUser.setModified( Utils.today());
			affiliationUser.setPassword( Utils.digest(affiliationUserForm.getPassword()));
		} else{
			affiliationUser = affiliationUserDAO.getAffiliationUserById( affiliationUserForm.getId());
			Date createdDate = affiliationUser.getCreated();
			String password = affiliationUser.getPassword();
			BeanUtils.copyProperties(affiliationUser, affiliationUserForm);
			affiliationUser.setCreated( createdDate);
			affiliationUser.setModified( Utils.today());
			affiliationUser.setAskedForTransfer( affiliationUserForm.getAskedForTransfer());
			if( Utils.nullOrBlank( affiliationUserForm.getPassword())){
				affiliationUser.setPassword( password);
			} else {
				affiliationUser.setPassword( Utils.digest(affiliationUserForm.getPassword()));
			}
			
		}
		
		affiliationUserDAO.setAffiliationUser(affiliationUser);
		
		return mapping.findForward( "ok");
	}

}		
