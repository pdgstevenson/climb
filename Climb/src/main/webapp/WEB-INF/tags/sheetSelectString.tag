<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of corresponding property in bean object" %>
<%@ attribute name="names" required="true" rtexprvalue="true" type="java.util.List"
              description="Names in the list" %>
<%@ attribute name="showPleaseSelect" required="true" rtexprvalue="true"
              description="Show Please Select Option" %>
<%@ attribute name="disabled" required="false" rtexprvalue="true"
              description="disable Select Option" %>
<spring:bind path="${name}">
    <c:set var="cssGroup" value="form-group ${status.error ? 'error' : '' }"/>
    <c:set var="valid" value="${not status.error and not empty status.actualValue}"/>    
    <div class="${cssGroup}" id="nomargin">  	
    	<form:select class="form-control" path="${name}" disabled="${disabled}">
     		<c:if test="${showPleaseSelect}"><form:option value=" ">&nbsp;</form:option></c:if>
     		<form:options items="${names}" />
		</form:select>    	
    	<c:if test="${valid}">
        	<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
       	</c:if>
      	<c:if test="${status.error}">
        	<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
            <span class="help-inline">${status.errorMessage}</span>
       	</c:if>
    </div>
</spring:bind>

