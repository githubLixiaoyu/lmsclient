<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="ss3" %>

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-toastr/toastr.min.css"/>
<!-- END PAGE LEVEL STYLES -->


<!-- BEGIN dataTables STYLES -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
<!-- END dataTables STYLES -->
<!-- BEGIN select STYLES -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-multi-select/css/multi-select.css"/>
<!-- END select STYLES -->
<!-- BEGIN search STYLES -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-datepicker/css/datepicker.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-notific8/jquery.notific8.min.css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/admin/pages/css/search.css" rel="stylesheet" type="text/css"/>
<!-- END search STYLES -->

<!-- BEGIN modal STYLES
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css"/>
 -->
<!-- END modal STYLES -->

<!-- BEGIN custom STYLES -->
<!-- 全局表单 -->
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/css/custom.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css"/>
<!-- END custom STYLES -->

<!-- BEGIN fileupload STYLES -->
<!--  
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-file-upload/css/ajaxfileupload.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet"/>
-->
<!-- END fileupload STYLES -->

<!-- BEGIN tuijianshiyang STYLES -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/slider-flickity/css/flickity-docs.css" media="screen" />
<!-- BEGIN tuijianshiyang STYLES -->
<!-- BEGIN jichengweihu STYLES -->
<!-- 
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/fixed_table/css/fixed_table_rc.css" rel="stylesheet" type="text/css" /> 
-->
<!-- BEGIN jichengweihu STYLES -->


<!-- BEGIN THEME STYLES -->
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="${pageContext.request.contextPath}/bootstrap/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/editable-select/jquery-editable-select.css"/>

<%
 session.setAttribute("version", System.currentTimeMillis());
%>
<script>
// var _hmt = _hmt || [];
// (function() {
//   var hm = document.createElement("script");
//   hm.src = "https://hm.baidu.com/hm.js?ee4c276cca1170037f863784e119a753";
//   var s = document.getElementsByTagName("script")[0]; 
//   s.parentNode.insertBefore(hm, s);
// })();
</script>