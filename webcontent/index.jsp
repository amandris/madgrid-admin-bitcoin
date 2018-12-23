<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>


<html>
<head>
	<title>Administración Instantri.ch</title>
</head>
<body>
	<table width="100%" style="font-family:Arial;">
		<tr height="200px">
			<td align="center">	
				<span style="font-size:18px">Administración Instantri.ch</span>
			</td>
		</tr>
		<tr>
			<td align="center">
				<table style="border:1px solid gray;width:300px;height:150px;font-size:12px;" cellspacing="10">
					<html:form action="/adminuser/login">
						<tr>
							<td width="50%" align="right">
								Login
							</td>
							<td width="50%" align="left">
								<html:text property="login"/>
							</td>
						</tr>
						<tr>
							<td width="50%" align="right">
								Password
							</td>
							<td width="50%" align="left">
								<html:password property="password"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<span style="color:red"><b><html:errors/></b></span>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<html:submit value="Submit"/>
							</td>
						</tr>
					</html:form>
				</table>
			</td>
		</tr>
	</table>
	
</body>


