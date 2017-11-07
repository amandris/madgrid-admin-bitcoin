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
		Listado de Ganadores (Bitcoins-Envío solicitado)
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
							<td>
								<c:out value="${win.id}"/>
							</td>
							<td>
								<fmt:formatDate value="${win.created}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							<td>
								<c:out value="${win.item.name}" escapeXml="false"/>
							</td>
							<td>
								<c:out value="${win.user.login}"/> (<c:out value="${win.user.email}"/>)
							</td>
							<td>
								<c:if test="${win.deliveryRequested}">
									<span style="color:green;font-weight:bold;">Sí</span>
								</c:if>
								<c:if test="${!win.deliveryRequested}">
									<span style="color:red;font-weight:bold;">No</span>
								</c:if>
							</td>
							<td>
								<c:if test="${win.itemSent}">
									<span style="color:green;font-weight:bold;">Sí</span>
								</c:if>
								<c:if test="${!win.itemSent}">
									<span style="color:red;font-weight:bold;">No</span>
								</c:if>
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
							<td>
								<c:if test="${(win.item.type==4 || win.item.type==5 ) && win.isOnlyCredits == false}">
									<fmt:formatNumber value="${win.item.bitcoins}" minFractionDigits="2" maxFractionDigits="8"/>
								</c:if>
							</td>
							<td>
								<c:if test="${not empty win.item.credits && win.item.credits != 0}">
									<fmt:formatNumber value="${win.item.credits}"  maxFractionDigits="0"/>
								</c:if>
							</td>
							<td>
								<c:if test="${win.item.type == 4 || win.item.type == 5 }">
									<c:out value="${win.bitcoinAddress}"/>
								</c:if>
							</td>
							
							<td style="text-align:center;">
								<input type="button" value="Editar" onclick="document.location.href='<html:rewrite page="/do/win/edit?id="/><c:out value="${win.id}"/>'"/>
							</td>
						</tr>
					</c:forEach>
				</table>
				
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


