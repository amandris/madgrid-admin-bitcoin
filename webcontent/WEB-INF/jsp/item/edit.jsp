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
		function checkType(){
			var type = document.getElementById("type");
			var credits = document.getElementById("credits");
			var bitcoins = document.getElementById("bitcoins");
			
			if( type.value==2){
				credits.style.display = "table-row";
				bitcoins.style.display = "none";
			} else if( type.value==4 ){
				credits.style.display = "none";
				bitcoins.style.display = "table-row";
			} else if( type.value==5 ){
				credits.style.display = "table-row";
				bitcoins.style.display = "table-row";
			}
		}
		
		window.onload=checkType;
	</script>

	<span style="font-family:Arial;font-size:18px;">Edición de Item</span>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<html:form action="/item/save" enctype="multipart/form-data"  method="POST" >
					<html:hidden name="itemForm" property="id"/>
					<table width="70%" class="tab">
						<tr>
							<td>
								Id
							</td>
							<td>
								<c:if test="${itemForm.id != 0}">
									<c:out value="${itemForm.id}"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								Created
							</td>
							<td>
								<fmt:formatDate value="${itemForm.created}" type="both" timeStyle="long" dateStyle="full" />
								
							</td>
						</tr>
						<tr>
							<td>
								Modified
							</td>
							<td>
								<fmt:formatDate value="${itemForm.modified}" type="both" timeStyle="long" dateStyle="full" />
								
							</td>
						</tr>
						
						<tr>
							<td>
								Name
								<span style="color:red;"><html:errors property="name"/></span>
							</td>
							<td>
								<html:text property="name"/>
							</td>
						</tr>
						
						
						
						<tr>
							<td>
								Type
								<span style="color:red;"><html:errors property="type"/></span>
							</td>
							<td>
								<html:select property="type" onchange="checkType();" styleId="type">
									<html:option value="2">Sólo Créditos</html:option>
									<html:option value="4">Sólo Bitcoins</html:option>
									<html:option value="5">Créditos y Bitcoins</html:option>
								</html:select>
							</td>
						</tr>
						
						<tr id="credits">
							<td>
								Credits
								<span style="color:red;"><html:errors property="credits"/></span>
							</td>
							<td>
								<html:text property="credits"/>
							</td>
						</tr>
						
						<tr id="bitcoins">
							<td>
								Bitcoins
								<span style="color:red;"><html:errors property="bitcoins"/></span>
							</td>
							<td>
								<html:text property="bitcoins"/>
							</td>
						</tr>
						
						<tr>
							<td>
								HtmlDescription
								<span style="color:red;"><html:errors property="htmlDescription"/></span>
							</td>
							<td>
								<html:textarea cols="100" rows="6" property="htmlDescription" ></html:textarea>
							</td>
						</tr>
						
						
						
						<tr>
							<td>
								VirtualPath
								<span style="color:red;"><html:errors property="virtualPath"/></span>
							</td>
							<td>
								<html:text property="virtualPath"></html:text>
							</td>
						</tr>
						
						<tr>
							<td>
								Imagen 1 (198x138)
								<span style="color:red;"><html:errors property="picture1Url"/></span>
							</td>
							<td>
								<table>
									<tr>
										<td>
											<html:file property="picture1Url" />
										</td>
										<td>
											<c:if test="${not empty item.picture1Url}">
												<img src="<html:rewrite page="/img/item/"/><c:out value="${item.picture1Url}"/>"/>
											</c:if>
											<c:if test="${empty item.picture1Url}">
												Imagen no asignada
											</c:if>
										</td>
										<td>
											<input type="button" value="Borrar" onclick="document.location.href='<html:rewrite page="/do/item/pictureDelete"/>?id=<c:out value="${item.id}"/>&picture=1'"/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						
						
				
						
						
						
						
						
						<tr>
							<td colspan="2" align="right">
								<input type="button" value="Cancelar" onclick="document.location.href='<html:rewrite page="/do/item/list"/>'"/>
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


