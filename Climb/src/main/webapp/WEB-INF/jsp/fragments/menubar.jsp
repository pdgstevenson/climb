<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-inverse">

	<div class="container-fluid">
	<security:authentication var="user" property="principal" />
    		<ul class="nav navbar-nav">
      		<li><a href="<c:url value="/home" />">Climb</a></li>  
      		<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Centre<span class="caret"></span></a>
        			<ul class="dropdown-menu">
        				<li><a href="<c:url value="/centre/new" />">Add New Centre</a></li>          				  			
        			</ul>
      		</li> 

      		<li><a href="<c:url value="/login" />"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>

      		<li><a href="<c:url value="/login/logout" />">Logout</a></li> 
      		<li><security:authorize access="isAuthenticated()">
    				<a><span class="glyphicon glyphicon-user"></span>&nbsp;${user.username}</a> 
				</security:authorize></li>

      		      
    		</ul>

  	</div>
</nav>