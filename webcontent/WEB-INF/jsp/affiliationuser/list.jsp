<%@page import="com.madgrid.model.AffiliationUser"%>
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
		function deleteAffiliationUser(id){
			if( confirm('¿Desea borrar el usuario afiliado?')){
				document.location.href='<html:rewrite page="/do/affiliationuser/delete?id="/>' + id;
			}
		}
		
		function sendPayment(id){
			if( confirm('¿Desea pagar al afiliado?')){
				$.post("<html:rewrite page="/do/affiliationuser/sendpayment"/>", { id: id}, function(data){
					alert(data);
				});
				
			}
		}
	</script>
	<span style="font-family:Arial;font-size:18px;">Listado de usuarios afiliados</span>
	
	<table width="90%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td>
				<input type="button" value="Nuevo afiliador" onclick="document.location.href='<html:rewrite page="/do/affiliationuser/edit"/>'"/>
				
				<input type="button" value="Dar 10% creditos" onclick="document.location.href='<html:rewrite page="/do/affiliationuser/givecredits"/>'"/>
				
				<input type="button" value="Enviar mensaje a todos" onclick="document.location.href='<html:rewrite page="/do/affiliationuser/messageAll"/>'"/>
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
							LastLogin
						</th>
						<th>
							Email
						</th>
						<th>
							Transfer Requested/<br/>validated
						</th>
						<th>
							Credit collected
						</th>
						<th>
							Messages sent to him
						</th>
						<th>
							Contacts received for me
						</th>
						<th>
							Users
						</th>
						<th>
							Pagados
						</th>
						<th>
							No pagados
						</th>
						<th>
							Edit
						</th>
					</tr>
					
					<c:forEach items="${affiliationUserInfoList}" var="affiliationUserInfo">
						<tr>
							<td>
								<c:out value="${affiliationUserInfo.affiliationUser.id}"/>
							</td>
							<td>
								<c:out value="${affiliationUserInfo.affiliationUser.login}"/>
							</td>
							<td>
								<fmt:formatDate value="${affiliationUserInfo.affiliationUser.created}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							<td>
								<fmt:formatDate value="${affiliationUserInfo.affiliationUser.lastLogin}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							<td>
								<c:out value="${affiliationUserInfo.affiliationUser.email}"/>
							</td>
							<td>
								<c:if test="${affiliationUserInfo.affiliationUser.askedForTransfer}">
									<span style="color:red;font-weight:bold;">Sí</span>
									<c:if test="${affiliationUserInfo.affiliationUser.paymentRequestValidated}">
										/<span style="color:red;font-weight:bold;">Sí</span>
									</c:if>
									<c:if test="${!affiliationUserInfo.affiliationUser.paymentRequestValidated}">
										/<span style="color:green;font-weight:bold;">No</span>
									</c:if>
								</c:if>
								<c:if test="${!affiliationUserInfo.affiliationUser.askedForTransfer}">
									<span style="color:green;font-weight:bold;">No</span>
								</c:if>
								
							</td>
							<td>
								<c:if test="${affiliationUserInfo.affiliationUser.creditAlreadyClaimed}">
									<span style="color:red;font-weight:bold;">Sí</span>
								</c:if>
								<c:if test="${!affiliationUserInfo.affiliationUser.creditAlreadyClaimed}">
									<span style="color:green;font-weight:bold;">No</span>
								</c:if>
							</td>
							<td>
								<c:out value="${affiliationUserInfo.affiliationUserMessages}"/>
							</td>
							<td>
								<c:out value="${affiliationUserInfo.affiliationContacts}"/>
							</td>
							<td>
								<c:out value="${affiliationUserInfo.userRegistered}"/>
							</td>
							<td>
								<fmt:formatNumber value="${affiliationUserInfo.bitcoinsPayed}" minFractionDigits="8" maxFractionDigits="8"/>
							</td>
							<td>
								<c:if test="${affiliationUserInfo.bitcoinsNotPayed == 0}">
									<fmt:formatNumber value="${affiliationUserInfo.bitcoinsNotPayed}" minFractionDigits="8" maxFractionDigits="8"/>
								</c:if>
								<c:if test="${affiliationUserInfo.bitcoinsNotPayed > 0}">
									<span style="color:red;"><fmt:formatNumber value="${affiliationUserInfo.bitcoinsNotPayed}" minFractionDigits="8" maxFractionDigits="8"/></span>
								</c:if>
							</td>
							<td style="text-align:center;">
								<input type="button" value="Editar" onclick="document.location.href='<html:rewrite page="/do/affiliationuser/edit?id="/><c:out value="${affiliationUserInfo.affiliationUser.id}"/>'"/>
								<input type="button" value="Borrar" onclick="deleteAffiliationUser('<c:out value="${affiliationUserInfo.affiliationUser.id}"/>');"/>
								<input type="button" value="Enviar mensaje" onclick="document.location.href='<html:rewrite page="/do/affiliationuser/message?id="/><c:out value="${affiliationUserInfo.affiliationUser.id}"/>'"/>
								<c:if test="${affiliationUserInfo.affiliationUser.askedForTransfer && affiliationUserInfo.affiliationUser.paymentRequestValidated}">
									<input type="button" value="Pagar" onclick="document.location.href='<html:rewrite page="/do/affiliationuser/sendpaymentedit"/>?id=<c:out value="${affiliationUserInfo.affiliationUser.id}"/>'"/>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


