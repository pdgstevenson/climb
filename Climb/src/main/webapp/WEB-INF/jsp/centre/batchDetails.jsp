<!DOCTYPE html>
<%@ page session="false" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tatari" tagdir="/WEB-INF/tags" %>
<html lang="en">
<jsp:include page="../fragments/htmlHeader.jsp"/>
<body>
<script>
$(document).ready(function() {
	$('[data-toggle="toggle"]').change(function(){
		$(this).parents().next('.hide').toggle();
	});
});
</script>
<SCRIPT>

	function scheduleGlobalCheckboxChange(event) {
	
    		$('.customgap').prop('checked', this.checked); 
    		$('.routinegap').prop('checked', this.checked);      	
	}
	
    function report(id1,id2) {

		if($(id1).val() == " ") {
        		$(id2).val(null);
        	} else {
        		$(id2).val('${today}');
        	}
    }
    function disableFormButtons() {
            //disable the submit button
            $("#sampleBtnSubmit").attr("disabled", true);
            $("#confirmationBtnSubmit").attr("disabled", true);
            $("#gapBtnSubmit").attr("disabled", true);
	}
	
	function toggle(source) {
    		var checkboxes = document.getElementsByName('routineGaps[].filled');
    		alert("Got " + checkboxes.length + " checkboxes");
    		for (var i = 0; i < checkboxes.length; i++) {
        		if (checkboxes[i] != source) checkboxes[i].checked = source.checked;
    		}
	}
</SCRIPT>
 <jsp:include page="../fragments/menubar.jsp"/>
	<security:authentication var="user" property="principal" />
	<div class="container-fluid">
	<c:if test="${batch!=null}">	
		<div class="st_formframe">
			<div class="whowheninfo"><span class="glyphicon glyphicon-user"></span> ${user.username} <span class="glyphicon glyphicon-comment"></span> ${batch.summary}</div>
			<div class="title">
				${batch.varSeqId} details				
			</div>	
								

  				<div class="panel-group">
    					<div class="panel panel-default">
      					<div class="samplesummary1">
       						<h4 class="panel-title">
          						Samples QC Pass&nbsp;<a data-toggle="collapse" href="#passes"><span class="glyphicon glyphicon-collapse-down"></span></a>
          						<c:if test="${!batch.repeats.isEmpty()}">
          							&nbsp;<span title="Download Exports"><a href='<spring:url value="/batch/download/exports/${batch.varSeqId}" htmlEscape="true"/>' onclick="disableFormButtons();"><span class="glyphicon glyphicon-save-file"></span></a></span>
        							</c:if>
        						</h4>
      					</div>
      					<div id="passes" class="panel-collapse collapse in">	      						
      						<table class="overallCoverage">
      							<tr class="overallCoveragePass">
      								<th>Sample</th>
      								<th>Disorder</th>
      								<th>Sample % 30x</th>
      		 						<th>Result</th>
      		 						<th>Reclassification</th>
      								<th>Gaps Filled</th>   	
      								<th>Confirmed</th>	
      								<th>Ready to discuss</th>
      								<th>Discussed</th>
      								<th>For MDT</th>
      								<th>Ready to export</th>
      								<th>Exported</th>
      							</tr>
								<c:forEach var="sample" items="${batch.samples}" >									
									<tr class="overallCoveragePass">
										<td>${sample.longIdentifier}&nbsp;<c:if test="${!sample.failed}"><a href='<spring:url value="/sample/${sample.sampleId}" htmlEscape="true"/>'><span class="glyphicon glyphicon-eye-open"></span></a></c:if></td>										
										<td>${sample.disorder.name}</td>
										<td>${sample.overallCoverage}</td>
										<td>	${sample.result}</td>
										<td><c:if test="${sample.hasPendingReclassifications()}"><span class="red">&#x2757;</span></c:if></td>
										<td>
											<c:if test="${sample.hasAnyGaps()}">
												<c:if test="${sample.getFillsComplete()}"><span class="green">&#x2714;</span></c:if>
												<c:if test="${!sample.getFillsComplete()}"><span class="red">&#x2757;</span></c:if>
											</c:if>
											<c:if test="${!sample.hasAnyGaps()}">
												n/a
											</c:if>
										</td>
										<td>
											<c:if test="${!sample.negative}">
												<c:if test="${!sample.hasUnconfirmedSignificantVariants()}"><span class="green">&#x2714;</span></c:if>
												<c:if test="${sample.hasUnconfirmedSignificantVariants()}"><span class="red">&#x2757;</span></c:if>
											</c:if>
											<c:if test="${sample.negative}">
												n/a
											</c:if>
										</td>
										<td>
											<c:if test="${!sample.negative}">
												<c:if test="${sample.readyToDiscuss}"><span class="green">&#x2714;</span></c:if>
												<c:if test="${!sample.readyToDiscuss}"><span class="red">&#x2757;</span></c:if>
											</c:if>
											<c:if test="${sample.negative}">
												n/a
											</c:if>
										
										</td>

										<td>	<c:if test="${sample.discussedAtMdt && !sample.exported}"><span class="green">&#x2714;</span></c:if></td>
										<td>	<c:if test="${sample.forMdt}"><span class="green">&#x2714;</span></c:if></td>	
										<td>	<c:if test="${sample.readyToExport && !sample.exported}"><span class="green">&#x2714;</span></c:if></td>
										<td>	<c:if test="${sample.exported}"><span class="green">&#x2714;</span> ${sample.exportFile}</c:if></td>		
									</tr>			
								</c:forEach>	
				
        						</table>
      					</div>
    					</div>
  				</div>	
  				<div class="panel-group">
    					<div class="panel panel-default">
      					<div class="samplesummary1">
       						<h4 class="panel-title">
          						Variants&nbsp;<a data-toggle="collapse" href="#variants"><span class="glyphicon glyphicon-collapse-down"></span></a>&nbsp;<span title="Download Confirmations"><a href='<spring:url value="/batch/download/confirmations/${batch.varSeqId}" htmlEscape="true"/>' onclick="disableFormButtons();"><span class="glyphicon glyphicon-save-file"></span></a></span>
        						</h4>
      					</div>
      					<div id="variants" class="panel-collapse collapse in">	
       						<table class="variant">
       							<tr class="Pending_significant">  
       								<th>Sample</th>
									<th>Chr:Pos</th>
									<th>Exon Number (Clinically Relevant)</th>
									<th>Gene</th>
									<th>Variant</th>
									<th>Classification</th>									
									<th>Primer?</th>
									<th>Genbank File?</th>								
									<th>Confirmation Scheduled By</th>
									<th>Confirmation Status</th>
									<th>Confirmed Result By</th>
									<th></th>
								</tr>
								<form:form role="form" id="confirmationstatusform" modelAttribute="batch" action="/SamVar/batch/confirmations/${batch.varSeqId}">
										<c:forEach var="variant" items="${batch.significantVariants}" varStatus="variantStatus">	
											<tr class="Pending_significant">											
												<td>${variant.sample.longIdentifier}&nbsp;<a href='<spring:url value="/sample/${variant.sample.sampleId}#variant${variant.sampleVariantId}" htmlEscape="true"/>'><span class="glyphicon glyphicon-eye-open"></span></a></td>
												<td><a href='<spring:url value="genomebrowse:/api/zoom?locus=${variant.chromosomePosition}" htmlEscape="true"/>'>${variant.chromosomePosition}</a></td>
												<td>${variant.exonNumber}</td>
												<td>${variant.gene.name}</td>
												<td>${variant.dnaVariant.currentHgvsc}&nbsp;/&nbsp;${variant.dnaVariant.proteinVariant.currentHgvsp}</td>
												<td>${variant.variantDatabaseClassification.classificationString}</td>
												<td>
													<tatari:sheetSelectField name="significantVariants[${variantStatus.index}].confirmation.primer" names="${primers}" itemValue="name" itemLabel="name" showPleaseSelect="true" disabled="${variant.confirmation.confirmationHasBeenScheduled || variant.sample.exported}"/>
												</td>
												<td>							
													<tatari:sheetSelectField name="significantVariants[${variantStatus.index}].confirmation.genbankFile" names="${genbankFiles}" itemValue="name" itemLabel="name" showPleaseSelect="true" disabled="${variant.confirmation.confirmationHasBeenScheduled || variant.sample.exported}"/>
				       							</td>
												<td>${variant.confirmation.confirmationScheduledString}</td>	
												<td><tatari:sheetSelectField name="significantVariants[${variantStatus.index}].confirmation.confirmationResult" names="${confirmationStatusOptions}" itemValue="name" itemLabel="name" showPleaseSelect="false" disabled="${!variant.confirmation.confirmationHasBeenScheduled || variant.sample.exported}"/></td>											
												<td>${variant.confirmation.confirmationReceivedString}</td>
												<td><c:if test="${variant.confirmation.complete}"><span class="green">&#x2714;</span></c:if><c:if test="${!variant.confirmation.complete}"><span class="red">&#x2757;</span></c:if></td>
												<form:input type="hidden" path="significantVariants[${variantStatus.index}]"/>
											</tr>
										</c:forEach>						
									<tr class="Pending_significant">
										<td colspan="13"><button type="submit" id="confirmationBtnSubmit" class="btn btn-default">Submit</button></td>
									</tr>
								</form:form>
							</table>
      					</div>
    					</div>
  				</div>	
  				<c:if test="${batch.hasAnyGaps}">			
				<div class="panel-group">
    					<div class="panel panel-default">
      					<div class="failedsamplesummary">
       						<h4 class="panel-title">
          						Gaps&nbsp;<a data-toggle="collapse" href="#gaps"><span class="glyphicon glyphicon-collapse-down"></span></a>&nbsp;<span title="Download Gap Fills"><a href='<spring:url value="/batch/download/gapfills/${batch.varSeqId}" htmlEscape="true"/>' onclick="disableFormButtons();"><span class="glyphicon glyphicon-save-file"></span></a></span>
        						</h4>
      					</div>
      					<div id="gaps" class="panel-collapse collapse in">
      						<table class="coverage">
      							<tr class="gaps">      								
       								<th>Sample</th>
       								<th>Disorder</th>
       								<th>Region</th>
       								<th>Type</th>
       								<th>Min depth</th>
      								<th>% 30x</th>
      								<th>Fill Scheduled</th>
      								<th>Fill Completed <c:if test="${batch.hasFillingScheduled}"><input type="checkbox" onClick="scheduleGlobalCheckboxChange.call(this, event)" /></c:if></th>
      								<th></th>
      		     				</tr>
      							<form:form role="form" modelAttribute="batch" action="/SamVar/batch/gaps/${batch.varSeqId}">
								<c:forEach var="customGap" items="${batch.customGaps}" varStatus="customGapStatus">
									<tr class="customgaps">
										<td>${customGap.sample.longIdentifier}&nbsp;<a href='<spring:url value="/sample/${customGap.sample.sampleId}" htmlEscape="true"/>'><span class="glyphicon glyphicon-eye-open"></span></a></td>
										<td>${customGap.sample.disorder.name}</td>
										<td>${customGap.region.regionName}</td>
										<td>Custom</td>										
										<td>${customGap.minDepth}</td>
										<td>${customGap.thirtyXPercentage}</td>
										<td>${customGap.fillScheduledInitialsAndDate}</td>
										<td>
											<c:if test="${customGap.scheduledAndNotFilled}">
												<form:checkbox class="customgap" path="customGaps[${customGapStatus.index}].filled" />
											</c:if>
											<c:if test="${customGap.filled}">
												${customGap.fillCompleteInitialsAndDate}
											</c:if>
										</td>					
										<td><c:if test="${customGap.filled}"><span class="green">&#x2714;</span></c:if><c:if test="${!customGap.filled}"><span class="red">&#x2757;</span></c:if></td>
									</tr>
								</c:forEach>	
								<c:forEach var="routineGap" items="${batch.routineGaps}" varStatus="routineGapStatus">
									<tr class="routinegaps">
										<td>${routineGap.sample.longIdentifier}&nbsp;<a href='<spring:url value="/sample/${routineGap.sample.sampleId}" htmlEscape="true"/>'><span class="glyphicon glyphicon-eye-open"></span></a></td>
										<td>${routineGap.sample.disorder.name}</td>
										<td>${routineGap.region.regionName}</td>
										<td>Routine</td>										
										<td></td>
										<td></td>
										<td>${routineGap.fillScheduledInitialsAndDate}</td>
										<td>
											<c:if test="${routineGap.scheduledAndNotFilled}">
												<form:checkbox class="routinegap" path="routineGaps[${routineGapStatus.index}].filled" />
											</c:if>	
											<c:if test="${routineGap.filled}">																				
												${routineGap.fillCompleteInitialsAndDate}
											</c:if>	
										</td>	
										<td><c:if test="${routineGap.filled}"><span class="green">&#x2714;</span></c:if><c:if test="${!routineGap.filled}"><span class="red">&#x2757;</span></c:if></td>				
									</tr>
								</c:forEach>
									<tr class="gaps">
										<td colspan="9"><c:if test="${batch.hasFillingScheduled}"><button type="submit" id="gapBtnSubmit" class="btn btn-default">Submit</button></c:if></td>
									</tr>
								</form:form>
								</table>
							</div>        			
    					</div>
  				</div>	
  				</c:if>
  				<c:if test="${!batch.repeats.isEmpty()}">  								
				<div class="panel-group">
    					<div class="panel panel-default">
      					<div class="failedsamplesummary">
       						<h4 class="panel-title">
          						Samples QC Fail For Repeat&nbsp;<a data-toggle="collapse" href="#fails"><span class="glyphicon glyphicon-collapse-down"></span></a>
          						<c:if test="${!batch.repeats.isEmpty()}">
          							&nbsp;<span title="Download Repeats"><a href='<spring:url value="/batch/download/repeats/${batch.varSeqId}" htmlEscape="true"/>' onclick="disableFormButtons();"><span class="glyphicon glyphicon-save-file"></span></a></span>
        							</c:if>
        						</h4>
      					</div>
      					<div id="fails" class="panel-collapse collapse in">	      						
      						<table class="overallCoverage">
      							<tr class="overallCoverageFail">
      								<th>Sample</th>
      								<th>Disorder</th>
      								<th>Sample % 30x</th>
      								<th>Repeat Scheduled</th>
      								<th></th>
      							</tr>
								<c:forEach var="sample" items="${batch.repeats}" varStatus="overallStatus">									
									<tr class="overallCoverageFail">
										<td>${sample.longIdentifier}&nbsp;<c:if test="${!sample.failed}"><a href='<spring:url value="/sample/${sample.sampleId}" htmlEscape="true"/>'><span class="glyphicon glyphicon-eye-open"></span></a></c:if></td>										
										<td>${sample.disorder.name}</td>
										<td>${sample.overallCoverage}</td>
										<td>	${sample.summary}</td>
										<td><c:if test="${sample.repeatComplete}"><span class="green">&#x2714;</span></c:if><c:if test="${!sample.repeatComplete}"><span class="red">&#x2757;</span></c:if></td>
									</tr>			
								</c:forEach>			
        						</table>
      					</div>
    					</div>
  				</div>	
  				</c:if>
				<div class="panel-group">
    					<div class="panel panel-default">
      					<div class="samplesummary1">
       						<h4 class="panel-title">
          						Files&nbsp;<a data-toggle="collapse" href="#files"><span class="glyphicon glyphicon-collapse-down"></span></a>
        						</h4>
      					</div>
      					<div id="files" class="panel-collapse collapse">	      					
      						<table>
      						   	<tr>
      								<td>Overall</td>
      								<td>${batch.coverageFile}</td>
      								<td>${batch.coverageFileComments}</td>  
      								<td></td> 
      								<td></td>     				
      							</tr>
      							<tr>
      								<th>Sample</th>
      								<th>Variant File</th>
      								<th>Upload Comments</th>
      								<th>Coverage File</th>
      								<th>Upload Comments</th>
      							</tr>   
      							<c:forEach var="sample" items="${batch.samples}">	
      							 	<tr> 									
										<td>${sample.longIdentifier}&nbsp;<a href='<spring:url value="/sample/${sample.sampleId}" htmlEscape="true"/>'><span class="glyphicon glyphicon-eye-open"></span></a></td>
										<td>${sample.variantFile}</td>
										<td>${sample.variantFileComments}</td>
										<td>${sample.coverageFile}</td>
										<td>${sample.coverageFileComments}</td>
									</tr>
       	 						</c:forEach>
      						</table>	
      					</div>
    					</div>
  				</div>				

  			</div>	
  			</c:if>			
	  	</div>
 	   

<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
