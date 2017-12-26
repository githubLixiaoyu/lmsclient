function initEdit(parentid, row){
	$("#portlet-upload-dlg .col-md-12").css("height", $(window).height()*0.7);
	$("#parentpoint").val(parentid);
	
	if($("#parentpoint").val() != 0){
		$("#categoryTd").hide();
	}else{
		$("#categoryTd").show();
	}
	//试题分类
	var url = server + "/manager/tquestioncategory/selectOptionQuestionCategoryList.do";
	var data = {};
	utils.ajax.postJson(url, data, function(result){
		createSelect2("categoryid", result.data, "");
	});
	if(row != null){
		$("#point").val(row.point);
		$("#categoryid").select2("val", row.categoryid);
		$("#pointname").val(row.pointname);
		$("#remark").val(row.remark);
	}
}

$("#saveBtn").on("click", function(){
	if(checkData()){
		var data = packData();
		var url = server + '/manager/tknowledgepoint/saveKnowledgepoint.do';
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
	if($("#parentpoint").val() == 0){
		var categoryid = $("#categoryid").val();
		if(categoryid == ""){
			utils.dialog.alert(fmtQuestionCategory+fmtIsNotNull);
			return false;
		}
	}
	var pointname = $("#pointname").val();
	if(pointname == ""){
		utils.dialog.alert(fmtKnowledgeName+fmtIsNotNull);
		return false;
	}
	return true;
}

function packData(){
	var data = {};
	data["point"] = $("#point").val();
	data["parentpoint"] = $("#parentpoint").val();
	data["categoryid"] = $("#categoryid").val();
	data["pointname"] = $("#pointname").val();
	data["remark"] = $("#remark").val();
	return data;
}