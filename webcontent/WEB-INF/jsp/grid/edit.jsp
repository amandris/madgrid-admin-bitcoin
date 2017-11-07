<%@page import="org.apache.ojb.broker.query.Criteria"%>
<%@page import="com.madgrid.dao.GridDAO"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date"%>
<%@page import="com.madgrid.admin.util.Utils"%>
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
		function checkGridType(){
			var elm = document.getElementById("type");
			var elm2 = document.getElementById("boxPrice");
			var elm3 = document.getElementById("boxes");
			var elm4 = document.getElementById("partialWinSeconds");
			
			
			elm2.disabled = true;
			elm2.value="";
			elm2.style.backgroundColor="#606060";
			elm3.disabled = false;
			elm3.style.backgroundColor="#FFFFFF";
			elm4.disabled = false;
			elm4.style.backgroundColor="#FFFFFF";
			
			if( elm.value==4){
				elm2.disabled = false;
				elm2.style.backgroundColor="#FFFFFF";
			} 
			
			if( elm.value==5){
				elm3.disabled = true;
				elm3.value="";
				elm3.style.backgroundColor="#606060";
				elm4.disabled = true;
				elm4.value="";
				elm4.style.backgroundColor="#606060";
			} 
			
			if( elm.value==3){
				elm4.disabled = true;
				elm4.value="";
				elm4.style.backgroundColor="#606060";
				
			}
		}
		
		function changeItem(elm){
			var elm2 = document.getElementById("virtualPath");
			<c:forEach items="${itemList}" var="item">
				if( elm.value == <c:out value="${item.id}"/>){
					elm2.value = "<c:out value="${item.virtualPath}"/>";
				}
			</c:forEach>
		}
		
		window.onload=checkGridType;
	</script>

	<span style="font-family:Arial;font-size:18px;">Edición de juego</span>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<html:form action="/grid/save">
					<html:hidden name="gridForm" property="id"/>
					<table width="70%" class="tab">
						<tr>
							<td>
								Id
							</td>
							<td>
								<c:if test="${grid.id != 0}">
									<c:out value="${grid.id}"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								Created
							</td>
							<td>
								<fmt:formatDate value="${grid.created}" type="both" timeStyle="short" dateStyle="full" />
								
							</td>
						</tr>
						<tr>
							<td>
								StartDate
								<span style="color:red;"><html:errors property="startDate"/></span>
							</td>
							<td>
								<%
									GregorianCalendar startTime = new GregorianCalendar();
									GridDAO gridDAO = new GridDAO();
								
									startTime.setTime( Utils.today());
								
									startTime.set( GregorianCalendar.MINUTE, ((startTime.get( GregorianCalendar.MINUTE) / 10) * 10) + 5 );
									startTime.set( GregorianCalendar.SECOND, 0);
									startTime.set( GregorianCalendar.MILLISECOND, 0);
								
									Criteria criteria = new Criteria();
									criteria.addEqualTo("startDate", startTime.getTime());
									Grid testGrid = gridDAO.getGridByCriteria(criteria);
									
									int addTime = 5;
									
									while( testGrid != null){
										startTime.setTime( Utils.today());
										
										startTime.set( GregorianCalendar.MINUTE, ((startTime.get( GregorianCalendar.MINUTE) / 10) * 10) + addTime );
										startTime.set( GregorianCalendar.SECOND, 0);
										startTime.set( GregorianCalendar.MILLISECOND, 0);
										
										criteria = new Criteria();
										criteria.addEqualTo("startDate", startTime.getTime());
										testGrid = gridDAO.getGridByCriteria(criteria);
										
										addTime = addTime + 5;
									}
									
									
									String date = Utils.getDate(startTime.getTime(), 1);
									String time = Utils.getTime(startTime.getTime());
									String startTimeString = date + " " + time;
									request.setAttribute("startTimeString", startTimeString);
									
								%>
							
								<c:if test="${empty grid.id || grid.id == 0}">
									
									<input type="text" name="startDate" value="<c:out value="${startTimeString}"/>"> DD/MM/YY hh:mm:ss
								</c:if>
								<c:if test="${not empty grid.id && grid.id != 0}">
									<fmt:formatDate value="${grid.startDate}" type="both" timeStyle="short" dateStyle="full" />
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								Type
							</td>
							<td>
								<c:if test="${empty grid.id || grid.id == 0}">
									<html:select property="type" styleId="type" onchange="checkGridType();">
										<html:option value="1">Normal</html:option>
										<html:option value="2">Princiante</html:option>
										<html:option value="3">Partida Rápida</html:option>
										<html:option value="4">Precio fijo</html:option>
										<html:option value="5">Doble o Nada</html:option>
										<html:option value="6">Gratuito</html:option>
										<html:option value="7">Multipremio</html:option>
									</html:select>
								</c:if>
								<c:if test="${not empty grid.id && grid.id != 0}">
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
										Doble o Nada
									</c:if>
									<c:if test="${grid.type==6}">
										Gratuito
									</c:if>
									<c:if test="${grid.type==7}">
										Multipremio
									</c:if>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								Item
								<span style="color:red;"><html:errors property="itemId"/></span>
							</td>
							<td>
								<c:if test="${empty grid.id || grid.id == 0}">
									<html:select property="itemId" onchange="changeItem(this);">
										<option value=""/>Elige un item</option>
										<c:forEach items="${itemList}" var="item">
											<c:if test="${item.type == 2}">
												<option value="<c:out value="${item.id}"/>"><c:out value="${item.name}" escapeXml="false"/></option>
											</c:if>
										</c:forEach>
										<c:forEach items="${itemList}" var="item">
											<c:if test="${item.type == 4 && item.id != 101 && item.id != 102 && item.id != 103 && item.id != 104 && item.id != 105 && item.id != 41 && item.id != 61}">
												
													<option value="<c:out value="${item.id}"/>"><c:out value="${item.name}" escapeXml="false"/></option>
												
											</c:if>
										</c:forEach>
										<c:forEach items="${itemList}" var="item">
											<c:if test="${item.type == 5 && item.name == 'Treasure chest'}">
													<option value="<c:out value="${item.id}"/>"><c:out value="${item.name}" escapeXml="false"/> (<fmt:formatNumber value="${item.bitcoins}" maxFractionDigits="3"/> BTC + <fmt:formatNumber value="${item.credits}" maxFractionDigits="0"/> credits)</option>
											</c:if>
											<c:if test="${item.type == 5 && item.name != 'Treasure chest'}">
													<option value="<c:out value="${item.id}"/>"><c:out value="${item.name}" escapeXml="false"/></option>
											</c:if>
										</c:forEach>
									</html:select>
								</c:if>
								<c:if test="${not empty grid.id && grid.id != 0}">
									<table>
										<tr>
											<td style="border:0px;">
												<a href="<html:rewrite page="/do/item/edit?id="/><c:out value="${grid.item.id}"/>"><c:out value="${grid.item.name}" escapeXml="false"/></a>
											</td>
											<td style="border:0px;">
												<img src="http://www.instantri.ch/img/item/<c:out value="${grid.item.picture1Url}" />" width="48"/>
											</td>
										</tr>
									</table>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								VirtualPath
								<span style="color:red;"><html:errors property="virtualPath"/></span>
								<htm:errors/>
							</td>
							<td>
								<c:if test="${empty grid.id || grid.id == 0}">
									<html:text property="virtualPath" value="/item/" styleId="virtualPath"/>
								</c:if>
								<c:if test="${not empty grid.id && grid.id != 0}">
									<c:out value="${grid.virtualPath}"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								Boxes
								<span style="color:red;"><html:errors property="boxes"/></span>
							</td>
							<td>
								<c:if test="${empty grid.id || grid.id == 0}">
									<html:text property="boxes" styleId="boxes"/>
								</c:if>
								<c:if test="${not empty grid.id && grid.id != 0}">
									<c:out value="${grid.boxes}"/> cajas
								</c:if>
								
							</td>
						</tr>
						<tr>
							<td>
								PartialWinSeconds
								<span style="color:red;"><html:errors property="partialWinSeconds"/></span>
							</td>
							<td>
								<c:if test="${empty grid.id || grid.id == 0}">
									<html:text property="partialWinSeconds" styleId="partialWinSeconds"/>
								</c:if>
								<c:if test="${not empty grid.id && grid.id != 0}">
									<c:out value="${grid.partialWinSeconds}"/> segundos
								</c:if>
								
							</td>
						</tr>
						<tr>
							<td>
								BoxPrice
							</td>
							<td>
								<c:if test="${empty grid.id || grid.id == 0}">
									<html:text property="boxPrice" styleId="boxPrice"/>
								</c:if>
								<c:if test="${not empty grid.id && grid.id != 0}">
									<fmt:formatNumber value="${grid.boxPrice}" maxFractionDigits="0"/> créditos
								</c:if>
							</td>
						</tr>
						
						
						
						<tr>
							<td>
								Cajas cerradas
							</td>
							<td>
								<c:out value="${grid.freeBoxes}"/> cajas
							</td>
						</tr>
						<tr>
							<td>
								Cajas abiertas
							</td>
							<td>
								<c:out value="${grid.boughtBoxes}"/> cajas
							</td>
						</tr>
						<tr>
							<td>
								Créditos gastados
							</td>
							<td>
								<fmt:formatNumber value="${grid.moneyWon}" maxFractionDigits="0"/> créditos
							</td>
						</tr>
						<tr>
							<td>
								WinPos
							</td>
							<td>
								<c:out value="${grid.winPos}"/>
							</td>
						</tr>
						<c:if test="${grid.type == 7 }">
						<tr>
							<td>
								MultiPrizeWinPos
							</td>
							<td>
								<c:out value="${grid.multiPrize1_1CreditPos}"/>, 
								<c:out value="${grid.multiPrize1_2CreditPos}"/>, 
								<c:out value="${grid.multiPrize1_3CreditPos}"/>, 
								<c:out value="${grid.multiPrize1_4CreditPos}"/>, 
								<c:out value="${grid.multiPrize1_5CreditPos}"/>,
								<c:out value="${grid.multiPrize2_1CreditPos}"/>,
								<c:out value="${grid.multiPrize2_2CreditPos}"/>,
								<c:out value="${grid.multiPrize5CreditPos}"/>,
								<c:out value="${grid.multiPrize10CreditPos}"/> 
						</td>
						</tr>
						</c:if>
						<tr>
							<td>
								WinPosText
							</td>
							<td>
								<c:out value="${grid.winPosText}"/>
							</td>
						</tr>
						
						<tr>
							<td>
								WinPosHash
							</td>
							<td>
								<c:out value="${grid.winPosHash}"/>
							</td>
						</tr>
						
						
						<tr>
							<td>
								Estado
							</td>
							<td>
								<c:choose>
									<c:when test="${grid.isInPartialWin}">
										Partial Win
									</c:when>
									<c:when test="${grid.finished}">
										Finished
									</c:when>
									<c:when test="${grid.ongoing}">
										Ongoing
									</c:when>
									<c:otherwise>
										Waiting to start
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="right">
								<input type="button" value="Cancelar" onclick="document.location.href='<html:rewrite page="/do/grid/list"/>'"/>
								<c:if test="${empty grid.id || grid.id == 0}">
									<input type="submit" value="Ok" />
								</c:if>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
		
		
		
		<tr>
			<td align="center"  style="font-family:Arial;font-size:18px;">
				Histórico
			</td>
		</tr>
		<tr>
			<td align="center">
				<table width="70%" class="tab">
					<tr>
						<td style="background-color: #303030;color:white;">
							<strong>Id</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Fecha</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Type</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Text</strong>
						</td>
					</tr>
					<c:forEach items="${gridHistoricList}" var="gridHistoric">
						<tr>
							<td>
								<c:out value="${gridHistoric.id}"/>
							</td>
							<td>
								<fmt:formatDate value="${gridHistoric.created}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							<td>
								<c:choose>
									<c:when test="${gridHistoric.type==1}">
										<span style="color:darkred;">1-Comienza el juego</span>
									</c:when>
									<c:when test="${gridHistoric.type==2}">
										<span style="color:darkgreen;">2-Se abre caja</span>
									</c:when>
									<c:when test="${gridHistoric.type==3}">
										<span style="color:navy;">3-Se encuentra premio</span>
									</c:when>
									<c:when test="${gridHistoric.type==4}">
										<span style="color:darkgoldenrod;">4-Final (Se gana premio)</span>
									</c:when>
									<c:when test="${gridHistoric.type==5}">
										<span style="color:MediumVioletRed;">5-Final (No hay ganador)</span>
									</c:when>
									<c:when test="${gridHistoric.type==6}">
										<span style="color:MediumVioletRed;">6-Final (Gana el anterior acertante)</span>
									</c:when>
									<c:when test="${gridHistoric.type==7}">
										<span style="color:MediumVioletRed;">7-Se encuentra premio en juego multipremio</span>
									</c:when>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${gridHistoric.type==1}">
										<span style="color:darkred;">Comienza el juego</span>
									</c:when>
									<c:when test="${gridHistoric.type==2}">
										<span style="color:darkgreen;">Se abre caja <fmt:formatNumber value="${gridHistoric.value1}" maxFractionDigits="0"/> por el usuario <c:out value="${gridHistoric.value2}"/></span>
									</c:when>
									<c:when test="${gridHistoric.type==3}">
										<span style="color:navy;">Se encuentra el premio en la caja <fmt:formatNumber value="${gridHistoric.value1}" maxFractionDigits="0"/> por el usuario <c:out value="${gridHistoric.value2}"/></span>
									</c:when>
									<c:when test="${gridHistoric.type==4}">
										<span style="color:darkgoldenrod;">Se termina el juego. El ganador es <c:out value="${gridHistoric.value2}"/></span>
									</c:when>
									<c:when test="${gridHistoric.type==5}">
										<span style="color:MediumVioletRed;">Final del juego sin ganadores</span>
									</c:when>
									<c:when test="${gridHistoric.type==6}">
										<span style="color:MediumVioletRed;">Se termina el juego. El ganador es <c:out value="${gridHistoric.value2}"/> (Anterior acertante)</span>
									</c:when>
									<c:when test="${gridHistoric.type==7}">
										<span style="color:MediumVioletRed;">El usuario <c:out value="${gridHistoric.value3}"/> encuentra <fmt:formatNumber value="${gridHistoric.value1}" maxFractionDigits="0"/> céditos en la caja <c:out value="${gridHistoric.value2}"/></span>
									</c:when>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		
		
	</table>

</tiles:put>
</tiles:insert>


