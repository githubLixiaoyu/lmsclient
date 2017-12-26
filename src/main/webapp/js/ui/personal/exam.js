var remainingTime;
var examname;
var examkhzl;
var showpoint;
var examtime;
var questionList;
var questionObj = {};
var nowQuestionId;
var paperId;
var continuetime;
var iCount;
var espPath;
var language = navigator.userLanguage || navigator.language;
$(function() {
	var url = server + "/ui/tnewexaminfo/getUserExamInfo.do";
	var data = {};
	var examnameTemp = "";
	var examkhzlTemp = "";
	data["examid"] = examId;
	data["loginUserId"] = userId;
	data["isVr"] = isVr;

	utils.ajax.post(url, data, function(result){
		var resultData = result.data;
		continuetime = resultData.continuetime;
		espPath = resultData.espPath;
		if(continuetime == null) {
			continuetime = 0;
		}
		examtime = resultData.examtime;
		paperId = resultData.userpaper;
		examname = resultData.examname;
		examkhzl = resultData.examkhzl;
		showpoint = resultData.showpoint;
		remainingTime = examtime - continuetime;
		if(isVr == 0){
			//网上考试
			examnameTemp = '<div class="exam-content-explain-left-body-examname">' + fmtNameOfTestPaper + '：' + examname + '</div>';
			examkhzlTemp = fmtTheory;
		}else{
			examkhzlTemp = "VR";
		}
		$("#exam-header-content-title").text(resultData.examname);
		
		if (type == 1) {
			startExam();
		} else {
			$("#exam-content").append('<div class="exam-content-body">'
						+ '<div class="exam-content-explain">'
						+ '<div class="exam-content-explain-left">'
							+ '<div class="exam-content-explain-left-body">'
								+ '<span class="exam-content-explain-left-body-label">' + fmtTestTime + '：</span>'
								+ '<span class="exam-content-explain-left-body-examtime">' + resultData.examtime + '</span>'
								+ '<span class="exam-content-explain-left-body-label">' + fmtMin + '</span>'
							+ '</div>'
							+ '<div class="exam-content-explain-left-body-line"></div>'
							+ examnameTemp
							//+ '<div class="exam-content-explain-left-body-examname">' + fmtNameOfTestPaper + '：' + examname + '</div>'
							+ '<div class="exam-content-explain-left-body-examkhzl">' + fmtTestSort + '：' + examkhzl + '</div>'
							+ '<div class="exam-content-explain-left-body-examkhzl">' + fmtTestType + '：' + examkhzlTemp + '</div>'
						+ '</div>'
						+ '<div class="exam-content-explain-centre">'
							+ '<span class="exam-content-explain-centre-line"></span>'
						+ '</div>'
						+ '<div class="exam-content-explain-right">' + fmtTestIntroduction + '：<br />' + resultData.remark + '</div>'
					+ '</div>'
					+ '<div class="exam-content-button" onclick="startExam();">' + fmtStartTest + '</div>'
				+'</div>');
		}
	});
});
//开始考试
function startExam() {
	if (isVr == 1 && type == 1) {
		// 显示VR考试结果
		var pid = 0;
		var cid = 0;
		var pjson = {}; //第一列跨行 记录  pid：数目
		var cjson = {}; //第二列跨行 记录  cid：数目
		var str = "";
		var num = 1;//行颜色记录器
		var examUrl = server + "/auth/common/selectVrAnswersByFilter.do";
		var examData = {};
		examData["examid"] = examId;
		examData["userid"] = userId;
		utils.ajax.post(examUrl, examData, function(result){
			var vrExamData = result.data;
			var examkhzl = vrExamData[0].examkind;//考试种类
			var remainingTimeLabel = fmtUsedTime; //“用时：”
			var remainingTimeValue = vrExamData[0].examTime; //考试用时
			if(remainingTimeValue == null) {
				remainingTimeValue = 0;
			}
			$("#exam-content").empty();
			$("#exam-content").css("background-color", "#eeeeee");
			//左侧----------------------
			$("#exam-content").append('<div class="exam-content-left">'
						+ '<div class="exam-content-left-body">'
							+ '<span id="remainingTime-label" class="exam-content-left-body-label">' + remainingTimeLabel + '</span>'
							+ '<span id="remainingTime" class="exam-content-left-body-remainingTime">' + remainingTimeValue + '</span>'
							+ '<span class="exam-content-left-body-label">' + fmtMin + '</span>'
						+ '</div>'
						+ '<div class="exam-content-left-body-line"></div>'
						+ '<div class="exam-content-left-body-examkhzl">' + fmtTestSort + '：' + examkhzl + '</div>'
						+ '<div class="exam-content-left-body-examkhzl">' + fmtTestType + '：' +'VR</div>'
						+ '<div id="outExam" class="exam-content-left-button" onclick="outExam();">' + fmtExit + '</div>'
					+ '</div>'
			//中间-----------------
					+ '<div id="exam-content-centre" class="exam-content-centre">'
					//表头
					+'<table id="table1" class="exam-content-centre-table-title"></table>'
					//表格内容
					+'<table id="table2" class="exam-content-centre-table-body"></table>'
					+ '</div>');
					$("#table1").append("<tr><td width='30%'>"+fmtTitleCourse+"</td><td width='30%'>"+fmtTitleCoursePoint+"</td><td width='30%'>"+fmtTitleTask+"</td><td width='10%'>"+fmtTitleResult+"</td></tr>");
					//显示退出按钮
					$("#outExam").css("display", "block");
			for(var i = 0; i < vrExamData.length; i++) {
				//结果展示 X 、O
				if(vrExamData[i].ispass == 0) {
					str = "<td width='10%' class='exam-content-centre-table-body-td'><font color='red'>X</font></td></tr>";
				}else{
					str = "<td width='10%' class='exam-content-centre-table-body-td'><font color='#CBA67C'>O</font></td></tr>";
				}
				if(pid != vrExamData[i].pid) {
					pjson[vrExamData[i].pid]=1; 
					cjson[vrExamData[i].cid]=1;
					$("#table2").append("<tr class='exam-content-centre-table-body-tr'>"
											+"<td width='30%' id="+vrExamData[i].pid+" class='exam-content-centre-table-body-td'>" + vrExamData[i].pname + "</td>"
											+"<td width='30%' id = "+vrExamData[i].cid+" class='exam-content-centre-table-body-td'>"+ vrExamData[i].cname + "</td>"
											+"<td width='30%' class='exam-content-centre-table-body-td'>"+ vrExamData[i].tname + "</td>" 
										+ str );
					if(num % 2 == 1){
						$("#"+vrExamData[i].cid).css("background-color", "#f5f5f5");
						$("#"+vrExamData[i].cid).next().css("background-color", "#f5f5f5");
						$("#"+vrExamData[i].cid).next().next().css("background-color", "#f5f5f5");
					}
					num++;
				} else {
					if(cid != vrExamData[i].cid) {
						pjson[vrExamData[i].pid] += 1;
						cjson[vrExamData[i].cid] = 1;
						$("#table2").append("<tr class='exam-content-centre-table-body-tr'>"
												+"<td width='30%' id="+vrExamData[i].cid+" class='exam-content-centre-table-body-td'>"+ vrExamData[i].cname + "</td>"
												+"<td width='30%' class='exam-content-centre-table-body-td'>"+ vrExamData[i].tname + "</td>" 
											+ str );
						if(num % 2 == 1 ) {
							$("#"+vrExamData[i].cid).css("background-color", "#f5f5f5");
							$("#"+vrExamData[i].cid).next().css("background-color", "#f5f5f5");
							$("#"+vrExamData[i].cid).next().next().css("background-color", "#f5f5f5");
						}
						num ++;
					} else {
						pjson[vrExamData[i].pid] += 1;
						cjson[vrExamData[i].cid] += 1;
						$("#table2").append("<tr class='exam-content-centre-table-body-tr'>"
												+"<td width='30%' class='exam-content-centre-table-body-td' >"+vrExamData[i].tname + "</td>" 
											+ str );
						if(num % 2 == 0) {
							$("#"+vrExamData[i].cid).parent().nextAll().css("background-color", "#f5f5f5");
						}
					}
				}
				pid = vrExamData[i].pid;
				cid = vrExamData[i].cid;
			}
			for(var i in pjson){
				$('#' +i).attr("rowspan",pjson[i]);
			}
			for(var j in cjson){
				$("#" +j).attr("rowspan",cjson[j]);
			}
		});
	} else if (isVr == 1 && type == 0) {
		// 跳转到ESP系统进行VR考试
        var requestParams = "?testRequestId=" + examId + "&userId=" + userId + "&language=" + language;
        var courseWindow = window.open(espPath + "StartCourse/startEspTest" + requestParams);
	} else {
		// 理论考试
		var examUrl = server + "/ui/tnewexamdetails/saveExamDetails.do";
		var examData = {};
		examData["examid"] = examId;
		examData["paperid"] = paperId;
		examData["userid"] = userId;
		utils.ajax.post(examUrl, examData, function(result){
			var remainingTimeLabel = "";
			var remainingTimeValue = "";
			if (type == 1) {
				remainingTimeLabel = fmtUsedTime + "：";
				remainingTimeValue = continuetime;
			} else {
				remainingTimeLabel = fmtRemainingTime + "：";
				remainingTimeValue = remainingTime;
			}
			$("#exam-content").empty();
			$("#exam-content").css("background-color", "#eeeeee");
			$("#exam-content").append('<div class="exam-content-left">'
						+ '<div class="exam-content-left-body">'
							+ '<span id="remainingTime-label" class="exam-content-left-body-label">' + remainingTimeLabel + '</span>'
							+ '<span id="remainingTime" class="exam-content-left-body-remainingTime">' + remainingTimeValue + '</span>'
							+ '<span class="exam-content-left-body-label">' + fmtMin + '</span>'
						+ '</div>'
						+ '<div class="exam-content-left-body-line"></div>'
						+ '<div class="exam-content-left-body-examname">' + examname + '</div>'
						+ '<div class="exam-content-left-body-examkhzl">' + fmtTestSort + '：' + examkhzl + '</div>'
						+ '<div class="exam-content-left-body-examkhzl">' + fmtTestType + '：' + fmtTheory + '</div>'
						+ '<div id="endExam" class="exam-content-left-button" onclick="endExam(1);">' + fmtTestPaperSubmission + '</div>'
						+ '<div id="outExam" class="exam-content-left-button" onclick="outExam();">' + fmtExit + '</div>'
						+ '<div id="remarkExam" class="exam-content-left-button-remark" onclick="remarkExam();">' + fmtQuestionAnalysis + '</div>'
					+ '</div>'
					+ '<div id="exam-content-centre" class="exam-content-centre">'
						+ '<div id="exam-content-centre-question" class="exam-content-centre-question"></div>'
						+ '<div class="exam-content-centre-line"></div>'
						+ '<div id="exam-content-centre-button" class="exam-content-centre-button">'
							+ '<div id="exam-content-centre-button-body" class="exam-content-centre-button-body"></div>'
						+ '</div>'
					+ '</div>'
					+ '<div id="exam-content-right" class="exam-content-right"></div>');
			
			if (type == 1) {
				$("#endExam").css("display", "none");
				$("#outExam").css("display", "block");
				$("#remarkExam").css("display", "none");
			} else {
				$("#endExam").css("display", "block");
				$("#outExam").css("display", "none");
				$("#remarkExam").css("display", "none");
			}
			
			if (type != 1) {
				//考试倒计时
				examRemainingTime();
			}
			
			var url = server + "/auth/common/selectPaperOptionsByFilter.do";
			var data = {};
			data["examid"] = examId;
			data["loginUserId"] = userId;
			utils.ajax.post(url, data, function(result){
				questionList = result.data;
				for (var i = 0; i < questionList.length; i++) {
					questionObj[questionList[i].questionId] = questionList[i];
					var problemState = questionList[i].problemState;
					var questionContent = questionList[i].questionContent;
					var questionId = questionList[i].questionId;
					var questionNum = questionList[i].questionNum;
					var questionScore = questionList[i].questionScore;
					var remarks = questionList[i].remarks;
					var showAnswer = questionList[i].showAnswer;
					var typeId = questionList[i].typeId;
					var typeName = questionList[i].typeName;
					var answer = questionList[i].answer;
					var userAnswer = questionList[i].userAnswer;
					var optionList = questionList[i].data;
					if (i == 0) {
						loadQuestion(questionList[i]);
					}
					$("#exam-content-centre-button-body").append('<div id="btn-' + questionId + '" class="exam-content-centre-button-body-btn">'
								+ '<span id="span-' + questionId + '" class="exam-content-centre-button-body-btn-span">' + questionNum + '</span>'
							+ '</div>');
					if (type == 1) {
						if (problemState == "1") {
							if (userAnswer != answer) {
								$("#btn-" + questionId).css("background", "url('" + server + "/images/exam/btn_3_2.png') no-repeat left top");
								$("#span-" + questionId).css("color", "#FFFFFF");
							} else {
								$("#btn-" + questionId).css("background", "url('" + server + "/images/exam/btn_2_2.png') no-repeat left top");
								$("#span-" + questionId).css("color", "#FFFFFF");
							}
						} else {
							if (userAnswer != answer) {
								$("#btn-" + questionId).css("background", "url('" + server + "/images/exam/btn_3_1.png') no-repeat left top");
								$("#span-" + questionId).css("color", "#FFFFFF");
							} else {
								$("#btn-" + questionId).css("background", "url('" + server + "/images/exam/btn_2_1.png') no-repeat left top");
								$("#span-" + questionId).css("color", "#FFFFFF");
							}
						}
					} else {
						if (problemState == "1") {
							if (userAnswer != "") {
								$("#btn-" + questionId).css("background", "url('" + server + "/images/exam/btn_2_2.png') no-repeat left top");
								$("#span-" + questionId).css("color", "#FFFFFF");
							} else {
								$("#btn-" + questionId).css("background", "url('" + server + "/images/exam/btn_1_2.png') no-repeat left top");
								$("#span-" + questionId).css("color", "#666666");
							}
						} else {
							if (userAnswer != "") {
								$("#btn-" + questionId).css("background", "url('" + server + "/images/exam/btn_2_1.png') no-repeat left top");
								$("#span-" + questionId).css("color", "#FFFFFF");
							} else {
								$("#btn-" + questionId).css("background", "url('" + server + "/images/exam/btn_1_1.png') no-repeat left top");
								$("#span-" + questionId).css("color", "#666666");
							}
						}
					}
				}
				$(".exam-content-centre-button-body-btn").click(function () {
					var thisId = $(this).attr("id");
					if (type != 1) {
						saveExamStyle(nowQuestionId);
					}
					loadQuestion(questionObj[thisId.substring(4)]);
				});
			});
		});
	}
}
//考试倒计时
function examRemainingTime() {
	var m = remainingTime - 1;
	iCount = setInterval(function() {
		$('#remainingTime').html(m);
		continuetime = examtime - m;
		if (m < 1) {
			//清空倒计时
			clearInterval(iCount);
			endExam(2);
		}
		m--;
	},60000);
}
//加载单个试题
function loadQuestion(question) {
	$("#exam-content-centre-question").empty();
	$("#exam-content-right").empty();
	var problemState = question.problemState;
	var questionContent = question.questionContent;
	var questionId = question.questionId;
	var questionNum = question.questionNum;
	var questionScore = question.questionScore;
	var remarks = question.remarks;
	var showAnswer = question.showAnswer;
	var typeId = question.typeId;
	var typeName = question.typeName;
	var userAnswer = question.userAnswer;
	var optionList = question.data;
	nowQuestionId = questionId;
	
	$("#exam-content-centre-question").append('<div class="exam-content-centre-question-body">'
				+ '<div class="exam-content-centre-question-content">' + questionNum + '.(' + typeName + ')' + questionContent + '</div>'
				+ '<div id="tag-' + questionId + '" class="exam-content-centre-question-state"></div>'
			+ '</div>');
	if (problemState == "1") {
		$("#tag-" + questionId).css("background", "url('" + server + "/images/exam/tag_2.png') no-repeat left top");
	} else {
		$("#tag-" + questionId).css("background", "url('" + server + "/images/exam/tag_1.png') no-repeat left top");
	}
	
	if (type != 1) {
		$("#tag-" + questionId).click(function () {
			var thisId = $(this).attr("id");
			var qi = thisId.substring(4);
			var ps = questionObj[qi].problemState;
			var ua = questionObj[qi].userAnswer;
			if (ps == "1") {
				$(this).css("background", "url('" + server + "/images/exam/tag_1.png') no-repeat left top");
				questionObj[qi].problemState = "0";
				if (ua != "") {
					$("#btn-" + qi).css("background", "url('" + server + "/images/exam/btn_2_1.png') no-repeat left top");
					$("#span-" + qi).css("color", "#FFFFFF");
				} else {
					$("#btn-" + qi).css("background", "url('" + server + "/images/exam/btn_1_1.png') no-repeat left top");
					$("#span-" + qi).css("color", "#666666");
				}
			} else {
				$(this).css("background", "url('" + server + "/images/exam/tag_2.png') no-repeat left top");
				questionObj[qi].problemState = "1";
				if (ua != "") {
					$("#btn-" + qi).css("background", "url('" + server + "/images/exam/btn_2_2.png') no-repeat left top");
					$("#span-" + qi).css("color", "#FFFFFF");
				} else {
					$("#btn-" + qi).css("background", "url('" + server + "/images/exam/btn_1_2.png') no-repeat left top");
					$("#span-" + qi).css("color", "#666666");
				}
			}
			saveExamProblemState(qi);
		});
	}
    
	for (var i = 0; i < optionList.length; i++) {
		var optionContent = optionList[i].optionContent;
		var optionId = optionList[i].optionId;
		var optionKey = optionList[i].optionKey;
		$("#exam-content-centre-question").append('<br /><div id="' + optionId + '" questionId="' + questionId + '" sel="0" class="exam-content-centre-question-option">　' + optionKey + '.' + optionContent + '</div>');
		if (userAnswer.indexOf(optionId) > -1) {
			$("#" + optionId).css("background-color", "#EEEEEE").css("color", "#CBA67C");
			$("#" + optionId).attr("sel", "1");
		} else {
			$("#" + optionId).css("background-color", "#FFFFFF").css("color", "#666666");
			$("#" + optionId).attr("sel", "0");
		}
	}
	
	if (type != 1) {
		$(".exam-content-centre-question-option").click(function() {
			if (typeId == "T00000000000003") {
				if ($(this).attr("sel") == "1") {
					if ($(".exam-content-centre-question-option[sel='1']").length > 1
							|| ($(".exam-content-centre-question-option[sel='1']").length == 1
							&& $(".exam-content-centre-question-option[sel='1']").attr("id") != $(this).attr("id"))) {
						$(this).css("background-color", "#FFFFFF").css("color", "#666666");
						$(this).attr("sel", "0");
					} else {
						utils.dialog.alert('lms.oneAnswer');
					}
				} else {
					$(this).css("background-color", "#EEEEEE").css("color", "#CBA67C");
					$(this).attr("sel", "1");
				}
			} else {
				$(".exam-content-centre-question-option").css("background-color", "#FFFFFF").css("color", "#666666");
				$(".exam-content-centre-question-option").attr("sel", "0");
				$(this).css("background-color", "#EEEEEE").css("color", "#CBA67C");
				$(this).attr("sel", "1");
			}
		});
	}
	
	var prevNum = questionNum - 1;
	var nextNum = questionNum + 1;
	$("#exam-content-centre-question").append('<div class="exam-content-centre-question-button">'
				+ '<span num=' + prevNum + ' switch=1 class="exam-content-centre-question-button-prev"></span>'
				+ '<span num=' + nextNum + ' switch=1 class="exam-content-centre-question-button-next"></span>'
			+ '</div>');
	$("span[switch=1]").click(function () {
		var num = $(this).attr("num");
		if (num < 1) {
			utils.dialog.alert('lms.firstQuestion');
		} else if (num > questionList.length) {
			utils.dialog.alert('lms.lastQuestion');
		} else {
			if (type != 1) {
				saveExamStyle(nowQuestionId);
			}
			loadQuestion(questionObj[questionList[num - 1].questionId]);
		}
	});
	
	if (showpoint == "1" && type == 1) {
		$("#exam-content-right").append('<div class="exam-content-right-remarks">' + fmtCorrectAnswer + '：' + showAnswer + '<br /><br />' + fmtQuestionAnalysis + '：<br /><br />' + remarks + '</div>');
	}
}
//保存当前显示样式
function saveExamStyle(questionId) {
	var userAnswer = questionObj[questionId].userAnswer;
	var problemState = questionObj[questionId].problemState;
	var nowUserAnswer = "";
	$(".exam-content-centre-question-option").each(function(){
		if ($(this).attr("sel") == "1") {
			nowUserAnswer += "," + $(this).attr("id");
		}
	});
	if (nowUserAnswer != "") {
		nowUserAnswer = nowUserAnswer.substring(1);
	}
	if (nowUserAnswer != "" && userAnswer != nowUserAnswer) {
		questionObj[questionId].userAnswer = nowUserAnswer;
		if (problemState == "1") {
			$("#btn-" + questionId).css("background", "url('" + server + "/images/exam/btn_2_2.png') no-repeat left top");
			$("#span-" + questionId).css("color", "#FFFFFF");
		} else {
			$("#btn-" + questionId).css("background", "url('" + server + "/images/exam/btn_2_1.png') no-repeat left top");
			$("#span-" + questionId).css("color", "#FFFFFF");
		}
		
		saveExamQuestionAnswer(questionId);
	}
}
//保存考试标记
function saveExamProblemState(questionId) {
	var url = server + "/ui/tnewexamprocedure/saveExamProblemState.do";
	var data = {};
	data["examid"] = examId;
	data["paperid"] = paperId;
	data["userid"] = userId;
	data["questionid"] = questionId;
	data["typeid"] = questionObj[questionId].typeId;
	data["problemstate"] = questionObj[questionId].problemState;
	data["continuetime"] = continuetime;
	utils.ajax.post(url, data);
}
//保存考试内容
function saveExamQuestionAnswer(questionId) {
	var url = server + "/ui/tnewexamprocedure/saveExamQuestionAnswer.do";
	var data = {};
	data["examid"] = examId;
	data["paperid"] = paperId;
	data["userid"] = userId;
	data["questionid"] = questionId;
	data["typeid"] = questionObj[questionId].typeId;
	data["answer"] = questionObj[questionId].userAnswer;
	data["continuetime"] = continuetime;
	utils.ajax.post(url, data);
}
//交卷
function endExam(flag) {
	saveExamStyle(nowQuestionId);
	if (flag == 1) {
		utils.dialog.confirm('lms.confirmSubmit', function(data) {
			if (data) {
				clearInterval(iCount);
				saveUserExam();
			}
		});
	} else {
		utils.dialog.alert('lms.testEnd', function() {
			saveUserExam();
		});
	}
}
//交卷保存方法
function saveUserExam() {
	var url = server + "/ui/tnewexamdetails/saveUserExam.do";
	var data = {};
	data["examid"] = examId;
	data["paperid"] = paperId;
	data["userid"] = userId;
	data["continuetime"] = continuetime;
	utils.ajax.post(url, data, function(result) {
		$("#endExam").css("display", "none");
		$("#outExam").css("display", "block");
		$("#remarkExam").css("display", "block");
		
		$("#remainingTime-label").text(fmtUsedTime + "：");
		$("#remainingTime").text(continuetime);
		
		$("#exam-content-centre").empty();
		$("#exam-content-right").empty();
		$("#exam-content-centre").append('<div class="exam-content-centre-score-label">' + fmtTestResult + ':</div>'
				+ '<div class="exam-content-centre-score-div">'
					+ '<span class="exam-content-centre-score-value">' + result.data.score + '</span>'
					+ '<span class="exam-content-centre-score-span">' + fmtScoreSmall + '</span>'
				+ '</div>');
	});
}
//退出
function outExam() {
	$(location).attr('href', server + '/pages/ui/personal/personalCenter.jsp?personalFlag=4');
}
//试题解析
function remarkExam() {
	$(location).attr('href', server + '/pages/ui/personal/exam.jsp?examId="' + examId + '"&userId="' + userId + '"&type=1');
}