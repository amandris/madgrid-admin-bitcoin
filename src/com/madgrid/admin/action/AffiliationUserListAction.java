package com.madgrid.admin.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.AffiliationActivityDAO;
import com.madgrid.dao.AffiliationContactDAO;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.dao.AffiliationUserMessageDAO;
import com.madgrid.model.AffiliationActivity;
import com.madgrid.model.AffiliationUser;

public class AffiliationUserListAction extends Action {

	
	public class AffiliationUserInfo{
		private AffiliationUser affiliationUser;
		private int affiliationUserMessages;
		private int affiliationContacts;
		private int userRegistered;
		private double bitcoinsPayed;
		private double bitcoinsNotPayed;
		
		
		public AffiliationUser getAffiliationUser() {
			return affiliationUser;
		}
		public void setAffiliationUser(AffiliationUser affiliationUser) {
			this.affiliationUser = affiliationUser;
		}
		public int getAffiliationUserMessages() {
			return affiliationUserMessages;
		}
		public void setAffiliationUserMessages(int affiliationUserMessages) {
			this.affiliationUserMessages = affiliationUserMessages;
		}
		public int getAffiliationContacts() {
			return affiliationContacts;
		}
		public void setAffiliationContacts(int affiliationContacts) {
			this.affiliationContacts = affiliationContacts;
		}
		public int getUserRegistered() {
			return userRegistered;
		}
		public void setUserRegistered(int userRegistered) {
			this.userRegistered = userRegistered;
		}
		public double getBitcoinsPayed() {
			return bitcoinsPayed;
		}
		public void setBitcoinsPayed(double bitcoinsPayed) {
			this.bitcoinsPayed = bitcoinsPayed;
		}
		public double getBitcoinsNotPayed() {
			return bitcoinsNotPayed;
		}
		public void setBitcoinsNotPayed(double bitcoinsNotPayed) {
			this.bitcoinsNotPayed = bitcoinsNotPayed;
		}
		
		
	}
	
	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		AffiliationUserMessageDAO affiliationUserMessageDAO = new AffiliationUserMessageDAO();
		AffiliationContactDAO affiliationContactDAO = new AffiliationContactDAO();
		AffiliationActivityDAO affiliationActivityDAO = new AffiliationActivityDAO();
		
		List<AffiliationUser> affiliationUserList = affiliationUserDAO.getAffiliationUserListByCriteria( new Criteria(),"lastLogin");
		Criteria criteria = new Criteria();
		List<AffiliationUserInfo> affiliationUserInfoList = new ArrayList<AffiliationUserInfo>();
		
		for( AffiliationUser affiliationUser: affiliationUserList){
			criteria = new Criteria();
			criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
			int affiliationUserMessages = affiliationUserMessageDAO.getAffiliationUserMessageCountByCriteria(criteria);
			int affiliationContacts = affiliationContactDAO.getAffiliationContactCountByCriteria(criteria);
			
			criteria = new Criteria();
			criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
			criteria.addEqualTo("type", 1);
			int userRegistered = affiliationActivityDAO.getAffiliationActivityCountByCriteria(criteria);
			
			criteria = new Criteria();
			criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
			criteria.addEqualTo("type", 2);
			List<AffiliationActivity> affiliationActivityList = affiliationActivityDAO.getAffiliationActivityListByCriteria(criteria);
			
			double bitcoinsPayed = 0d;
			double bitcoinsNotPayed = 0d;
			for( AffiliationActivity affiliationActivity:affiliationActivityList){
				if( affiliationActivity.getAlreadyPayed() == false){
					bitcoinsNotPayed = bitcoinsNotPayed + affiliationActivity.getAssignedBitcoins();
				} else{
					bitcoinsPayed = bitcoinsPayed + affiliationActivity.getAssignedBitcoins();
				}
			}
			
			
			AffiliationUserInfo affiliationUserInfo = new AffiliationUserInfo();
			affiliationUserInfo.setAffiliationUser(affiliationUser);
			affiliationUserInfo.setAffiliationContacts(affiliationContacts);
			affiliationUserInfo.setAffiliationUserMessages(affiliationUserMessages);
			affiliationUserInfo.setUserRegistered(userRegistered);
			affiliationUserInfo.setBitcoinsPayed(bitcoinsPayed);
			affiliationUserInfo.setBitcoinsNotPayed(bitcoinsNotPayed);
			affiliationUserInfoList.add(affiliationUserInfo);
		}
		
		
		request.setAttribute( "affiliationUserInfoList", affiliationUserInfoList);
		
		
		return mapping.findForward( "ok");
	}

}		
