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
			<div class="title">Register to use Climb</div>
			<div class="panel-group">
    				<div class="panel panel-default">
      				<div class="panel-heading">
       					<h4 class="panel-title">
          					<a data-toggle="collapse" href="#uploadinfo"><span class="green">&#9432;</span></a>
        					</h4>
      				</div>
      				<div id="uploadinfo" class="panel-collapse collapse">
        					<div class="panel-body">
       	 					<div class="info">
								<ul>
									<li>Access to Climb requires login with a username and password.</li>
									<li>Please use your initials as the username.</li>
									<li>Your username/initials must be unique within the organisation, and match legacy records. If your initials match someone in the system, append an extra letter or character (max 4 chars)</li>
									<li>Please enter a secure password.</li>
									<li>At all stages your password is securely encrypted.</li>	
									<li>* = Required fields.</li>								
								</ul>
							</div>				
        					</div>
      				</div>
    				</div>
  			</div> 
			<form:form role="form" modelAttribute="registerModel" >
				<div class="form-group has-feedback">
					<tatari:inputField label="Username *" type="text" name="initials" placeholder="Enter unique initials"/>
				</div>
				<div class="form-group has-feedback">
					<tatari:inputField label="Password *" type="password" name="password1" placeholder="Enter password"/>
				</div>	
				<div class="form-group has-feedback">
					<tatari:inputField label="Retype password *" type="password" name="password2" placeholder="Retype password"/>
				</div>	     		
 				<div class="buttondiv"><button type="submit" class="btn btn-default">Submit</button></div>
    			</form:form>
    			<p class="error">${errmsg}</p>
			<p class="message">${msg}</p>
    		</div>
    	</div>
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
