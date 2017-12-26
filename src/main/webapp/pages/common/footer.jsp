<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/respond.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script type="text/javascript">
<!--
	var server = "${pageContext.request.contextPath}";
//-->
</script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<!--  
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
-->
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery.blockui.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<!-- BEGIN dataTable PLUGINS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/select2/select2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-notific8/jquery.notific8.min.js"></script>
<!-- END dataTable PLUGINS -->

<!-- BEGIN search PLUGINS -->
<script src="${pageContext.request.contextPath}/bootstrap/assets/admin/pages/scripts/search.js"></script>
<!-- END search PLUGINS -->
<!-- BEGIN fileupload PLUGINS -->
<!--  
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-file-upload/js/ajaxfileupload.js"></script>
-->
<!-- END fileupload PLUGINS -->

<!-- BEGIN 推荐仕样 PLUGINS -->
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/slider-flickity/js/flickity-docs.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/slider-flickity/js/flickity-custom.js"></script>
<!-- END 推荐仕样 PLUGINS -->

<!-- BEGIN 集成维护 PLUGINS -->
<!--  
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/fixed_table/js/fixed_table_rc.js"></script>
-->
<!-- END 集成维护 PLUGINS -->

<!-- BEGIN CUSTOME SCRIPTS -->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/tools.js?version=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/string.js?version=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/server.js?version=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/dialog.js?version=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/ajax.js?version=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/select.js?version=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/file.js?version=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/validate.js?version=${version}"></script>
 <script type="text/javascript">
	var globalServerMsg;
	var language = navigator.userLanguage || navigator.language;
	language = language.replace("-", "_");
	$(function(){
		$.getJSON('${pageContext.request.contextPath}/common/loadAllMsg.do?locale=' + language,function(msg){
			globalServerMsg = msg;
		});
	});

</script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/msg.js?version=${version}"></script>
<!-- END CUSTOME SCRIPTS -->

<!-- BEGIN select PLUGINS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-select/bootstrap-select.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js"></script>
<!-- END select PLUGINS -->



<!-- BEGIN modal PLUGINS 
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
-->
<!-- END modal PLUGINS -->

<script src="${pageContext.request.contextPath}/bootstrap/assets/global/scripts/datatable.js"></script>

<!-- BEGIN jquery-validation PLUGINS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-validation/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-validation/js/additional-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-validation/js/localization/messages_zh.js"></script>
<!-- END jquery-validation PLUGINS -->

<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/editable-select/jquery-editable-select.js" type="text/javascript"></script>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->



<script>
jQuery(document).ready(function() {    
	   Metronic.init(); // init metronic core componets
	   Layout.init(); // init layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features 
	});
</script>