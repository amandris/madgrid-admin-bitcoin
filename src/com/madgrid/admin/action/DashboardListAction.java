package com.madgrid.admin.action;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.util.BalanceData;
import com.madgrid.admin.util.PaymentUserData;
import com.madgrid.admin.util.Utils;
import com.madgrid.dao.AffiliationActivityDAO;
import com.madgrid.dao.AffiliationPaymentDAO;
import com.madgrid.dao.BitcoinPaymentDAO;
import com.madgrid.dao.UserDAO;
import com.madgrid.dao.WinDAO;
import com.madgrid.model.AffiliationActivity;
import com.madgrid.model.AffiliationPayment;
import com.madgrid.model.BitcoinPayment;
import com.madgrid.model.GridHistoric;
import com.madgrid.model.Item;
import com.madgrid.model.User;
import com.madgrid.model.Win;
import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

public class DashboardListAction extends Action {
	
	

	
	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		BitcoinPaymentDAO bitcoinPaymentDAO = new BitcoinPaymentDAO();
		WinDAO winDAO = new WinDAO();
		UserDAO userDAO = new UserDAO();
		AffiliationActivityDAO affiliationActivityDAO = new AffiliationActivityDAO();
		HashMap<String, Double> bitcoinPaymentUserMap = new HashMap<String, Double>();
		List<PaymentUserData> paymentUserDataList = new ArrayList<PaymentUserData>();
		
		Criteria criteria = new Criteria();
		
		List<BitcoinPayment> bitcoinPaymentList = bitcoinPaymentDAO.getBitcoinPaymentListByCriteria( criteria, null);
		
		List<Win> winList = winDAO.getWinListByCriteria(criteria, null);
		
		double bitcoinPaymentSum = 0d;
		double winSum = 0d;
		
		//Calculamos todo lo pagado en afiliacion
		Criteria affiliationCriteria = new Criteria();
		affiliationCriteria.addEqualTo("type", AffiliationActivity.AFFILIATION_ACTIVITY_USER_CREDITS);
		affiliationCriteria.addEqualTo("alreadyPayed", true);
		List<AffiliationActivity> affiliationActivityList = affiliationActivityDAO.getAffiliationActivityListByCriteria(affiliationCriteria);
		double affiliationPaymentSum = 0d;
		for( AffiliationActivity affiliationActivity:affiliationActivityList){
			affiliationPaymentSum = affiliationPaymentSum + affiliationActivity.getAssignedBitcoins();
		}
		
		request.setAttribute("affiliationPaymentSum", affiliationPaymentSum);
		SimpleDateFormat formatter= new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		ParsePosition 		pos 		= new ParsePosition( 0);
		
		for( BitcoinPayment bitcoinPayment:bitcoinPaymentList){
			
			User user = userDAO.getUserById( bitcoinPayment.getUserId());
			
			if( user != null){
				bitcoinPayment.setPayerId(user.getLogin());
			} else{
				bitcoinPayment.setPayerId(null);
			}
			
			String createdString = bitcoinPayment.getCreated().replace("T", " ").replace("-07:00", "");
			
			bitcoinPayment.setCreatedDate( formatter.parse( createdString));
			
			bitcoinPaymentSum = bitcoinPaymentSum + bitcoinPayment.getBitcoins() * 0.00000001d;
			
			if( bitcoinPaymentUserMap.get(user.getLogin()) == null){
				bitcoinPaymentUserMap.put(user.getLogin(), bitcoinPayment.getBitcoins() * 0.00000001d);
			} else{
				bitcoinPaymentUserMap.put(user.getLogin(), bitcoinPaymentUserMap.get(user.getLogin()).doubleValue() + (bitcoinPayment.getBitcoins() * 0.00000001d));
			}
		}
		

		for( String login: bitcoinPaymentUserMap.keySet()){
			criteria = new Criteria();
			criteria.addEqualTo("login", login);
			User user = userDAO.getUserByCriteria(criteria);
			
			Criteria criteria3 = new Criteria();
			criteria3.addEqualTo("userId", user.getId());
			Integer paymentCount = bitcoinPaymentDAO.getBitcoinPaymentCountByCriteria(criteria3);
			
			PaymentUserData paymentUserData = new PaymentUserData();
			paymentUserData.setId( user.getId().toString());
			paymentUserData.setLogin( user.getLogin());
			paymentUserData.setBitcoins(bitcoinPaymentUserMap.get(login) );
			paymentUserData.setPayments( paymentCount);
			paymentUserDataList.add(paymentUserData);
		}
		
		Collections.sort( paymentUserDataList, new Comparator<PaymentUserData>() {
			public int compare( PaymentUserData g1, PaymentUserData g2) {
				return g2.getBitcoins().compareTo(g1.getBitcoins());
			}
		});
		
		for( Win win:winList){
			winSum = winSum + win.getItem().getBitcoins();
		}
		
		request.setAttribute( "bitcoinPaymentList", bitcoinPaymentList);
		request.setAttribute( "bitcoinPaymentListSize", bitcoinPaymentList.size());
		request.setAttribute( "winList", winList);
		request.setAttribute( "winListSize", winList.size());
		request.setAttribute( "bitcoinPaymentSum", bitcoinPaymentSum);
		request.setAttribute( "paymentUserDataList", paymentUserDataList);
		request.setAttribute( "winSum", winSum);
		
		//Calculamos el numero de usuarios
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

		
		//Grafica de balance
		List<BalanceData> balanceDataList = new ArrayList<BalanceData>();
		
		for( long time=startTime.getTimeInMillis();time<=Utils.today().getTime();time=time+(86400000)){//1 dia
			balanceDataList.add( getBalance( time, bitcoinPaymentList, affiliationActivityList, winList));
		}
		
		request.setAttribute( "balanceDataList", balanceDataList);
		
		//Calculamos ingresos-gastos por día
		List<BalanceData> incomeList = new ArrayList<BalanceData>();
		
		for( long time=startTime.getTimeInMillis();time<=Utils.today().getTime();time=time+(86400000)){//1 dia
			double income = 0d;
			incomeList.add( getIncome( time, bitcoinPaymentList, winList));
		}
		
		request.setAttribute( "incomeList", incomeList);
		
		
		//Grafica de usuarios
		int userCount = 0;
		List<Integer> userCountList = new ArrayList<Integer>();
		
		while( endTime.getTime().before(Utils.today())){
			Criteria timeCriteria = new Criteria();
			timeCriteria.addGreaterOrEqualThan("created", startTime.getTime());
			timeCriteria.addLessThan("created", endTime.getTime());
			userCount = userCount  + userDAO.getUserCountByCriteria(timeCriteria);
			
			
			userCountList.add( userCount);
			
			startTime.add(GregorianCalendar.DAY_OF_MONTH, 1);
			endTime.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}
		
		request.setAttribute( "userCountList", userCountList);
		
		
		
		
		return mapping.findForward( "ok");
	}
	
	private BalanceData getBalance(long date, List<BitcoinPayment> bitcoinpaymentList, List<AffiliationActivity> affiliationActivityList, List<Win> winList){
		double in = 0;
		double out1 = 0;
		double out2 = 0;
		
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
				out1 = out1 + win.getItem().getBitcoins();
			}
		}
		
		out2 = out1;
		for( AffiliationActivity affiliationActivity:affiliationActivityList){
			if( affiliationActivity.getCreated().before( endTime.getTime()) ){
				out2 = out2 + affiliationActivity.getAssignedBitcoins();
			}
		}
		
		
		
		
		double balance1 = in - out1;
		double balance2 = in - out2;
		
		BalanceData balanceData = new BalanceData();
		balanceData.setIn(in);
		balanceData.setOut(out1);
		balanceData.setOut2(out2);
		balanceData.setDate(new Date(date));
		balanceData.setBalance(balance1);
		balanceData.setBalance2(balance2);
		
		return balanceData;
	}
	
	
	private BalanceData getIncome(long date, List<BitcoinPayment> bitcoinpaymentList, List<Win> winList){
		double in = 0;
		double out = 0;
		
		BalanceData balanceData = new BalanceData();
		
		GregorianCalendar startTime = new GregorianCalendar();
		startTime.setTime( new Date(date));
		
		startTime.set( GregorianCalendar.HOUR, 0 );
		startTime.set( GregorianCalendar.MINUTE, 0 );
		startTime.set( GregorianCalendar.SECOND, 0);
		startTime.set( GregorianCalendar.MILLISECOND, 0); 
		
		GregorianCalendar endTime = new GregorianCalendar();
		endTime.setTime( new Date(date));
		
		endTime.set( GregorianCalendar.HOUR, 23 );
		endTime.set( GregorianCalendar.MINUTE, 59 );
		endTime.set( GregorianCalendar.SECOND, 59);
		endTime.set( GregorianCalendar.MILLISECOND, 999); 
		
		for( BitcoinPayment bitcoinPayment:bitcoinpaymentList){
			if( bitcoinPayment.getCreatedDate().after( startTime.getTime()) && bitcoinPayment.getCreatedDate().before( endTime.getTime()) ){
				in = in + bitcoinPayment.getBitcoins() * 0.00000001d;
			}
		}
		
		for( Win win:winList){
			if( win.getCreated().after( startTime.getTime()) && win.getCreated().before( endTime.getTime()) ){
				if( (win.getItem().getType()==Item.ITEM_TYPE_ONLY_BITCOINS || win.getItem().getType()==Item.ITEM_TYPE_CREDITS_AND_BITCOINS ) && win.getIsOnlyCredits() == false)
				out = out + win.getItem().getBitcoins();
			}
		}
		
		double income = in - out;
		
		balanceData.setBalance(income);
		balanceData.setIn(in);
		balanceData.setOut(out * (-1));
		
		return balanceData;
	}

}		
