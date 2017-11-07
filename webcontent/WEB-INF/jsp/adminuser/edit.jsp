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

	<span style="font-family:Arial;font-size:18px;">Edición de administrador</span>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<html:form action="/adminuser/save">
					<html:hidden name="adminUserForm" property="id"/>
					<table width="70%" class="tab">
						<tr>
							<td>
								Id
							</td>
							<td>
								<c:if test="${adminUserForm.id != 0}">
									<c:out value="${adminUserForm.id}"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								Created
							</td>
							<td>
								<fmt:formatDate value="${adminUserForm.created}" type="both" timeStyle="long" dateStyle="full" />
								
							</td>
						</tr>
						<tr>
							<td>
								Modified
							</td>
							<td>
								<fmt:formatDate value="${adminUserForm.modified}" type="both" timeStyle="long" dateStyle="full" />
								
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
								<html:text property="password" />
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
								Surname
							</td>
							<td>
								<html:text property="surname" />
							</td>
						</tr>
						<tr>
							<td>
								Email
								<span style="color:red;"><html:errors property="email"/></span>
							</td>
							<td>
								<html:text  property="email" />
							</td>
						</tr>
						<tr>
							<td>
								PhoneNumber
								<span style="color:red;"><html:errors property="phoneNumber"/></span>
							</td>
							<td>
								<html:text  property="phoneNumber" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="button" value="Cancelar" onclick="document.location.href='<html:rewrite page="/do/adminuser/list"/>'"/>
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


