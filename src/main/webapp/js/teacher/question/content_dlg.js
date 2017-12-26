function initEdit(id, content){
	$("#contentId").val(id);
	$("#content").val(content);
}

$("#saveBtn").on("click", function(){
	var id = $("#contentId").val();
	var content = $("#content").val();
	$('#portlet-upload-dlg').modal('hide');
	winPar.changeContent(id, content);
});
$(function() {
	$('#content').keypress(function(e) {
		if (e.which == 13) {
			$("#saveBtn").click();
		}
	});
    $("#portlet-upload-dlg").show(function(){
    	$("#content").focus();
    	$(".modal-backdrop").css("height", $(window).height());
    });
});