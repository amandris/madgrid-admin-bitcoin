package com.madgrid.admin.action;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.UserForm;
import com.madgrid.admin.util.BalanceData;
import com.madgrid.admin.util.PaymentUserData;
import com.madgrid.admin.util.Utils;
import com.madgrid.dao.BitcoinPaymentDAO;
import com.madgrid.dao.UserDAO;
import com.madgrid.dao.UserHistoricDAO;
import com.madgrid.dao.UserMessageDAO;
import com.madgrid.dao.WinDAO;
import com.madgrid.model.BitcoinPayment;
import com.madgrid.model.Item;
import com.madgrid.model.User;
import com.madgrid.model.UserHistoric;
import com.madgrid.model.UserMessage;
import com.madgrid.model.Win;


public class UserEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		UserDAO userDAO = new UserDAO();
		UserMessageDAO userMessageDAO = new UserMessageDAO();
		BitcoinPaymentDAO bitcoinPaymentDAO = new BitcoinPaymentDAO();
		UserHistoricDAO userHistoricDAO = new UserHistoricDAO();
		User user = null; 
		String id = (String)request.getParameter("id");

		if( id !=  null){
			user = userDAO.getUserById( Integer.parseInt( id));
			
			UserForm userForm = new UserForm();
			BeanUtils.copyProperties(userForm, user);
			
			userForm.setPassword( null);
			request.setAttribute( "userForm", userForm);
			request.setAttribute( "user", user);
			
			Criteria criteria = new Criteria();
			criteria.addEqualTo( "userId", user.getId());
			
			List<UserMessage> userMessageList = userMessageDAO.getUserMessageListByCriteria(criteria);
			request.setAttribute( "userMessageList", userMessageList);
			
			
			criteria = new Criteria();
			criteria.addEqualTo( "userId", user.getId());
			List<BitcoinPayment> bitcoinPaymentList = bitcoinPaymentDAO.getBitcoinPaymentListByCriteriaAndRange(criteria, "id", 0, 20);
			request.setAttribute( "bitcoinPaymentList", bitcoinPaymentList);
			
			List<UserHistoric> userHistoricList = userHistoricDAO.getUserHistoricListByCriteriaAndRange(criteria, "created", 0, 20);
			request.setAttribute( "userHistoricList", userHistoricList);
			
			//Calculo de bt gastados y ganados
			criteria = new Criteria();
			criteria.addEqualTo( "userId", user.getId());
			List<BitcoinPayment> allBitcoinPaymentList = bitcoinPaymentDAO.getBitcoinPaymentListByCriteria(criteria, "id");
			double bitcoinSpent = 0d;
			for( BitcoinPayment bitcoinPayment:allBitcoinPaymentList){
				bitcoinSpent = bitcoinSpent + (bitcoinPayment.getBitcoins() / 100000000d);
			}

			
			double bitcoinWon = 0d;
			WinDAO winDAO = new WinDAO();
			criteria = new Criteria();
			criteria.addEqualTo( "userId", user.getId());
			List<Win> winList = (List<Win>)winDAO.getWinListByCriteria(criteria, null);
			for( Win win:winList){
				if( !win.getIsOnlyCredits() && win.getItem().getBitcoins() != null && win.getItem().getBitcoins().doubleValue() != 0){
					bitcoinWon = bitcoinWon + win.getItem().getBitcoins();
				}
			}
			
			request.setAttribute( "bitcoinSpent", bitcoinSpent);
			request.setAttribute( "bitcoinWon", bitcoinWon);
			
			
			

			//Grafica de balance
			GregorianCalendar startTime = new GregorianCalendar();
			startTime.set( GregorianCalendar.DAY_OF_MONTH, 1 );
			startTime.set( GregorianCalendar.MONTH, 4 );
			startTime.set( GregorianCalendar.YEAR, 2014 );
			startTime.set( GregorianCalendar.HOUR, 0 );
			startTime.set( GregorianCalendar.MINUTE, 0 );
			startTime.set( GregorianCalendar.SECOND, 0);
			startTime.set( GregorianCalendar.MILLISECOND, 0);
			
			GregorianCalendar endTime = new GregorianCalendar();
			endTime.setTime( startTime.getTime());
			endTime.set( GregorianCalendar.HOUR, 23 );
			endTime.set( GregorianCalendar.MINUTE, 59 );
			endTime.set( GregorianCalendar.SECOND, 59);
			endTime.set( GregorianCalendar.MILLISECOND, 999);
			
			SimpleDateFormat formatter= new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");

			List<BalanceData> balanceDataList = new ArrayList<BalanceData>();
			
			for( BitcoinPayment bitcoinPayment:allBitcoinPaymentList){
				String createdString = bitcoinPayment.getCreated().replace("T", " ").replace("-07:00", "");
				
				bitcoinPayment.setCreatedDate( formatter.parse( createdString));
			}
			
			for( long time=startTime.getTimeInMillis();time<=Utils.today().getTime();time=time+(86400000)){//1 dia
				balanceDataList.add( getBalance( time, allBitcoinPaymentList, winList));
			}
			
			request.setAttribute( "balanceDataList", balanceDataList);
			
			
		}		
		return mapping.findForward( "ok");
	}
	
	private BalanceData getBalance(long date, List<BitcoinPayment> bitcoinpaymentList, List<Win> winList){
		double in = 0;
		double out = 0;
		
		GregorianCalendar endTime = new GregorianCalendar();
		endTime.setTime( new Date(date));
		
		endTime.set( GregorianCalendar.HOUR, 23 );
		endTime.set( GregorianCalendar.MINUTE, 59 );
		endTime.set( GregorianCalendar.SECOND, 59);
		endTime.set( GregorianCalendar.MILLISECOND, 999); 
		
		for( BitcoinPayment bitcoinPayment:bitcoinpaymentList){
			if( bitcoinPayment.getCreatedDate().before( endTime.getTime())){
				in = in + bitcoinPayment.getBitcoins() * 0.00000001d;
			}
		}
		
		for( Win win:winList){
			if( win.getCreated().before( endTime.getTime()) ){
				if( (win.getItem().getType()==Item.ITEM_TYPE_ONLY_BITCOINS || win.getItem().getType()==Item.ITEM_TYPE_CREDITS_AND_BITCOINS ) && win.getIsOnlyCredits() == false)
				out = out + win.getItem().getBitcoins();
			}
		}
		
		
		double balance = in - out;
		
		BalanceData balanceData = new BalanceData();
		balanceData.setIn(in);
		balanceData.setOut(out);
		balanceData.setDate(new Date(date));
		balanceData.setBalance(balance);
		
		return balanceData;
	}

}		
