$(function(){
	$(".col-md-12").height($(window).height()*0.75);
	//考试类型
	var dataValue = [{"id":"0","text":fmtExamType0},{"id":"1","text":fmtExamType1},{"id":"2","text":fmtExamType2}];
	createSelect2("examtype", dataValue, "");
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
	
	if($("input[name='passscorestatus']:checked").val() == 1){
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
		$('#starttime').datepicker({
			rtl : Metronic.isRTL(),
			orientation : "right",
			format : 'yyyy/mm/dd',
			autoclose : true,
			language : 'zh-CN',
		}).on('changeDate',function(e){  
		    var startDate = e.date;  
		    $('#endtime').datepicker('setStartDate',startDate);  
		    var testEndDate = new Date($('#endtime').val());
		    var startDateForDate = new Date(startDate);
		    if(testEndDate < startDateForDate){
		    	$('#endtime').val('');
		    }
		});
		$("#starttime").datepicker("disable").attr("readonly","readonly");
		$('#endtime').datepicker({
			rtl : Metronic.isRTL(),
			orientation : "right",
			format : 'yyyy/mm/dd',
			autoclose : true,
			language : 'zh-CN',
		});
		$("#endtime").datepicker("disable").attr("readonly","readonly");
	}
//	$(".date-picker").datepicker("setDate", projectInfoist[0].inquiryAnswerDateHope);
	
	var examid = $("#examid").val();
	url = server + "/teacher/tnewexaminfo/selectNewExamInfoById.do";
	var data = {};
	data["examid"] = examid;
	utils.ajax.post(url, data, function(result){
		if(examid != ""){
			//初始化页面信息
			initPageInfo(result.data);
		}
	});
});

function initPageInfo(data){
	var examinfo = data.examinfo;
	//考试名称
	$("#formTable #examname").val(examinfo.examname);
	//考试类型
	$("#formTable #examtype").select2('val', examinfo.examtype);
	if(examinfo.examtype == 0){
		//开始日期
		$("#starttime").datepicker("setDate", examinfo.starttime);
		//截至日期
		$("#endtime").datepicker("setDate", examinfo.endtime);
	}else{
		$("table tr").eq(1).hide();
	}
	//使用试卷
	$("#formTable #papername").val(examinfo.papername);
	//考试时长
	$("#formTable #examtime").val(examinfo.examtime);
	//考试分数
	var examscorestatus = examinfo.examscorestatus;
	$("input[name='examscorestatus']").parent().removeClass("checked");
	$("input[name='examscorestatus'][value="+examscorestatus+"]").attr("checked",true);
	$("input[name='examscorestatus'][value="+examscorestatus+"]").parent().addClass("checked");
	if(examscorestatus == "1"){
		$("#examscore").hide();
		$("#definedscore").show();
	}else{
		$("#examscore").show();
		$("#definedscore").hide();
	}
	$("#definedscore").val(examinfo.examsocre);
	$("#examscore").val(examinfo.score);
	//及格分数
	var passscorestatus = examinfo.passscorestatus;
	$("input[name='passscorestatus']").parent().removeClass("checked");
	$("input[name='passscorestatus'][value="+passscorestatus+"]").attr("checked",true);
	$("input[name='passscorestatus'][value="+passscorestatus+"]").parent().addClass("checked");
	if(passscorestatus == "1"){
		$("#percentageDiv").show();
		$("#definedpassscore").hide();
		$("#percentage").select2('val', examinfo.passscore);
	}else{
		$("#percentageDiv").hide();
		$("#definedpassscore").show();
		$("#definedpassscore").val(examinfo.passscore);
	}
	//允许查看答卷
	var showpoint = examinfo.showpoint;
	$("input[name='showpoint']").parent().removeClass("checked");
	$("input[name='showpoint'][value="+showpoint+"]").attr("checked",true);
	$("input[name='showpoint'][value="+showpoint+"]").parent().addClass("checked");
	//考试分类
	$("#categoryid").val(examinfo.examcategroy);
	$("#categoryname").val(examinfo.cagetoryname);
	//选择试卷
	$("#paperid").val(examinfo.userpaper);
	//考核目标
	$("#examKhxm").val(examinfo.examkhxm);
	//考核种类
	$("#examKhzl").val(examinfo.examkhzl);
//	$("#examid").val(examinfo.examid);
	$("#remark").val(examinfo.remark);
	$("#papername").val(examinfo.papername);
	$("#imagename").val(examinfo.imgName);
	$("#imageFileDiv img").attr("src", examinfo.imagepath);
	
	if($("#imagename").val() != ""){
		$("#removeImgBtn").css("display", "block");
	}
}

//考试类型
$("#examtype").on("change", function(){
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
		var url = server + '/teacher/tnewexaminfo/saveNewExamInfo.do';
		utils.ajax.post(url, data, function(result){
			if(result.flag == "1" && result.data != 0){
				utils.dialog.alert("lms0006", function(){
					var dataStr = "";
					window.location.href= server+"/pages/teacher/newexam/newExamInfoList.jsp?"+dataStr;
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
	window.location.href= server+"/pages/teacher/newexam/newExamInfoList.jsp?"+dataStr;
});


function checkData(){
	var validatescore=/^\d*(?:\.\d{0,2})?$/;
    var validate=/^[@#%&*()~￥^$]$/;
    
	var examname = $("#examname").val();
	if(examname == ""){
		utils.dialog.alert("lms.examNameIsNotNullMsg");
		return false;
	}else if(examname.length>100 ){
		utils.dialog.alert("lms.examNameToolongMsg");
        return false;
	}else if(validate.test(examname)){
		utils.dialog.alert("lms.examNameValidateMsg");
	    return false;
	}
	var examtype = $("#examtype").val();
	if(examtype == ""){
		utils.dialog.alert("lms.examTypeIsNotNullMsg");
		return false;
	}
	if(examtype == 0){
		var starttime = $("#starttime").val();
		if(starttime == ""){
			utils.dialog.alert("lms.startDateIsNotNullMsg");
			return false;
		}
		var endtime = $("#endtime").val();
		if(endtime == ""){
			utils.dialog.alert("lms.endDateIsNotNullMsg");
			return false;
		}
		if(Date.parse(starttime) > Date.parse(endtime)){
			utils.dialog.alert("lms.startDateValidateMsg");
			return false;
		}
	}
	var paperid = $("#paperid").val();
	if(paperid == ""){
		utils.dialog.alert("lms.usePaperIsNotNullMsg");
		return false;
	}
	var examtime = $("#examtime").val();
	if(examtime.replace(/^ +| +$/g,'') == ""){
		utils.dialog.alert("lms.testTimeIsNotNullMsg");
		return false;
	}else if(examtime < 0){
		utils.dialog.alert("lms.testTimeAboveZeroMsg");
        return false;
	}else if(examtime.search("^-?\\d+$")!=0){
		utils.dialog.alert("lms.testTimeValidateIntegerMsg");
        return false;
	}
	
	var examscorestatus = $("input[name='examscorestatus']:checked").val();
	if(examscorestatus == "1"){
		var definedscore = $("#definedscore").val();
		if(definedscore.replace(/^ +| +$/g,'') == ""){
			utils.dialog.alert("lms.testScoreIsNotNullMsg");
			return false;
		}else if(definedscore<=0){
			utils.dialog.alert("lms.testScoreAboveZeroMsg");
            return false;
		}else if(!(validatescore.test(definedscore))){
			utils.dialog.alert("lms.testScoreValidateNumberMsg");
            return false;
		}
	}
	var passscorestatus = $("input[name='passscorestatus']:checked").val();
	if(passscorestatus == "1"){
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
		var examscore = $("#examscore").val();
		if(examscorestatus == "1"){
			examscore = $("#definedscore").val();
		}
		if(parseFloat(examscore) < parseFloat(definedpassscore)){
			utils.dialog.alert("lms.passScoreValidateMsg");
            return false;
		}
	}
	return true;
}

function packData(){
	var data = {};
	//考试名称
	var examname = $("#examname").val();
	//考试类型
	var examtype = $("#examtype").val();
	data["examname"] = examname;
	data["examtype"] = examtype;
	if(examtype == 0){
		//开始日期
		var starttime = $("#starttime").val();
		//截至日期
		var endtime = $("#endtime").val();
		data["starttime"] = starttime;
		data["endtime"] = endtime;
	}
	//使用试卷
	var papername = $("#papername").val();
	//考试时长
	var examtime = $("#examtime").val();
	//
	var examscorestatus = $("input[name='examscorestatus']:checked").val();
	data["papername"] = papername;
	data["examtime"] = examtime;
	data["examscorestatus"] = examscorestatus;
	if(examscorestatus == "1"){
		var definedscore = $("#definedscore").val();
		//试卷分数
		data["examsocre"] = definedscore;
	}else{
		var examscore = $("#examscore").val();
		data["examsocre"] = examscore;
	}
	
	var passscorestatus = $("input[name='passscorestatus']:checked").val();
	data["passscorestatus"] = passscorestatus;
	if(passscorestatus == "1"){
		var percentage = $("#percentage").val();
		data["passscore"] = percentage;
	}else{
		var definedpassscore = $("#definedpassscore").val();
		data["passscore"] = definedpassscore;
	}
	var showpoint = $("input[name='showpoint']:checked").val();
	data["showpoint"] = showpoint;
	//考试分类
	var categoryid = $("#categoryid").val();
	data["examcategroy"] = categoryid;
	//选择试卷
	var paperid = $("#paperid").val();
	data["userpaper"] = paperid;
	//考核目标
	var examKhxm = $("#examKhxm").val();
	data["examkhxm"] = examKhxm;
	//考核种类
	var examKhzl = $("#examKhzl").val();
	data["examkhzl"] = examKhzl;
	var examid = $("#examid").val();
	data["examid"] = examid;
	var imagename = $("#imagename").val();
	data["imagename"] = imagename;
	var remark = $("#remark").val();
	data["remark"] = remark;
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

$("input[name='passscorestatus']").on("click", function(){
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
	var url = server + '/pages/teacher/newexam/paperList_dlg.jsp';
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

function changeSelectPaper(paperid, papername, categoryid, cagetoryname, score){
	$("#formTable #papername").val(papername);
    $("#formTable #paperid").val(paperid);
    $("#formTable #categoryid").val(categoryid);
    $("#formTable #categoryname").val(cagetoryname);
    $("#formTable #examscore").val(score);
}