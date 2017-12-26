function initEdit(parentid, row){
	$("#portlet-upload-dlg .col-md-12").css("height", $(window).height()*0.7);
	$("#pointsparentid").val(parentid);
	if(row != null){
		$("#pointsid").val(row.pointsid);
		$("#pointsname").val(row.pointsname);
		$("#remark").val(row.remark);
	}
}

$("#saveBtn").on("click", function(){
	if(checkData()){
		var data = packData();
		var url = server + '/manager/tnewtestpapercategory/saveNewtestpaperCategory.do';
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
	var pointsname = $("#pointsname").val();
	if(pointsname == ""){
		utils.dialog.alert(fmtTestaperCategoryName+fmtIsNotNull);
		return false;
	}
	return true;
}

function packData(){
	var data = {};
	data["pointsid"] = $("#pointsid").val();
	data["pointsparentid"] = $("#pointsparentid").val();
	data["status"] = $("#status").val();
	data["pointsname"] = $("#pointsname").val();
	data["remark"] = $("#remark").val();
	return data;
}