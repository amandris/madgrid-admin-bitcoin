package com.madgrid.admin.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.UserForm;
import com.madgrid.admin.util.Mail;
import com.madgrid.admin.util.Utils;
import com.madgrid.admin.util.mail.AddCreditsByAdminMailObject;
import com.madgrid.admin.util.mail.UserMessageMailObject;
import com.madgrid.dao.UserDAO;
import com.madgrid.dao.UserHistoricDAO;
import com.madgrid.model.User;
import com.madgrid.model.UserHistoric;

public class UserSaveAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		UserDAO userDAO = new UserDAO();
		User user = null;
		UserForm userForm = (UserForm) form;
		ActionErrors errors	= new ActionErrors();
		Date today = Utils.today();
		
		if( userForm.getId() == 0){
			if( Utils.nullOrBlank( userForm.getPassword())){
				errors.add( "password",new ActionError( "errors.required"));
				saveErrors	( request, errors);
				return mapping.getInputForward();
			}
			user = new User();
			BeanUtils.copyProperties(user, userForm);
			user.setId( null);
			user.setCreated( today);
			user.setLastLogin( today);
			user.setModified( today);
			user.setIsBeginner( true);
			user.setIsFraudulent( false);
			user.setStatisticsBoughtBoxes(0);
			user.setStatisticsPlayedGames(0);
			user.setStatisticsUsedCredits(0);
			user.setStatisticsWonGames(0);
			user.setIsSubscribed(true);
			user.setAutoPay(false);
			user.setBitcoinAddress(null);
			user.setRequestPrizeSubscribed(true);
			user.setBitcoinSentSubscribed(true);
		} else{
			user = userDAO.getUserById( userForm.getId());
			Integer previousCredits = user.getCredits();
			String password = user.getPassword();
			BeanUtils.copyProperties(user, userForm);
			user.setModified( today);
			if( Utils.nullOrBlank( userForm.getPassword())){
				user.setPassword( password);
			} else {
				user.setPassword( Utils.digest(userForm.getPassword()));
			}
			
			//Si hay un cambio de créditos generar el UserHistoric
			if( user.getCredits() > previousCredits){
				UserHistoricDAO userHistoricDAO = new UserHistoricDAO();
				UserHistoric userHistoric = new UserHistoric();
				userHistoric.setCreated( today);
				userHistoric.setType( UserHistoric.GET_CREDIT_BY_ADMIN_MORE);
				userHistoric.setUser(user);
				userHistoric.setUserId(user.getId());
				int diffCredits = user.getCredits().intValue() - previousCredits.intValue();
				userHistoric.setValue1(new Double(diffCredits));
				
				userHistoricDAO.setUserHistoric(userHistoric);
				
				AddCreditsByAdminMailObject addCreditsByAdminMailObject = new AddCreditsByAdminMailObject( user.getLogin(), diffCredits, true, Utils.getBaseUrl());
				Mail mail = new Mail( user.getEmail(), "We've added " + diffCredits + " credits to your account",  addCreditsByAdminMailObject);
				mail.start();
			} else if( user.getCredits() < previousCredits){
				UserHistoricDAO userHistoricDAO = new UserHistoricDAO();
				UserHistoric userHistoric = new UserHistoric();
				userHistoric.setCreated( today);
				userHistoric.setType( UserHistoric.GET_CREDIT_BY_ADMIN_LESS);
				userHistoric.setUser(user);
				userHistoric.setUserId(user.getId());
				int diffCredits = previousCredits.intValue() - user.getCredits().intValue();
				userHistoric.setValue1(new Double(diffCredits));
				
				userHistoricDAO.setUserHistoric(userHistoric);
				
				AddCreditsByAdminMailObject addCreditsByAdminMailObject = new AddCreditsByAdminMailObject( user.getLogin(), diffCredits, false, Utils.getBaseUrl());
				Mail mail = new Mail( user.getEmail(), "We've removed " + diffCredits + " credits from your account",  addCreditsByAdminMailObject);
				mail.start();
			}
		}
		
		userDAO.setUser(user);
		
		return mapping.findForward( "ok");
	}

}		
