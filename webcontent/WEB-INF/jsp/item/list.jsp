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
		function deleteItem(id){
			if( confirm('¿Desea borrar el Item?')){
				document.location.href='<html:rewrite page="/do/item/delete?id="/>' + id;
			}
		}
	</script>
	<span style="font-family:Arial;font-size:18px;">Listado de Items</span>
	
	<table width="90%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td>
				<input type="button" value="Nuevo item" onclick="document.location.href='<html:rewrite page="/do/item/edit"/>'"/>
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
							Created
						</th>
						<th>
							Name
						</th>
						<th>
							VirtualPath
						</th>
						<th>
							Type
						</th>
						
						<th>
							Credits
						</th>
						<th>
							Bitcoins
						</th>
						<th>
							Picture
						</th>
						<th>
							Edit
						</th>
					</tr>
					
					<c:forEach items="${itemList}" var="item">
						<c:if test="${item.id != 101 && item.id != 102 && item.id != 103 && item.id != 104 && item.id != 105}">
							<tr>
								<c:choose>
									<c:when test="${item.type==2}"><td style="color:black;font-weight:bold;background-color:#66BBF0;"></c:when>
									<c:when test="${item.type==4}"><td style="color:black;font-weight:bold;background-color:#FFF0A1;"></c:when>
									<c:when test="${item.type==5}"><td style="color:black;font-weight:bold;background-color:#705C5F;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<c:out value="${item.id}"/>
								</td>
								<c:choose>
									<c:when test="${item.type==2}"><td style="color:black;font-weight:bold;background-color:#66BBF0;"></c:when>
									<c:when test="${item.type==4}"><td style="color:black;font-weight:bold;background-color:#FFF0A1;"></c:when>
									<c:when test="${item.type==5}"><td style="color:black;font-weight:bold;background-color:#705C5F;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<fmt:formatDate value="${item.created}" type="both" timeStyle="short" dateStyle="full" />
								</td>
								<c:choose>
									<c:when test="${item.type==2}"><td style="color:black;font-weight:bold;background-color:#66BBF0;"></c:when>
									<c:when test="${item.type==4}"><td style="color:black;font-weight:bold;background-color:#FFF0A1;"></c:when>
									<c:when test="${item.type==5}"><td style="color:black;font-weight:bold;background-color:#705C5F;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<c:out value="${item.name}" escapeXml="false"/>
								</td>
								<c:choose>
									<c:when test="${item.type==2}"><td style="color:black;font-weight:bold;background-color:#66BBF0;"></c:when>
									<c:when test="${item.type==4}"><td style="color:black;font-weight:bold;background-color:#FFF0A1;"></c:when>
									<c:when test="${item.type==5}"><td style="color:black;font-weight:bold;background-color:#705C5F;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<c:out value="${item.virtualPath}" escapeXml="false"/>
								</td>
								<c:choose>
									<c:when test="${item.type==2}"><td style="color:black;font-weight:bold;background-color:#66BBF0;"></c:when>
									<c:when test="${item.type==4}"><td style="color:black;font-weight:bold;background-color:#FFF0A1;"></c:when>
									<c:when test="${item.type==5}"><td style="color:black;font-weight:bold;background-color:#705C5F;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<c:choose>
										<c:when test="${item.type==2}">
											Créditos
										</c:when>
										<c:when test="${item.type==4}">
											Bitcoins
										</c:when>
										<c:when test="${item.type==5}">
											Créditos y Bitcoins
										</c:when>
									</c:choose>
								</td>
								
								<c:choose>
									<c:when test="${item.type==2}"><td style="color:black;font-weight:bold;background-color:#66BBF0;"></c:when>
									<c:when test="${item.type==4}"><td style="color:black;font-weight:bold;background-color:#FFF0A1;"></c:when>
									<c:when test="${item.type==5}"><td style="color:black;font-weight:bold;background-color:#705C5F;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<c:if test="${not empty item.credits && item.credits != 0}">
										<c:out value="${item.credits}"/>
									</c:if>
								</td>
								<c:choose>
									<c:when test="${item.type==2}"><td style="color:black;font-weight:bold;background-color:#66BBF0;"></c:when>
									<c:when test="${item.type==4}"><td style="color:black;font-weight:bold;background-color:#FFF0A1;"></c:when>
									<c:when test="${item.type==5}"><td style="color:black;font-weight:bold;background-color:#705C5F;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<c:if test="${not empty item.bitcoins && item.bitcoins != 0}">
										<fmt:formatNumber value="${item.bitcoins}" minFractionDigits="0" maxFractionDigits="8"/>
									</c:if>
								</td>
								<c:choose>
									<c:when test="${item.type==2}"><td style="color:black;font-weight:bold;background-color:#66BBF0;"></c:when>
									<c:when test="${item.type==4}"><td style="color:black;font-weight:bold;background-color:#FFF0A1;"></c:when>
									<c:when test="${item.type==5}"><td style="color:black;font-weight:bold;background-color:#705C5F;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<c:if test="${not empty item.picture1Url}">
										<img src="<html:rewrite page="/img/item/"/><c:out value="${item.picture1Url}"/>" width="50"/>
									</c:if>
								</td>
								<c:choose>
									<c:when test="${item.type==2}"><td style="color:black;font-weight:bold;background-color:#66BBF0;text-align:center;"></c:when>
									<c:when test="${item.type==4}"><td style="color:black;font-weight:bold;background-color:#FFF0A1;text-align:center;"></c:when>
									<c:when test="${item.type==5}"><td style="color:black;font-weight:bold;background-color:#705C5F;text-align:center;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<input type="button" value="Editar" onclick="document.location.href='<html:rewrite page="/do/item/edit?id="/><c:out value="${item.id}"/>'"/>
									<input type="button" value="Borrar" onclick="deleteItem('<c:out value="${item.id}"/>');"/>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
				
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


