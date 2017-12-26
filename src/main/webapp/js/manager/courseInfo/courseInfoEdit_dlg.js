function initEdit(row){
	$(".scormTd").show();
	$("#portlet-upload-dlg .col-md-12").css("height", $(window).height()*0.7);
	//课程类型
	var url = server + "/manager/tcoursetype/selectCourseTypeListById.do";
	var data = {};
	data["parentid"] = "0";
	utils.ajax.post(url, data, function(result){
		if(row != null){
			createSelect2("flag", result.data, row.flag);
			$("#flag").val(row.flag);
		}else{
			createSelect2("flag", result.data, '');
		}
	});
	//二级分类
	dataValue = [{"id":"","text":""}];
	createSelect2("coursesectype", dataValue, "");
	//课件类型
	dataValue = [{"id":"1","text":fmtCourseUploadType1},{"id":"2","text":fmtCourseUploadType2},{"id":"3","text":fmtCourseUploadType3},{"id":"4","text":"VR"}];
	createSelect2("courseuploadtype", dataValue, "");
	//状态
//	var dataValue = [{"id":"0","text":"正在创建"},{"id":"2","text":"审核未过"}];
//	createSelect2("active", dataValue, "");
	
	addRadioHtml();
	
	if(row != null){
		$("#courseid").val(row.courseid);
		$("#coursename").val(row.coursename);
		$("#status").select2("val", row.status);
		$("#imageFileDiv img").attr("src", row.imagepath);
		$("#imageMobileFileDiv img").attr("src", row.imagemobilepath);
		$("#period").val(row.period);
		if(row.flag != ""){
			//课程类型
			var url = server + "/manager/tcoursetype/selectCourseTypeListById.do";
			var data = {};
			data["parentid"] = row.flag;
			utils.ajax.post(url, data, function(result){
				createSelect2("coursesectype", result.data, row.coursesectype);
			});
		}
		$("#courseuploadtype").select2("val", row.courseuploadtype);
		$("#forpeople").val(row.forpeople);
		$("#keywords").val(row.keywords);
		$("#remarks").val(row.remarks);
		$("input[name='ifFreeCourse'][value="+row.iffreecourse+"]").attr("checked",'checked'); 
		$("input[name='userpaper'][value="+row.userpaper+"]").attr("checked",'checked');
		$("#fileScormName").val(row.standby1);
		$("#imagename").val(row.imagename);
		$("#imagemobilename").val(row.imagemobilename);
		$("#espkey").val(row.espkey);
		$("#remarks").val(row.REMARKS);
		$("#imageId").val(row.imageId);
		
		if(row.courseuploadtype == "2" || row.courseuploadtype == "4"){
			$(".scormTd").hide();
			$("#espkey").attr("disabled", false);
		}else{
			$(".scormTd").show();
			$("#espkey").attr("disabled", true);
			if(row.courseuploadtype == "3"){
				$("table tr:eq(0) td:eq(0) label").html(fmtVideoFile+"：");
			}else{
				$("table tr:eq(0) td:eq(0) label").html(fmtLocalSCORM+"：");
			}
		}
	}
	
	
}

$("#flag").on("change", function(){
	//课程类型
	var url = server + "/manager/tcoursetype/selectCourseTypeListById.do";
	var data = {};
	data["parentid"] = $("#flag").val();
	utils.ajax.post(url, data, function(result){
		createSelect2("coursesectype", result.data, "");
	});
	if($("#flag").val() == ""){
		var dataValue = [{"id":"","text":""}];
		createSelect2("coursesectype", dataValue, "");
	}
});

$("#courseuploadtype").on("change", function(){
	if($(this).val() == "2" || $(this).val() == "4"){
		$("#espkey").attr("disabled", false);
//		$("table tr:eq(0) td:eq(0) label").html("课程名称：");
//		$("table tr:eq(0) td:eq(1)").html('<input class="form-control" id="coursename" name="coursename" type="text" style="width: 200px;">');
		$(".scormTd").hide();
	}else{
		$("#espkey").attr("disabled", true);
		$(".scormTd").show();
		if($(this).val() == "3"){
			$("table tr:eq(0) td:eq(0) label").html(fmtVideoFile+"：");
		}else{
			$("table tr:eq(0) td:eq(0) label").html(fmtLocalSCORM+"：");
		}
//		var changeStr = "fileUploadScormChange('myfile','','fileScormName')";
//		$("table tr:eq(0) td:eq(1)").html('<input type="file" id="myfile" name="myfile" onchange="'+changeStr+'"/><input type="hidden" id="fileScormName" name="fileScormName"/><font color="red"><span id="sp1"> * 文件类型：.zip</span></font>');
	}
});

function addRadioHtml(){
	//公开课程
	var ifFreeCourseHtml ='<label style="padding-right:20px;">'+
	'<label><input name="ifFreeCourse" type="radio" value="1" class="radioCss" checked="checked">'+fmtNo+'</label>'+
	'<input name="ifFreeCourse" type="radio" value="0" class="radioCss">'+fmtYes+'</label>';
	$("table tr:eq(4) td:eq(1)").html(ifFreeCourseHtml);
	//使用测试
	var userpaperHtml ='<label style="padding-right:20px;">'+
	'<label><input name="userpaper" type="radio" value="1" class="radioCss" checked="checked">'+fmtNo+'</label>'+
	'<input name="userpaper" type="radio" value="0" class="radioCss">'+fmtYes+'</label>';
	$("table tr:eq(4) td:eq(3)").html(userpaperHtml);
}

$("#saveBtn").on("click", function(){
	if(checkData()){
		var data = packData();
		var url = server + '/manager/tcourseinfo/saveCourseInfo.do';
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
		
		
//		var valideUrl = server + '/manager/tpermissionsstudentinfo/valideLoginCode.do';
//		var valideData = {};
//		valideData["logincode"] = $("#logincode").val();
//		valideData["studentid"] = $("#studentid").val();
//		utils.ajax.post(valideUrl, valideData, function(result){
//			if(!result.data){
//				utils.dialog.alert("用户名重复！");
//			}else{
//				var data = packData();
//				var url = server + '/manager/tpermissionsstudentinfo/savePermissionsStudentinfo.do';
//				utils.ajax.post(url, data, function(result){
//					if(result.flag == "1"){
//						utils.dialog.alert("lms0006", function(){
//							$('#portlet-upload-dlg').modal('hide');
//							init(grid);
//						});
//					}else{
//						utils.dialog.alert("lms.saveFail");
//					}
//				});
//			}
//		});
		
	}
});

//上传图片方法
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

function fileUploadScormChange(id, fieldId){
	var me = $("#" + id);
	var fileType = ",zip,";
	// 判断是否为图片格式的文件
	if (fileType.indexOf(","
			+ me.val().substring(me.val().lastIndexOf(".") + 1).toLowerCase()
			+ ",") < 0) {
		utils.dialog.alert('lms.fileCheckTypeZip');
		me.val("");
	} else {
		uploadFile(id, '', fieldId);
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
				//显示图片
				if(divId != null){
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

function checkData(){
	var courseuploadtype = $("#courseuploadtype").val();
	var validatescore = /^\d*(?:\.\d{0,2})?$/;
	if(courseuploadtype != "2"){
		var myfile = $("#fileScormName").val();
		if(myfile == ""){
			utils.dialog.alert(fmtLocalSCORM+fmtIsNotNull);
			return false;
		}else{
			var allowType=".zip";
            var fileType = (myfile.substring(myfile.lastIndexOf(".")+1)).toLowerCase();
            if(allowType.indexOf(fileType)==-1){
            	var msg = fmtCheckFileEndWith.replace("{0}", allowType);
              utils.dialog.alert(msg);
              return false;
            }
		}
	}
	var period = $("#period").val();
	var periodtext=period.replace(/(^\s*)|(\s*$)/g, "");
	if(period.replace(/^ +| +$/g,'')==''){
		utils.dialog.alert(fmtCourseTime+fmtIsNotNull);
		return false;
	}else if(!(validatescore.test(periodtext))){
		utils.dialog.alert("lms.validateCourseTimeMsg1");
		return false;
	}else if(period=="0.0" || period=="0"){
		utils.dialog.alert("lms.validateCourseTimeMsg2");
		return false;
	}
	
	var coursename = $("#coursename").val();
	if(coursename == ""){
		utils.dialog.alert(fmtCourseName+fmtIsNotNull);
		return false;
	}
	var flag = $("#flag").val();
	if(flag == ""){
		utils.dialog.alert(fmtCourseFirstTypeName+fmtIsNotNull);
		return false;
	}
	var coursesectype = $("#coursesectype").val();
	if(coursesectype == ""){
		utils.dialog.alert(fmtCourseSecTypeName+fmtIsNotNull);
		return false;
	}
	var courseuploadtype = $("#courseuploadtype").val();
	if(courseuploadtype == ""){
		utils.dialog.alert(fmtCourseUploadType+fmtIsNotNull);
		return false;
	}
	var imagename = $("#imagename").val();
	if(imagename == ""){
		utils.dialog.alert(fmtWebPic+fmtIsNotNull);
		return false;
	}
	var imagemobilename = $("#imagemobilename").val();
	if(imagemobilename == ""){
//		utils.dialog.alert(fmtMobilePic+fmtIsNotNull);
//		return false;
	}
	return true;
}

function packData(){
	var data = {};
	data["courseid"] = $("#courseid").val();
	data["coursename"] = $("#coursename").val();
	data["period"] = $("#period").val();
	data["flag"] = $("#flag").val();
	data["coursesectype"] = $("#coursesectype").val();
	data["courseuploadtype"] = $("#courseuploadtype").val();
	data["forpeople"] = $("#forpeople").val();
	data["keywords"] = $("#keywords").val();
	data["remarks"] = $("#remarks").val();
	data["fileScormName"] = $("#fileScormName").val();
	data["imagename"] = $("#imagename").val();
	data["imagemobilename"] = $("#imagemobilename").val();
	data["iffreecourse"] = $("input[name='ifFreeCourse']:checked").val();
	data["userpaper"] = $("input[name='userpaper']:checked").val();
	data["espkey"] = $("#espkey").val();
	data["imageId"] = $("#imageId").val();
	return data;
}

function clickFile(id){
	$("#"+id).click();
}