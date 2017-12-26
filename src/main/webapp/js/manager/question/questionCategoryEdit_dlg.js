function initEdit(parentid, row){
	$("#portlet-upload-dlg .col-md-12").css("height", $(window).height()*0.7);
	$("#parentid").val(parentid);
	if(row != null){
		$("#categoryid").val(row.categoryid);
		$("#categoryname").val(row.categoryname);
		$("#remark").val(row.remark);
	}
}

$("#saveBtn").on("click", function(){
	if(checkData()){
		var data = packData();
		var url = server + '/manager/tquestioncategory/saveQuestionCategory.do';
		utils.ajax.post(url, data, function(result){
			if(result.flag == "1" && result.data != 0){
				utils.dialog.alert("lms0006", function(){
					$('#portlet-upload-dlg').modal('hide');
					init(grid);
				});
			}else{
				utils.dialog.alert("lms.saveFail");
			}
		});
		
	}
});

function checkData(){
	var categoryname = $("#categoryname").val();
	if(categoryname == ""){
		utils.dialog.alert(fmtQuestionCategoryName+fmtIsNotNull);
		return false;
	}
	return true;
}

function packData(){
	var data = {};
	data["parentid"] = $("#parentid").val();
	data["categoryid"] = $("#categoryid").val();
	data["categoryname"] = $("#categoryname").val();
	data["remark"] = $("#remark").val();
	return data;
}