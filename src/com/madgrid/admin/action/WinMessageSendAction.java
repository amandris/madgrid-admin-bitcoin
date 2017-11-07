package com.madgrid.admin.action;

import java.text.DecimalFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.madgrid.admin.util.Utils;
import com.madgrid.admin.util.mail.BitcoinSentMailObject;
import com.madgrid.dao.UserMessageDAO;
import com.madgrid.dao.WinDAO;
import com.madgrid.model.UserMessage;
import com.madgrid.model.Win;
import com.madgrid.admin.util.Mail;

public class WinMessageSendAction extends Action {

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
			if( win.getUser().getBitcoinSentSubscribed()){
				try{
					DecimalFormat numberFormat = new DecimalFormat("#.###");
					
					BitcoinSentMailObject bitcoinSentMailMailObject = new BitcoinSentMailObject( win.getUser().getLogin(), win.getItem().getBitcoins(), win.getUser().getEmail(), Utils.getBaseUrl());
					Mail mail = new Mail( win.getUser().getEmail(), "We've just sent the " + numberFormat.format( win.getItem().getBitcoins()) + " BTC you won in Instantri.ch",  bitcoinSentMailMailObject);
					mail.start();
					
					Thread.sleep(1000);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		
		
		response.getWriter().println( "ok");
			
		return null;
	}

}		
