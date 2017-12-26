<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title><fmt:message key="lms.testResult"/></title>
	<style type="text/css">
		#searchForm td {
			white-space:nowrap; 
		}
		tbody td{
			padding-left: 5px !important;
		}
		#searchForm ul{
			list-style:none;
		    margin: 0px;
		    padding: 0px;
		    width: auto;
		}
		#searchForm ul li{
 			float: left;
			margin-left: 20px;
/* 			margin-bottom: 10px; */
			height: 33px;
			line-height: 30px;
/* 			border:1px solid red; */
		}
		#searchForm div{
			height: 18px;
		}
		.paramName{
			float: left;
			line-height: 20px;
			height: 30px;
		}
		.paramName label{
			font-weight: bold;
    		line-height: 20px;
    		padding: 5px;
    		text-align: right;
		}
		.paramField{
			float: left;
			margin-left: 10px;
			height: 30px;padding: 3px;
		}
		.paramField input{
			width: 180px;
		}
	</style>
</head>
<body>
<div class="modal fade" id="portlet-upload-dlg" tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">Modal Title</h4>
			</div>
			<div class="modal-body">
				 Modal body goes here
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue-hoki nomargin">
				<div class="portlet-title">
					<div class="caption">
					<i class="fa fa-building"></i><fmt:message key="lms.testResult"/>
					</div>
				</div>
				<div class="portlet-body myform">
					<div id="searchDiv">
						<form id="searchForm" action="#">
							<input id="testInstanceRequestId" name="testInstanceRequestId" type="hidden" value="<%=request.getParameter("id")%>"/>
							<div>
								<ul>
									<li>
										<div class="paramName"><label><fmt:message key="lms.loginCode"/></label></div>
										<div class="paramField"><input id="logincode" name="logincode" class="form-control input-sm select2" type="text"/></div>
									</li>
									<li>
										<div class="paramName"><label><fmt:message key="lms.name"/></label></div>
										<div class="paramField"><input id="nickname" name="nickname" class="form-control input-sm select2" type="text"/></div>
									</li>
									<li>
										<div class="paramName"><label><fmt:message key="lms.depart"/></label></div>
										<div class="paramField"><input id="departid" name="departid" class="form-control input-sm select2" type="text" style="width: 180px;"/></div>
									</li>
									<li style="float: right;">
										<div>
											<span class="input-group-btn btn-sm">
												<button id="searchBtn" class="btn green-haze" type="button" style="height: 30px;top: 1px;font-size: 10pt;" ><i class="glyphicon glyphicon-search"></i> <fmt:message key="lms.search"/> </button>
											</span>
										</div>
									</li>
								</ul>
							
							</div>
							<div style="clear: both;height: 0px;"></div> 
	 					</form>
					</div>
				</div>
			</div>
			<div class="portlet box blue-hoki nomargin ">
				<div class="portlet-title">
					<div class="caption" id="typeCaption"><fmt:message key="lms.testResultList"/>
					</div>
					<div class="actions">
						<button type="button" id="returnBtn" class="btn btn-success">
							<i class="fa fa-arrow-left"></i> <fmt:message key="lms.return"/>
						</button>
					</div>
				</div>
				<div class="portlet-body myform nopadding">
					<div class="portlet-body">
						<div class="table-responsive">
							<div class="tabbable-custom ">
								<table id="listTable" class="table table-hover">
									<thead>
										<tr>
											<th width="15%"><fmt:message key="lms.loginCode"/></th>
											<th width="10%"><fmt:message key="lms.name"/></th>
											<th width="10%"><fmt:message key="lms.depart"/></th>
											<th width="10%"><fmt:message key="lms.testStatus"/></th>
											<th width="10%"><fmt:message key="lms.score"/></th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
<sitemesh:script>
<script src="${pageContext.request.contextPath}/js/teacher/vrnewexam/examResultList.js?version=${version}"></script>
<script>
//请选择
var fmtPleaseSelect = '<fmt:message key="lms.pleaseSelect"/>';
</script>
</sitemesh:script>
</html>