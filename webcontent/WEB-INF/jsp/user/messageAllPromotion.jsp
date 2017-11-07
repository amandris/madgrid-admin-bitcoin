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

	<span style="font-family:Arial;font-size:18px;">Enviar mensaje a todos los usuarios</span>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<html:form action="/user/messageAllPromotionSend">
					<html:errors/>
					<table width="70%" class="tab">
						<tr>
							<td>
								Subject
							</td>
							<td>
								<html:text property="subject" />
							</td>
						</tr>
					
					
						<tr>
							<td>
								Title 1
							</td>
							<td>
								<html:text property="title1" />
							</td>
						</tr>
						<tr>
							<td>
								Picture 1 (http://www.instantri.ch/img/picture1.png)
							</td>
							<td>
								<html:text property="picture1" />
							</td>
						</tr>
						<tr>
							<td>
								Mensaje 2
							</td>
							<td>
								<html:textarea property="message1" cols="30" rows="5"/>
							</td>
						</tr>
						<tr>
							<td>
								Title 2
							</td>
							<td>
								<html:text property="title2" />
							</td>
						</tr>
						<tr>
							<td>
								Picture 2 (http://www.instantri.ch/img/picture2.png)
							</td>
							<td>
								<html:text property="picture2" />
							</td>
						</tr>
						<tr>
							<td>
								Mensaje 2
							</td>
							<td>
								<html:textarea property="message2" cols="30" rows="5"/>
							</td>
						</tr>
						<tr>
							<td>
								Title 3
							</td>
							<td>
								<html:text property="title3" />
							</td>
						</tr>
						<tr>
							<td>
								Picture 3 (http://www.instantri.ch/img/picture3.png)
							</td>
							<td>
								<html:text property="picture3" />
							</td>
						</tr>
						<tr>
							<td>
								Mensaje 3
							</td>
							<td>
								<html:textarea property="message3" cols="30" rows="5"/>
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="right">
								<input type="button" value="Cancelar" onclick="document.location.href='<html:rewrite page="/do/user/list"/>'"/>
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


