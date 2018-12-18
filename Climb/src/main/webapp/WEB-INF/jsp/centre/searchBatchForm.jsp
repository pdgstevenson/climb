<!DOCTYPE html>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tatari" tagdir="/WEB-INF/tags" %>
<html lang="en">
<jsp:include page="../fragments/htmlHeader.jsp"/>
<body>
<jsp:include page="../fragments/menubar.jsp"/>
	<div class="container">
		<div class="st_formframe">
			<div class="title">Search For Batch by VarSeq Id</div>
   			<form:form role="form" modelAttribute="searchBatchIdModel">
        			<div class="form-group has-feedback">	
        				<tatari:inputField label="VarSeq ID" type="text" name="varSeqId" placeholder="Enter VarSeq ID, e.g. '23726'"/>
				</div>
				<div class="buttondiv"><button type="submit" class="btn btn-default">Submit</button></div>
       		</form:form>
  		</div>
  	</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
