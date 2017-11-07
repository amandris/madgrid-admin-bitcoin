package com.madgrid.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.BitcoinPaymentDAO;
import com.madgrid.dao.BoxDAO;
import com.madgrid.dao.MessageDAO;
import com.madgrid.dao.UserDAO;
import com.madgrid.dao.UserHistoricDAO;
import com.madgrid.dao.UserMessageDAO;
import com.madgrid.dao.WinDAO;
import com.madgrid.model.BitcoinPayment;
import com.madgrid.model.Box;
import com.madgrid.model.Message;
import com.madgrid.model.User;
import com.madgrid.model.UserHistoric;
import com.madgrid.model.UserMessage;
import com.madgrid.model.Win;

public class UserDeleteAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		UserDAO userDAO = new UserDAO();
		BoxDAO boxDAO = new BoxDAO();
		WinDAO winDAO = new WinDAO();
		UserHistoricDAO userHistoricDAO = new UserHistoricDAO();
		UserMessageDAO userMessageDAO = new UserMessageDAO();
		MessageDAO messageDAO = new MessageDAO();
		BitcoinPaymentDAO bitcoinPaymentDAO = new BitcoinPaymentDAO();

		String id=request.getParameter( "id");
		
		if( id != null){
			User user = userDAO.getUserById( Integer.parseInt( id));
			
			Criteria criteria = new Criteria();
			criteria.addEqualTo("userId", user.getId());
			List<Box> boxList = boxDAO.getBoxListByCriteria(criteria, null);
			
			//Box
			if( boxList != null && boxList.size() > 0){
				return mapping.findForward( "ko");
			}
			
			//Win
			List<Win> winList = winDAO.getWinListByCriteria(criteria, null);
			
			if( winList != null && winList.size() > 0){
				return mapping.findForward( "ko");
			}
			
			//UserHistoric
			List<UserHistoric> userHistoricList = userHistoricDAO.getUserHistoricListByCriteria(criteria, null);
			
			if( userHistoricList != null && userHistoricList.size() > 0){
				for( UserHistoric userHistoric:userHistoricList){
					userHistoricDAO.deleteUserHistoric(userHistoric);
				}
			}
			
			//UserMessage
			List<UserMessage> userMessageList = userMessageDAO.getUserMessageListByCriteria(criteria);
			
			if( userMessageList != null && userMessageList.size() > 0){
				for( UserMessage userMessage:userMessageList){
					userMessageDAO.deleteUserMessage(userMessage);
				}
			}
			
			//Message
			List<Message> messageList = messageDAO.getMessageListByCriteria(criteria, null);
			
			if( messageList != null && messageList.size() > 0){
				for( Message message:messageList){
					messageDAO.deleteMessage(message);
				}
			}
			
			//BitcoinPayment
			List<BitcoinPayment> bitcoinPaymentList = bitcoinPaymentDAO.getBitcoinPaymentListByCriteria(criteria, null);
			
			if( bitcoinPaymentList != null && bitcoinPaymentList.size() > 0){
				for( BitcoinPayment bitcoinPayment:bitcoinPaymentList){
					bitcoinPaymentDAO.deleteBitcoinPayment(bitcoinPayment);
				}
			}
			
			userDAO.deleteUser( user);
		}
		
		return mapping.findForward( "ok");
	}

}		
