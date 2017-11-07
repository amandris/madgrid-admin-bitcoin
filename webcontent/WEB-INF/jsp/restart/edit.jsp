<%@page import="com.madgrid.model.Parameter"%>
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
		function deleteRestart(){
			if( confirm('¿Desea cancelar el reinicio?')){
				document.location.href='<html:rewrite page="/do/restart/delete"/>';
			}
		}
	</script>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<html:form action="/parameter/save">
					<html:hidden name="parameterForm" property="id"/>
					<table width="70%">
						<c:if test="${isScheduled}">
							<tr>
								<td style="text-align:center;font-size:19px;">
									Hay un reinicio programado a las <span style="font-size:19px;font-weight:bold;color:red;"><c:out value="${scheduledTime}"/></span>
									<br/><br/><br/>
									<input style="font-size:19px;"  type="button" onclick="deleteRestart();" value="Cancelar reinicio"/>
								</td>
								<td>
									<img src="<html:rewrite page="/img/clock.png"/>"/>
								</td>
							</tr>
						</c:if>
						<c:if test="${!isScheduled}">
							<tr>
								<td style="text-align:center;font-size:19px;">
									¿Desea programar reinicio de servidor a las <span style="font-size:19px;font-weight:bold;color:red;"><c:out value="${scheduledTime}"/></span>?
									<br/><br/><br/>
									<input style="font-size:19px;" type="button" onclick="document.location.href='<html:rewrite page="/do/restart/save"/>';" value="Programar reinicio"/>
								</td>
							</tr>
						</c:if>
					</table>
				</html:form>
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


