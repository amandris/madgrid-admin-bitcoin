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
	<fmt:setLocale value="en_US" /> 
	$(function () {
		
		$('#container').highcharts({
	        chart: {
	            zoomType: 'x'
	        },
	        title: {
	            text: 'Balance'
	        },
	        subtitle: {
	            text: document.ontouchstart === undefined ?
	                    'Click and drag in the plot area to zoom in' :
	                    'Pinch the chart to zoom in'
	        },
	        xAxis: {
	            type: 'datetime',
	            minRange: 7 * 24 * 3600000 // fourteen days
	        },
	        yAxis: {
	            title: {
	                text: 'BTC'
	            }
	        },
	        legend: {
	            enabled: false
	        },
	       
	        series: [{
	            name: 'Balance',
	            marker: {radius: 0},
	            lineWidth: 1,
	            color : 'blue',
	            pointInterval: 24 * 3600 * 1000,
	            pointStart: Date.UTC(2014, 4, 1),
	            data: [<c:forEach items="${balanceDataList}" var="balanceData" varStatus="index"><fmt:formatNumber value="${balanceData.balance}" minFractionDigits="2" maxFractionDigits="6"/><c:if test="${!index.last}">,</c:if></c:forEach>]
	        }]
	    });
		
	});
		
		</script>

	<span style="font-family:Arial;font-size:18px;">Edición de usuario</span>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<html:form action="/user/save">
					<html:hidden name="userForm" property="id"/>
					<table width="70%" class="tab">
						<tr>
							<td>
								Id
							</td>
							<td>
								<c:if test="${userForm.id != 0}">
									<c:out value="${userForm.id}"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								Active
							</td>
							<td>
								<html:checkbox property="active"/>
								<input type="hidden" name="active" value="false"/>
							</td>
						</tr>
						<tr>
							<td>
								Created
							</td>
							<td>
								<fmt:formatDate value="${user.created}" type="both" timeStyle="long" dateStyle="full" />
								
							</td>
						</tr>
						<tr>
							<td>
								Modified
							</td>
							<td>
								<fmt:formatDate value="${user.modified}" type="both" timeStyle="long" dateStyle="full" />
								
							</td>
						</tr>
						<tr>
							<td>
								LastLogin
							</td>
							<td>
								<fmt:formatDate value="${user.lastLogin}" type="both" timeStyle="long" dateStyle="full" />
								
							</td>
						</tr>
						<tr>
							<td>
								IP
							</td>
							<td>
								<c:out value="${user.ip}"/>
								
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
								<html:text property="password" />&nbsp;&nbsp;<c:out value="${user.password}"/>
							</td>
						</tr>
						<tr>
							<td>
								CodeForPasswordRestart
							</td>
							<td>
								<c:out value="${user.codeForPasswordRestart}"/>
							</td>
						</tr>
						<tr>
							<td>
								CodeForPasswordRestartCreated
							</td>
							<td>
								
								<fmt:formatDate value="${user.codeForPasswordRestartCreated}" type="both" timeStyle="long" dateStyle="full" />
							</td>
						</tr>
						<tr>
							<td>
								Credits
								<span style="color:red;"><html:errors property="credits"/></span>
							</td>
							<td>
								<html:text property="credits" />
							</td>
						</tr>
						
						<tr>
							<td>
								Email
								<span style="color:red;"><html:errors property="email"/></span>
							</td>
							<td>
								<html:text  property="email" size="40"/>
							</td>
						</tr>
						
						<tr>
							<td>
								CanonicalEmail
								<span style="color:red;"><html:errors property="canonicalEmail"/></span>
							</td>
							<td>
								<html:text  property="canonicalEmail" size="40"/>
							</td>
						</tr>
						
						<tr>
							<td>
								RecomendedUser
							</td>
							<td>
								<c:out value="${user.recomendedUser}"/>
							</td>
						</tr>
						<tr>
							<td>
								PromoCode
							</td>
							<td>
								<c:out value="${user.promoCode}"/>
							</td>
						</tr>
						<tr>
							<td>
								buyCreditsPromoCode
							</td>
							<td>
								<c:out value="${user.buyCreditsPromoCode}"/>
							</td>
						</tr>
						<tr>
							<td>
								buyCreditsPromoCodeText
							</td>
							<td>
								<c:out value="${user.buyCreditsPromoCodeText}"/>
							</td>
						</tr>
						<tr>
							<td>
								IsBegginer
							</td>
							<td>
								<html:checkbox property="isBeginner"/>
								<input type="hidden" name="isBeginner" value="false"/>
							</td>
						</tr>
						<tr>
							<td>
								IsFraudulent
							</td>
							<td>
								<html:checkbox property="isFraudulent"/>
								<input type="hidden" name="isFraudulent" value="false"/>
							</td>
						</tr>
						<tr>
							<td>
								IsSubscribed
							</td>
							<td>
								<html:checkbox property="isSubscribed"/>
								<input type="hidden" name="isSubscribed" value="false"/>
							</td>
						</tr>
						<tr>
							<td>
								AutoPay
							</td>
							<td>
								<html:checkbox property="autoPay"/>
								<input type="hidden" name="autoPay" value="false"/>
							</td>
						</tr>
						<tr>
							<td>
								bitcoinAddress
							</td>
							<td>
								<html:text  property="bitcoinAddress" size="60" />
							</td>
						</tr>
						
						
						<tr>
							<td>
								RequestPrizeSubscribed
							</td>
							<td>
								<html:checkbox property="requestPrizeSubscribed"/>
								<input type="hidden" name="requestPrizeSubscribed" value="false"/>
							</td>
						</tr>
						<tr>
							<td>
								BitcoinSentSubscribed
							</td>
							<td>
								<html:checkbox property="bitcoinSentSubscribed"/>
								<input type="hidden" name="bitcoinSentSubscribed" value="false"/>
							</td>
						</tr>
						
						
						<tr>
							<td>
								Validated
							</td>
							<td>
								<html:checkbox property="validated"/>
								<input type="hidden" name="validated" value="false"/>
							</td>
						</tr>
						<tr>
							<td>
								ValidationCode
							</td>
							<td>
								<html:text  property="validationCode" size="40" />
							</td>
						</tr>
						<tr>
							<td>
								statisticsPlayedGames
							</td>
							<td>
								<c:out value="${user.statisticsPlayedGames}"/>
							</td>
						</tr>
						<tr>
							<td>
								statisticsWonGames
							</td>
							<td>
								<c:out value="${user.statisticsWonGames}"/>
							</td>
						</tr>
						<tr>
							<td>
								statisticsBoughtBoxes
							</td>
							<td>
								<c:out value="${user.statisticsBoughtBoxes}"/>
							</td>
						</tr>
						<tr>
							<td>
								statisticsUsedCredits
							</td>
							<td>
								<c:out value="${user.statisticsUsedCredits}"/>
							</td>
						</tr>
						<tr>
							<td>
								affiliatedUsers
							</td>
							<td>
								<c:out value="${user.affiliatedUsers}"/>
							</td>
						</tr>
						<tr>
							<td>
								CompanyAffiliationUser
							</td>
							<td>
								<c:out value="${user.companyAffiliationUser.login}"/> (<c:out value="${user.companyAffiliationUser.id}"/>)
							</td>
						</tr>
						<tr>
							<td>
								Bitcoins Gastados
							</td>
							<td>
								<fmt:formatNumber value="${bitcoinSpent}" minFractionDigits="0" maxFractionDigits="3"/>
							</td>
						</tr>
						<tr>
							<td>
								Bitcoins Ganados
							</td>
							<td>
								-<fmt:formatNumber value="${bitcoinWon}" minFractionDigits="0" maxFractionDigits="3"/>
							</td>
						</tr>
						<tr>
							<td>
								Balance (Positvo=saldo a favor de Instantri.ch)
							</td>
							<td>
								<fmt:formatNumber value="${bitcoinSpent - bitcoinWon}" minFractionDigits="0" maxFractionDigits="3"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="button" value="Cancelar" onclick="document.location.href='<html:rewrite page="/do/user/list"/>'"/>
								<input type="button" value="Enviar mensaje" onclick="document.location.href='<html:rewrite page="/do/user/message?id="/><c:out value="${user.id}"/>'"/>
								<input type="submit" value="Ok" />
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
		<tr>
			<td align="center"  style="font-family:Arial;font-size:18px;">
				Mensajes enviados
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
					<c:forEach items="${userMessageList}" var="userMessage">
						<tr>
							<td>
								<fmt:formatDate value="${userMessage.created}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							<td>
								<c:out value="${userMessage.subject}"/>
							</td>
							<td>
								<c:out value="${userMessage.message}"/>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center">
				<table>
					<tr style="height:30px;vertical-align: bottom;">
						<td align="center"  style="font-family:Arial;font-size:18px;">
							Pagos con Bitcoins
						</td>
						<td align="center"  style="font-family:Arial;font-size:18px;">
							Acciones del usuario
						</td>
					</tr>
					<tr style="vertical-align: top;">
						<td style="width:50%;">
							<table class="tab">
								<tr>
									<td style="background-color: #303030;color:white;">
										<strong>Id</strong>
									</td>
									<td style="background-color: #303030;color:white;">
										<strong>Created</strong>
									</td>
									<td style="background-color: #303030;color:white;">
										<strong>Address</strong>
									</td>
									<td style="background-color: #303030;color:white;">
										<strong>Bitcoins</strong>
									</td>
									<td style="background-color: #303030;color:white;">
										<strong>Credits</strong>
									</td>
								</tr>
								<c:forEach items="${bitcoinPaymentList}" var="bitcoinPayment">
									<tr>
										<td>
											<c:out value="${bitcoinPayment.id}"/>
										</td>
										<td>
											<c:out value="${bitcoinPayment.created}"/>
										</td>
										<td>
											<c:out value="${bitcoinPayment.receiveAddress}"/>
										</td>
										<td>
											<c:out value="${bitcoinPayment.bitcoins / 100000000}"/>
										</td>
										<td>
											<c:out value="${bitcoinPayment.credits}"/>
										</td>
									</tr>
								</c:forEach>
							</table>
						</td>
						<td style="width:50%;">
							<table class="tab">
								<tr>
									<td style="background-color: #303030;color:white;">
										<strong>Id</strong>
									</td>
									<td style="background-color: #303030;color:white;">
										<strong>Created</strong>
									</td>
									<td style="background-color: #303030;color:white;">
										<strong>Type</strong>
									</td>
									<td style="background-color: #303030;color:white;">
										<strong>Action</strong>
									</td>
								</tr>
								<tr>
									<c:forEach items="${userHistoricList}" var="userHistoric">
										<tr>
											<td>
												<c:out value="${userHistoric.id}"/>
											</td>
											<td>
												<fmt:formatDate value="${userHistoric.created}" type="both" timeStyle="short" dateStyle="full" />
											</td>
											<td>
												<c:choose>
													<c:when test="${userHistoric.type==1}">
														<span style="color:darkred;">Compra de créditos</span>
													</c:when>
													<c:when test="${userHistoric.type==2}">
														<span style="color:darkgreen;">Conseguir créditos por promoción</span>
													</c:when>
													<c:when test="${userHistoric.type==3}">
														<span style="color:navy;">Abrir caja</span>
													</c:when>
													<c:when test="${userHistoric.type==4}">
														<span style="color:darkgoldenrod;">Encontrar premio</span>
													</c:when>
													<c:when test="${userHistoric.type==5}">
														<span style="color:MediumVioletRed;">Ganar premio</span>
													</c:when>
													<c:when test="${userHistoric.type==6}">
														<span style="color:MediumVioletRed;">Conseguir crédito por validar email</span>
													</c:when>
													<c:when test="${userHistoric.type==7}">
														<span style="color:MediumVioletRed;">Conseguir crédito por afiliado</span>
													</c:when>
													<c:when test="${userHistoric.type==8}">
														<span style="color:MediumVioletRed;">Créditos devueltos</span>
													</c:when>
													<c:when test="${userHistoric.type==9}">
														<span style="color:MediumVioletRed;">Créditos añadidos por admin</span>
													</c:when>
													<c:when test="${userHistoric.type==10}">
														<span style="color:MediumVioletRed;">Créditos eliminados por admin</span>
													</c:when>
													<c:when test="${userHistoric.type==11}">
														<span style="color:MediumVioletRed;">Créditos ganados en juego multipremio</span>
													</c:when>
													<c:when test="${userHistoric.type==12}">
														<span style="color:MediumVioletRed;">Crédito ganados por tweet</span>
													</c:when>
													
												</c:choose>
												
											</td>
											<td>
									
												<c:choose>
													<c:when test="${userHistoric.type==1}">
														<span style="color:darkred;">You bought <strong><fmt:formatNumber value="${userHistoric.value1}" maxFractionDigits="0"/></strong> credits</span>
													</c:when>
													<c:when test="${userHistoric.type==2}">
														<span style="color:darkgreen;">You got <strong><c:out value="${userHistoric.value1}"/></strong> credits for promotion <strong><c:out value="${userHistoric.value2}"/></strong></span>
													</c:when>
													<c:when test="${userHistoric.type==3}">
														<span style="color:navy;">You opened box <strong><c:out value="${userHistoric.value2}"/></strong> in game <strong><c:out value="${userHistoric.value3}" escapeXml="false"/></strong> for <strong><fmt:formatNumber value="${userHistoric.value1}" maxFractionDigits="0"/></strong> <c:if test="${userHistoric.value1==1}">credit</c:if><c:if test="${userHistoric.value1!=1}">credits</c:if></span>
													</c:when>
													<c:when test="${userHistoric.type==4}">
														<span style="color:darkgoldenrod;">You found the prize <strong><c:out value="${userHistoric.value3}" escapeXml="false"/></strong> in box <strong><c:out value="${userHistoric.value2}"/></strong></span>
													</c:when>
													<c:when test="${userHistoric.type==5}">
														<span style="color:MediumVioletRed;">You got the prize <strong><c:out value="${userHistoric.value3}" escapeXml="false"/></strong></span>
													</c:when>
													<c:when test="${userHistoric.type==6}">
														<span style="color:MediumVioletRed;">For validating your email account you got <strong><c:out value="${userHistoric.value1}" escapeXml="false"/>credit</strong></span>
													</c:when>
													<c:when test="${userHistoric.type==7}">
														<span style="color:MediumVioletRed;">For an affiliated user you got <strong><c:out value="${userHistoric.value1}" escapeXml="false"/>credit</strong></span>
													</c:when>
													<c:when test="${userHistoric.type==8}">
														<span style="color:MediumVioletRed;">You've got a <fmt:formatNumber value="${userHistoric.value1}" maxFractionDigits="0"/> credits refund because nobody won the <strong><c:out value="${userHistoric.value2}" escapeXml="false"/> game</strong></span>
													</c:when>
													<c:when test="${userHistoric.type==9}">
														<span style="color:MediumVioletRed;">Instantri.ch admin added <strong><fmt:formatNumber value="${userHistoric.value1}" maxFractionDigits="0"/> credits</strong> to your account</span>
													</c:when>
													<c:when test="${userHistoric.type==10}">
														<span style="color:MediumVioletRed;">Instantri.ch admin removed <strong><fmt:formatNumber value="${userHistoric.value1}" maxFractionDigits="0"/> credits</strong> from your account</span>
													</c:when>
													<c:when test="${userHistoric.type==11}">
														<span style="color:MediumVioletRed;">You won <strong><fmt:formatNumber value="${userHistoric.value1}" maxFractionDigits="0"/> credits</strong> hidden in box <c:out value="${userHistoric.value2}"/> of the <c:out value="${userHistoric.value3}" escapeXml="false"/> game</span>
													</c:when>
													<c:when test="${userHistoric.type==12}">
														<span style="color:MediumVioletRed;">We gave you <strong>1 credit</strong> for your tweet.
													</c:when>
												</c:choose>
											</td>
										</tr>
									</c:forEach>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		
		
		
		<tr>
			<td align="center"  style="font-family:Arial;font-size:18px;">
				Balance
			</td>
		</tr>
		<tr>
			<td align="center">
				<div id="container" class="chart-container" style="border:1px solid black;margin:10px;"></div>
			</td>
		</tr>
		
		
		
	</table>

</tiles:put>
</tiles:insert>


