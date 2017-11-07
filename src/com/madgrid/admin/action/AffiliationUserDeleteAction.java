package com.madgrid.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.AffiliationActivityDAO;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.dao.UserDAO;
import com.madgrid.model.AffiliationActivity;
import com.madgrid.model.AffiliationUser;
import com.madgrid.model.User;

public class AffiliationUserDeleteAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		AffiliationActivityDAO affiliationActivityDAO = new AffiliationActivityDAO();
		UserDAO userDAO = new UserDAO();
		
		
		String id=request.getParameter( "id");
		
		if( id != null){
			AffiliationUser affiliationUser = affiliationUserDAO.getAffiliationUserById( Integer.parseInt( id));
			
			if( affiliationUser != null){
				Criteria criteria = new Criteria();
				criteria.addEqualTo("companyAffiliationUserId", affiliationUser.getId());
				List<User> userList = userDAO.getUserListByCriteria(criteria);
				if( userList != null && userList.size() > 0){
					for( User user:userList){
						user.setCompanyAffiliationUser(null);
						user.setCompanyAffiliationUserId(null);
						
						userDAO.setUser(user);
					}
				}
				
				criteria = new Criteria();
				criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
				List<AffiliationActivity> affiliationActivityList = affiliationActivityDAO.getAffiliationActivityListByCriteria(criteria);
				if( affiliationActivityList != null && affiliationActivityList.size() > 0){
					for( AffiliationActivity affiliationActivity:affiliationActivityList){
						affiliationActivityDAO.deleteAffiliationActivity(affiliationActivity);
					}
				}
				
				
				affiliationUserDAO.deleteAffiliationUser( affiliationUser);
			}
		}
		
		return mapping.findForward( "ok");
	}

}		
