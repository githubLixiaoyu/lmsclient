<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title><fmt:message key="lms.discussionMaintenance"/></title>
	<style type="text/css">
		#searchForm td {
			white-space:nowrap; 
		}
		tbody td{
			padding-left: 5px !important;
		}
		
		#listTable{  
		    width:100px;  
		    table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */  
		}  
		#listTable td{  
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
					<i class="fa fa-building"></i><fmt:message key="lms.discussionMaintenance"/>
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
													<fmt:message key="lms.discussionTheme"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="subject" name="subject" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
                                                <td class="formlable" width="8.5%">
													<fmt:message key="lms.discussionContent"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="body" name="body" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
 												<td class="formlable" width="8.5%">
													<fmt:message key="lms.discussionType"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="type" name="type" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
 											</tr>
 											<tr>
 												<td class="formlable" width="8.5%">
													<fmt:message key="lms.dataStatus"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="enableFlag" name="enableFlag" class="form-control input-sm select2" type="text" style="width: 200px;"/>
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
					<div class="caption" id="typeCaption"><fmt:message key="lms.discussionList"/>
					</div>
					<div class="actions">
						<button type="button" id="removeBtn" class="btn btn-success">
							<i class="fa fa-trash-o"></i> <fmt:message key="lms.delete"/>
						</button>
						<button type="button" id="viewCommentBtn" class="btn btn-success">
							<i class="fa fa-upload"></i> <fmt:message key="lms.viewComment"/>
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
											<th width="15%"><fmt:message key="lms.discussionTheme"/></th>
											<th width="42%"><fmt:message key="lms.discussionContent"/></th>
											<th width="5%"><fmt:message key="lms.discussionType"/></th>
											<th width="8%"><fmt:message key="lms.lookNum"/></th>
											<th width="8%"><fmt:message key="lms.replyNum"/></th>
											<th width="12%"><fmt:message key="lms.createTime"/></th>
											<th width="5%"><fmt:message key="lms.dataStatus"/></th>
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
<script src="${pageContext.request.contextPath}/js/manager/bbs/bbsPostList.js?version=${version}"></script>
<script>
//状态 正常
var fmtDataStatus0 = '<fmt:message key="lms.dataStatus0"/>';
//状态 已删除
var fmtDataStatus1 = '<fmt:message key="lms.dataStatus1"/>';
//详细信息
var fmtDetailInfo = '<fmt:message key="lms.detailInfo"/>';
//请选择
var fmtPleaseSelect = '<fmt:message key="lms.pleaseSelect"/>';
</script>
</sitemesh:script>
</html>