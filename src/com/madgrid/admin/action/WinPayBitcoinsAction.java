package com.madgrid.admin.action;

import java.text.DecimalFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.coinapult.api.httpclient.CoinapultClient;
import com.coinapult.api.httpclient.Transaction;
import com.madgrid.admin.util.Utils;
import com.madgrid.admin.util.mail.BitcoinSentMailObject;
import com.madgrid.dao.WinDAO;
import com.madgrid.model.Win;
import com.madgrid.admin.util.Mail;

public class WinPayBitcoinsAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		WinDAO winDAO = new WinDAO();
		
		String idString = request.getParameter("id");
		
		int id = Integer.parseInt( idString);
		
	
		Win win = winDAO.getWinById(id);
		
		if( win != null && win.getItem().getBitcoins() > 0 && win.getDeliveryRequested() && !win.getItemSent()){
			
			CoinapultClient cli = new CoinapultClient("0987a2dfdd5f98a49ae6ab984cbeef","bc79fb3f9adb53c2366c7c5c8cc50de7a2bffcaf6bc836a71845904da201");
			
			String transactionId = null;
			
			try{
				Transaction.Json result = cli.send(win.getItem().getBitcoins(), "BTC", win.getBitcoinAddress(), 0, null, null, null);
				
				transactionId = result.tid;
			}catch (Exception e){
				e.printStackTrace();
				throw e;
			}
			
			if( transactionId != null){
			
				win.setItemSent(true);
				win.setTransactionId(transactionId);
				win.setModified(Utils.today());

				winDAO.setWin(win);
				
				if( win.getUser().getBitcoinSentSubscribed()){
					try{
						DecimalFormat numberFormat = new DecimalFormat("#.###");
						
						BitcoinSentMailObject bitcoinSentMailMailObject = new BitcoinSentMailObject( win.getUser().getLogin(), win.getItem().getBitcoins(), win.getUser().getEmail(), Utils.getBaseUrl());
						Mail mail = new Mail( win.getUser().getEmail(), "We've just sent the " + numberFormat.format( win.getItem().getBitcoins()) + " BTC you won in Instantri.ch",  bitcoinSentMailMailObject);
						mail.start();
						
						Thread.sleep(500);
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			} else{
				
				return mapping.findForward( "ko");
			}
		}
		
		
		return mapping.findForward( "ok");
	}

}		
