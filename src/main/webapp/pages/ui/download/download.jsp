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
.down-header {
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
.down-header-content {
 	position: absolute;
	top: 130px;
    width: 100%;
    height: 150px;
    text-align: center;
}
.down-header-content-title {
    color: #FFF;
    font-size: 40px;
    line-height: 60px;
}
.down-header-content-line {
    background-color: #CBA67C;
    width: 95px;
    height: 5px;
    margin: 10px auto;
}
.down-header-content-note {
    color: #FFF;
    font-size: 12px;
    margin-top: 5px;
}
.down-content {
    width: 100%;
    overflow: hidden;
}
.down-content1 {
	margin-top: 80px;
}
.down-content2 {
	position:absolute;
	margin-left:750px;
	top:380px;
	width:500px;
}
.down-content3 {
	margin-top: 60px;
}
.down-content4 {
	position:absolute;
	margin-left:750px;
	top:380px;
	width:500px;
}
.down-content-font1 {
	font-size:33px;
	color: #333333;
	
}
.down-content-font2 {
	font-size:25px;
	color: #666666;
	margin:0 auto;  
}
.down-content-font3 {
	position:absolute;
	top:300px;
	font-size:25px;
	color: #CBA67C;
	cursor:pointer;
}
.down-content-font4 {
	position:absolute;
	top:300px;
	margin-left:200px;
	font-size:25px;
	color: #CDC5BF;
	cursor:pointer;
}
.down-content-font5 {
	position:absolute;
	font-size:20px;
	color: #CDC5BF;
	width:500px;
	margin-top:40px;
	text-align:center;
}
.down-content-font6 {
	position:absolute;
	top:300px;
	font-size:25px;
	color: #CDC5BF;
	cursor:pointer;
}
.down-content-font7 {
	position:absolute;
	top:300px;
	margin-left:200px;
	font-size:25px;
	color: #CBA67C;
	cursor:pointer;
}
.button {
    width:200px;
    height:50px;
	background-image: url("${pageContext.request.contextPath}/images/download/downButton.png");
    background-size: 200px 50px;
    border: none;
    color: white;
    display: inline-block;
    font-size: 20px;
    cursor:pointer;
    text-align:center;
    line-height:45px;
}
.android{
	margin-left:70px;
	font-size: 23px;
	color: #666666;
}
.ios{
	margin-left:185px;
	font-size: 23px;
	color: #666666;
}
.personal-type-key-qrcode {
	width: 250px;
	height: 250px;
}
#mobAppPic{
	margin-top:10px;
	position:absolute;
	width:540px;
}
#ios-qrcode{
	margin-left:20px;
	width:40%;
	height:40%;
}
#android-qrcode{
    width:40%;
	height:40%;

}
#pcClient-show{
	width:1280px; 
	height:500px;
	margin:0 auto;  
}
#mobClient-show{
	width:1280px;
	margin:0 auto; 
	height:520px; 
}
#pcmoClient{
  margin:0 auto;
  width:300px;

}
#down-img1{
width:60%;
height:60%;

}
body {
	background-color: #FFF;
}
</style>
<div class="down-header-content">
	<div class="down-header-content-title"><fmt:message key="lms.dowloadCenter"/></div>
	<div class="down-header-content-line"></div>
</div>
<div class="down-header"></div>
<div class="down-content">
    <div id="pcmoClient">
	    <label class="down-content-font3" id="pcClient"><fmt:message key="lms.pcClient"/></label>
	    <label class="down-content-font4" id="mobClient"><fmt:message key="lms.mobClient"/></label>
    </div>
    <div id="pcClient-show"> 
		<div class="down-content1">
			<img id="down-img" src="" width="716px" height="502px"/>
		</div>
		<div class="down-content2">
			<label class="down-content-font1"><fmt:message key="lms.platformDowload"/></label></br></br></br></br>
			<label class="down-content-font2"><fmt:message key="lms.size"/>:  &nbsp;136M</label></br>
			<label class="down-content-font2"><fmt:message key="lms.version"/>: &nbsp; V1.0</label></br>
			<label class="down-content-font2"><fmt:message key="lms.adaptSystem"/>:&nbsp;  win7 / win8/ win10</label></br>
			<label class="down-content-font2"><fmt:message key="lms.updateTime"/>: &nbsp; 2017 / 09/ 01</label>
			</br></br></br></br>
			<label class="button"><fmt:message key="lms.dowload"/></label>
		</div>
	</div>
	<div id="mobClient-show">
		<div class="down-content3">
			<img id="down-img1" src=""/>
		</div>
		<div class="down-content4">
			<label class="down-content-font1"><fmt:message key="lms.mobAppDowload"/></label></br></br>
			<div id="mobAppPic">	
			</div>
		</div>
		
	</div>
</div>
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
$(function() {
	var url = server + "/ui/tapp/getQrcode.do";
	utils.ajax.post(url, {}, function(result){
		$("#mobAppPic").html('<img id="android-qrcode" class="personal-type-key-qrcode" src="' + server + '/../lmsFiles/qrcodeImages/' + result.data.android + '">'
	                            +'<img id="ios-qrcode" class="personal-type-key-qrcode" src="' + server + '/../lmsFiles/qrcodeImages/' + result.data.ios + '">'
		                        +'<br/><label class="android">Android</label><label class="ios">IOS</label><br />'
		                        +'<label class="down-content-font5"><fmt:message key="lms.scanQRcode"/></label>');
	});
});
	$("#pcClient-show").show();
	$("#mobClient-show").hide();
	var language = navigator.userLanguage || navigator.language;
	$("#down-img").attr("src", "${pageContext.request.contextPath}/images/download/pcClient.png");
	$("#down-img1").attr("src", "${pageContext.request.contextPath}/images/download/mobClient.png");
	
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
	
	$("#pcClient").on("click", function(){
		$("#pcClient-show").show();
		$("#mobClient-show").hide();
		$('#pcClient').removeClass("down-content-font6");
		$('#pcClient').addClass("down-content-font3");
		$('#mobClient').removeClass("down-content-font7");
		$('#mobClient').addClass("down-content-font4");	
	});
	$("#mobClient").on("click", function(){
		$("#pcClient-show").hide();
		$("#mobClient-show").show();
		$('#pcClient').removeClass("down-content-font3");
		$('#pcClient').addClass("down-content-font6");
		$('#mobClient').removeClass("down-content-font4");
		$('#mobClient').addClass("down-content-font7");
	});
	$(".button").on("click", function(){
		if (language.substring(0, 2) == "zh") {
			window.location.href = server + "/../lmsFiles/esp/esp_ch.exe"
		} else {
			window.location.href = server + "/../lmsFiles/esp/esp_en.exe"
		}
	});
</script>
</html>