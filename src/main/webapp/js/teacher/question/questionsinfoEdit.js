$(function(){
	$("#portlet-upload-dlg .col-md-12").height($(window).height()*0.75);
	//知识点
	var dataValue = [{"id":"","text":""}];
	createSelect2("point", dataValue, "");
	//判断题
	var judgeHtml = '<input type="radio" name="judgeAnswer" id="judgeAnswer1" value="Y" />'+fmtCorrect+' &nbsp;';
		judgeHtml += '<input type="radio" name="judgeAnswer" id="judgeAnswer2" value="N" />'+fmtFault+'&nbsp;';
	$("#judgeContent").html(judgeHtml);
	var questionsid = $("#questionsid").val();
	url = server + "/teacher/tquestioninfo/selectQuestionsinfoById.do";
	var data = {};
	data["questionsid"] = questionsid;
	utils.ajax.post(url, data, function(result){
		//console.info(result)
		if(questionsid != ""){
			var quesioninfo = result.data.quesioninfo;
			var questionOptionsList = result.data.questionOptionsList;
			//题型
			createSelect2("typeid", result.data.questionstypeList, quesioninfo["typeid"]);
			$("#typeid").attr("disabled", true);
			//试题难度
			createSelect2("difficultyId", result.data.questionDifficultyList, quesioninfo["difficulty"]);
			//试题分类
			createSelect2("categoryid", result.data.questionCategoryList, quesioninfo["pointsid"]);
			if(quesioninfo["knowledgepoint"] != null){
				//知识点
				createSelect2("point", result.data.knowledgepointList, quesioninfo["knowledgepoint"]);
			}else{
				//知识点
				createSelect2("point", result.data.knowledgepointList, "");
			}
			//题干内容
			changeContent("stemContent", quesioninfo["content"]);
			//试题解析
			changeContent("analysisContent", quesioninfo["remarks"]);
			//初始化页面信息
			initPageInfo(quesioninfo["typeid"], result.data);
		}else{
			//题型
			createSelect2("typeid", result.data.questionstypeList, "");
			//试题难度
			createSelect2("difficultyId", result.data.questionDifficultyList, "");
			//试题分类
			createSelect2("categoryid", result.data.questionCategoryList, "");
		}
	});
	
});

//题型改变
$("#typeid").on("change", function(){
	var typeid = $(this).val();
	initType(typeid);
});

function initType(typeid){
	if(typeid == "T00000000000001"){//填空题
		$("#questionBlank").css("display", "block");
		$("#questionChoice").css("display", "none");
		$("#questionJudge").css("display", "none");
		$("#questionEssay").css("display", "none");
	}else if(typeid == "T00000000000002" || typeid == "T00000000000003"){//选择题
		$("#questionBlank").css("display", "none");
		$("#questionChoice").css("display", "block");
		$("#questionJudge").css("display", "none");
		$("#questionEssay").css("display", "none");
		if(typeid == "T00000000000002"){//单选
			$("#optionTable tr").find('td:eq(0)').show();
			$("#optionTable tr").find('td:eq(1)').hide();
		}else if(typeid == "T00000000000003"){//多选
			$("#optionTable tr").find('td:eq(0)').hide();
			$("#optionTable tr").find('td:eq(1)').show();
		}
	}else if(typeid == "T00000000000004"){//判断题
		$("#questionBlank").css("display", "none");
		$("#questionChoice").css("display", "none");
		$("#questionJudge").css("display", "block");
		$("#questionEssay").css("display", "none");
	}else if(typeid == "T00000000000005"){//问答题
		$("#questionBlank").css("display", "none");
		$("#questionChoice").css("display", "none");
		$("#questionJudge").css("display", "none");
		$("#questionEssay").css("display", "block");
	}else if(typeid == "T00000000000006"){//操作题
//		$("questionBlank").css("display", "block");
	}
}

function initPageInfo(typeid, data){
	initType(typeid);
	var quesioninfo = data.quesioninfo;
	var answer = quesioninfo.answer;
	if(typeid == "T00000000000001"){//填空题
		var answerArr = answer.split("^");
		for(var i=0;i<answerArr.length;i++){
			if(i != 0){
				addAnswer();
			}
			$("input[name='quesBlanks']").eq(i).val(answerArr[i]);
		}
	}else if(typeid == "T00000000000002" || typeid == "T00000000000003"){//选择题
		var questionOptionsList = data.questionOptionsList;
		for(var i=0;i<questionOptionsList.length;i++){
			changeContent("optionContent", questionOptionsList[i]["OPTIONSCONTENT"]);
			answerArr = answer.split(",");
			for(var j=0;j<answerArr.length;j++){
				if(answerArr[j] == questionOptionsList[i]["optionsid"]){
					if(typeid == "T00000000000002"){//单选
						$("#optionTable tr").eq(i).find('td:eq(0) input').attr("checked",true);
					}else if(typeid == "T00000000000003"){//多选
						$("#optionTable tr").eq(i).find('td:eq(1) input').attr("checked",true);
					}
				}
			}
		}
	}else if(typeid == "T00000000000004"){//判断题
		$("input[name='judgeAnswer'][value="+answer+"]").attr("checked",true); 
	}else if(typeid == "T00000000000005"){//问答题
		changeContent("essayContent", answer);
	}else if(typeid == "T00000000000006"){//操作题
//		$("questionBlank").css("display", "block");
	}
}

//试题分类 ,知识点 联动
$("#categoryid").on("change", function(){
	var categoryid = $(this).val();
	//知识点
	var url = server + "/auth/common/selectOptionKnowledgepointList.do";
	var data = {"categoryid" : categoryid};
	utils.ajax.post(url, data, function(result){
		if(result.data.length == 0 || categoryid == ""){
			var dataValue = [{"id":"","text":""}];
			createSelect2("point", dataValue, "");
		}else{
			createSelect2("point", result.data, "");
		}
	});
});

//保存
$("#saveBtn").on("click", function(){
	if(checkData()){
		var data = packData();
		var url = server + '/teacher/tquestioninfo/saveQuestionsinfo.do';
		utils.ajax.postJson(url, data, function(result){
			if(result.flag == "1" && result.data != 0){
				utils.dialog.alert("lms0006", function(){
					var dataStr = "";
					window.location.href= server+"/pages/teacher/question/questionsinfoList.jsp?"+dataStr;
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
	window.location.href= server+"/pages/teacher/question/questionsinfoList.jsp?"+dataStr;
});


function checkData(){
	var typeid = $("#typeid").val();
	if(typeid == ""){
		utils.dialog.alert(fmtQuestionType+fmtIsNotNull);
		return false;
	}
	var difficultyId = $("#difficultyId").val();
	if(difficultyId == ""){
		utils.dialog.alert(fmtQuestionDifficulty+fmtIsNotNull);
		return false;
	}
	var categoryid = $("#categoryid").val();
	if(categoryid == ""){
		utils.dialog.alert(fmtQuestionCategory+fmtIsNotNull);
		return false;
	}
	var stemContent = $("#stemContent").html();
	if(stemContent == ""){
		utils.dialog.alert(fmtQuestionContent+fmtIsNotNull);
		return false;
	}
	
	if(typeid == "T00000000000001"){//填空题
		var size = $("input[name='quesBlanks']").size();
		for(var i=0;i<size;i++){
			if($("input[name='quesBlanks']").eq(i).val() == ""){
				utils.dialog.alert(fmtReferenceAnswer+fmtIsNotNull);
				return false;
			}
		}
	}else if(typeid == "T00000000000002" || typeid == "T00000000000003"){//选择题
		if(typeid == "T00000000000002"){//单选
			var radioCheck = $("input[name='radioType']:checked").val();
			if(radioCheck == null){
				utils.dialog.alert("lms.validateQuestionAnswerMsg1");
				return false;
			}
		}else if(typeid == "T00000000000003"){//多选
			var checkboxType = $("input[name='checkboxType']:checked").val();
			if(checkboxType == null){
				utils.dialog.alert("lms.validateQuestionAnswerMsg1");
				return false;
			}
		}
	}else if(typeid == "T00000000000004"){//判断题
		var judgeAnswer = $("input[name='judgeAnswer']:checked").val();
		if(judgeAnswer == null){
			utils.dialog.alert("lms.validateQuestionAnswerMsg1");
			return false;
		}
	}else if(typeid == "T00000000000005"){//问答题
		var essayContent = $("#essayContent").html();
		if(essayContent == ""){
			utils.dialog.alert("lms.validateQuestionAnswerMsg2");
			return false;
		}
	}else if(typeid == "T00000000000006"){//操作题
		utils.dialog.alert("操作题正在创建中,请选择其他题型！");
		return false;
	}
	return true;
}

function packData(){
	var data = {};
	var questioninfo = {};
	var questionOptionsList = [];
	
	var typeid = $("#typeid").val();
	var questionsid = $("#questionsid").val();
	var difficultyId = $("#difficultyId").val();
	var categoryid = $("#categoryid").val();
	var point = $("#point").val();
	var stemContent = $("#stemContent").html();
	var analysisContent = $("#analysisContent").html();
	
	questioninfo["questionsid"] = questionsid;
	questioninfo["content"] = stemContent;
	questioninfo["typeid"] = typeid;
	questioninfo["pointsid"] = categoryid;
	questioninfo["knowlegdePointsid"] = point;
	questioninfo["difficulty"] = difficultyId;
	questioninfo["remarks"] = analysisContent;
	if(typeid == "T00000000000001"){//填空题
		var size = $("input[name='quesBlanks']").size();
		var questionBlank = "";
		for(var i=0;i<size;i++){
			questionBlank += $("input[name='quesBlanks']").eq(i).val();
			if(i != size-1){
				questionBlank += "^";
			}
		}
		questioninfo["answer"] = questionBlank;
	}else if(typeid == "T00000000000002" || typeid == "T00000000000003"){//选择题
		var answerStr = "";
		$("#optionTable tr").each(function(){
			var index = $(this).closest("tr").index();
			var answerIndex = index+1;
			var questionOption = {};
			var content = $(this).find("td").eq(2).html();
			questionOption["optionscontent"] = content;
			questionOption["level"] = answerIndex;
			questionOptionsList.push(questionOption);
			
			if(typeid == "T00000000000002"){//单选
				var checkFlag = $(this).find("input[name='radioType']:checked").val();
				if(checkFlag != null){
					answerStr += answerIndex+",";
				}
				
			}else if(typeid == "T00000000000003"){//多选
				var checkFlag = $(this).find("input[name='checkboxType']:checked").val();
				if(checkFlag != null){
					answerStr += answerIndex+",";
				}
			}
		});
		questioninfo["answer"] = answerStr;
	}else if(typeid == "T00000000000004"){//判断题
		var judgeAnswer = $("input[name='judgeAnswer']:checked").val();
		questioninfo["answer"] = judgeAnswer;
	}else if(typeid == "T00000000000005"){//问答题
		var essayContent = $("#essayContent").html();
		questioninfo["answer"] = essayContent;
	}else if(typeid == "T00000000000006"){//操作题
		questioninfo["answer"] = "";
		questioninfo["attachstatus"] = "1";
	}
	data["questioninfo"] = questioninfo;
	data["questionOptionsList"] = questionOptionsList;
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

//添加
$("#addStemBtn").on("click", function(){
	var title = fmtQuestionContent;
	var id = "stemContent";
	var content = $("#"+id).html();
	openDlg(title, id, content);
});

$("#addOptionBtn").on("click", function(){
	var title = fmtSelectionContent;
	var id = "optionContent";
	var content = $("#"+id).html();
	openDlg(title, id, content);
});

$("#addEssayBtn").on("click", function(){
	var title = fmtReferenceContent;
	var id = "essayContent";
	var content = $("#"+id).html();
	openDlg(title, id, content);
});

$("#addAnalysisBtn").on("click", function(){
	var title = fmtAnalysisContent;
	var id = "analysisContent";
	var content = $("#"+id).html();
	openDlg(title, id, content);
});

function openDlg(title, id, content){
	var url = server + '/pages/teacher/question/content_dlg.jsp';
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title: title,
			clear:"true",
			init:function(){
				initEdit(id, content);
			},
			css:{"width":"large"},
			callback:function(){
			}
	};
	utils.dialog.openDialog(options);
}

//内容修改
function changeContent(id, content){
	if(id == "stemContent" || id == "essayContent" || id == "analysisContent"){
		$("#"+id).html(content);
		$("#"+id).parent().parent().find(".actions input").val(fmtEdit);
	}else if(id == "optionContent"){
		var addHtml = "<tr>";
		addHtml += "<td><input type='radio' name='radioType'></td>";
		addHtml += "<td><input type='checkbox' name='checkboxType'></td>";
		addHtml += "<td>"+content+"</td>";
		addHtml += "<td><a href='#' onclick='editOption(this)'>"+fmtEdit+"</a>&nbsp;<a href='#' onclick='deleteOption(this)'>"+fmtDelete+"</a></td>";
		addHtml += "</tr>";
		$("#optionTable").append(addHtml);
		var typeid = $("#typeid").val();
		if(typeid == "T00000000000002"){
			$("#optionTable tr").find('td:eq(0)').show();
			$("#optionTable tr").find('td:eq(1)').hide();
		}else if(typeid == "T00000000000003"){
			$("#optionTable tr").find('td:eq(0)').hide();
			$("#optionTable tr").find('td:eq(1)').show();
		}
	}else if(id.indexOf("editOptionContent") == 0){
		var index = id.replace("editOptionContent", "");
		$("#optionTable tr").eq(index).find("td").eq(2).html(content);
	}
}

//添加答案
function addAnswer() {
	var size = $("input[name='quesBlanks']").size();
	if (size == 8) {
		utils.dialog.alert("lms.validateQuestionBlanksSizeMsg");
	} else {
		$("input[name='quesBlanks']:last").after("&nbsp;<input type='text' size='13' name='quesBlanks' id= 'i"+ size + "' class='txt' >")
	}
}

//删除答案
function moveAnswer() {
	var size = $("input[name='quesBlanks']").size();
	if(size != 1){
		$("input[name='quesBlanks']:last").remove();
	}
}

//编辑选项
function editOption(obj){
	var index = $(obj).closest("tr").index();
	var title = fmtSelectionContent;
	var id = "editOptionContent"+index;
	var content = $("#optionTable tr").eq(index).find("td").eq(2).html();
	openDlg(title, id, content);
}
//删除选项
function deleteOption(obj){
	var index = $(obj).closest("tr").index();
	$("#optionTable tr").eq(index).remove();
}

//初始化试题详细信息
function initShowDetail(){
	$("#difficultyId").attr("disabled", true);
	$("#categoryid").attr("disabled", true);
	$("#point").attr("disabled", true);
	$("#addStemBtn").hide();
	$("#addOptionBtn").hide();
	$("#addEssayBtn").hide();
	$("#addAnalysisBtn").hide();
	$("#portlet-upload-dlg").show(function(){
		$("#optionTable tbody tr").each(function(){
			$(this).find("td:eq(0) input").attr("disabled", true);
			$(this).find("td:eq(1) input").attr("disabled", true);
			$(this).find("td").eq(3).hide();
			$(this).find("td").eq(4).hide();
		});
		$("input[name='judgeAnswer'").attr("disabled", true);
	});
}
