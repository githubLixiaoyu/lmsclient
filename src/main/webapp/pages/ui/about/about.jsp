<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title><fmt:message key="lms.webTitle"/></title>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<%@ include file="/pages/common/header.jsp"%>
</head>
<body>
<%@ include file="/header.jsp"%>
<style type="text/css">
.about-header {
    width: 100%;
    height: 283px;
    background: url("${pageContext.request.contextPath}/images/index/background.jpg");
}
.bgstretcher-logo {
 	position: absolute;
 	left: 0px;
	top: 0px;
    width: 329px;
    height: 153px;
    background: url("${pageContext.request.contextPath}/images/index/logo.png") no-repeat left top; 
}
.bgstretcher-menu {
    position: absolute;
    top: 0px;
    right: 0px;
    width: 1200px;
    height: 153px;
    color: #FFF;
    text-align: right;
    line-height: 153px;
    font-size: 14px;
}
.about-header-content {
 	position: absolute;
	top: 130px;
    width: 100%;
    height: 150px;
    text-align: center;
}
.about-header-content-title {
    color: #FFF;
    font-size: 40px;
    line-height: 60px;
}
.about-header-content-line {
    background-color: #CBA67C;
    width: 95px;
    height: 5px;
    margin: 10px auto;
}
.about-header-content-note {
    color: #FFF;
    font-size: 12px;
    margin-top: 5px;
}
.about-content {
    width: 100%;
    overflow: hidden;
}
body {
    background-color: #FFF;
}
</style>
<div class="about-header-content">
	<div class="about-header-content-title"><fmt:message key="lms.aboutZXtech"/></div>
	<div class="about-header-content-line"></div>
	<div class="about-header-content-note"><fmt:message key="lms.aboutZXtechExplain"/></div>
</div>
<div class="about-header"></div>
<div class="about-content">
	<img id="about-img" src="" width="100%" height="100%"/>  
</div>
<%@ include file="/pages/common/footer.jsp"%>
<%@ include file="/footer.jsp"%>
<%@ include file="/pages/common/goTop.jsp"%>
<style type="text/css">
.footer-font {
    position: static;
	text-align: center;
	clear: both;
	width: 100%;
	color: #666666;
    font-family: "Lucida Grande",Arial,Helvetica,sans-serif;
    font-size: 12px;
}
</style>
</body>
<script type="text/javascript">

	var language = navigator.userLanguage || navigator.language;
	if (language.substring(0, 2) == "zh") {
		$("#about-img").attr("src", "${pageContext.request.contextPath}/images/about/about.jpg");
	} else {
		$("#about-img").attr("src", "${pageContext.request.contextPath}/images/about/about_en.jpg");
	}

	var server = "${pageContext.request.contextPath}";
	$.ajax({
	      url: server + "/ui/tpermissionsstudentinfo/selectLoginUser.do",
	      data: {},
	      dataType:"json",
	      type: "POST",
	      beforeSend:function(XMLHttpRequest){
	    	  utils.tools.loading();
		  },
	      success: function(result){
	    	 utils.tools.unloading();
	    	 if (result == null){
	 			$("#menu-login").css("display", "inline");
				$("#menu-username").css("display", "none");
				$("#menu-logout").css("display", "none");
	    	 } else {
	 			$("#menu-username").html(result.data.nickName + "&nbsp;/&nbsp;");
				$("#menu-login").css("display", "none");
				$("#menu-username").css("display", "inline");
				$("#menu-logout").css("display", "inline");
	    	 }
	      },
	      error:function(){
	    	  utils.tools.unloading();
	    	  utils.dialog.toastr("lms0003", "lms0002", "error");
	      }
	   }
	);
</script>
<script src="${pageContext.request.contextPath}/js/ui/about/about.js?version=${version}"></script>
</html>