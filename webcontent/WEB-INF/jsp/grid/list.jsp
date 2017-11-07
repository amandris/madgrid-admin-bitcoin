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
			function deleteGrid(id){
				if( confirm('¿Desea borrar el juego?')){
					document.location.href='<html:rewrite page="/do/grid/delete?id="/>' + id;
				}
			}
			
			function changeSeconds(id){
				 var seconds = prompt("PartialWinSeconds : ", "");
				 document.location.href='<html:rewrite page="/do/grid/changeSeconds?seconds="/>' + seconds+ '&id=' + id;
			}
		</script>
		<span style="font-family:Arial;font-size:18px;">Listado de Juegos</span>
		
		<table width="90%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
			<tr>
				<td>
					<input type="button" value="Nuevo juego" onclick="document.location.href='<html:rewrite page="/do/grid/edit"/>'"/>
				</td>
			</tr>
		</table>
	
		<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
			<tr>
				<td align="center">
					<table width="90%" class="tab" style="color:black;font-weight:bold;">
						<tr>
							<th>
								Id
							</th>
							<th>
								StartDate
							</th>
							<th>
								Type
							</th>
							<th>
								Item
							</th>
							<th>
							</th>
							<th>
								Abiertas
							</th>
							<th>
								Cerradas
							</th>
							<th>
								Creditos
							</th>
							<th>
								WinPos
							</th>
							<th>
								Seconds
							</th>
							<th>
								Status
							</th>
							<th>
								Edit
							</th>
						</tr>
						
						<c:forEach items="${gridList}" var="grid">
							<tr style="color:black;font-weight:bold;">
								<c:choose>
									<c:when test="${grid.type==1}"><td style="color:black;background-color:#DC104C;"></c:when>
									<c:when test="${grid.type==2}"><td style="color:black;background-color:#AAC747;"></c:when>
									<c:when test="${grid.type==3}"><td style="color:black;background-color:#1771CC;"></c:when>
									<c:when test="${grid.type==4}"><td style="color:black;background-color:#8018C9;"></c:when>
									<c:when test="${grid.type==5}"><td style="color:black;background-color:#9A764A;"></c:when>
									<c:when test="${grid.type==6}"><td style="color:black;background-color:#E6751A;"></c:when>
									<c:when test="${grid.type==7}"><td style="color:black;background-color:#09C8CA;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<c:out value="${grid.id}"/>
								</td>
							
								<c:choose>
									<c:when test="${grid.type==1}"><td style="color:black;background-color:#DC104C;"></c:when>
									<c:when test="${grid.type==2}"><td style="color:black;background-color:#AAC747;"></c:when>
									<c:when test="${grid.type==3}"><td style="color:black;background-color:#1771CC;"></c:when>
									<c:when test="${grid.type==4}"><td style="color:black;background-color:#8018C9;"></c:when>
									<c:when test="${grid.type==5}"><td style="color:black;background-color:#9A764A;"></c:when>
									<c:when test="${grid.type==6}"><td style="color:black;background-color:#E6751A;"></c:when>
									<c:when test="${grid.type==7}"><td style="color:black;background-color:#09C8CA;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<fmt:formatDate value="${grid.startDate}" type="both" timeStyle="short" dateStyle="full" />
								</td>
								<c:choose>
									<c:when test="${grid.type==1}"><td style="color:black;background-color:#DC104C;"></c:when>
									<c:when test="${grid.type==2}"><td style="color:black;background-color:#AAC747;"></c:when>
									<c:when test="${grid.type==3}"><td style="color:black;background-color:#1771CC;"></c:when>
									<c:when test="${grid.type==4}"><td style="color:black;background-color:#8018C9;"></c:when>
									<c:when test="${grid.type==5}"><td style="color:black;background-color:#9A764A;"></c:when>
									<c:when test="${grid.type==6}"><td style="color:black;background-color:#E6751A;"></c:when>
									<c:when test="${grid.type==7}"><td style="color:black;background-color:#09C8CA;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<c:if test="${grid.type==1}">
										Normal
									</c:if>
									<c:if test="${grid.type==2}">
										Princiante
									</c:if>
									<c:if test="${grid.type==3}">
										Partida rápida
									</c:if>
									<c:if test="${grid.type==4}">
										Precio fijo
									</c:if>
									<c:if test="${grid.type==5}">
										Doble O Nada
									</c:if>
									<c:if test="${grid.type==6}">
										Gratuito
									</c:if>
									<c:if test="${grid.type==7}">
										Multipremio
									</c:if>
								</td>
								<c:choose>
									<c:when test="${grid.type==1}"><td style="color:black;background-color:#DC104C;"></c:when>
									<c:when test="${grid.type==2}"><td style="color:black;background-color:#AAC747;"></c:when>
									<c:when test="${grid.type==3}"><td style="color:black;background-color:#1771CC;"></c:when>
									<c:when test="${grid.type==4}"><td style="color:black;background-color:#8018C9;"></c:when>
									<c:when test="${grid.type==5}"><td style="color:black;background-color:#9A764A;"></c:when>
									<c:when test="${grid.type==6}"><td style="color:black;background-color:#E6751A;"></c:when>
									<c:when test="${grid.type==7}"><td style="color:black;background-color:#09C8CA;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<a href="<html:rewrite page="/do/item/edit?id="/><c:out value="${grid.item.id}"/>"><c:out value="${grid.item.name}" escapeXml="false"/></a>
									<br/>
									<c:if test="${grid.item.type == 5}">
										(<fmt:formatNumber value="${grid.item.bitcoins}" maxFractionDigits="3"/> BTC + <fmt:formatNumber value="${grid.item.credits}" maxFractionDigits="0"/> credits)
									</c:if>
								</td>
								
								
								<c:choose>
									<c:when test="${grid.type==1}"><td style="color:black;background-color:#DC104C;"></c:when>
									<c:when test="${grid.type==2}"><td style="color:black;background-color:#AAC747;"></c:when>
									<c:when test="${grid.type==3}"><td style="color:black;background-color:#1771CC;"></c:when>
									<c:when test="${grid.type==4}"><td style="color:black;background-color:#8018C9;"></c:when>
									<c:when test="${grid.type==5}"><td style="color:black;background-color:#9A764A;"></c:when>
									<c:when test="${grid.type==6}"><td style="color:black;background-color:#E6751A;"></c:when>
									<c:when test="${grid.type==7}"><td style="color:black;background-color:#09C8CA;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<img src="http://www.instantri.ch/img/item/<c:out value="${grid.item.picture1Url}" />" width="48"/>
								</td>
								
								<c:choose>
									<c:when test="${grid.type==1}"><td style="color:black;background-color:#DC104C;"></c:when>
									<c:when test="${grid.type==2}"><td style="color:black;background-color:#AAC747;"></c:when>
									<c:when test="${grid.type==3}"><td style="color:black;background-color:#1771CC;"></c:when>
									<c:when test="${grid.type==4}"><td style="color:black;background-color:#8018C9;"></c:when>
									<c:when test="${grid.type==5}"><td style="color:black;background-color:#9A764A;"></c:when>
									<c:when test="${grid.type==6}"><td style="color:black;background-color:#E6751A;"></c:when>
									<c:when test="${grid.type==7}"><td style="color:black;background-color:#09C8CA;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<c:out value="${grid.boughtBoxes}"/> / <c:out value="${grid.boxes}"/>
								</td>
								
								<c:choose>
									<c:when test="${grid.type==1}"><td style="color:black;background-color:#DC104C;"></c:when>
									<c:when test="${grid.type==2}"><td style="color:black;background-color:#AAC747;"></c:when>
									<c:when test="${grid.type==3}"><td style="color:black;background-color:#1771CC;"></c:when>
									<c:when test="${grid.type==4}"><td style="color:black;background-color:#8018C9;"></c:when>
									<c:when test="${grid.type==5}"><td style="color:black;background-color:#9A764A;"></c:when>
									<c:when test="${grid.type==6}"><td style="color:black;background-color:#E6751A;"></c:when>
									<c:when test="${grid.type==7}"><td style="color:black;background-color:#09C8CA;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<c:out value="${grid.freeBoxes}"/>
								</td>
								<c:choose>
									<c:when test="${grid.type==1}"><td style="color:black;background-color:#DC104C;"></c:when>
									<c:when test="${grid.type==2}"><td style="color:black;background-color:#AAC747;"></c:when>
									<c:when test="${grid.type==3}"><td style="color:black;background-color:#1771CC;"></c:when>
									<c:when test="${grid.type==4}"><td style="color:black;background-color:#8018C9;"></c:when>
									<c:when test="${grid.type==5}"><td style="color:black;background-color:#9A764A;"></c:when>
									<c:when test="${grid.type==6}"><td style="color:black;background-color:#E6751A;"></c:when>
									<c:when test="${grid.type==7}"><td style="color:black;background-color:#09C8CA;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									
									<fmt:formatNumber value="${grid.boxPrice}" maxFractionDigits="0"/>
								</td>
								<c:choose>
									<c:when test="${grid.type==1}"><td style="color:black;background-color:#DC104C;"></c:when>
									<c:when test="${grid.type==2}"><td style="color:black;background-color:#AAC747;"></c:when>
									<c:when test="${grid.type==3}"><td style="color:black;background-color:#1771CC;"></c:when>
									<c:when test="${grid.type==4}"><td style="color:black;background-color:#8018C9;"></c:when>
									<c:when test="${grid.type==5}"><td style="color:black;background-color:#9A764A;"></c:when>
									<c:when test="${grid.type==6}"><td style="color:black;background-color:#E6751A;"></c:when>
									<c:when test="${grid.type==7}"><td style="color:black;background-color:#09C8CA;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									
									<fmt:formatNumber value="${grid.winPos}" maxFractionDigits="0"/>
								</td>
								<c:choose>
									<c:when test="${grid.type==1}"><td style="color:black;background-color:#DC104C;"></c:when>
									<c:when test="${grid.type==2}"><td style="color:black;background-color:#AAC747;"></c:when>
									<c:when test="${grid.type==3}"><td style="color:black;background-color:#1771CC;"></c:when>
									<c:when test="${grid.type==4}"><td style="color:black;background-color:#8018C9;"></c:when>
									<c:when test="${grid.type==5}"><td style="color:black;background-color:#9A764A;"></c:when>
									<c:when test="${grid.type==6}"><td style="color:black;background-color:#E6751A;"></c:when>
									<c:when test="${grid.type==7}"><td style="color:black;background-color:#09C8CA;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<c:out value="${grid.partialWinSeconds}"/>
								</td>
								
								
								<c:choose>
									<c:when test="${grid.type==1}"><td style="color:black;background-color:#DC104C;"></c:when>
									<c:when test="${grid.type==2}"><td style="color:black;background-color:#AAC747;"></c:when>
									<c:when test="${grid.type==3}"><td style="color:black;background-color:#1771CC;"></c:when>
									<c:when test="${grid.type==4}"><td style="color:black;background-color:#8018C9;"></c:when>
									<c:when test="${grid.type==5}"><td style="color:black;background-color:#9A764A;"></c:when>
									<c:when test="${grid.type==6}"><td style="color:black;background-color:#E6751A;"></c:when>
									<c:when test="${grid.type==7}"><td style="color:black;background-color:#09C8CA;"></c:when>
									<c:otherwise><td></c:otherwise>
								</c:choose>
									<c:choose>
									
										<c:when test="${grid.isInPartialWin}">
											<span style="color:#FF11FF;text-shadow: 2px 2px 6px #000000;font-size: 18px;">Partial Win</span>
										</c:when>
										<c:when test="${grid.finished}">
											<span style="color:#FF1111;text-shadow: 2px 2px 6px #000000;font-size: 18px;">Finished</span>
										</c:when>
										<c:when test="${grid.ongoing}">
											<span style="color:#11FF11;text-shadow: 2px 2px 6px #000000;font-size: 18px;">Ongoing</span>
										</c:when>
										<c:otherwise>
											<span style="color:#EEEEEE;text-shadow: 2px 2px 6px #000000;font-size: 18px;">Waiting to start</span>
										</c:otherwise>
									</c:choose>
								</td>
								
								<c:choose>
									<c:when test="${grid.type==1}"><td style="color:black;background-color:#DC104C;text-align:center;"></c:when>
									<c:when test="${grid.type==2}"><td style="color:black;background-color:#AAC747;text-align:center;"></c:when>
									<c:when test="${grid.type==3}"><td style="color:black;background-color:#1771CC;text-align:center;"></c:when>
									<c:when test="${grid.type==4}"><td style="color:black;background-color:#8018C9;text-align:center;"></c:when>
									<c:when test="${grid.type==5}"><td style="color:black;background-color:#9A764A;text-align:center;"></c:when>
									<c:when test="${grid.type==6}"><td style="color:black;background-color:#E6751A;text-align:center;"></c:when>
									<c:when test="${grid.type==7}"><td style="color:black;background-color:#09C8CA;text-align:center;"></c:when>
									<c:otherwise><td style="text-align:center;"></c:otherwise>
								</c:choose>
									<input type="button" value="Ver" onclick="document.location.href='<html:rewrite page="/do/grid/edit?id="/><c:out value="${grid.id}"/>'"/>
									<c:if test="${!grid.isInPartialWin && !grid.finished}">
										<input type="button" value="Segundos" onclick="changeSeconds(<c:out value="${grid.id}"/>);"/>
									</c:if>
									<input type="button" value="Borrar" onclick="deleteGrid('<c:out value="${grid.id}"/>');"/>
								</td>
							</tr>
						</c:forEach>
					</table>
					
				</td>
			</tr>
		</table>
	
	</tiles:put>
	</tiles:insert>
	
	
