<%@page import="java.text.DecimalFormat"%>
<%@page import="com.madgrid.admin.util.PaymentUserData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.madgrid.model.User"%>
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
	            color:'blue',
	            lineWidth: 1,
	            pointInterval: 24 * 3600 * 1000,
	            pointStart: Date.UTC(2014, 4, 1),
	            data: [<c:forEach items="${balanceDataList}" var="balanceData" varStatus="index"><fmt:formatNumber value="${balanceData.balance}" minFractionDigits="2" maxFractionDigits="6"/><c:if test="${!index.last}">,</c:if></c:forEach>]
	        },
	        {
	            name: 'Balance - affiliation',
	            marker: {radius: 0},
	            color:'#000066',
	            lineWidth: 1,
	            pointInterval: 24 * 3600 * 1000,
	            pointStart: Date.UTC(2014, 4, 1),
	            data: [<c:forEach items="${balanceDataList}" var="balanceData" varStatus="index"><fmt:formatNumber value="${balanceData.balance2}" minFractionDigits="2" maxFractionDigits="6"/><c:if test="${!index.last}">,</c:if></c:forEach>]
	        }]
	    });
		
		
		
		
		
		
		
		
		$('#container2').highcharts({
	        chart: {
	        	zoomType: 'x'
	        },
	        title: {
	            text: 'Ingresos por día'
	        },
	        yAxis: {
	            title: {
	                text: 'BTC'
	            }
	        },
	        xAxis: {
	            type: 'datetime',
	            minRange: 7 * 24 * 3600000 // fourteen days
	        },
	        credits: {
	            enabled: false
	        },
	        series: [{
	        	type: 'column',
	        	name: 'Balance diario',
	        	color:'blue',
	        	pointInterval: 24 * 3600 * 1000,
	            pointStart: Date.UTC(2014, 4, 1),
	            data: [<c:forEach items="${incomeList}" var="balanceData" varStatus="index"><fmt:formatNumber value="${balanceData.balance}" minFractionDigits="2" maxFractionDigits="6"/><c:if test="${!index.last}">,</c:if></c:forEach>]
	        },
	        {
	            name: 'Ingresos',
	            pointInterval: 24 * 3600 * 1000,
	            color:'green',
	            marker: {radius: 0},
	            lineWidth: 1,
	            pointStart: Date.UTC(2014, 4, 1),
	            data: [<c:forEach items="${incomeList}" var="balanceData" varStatus="index"><fmt:formatNumber value="${balanceData.in}" minFractionDigits="2" maxFractionDigits="6"/><c:if test="${!index.last}">,</c:if></c:forEach>]
	        },
	        {
	           
	            name: 'Gastos',
	            color:'red',
	            marker: {radius: 0},
	            lineWidth: 1,
	            pointInterval: 24 * 3600 * 1000,
	            pointStart: Date.UTC(2014, 4, 1),
	            data: [<c:forEach items="${incomeList}" var="balanceData" varStatus="index"><fmt:formatNumber value="${balanceData.out}" minFractionDigits="2" maxFractionDigits="6"/><c:if test="${!index.last}">,</c:if></c:forEach>]
	        }]
	    });
		
		
		$('#container3').highcharts({
	        chart: {
	            zoomType: 'x'
	        },
	        title: {
	            text: 'Usuarios'
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
	                text: 'Usuarios'
	            }
	        },
	        legend: {
	            enabled: false
	        },
	        plotOptions: {
	            area: {
	                fillColor: {
	                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
	                    stops: [
	                        [0, Highcharts.getOptions().colors[5]],
	                        [1, Highcharts.Color(Highcharts.getOptions().colors[5]).setOpacity(0).get('rgba')]
	                    ]
	                },
	                marker: {
	                    radius: 0
	                },
	                lineWidth: 1,
	                states: {
	                    hover: {
	                        lineWidth: 1
	                    }
	                },
	                threshold: null
	            }
	        },

	        series: [{
	            type: 'area',
	            name: 'Balance',
	            pointInterval: 24 * 3600 * 1000,
	            pointStart: Date.UTC(2014, 4, 1),
	            data: [<c:forEach items="${userCountList}" var="userCount" varStatus="index"><c:out value="${userCount}" /><c:if test="${!index.last}">,</c:if></c:forEach>]
	        }]
	    });
	
		
		$('#container4').highcharts({
	        chart: {
	            zoomType: 'x'
	        },
	        title: {
	            text: 'Ingresos/Gastos acumulados'
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
	           
	            name: 'Ingresos',
	            pointInterval: 24 * 3600 * 1000,
	            color:'green',
	            marker: {radius: 0},
	            lineWidth: 1,
	            pointStart: Date.UTC(2014, 4, 1),
	            data: [<c:forEach items="${balanceDataList}" var="balanceData" varStatus="index"><fmt:formatNumber value="${balanceData.in}" minFractionDigits="2" maxFractionDigits="6"/><c:if test="${!index.last}">,</c:if></c:forEach>]
	        },
	        {
	           
	            name: 'Gastos',
	            color:'red',
	            marker: {radius: 0},
	            lineWidth: 1,
	            pointInterval: 24 * 3600 * 1000,
	            pointStart: Date.UTC(2014, 4, 1),
	            data: [<c:forEach items="${balanceDataList}" var="balanceData" varStatus="index"><fmt:formatNumber value="${balanceData.out2}" minFractionDigits="2" maxFractionDigits="6"/><c:if test="${!index.last}">,</c:if></c:forEach>]
	        }]
	    });
		
		
		
		
	    
	    
	  
	});
		
	</script>
	
	
	

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center" valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
					<tr>
						<td width="100%" align="center" style="font-size:24px;padding-bottom: 10px;">
							Últimos Ingresos en Bitcoins
						</td>
					</tr>
					<tr>
						<td align="center">
							<table width="90%" class="tab">
								<tr>
									
									<th>
										UserId
									</th>
									<th>
										Created
									</th>
									<th>
										Bitcoins
									</th>
								</tr>
								<c:set var="busers" value="0" />
								<c:set var="btotal1" value="0" />
								<c:forEach items="${bitcoinPaymentList}" var="bitcoinPayment" varStatus="index" >
									<c:if test="${bitcoinPayment.userId != '6621'}">
										<c:set var="bvalue" value="${bitcoinPayment.bitcoins * 0.00000001}" />
										<c:set var="busers" value="${busers + 1}" />
										<c:set var="btotal1" value="${btotal1 + bvalue}" />
										<c:if test="${index.index > (bitcoinPaymentListSize -20)}">		
											<tr>
												
												<td>
													<c:if test="${not empty bitcoinPayment.payerId}">
														<c:out value="${bitcoinPayment.payerId}"/>(<c:out value="${bitcoinPayment.userId}"/>)
													</c:if>
													<c:if test="${empty bitcoinPayment.payerId}">
														<c:out value="${bitcoinPayment.userId}"/>
													</c:if>
												</td>
												<td>
													<c:out value="${bitcoinPayment.created}"/>
												</td>
												<td>
													
													<fmt:formatNumber value="${bvalue}" minFractionDigits="2" maxFractionDigits="8"/>
												</td>
											</tr>
										</c:if>
									</c:if>
								</c:forEach>
								<tr>
									<td style="font-weight:bold;background-color: #303030;color:white;font-size:20px;">
										Total
									</td>
									<td style="font-weight:bold;background-color: #303030;color:white;font-size:20px;">
										<c:out value="${busers}"/> Cobros
									</td>
								
									<td style="font-weight:bold;background-color: #303030;color:white;font-size:20px;">
										
										<fmt:formatNumber value="${btotal1}" minFractionDigits="2" maxFractionDigits="2"/> BTC
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
			<td align="center" valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
					<tr>
						<td width="100%" align="center" style="font-size:24px;padding-bottom: 10px;">
							Últimos Premios en Bitcoins 
						</td>
					</tr>
					<tr>
						<td align="center">
							<table width="90%" class="tab">
								<tr>
									
									<th>
										User
									</th>
									<th>
										Created
									</th>
									<th>
										Bitcoins
									</th>									
								</tr>
								<c:set var="busers" value="0" />
								<c:set var="btotal2" value="0" />
								<c:forEach items="${winList}" var="win" varStatus="index">
										
								
									<c:if test="${(win.item.type==4 || win.item.type==5 ) && win.isOnlyCredits == false}">
									
										<c:if test="${ win.itemSent}">
											<c:set var="busers" value="${busers + 1}" />
											<c:set var="btotal2" value="${btotal2 + win.item.bitcoins}" />
											<c:if test="${index.index > (winListSize - 40)}">			
												<tr>
													
													<td>
														<c:out value="${win.user.login}"/>(<c:out value="${win.user.id}"/>)
													</td>
													<td>
														<fmt:formatDate value="${win.created}" type="both" timeStyle="short" dateStyle="full" />
													</td>
													<td>
														
														<fmt:formatNumber value="${win.item.bitcoins}" minFractionDigits="2" maxFractionDigits="8"/>
														
													</td>
												</tr>
											</c:if>
										</c:if>
									</c:if>
								</c:forEach>
								<tr>
									<td style="font-weight:bold;background-color: #303030;color:white;font-size:20px;">
										Total
									</td>
									<td style="font-weight:bold;background-color: #303030;color:white;font-size:20px;">
										<c:out value="${busers}"/> Pagos
									</td>
									
									<td style="font-weight:bold;background-color: #303030;color:white;font-size:20px;">
										
										<fmt:formatNumber value="${btotal2}" minFractionDigits="2" maxFractionDigits="2"/> BTC
									</td>
								</tr>
							</table>
							
						</td>
					</tr>
				</table>
			</td>
			
			
			
			
			
			
			<td align="center" valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
					<tr>
						<td width="100%" align="center" style="font-size:24px;padding-bottom: 10px;">
							Usuarios que más ingresan
						</td>
					</tr>
					<tr>
						<td align="center">
							<table width="90%" class="tab">
								<tr>
									<th>
										Usuario
									</th>
									<th>
										Bitcoins
									</th>
									<th>
										Pagos
									</th>
								</tr>
								<%
								DecimalFormat numberFormat = new DecimalFormat("#.########");
								ArrayList<PaymentUserData> paymentUserDataList = (ArrayList<PaymentUserData>)request.getAttribute("paymentUserDataList");
								int count = 0;
								for( PaymentUserData paymentUserData:paymentUserDataList) { 
									if( count < 30){
									%>
									<tr>
										<td>
											<a href="<html:rewrite page="/do/user/edit?id="/><%=paymentUserData.getId()%>"><%=paymentUserData.getLogin()%>(<%=paymentUserData.getId()%>)</a>
										</td>
										<td>
											<%=numberFormat.format(paymentUserData.getBitcoins())%>
										</td>
										<td>
											<%=paymentUserData.getPayments()%>
										</td>
									</tr>
									
									<%
										}
										count++;
									} %>
									<tr>
										<td style="font-weight:bold;background-color: #303030;color:white;font-size:20px;">
											Total
										</td>
										<td colspan="2" style="font-weight:bold;background-color: #303030;color:white;font-size:20px;">
											<%= paymentUserDataList.size()%> usuarios
										</td>
									</tr>
							</table>
							
						</td>
					</tr>
				</table>
			</td>
			
			
			
			
			
			
			
			
			
		</tr>
		<tr>
			<td colspan="3" align="center" style="padding-top:20px;font-size: 24px;">
				<c:set var="btotal3" value="${btotal1 - btotal2 }" />
				Balance : <fmt:formatNumber value="${btotal3}" minFractionDigits="3" maxFractionDigits="3"/> BTC
				<br/>
				Balance - affiliación (<fmt:formatNumber value="${affiliationPaymentSum}" minFractionDigits="3" maxFractionDigits="3"/> BTC): <fmt:formatNumber value="${btotal3 - affiliationPaymentSum}" minFractionDigits="3" maxFractionDigits="3"/> BTC
			</td>
		</tr>
		
		<tr>
			<td colspan="3" align="center">
				<div id="container" class="chart-container" style="border:1px solid black;margin:10px;"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="3" align="center">
				<div id="container2" class="chart-container2" style="border:1px solid black;margin:10px;"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="3" align="center">
				<div id="container3" class="chart-container3" style="border:1px solid black;margin:10px;"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="3" align="center">
				<div id="container4" class="chart-container4" style="border:1px solid black;margin:10px;"></div>
			</td>
		</tr>
		
		
	</table>

</tiles:put>
</tiles:insert>


