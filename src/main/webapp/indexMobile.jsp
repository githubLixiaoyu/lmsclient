<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="lms.webTitle"/></title>

<link rel="stylesheet" type="text/css" href="css/index/main.css" />
<link rel="stylesheet" type="text/css" href="css/index/bgstretcher.css" />

<script type="text/javascript" src="js/index/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/index/bgstretcher.js"></script>

<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/tools.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/msg.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/dialog.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/ajax.js" type="text/javascript"></script>
<script type="text/javascript">
	var server = "${pageContext.request.contextPath}";
	$.ajax({
	     url: server + "/ui/tapp/getQrcode.do",
	     data: {},
	     dataType:"json",
	     type: "POST",
	     beforeSend:function(XMLHttpRequest){
	     	utils.tools.loading();
		 },
	     success: function(result){
	    	 utils.tools.unloading();
	    	 if (result.flag == 1){
	    			$("#android-qrcode").attr("src", server + "/../lmsFiles/qrcodeImages/" + result.data.android);
	    			$("#ios-qrcode").attr("src", server + "/../lmsFiles/qrcodeImages/" + result.data.ios);
	    			$("#android-qrcode-path").attr("href", result.data.androidPath);
	    			$("#ios-qrcode-path").attr("href", result.data.iosPath);
	    	 }
	     },
	     error:function(){
	    	 utils.tools.unloading();
	    	 utils.dialog.toastr("lms0003", "lms0002", "error");
	     }
	});
</script>
</head>
<body>
<style type="text/css">
body {
    background-image: url(${pageContext.request.contextPath}/images/app_bg.jpg);  
    background-size: cover;
}
.app-qrcode-div {
	width: 100%;
	margin-top: 100px;
	text-align: center;
}
.app-qrcode-div-body {
	width: 320px;
	height: 350px;
	margin: 0 auto;
	background-color: #FFF;
}
.app-qrcode-div-body-label {
	position: relative;
	top: 15px;
	left: 20px;
	font-size: 25px;
	color: #666666;
	text-align: left;
}
.app-qrcode-div-body-a {
	position: relative;
	top: -17px;
	font-size: 25px;
	text-decoration: none;
}
</style>
<div class="app-qrcode-div">
	<div class="app-qrcode-div-body">
		<div class="app-qrcode-div-body-label">IOS</div>
		<div>
			<img id="ios-qrcode" src="" />
		</div>
		<div>
			<a id="ios-qrcode-path" class="app-qrcode-div-body-a" href="">DOWNLOAD</a>
		</div>
	</div>
</div>
<div class="app-qrcode-div">
	<div class="app-qrcode-div-body">
		<div class="app-qrcode-div-body-label">Android</div>
		<div>
			<img id="android-qrcode" src="" />
		</div>
		<div>
			<a id="android-qrcode-path" class="app-qrcode-div-body-a" href="">DOWNLOAD</a>
		</div>
	</div>
</div>
</body>
</html>
