<%@page import="com.madgrid.model.AdminUser"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<tiles:insert template='../tile.jsp'>
	<tiles:put name='menu' content='menu.jsp'/>
	<tiles:put name='body' type='string'>
	
	<script	type="text/javascript">
	
	
		
	</script>
	<span style="font-family:Arial;font-size:18px;">Error</span>
	
	<table width="90%" border="0" cellpadding="0" cellspacing="30" align="center" valign="top">
		<tr>
			<td>
				No se ha podido realizar el pago
			</td>
		</tr>
		<tr>
			<td>
				<a href="<html:rewrite page="/do/win/listAll"/>">Volver al listado</a>
			</td>
		</tr>
	</table>

	

</tiles:put>
</tiles:insert>


