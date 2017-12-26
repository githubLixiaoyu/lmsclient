<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title></title>
	<style type="text/css">
		#searchForm td {
			white-space:nowrap; 
		}
		tbody td{
			padding-left: 5px !important;
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
					<i class="fa fa-building"></i><fmt:message key="lms.queryCondition"/>
					</div>
				</div>
				<div class="portlet-body myform">
					<div id="searchDiv">
						<form id="searchForm" action="#">
							<div class="form-body">
								<div class="table-responsive">
									<table class="table">
										<tbody>
											<tr>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.testpaperName"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="papername" name="papername" class="form-control" type="text" style="width: 180px;"/>
                                                    <input id="status" name="status" type="hidden" value="2"/>
                                                </td>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.testaperCategory"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="categoryid" name="categoryid" class="form-control input-sm select2" type="text" style="width: 180px;"/>
                                                </td>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.questionDifficulty"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="difficult" name="difficult" class="form-control input-sm select2" type="text" style="width: 180px;"/>
                                                </td>
                                                <td class="formbody" width="10.7%" colspan="5">
													<span class="input-group-btn btn-sm">
														<button id="searchBtn" class="btn green-haze" type="button" style="height: 30px;top: 1px;font-size: 10pt;" ><i class="glyphicon glyphicon-search"></i> <fmt:message key="lms.search"/> </button>
													</span>
												</td>
 											</tr>
										</tbody>
									</table>
								</div>
							</div>
	 					</form>
					</div>
				</div>
			</div>
			<div class="portlet box blue-hoki nomargin ">
				<div class="portlet-title">
					<div class="caption" id="typeCaption"><fmt:message key="lms.testpaperList"/>
					</div>
<!-- 					<div class="actions"> -->
<!-- 						<button type="button" id="returnBtn" class="btn btn-success" style="display: none"> -->
<!-- 							<i class="fa fa-arrow-left"></i> <fmt:message key="lms.return"/> -->
<!-- 						</button> -->
<!-- 						<button type="button" id="addBtn" class="btn btn-success"> -->
<!-- 							<i class="fa fa-plus"></i> <fmt:message key="lms.add"/> -->
<!-- 						</button> -->
<!-- 						<button type="button" id="editBtn" class="btn btn-success"> -->
<!-- 							<i class="fa fa-edit"></i> <fmt:message key="lms.edit"/> -->
<!-- 						</button> -->
<!-- 						<button type="button" id="removeBtn" class="btn btn-success"> -->
<!-- 							<i class="fa fa-trash-o"></i> <fmt:message key="lms.delete"/> -->
<!-- 						</button> -->
<!-- 						<button type="button" id="sendBtn" class="btn btn-success"> -->
<!-- 							<i class="fa fa-plus"></i> <fmt:message key="lms.publish"/> -->
<!-- 						</button> -->
<!-- 					</div> -->
				</div>
				<div class="portlet-body myform nopadding">
					<div class="portlet-body">
						<div class="table-responsive">
							<div class="tabbable-custom ">
								<table id="listTable" class="table table-hover">
									<thead>
										<tr>
											<th width="25%"><fmt:message key="lms.testpaperName"/></th>
											<th width="10%"><fmt:message key="lms.testaperCategory"/></th>
											<th width="10%"><fmt:message key="lms.questionDifficulty"/></th>
											<th width="10%"><fmt:message key="lms.testpaperTotalScore"/></th>
											<th width="10%"><fmt:message key="lms.operation"/></th>
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
		<div class="modal-footer" style="padding: 15px 32px 0px 15px;">
			<button id="cancelBtn" type="button" class="btn default btn-sm"
				data-dismiss="modal" style="margin-top: 15px;"><fmt:message key="lms.close"/></button>
		</div>
	</div>
	
</body>
<sitemesh:script>
<script src="${pageContext.request.contextPath}/js/teacher/newexam/paperList_dlg.js?version=${version}"></script>
<script>
	//获取父页面对象
	var winPar = window.parent;
	//请选择
	var fmtPleaseSelect = '<fmt:message key="lms.pleaseSelect"/>';
	//选择
	var fmtSelect = '<fmt:message key="lms.select"/>';
	//预览
	var fmtPreview = '<fmt:message key="lms.preview"/>';
</script>
</sitemesh:script>
</html>