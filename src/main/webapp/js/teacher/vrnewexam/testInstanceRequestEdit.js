$(function(){
	$(".col-md-12").height($(window).height()*0.75);
	//考试类型
	var dataValue = [{"id":"0","text":fmtExamType0},{"id":"1","text":fmtExamType1},{"id":"2","text":fmtExamType2}];
	createSelect2("examType", dataValue, "");
	//及格分数 按比例
	var dataValue = [];
	dataValue.push({"id":"0.1","text":"10%"});
	dataValue.push({"id":"0.2","text":"20%"});
	dataValue.push({"id":"0.3","text":"30%"});
	dataValue.push({"id":"0.4","text":"40%"});
	dataValue.push({"id":"0.5","text":"50%"});
	dataValue.push({"id":"0.6","text":"60%"});
	dataValue.push({"id":"0.7","text":"70%"});
	dataValue.push({"id":"0.8","text":"80%"});
	dataValue.push({"id":"0.9","text":"90%"});
	createSelect2("percentage", dataValue, "0.1");
	
	if($("input[name='passScoreStatus']:checked").val() == 1){
		$("#percentageDiv").show();
		$("#definedpassscore").hide();
	}
	
	if($("input[name='examscorestatus']:checked").val() == 0){
		$("#examscore").show();
		$("#definedscore").hide();
	}else{
		$("#examscore").hide();
		$("#definedscore").show();
	}
	
	if (jQuery().datepicker) {
		$('#testStartDate').datepicker({
			rtl : Metronic.isRTL(),
			orientation : "right",
			format : 'yyyy/mm/dd',
			autoclose : true,
			language : 'zh-CN',
		}).on('changeDate',function(e){  
		    var startDate = e.date;  
		    $('#testEndDate').datepicker('setStartDate',startDate);  
		    var testEndDate = new Date($('#testEndDate').val());
		    var startDateForDate = new Date(startDate);
		    if(testEndDate < startDateForDate){
		    	$('#testEndDate').val('');
		    }
		});
		$("#testStartDate").datepicker("disable").attr("readonly","readonly");
		$('#testEndDate').datepicker({
			rtl : Metronic.isRTL(),
			orientation : "right",
			format : 'yyyy/mm/dd',
			autoclose : true,
			language : 'zh-CN',
		});
		$("#testEndDate").datepicker("disable").attr("readonly","readonly");
	}
//	$(".date-picker").datepicker("setDate", projectInfoist[0].inquiryAnswerDateHope);
	
	var id = $("#id").val();
	url = server + "/teacher/testinstancerequest/selectTestInstanceRequestById.do";
	var data = {};
	data["id"] = id;
	utils.ajax.post(url, data, function(result){
		if(id != ""){
			//初始化页面信息
			initPageInfo(result.data);
		}
	});
});

function initPageInfo(data){
	var examinfo = data.examinfo;
	//考试名称
	$("#formTable #mileStoneName").val(examinfo.mileStoneName);
	//考试类型
	$("#formTable #examType").select2('val', examinfo.examType);
	if(examinfo.examType == 0){
		//开始日期
		var testStartDate = examinfo.testStartDate;
		testStartDate = testStartDate.substring(0, 10);
		var testEndDate = examinfo.testEndDate;
		testEndDate = testEndDate.substring(0, 10);
		$("#testStartDate").datepicker("setDate", testStartDate);
		//截至日期
		$("#testEndDate").datepicker("setDate", testEndDate);
	}else{
		$("table tr").eq(1).hide();
	}
	//使用试卷
	$("#formTable #testTemplateId").val(examinfo.testTemplateId);
	//考试时长
	$("#formTable #examTime").val(examinfo.examTime);
	//考试分数
	$("#paperScore").val(examinfo.paperScore);
	//及格分数
	var passScoreStatus = examinfo.passScoreStatus;
	$("input[name='passScoreStatus']").parent().removeClass("checked");
	$("input[name='passScoreStatus'][value="+passScoreStatus+"]").attr("checked",true);
	$("input[name='passScoreStatus'][value="+passScoreStatus+"]").parent().addClass("checked");
	if(passScoreStatus == "1"){
		$("#percentageDiv").show();
		$("#definedpassscore").hide();
		$("#percentage").select2('val', examinfo.passScore);
	}else{
		$("#percentageDiv").hide();
		$("#definedpassscore").show();
		$("#definedpassscore").val(examinfo.passScore);
	}
	//考试分类
	$("#testName").val(examinfo.testName);
	//考核目标
	$("#examTarget").val(examinfo.examTarget);
	//考核种类
	$("#examKind").val(examinfo.examKind);
	$("#remark").val(examinfo.description);
	$("#papername").val(examinfo.papername);
	$("#imagename").val(examinfo.imageName);
	$("#imageFileDiv img").attr("src", examinfo.imagepath);
	$("#testTemplateMileStoneId").val(examinfo.testTemplateMileStoneId);
	
	if($("#imagename").val() != ""){
		$("#removeImgBtn").css("display", "block");
	}
	$("#formTable tbody tr").eq(2).hide();
}

//考试类型
$("#examType").on("change", function(){
	var examtype = $(this).val();
	if(examtype != "0"){
		$("table tr").eq(1).hide();
	}else{
		$("table tr").eq(1).show();
	}
});

//保存
$("#saveBtn").on("click", function(){
	if(checkData()){
		var data = packData();
//		console.info(data)
		var url = server + '/teacher/testinstancerequest/saveTestInstanceRequest.do';
		utils.ajax.post(url, data, function(result){
			if(result.flag == "1" && result.data != 0){
				utils.dialog.alert("lms0006", function(){
					var dataStr = "";
					window.location.href= server+"/pages/teacher/vrnewexam/testInstanceRequestList.jsp?"+dataStr;
				});
			}else{
				utils.dialog.alert("lms.saveFail");
			}
		});
		
	}
});

//关闭
$("#cancelBtn").on("click", function(){
	var dataStr = "";
	window.location.href= server+"/pages/teacher/vrnewexam/testInstanceRequestList.jsp?"+dataStr;
});


function checkData(){
	var validatescore=/^\d*(?:\.\d{0,2})?$/;
    var validate=/^[@#%&*()~￥^$]$/;
    
	var mileStoneName = $("#mileStoneName").val();
	if(mileStoneName == ""){
		utils.dialog.alert("lms.examNameIsNotNullMsg");
		return false;
		utils.dialog.alert("lms.examNameToolongMsg");
        return false;
	}else if(validate.test(mileStoneName)){
		utils.dialog.alert("lms.examNameValidateMsg");
	    return false;
	}
	var examType = $("#examType").val();
	if(examType == ""){
		utils.dialog.alert("lms.examTypeIsNotNullMsg");
		return false;
	}
	if(examType == 0){
		var testStartDate = $("#testStartDate").val();
		if(testStartDate == ""){
			utils.dialog.alert("lms.startDateIsNotNullMsg");
			return false;
		}
		var testEndDate = $("#testEndDate").val();
		if(testEndDate == ""){
			utils.dialog.alert("lms.endDateIsNotNullMsg");
			return false;
		}
		if(Date.parse(testStartDate) > Date.parse(testEndDate)){
			utils.dialog.alert("lms.startDateValidateMsg");
			return false;
		}
	}
	var examTime = $("#examTime").val();
	if(examTime.replace(/^ +| +$/g,'') == ""){
		utils.dialog.alert("lms.testTimeIsNotNullMsg");
		return false;
	}else if(examTime < 0){
		utils.dialog.alert("lms.testTimeAboveZeroMsg");
        return false;
	}else if(examTime.search("^-?\\d+$")!=0){
		utils.dialog.alert("lms.testTimeValidateIntegerMsg");
        return false;
	}
	
	var passScoreStatus = $("input[name='passScoreStatus']:checked").val();
	if(passScoreStatus == "1"){
		var percentage = $("#percentage").val();
		if(percentage == ""){
			utils.dialog.alert("lms.passScoreIsNotNullMsg");
			return false;
		}
	}else{
		var definedpassscore = $("#definedpassscore").val();
		if(definedpassscore.replace(/^ +| +$/g,'') == ""){
			utils.dialog.alert("lms.passScoreIsNotNullMsg");
			return false;
		}else if(!(validatescore.test(definedpassscore))){
			utils.dialog.alert("lms.passScoreValidateIntegerMsg");
            return false;
		}else if(definedpassscore < 0){
			utils.dialog.alert("lms.passScoreAboveZeroMsg");
            return false;
		}
		var paperScore = $("#paperScore").val();
		if(parseFloat(paperScore) < parseFloat(definedpassscore)){
			utils.dialog.alert("lms.passScoreValidateMsg");
            return false;
		}
	}
	return true;
}

function packData(){
	var data = {};
	//考试名称
	var mileStoneName = $("#mileStoneName").val();
	//考试类型
	var examType = $("#examType").val();
	data["mileStoneName"] = mileStoneName;
	data["examType"] = examType;
	if(examType == 0){
		//开始日期
		var testStartDate = $("#testStartDate").val();
		//截至日期
		var testEndDate = $("#testEndDate").val();
		data["testStartDate"] = testStartDate;
		data["testEndDate"] = testEndDate;
	}
	//使用试卷
	var papername = $("#papername").val();
	//考试时长
	var examTime = $("#examTime").val();
	//
	data["examTime"] = examTime;
	var paperScore = $("#paperScore").val();
	data["paperScore"] = paperScore;
	
	var passScoreStatus = $("input[name='passScoreStatus']:checked").val();
	data["passScoreStatus"] = passScoreStatus;
	if(passScoreStatus == "1"){
		var percentage = $("#percentage").val();
		data["passScore"] = percentage;
	}else{
		var definedpassscore = $("#definedpassscore").val();
		data["passScore"] = definedpassscore;
	}
	//考试分类
	var testTemplateId = $("#testTemplateId").val();
	data["testTemplateId"] = testTemplateId;
	//考核目标
	var examTarget = $("#examTarget").val();
	data["examTarget"] = examTarget;
	//考核种类
	var examKind = $("#examKind").val();
	data["examKind"] = examKind;
	var id = $("#id").val();
	if(id == ""){
		id = "0";
	}
	data["id"] = id;
	var imagename = $("#imagename").val();
	data["imageName"] = imagename;
	var remark = $("#remark").val();
	data["description"] = remark;
	var testTemplateMileStoneId = $("#testTemplateMileStoneId").val();
	data["testTemplateMileStoneId"] = testTemplateMileStoneId;
	return data;
}
function createSelect2(key,dataVal,val){
	if(dataVal!=""){
    	var select2 = $("#" + key);
    	select2.val("");
    	select2.empty();
    	select2.select2({
			placeholder : fmtPleaseSelect,
			data:dataVal,
			allowClear : true,
			formatResult : this.format,
			formatSelection : this.format,
			escapeMarkup : function(m) {
				return m;
			}
		});
    	if(val != ""){
            select2.select2("val", val);	
    	}
	}
}

$("#uploadImgBtn").on("click", function(){
	$("#imageFileUpload").click();
})

$("input[name='examscorestatus']").on("click", function(){
	if($(this).val() == 0){
		$("#examscore").show();
		$("#definedscore").hide();
	}else{
		$("#examscore").hide();
		$("#definedscore").show();
	}
});

$("input[name='passScoreStatus']").on("click", function(){
	if($(this).val() == 1){
		$("#percentageDiv").show();
		$("#definedpassscore").hide();
	}else{
		$("#percentageDiv").hide();
		$("#definedpassscore").show();
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

function uploadFile(id, divId, fieldId) {
	var url = server + '/auth/common/uploadImageToTemp.do';
	var data = {};
	data["tempName"] = $("#imagename").val();
	Metronic.blockUI({
		animate : true,
		overlayColor : 'none'
	});
	// 执行上传文件操作的函数
	$.ajaxFileUpload({
		// 处理文件上传操作的服务器端地址
		url : url,
		data : data,
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
				var pathD = data.data.imagesSrc;
				for (var i = 0; i < pathD.length; i++) {
					html += '<div class="d1"><img style="width:170px;height:170px;" src="' + pathD[i] + '"/></div>';
				}
				if (html != "") {
					$("#" + divId).empty();
					$("#" + divId).append(html);
				}
				$("#"+fieldId).val(data.data.fileName);
				if($("#"+fieldId).val() != ""){
					$("#removeImgBtn").css("display", "block");
				}
			}
		},
		error : function(data, status, e) { // 服务器响应失败时的处理函数
			Metronic.unblockUI();
			utils.dialog.toastr(data.msg, "lms0002", "error");
		}
	});
}

$("#removeImgBtn").on("click", function(){
	$(this).css("display", "none");
	var tempFileName = $("#imagename").val();
	var url = server + "/auth/common/deleteTempImage.do";
	var data = {};
	data["tempFileName"] = tempFileName;
	utils.ajax.post(url, data, function(result){
		if(result.flg == 1){
			$("#imagename").val("");
		}
	});
	var imgPath = server+'/images/noimage.jpg';
	$("#imageFileDiv").html('<div class="d1"><img src="'+imgPath+'" style="width:170px;height:170px;" /></div>');
	var imagename = $("#imagename").val();
//	if(imagename.indexOf("temp_") == -1){
//		$("#imagename").val("delete_"+imagename);
//	}else{
		$("#imagename").val("");
//	}
});

$("#selectPaperBtn").on("click", function(){
	var url = server + '/pages/teacher/vrnewexam/paperList_dlg.jsp';
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title: fmtTestpaperList,
			clear:"true",
			init:function(){
//				initEdit(id, content);
			},
			css:{"width":"large"},
			callback:function(){
			}
	};
	utils.dialog.openDialog(options);
});

function changeSelectPaper(testTemplateId, mileStoneName, testTemplateMileStoneId, paperScore, time, testName){
	$("#formTable #papername").val(mileStoneName);
    $("#formTable #testTemplateId").val(testTemplateId);
    $("#formTable #testTemplateMileStoneId").val(testTemplateMileStoneId);
    if(paperScore == null || paperScore == "" || paperScore == "undefined"){
    	$("#formTable #paperScore").val(0);
    }else{
    	$("#formTable #paperScore").val(paperScore);
    }
    $("#formTable #examTime").val(time);
    $("#formTable #testName").val(testName);
}