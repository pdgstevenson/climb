<!DOCTYPE html>
<%@ page session="false" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tatari" tagdir="/WEB-INF/tags" %>
<html lang="en">
<jsp:include page="../fragments/htmlHeader.jsp"/>



<body>
<SCRIPT>
function scheduleDateChange(event) {
	if(this.options[this.selectedIndex].text != "Please Select") {
    		$('.samplestatus').prop('value', this.options[this.selectedIndex].text);
    	}
}
    function report(id1,id2) {

		if($(id1).val() == " ") {
        		$(id2).val(null);
        	} else {
        		$(id2).val('${today}');
        	}
    }
</SCRIPT>
<jsp:include page="../fragments/menubar.jsp"/>
<security:authentication var="user" property="principal" />
	<div class="container">
		<div class="st_formframe">
		
			<div class="title">New ${disorder} Batch Details</div>
			<div class="panel-group">
    				<div class="panel panel-default">
      				<div class="panel-heading">
       					<h4 class="panel-title">
          					<a data-toggle="collapse" href="#addbatchinfo"><span class="green">&#9432;</span></a>
        					</h4>
      				</div>
      				<div id="addbatchinfo" class="panel-collapse collapse">
        					<div class="panel-body">
							<div class="info">
								<ul>
									<li>Specify the VarSeq Id (usually a 5-digit identifier), and both the date and type of the sequencing run.</li>
									<c:if test="${batchtype.equals('single')}">
										<li>You have created a single disorder batch.Please select the single disorder associated with every sample in the batch.</li>
									</c:if>
									<c:if test="${batchtype.equals('mixed')}">
										<li>You have created a mixed disorder batch. Please state which disorder is associated with each batch number.</li>
									</c:if>
									<li>Clicking submit will trigger the upload of all files associated with the new batch.</li>
									<li>Ensure the overall coverage file and all coverage and variant files are in the directory ${basedir}</li>
									<li>Take care to enter data correctly as it is not trivial to undo these data.</li>
									<li>All fields are required.</li>
								</ul>
							</div> 				
        					</div>
      				</div>
    				</div>
  			</div>			
			<form:form role="form" modelAttribute="batch" >

					<tatari:inputField label="VarSeq Id *" type="text" name="varSeqId" placeholder="Enter VarSeq Id, e.g. 23726"/>
					<tatari:inputField label="Sequence Run Date *" type="date" name="sequenceRunDate" />
					<tatari:select label="Sequence Type *" name="sequencingType" names="${sequenceTypeOptions}" itemValue="type" itemLabel="type" showPleaseSelect="true"/>
					
				<c:if test="${batchtype.equals('single')}">
  					<tatari:select label="Disorder *" name="disorder" names="${disorders}" itemValue="name" itemLabel="name" showPleaseSelect="true"/>
				</c:if>
				<c:if test="${batchtype.equals('mixed')}">
   				<table class="data">
   					<tr>
   						<th>Batch Number</th>
   						<th>Disorder</th> 						
   					</tr>
        				<c:forEach items="${batch.batchDisorders}" var="disorder"  varStatus="status">
        					<tr>
							<td>${status.index + 1}</td>
							<td><tatari:sheetSelectField  name="batchDisorders[${status.index}]" names="${disorders}" itemValue="name" itemLabel="name" showPleaseSelect="true" /></td>
   						</tr> 
					</c:forEach>
				</table>	
				</c:if>
 				<div class="buttondiv"><button type="submit" class="btn btn-default">Submit</button></div>
    			</form:form>
    		</div>
    	</div>
	
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
