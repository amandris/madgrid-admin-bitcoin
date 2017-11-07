package com.madgrid.admin.util.mail;

import com.madgrid.admin.util.Utils;

public class AffiliationUserMessageMailObject implements MailObject {

	private String 	baseUrl; 
	private String 	message;
	private String login;
	
	
	public AffiliationUserMessageMailObject( String message, String login, String baseUrl)
	{
		this.message = message;
		this.baseUrl = baseUrl;
		this.login = login;
	}
	
	public AffiliationUserMessageMailObject( String message, String baseUrl)
	{
		this.message = message;
		this.baseUrl = baseUrl;
		this.login = null;
	}

	public String toHtml()
	{
		StringBuffer result = new StringBuffer( "");
		
		
		result.append( "<!DOCTYPE html>");
		result.append( "<html>");
		result.append( "<head>");
		result.append( "<meta charset='utf-8'>");
		result.append( "<meta name='viewport' content='width=device-width, initial-scale=1'>");
		result.append( "<title>Instantri.ch</title>");
		result.append( "<style type='text/css'>");
		result.append( "body {");
		result.append( "background-color: #000;");
		result.append( "background-image: url(" + baseUrl+ "/img/bg.jpg);");
		result.append( "background-position: top;");
		result.append( "background-repeat: repeat-x;;");
		result.append( "font-family: Arial, sans-serif;");
		result.append( "font-size: 16px;");
		result.append( "padding-top: 40px;");
		result.append( "padding-bottom: 20px;");
		result.append( "color: #333;");
		result.append( "text-align: center;");
		result.append( "width: 100%;");
		result.append( "margin: 0;");
		result.append( "width: 100%;");
		result.append( "}");
		result.append( "table{");
		result.append( "max-width: 560px;");
		result.append( "width:100%");
		result.append( "}");
		result.append( "</style>");
		result.append( "</head>");
		result.append( "<body style='background-color:#000;background-image:url(" + baseUrl+ "/img/bg.jpg);background-position:top;background-repeat:repeat-x;font-family:Arial,sans-serif;font-size:16px;padding-top:40px;padding-bottom:20px;color:#333;text-align:center;width:100%;margin:0;width: 100%;'>");

		result.append( "    <table cellpadding='0' cellspacing='0' align='center' background='" + baseUrl+ "/img/header_bg.jpg' style='background-image:url(" + baseUrl+ "/img/header_bg.jpg);background-repeat:no-repeat;background-position:top right;background-color:#191919'>");
		result.append( "        <tr>");
		result.append( "            <td class='' align='left' valign='top' style='padding:18px 25px;width:100%;'>");
		result.append( "                <img src='" + baseUrl+ "/img/logo.png'>");
		result.append( "            </td>");
		result.append( "        </tr>");
		result.append( "    </table>");
		result.append( "    <table class='' cellpadding='0' cellspacing='0' align='center' style='background-color:#fff;padding:50px;'>");
		result.append( "        <tr>");
		result.append( "            <td class='' align='left' valign='middle' style=''>");
		if( !Utils.nullOrBlank(login)){
			result.append( "                <h1 style='font-size:24px;'>Hi "+login+"!</h1>");
		}
		result.append( "                <p>"+message+"</p>");
		result.append( "                <p align='right' style='margin-top:40px;'>InstantRi.ch team</p>");
		result.append( "            </td>");
		result.append( "        </tr>");
		result.append( "    </table>");
		result.append( "    <table class='footer' cellpadding='0' cellspacing='0' align='center' style='background-color:#454545;'>");
		result.append( "        <tr>");
		result.append( "            <td class='' align='center' valign='middle' style='width:100%;color:#999;'>");
		result.append( "                <p>Follow us on <a href='http://twitter.com/instantri_ch' style='font-weight:bold;color:#fff;text-decoration:none;'>Twitter</a> and <a href='http://www.facebook.com/instantri.ch.btc' style='font-weight:bold;color:#fff;text-decoration:none;'>Facebook</a></p>");
		result.append( "            </td>");
		result.append( "        </tr>");
		result.append( "    </table>");
		result.append( "    <table cellpadding='0' cellspacing='0' align='center' style='padding:25px;'>");
		result.append( "        <tr style=''>");
		result.append( "            <td class='' align='center' style='font-size:12px; color:#999;'>");
		result.append( "            </td>");
		result.append( "        </tr>");
		result.append( "    </table>");
		result.append( "</body>");
		result.append( "</html>");
		
		
		
		

		
		return result.toString();
	}
	
	public String toText()
	{
		StringBuffer result = new StringBuffer( "");
		if( !Utils.nullOrBlank(login)){
			result.append( "Hi " + login + ". \r\n");
		}
		result.append( message+"\r\n");
				
		return result.toString();
	}

	

}
