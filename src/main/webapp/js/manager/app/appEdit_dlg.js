function initEdit(){
	$("#portlet-upload-dlg .col-md-12").css("height", $(window).height()*0.7);
	//平台类型
	var dataValue = [{"id":"android","text":"android"},{"id":"ios","text":"ios"}];
	createSelect2("editTable #platform", dataValue, "");
	//强制更新
	var dataValue = [{"id":"1","text":fmtUpdateYes},{"id":"0","text":fmtUpdateNo}];
	createSelect2("editTable #mustUpdate", dataValue, "1");
}

//上传文件方法
function fileUploadChange(id, fieldId) {
	var me = $("#" + id);
	var obj = document.getElementById(id);
	if (obj.files.length > 3) {
		me.val("");
		return utils.dialog.alert("lms.uploadPicCheckSum");
	}
	// 文件类型
	var fileType = ",apk,ipa,";
	// 判断是否为图片格式的文件
	if (fileType.indexOf(","
			+ me.val().substring(me.val().lastIndexOf(".") + 1).toLowerCase()
			+ ",") < 0) {
		utils.dialog.alert('lms.fileCheckType');
		me.val("");
	} else {
		uploadFile(id, fieldId);
	}
}

function uploadFile(id, fieldId) {
	var url = server + '/manager/tapp/uploadToTemp.do';
	Metronic.blockUI({
		animate : true,
		overlayColor : 'none'
	});
	// 执行上传文件操作的函数
	$.ajaxFileUpload({
		// 处理文件上传操作的服务器端地址
		url : url,
		secureuri : false, // 是否启用安全提交,默认为false
		fileElementId : id, // 文件选择框的id属性
		dataType : 'text', // 服务器返回的格式,可以是json或xml等
		success : function(data, status) {
			// 服务器响应成功时的处理函数
			data = data.replace(/<pre.*?>/g, ''); // ajaxFileUpload会对服务器响应回来的text内容加上<pre
													// style="....">text</pre>前后缀
			data = data.replace(/<PRE.*?>/g, '');
			data = data.replace("<PRE>", '');
			data = data.replace("</PRE>", '');
			data = data.replace("<pre>", '');
			data = data.replace("</pre>", '');
			data = JSON.parse(data);
			Metronic.unblockUI();
			if ("1" == data.flag) {
				$("#"+fieldId).val(data.data.fileName);
			}
		},
		error : function(data, status, e) { // 服务器响应失败时的处理函数
			Metronic.unblockUI();
			utils.dialog.toastr(data.msg, "lms0002", "error");
		}
	});
}

$("#saveBtn").on("click", function(){
	if(checkData()){
		var data = packData();
		var url = server + '/manager/tapp/saveApp.do';
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
	var platform = $("#platform").val();
	var mustUpdate = $("#mustUpdate").val();
	var versionCode = $("#versionCode").val();
	var filename = $("#filename").val();
	if(platform == ""){
		utils.dialog.alert(fmtPlatformType+fmtIsNotNull);
		return false;
	}
	if(mustUpdate == ""){
		utils.dialog.alert(fmtMustUpdate+fmtIsNotNull);
		return false;
	}
	if(versionCode == ""){
		utils.dialog.alert(fmtVersionNum+fmtIsNotNull);
		return false;
	}
	if(filename == ""){
		utils.dialog.alert("lms.fileIsNotNull");
		return false;
	}
	if(platform == "android" && !filename.endWith(".apk")){
		var msg = fmtCheckFileEndWith.replace("{0}", ".apk");
		utils.dialog.alert(msg);
		return false;
	}
	if(platform == "ios" && !filename.endWith(".ipa")){
		var msg = fmtCheckFileEndWith.replace("{0}", ".ipa");
		utils.dialog.alert(msg);
		return false;
	}
	return true;
}

function packData(){
	var data = {};
	data["platform"] = $("#platform").val();
	data["mustUpdate"] = $("#mustUpdate").val();
	data["versionCode"] = $("#versionCode").val();
	data["filename"] = $("#filename").val();
	return data;
}
String.prototype.endWith = function(endStr) {
	var d = this.length - endStr.length;
	return (d >= 0 && this.lastIndexOf(endStr) == d)
}

function checkNum(obj){
	var val = $(obj).val();
	var re = /^[0-9]+$/;
	if(!re.test(val)){
		utils.dialog.alert("lms.checkNum");
		$(obj).val(0);
	}
}