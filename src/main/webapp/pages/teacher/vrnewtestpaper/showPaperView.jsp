<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
<title><fmt:message key="lms.testpaperPreview"/></title>
<style type="text/css">
p {
	display: inline;
}
#ExamName{
	background-color: #ffffff;
    color: #ff0000;
    font-family: 黑体;
    font-size: 20pt;
    font-weight: bold;
    height: 60px;
    line-height: 60px;
    margin: 0 auto;
    text-align: center;
    width: 100%;
}
.QBody{
    color: #000000;
    height: auto;
    line-height: 24px;
    margin: 5px 0;
    padding: 10px 15px;
}
.QName{
	border-bottom: 1px dashed #c9c9c9;
    height: auto;
    line-height: 30px;
}
.QOption{
	padding-left:10px;
    line-height: 30px;
}
</style>
</head>
<body>
	<div id="body">
	</div>
	<div class="col-md-12" style="overflow-y: auto; overflow-x: hidden;">
		<div class="portlet box blue-hoki nomargin">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-bookmark"></i>&nbsp;
					<fmt:message key="lms.fillFollowing" />
				</div>
				<div class="actions">
				</div>
			</div>
			<div class="portlet-body myform nopadding">
				<div class="portlet-body">
					<div class="table-responsive">
						<div class="tabbable-custom ">
							<table id="listTable" class="table">
								<thead>
									<tr>
										<th width="20%"><fmt:message key="lms.course" /></th>
										<th width="40%"><fmt:message key="lms.knowledge" /></th>
										<th width="10%"><fmt:message key="lms.score" /></th>
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
</body>
<sitemesh:script>
<script type="text/javascript">
var mileStoneId = <%=request.getParameter("id")%>;
var mileStoneNameTitle = <%=request.getParameter("mileStoneName")%>;
//第几题
var fmtQuestionNo = '<fmt:message key="lms.questionNo"/>';
//分
var fmtQuestionScore = '<fmt:message key="lms.questionScore"/>';
</script>
<script src="${pageContext.request.contextPath}/js/teacher/vrnewtestpaper/showPaperView.js?version=${version}"></script>
</sitemesh:script>
</html>
