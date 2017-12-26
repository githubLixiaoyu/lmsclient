function initCourseTypeEdit(row){
	$("#portlet-upload-dlg .col-md-12").css("height", $(window).height()*0.7);
	//状态
	var dataValue = [{"id":"0","text":fmtUserStatus0},{"id":"1","text":fmtUserStatus1},{"id":"2","text":fmtUserStatus2}];
	createSelect2("status", dataValue, "");
	//所属部门
	if(loginUserAuthId == 4){
		var dataValue = [{"id":loginUserDepartid,"text":loginUserDepartname}];
		createSelect2("departid", dataValue, "");
	}else{
		var url = server + "/manager/tpermissionsstudentdepart/selectAllPermissionsStudentDepartList.do";
		var data = {};
		utils.ajax.postJson(url, data, function(result){
			createSelect2("departid", result.data, "");
		});
	}
	//角色
	var url = server + "/manager/tauth/selectOptionAuthList.do";
	var data = {};
	utils.ajax.postJson(url, data, function(result){
		var dataValue = result.data;
		if(loginUserAuthId == 4){
			var resultList = [];
			for(var i=0;i<dataValue.length;i++){
				if(dataValue[i].id != "1" && dataValue[i].id != "4"){
					resultList.push(dataValue[i]);
				}
				if(row != null && row.authId == loginUserAuthId){
					resultList.push(dataValue[i]);
				}
			}
			createSelect2("authId", resultList, "");
		}else{
			createSelect2("authId", dataValue, "");
		}
	});
	if(row != null){
		$("#studentid").val(row.studentid);
		$("#logincode").val(row.logincode);
		$("#status").select2("val", row.status);
		$("#photoDiv img").attr("src", row.imagepath);
		$("#name").val(row.name);
		$("#nickname").val(row.nickname);
		$("#departid").select2("val", row.departid);
		$("#authId").select2("val", row.authId);
		if(row.authId == loginUserAuthId){
			$("#authId").attr("disabled", true)
		}
	}else{
		$("#name").val('');
		$("#nickname").val('');
	}
	$("#newPassword").val('');
}

$("#saveBtn").on("click", function(){
	if(checkData()){
		var valideUrl = server + '/manager/tpermissionsstudentinfo/valideLoginCode.do';
		var valideData = {};
		valideData["logincode"] = $("#logincode").val();
		valideData["studentid"] = $("#studentid").val();
		utils.ajax.post(valideUrl, valideData, function(result){
			if(!result.data){
				utils.dialog.alert("lms.loginCodeRepeat");
			}else{
				var data = packData();
				var url = server + '/manager/tpermissionsstudentinfo/savePermissionsStudentinfo.do';
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
	var studentid = $("#studentid").val();
	var logincode = $("#logincode").val();
	var status = $("#status").val();
	var name = $("#name").val();
	var nickname = $("#nickname").val();
	var departid = $("#departid").val();
	var authId = $("#authId").val();
	var newPassword = $("#newPassword").val();
	var newPassword1 = $("#newPassword1").val();
	if(logincode == ""){
		utils.dialog.alert(fmtLoginCode+fmtIsNotNull);
		return false;
	}
	if(name == ""){
		utils.dialog.alert(fmtName+fmtIsNotNull);
		return false;
	}
	if(nickname == ""){
		utils.dialog.alert(fmtNickName+fmtIsNotNull);
		return false;
	}
	if(status == ""){
		utils.dialog.alert(fmtDataStatus+fmtIsNotNull);
		return false;
	}
	if(departid == ""){
		utils.dialog.alert(fmtDepartName+fmtIsNotNull);
		return false;
	}
	
	if($("#studentid").val() == '' && newPassword == ''){
		utils.dialog.alert(fmtPassword+fmtIsNotNull);
		return false;
	}
	
	if(authId == ''){
		utils.dialog.alert(fmtRole+fmtIsNotNull);
		return false;
	}
	
//	if(newPassword != newPassword1){
//		utils.dialog.alert("两次密码的输入不相同！");
//		return false;
//	}
	return true;
}

function packData(){
	var data = {};
	data["studentid"] = $("#studentid").val();
	data["logincode"] = $("#logincode").val();
	data["name"] = $("#name").val();
	data["nickname"] = $("#nickname").val();
	data["departid"] = $("#departid").val();
	data["status"] = $("#status").val();
	data["authId"] = $("#authId").val();
	if($("#newPassword").val() != ''){
		data["password"] = $("#newPassword").val();
	}
	return data;
}