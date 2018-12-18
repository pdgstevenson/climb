<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of corresponding property in bean object" %>
<%@ attribute name="label" required="true" rtexprvalue="true"
              description="Label appears in red color if input is considered as invalid after submission" %>
<%@ attribute name="type" required="false" rtexprvalue="true"
              description="Type of input field" %>
<%@ attribute name="placeholder" required="false" rtexprvalue="true"
              description="Optional placeholder text" %>
<%@ attribute name="min" required="false" rtexprvalue="true"
              description="Sets min number on number fields" %>
<%@ attribute name="max" required="false" rtexprvalue="true"
              description="Sets max number on number fields" %>
<%@ attribute name="disabled" required="false" rtexprvalue="true"
              description="Sets greyed out fields" %>
              
<spring:bind path="${name}">
    <div class="form-group ${status.error ? 'has-error' : ''}">
    		<label>${label}</label>
       	<form:input class="form-control" type="${type}" path="${name}" min="${min}" max="${max}" placeholder="${placeholder}" disabled="${disabled}"/>
		<form:errors path="${name}" />
     </div>
</spring:bind>
