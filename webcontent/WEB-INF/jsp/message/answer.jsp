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

	<span style="font-family:Arial;font-size:18px;">Responder por email al usuario</span>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<html:form action="/answer/send">
					<html:hidden name="answerForm" property="id"/>
					<table width="70%" class="tab">

						<tr>
							<td>
								To
							</td>
							<td>
								<c:out value="${message.email}"/>
							</td>
						</tr>
						<tr>
							<td>
								Subject
							</td>
							<td>
								Re: <c:out value="${message.subject}"/>
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
								<input type="button" value="Cancelar" onclick="document.location.href='<html:rewrite page="/do/message/edit"/>?id=${message.id}'"/>
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


