<!DOCTYPE html>
<%@ page session="false" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tatari" tagdir="/WEB-INF/tags" %>
<html lang="en">
<jsp:include page="../fragments/htmlHeader.jsp"/>
<body>

 <jsp:include page="../fragments/menubar.jsp"/>
<security:authentication var="user" property="principal" />
	<div class="container">
		<div class="st_formframe">
			<div class="title">${centre.name}<span class="candidate" title="Add Room"><a href='<spring:url value="/room/add/${centre.centreId}" htmlEscape="true"/>'> <span class="glyphicon glyphicon-plus"></span></a></span></div>
								
				<ol>
					<c:forEach var="room" items="${centre.rooms}" >
									
						<li><a href='<spring:url value="/room/${room.roomId}" htmlEscape="true"/>'>${room.name}</a></li>

					</c:forEach>
				</ol>
	
  		</div>
 	</div>   

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
