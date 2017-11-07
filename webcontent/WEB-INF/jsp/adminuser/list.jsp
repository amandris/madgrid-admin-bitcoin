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
		function deleteAdminUser(id){
			if( confirm('¿Desea borrar el administrador?')){
				document.location.href='<html:rewrite page="/do/adminuser/delete?id="/>' + id;
			}
		}
	</script>
	<span style="font-family:Arial;font-size:18px;">Listado de administradores</span>
	
	<table width="90%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td>
				<input type="button" value="Nuevo administrador" onclick="document.location.href='<html:rewrite page="/do/adminuser/edit"/>'"/>
			</td>
		</tr>
	</table>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<table width="90%" class="tab">
					<tr>
						<th>
							Id
						</th>
						<th>
							Login
						</th>
						<th>
							Created
						</th>
						<th>
							Name
						</th>
						<th>
							Email
						</th>
						<th>
							Edit
						</th>
					</tr>
					
					<c:forEach items="${adminUserList}" var="adminUser">
						<tr>
							<td>
								<c:out value="${adminUser.id}"/>
							</td>
							<td>
								<c:out value="${adminUser.login}"/>
							</td>
							<td>
								<fmt:formatDate value="${adminUser.created}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							<td>
								<c:out value="${adminUser.name}"/>&nbsp;<c:out value="${adminUser.surname}"/>
							</td>
							<td>
								<c:out value="${adminUser.email}"/>
							</td>
							
							<td style="text-align:center;">
								<input type="button" value="Editar" onclick="document.location.href='<html:rewrite page="/do/adminuser/edit?id="/><c:out value="${adminUser.id}"/>'"/>
								<input type="button" value="Borrar" onclick="deleteAdminUser('<c:out value="${adminUser.id}"/>');"/>
							</td>
						</tr>
					</c:forEach>
				</table>
				
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


