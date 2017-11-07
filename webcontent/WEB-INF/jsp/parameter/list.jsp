<%@page import="com.madgrid.model.Parameter"%>
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
		function deleteParameter(id){
			if( confirm('¿Desea borrar el parámetro?')){
				document.location.href='<html:rewrite page="/do/parameter/delete?id="/>' + id;
			}
		}
	</script>
	<span style="font-family:Arial;font-size:18px;">Listado de parámetros</span>
	
	<table width="60%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td>
				<input type="button" value="Nuevo parámetro" onclick="document.location.href='<html:rewrite page="/do/parameter/edit"/>'"/>
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
							Name
						</th>
						<th>
							Value
						</th>
						<th>
							Edit
						</th>
					</tr>
					
					<c:forEach items="${parameterList}" var="parameter">
						<tr>
							<td>
								<c:out value="${parameter.id}"/>
							</td>
							<td>
								<c:out value="${parameter.name}"/>
							</td>
							<td>
								<c:out value="${parameter.value}"/>
							</td>
							<td style="text-align:center;">
								<input type="button" value="Editar" onclick="document.location.href='<html:rewrite page="/do/parameter/edit?id="/><c:out value="${parameter.id}"/>'"/>
								<input type="button" value="Borrar" onclick="deleteParameter('<c:out value="${parameter.id}"/>');"/>
							</td>
						</tr>
					</c:forEach>
				</table>
				
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


