<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title><fmt:message key="lms.knowledgeMaintenance"/></title>
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
					<i class="fa fa-building"></i><fmt:message key="lms.knowledgeMaintenance"/>
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
													<fmt:message key="lms.knowledgeName"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                	<input id="parentpoint" name="parentpoint" type="hidden" value="0"/>
                                                    <input id="pointname" name="pointname" class="form-control input-sm" type="text" style="width: 200px;"/>
                                                </td>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.questionCategory"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="categoryid" name="categoryid" class="form-control input-sm select2" type="text" style="width: 200px;"/>
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
					<div class="caption" id="typeCaption"><fmt:message key="lms.knowledgeList"/>
					</div>
					<div class="actions">
						<button type="button" id="returnBtn" class="btn btn-success" style="display: none">
							<i class="fa fa-arrow-left"></i> <fmt:message key="lms.return"/>
						</button>
						<button type="button" id="addBtn" class="btn btn-success">
							<i class="fa fa-plus"></i> <fmt:message key="lms.add"/>
						</button>
						<button type="button" id="editBtn" class="btn btn-success">
							<i class="fa fa-edit"></i> <fmt:message key="lms.edit"/>
						</button>
						<button type="button" id="removeBtn" class="btn btn-success">
							<i class="fa fa-trash-o"></i> <fmt:message key="lms.delete"/>
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
											<th width="5%"><input type="checkbox" class="group-checkable" id="checkAll"></th>
											<th width="15%"><fmt:message key="lms.knowledgeName"/></th>
											<th width="15%"><fmt:message key="lms.questionCategory"/></th>
											<th width="60%"><fmt:message key="lms.description"/></th>
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
<script src="${pageContext.request.contextPath}/js/manager/question/knowledgepointList.js?version=${version}"></script>
<script>
//知识点新增
var fmtKnowledgeAdd = '<fmt:message key="lms.knowledgeAdd"/>';
//知识点分类新增
var fmtKnowledgeSecondLevelAdd = '<fmt:message key="lms.knowledgeSecondLevelAdd"/>';
//知识点编辑
var fmtKnowledgeEdit = '<fmt:message key="lms.knowledgeEdit"/>';
//知识点分类编辑
var fmtKnowledgeSecondLevelEdit = '<fmt:message key="lms.knowledgeSecondLevelEdit"/>';
//知识点列表
var fmtKnowledgeList = '<fmt:message key="lms.knowledgeList"/>';
//试题分类
var fmtQuestionCategory = '<fmt:message key="lms.questionCategory"/>';
//知识点名称
var fmtKnowledgeName = '<fmt:message key="lms.knowledgeName"/>';
//不能为空
var fmtIsNotNull = "<fmt:message key='lms.isNotNull'/>";
//请选择
var fmtPleaseSelect = '<fmt:message key="lms.pleaseSelect"/>';
</script>
</sitemesh:script>
</html>