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
		$(document).ready(function(){
	        //  Initialize Backgound Stretcher	   
	        var language = navigator.userLanguage || navigator.language;
	        var imagesArray;
			if (language.substring(0, 2) == "zh") {
				imagesArray = ['images/index/1.jpg', 'images/index/2.jpg', 'images/index/3.jpg', 'images/index/4.jpg', 'images/index/5.jpg', 'images/index/6.jpg'];
			} else {
				imagesArray = ['images/index/1_en.jpg', 'images/index/2_en.jpg', 'images/index/3_en.jpg', 'images/index/4_en.jpg', 'images/index/5_en.jpg', 'images/index/6_en.jpg'];
			}
			$('BODY').bgStretcher({
				images: imagesArray,
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
		});
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
	}
</script>

<script type="text/javascript" src="js/index/main.js"></script>

</head>
<body>
<%@ include file="/header.jsp"%>
	<div id="nav" class="bgstretcher-page-div"></div>
	<div id="line" class="bgstretcher-page-line"></div>
<%@ include file="/footer.jsp"%>
</body>
</html>
