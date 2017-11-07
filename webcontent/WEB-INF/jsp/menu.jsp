<%@page import="com.madgrid.dao.TweetDAO"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%

	AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
	UserDAO userDAO = new UserDAO();
	MessageDAO messageDAO = new MessageDAO();
	ItemDAO itemDAO = new ItemDAO();
	GridDAO gridDAO = new GridDAO();
	WinDAO winDAO = new WinDAO();
	PromotionDAO promotionDAO = new PromotionDAO();
	TweetDAO tweetDAO = new TweetDAO();
	Criteria criteria = new Criteria();
	int affiliationUserCount = affiliationUserDAO.getAffiliationUserCountByCriteria( criteria);
	int userCount = userDAO.getUserCountByCriteria( criteria);
	int messageCount = messageDAO.getMessageCountByCriteria( criteria);
	Criteria criteria2 = new Criteria();
	criteria2.addEqualTo( "isRead", false);
	int notReadMessageCount = messageDAO.getMessageCountByCriteria( criteria2);
	int itemCount = itemDAO.getItemCountByCriteria( criteria);
	int gridCount = gridDAO.getGridCountByCriteria( criteria);
	criteria2 = new Criteria();
	criteria2.addEqualTo( "ongoing", true);
	criteria2.addEqualTo( "finished", false);
	int ongoingGridCount = gridDAO.getGridCountByCriteria( criteria2);
	int winCount = winDAO.getWinCountByCriteria( criteria);
	Criteria criteria3 = new Criteria();
	criteria3 = new Criteria();
	criteria3.addEqualTo( "deliveryRequested", true);
	criteria3.addEqualTo( "itemSent", false);
	int deliveryRequestedWinCount = winDAO.getWinCountByCriteria( criteria3);
	int promotionCount = promotionDAO.getPromotionCountByCriteria( criteria);
	Criteria requestedPaymentCriteria = new Criteria();
	requestedPaymentCriteria.addEqualTo("askedForTransfer", true);
	int requestedPaymentCount = affiliationUserDAO.getAffiliationUserCountByCriteria(requestedPaymentCriteria);
	
	criteria = new Criteria();
	criteria.addEqualTo( "paid", false);
	int tweetsUnpaidCount = tweetDAO.getTweetCountByCriteria( criteria);
	int tweetsCount = tweetDAO.getTweetCountByCriteria( new Criteria());
	
%>


<%@page import="com.madgrid.dao.AffiliationUserDAO"%>
<%@page import="org.apache.ojb.broker.query.Criteria"%>
<%@page import="com.madgrid.dao.UserDAO"%>
<%@page import="com.madgrid.dao.MessageDAO"%>
<%@page import="com.madgrid.dao.ItemDAO"%>
<%@page import="com.madgrid.dao.GridDAO"%>
<%@page import="com.madgrid.dao.WinDAO"%>
<%@page import="com.madgrid.dao.PromotionDAO"%>
<table width="100%" cellpadding="0" cellspacing="0" border="0" >
	<tr>
		<td  bgcolor="#FFFFFF" >
			<script type="text/javascript" src="../../js/milonic_src.js"></script>	
			<div class=milonic><a href="http://www.milonic.com/">JavaScript Menu, DHTML Menu Powered By Milonic</a></div>
			<script	type="text/javascript">
				if(ns4)_d.write("<scr"+"ipt type=text/javascript src=../../js/mmenuns4.js><\/scr"+"ipt>");		
				  else _d.write("<scr"+"ipt type=text/javascript src=../../js/mmenudom.js><\/scr"+"ipt>"); 
			</script>
			<script type="text/javascript" src="../../js/menu_data.js"></script>	
			<script type="text/javascript">
				with(milonic=new menuname("Main Menu")){
					alwaysvisible=1;
					orientation="horizontal";
					style=menuStyle;
					top=0;
					aI("showmenu=Resumen;text=Resumen;url=<html:rewrite page="/do/dashboard/list"/>;");
					<%if( requestedPaymentCount > 0){%>
						aI("showmenu=Afiliados;text=Afiliados <span style='font-size:15px;color:#DD0000;font-weight:bold;'>(<%=requestedPaymentCount%>/<%=affiliationUserCount%>)</span>;url=<html:rewrite page="/do/affiliationuser/list"/>;");
					<%} else{%>
						aI("showmenu=Afiliados;text=Afiliados <span style='font-size:11px;'>(<%=requestedPaymentCount%>/<%=affiliationUserCount%>)</span>;url=<html:rewrite page="/do/affiliationuser/list"/>;");
					<%}%>
					
					aI("showmenu=Usuarios;text=Usuarios <span style='font-size:11px;'>(<%=userCount%>)</span>;url=<html:rewrite page="/do/user/list"/>;");
					
					
					<%if( notReadMessageCount > 0){%>
						aI("showmenu=Mensajes;text=Mensajes <span style='font-size:15px;color:#DD0000;font-weight:bold;'>(<%=notReadMessageCount%>/<%=messageCount%>)</span>;url=<html:rewrite page="/do/message/list"/>;");
					<%} else{%>
						aI("showmenu=Mensajes;text=Mensajes <span style='font-size:11px;'>(<%=notReadMessageCount%>/<%=messageCount%>)</span>;url=<html:rewrite page="/do/message/list"/>;");
					<%}%>
					
					
					
					aI("showmenu=Items;text=Items <span style='font-size:11px;'>(<%=itemCount%>)</span>;url=<html:rewrite page="/do/item/list"/>;");
					aI("showmenu=Juegos;text=Juegos <span style='font-size:11px;'>(<%=ongoingGridCount%>/<%=gridCount%>)</span>;url=<html:rewrite page="/do/grid/list"/>;");
					
					
					<%if( deliveryRequestedWinCount > 0){%>
						aI("showmenu=Ganados;text=Ganados <span style='font-size:15px;color:#DD0000;font-weight:bold;'>(<%=deliveryRequestedWinCount%>/<%=winCount%>)</span>;url=<html:rewrite page="/do/win/listAll"/>;");
					<%} else{%>
						aI("showmenu=Ganados;text=Ganados <span style='font-size:11px;'>(<%=deliveryRequestedWinCount%>/<%=winCount%>)</span>;url=<html:rewrite page="/do/win/listAll"/>;");
					<%}%>
					
					<%if( tweetsUnpaidCount > 0){%>
						aI("showmenu=Tweets;text=Tweets <span style='font-size:15px;color:#DD0000;font-weight:bold;'>(<%=tweetsUnpaidCount%>/<%=tweetsCount%>)</span>;url=<html:rewrite page="/do/tweet/list"/>;");
					<%} else{%>
						aI("showmenu=Tweets;text=Tweets <span style='font-size:11px;'>(<%=tweetsUnpaidCount%>/<%=tweetsCount%>)</span>;url=<html:rewrite page="/do/tweet/list"/>;");
					<%}%>
					
					
					aI("showmenu=Promociones;text=Promoción <span style='font-size:11px;'>(<%=promotionCount%>)</span>;url=<html:rewrite page="/do/promotion/list"/>;");
					aI("showmenu=PromoCodes;text=Códigos promocionales;url=<html:rewrite page="/do/promocode/list"/>;");
					aI("showmenu=Parametros;text=Parámetros;url=<html:rewrite page="/do/parameter/list"/>;");
					aI("showmenu=Reinicio;text=Reinicio;url=<html:rewrite page="/do/restart/edit"/>;");
					aI("showmenu=Salir;text=Salir;url=<html:rewrite page="/do/adminuser/logoff"/>;");
				}
				
				
				
				drawMenus();
			</script>	
		</td>
		<td width="2500" height="24" bgcolor="#AAAAAA" >
		</td>
	</tr>
</table>