<!DOCTYPE html>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tatari" tagdir="/WEB-INF/tags" %>
<html lang="en">
<jsp:include page="../fragments/htmlHeader.jsp"/>
<body>
<jsp:include page="../fragments/menubar.jsp"/>
	<div class="container">
		<div class="st_formframe">
			<div class="title">Batches</div>
       		<ul>
				<c:forEach var="batch" items="${batches.content}">
					<li><a href='<spring:url value="/batch/${batch.varSeqId}" htmlEscape="true"/>'>${batch.varSeqId}</a> [${batch.sequencingType.type}] ${batch.formattedSequenceRunDate}</td>	
				</c:forEach>
			</ul>	
			<div class="previousnext">
    				<c:if test="${batches.number > 0}">
					<a class="previousnext" href="<spring:url value="/batch/list?page=${batches.number - 1}&size=20"/>">&laquo; Previous</a>
    				</c:if>
    				<c:if test="${batches.number < variants.getTotalPages()-1}">
    					<a class="previousnext" href="<spring:url value="/batch/list?page=${batches.number + 1}&size=20"/>">Next &raquo;</a>
    				</c:if>
  			</div>
 		</div>
 	</div>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
