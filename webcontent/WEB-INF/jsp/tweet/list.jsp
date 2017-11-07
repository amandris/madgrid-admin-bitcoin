<%@page import="com.madgrid.model.Tweet"%>
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
	function deleteTweet(id){
		if( confirm('¿Desea borrar el tweet?')){
			document.location.href='<html:rewrite page="/do/tweet/delete?id="/>' + id;
		}
	}
	</script>
	<span style="font-family:Arial;font-size:18px;">
		Listado de Tweets
	</span>
	
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
							User
						</th>
						<th>
							Item
						</th>
						<th>
							Paid
						</th>
						
						<th>
							Edit
						</th>
					</tr>
					
					<c:forEach items="${tweetList}" var="tweet">
						<tr>
						
							<c:choose>
								<c:when test="${!tweet.paid }">
									<td style="color:black;font-weight:bold;background-color:#FFAAAA;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
							
								<c:out value="${tweet.id}"/>
							</td>
							<c:choose>
								<c:when test="${!tweet.paid }">
									<td style="color:black;font-weight:bold;background-color:#FFAAAA;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
								<fmt:formatDate value="${tweet.created}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							
							<c:choose>
								<c:when test="${!tweet.paid }">
									<td style="color:black;font-weight:bold;background-color:#FFAAAA;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
								<a href="<html:rewrite page="/do/user/edit?id="/><c:out value="${tweet.user.id}"/>"><c:out value="${tweet.user.login}"/></a> (<c:out value="${tweet.user.email}"/>)
							</td>
							
							
							<c:choose>
								<c:when test="${!tweet.paid }">
									<td style="color:black;font-weight:bold;background-color:#FFAAAA;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
								<c:out value="${tweet.win.item.name}" escapeXml="false"/> (<c:out value="${tweet.win.id}" escapeXml="false"/>)
							</td>
							
							<c:choose>
								<c:when test="${!tweet.paid }">
									<td style="color:black;font-weight:bold;background-color:#FFAAAA;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
								<c:if test="${tweet.paid}">
									<span style="color:green;font-weight:bold;">Sí</span>
								</c:if>
								<c:if test="${!tweet.paid}">
									<span style="color:red;font-weight:bold;">No</span>
								</c:if>
							</td>
							
							
							<c:choose>
								<c:when test="${!tweet.paid }">
									<td style="color:black;font-weight:bold;background-color:#FFAAAA;">
								</c:when>
								<c:otherwise>
									<td>
								</c:otherwise>
							</c:choose>
								<input type="button" value="Editar" onclick="document.location.href='<html:rewrite page="/do/tweet/edit?id="/><c:out value="${tweet.id}"/>'"/>
								<input type="button" value="Borrar" onclick="deleteTweet(<c:out value="${tweet.id}"/>);"/>
							</td>
						</tr>
					</c:forEach>
				</table>
				
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


