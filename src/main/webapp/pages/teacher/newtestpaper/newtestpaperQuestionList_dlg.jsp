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
		#listQuestionTable{  
		    width:100px;  
		    table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */  
		}  
		#listQuestionTable td{  
		    width:100%;  
		    word-break:keep-all;/* 不换行 */  
		    white-space:nowrap;/* 不换行 */  
		    overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */  
		    text-overflow:ellipsis;/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用*/  
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
									<input id="typeid" name="typeid" type="hidden"/>
									<input id="paperid" name="paperid" type="hidden"/>
									<input id="flowstatus" name="flowstatus" type="hidden" value="2"/>
									<table class="table">
										<tbody>
											<tr>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.questionContent"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="content" name="content" class="form-control input-sm select2" type="text" style="width: 180px;"/>
                                                </td>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.questionCategory"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="pointsid" name="pointsid" class="form-control input-sm select2" type="text" style="width: 180px;"/>
                                                </td>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.questionDifficulty"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="difficulty" name="difficulty" class="form-control input-sm select2" type="text" style="width: 180px;"/>
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
					<div class="caption" id="typeCaption"><fmt:message key="lms.questionInfoList"/>
					</div>
					<div class="actions">
						<button type="button" id="selectBtn" class="btn btn-success">
							<i class="fa fa-plus"></i> <fmt:message key="lms.confirmSelect"/>
						</button>
					</div>
				</div>
				<div class="portlet-body myform nopadding">
					<div class="portlet-body">
						<div class="table-responsive">
							<div class="tabbable-custom ">
								<table id="listQuestionTable" class="table table-hover">
									<thead>
										<tr>
											<th width="5%"><input type="checkbox" class="group-checkable" id="checkAll"></th>
											<th width="60%"><fmt:message key="lms.questionContent"/></th>
											<th width="20%"><fmt:message key="lms.questionCategory"/></th>
											<th width="10%"><fmt:message key="lms.knowledge"/></th>
											<th width="15%"><fmt:message key="lms.questionDifficulty"/></th>
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
<script src="${pageContext.request.contextPath}/js/teacher/newtestpaper/newtestpaperQuestionList_dlg.js?version=${version}"></script>
<script type="text/javascript">
//选中的试题
var checkMap = {};
//获取父页面对象
var winPar = window.parent;
//请选择
var fmtPleaseSelect = '<fmt:message key="lms.pleaseSelect"/>';
</script>

</sitemesh:script>
</html>