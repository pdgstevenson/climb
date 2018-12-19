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
		
			<div class="title">New ${wall.room.name} Wall Details</div>
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
			<form:form role="form" modelAttribute="wall" >

					<tatari:inputField label="Name " type="text" name="name" placeholder="Enter wall name, max 32 chars."/>
					<tatari:inputField label="Number *" type="text" name="num" placeholder="Enter wall order."/>
					<tatari:inputField label="Orientation *" type="text" name="orientation" placeholder="Enter orientation."/>
					<tatari:inputField label="Width Base *" type="text" name="widthBase" placeholder="Enter wall order."/>
					<tatari:inputField label="Width Top *" type="text" name="widthTop" placeholder="Enter wall order."/>
					<tatari:inputField label="Height Left *" type="text" name="heightLeft" placeholder="Enter wall order."/>
					<tatari:inputField label="Height Right *" type="text" name="heightRight" placeholder="Enter wall order."/>
					<tatari:inputField label="Z Left" type="text" name="zLeft" placeholder="Enter wall order."/>
					<tatari:inputField label="Z Right" type="text" name="zRight" placeholder="Enter wall order."/>
 				<div class="buttondiv"><button type="submit" class="btn btn-default">Submit</button></div>
    			</form:form>
    		</div>
    	</div>
	
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
