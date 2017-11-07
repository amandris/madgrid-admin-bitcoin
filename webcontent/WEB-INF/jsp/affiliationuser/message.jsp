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

	<span style="font-family:Arial;font-size:18px;">Enviar mensaje a usuario afiliado</span>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<html:form action="/affiliationuser/messageSend">
					<html:hidden name="userMessageForm" property="id"/>
					<table width="70%" class="tab">
						<tr>
							<td>
								Enviar mensaje a 
							</td>
							<td>
								<c:out value="${affiliationUser.login}"/> (<c:out value="${affiliationUser.email}"/>)
							</td>
						</tr>
						<tr>
							<td>
								Asunto
							</td>
							<td>
								<html:text property="subject" />
							</td>
						</tr>
						<tr>
							<td>
								Mensaje
							</td>
							<td>
								<html:textarea property="message" cols="30" rows="5"/>
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="right">
								<input type="button" value="Cancelar" onclick="document.location.href='<html:rewrite page="/do/affiliationuser/list"/>'"/>
								<input type="submit" value="Ok" />
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


