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
		<div class="st_formframe" style="width:50%;align-content:center;">
			<div class="title">SamVar Login</div>
   			<form:form role="form" modelAttribute="loginModel">
				<div class="formlabel">Initials *</div>
				<input type="text" name="username" size="40"/>
				<div class="formlabel">Password *</div>
				<input type="password" name="password" size="40"/>
				<br><br>
 				<button type="submit">Submit</button>
  			</form:form>
   		</div> 
   	</div>


 <jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
