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
		
		function markAsPayed(id){
			if( confirm('¿Marcar esta actividad como pagada?')){
				$.post("<html:rewrite page="/do/affiliationuser/markAsPayed"/>", { id: id}, function(data){
					alert(data);
					document.getElementById('payed_' + id).innerHTML="Sí"
				});
			}
		}
		
		
		
	</script>


	<span style="font-family:Arial;font-size:18px;">Edición de usuario afiliado</span>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<html:form action="/affiliationuser/save">
					<html:hidden name="affiliationUserForm" property="id"/>
					<table width="70%" class="tab">
						<tr>
							<td>
								Id
							</td>
							<td>
								<c:if test="${affiliationUserForm.id != 0}">
									<c:out value="${affiliationUserForm.id}"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								Created
							</td>
							<td>
								<fmt:formatDate value="${affiliationUserForm.created}" type="both" timeStyle="long" dateStyle="full" />
								
							</td>
						</tr>
						<tr>
							<td>
								LastLogin
							</td>
							<td>
								<fmt:formatDate value="${affiliationUserForm.created}" type="both" timeStyle="long" dateStyle="full" />
								
							</td>
						</tr>
						<tr>
							<td>
								Modified
							</td>
							<td>
								<fmt:formatDate value="${affiliationUserForm.modified}" type="both" timeStyle="long" dateStyle="full" />
								
							</td>
						</tr>
						<tr>
							<td>
								Login
								<span style="color:red;"><html:errors property="login"/></span>
							</td>
							<td>
								<html:text property="login" />
							</td>
						</tr>
						<tr>
							<td>
								Password
								<span style="color:red;"><html:errors property="password"/></span>
							</td>
							<td>
								<html:text property="password" />
							</td>
						</tr>
						<tr>
							<td>
								Name
								<span style="color:red;"><html:errors property="name"/></span>
							</td>
							<td>
								<html:text property="name" />
							</td>
						</tr>
						<tr>
							<td>
								Url
								<span style="color:red;"><html:errors property="url"/></span>
							</td>
							<td>
								<html:text property="url" />
							</td>
						</tr>
						<tr>
							<td>
								Email
								<span style="color:red;"><html:errors property="email"/></span>
							</td>
							<td>
								<html:text  property="email" />
							</td>
						</tr>
						<tr>
							<td>
								Percentage
								<span style="color:red;"><html:errors property="percentage"/></span>
							</td>
							<td>
								<html:text  property="percentage" />
							</td>
						</tr>
						<tr>
							<td>
								Asked For Transfer
							</td>
							<td>
								<html:checkbox property="askedForTransfer"/>
								<input type="hidden" name="askedForTransfer" value="false"/>
							</td>
						</tr>
						<tr>
							<td>
								Payment request validated 
							</td>
							<td>
								<html:checkbox property="paymentRequestValidated"/>
								<input type="hidden" name="paymentRequestValidated" value="false"/>
							</td>
						</tr>
						<tr>
							<td>
								BitcoinAddress
								<span style="color:red;"><html:errors property="bitcoinAddress"/></span>
							</td>
							<td>
								<html:text  property="bitcoinAddress" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="button" value="Enviar mensaje" onclick="document.location.href='<html:rewrite page="/do/affiliationuser/message?id="/><c:out value="${affiliationUserForm.id}"/>'"/>
								<input type="button" value="Cancelar" onclick="document.location.href='<html:rewrite page="/do/affiliationuser/list"/>'"/>
								<input type="submit" value="Ok" />
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
		
		<tr>
			<td align="center"  style="font-family:Arial;font-size:18px;">
				Mensajes enviados por mí
			</td>
		</tr>
		<tr>
			<td align="center">
				<table width="70%" class="tab">
					<tr>
						<td style="background-color: #303030;color:white;">
							<strong>Fecha</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Asunto</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Mensaje</strong>
						</td>
					</tr>
					<c:if test="${empty affiliationUserMessageList}">
						<tr>
							<td colspan="3">
								Todavía no se han enviado mensajes
							</td>
						</tr>
					</c:if>
					<c:forEach items="${affiliationUserMessageList}" var="affiliationUserMessage">
						<tr>
							<td>
								<fmt:formatDate value="${affiliationUserMessage.created}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							<td>
								<c:out value="${affiliationUserMessage.subject}"/>
							</td>
							<td>
								<c:out value="${affiliationUserMessage.message}"/>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		
		<tr>
			<td align="center"  style="font-family:Arial;font-size:18px;">
				Mensajes recibidos de él
			</td>
		</tr>
		<tr>
			<td align="center">
				<table width="70%" class="tab">
					<tr>
						<td style="background-color: #303030;color:white;">
							<strong>Fecha</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Asunto</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Mensaje</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Responder</strong>
						</td>
					</tr>
					<c:if test="${empty affiliationContactList}">
						<tr>
							<td colspan="3">
								Todavía no se han recibido mensajes
							</td>
						</tr>
					</c:if>
					<c:forEach items="${affiliationContactList}" var="affiliationContact">
						<tr>
							<td>
								<fmt:formatDate value="${affiliationContact.created}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							<td>
								<c:out value="${affiliationContact.subject}"/>
							</td>
							<td>
								<c:out value="${affiliationContact.message}"/>
							</td>
							<td>
								<a href="<html:rewrite page="/do/affiliationuser/message"/>?messageId=<c:out value="${affiliationContact.id}"/>">Responder</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td height="40px;">
			</td>
		</tr>
		
		<tr>
			<td align="center"  style="font-family:Arial;font-size:18px;">
				Pagos enviados al usuario
			</td>
		</tr>
		<tr>
			<td align="center">
				<table width="70%" class="tab">
					<tr>
						<td style="background-color: #303030;color:white;">
							<strong>Fecha</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Bitcoins</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>TransactionId</strong>
						</td>
					</tr>
					<c:if test="${empty affiliationPaymentList}">
						<tr>
							<td colspan="3">
								Todavía no se han enviado pagos
							</td>
						</tr>
					</c:if>
					<c:forEach items="${affiliationPaymentList}" var="affiliationPayment">
						<tr>
							<td>
								<fmt:formatDate value="${affiliationPayment.created}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							<td>
								<fmt:formatNumber value="${affiliationPayment.bitcoins}" minFractionDigits="8" maxFractionDigits="8"/>
							</td>
							<td>
								<c:out value="${affiliationPayment.transactionId}"/>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td height="40px;">
			</td>
		</tr>
		
		
		<tr>
			<td align="center"  style="font-family:Arial;font-size:18px;">
				Actividad
			</td>
		</tr>
		<tr>
			<td align="center">
				<table width="70%" class="tab">
					<tr>
						<td style="background-color: #303030;color:white;">
							<strong>Fecha</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Usuario(Id)</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Type</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Total Bitcoins</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Bitcoins assignados</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Ya pagados</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Marcar como pagado</strong>
						</td>
					</tr>
					<c:if test="${empty affiliationActivityList}">
						<tr>
							<td colspan="6">
								Todavía no hay actividad
							</td>
						</tr>
					</c:if>
					<c:forEach items="${affiliationActivityList}" var="affiliationActivity">
						<tr>
							<td>
								<fmt:formatDate value="${affiliationActivity.created}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							<td>
								<c:out value="${affiliationActivity.user.login}"/>(<c:out value="${affiliationActivity.user.id}"/>)
							</td>
							<td>
								<c:choose>
									<c:when test="${affiliationActivity.type==1}">Registro de usuario</c:when>
									<c:when test="${affiliationActivity.type==2}">
										<span style="color:green;font-weight:bold;">Compra de créditos</span>
									</c:when>
									
								</c:choose>
							</td>
							<td>
								<fmt:formatNumber value="${affiliationActivity.totalBitcoins}" minFractionDigits="2" maxFractionDigits="8"/>
							</td>
							<td>
								<fmt:formatNumber value="${affiliationActivity.assignedBitcoins}" minFractionDigits="2" maxFractionDigits="8"/>
							</td>
							<td id="payed_<c:out value="${affiliationActivity.id}"/>">
								<c:choose>
									<c:when test="${affiliationActivity.alreadyPayed}">Sí</c:when>
									<c:when test="${!affiliationActivity.alreadyPayed}">No</c:when>
									
								</c:choose>
							</td>
							<td>
								<c:if test="${affiliationActivity.type==2 && !affiliationActivity.alreadyPayed}">
									<input type="button" value="Marcar como pagado" onclick="markAsPayed('<c:out value="${affiliationActivity.id}"/>')"/>
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


