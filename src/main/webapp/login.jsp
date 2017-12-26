<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><fmt:message key="lms.webTitle"/></title>

<link rel="stylesheet" type="text/css" href="css/index/main.css" />
<link rel="stylesheet" type="text/css" href="css/index/bgstretcher.css" />
<style type="text/css">
.loading-message.loading-message-boxed {
    background-color: #eee;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-shadow: 0 1px 8px rgba(0, 0, 0, 0.1);
    height: 22px;
}
.loading-message {
    color: #000 !important;
    display: inline-block;
    font-size: 13px;
    font-weight: 400;
    margin: 0 auto;
    min-width: 125px;
    padding: 10px;
    text-align: center;
    vertical-align: middle;
}
.loading-message-span {
	position: relative;
	top: -6px;
}
</style>

<script type="text/javascript" src="js/index/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/index/bgstretcher.js"></script>

<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/tools.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/msg.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/dialog.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/ajax.js" type="text/javascript"></script>

<script type="text/javascript">

	var server = "${pageContext.request.contextPath}";
	//平台、设备和操作系统
	var sUserAgent = navigator.userAgent.toLowerCase();  
    var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";  
    var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";  
    var bIsMidp = sUserAgent.match(/midp/i) == "midp";  
    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";  
    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";  
    var bIsAndroid = sUserAgent.match(/android/i) == "android";  
    var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";  
    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";  
    if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {  
    	$(location).attr('href', server + '/indexMobile.jsp');
	} else {
		var globalServerMsg;
		var language = navigator.userLanguage || navigator.language;
		language = language.replace("-", "_");
		$(function(){
			$.getJSON('${pageContext.request.contextPath}/common/loadAllMsg.do?locale=' + language,function(msg){
				globalServerMsg = msg;
			});
		});

		$(document).ready(function(){
			
			$("#login_button").click(function() {
				var url = server + "/login";
				var data = {};
				data["login_id"] = $("#login_id").val();
				data["login_pwd"] = $("#login_pwd").val();
				utils.ajax.post(url, data, function(result){
					if (result.status == 1) {
						var savrUrl = server + "/auth/common/saveLoginInfo.do";
						utils.ajax.post(savrUrl, data, function(res){
							$("#error").css("display", "none");
							$(location).attr('href', server + '/pages/ui/personal/personalCenter.jsp');
						});
					} else {
						$("#error").css("display", "block");
					}
				});
			});
			
	        //  Initialize Backgound Stretcher	   
			$('BODY').bgStretcher({
				images: ['images/index/login.jpg'],
				imageWidth: 1024, 
				imageHeight: 768, 
				slideDirection: 'N',
				slideShowSpeed: 1000,
				transitionEffect: 'fade',
				sequenceMode: 'normal',
				buttonPrev: '#prev',
				buttonNext: '#next',
				pagination: '#nav',
				anchoring: 'left center',
				anchoringImg: 'left center'
			});
			$('BODY').bgStretcher.pause();
		});
		function registerUser() {
	    	$(location).attr('href', server + '/registerUser.jsp');
		}
	}
</script>

<script type="text/javascript" src="js/index/main.js"></script>

</head>
<body>
<%@ include file="/header.jsp"%>
	<div id="login" class="bgstretcher-login">
		<div class="bgstretcher-login-title"><fmt:message key="lms.welcomeUi"/></div>
		<br />
		<br />
		<div class="bgstretcher-login-label">
			<span class="bgstretcher-login-id"><fmt:message key="lms.account"/></span>
			<span class="bgstretcher-login-id-new"><a class="bgstretcher-login-id-new-a" href="javascript:void(registerUser())"><fmt:message key="lms.joinFreeNow"/></a></span>
		</div>
		<br />
		<div><input id="login_id" type="text" name="login_id" /></div>
		<br />
		<div class="bgstretcher-login-label"><fmt:message key="lms.password"/></div>
		<br />
		<div><input id="login_pwd" type="password" name="login_pwd" /></div>
		<br />
		<br />
		<br />
		<button id="login_button" class="bgstretcher-login-button"><fmt:message key="lms.loginSignIn"/></button>
		<br />
		<br />
		<br />
		<div id="error" style="display:none;"><font class="bgstretcher-login-x">X</font>&nbsp;&nbsp;&nbsp;<font class="bgstretcher-login-error"><fmt:message key="lms.accountOrPasswordError"/></font></div>
	</div>
<%@ include file="/footer.jsp"%>
</body>
</html>
