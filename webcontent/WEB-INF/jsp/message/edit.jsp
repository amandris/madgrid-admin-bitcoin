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
		
		function affiliationUserRequestSend(email, login){
			if( confirm('¿Desea crear el affiliationUser y enviar el mensaje al usuario?')){
				$.post("<html:rewrite page="/do/affiliationuser/requestMessageSend"/>", { email: email, login: login}, function(data){
					alert(data);
				});
			}
		}
		
		function answer(id){
			document.location.href='<html:rewrite page="/do/answer/edit"/>?id=' + id;
		}
		
		
		
	</script>

	<span style="font-family:Arial;font-size:18px;">Ver mensaje</span>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
					
				<table width="70%" class="tab">
					<tr>
						<td>
							Id
						</td>
						<td>
							<c:out value="${message.id}"/>
						</td>
					</tr>
					<tr>
						<td>
							Created
						</td>
						<td>
							<fmt:formatDate value="${message.created}" pattern="dd/MM/yyyy HH:mm"/>
						</td>
					</tr>
					<tr>
						<td>
							User
						</td>
						<td>
							<c:if test="${empty message.user}">
								-
							</c:if>
							<c:if test="${not empty message.user}">
								<a href="<html:rewrite page="/do/user/edit?id="/><c:out value="${message.user.id}"/>"><c:out value="${message.user.login}"/></a>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>
							Email
						</td>
						<td>
							<c:if test="${empty message.email}">
								-
							</c:if>
							<c:if test="${not empty message.email}">
								<c:out value="${message.email}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>
							Type
						</td>
						<td>
							<c:choose>
								<c:when test="${message.type == 1}">
									Ayuda
								</c:when>
								<c:when test="${message.type == 2}">
									Sugerencia
								</c:when>
								<c:when test="${message.type == 4}">
									Otro
								</c:when>
								<c:when test="${message.type == 5}">
									Afiliación
								</c:when>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td>
							Subject
						</td>
						<td>
							<c:out value="${message.subject}"/>
						</td>
					</tr>
					<tr>
						<td>
							text
						</td>
						<td>
							<c:out value="${message.text}"/>
						</td>
					</tr>
					<c:if test="${message.type == 5 && not empty message.user}">
						<tr>
							<td>
								Crear usuario de afiliación y enviar email
							</td>
							<td>
								<input type="button" onclick="affiliationUserRequestSend('<c:out value="${message.email}"/>','<c:out value="${message.user.login}"/>');" value="Crear usuario" />
							</td>
						</tr>
					</c:if>
					
					<c:if test="${message.type != 5 }">
						<tr>
							<td>
								Responder
							</td>
							<td>
								<input type="button" onclick="answer('<c:out value="${message.id}"/>');" value="Responder" />
							</td>
						</tr>
					</c:if>
					
					<tr>
						<td colspan="2" align="right">
							<input type="button" value="Volver" onclick="document.location.href='<html:rewrite page="/do/message/list"/>'"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


