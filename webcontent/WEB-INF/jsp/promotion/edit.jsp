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

	<span style="font-family:Arial;font-size:18px;">Edición de promoción</span>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<html:form action="/promotion/save">
				<html:errors/>
					<html:hidden name="promotionForm" property="id"/>
					<table width="70%" class="tab">
						<tr>
							<td>
								Id
							</td>
							<td>
								<c:if test="${promotionForm.id != 0}">
									<c:out value="${promotionForm.id}"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								Created
							</td>
							<td>
								<fmt:formatDate value="${promotion.created}" type="both" timeStyle="long" dateStyle="full" />
								
							</td>
						</tr>
						<tr>
							<td>
								Modified
							</td>
							<td>
								<fmt:formatDate value="${promotion.modified}" type="both" timeStyle="long" dateStyle="full" />
							</td>
						</tr>
						<tr>
							<td>
								StartDate
								<span style="color:red;"><html:errors property="startDate"/></span>
							</td>
							<td>
								<html:text property="startDate" /> DD/MM/YYYY hh:mm:ss
							</td>
						</tr>
						<tr>
							<td>
								EndDate
								<span style="color:red;"><html:errors property="endDate"/></span>
							</td>
							<td>
								<html:text property="endDate" /> DD/MM/YYYY hh:mm:ss
							</td>
						</tr>
						<tr>
							<td>
								Name
								<span style="color:red;"><html:errors property="name"/></span>
							</td>
							<td>
								<html:text property="name" />
							</td>
						</tr>
						
						<tr>
							<td>
								Type
							</td>
							<td>
								<html:select property="type">
									<html:option value="1">2X1</html:option>
									<html:option value="2">10 créditos de regalo al registrarte</html:option>
									<html:option value="3">20 créditos de regalo al registrarte</html:option>
									<html:option value="4">10 créditos de regalo al comprar</html:option>
									<html:option value="13">5 créditos de regalo al comprar</html:option>
									<html:option value="14">1 créditos de regalo al comprar</html:option>
									<html:option value="10">10% de regalo al comprar</html:option>
									<html:option value="11">20% de regalo al comprar</html:option>
									<html:option value="12">30% de regalo al comprar</html:option>
									<html:option value="5">Devolver créditos gastados</html:option>
									<html:option value="6">5 créditos al registrarse</html:option>
									<html:option value="7">2 créditos al registrarse</html:option>
									<html:option value="8">1 crédito al registrarse</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="button" value="Cancelar" onclick="document.location.href='<html:rewrite page="/do/promotion/list"/>'"/>
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


