<%@page import="com.madgrid.model.Grid"%>
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
		
		function tweetMessageSend(id){
			
			$.post("<html:rewrite page="/do/tweet/messageSend"/>", { id: id}, function(data){
				var button = document.getElementById('sendMessageButton');
				if( button != null){
					button.style.visibility="hidden";
				}
			});
			
		}
		
		
		
	</script>

	<span style="font-family:Arial;font-size:18px;">Edición de tweet</span>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<html:form action="/tweet/save">
					<html:hidden name="tweetForm" property="id"/>
					<table width="70%" class="tab">
						<tr>
							<td>
								Id
							</td>
							<td>
								<c:out value="${tweet.id}"/>
							</td>
						</tr>
						<tr>
							<td>
								Created
							</td>
							<td>
								<fmt:formatDate value="${tweet.created}" type="both" timeStyle="long" dateStyle="full" />
							</td>
						</tr>
						<tr>
							<td>
								User
							</td>
							<td>
								<a href="<html:rewrite page="/do/user/edit?id="/><c:out value="${tweet.user.id}"/>"><c:out value="${tweet.user.login}"/></a> (<c:out value="${tweet.user.email}"/>)
							</td>
						</tr>
						<tr>
							<td>
								Item
							</td>
							<td>
								 
								<c:out value="${tweet.win.item.name}" escapeXml="false"/> (<c:out value="${tweet.win.id}" escapeXml="false"/>)
							</td>
						</tr>
						
						<tr>
							<td>
								Text
							</td>
							<td>
								 
								<c:out value="${tweet.text}" escapeXml="false"/> 
							</td>
						</tr>
						
						<tr>
							<td>
								Paid
							</td>
							<td>
								<html:checkbox property="paid"/>
								<input type="hidden" value="false" name="paid"/>
								<c:if test="${!tweet.paid}">
									<input type="button" onclick="tweetMessageSend('<c:out value="${tweet.id}"/>');" value="Enviar mensaje" id="sendMessageButton"/>
								</c:if>
							</td>
						</tr>
						
						
						
						<tr>
							<td colspan="2" align="right">
								<input type="button" value="Cancelar" onclick="document.location.href='<html:rewrite page="/do/tweet/list"/>'"/>
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


