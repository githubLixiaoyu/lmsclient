<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="i18n/msg" />
<!DOCTYPE html>
<html lang="en">
<head>
<title><fmt:message key="lms.testpaperInfo" /></title>
<style type="text/css">
table label {
	float: right;
	margin-bottom: 0px;
}

td {
	padding: 5px;
}
</style>

</head>
<body>
	<div class="modal fade" id="portlet-upload-dlg" tabindex="-1"
		role="basic" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">Modal Title</h4>
				</div>
				<div class="modal-body">Modal body goes here</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<div>
		<div class="col-md-12" style="overflow-y: auto; overflow-x: hidden;">

			<div class="portlet box blue-hoki nomargin">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-bookmark"></i>&nbsp;
						<fmt:message key="lms.fillFollowing" />
					</div>
					<div class="actions">
						<table>
							<tr>
								<td><fmt:message key="lms.totalScore" />：</td>
								<td><input id="aggregateScore" name="aggregateScore"
									class="form-control" type="text"  maxlength="20"></td>
								<td>
									<button type="button" id="avgdistribution"
										class="btn btn-success">

										<fmt:message key="lms.avgdistribution" />
									</button>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="portlet-body myform nopadding">
					<div class="portlet-body">
						<div class="table-responsive">
							<div class="tabbable-custom ">
								<table id="listTable" class="table">
									<thead>
										<tr>
											<th width="20%"><fmt:message key="lms.titleCourse" /></th>
											<th width="40%"><input type="checkbox"
												class="group-checkable" id="checkAll"> <fmt:message
													key="lms.knowledge" /></th>
											<th width="10%"><fmt:message key="lms.score" /></th>
											<th width="10%"><fmt:message key="lms.orderNum" /></th>
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
			<input type="hidden" id="saveBtn" class="btn btn-success"
				style="margin-top: 15px;" value="<fmt:message key="lms.save"/>">
			<button id="returnBut" type="button" class="btn default btn-sm"
				data-dismiss="modal" style="margin-top: 15px;">
				<fmt:message key="lms.return" />
			</button>
		</div>
	</div>
</body>
<sitemesh:script>
	<script
		src="${pageContext.request.contextPath}/js/teacher/vrnewtestpaper/newtestpaperoperationEdit.js?version=${version}"></script>
	<script>
		var paperId =<%=request.getParameter("paperId")%>;
		//不能为空
		var fmtIsNotNull = "<fmt:message key='lms.isNotNull'/>";
		//总分
		var fmtSumScore = '<fmt:message key="lms.totalScore"/>';
		//应是正整数
		var fmtSignlessInteger = '<fmt:message key="lms.signlessInteger"/>';
		//分数
		var fmtScore = '<fmt:message key="lms.score"/>';
		//排序
		var fmtSequence = '<fmt:message key="lms.orderNum"/>';
		//排序存在相同的顺序
		var fmtSequenceEqual = '<fmt:message key="lms.sequenceEqual"/>';
		//总分数与实际分配分数不等
		var fmtUnequalSumScore = '<fmt:message key="lms.unequalSumScore"/>';
		//选择需要保存的知识点
		var fmtCheckError = '<fmt:message key="lms.checkError"/>';
		
	</script>
</sitemesh:script>
</html>