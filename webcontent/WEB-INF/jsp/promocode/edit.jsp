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

	<span style="font-family:Arial;font-size:18px;">Edici�n de c�digo promocional</span>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<html:form action="/promocode/save">
					<html:hidden name="promoCodeForm" property="id"/>
					<table width="70%" class="tab">
						<tr>
							<td>
								Id
							</td>
							<td>
								<c:if test="${promoCodeForm.id != 0}">
									<c:out value="${promoCodeForm.id}"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								Code
								<span style="color:red;"><html:errors property="code"/></span>
							</td>
							<td>
								<html:text property="code" />
							</td>
						</tr>
						<tr>
							<td>
								Type
								<span style="color:red;"><html:errors property="type"/></span>
							</td>
							<td>
								<html:select property="type">
									<html:option value="1">5 cr�ditos de regalo al registrarse</html:option>
									<html:option value="2">10 cr�ditos de regalo al registrarse</html:option>
									<html:option value="3">15 cr�ditos de regalo al registrarse</html:option>
									<html:option value="4">20 cr�ditos de regalo al registrarse</html:option>
									
									<html:option value="5">2x1 en la compra de cr�ditos</html:option>
									<html:option value="6">50% m�s en la compra de creditos</html:option>
									<html:option value="7">20% m�s en la compra de creditos</html:option>
									<html:option value="8">10% m�s en la compra de creditos</html:option>
									<html:option value="9">10 cr�ditos de regalo en la compra de cr�ditos</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td>
								Count
								<span style="color:red;"><html:errors property="count"/></span>
							</td>
							<td>
								<html:text property="count" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="button" value="Cancelar" onclick="document.location.href='<html:rewrite page="/do/promocode/list"/>'"/>
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


