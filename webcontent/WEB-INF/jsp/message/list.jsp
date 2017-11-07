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
		function deleteMessage(id){
			if( confirm('¿Desea borrar el mensaje?')){
				document.location.href='<html:rewrite page="/do/message/delete?id="/>' + id;
			}
		}
	</script>
	<span style="font-family:Arial;font-size:18px;">Listado de mensajes</span>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<table width="60%" class="tab">
					<tr>
						<th>
							Id
						</th>
						<th>
							Created
						</th>
						<th>
							User
						</th>
						<th>
							email
						</th>
						<th>
							Type
						</th>
						<th>
							Subject
						</th>
						<th>
							IsRead
						</th>
						<th>
							Delete
						</th>
					</tr>
					
					<c:forEach items="${messageList}" var="message">
						<tr>
							<c:if test="${message.isRead}"><td></c:if>
							<c:if test="${!message.isRead}"><td style="background-color:#DDDDDD;font-weight:bold;"></c:if>
								<c:out value="${message.id}"/>
							</td>
							<c:if test="${message.isRead}"><td></c:if>
							<c:if test="${!message.isRead}"><td style="background-color:#DDDDDD;font-weight:bold;"></c:if>
								<fmt:formatDate value="${message.created}" pattern="dd/MM/yyyy HH:mm"/>
							</td>
							<c:if test="${message.isRead}"><td></c:if>
							<c:if test="${!message.isRead}"><td style="background-color:#DDDDDD;font-weight:bold;"></c:if>
								<c:if test="${empty message.user}">
									-
								</c:if>
								<c:if test="${not empty message.user}">
									<a href="<html:rewrite page="/do/user/edit?id="/><c:out value="${message.user.id}"/>"><c:out value="${message.user.login}"/></a>
								</c:if>
							</td>
							
							
							<c:if test="${message.isRead}"><td></c:if>
							<c:if test="${!message.isRead}"><td style="background-color:#DDDDDD;font-weight:bold;"></c:if>
								<c:if test="${empty message.email}">
									-
								</c:if>
								<c:if test="${not empty message.email}">
									<c:out value="${message.email}"/>
								</c:if>
							</td>
							
							<c:if test="${message.isRead}"><td></c:if>
							<c:if test="${!message.isRead}"><td style="background-color:#DDDDDD;font-weight:bold;"></c:if>
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
										Afiliacion
									</c:when>
								</c:choose>
							</td>
							<c:if test="${message.isRead}"><td></c:if>
							<c:if test="${!message.isRead}"><td style="background-color:#DDDDDD;font-weight:bold;"></c:if>
								<c:out value="${message.subject}"/>
							</td>
							<c:if test="${message.isRead}"><td style="text-align:center;"></c:if>
							<c:if test="${!message.isRead}"><td style="text-align:center;background-color:#DDDDDD;font-weight:bold;"></c:if>
								<c:if test="${message.isRead}">
									<input type="button" value="Marcar como no leido" onclick="document.location.href='<html:rewrite page="/do/message/markasnotread?id="/><c:out value="${message.id}"/>'"/>
								</c:if>
								<c:if test="${!message.isRead}">
									<input type="button" value="Ver" onclick="document.location.href='<html:rewrite page="/do/message/edit?id="/><c:out value="${message.id}"/>'"/>
								</c:if>
							</td>
							<c:if test="${message.isRead}"><td style="text-align:center;"></c:if>
							<c:if test="${!message.isRead}"><td style="text-align:center;background-color:#DDDDDD;font-weight:bold;"></c:if>
								<input type="button" value="Borrar" onclick="deleteMessage('<c:out value="${message.id}"/>');"/>
							</td>
						</tr>
					</c:forEach>
				</table>
				
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


