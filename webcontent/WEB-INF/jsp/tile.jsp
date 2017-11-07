<%@page import="java.util.Date"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%
response.setHeader( "Pragma", "no-cache" );
response.addHeader( "Cache-Control", "must-revalidate" );
response.addHeader( "Cache-Control", "no-cache" );
response.addHeader( "Cache-Control", "no-store" );
response.setDateHeader("Expires", 0);  

String ip = request.getRemoteAddr();
//oficina, casa, local, casa2
if( ip.equals("80.26.159.249") || ip.equals("80.26.159.251") || ip.equals ("77.228.91.164") || ip.equals ("127.0.0.1") || ip.equals("81.39.153.200")){


%>

<html:html>
	<head>
		<title>Madgrid Admin</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href="<html:rewrite page="/css/main.css"/>" rel="stylesheet"/>
		<script type="text/javascript" src="<html:rewrite page="/js/jquery-1.4.3.min.js"/>"></script>
		<script src="<html:rewrite page="/js/highcharts.js"/>"></script>
		<script src="<html:rewrite page="/js/modules/exporting.js"/>"></script>
	</head>
	<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
		<script languaje=javascript>
			
		</script>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" align="left" valign="top">
			<tr>
				<td>
					<table width="100%" align="left" cellpadding="0" cellspacing="0" border="0" >
						<tr>
							<td>
								<tiles:get name='menu'/>
							</td>
						</tr>
						<tr>
							<td height="40">
							</td>
						</tr>
						<tr>
							<td style="text-align:center;">
								<tiles:get name='body'/>
							</td>						
						</tr>	
					</table>			
				</td>
			</tr>
		</table>
	</body>
</html:html>
<%} %>
