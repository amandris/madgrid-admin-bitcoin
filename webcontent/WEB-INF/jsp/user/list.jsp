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
		
		function deleteUser(id){
			if( confirm('¡ATENCIÓN! No se deben borrar nunca usuarios. Usar esta opción sólo en entornos de test o para usuarios creados para hacer pruebas ¿Desea borrar el usuario de todas formas?')){
				document.location.href='<html:rewrite page="/do/user/delete?id="/>' + id;
			}
		}
		
		function messageAllWithCredits(id){
			if( confirm('¿Realmente desea enviar un email a todos los usuarios con créditos?')){
				document.location.href='<html:rewrite page="/do/user/messageAllWithCredits"/>';
			}
		}
	
		
	</script>
	<span style="font-family:Arial;font-size:18px;">Listado de usuarios</span>
	
	<table width="90%" border="0" cellpadding="0" cellspacing="10" align="center" valign="top">
		<tr>
			<td>
				<input type="button" value="Nuevo usuario" onclick="document.location.href='<html:rewrite page="/do/user/edit"/>'"/>
			
				<input type="button" value="Enviar mensaje a todos los usuarios" onclick="document.location.href='<html:rewrite page="/do/user/messageAll"/>'"/>
				
				<input type="button" value="Avisar a usuarios con créditos" onclick="messageAllWithCredits();"/>
				
				<input type="button" value="Mensaje de promoción" onclick="document.location.href='<html:rewrite page="/do/user/messageAllPromotion"/>'"/>
			</td>
		</tr>
	</table>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<table width="90%" class="tab">
					<tr>
						<th>Id</th>
						<th>Login</th>
						<th>Created</th>
						<th>Last Login</th>
						<th>Valid</th>
						<th>Fraud</th>
						<th>Cred</th>
						<th>Email</th>
						<th>IP</th>
						<th>Reward/affiliation</th>
						<th></th>
					</tr>
					
					<c:forEach items="${userList}" var="user">
						<tr>
							<td><c:out value="${user.id}"/></td>
							<td><c:out value="${user.login}"/></td>
							<td><fmt:formatDate value="${user.created}" type="both" timeStyle="short" dateStyle="short" /></td>
							<td><fmt:formatDate value="${user.lastLogin}" type="both" timeStyle="short" dateStyle="short" /></td>
							<td>
								<c:if test="${user.validated}"><span style="color:green;">Sí</span></c:if>
								<c:if test="${!user.validated}"><span style="color:red;">No</span></c:if>
							</td>
							<td>
								<c:if test="${user.isFraudulent}"><span style="color:red;">Sí</span></c:if>
								<c:if test="${!user.isFraudulent}"><span style="color:green;">No</span></c:if>
							</td>
							<td><c:out value="${user.credits}"/></td>
							<td><c:out value="${user.email}"/></td>
							<td><c:out value="${user.ip}"/></td>
							<td><c:out value="${user.recomendedUser}"/>/<c:out value="${user.companyAffiliationUser.name}"/></td>
							<td style="text-align:center;">
								<input type="button" value="Editar" onclick="document.location.href='<html:rewrite page="/do/user/edit?id="/><c:out value="${user.id}"/>'"/>
								<input type="button" value="Borrar" onclick="deleteUser('<c:out value="${user.id}"/>');"/> 
								<input type="button" value="Mensaje" onclick="document.location.href='<html:rewrite page="/do/user/message?id="/><c:out value="${user.id}"/>'"/>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


