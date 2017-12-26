function initCourseTypeEdit(parentid, row){
	$("#portlet-upload-dlg .col-md-12").css("height", $(window).height()*0.7);
	$("#parentid").val(parentid);
	if(parentid == 0){
		$("#parentIdDiv").hide();
		$("#remarksDiv").hide();
	}
	if(row != null){
		$("#id").val(row.id);
		$("#sort").val(row.sort);
		$("#coursetypename").val(row.coursetypename);
		$("#coursetypenameEn").val(row.coursetypenameEn);
		$("#imageFileDiv img").attr("src", row.imagepath);
		$("#imagename").val(row.imagename);
		$("#imageMobileFileDiv img").attr("src", row.imagemobilepath);
		$("#imagemobilename").val(row.imagemobilename);
		$("#remarks").val(row.remarks);
	}
}

// 上传图片方法
function fileUploadChange(id, divId, fieldId) {
	var me = $("#" + id);
	var obj = document.getElementById(id);
	if (obj.files.length > 3) {
		me.val("");
		return utils.dialog.alert("lms.uploadPicCheckSum");
	}
	// 图片类型
	var imageType = ",bmp,gif,jpg,ico,png,tif,";
	// 判断是否为图片格式的文件
	if (imageType.indexOf(","
			+ me.val().substring(me.val().lastIndexOf(".") + 1).toLowerCase()
			+ ",") < 0) {
		utils.dialog.alert('lms.uploadPicCheckType');
		me.val("");
	} else {
		uploadFile(id, divId, fieldId);
	}
}

function uploadFile(id, divId, fieldId) {
	var url = server + '/manager/tcoursetype/uploadImageToTemp.do';
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
				var html = "";
				var imagesId = data.data.imagesId;
				var pathD = data.data.imagesSrc;
				for (var i = 0; i < pathD.length; i++) {
					html += '<div class="d1"><img style="width:170px;height:170px;" src="'
							+ pathD[i] + '"/></div>';
					// html += '<a class="hide delete-btn">删除</a>';
				}
				if (html != "") {
					$("#" + divId).empty();
					$("#" + divId).append(html);
				}
				if(imagesId != null && imagesId != ""){
					$("#" + divId + "imageCondId").val(imagesId["imageCondId"]);
					$("#" + divId + "imageItemKindId").val(imagesId["imageItemKindId"]);
					utils.dialog.toastr("lms.uploadSuccess");
				}
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
		var url = server + '/manager/tcoursetype/saveCourseType.do';
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
	var parentid = $("#parentid").val();
	var coursetypename = $("#coursetypename").val();
	var coursetypenameEn = $("#coursetypenameEn").val();
	var sort = $("#sort").val();
	if(coursetypename == ""){
		utils.dialog.alert(fmtCourseTypeName+fmtIsNotNull);
		return false;
	}
	if(coursetypenameEn == ""){
		utils.dialog.alert(fmtCourseTypeNameEn+fmtIsNotNull);
		return false;
	}
	if(sort == ""){
		utils.dialog.alert(fmtOrderNum+fmtIsNotNull);
		return false;
	}else if(sort.search("^-?\\d+$")!=0){
		utils.dialog.alert(fmtOrderNum+fmtCheckInteger);
        return false;
	}
	if(parentid != 0){
		var imagename = $("#imagename").val();
		if(imagename == ""){
			utils.dialog.alert(fmtWebPic+fmtIsNotNull);
			return false;
		}
		var imagemobilename = $("#imagemobilename").val();
		if(imagemobilename == ""){
			utils.dialog.alert(fmtMobilePic+fmtIsNotNull);
			return false;
		}
	}
	return true;
}

function packData(){
	var data = {};
	data["id"] = $("#id").val();
	data["coursetypename"] = $("#coursetypename").val();
	data["coursetypenameEn"] = $("#coursetypenameEn").val();
	data["imagename"] = $("#imagename").val();
	data["imagemobilename"] = $("#imagemobilename").val();
	data["parentid"] = $("#parentid").val();
	data["sort"] = $("#sort").val();
	data["remarks"] = $("#remarks").val();
	return data;
}