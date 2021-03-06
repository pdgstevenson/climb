<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of corresponding property in bean object" %>
 <%@ attribute name="disabled" required="false" rtexprvalue="true"
              description="Sets greyed out fields" %> 
<%@ attribute name="idx" required="false" rtexprvalue="true"
              description="Sets index value" %>              
<spring:bind path="${name}">
    <c:set var="cssGroup" value="form-group ${status.error ? 'has-error' : '' }"/>
    <c:set var="valid" value="${not status.error and not empty status.actualValue}"/>
    <div class="${cssGroup}" id="nomargin">
       	<form:input class="form-control" type="date" path="${name}" id="datepicker${idx}" disabled="${disabled}"/>
            <c:if test="${valid}">
                <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
            </c:if>
            <c:if test="${status.error}">
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
            </c:if>
     </div>
</spring:bind>
