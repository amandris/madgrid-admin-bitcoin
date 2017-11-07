<%@page import="com.madgrid.model.Item"%>
<%@page import="com.madgrid.model.Win"%>
<%@page import="com.madgrid.model.Grid"%>
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
		
		function winMessageSend(id){
			
			$.post("<html:rewrite page="/do/win/messageSend"/>", { id: id}, function(data){
				var button = document.getElementById('sendMessageButton');
				if( button != null){
					button.style.visibility="hidden";
				}
			});
			
		}
		
		
		
	</script>

	<span style="font-family:Arial;font-size:18px;">Edición de ganador</span>
	<fmt:setLocale value="en_US" /> 
	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<html:form action="/win/save">
					<html:hidden name="winForm" property="id"/>
					<table width="70%" class="tab">
						<tr>
							<td>
								Id
							</td>
							<td>
								<c:out value="${win.id}"/>
							</td>
						</tr>
						<tr>
							<td>
								Created
							</td>
							<td>
								<fmt:formatDate value="${win.created}" type="both" timeStyle="long" dateStyle="full" />
							</td>
						</tr>
						<tr>
							<td>
								Item
							</td>
							<td>
								<c:out value="${win.item.name}" escapeXml="false"/>
							</td>
						</tr>
						<tr>
							<td>
								User
							</td>
							<td>
								 <a href="<html:rewrite page="/do/user/edit?id="/><c:out value="${win.user.id}"/>"><c:out value="${win.user.login}"/></a> (<c:out value="${win.user.email}"/>)
							</td>
						</tr>
						<tr>
							<td>
								BoughtBoxes
							</td>
							<td>
								<c:out value="${win.boughtBoxes}"/>
							</td>
						</tr>
						<%
						
						Win win = (Win)request.getAttribute("win");
						long prizeValue = 0;
						if( win.getItem().getType() == Item.ITEM_TYPE_ONLY_BITCOINS){
							prizeValue = Math.round( (win.getItem().getBitcoins() * 1000)) ;
						} else if( win.getItem().getType() == Item.ITEM_TYPE_ONLY_CREDITS){
							prizeValue = Math.round( win.getItem().getCredits());
						} else if( win.getItem().getType() == Item.ITEM_TYPE_CREDITS_AND_BITCOINS){
							prizeValue = Math.round( (win.getItem().getBitcoins() * 1000) + win.getItem().getCredits());
						}
						
						
						%>
						<tr>
							<td>
								Spent Credits
							</td>
							<%if( win.getMoneySpent() > prizeValue) {%>
								<td style="color:green;font-weight:bold;">
									<fmt:formatNumber value="${win.moneySpent}" minFractionDigits="0" maxFractionDigits="0"/> (+<%=win.getMoneySpent().intValue() -prizeValue %>)
								</td>
							<%} else{%>
								<td style="color:red;font-weight:bold;">
									<fmt:formatNumber value="${win.moneySpent}" minFractionDigits="0" maxFractionDigits="0"/> (-<%=prizeValue - win.getMoneySpent().intValue() %>)
								</td>
							<%} %>
						</tr>
						<tr>
							<td>
								IsOnlyCredits
							</td>
							<td>
								<c:out value="${win.isOnlyCredits}"/>
							</td>
						</tr>
						
						<tr>
							<td>
								Type
							</td>
							<td>
								<c:choose>
									<c:when test="${win.item.type == 2 }">
										Sólo créditos
									</c:when>
									<c:when test="${win.item.type == 4 }">
										Sólo Bitcoins
									</c:when>
									<c:when test="${win.item.type == 5 }">
										Creditos y bitcoins  
									</c:when>
								</c:choose>
							</td>
						</tr>
						
						
						<tr>
							<td>
								ItemSent
							</td>
							<td>
								<html:checkbox property="itemSent"/>
								<input type="hidden" value="false" name="itemSent"/>
								<c:if test="${win.item.bitcoins > 0 && win.deliveryRequested }">
									<c:if test="${win.user.bitcoinSentSubscribed && win.itemSent == false}">
										<input type="button" onclick="winMessageSend('<c:out value="${win.id}"/>');" value="Enviar mensaje" id="sendMessageButton"/>
									</c:if>
								</c:if>
							</td>
						</tr>
						
						<tr>
							<td>
								deliveryRequested
							</td>
							<td>
								<html:checkbox property="deliveryRequested"/>
								<input type="hidden" value="false" name="deliveryRequested"/>
							</td>
						</tr>
						<c:if test="${win.item.type == 4 || win.item.type == 5 }">
							<tr>
								<td>
									Bitcoins
								</td>
								<td style="font-size:15px;font-weight:bold;">
									<fmt:formatNumber value="${win.item.bitcoins}" minFractionDigits="2" maxFractionDigits="8"/>
								</td>
							</tr>
							<tr>
								<td>
									Wallet address
								</td>
								<td>
									<c:out value="${win.bitcoinAddress}"/>
								</td>
							</tr>
							<tr>
								<td>
									TransactionId
								</td>
								<td>
									<html:text property="transactionId" size="100"/>
								</td>
							</tr>
						</c:if>
						
						<tr>
							<td colspan="2" align="right">
								<c:if test="${win.itemSent}">
									<input type="button" value="Cancelar" onclick="document.location.href='<html:rewrite page="/do/win/listSent"/>'"/>
								</c:if>
								<c:if test="${!win.itemSent}">
									<input type="button" value="Cancelar" onclick="document.location.href='<html:rewrite page="/do/win/list"/>'"/>
								</c:if>
								
								<input type="button" value="Editar" onclick="document.location.href='<html:rewrite page="/do/win/payBitcoins?id="/><c:out value="${win.id}"/>'"/>								
								
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


