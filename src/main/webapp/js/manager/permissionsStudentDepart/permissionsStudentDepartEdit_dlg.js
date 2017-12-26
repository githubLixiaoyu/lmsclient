function initEdit(row){
	$("#portlet-upload-dlg .col-md-12").css("height", $(window).height()*0.7);
	if(row != null){
		$("#departid").val(row.departid);
		$("#departname").val(row.departname);
		$("#remarks").val(row.remarks);
	}
}

$("#saveBtn").on("click", function(){
	if(checkData()){
		var valideUrl = server + '/manager/tpermissionsstudentdepart/valideDepartName.do';
		var valideData = {};
		valideData["departname"] = $("#departname").val();
		valideData["departid"] = $("#departid").val();
		utils.ajax.post(valideUrl, valideData, function(result){
			if(!result.data){
				utils.dialog.alert("lms.departNameRepeat");
			}else{
				var data = packData();
				var url = server + '/manager/tpermissionsstudentdepart/savePermissionsStudentDepart.do';
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
		
	}
});

function checkData(){
	var departid = $("#departid").val();
	var departname = $("#departname").val();
	if(departname == ""){
		utils.dialog.alert(fmtCompanyName+fmtIsNotNull);
		return false;
	}
	return true;
}

function packData(){
	var data = {};
	data["departid"] = $("#departid").val();
	data["departname"] = $("#departname").val();
	data["remarks"] = $("#remarks").val();
	return data;
}