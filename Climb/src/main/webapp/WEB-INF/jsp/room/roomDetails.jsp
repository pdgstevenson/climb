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
	<div class="container-fluid">
		<div class="st_formframe">
			<div class="title">${room.name}<span class="candidate" title="Add Wall"><a href='<spring:url value="/wall/add/${room.roomId}" htmlEscape="true"/>'> <span class="glyphicon glyphicon-plus"></span></a></span></div>

								
				<ol>
					<c:forEach var="wall" items="${room.walls}">
									
						<li><a href='<spring:url value="/wall/${wall.wallId}" htmlEscape="true"/>'>${wall.name}</a></li>

					</c:forEach>
				</ol>
	
  		</div>
 	</div>   

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
