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
		
		function sendPayment(){
			if( confirm('¿Enviar pago?')){
				var id = '<c:out value="${affiliationUserId}"/>';
				var transactionId = document.getElementById('transactionId').value;
				
				if( transactionId != null && transactionId!=''){
					document.location.href="<html:rewrite page="/do/affiliationuser/sendpayment"/>?id="+id+"&transactionId="+transactionId
				}
			}
		}
		
		
		
	</script>


	<span style="font-family:Arial;font-size:18px;">Edición de usuario afiliado</span>
	<fmt:setLocale value="en_US" /> 
	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
					<table width="70%" class="tab">
						<tr>
							<td>
								Affiliation user
							</td>
							<td>
								<c:out value="${affiliationUserLogin}"/>
							</td>
						</tr>
						<tr>
							<td>
								Bitcoins
							</td>
							<td>
								<fmt:formatNumber value="${bitcoins}" minFractionDigits="8" maxFractionDigits="8"/>
								
							</td>
						</tr>
						<tr>
							<td>
								Wallet address
							</td>
							<td>
								<c:out value="${affiliationUserWalletAddress}"/>
							</td>
						</tr>
						<tr>
							<td>
								TrabsactionId
							</td>
							<td>
								<input type="text" id="transactionId" size="100"/>
								
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="right">
								<input type="button" value="Aceptar" onclick="sendPayment();"/>
								<input type="button" value="Cancelar" onclick="document.location.href='<html:rewrite page="/do/affiliationuser/list"/>';"/>
							</td>
						</tr>
					</table>
				
			</td>
		</tr>

	</table>

</tiles:put>
</tiles:insert>


