<%@page import="com.madgrid.model.Promotion"%>
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
		function deletePromotion(id){
			if( confirm('¿Desea borrar la promoción?')){
				document.location.href='<html:rewrite page="/do/promotion/delete?id="/>' + id;
			}
		}
	</script>
	<span style="font-family:Arial;font-size:18px;">Listado de promociones</span>
	
	<table width="90%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td>
				<input type="button" value="Nueva promoción" onclick="document.location.href='<html:rewrite page="/do/promotion/edit"/>'"/>
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
							Name
						</th>
						<th>
							Type
						</th>
						<th>
							StartDate
						</th>
						<th>
							EndDate
						</th>
						<th>
							Edit
						</th>
					</tr>
					
					<c:forEach items="${promotionList}" var="promotion">
						<tr>
							<td>
								<c:out value="${promotion.id}"/>
							</td>
							<td>
								<c:out value="${promotion.name}"/>
							</td>
							<td>
								<c:choose>
									<c:when test="${promotion.type==1}">
										2X1
									</c:when>
									<c:when test="${promotion.type==2}">
										10 créditos de regalo al registrarte
									</c:when>
									<c:when test="${promotion.type==3}">
										20 créditos de regalo al registrarte
									</c:when>
									<c:when test="${promotion.type==4}">
										10 créditos de regalo al comprar
									</c:when>
									<c:when test="${promotion.type==13}">
										5 créditos de regalo al comprar
									</c:when>
									<c:when test="${promotion.type==14}">
										1 crédito de regalo al comprar
									</c:when>
									<c:when test="${promotion.type==10}">
										10% de regalo al comprar
									</c:when>
									<c:when test="${promotion.type==11}">
										20% de regalo al comprar
									</c:when>
									<c:when test="${promotion.type==12}">
										30% de regalo al comprar
									</c:when>
									<c:when test="${promotion.type==5}">
										Devolver créditos gastados
									</c:when>
									<c:when test="${promotion.type==6}">
										5 créditos al registrarse
									</c:when>
									<c:when test="${promotion.type==7}">
										2 créditos al registrarse
									</c:when>
									<c:when test="${promotion.type==8}">
										1 crédito al resgitarse
									</c:when>
									
								</c:choose>
							</td>
							<td>
								<fmt:formatDate value="${promotion.startDate}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							<td>
								<fmt:formatDate value="${promotion.endDate}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							<td style="text-align:center;">
								<input type="button" value="Editar" onclick="document.location.href='<html:rewrite page="/do/promotion/edit?id="/><c:out value="${promotion.id}"/>'"/>
								<input type="button" value="Borrar" onclick="deletePromotion('<c:out value="${promotion.id}"/>');"/>
							</td>
						</tr>
					</c:forEach>
				</table>
				
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


