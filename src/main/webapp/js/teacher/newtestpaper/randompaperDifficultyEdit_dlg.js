function initEdit(paperid, typeid, id){
	$("#paperid").val(paperid);
	$("#typeid").val(typeid);
	$("#id").val(id);
	
	//知识点
	var dataValue = [{"id":"","text":""}];
	createSelect2("knowledgepoint", dataValue, "");
	url = server + "/teacher/trandompaperdifficultysum/selectRandompaperDifficultySumById.do";
	var data = {};
	data["id"] = id;
	data["paperid"] = paperid;
	data["typeid"] = typeid;
	utils.ajax.post(url, data, function(result){
		difficultyMap = result.data.difficultyMap;
		if(id != ""){
			//初始化页面信息
			initPageInfo(result.data);
		}else{
			//试题分类
			createSelect2("categoryid", result.data.questionCategoryList, "");
			//设置方式
			if($("input[name='addtype']:checked").val() == "1"){
				$(".setAll").show();
				$(".setDetail").hide();
			}
		}
	});
}

function initPageInfo(data){
	var randompaperDifficultySum = data.randompaperDifficultySum;
	//试题分类
	createSelect2("categoryid", data.questionCategoryList, randompaperDifficultySum["categoryid"]);
	//知识点
	createSelect2("knowledgepoint", data.knowledgepointList, randompaperDifficultySum["knowledgepoint"]);
	//设置方式
	var addtype = randompaperDifficultySum.addtype;
	$("input[name='addtype']").parent().removeClass("checked");
	$("input[name='addtype'][value="+addtype+"]").attr("checked",true);
	$("input[name='addtype'][value="+addtype+"]").parent().addClass("checked");
	//查询各难度试题个数
	getQuestionNum();
	
	var numData = data.numData;
	if(addtype == "1"){
		$(".setAll").show();
		$(".setDetail").hide();
		if(numData["summaryNum"] != null){
			$("#questionSummaryNum").val(numData["summaryNum"].totlequestions);
			$("#questionSummaryScore").val(numData["summaryNum"].titlescore);
		}
	}else{
		$(".setAll").hide();
		$(".setDetail").show();
		if(numData["difficulty1"] != null){
			$("#questionNum1").val(numData["difficulty1"].totlequestions);
			$("#questionScore1").val(numData["difficulty1"].titlescore);
		}
		if(numData["difficulty2"] != null){
			$("#questionNum2").val(numData["difficulty2"].totlequestions);
			$("#questionScore2").val(numData["difficulty2"].titlescore);
		}
		if(numData["difficulty3"] != null){
			$("#questionNum3").val(numData["difficulty3"].totlequestions);
			$("#questionScore3").val(numData["difficulty3"].titlescore);
		}
	}
	
}

//试题分类 ,知识点 联动
$("#categoryid").on("change", function(){
	getQuestionNum();
	var categoryid = $(this).val();
	//知识点
	var url = server + "/auth/common/selectOptionKnowledgepointList.do";
	var data = {"categoryid" : categoryid};
	utils.ajax.post(url, data, function(result){
		if(result.data != null && result.data.length == 0 || categoryid == ""){
			var dataValue = [{"id":"","text":""}];
			createSelect2("knowledgepoint", dataValue, "");
		}else{
			createSelect2("knowledgepoint", result.data, "");
		}
	});
});

$("#knowledgepoint").on("change", function(){
	getQuestionNum();
});

function getQuestionNum(){
	$("#questionSummaryNum").val(0);
	$("#questionSummaryScore").val(0);
	$("#questionNum1").val(0);
	$("#questionScore1").val(0);
	$("#questionNum2").val(0);
	$("#questionScore2").val(0);
	$("#questionNum3").val(0);
	$("#questionScore3").val(0);

	var typeid = $("#typeid").val();
	var categoryid = $("#categoryid").val();
	var knowledgepoint = $("#knowledgepoint").val();
	var paperid = $("#paperid").val();
	var difficultydetailid = $("#id").val();
	var url = server + "/auth/common/selectQuestionNumByType.do";
	var data = {"typeid" : typeid, "pointsid" : categoryid, "knowlegdePointsid" : knowledgepoint, "paperid" : paperid, "difficultydetailid" : difficultydetailid};
	utils.ajax.post(url, data, function(result){
		var resultData = result.data;
		$("#questionSummaryNumStr").html(resultData.totalCount);
		$("#questionNumStr1").html(resultData.difficulty1);
		$("#questionNumStr2").html(resultData.difficulty2);
		$("#questionNumStr3").html(resultData.difficulty3);
	});
}

//设置类型
$("input[name='addtype']").on("click", function(){
	var addtype = $(this).val();
	if(addtype == "1"){
		$(".setAll").show();
		$(".setDetail").hide();
	}else{
		$(".setAll").hide();
		$(".setDetail").show();
	}
});

//分数相同
$("#sameScore").on("click", function(){
	if($(this).attr("checked")){
		$("#questionScore2").attr("disabled", true);
		$("#questionScore3").attr("disabled", true);
		var val = $("#questionScore1").val(); 
		$("#questionScore2").val(val);
		$("#questionScore3").val(val);
	}else{
		$("#questionScore2").attr("disabled", false);
		$("#questionScore3").attr("disabled", false);
	}
});

//
$("#questionScore1").on("keyup", function(){
	if($("#sameScore").attr("checked")){
		var val = $(this).val(); 
		$("#questionScore2").val(val);
		$("#questionScore3").val(val);
	}
});


//保存
$("#saveBtn").on("click", function(){
	if(checkData()){
		var data = packData();
//		console.info(data)
		var url = server + '/teacher/trandompaperdifficultysum/saveRandompaperDifficultySum.do';
		utils.ajax.postJson(url, data, function(result){
			if(result.flag == "1" && result.data != 0){
				utils.dialog.alert("lms0006", function(){
					winPar.refreshInit();
					$('#portlet-upload-dlg').modal('hide');
				});
			}else{
				utils.dialog.alert("lms.saveFail");
			}
		});
		
	}
});

function checkData(){
	var categoryid = $("#categoryid").val();
	if(categoryid == ""){
		utils.dialog.alert("lms.questionCategoryIsNotNullMsg");
		return false;
	}
	var addtype = $("input[name='addtype']:checked").val();
	if(addtype == "1"){
		var questionSummaryNum = parseInt($("#questionSummaryNum").val());
		var questionSummaryNumStr = parseInt($("#questionSummaryNumStr").html());
		if(questionSummaryNum == 0){
			utils.dialog.alert("lms.questionSizeAboveZeroMsg");
			return false;
		}
		if(questionSummaryNum > questionSummaryNumStr){
			utils.dialog.alert("lms.questionSizeBelowSystemAmountMsg");
			return false;
		}else{
			var questionSummaryScore = $("#questionSummaryScore").val();
			if(questionSummaryScore <= 0){
				utils.dialog.alert("lms.questionScoreAboveZeroMsg");
				return false;
			}
		}
	}else{
		var questionNum1 = parseInt($("#questionNum1").val());
		var questionNumStr1 = parseInt($("#questionNumStr1").html());
		var questionNum2 = parseInt($("#questionNum2").val());
		var questionNumStr2 = parseInt($("#questionNumStr2").html());
		var questionNum3 = parseInt($("#questionNum3").val());
		var questionNumStr3 = parseInt($("#questionNumStr3").html());
		if(questionNum1 == 0 && questionNum2 == 0 && questionNum3 == 0){
			utils.dialog.alert("lms.questionSizeAboveZeroMsg");
			return false;
		}
		if(questionNum1 > questionNumStr1 || questionNum2 > questionNumStr2 || questionNum3 > questionNumStr3){
			utils.dialog.alert("lms.questionSizeBelowSystemAmountMsg");
			return false;
		}else{
			var questionScore1 = $("#questionScore1").val();
			var questionScore2 = $("#questionScore2").val();
			var questionScore3 = $("#questionScore3").val();
			if((questionNum1 > 0 && questionScore1 <= 0) || (questionNum2 > 0 && questionScore2 <= 0) || (questionNum3 > 0 && questionScore3 <= 0)){
				utils.dialog.alert("lms.questionScoreAboveZeroMsg");
				return false;
			}
		}
	}
	return true;
}

function packData(){
	var data = {};
	var randompaperDifficultySum = {};
	var randompaperDifficultyList = [];
	
	var id = $("#id").val();
	var paperid = $("#paperid").val();
	var typeid = $("#typeid").val();
	var categoryid = $("#categoryid").val();
	var addtype = $("input[name='addtype']:checked").val();
	var knowledgepoint = $("#knowledgepoint").val();
	var remark = "";
	
	randompaperDifficultySum["id"] = id;
	randompaperDifficultySum["paperid"] = paperid;
	randompaperDifficultySum["typeid"] = typeid;
	randompaperDifficultySum["categoryid"] = categoryid;
	randompaperDifficultySum["addtype"] = addtype;
	randompaperDifficultySum["knowledgepoint"] = knowledgepoint;
	
	if(addtype == "1"){
		var questionSummaryNum = $("#questionSummaryNum").val();
		var questionSummaryScore = $("#questionSummaryScore").val();
		var randompaperDifficulty = {};
		randompaperDifficulty["totlequestions"] = questionSummaryNum;
		randompaperDifficulty["titlescore"] = questionSummaryScore;
		randompaperDifficulty["difficulty"] = "0";
		randompaperDifficultyList.push(randompaperDifficulty);
		remark = fmtAllTypesRemarkInfo.replace("{0}", questionSummaryNum).replace("{1}", questionSummaryScore);
	}else{
		var questionNum1 = $("#questionNum1").val();
		var questionNum2 = $("#questionNum2").val();
		var questionNum3 = $("#questionNum3").val();
		var questionScore1 = $("#questionScore1").val();
		var questionScore2 = $("#questionScore2").val();
		var questionScore3 = $("#questionScore3").val();
		if(questionNum1 > 0){
			var randompaperDifficulty = {};
			randompaperDifficulty["totlequestions"] = questionNum1;
			randompaperDifficulty["titlescore"] = questionScore1;
			randompaperDifficulty["difficulty"] = difficultyMap["简单"];
			randompaperDifficultyList.push(randompaperDifficulty);
//			remark = "简单题:"+questionNum1+"个，每个"+questionScore1+"分 ";
			remark += fmtEasyQuestionRemarkInfo.replace("{0}", questionNum1).replace("{1}", questionScore1);
		}
		if(questionNum2 > 0){
			var randompaperDifficulty = {};
			randompaperDifficulty["totlequestions"] = questionNum2;
			randompaperDifficulty["titlescore"] = questionScore2;
			randompaperDifficulty["difficulty"] = difficultyMap["中等"];
			randompaperDifficultyList.push(randompaperDifficulty);
//			remark += "中等题:"+questionNum2+"个，每个"+questionScore2+"分 ";
			remark += fmtMediumQuestionRemarkInfo.replace("{0}", questionNum2).replace("{1}", questionScore2);
		}
		if(questionNum3 > 0){
			var randompaperDifficulty = {};
			randompaperDifficulty["totlequestions"] = questionNum3;
			randompaperDifficulty["titlescore"] = questionScore3;
			randompaperDifficulty["difficulty"] = difficultyMap["困难"];
			randompaperDifficultyList.push(randompaperDifficulty);
//			remark += "困难题:"+questionNum3+"个，每个"+questionScore3+"分 ";
			remark += fmtDifficultyQuestionRemarkInfo.replace("{0}", questionNum3).replace("{1}", questionScore3);
		}
	}
	randompaperDifficultySum["remark"] = remark;
	data["randompaperDifficultySum"] = randompaperDifficultySum;
	data["randompaperDifficultyList"] = randompaperDifficultyList;
	return data;
}

function checkNum(obj){
	var val = $(obj).val();
	var re = /^[0-9]+$/;
	if(!re.test(val)){
		utils.dialog.alert("lms.checkNum");
		$(obj).val(0);
	}
}