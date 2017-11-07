package com.madgrid.admin.util.mail;

import com.madgrid.admin.util.Utils;

public class MessageAllPromotionMailObject implements MailObject {

	private String 	baseUrl;
	private String 	title1;
	private String 	picture1;
	private String 	message1;
	private String 	title2;
	private String 	picture2;
	private String 	message2;
	private String 	title3;
	private String 	picture3;
	private String 	message3;
	private String 	email;
	private String 	login;
	
	
	public MessageAllPromotionMailObject( String title1, String picture1, String message1, String title2, String picture2, String message2, String title3, String picture3, String message3, String email, String login, String baseUrl)
	{
		this.title1 = title1;
		this.picture1 = picture1;
		this.message1 = message1;
		this.title2 = title2;
		this.picture2 = picture2;
		this.message2 = message2;
		this.title3 = title3;
		this.picture3 = picture3;
		this.message3 = message3;
		this.baseUrl = baseUrl;
		this.email = email;
		this.login = login;
	}

	public String toHtml()
	{
		StringBuffer result = new StringBuffer( "");
		
		result.append( "<!DOCTYPE html>");
		result.append( "<html>");
		result.append( "<head>");
		result.append( "    <meta charset='utf-8'>");
		result.append( "    <meta name='viewport' content='width=device-width, initial-scale=1'>");
		result.append( "    <title>Instantri.ch</title>");
		result.append( "    <style type='text/css'>");
		result.append( "        body {");
		result.append( "            background-color: #000;");
		result.append( "            background-image: url(" + baseUrl+ "/img/bg.jpg);");
		result.append( "            background-position: top;");
		result.append( "            background-repeat: repeat-x;;");
		result.append( "            font-family: Arial, sans-serif;");
		result.append( "            font-size: 16px;");
		result.append( "            padding-top: 40px;");
		result.append( "            padding-bottom: 20px;");
		result.append( "            color: #333;");
		result.append( "            text-align: center;");
		result.append( "            width: 100%;");
		result.append( "            margin: 0;");
		result.append( "            width: 100%;");
		result.append( "        }");
		result.append( "        table{");
		result.append( "            max-width: 560px;");
		result.append( "            width:100%");
		result.append( "        }");
		result.append( "        @media only screen and (max-width: 560px) {");
		result.append( "            h1{");
		result.append( "                font-size: 30px !important;");
		result.append( "            }");
		result.append( "            td[class='col'],");
		result.append( "            td[class='colCenterXS']{");
		result.append( "                display: block;");
		result.append( "                width: 100% !important;");
		result.append( "                -moz-box-sizing: border-box !important;");
		result.append( "                -webkit-box-sizing: border-box !important;");
		result.append( "                box-sizing: border-box !important;");
		result.append( "            }");
		result.append( "            td[class='colCenterXS']{");
		result.append( "                text-align: center !important;");
		result.append( "                padding-left: 0px !important;");
		result.append( "            }");
		result.append( "        }");
		result.append( "    </style>");
		result.append( "</head>");
		result.append( "<body style='background-color:#000;background-image:url(" + baseUrl+ "/img/bg.jpg);background-position:top;background-repeat:repeat-x;font-family:Arial,sans-serif;font-size:16px;padding-top:40px;padding-bottom:20px;color:#333;text-align:center;width:100%;margin:0;width: 100%;'>");
		result.append( "    <table cellpadding='0' cellspacing='0' align='center'background='" + baseUrl+ "/img/header_bg.jpg'  style='background-image:url(" + baseUrl+ "/img/header_bg.jpg);background-repeat:no-repeat;background-position:top right;background-color:#191919'>");
		result.append( "        <tr>");
		result.append( "            <td class='' align='left' valign='top' style='padding:18px 25px;width:100%;'>");
		result.append( "                <img src='" + baseUrl+ "/img/logo.png'>");
		result.append( "            </td>");
		result.append( "        </tr>");
		result.append( "    </table>");
		result.append( "    <table cellpadding='0' cellspacing='0' align='center' style='background-color:#fff;padding:25px;'>");
		result.append( "        <tr>");
		result.append( "            <td class='' align='center' valign='top' style='padding:18px 25px;width:100%;'>");
		result.append( "                <h1 style='font-size:18px;'>Hey "+login+", Check out our <strong style='color:#f60068;'>new promotions!</strong></h1>");
		result.append( "            </td>");
		result.append( "        </tr>");
		result.append( "    </table>");
		result.append( "    <table class='' cellpadding='0' cellspacing='0' align='center' style='background-color:#e6e6e6;padding:15px 25px;'>");
		result.append( "        <tr>");
		result.append( "            <td class='col' align='center' valign='middle' style='width:80px;'>");
		result.append( "                <img src='"+picture1+"'>");
		result.append( "            </td>");
		result.append( "            <td class='colCenterXS' align='left' valign='middle' style='padding-left:40px;'>");
		result.append( "                <h2 style='font-size:20px;'>"+title1+"</h2>");
		result.append( "                <p>"+message1+"</p>");
		result.append( "            </td>");
		result.append( "        </tr>");
		result.append( "    </table>");
		if( !Utils.nullOrBlank( title2) && !Utils.nullOrBlank(picture1) && !Utils.nullOrBlank(message1)){
			result.append( "    <table class='' cellpadding='0' cellspacing='0' align='center' style='background-color:#eb0164;padding:15px 25px;color:#ffc4dd;'>");
			result.append( "        <tr>");
			result.append( "            <td class='col' align='center' valign='middle' style='width:80px;'>");
			result.append( "                <img src='"+picture2+"'>");
			result.append( "            </td>");
			result.append( "            <td class='colCenterXS' align='left' valign='middle' style='padding-left:40px;'>");
			result.append( "                <h2 style='font-size:20px;color:#fff;'>"+title2+"</h2>");
			result.append( "                <p>"+message2+"</p>");
			result.append( "            </td>");
			result.append( "        </tr>");
			result.append( "    </table>");
		}
		if( !Utils.nullOrBlank( title3) && !Utils.nullOrBlank(picture3) && !Utils.nullOrBlank(message3)){
			result.append( "    <table class='' cellpadding='0' cellspacing='0' align='center' style='background-color:#7a9d15;padding:15px 25px;color:#d7eb9e;'>");
			result.append( "        <tr>");
			result.append( "            <td class='col' align='center' valign='middle' style='width:80px;'>");
			result.append( "                <img src='"+picture3+"'>");
			result.append( "            </td>");
			result.append( "            <td class='colCenterXS' align='left' valign='middle' style='padding-left:40px;'>");
			result.append( "                <h2 style='font-size:20px;color:#fff;'>"+title3+"</h2>");
			result.append( "                <p>"+message3+"</p>");
			result.append( "            </td>");
			result.append( "        </tr>");
			result.append( "    </table>");
		}
		result.append( "    <table class='' cellpadding='0' cellspacing='0' align='center' style='background-color:#fff;padding:25px;'>");
		result.append( "         <tr>");
		result.append( "            <td class='' colspan='2' align='center' valign='middle' style='padding-top:20px;'>");
		result.append( "                <a href='" + baseUrl+ "'><img src='" + baseUrl+ "/img/playnow.png'></a>");
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
		result.append( "                <p>If you no longer want to receive this kind of emails <a href='"+baseUrl+"/do/unsubscribe?email="+email+"&user="+login+"' style='color:#bbb;'>unsubscribe here</a></p>");
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
		result.append( "Hey "+login+", Check out our <strong style='color:#f60068;'>new promotions!\r\n");
		result.append( title1 + "\r\n");
		result.append( message1 + "\r\n");
		if( !Utils.nullOrBlank( title2) && !Utils.nullOrBlank(picture1) && !Utils.nullOrBlank(message1)){
			result.append( title2 + "\r\n");
			result.append( message2 + "\r\n");
		}
		if( !Utils.nullOrBlank( title3) && !Utils.nullOrBlank(picture3) && !Utils.nullOrBlank(message3)){
			result.append( title3 + "\r\n");
			result.append( message3 + "\r\n");
		}
				
		return result.toString();
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getPicture1() {
		return picture1;
	}

	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}

	public String getMessage1() {
		return message1;
	}

	public void setMessage1(String message1) {
		this.message1 = message1;
	}

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getPicture2() {
		return picture2;
	}

	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}

	public String getMessage2() {
		return message2;
	}

	public void setMessage2(String message2) {
		this.message2 = message2;
	}

	public String getTitle3() {
		return title3;
	}

	public void setTitle3(String title3) {
		this.title3 = title3;
	}

	public String getPicture3() {
		return picture3;
	}

	public void setPicture3(String picture3) {
		this.picture3 = picture3;
	}

	public String getMessage3() {
		return message3;
	}

	public void setMessage3(String message3) {
		this.message3 = message3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	

}
