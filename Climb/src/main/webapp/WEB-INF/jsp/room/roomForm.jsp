<!DOCTYPE html>
<%@ page session="false" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
		
			<div class="title">New Room Details</div>
			<div class="panel-group">
    				<div class="panel panel-default">
      				<div class="panel-heading">
       					<h4 class="panel-title">
          					<a data-toggle="collapse" href="#addcentreinfo"><span class="green">&#9432;</span></a>
        					</h4>
      				</div>
      				<div id="addcentreinfo" class="panel-collapse collapse">
        					<div class="panel-body">
							<div class="info">
								<ul>

									<li>Name required.</li>
								</ul>
							</div> 				
        					</div>
      				</div>
    				</div>
  			</div>			
			<form:form role="form" modelAttribute="room" >

					<tatari:select label="Centre *" name="centre" names="${centres}" itemValue="name" itemLabel="name" showPleaseSelect="false" disabled="true"/>
					<tatari:inputField label="Name *" type="text" name="name" placeholder="Enter room name, max 64 chars."/>

 				<div class="buttondiv"><button type="submit" class="btn btn-default">Submit</button></div>
    			</form:form>
    		</div>
    	</div>
	
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
