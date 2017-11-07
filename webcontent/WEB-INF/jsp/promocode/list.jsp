<%@page import="com.madgrid.model.PromoCode"%>
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
		function deletePromoCode(id){
			if( confirm('¿Desea borrar el código promocional?')){
				document.location.href='<html:rewrite page="/do/promocode/delete?id="/>' + id;
			}
		}
	</script>
	<span style="font-family:Arial;font-size:18px;">Listado de códigos promocionales</span>
	
	<table width="60%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td>
				<input type="button" value="Nuevo código promocional" onclick="document.location.href='<html:rewrite page="/do/promocode/edit"/>'"/>
			</td>
		</tr>
	</table>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<table width="60%" class="tab">
					<tr>
						<th>
							Id
						</th>
						<th>
							Code
						</th>
						<th>
							Type
						</th>
						<th>
							Count
						</th>
						<th>
							Edit
						</th>
					</tr>
					
					<c:forEach items="${promoCodeList}" var="promoCode">
						<tr>
							<td>
								<c:out value="${promoCode.id}"/>
							</td>
							<td>
								<c:out value="${promoCode.code}"/>
							</td>
							<td>
								<c:choose>
									<c:when test="${promoCode.type==1}">
										5 créditos de regalo al registrarse
									</c:when>
									<c:when test="${promoCode.type==2}">
										10 créditos de regalo al registrarse
									</c:when>
									<c:when test="${promoCode.type==3}">
										15 créditos de regalo al registrarse
									</c:when>
									<c:when test="${promoCode.type==4}">
										20 créditos de regalo al registrarse
									</c:when>
									<c:when test="${promoCode.type==5}">
										2x1 en la compra de créditos
									</c:when>
									<c:when test="${promoCode.type==6}">
										50% más en la compra de creditos
									</c:when>
									<c:when test="${promoCode.type==7}">
										20% más en la compra de creditos
									</c:when>
									<c:when test="${promoCode.type==8}">
										10% más en la compra de creditos
									</c:when>
									<c:when test="${promoCode.type==9}">
										10 créditos de regalo en la compra de créditos
									</c:when>
								</c:choose>
							</td>
							<td>
								<c:out value="${promoCode.count}"/>
							</td>
							<td style="text-align:center;">
								<input type="button" value="Editar" onclick="document.location.href='<html:rewrite page="/do/promocode/edit?id="/><c:out value="${promoCode.id}"/>'"/>
								<input type="button" value="Borrar" onclick="deletePromoCode('<c:out value="${promoCode.id}"/>');"/>
							</td>
						</tr>
					</c:forEach>
				</table>
				
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


