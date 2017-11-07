<%@page import="com.madgrid.model.Win"%>
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
	</script>
	<span style="font-family:Arial;font-size:18px;">
		Listado de Ganadores (TODOS)
	</span>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<table width="90%" class="tab">
					<tr>
						<th>
							Id
						</th>
						<th>
							Created
						</th>
						<th>
							Item
						</th>
						<th>
							User
						</th>
						<th>
							DeliveryRequested
						</th>
						<th>
							ItemSent
						</th>
						
						<th>
							Type
						</th>
						<th>
							Bitcoins
						</th>
						<th>
							Credits
						</th>
						<th>
							Wallet address
						</th>
						<th>
							Edit
						</th>
					</tr>
					
					<c:forEach items="${winList}" var="win">
						<tr>
						
							<c:choose>
								<c:when test="${!win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FFDDDD;">
								</c:when>
								<c:when test="${win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FF8080;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
							
								<c:out value="${win.id}"/>
							</td>
							<c:choose>
								<c:when test="${!win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FFDDDD;">
								</c:when>
								<c:when test="${win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FF8080;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
								<fmt:formatDate value="${win.created}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							<c:choose>
								<c:when test="${!win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FFDDDD;">
								</c:when>
								<c:when test="${win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FF8080;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
								<a href="<html:rewrite page="/do/item/edit?id="/><c:out value="${win.item.id}"/>"><c:out value="${win.item.name}" escapeXml="false"/></a>
							</td>
							<c:choose>
								<c:when test="${!win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FFDDDD;">
								</c:when>
								<c:when test="${win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FF8080;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
								<a href="<html:rewrite page="/do/user/edit?id="/><c:out value="${win.user.id}"/>"><c:out value="${win.user.login}"/></a> (<c:out value="${win.user.email}"/>)
							</td>
							<c:choose>
								<c:when test="${!win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FFDDDD;">
								</c:when>
								<c:when test="${win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FF8080;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${win.item.type == 2 }">
									
								</c:when>
								<c:when test="${win.item.type == 4 }">
									<c:if test="${win.deliveryRequested}">
										<span style="color:green;font-weight:bold;">Sí</span>
									</c:if>
									<c:if test="${!win.deliveryRequested}">
										<span style="color:red;font-weight:bold;">No</span>
									</c:if>
								</c:when>
								<c:when test="${win.item.type == 5 && !win.isOnlyCredits}">
									<c:if test="${win.deliveryRequested}">
										<span style="color:green;font-weight:bold;">Sí</span>
									</c:if>
									<c:if test="${!win.deliveryRequested}">
										<span style="color:red;font-weight:bold;">No</span>
									</c:if>
								</c:when>
							
							</c:choose>
								
							</td>
							<c:choose>
								<c:when test="${!win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FFDDDD;">
								</c:when>
								<c:when test="${win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FF8080;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${win.item.type == 2 }">
									
								</c:when>
								<c:when test="${win.item.type == 4 }">
									<c:if test="${win.itemSent}">
									<span style="color:green;font-weight:bold;">Sí</span>
								</c:if>
								<c:if test="${!win.itemSent}">
									<span style="color:red;font-weight:bold;">No</span>
								</c:if>
								</c:when>
								<c:when test="${win.item.type == 5 && !win.isOnlyCredits}">
									<c:if test="${win.itemSent}">
										<span style="color:green;font-weight:bold;">Sí</span>
									</c:if>
									<c:if test="${!win.itemSent}">
										<span style="color:red;font-weight:bold;">No</span>
									</c:if>
								</c:when>
							
							</c:choose>
							
							
							
							
								
							</td>
							<c:choose>
								<c:when test="${!win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FFDDDD;">
								</c:when>
								<c:when test="${win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FF8080;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
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
							<c:choose>
								<c:when test="${!win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FFDDDD;">
								</c:when>
								<c:when test="${win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FF8080;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
								<c:if test="${(win.item.type==4 || win.item.type==5 ) && win.isOnlyCredits == false}">
									<fmt:formatNumber value="${win.item.bitcoins}" minFractionDigits="2" maxFractionDigits="8"/>
								</c:if>
							</td>
							<c:choose>
								<c:when test="${!win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FFDDDD;">
								</c:when>
								<c:when test="${win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FF8080;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
								<c:if test="${not empty win.item.credits && win.item.credits != 0 && win.isOnlyCredits == true}">
									<fmt:formatNumber value="${win.item.credits}"  maxFractionDigits="0"/>
								</c:if>
							</td>
							<c:choose>
								<c:when test="${!win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FFDDDD;">
								</c:when>
								<c:when test="${win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FF8080;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
								<c:if test="${(win.item.type==4 || win.item.type==5 ) && win.isOnlyCredits == false}">
									<c:out value="${win.bitcoinAddress}"/>
								</c:if>
							</td>
							
							<c:choose>
								<c:when test="${!win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FFDDDD;">
								</c:when>
								<c:when test="${win.deliveryRequested && !win.itemSent}">
									<td style="color:black;font-weight:bold;background-color:#FF8080;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
								<input type="button" value="Editar" onclick="document.location.href='<html:rewrite page="/do/win/edit?id="/><c:out value="${win.id}"/>'"/>
								<input type="button" value="Editar" onclick="document.location.href='<html:rewrite page="/do/win/payBitcoins?id="/><c:out value="${win.id}"/>'"/>
							</td>
						</tr>
					</c:forEach>
				</table>
				
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


