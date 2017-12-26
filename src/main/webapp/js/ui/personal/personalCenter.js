var resultData;
var firstList;
var firstLength;
var userId;
var photoName;
var espPath;
var language = navigator.userLanguage || navigator.language;
$(function() {
	var url = server + "/ui/tapp/getQrcode.do";
	utils.ajax.post(url, {}, function(result){
		$("#personal-type-key").after('<img id="android-qrcode" class="personal-type-key-qrcode" src="' + server + '/../lmsFiles/qrcodeImages/' + result.data.android + '"><br />android<br />'
				+ '<img id="ios-qrcode" class="personal-type-key-qrcode" src="' + server + '/../lmsFiles/qrcodeImages/' + result.data.ios + '"><br />ios<br />');
	});
	
	if (personalFlag == 4) {
		myExam();
	} else {
		myCourse();
	}
});
//个人信息
function myInfo() {
	$("#personal-type-content").empty();
	var url = server + "/ui/tpermissionsstudentinfo/getUserInfo.do";
	utils.ajax.post(url, {}, function(result){
		resultData = result.data;
		userId = resultData.userId;
		var photoName = resultData.photoName;
		var nickName = resultData.nickName;
		var path = server + "/../lmsFiles/userPhoto/" + photoName;
		if (photoName == null || photoName == "null") {
			path = server + "/images/nopersimage.jpg";
		}
		$("#personal-type-content").append('<div class="personal-info-img-label">' + fmtCurrentAvatar + '：</div>'
				+ '<div class="personal-info-img-div">'
					+ '<a href="javascript:void(editPhotoFun())">'
						+ '<img id="personal-info-img" class="personal-info-img" src="' + path + '" />'
					+ '</a>'
				+ '</div>'
				+ '<div class="personal-info-photoImage"><input type="file" id="photoImage" name="file" onchange="imageUploadFun()"/></div>'
				+ '<div class="personal-info-level"></div>'
				+ '<div class="personal-info-nick-label">'
					+ '<span class="personal-info-nick-label-span">' + fmtNickName + '：</span><a class="personal-info-nick-label-a" href="javascript:void(updateNickName())">' + fmtModify + '<span class="glyphicon glyphicon-pencil"></span></a>'
				+ '</div>'
				+ '<div class="personal-info-nick-input-div">'
					+ '<input id="nick-input" class="personal-info-nick-input" type="text" value="' + nickName + '" max=50/>'
				+ '</div>'
				+ '<div class="personal-info-pwd-label">'
					+ '<span class="personal-info-pwd-label-span">' + fmtPassword + '：</span><a class="personal-info-nick-label-a" href="javascript:void(updatePassword())">' + fmtModify + '<span class="glyphicon glyphicon-pencil"></span></a>'
				+ '</div>'
				+ '<div class="personal-info-pwd-input-div">'
					+ '<input id="pwd-input" class="personal-info-pwd-input" type="password" value="*******" max=50/>'
				+ '</div>');
		$("#pwd-input").focus(function() {
			$("#pwd-input").val("");
		});
	});
}
//我的课程
function myCourse() {
	$("#personal-type-content").empty();
	$("#personal-type-content").append('<div id="personal-type-content-tag" class="personal-type-content-tag">'
			+ '<span class="personal-type-content-tag-span">' + fmtCourseLanguage + '：</span>'
			+ '<span id="zh" showflag="0" class="personal-type-content-tag-span-zh" onclick="changeCourseLanguage(this)">' + fmtCourseChinese + '</span>'
			+ '<span id="en" showflag="0" class="personal-type-content-tag-span-en" onclick="changeCourseLanguage(this)">' + fmtCourseEnglish + '</span>'
			+ '<span id="all" showflag="0" class="personal-type-content-tag-span-all" onclick="changeCourseLanguage(this)">' + fmtCourseAll + '</span>'
		+ '</div>'
		+ '<div id="personal-type-content-info" class="personal-type-content-info"></div>');
	
	var languageflag = language.substring(0, 2);
	if (languageflag != "zh") {
		languageflag = "en";
	}
	$("#" + languageflag).css("color", "#CBA67C");
	$("#" + languageflag).attr("showflag", "1");
	showCourseType(languageflag);
}
function changeCourseLanguage(me) {
	$("span[showflag='1']").css("color", "#373737");
	$("span[showflag='1']").attr("showFlag", "0");
	$(me).css("color", "#CBA67C");
	$(me).attr("showflag", "1");
	var languageflag = $(me).attr("id");
	if (languageflag == "all") {
		languageflag = "";
	}
	showCourseType(languageflag);
}
function showCourseType(languageflag) {
	$("#personal-type-content-info").empty();
	var url = server + "/ui/tcoursetype/selectAllByUserId.do";
	var data = {};
	if (languageflag != "" && languageflag != "zh") {
		languageflag = "en";
	}
	data["languageflag"] = languageflag;
	utils.ajax.post(url, data, function(result){
		resultData = result.data;
		firstList = resultData["0"];
		firstLength = firstList.length;
		userId = resultData.userId;
		photoName = resultData.photoName;
		var num = 1;
		for (var i = 0; i < firstLength; i++) {
			var id = firstList[i].id;
			loadCourTypeDeta(id, num);
			num ++;
		}
	});
}
//我的排名
function myRanking() {
	$("#personal-type-content").empty();
	var photoPath = server + "/../lmsFiles/userPhoto/" + photoName;
	if (photoName == "") {
		photoPath = server + "/images/nopersimage.jpg";
	}
	$("#personal-type-content").append('<div class="personal-ranking">'
				+ '<div class="personal-ranking-self">'
					+ '<img class="personal-ranking-self-img" src="' + photoPath + '"/>'
					+ '<br />'
					+ '<span id="personal-ranking-self-span" class="personal-ranking-self-span"></span>'
				+ '</div>'
				+ '<div class="personal-ranking-line"></div>'
				+ '<div class="personal-ranking-content">'
					+ '<div class="personal-ranking-content-title">'
						+ '<table class="personal-ranking-content-table">'
							+ '<tr>'
								+ '<td class="personal-ranking-content-td">' + fmtNationalRankingList + '</td>'
								+ '<td class="personal-ranking-content-td"></td>'
								+ '<td class="personal-ranking-content-td"></td>'
								+ '<td class="personal-ranking-content-td"></td>'
								+ '<td class="personal-ranking-content-td"></td>'
							+ '</tr>'
						+ '</table>'
					+ '</div>'
					+ '<div class="personal-ranking-content-column">'
						+ '<table class="personal-ranking-content-table">'
							+ '<tr>'
								+ '<td class="personal-ranking-content-td">' + fmtRanking + '</td>'
								+ '<td class="personal-ranking-content-td">' + fmtAvatar + '</td>'
								+ '<td class="personal-ranking-content-td">' + fmtNickName + '</td>'
								+ '<td class="personal-ranking-content-td">' + fmtLevel + '</td>'
								+ '<td class="personal-ranking-content-td">' + fmtScore + '</td>'
							+ '</tr>'
						+ '</table>'
					+ '</div>'
					+ '<div class="personal-ranking-content-data">'
						+ '<table id="personal-ranking-content-table-data" class="personal-ranking-content-table"></table>'
					+ '</div>'
				+ '</div>'
			+ '</div>');
	var url = server + "/ui/tranking/getRankingInfo.do";
	utils.ajax.post(url, {}, function(result){
		var rankingList = result.data;
		var numFlag = "";
		for (var i = 0; i < rankingList.length; i++) {
			if (userId == rankingList[i].id) {
				numFlag = i + 1;
			}
			var photoName = rankingList[i].photoName;
			var photoPath = server + "/../lmsFiles/userPhoto/" + photoName;
			if (photoName == null) {
				photoPath = server + "/images/nopersimage.jpg";
			}
			$("#personal-ranking-content-table-data").append('<tr>'
						+ '<td class="personal-ranking-content-td">' + (i + 1) + '</td>'
						+ '<td class="personal-ranking-content-td"><img class="personal-ranking-content-td-img" src="' + photoPath + '"/></td>'
						+ '<td class="personal-ranking-content-td">' + rankingList[i].nickName + '</td>'
						+ '<td class="personal-ranking-content-td">V</td>'
						+ '<td class="personal-ranking-content-td">' + rankingList[i].totalScore + '</td>'
					+ '</tr>');
		}
		$("#personal-ranking-self-span").text(numFlag);
	});
}
//我的考试
function myExam() {
	var url = server + "/auth/common/getUserInfo.do";
	utils.ajax.post(url, {}, function(result) {
		userId = result.data.userId;
		photoName = result.data.photoName;
		$("#personal-type-content").empty();
		var photoPath = server + "/../lmsFiles/userPhoto/" + photoName;
		if (photoName == "") {
			photoPath = server + "/images/nopersimage.jpg";
		}
		$("#personal-type-content").append('<div class="personal-ranking">'
					+ '<div class="personal-ranking-self">'
						+ '<img class="personal-ranking-self-img" src="' + photoPath + '"/>'
						+ '<br />'
						+ '<span id="personal-exam-self-no" class="personal-exam-self-no"></span>'
						+ '<span id="personal-exam-self-yes" class="personal-exam-self-yes"></span>'
						+ '<br />'
						+ '<span class="personal-exam-self-span">' + fmtUntested + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
						+ '<span class="personal-exam-self-span">' + fmtTested + '</span>'
					+ '</div>'
					+ '<div class="personal-ranking-line"></div>'
					+ '<div class="personal-ranking-content">'
						+ '<div class="personal-ranking-content-title">'
							+ '<table class="personal-ranking-content-table">'
								+ '<tr>'
									+ '<td class="personal-exam-content-wk"><span id="noExam" class="personal-exam-content-span" onclick="loadExamInfo(this,0);">' + fmtUntested + '</span></td>'
									+ '<td class="personal-exam-content-yk"><span class="personal-exam-content-span" onclick="loadExamInfo(this,1);">' + fmtTested + '</span></td>'
									+ '<td class="personal-ranking-content-td"></td>'
									+ '<td class="personal-ranking-content-td"></td>'
									+ '<td class="personal-ranking-content-td"></td>'
								+ '</tr>'
							+ '</table>'
						+ '</div>'
						+ '<div class="personal-exam-content-data">'
							+ '<div id="personal-exam-content-table-data" class="personal-exam-content-table-data"></div>'
						+ '</div>'
					+ '</div>'
				+ '</div>');
		$("#noExam").click();
	});
}
//加载考试详细信息
function loadExamDetail(me, type) {
	var examId = me.parent().attr("id");
	var flag = me.parent().attr("flag");
	var isVr = me.parent().attr("isVr");
	if (type == 1) {
		$(location).attr('href', server + '/pages/ui/personal/exam.jsp?examId="' + examId + '"&userId="' + userId + '"&type=' + type + '&isVr=' + isVr);
	} else if (flag == "before") {
		utils.dialog.alert("lms.beforeTestTime");
	} else if (flag == "after") {
		utils.dialog.alert("lms.afterTestTime");
	} else {
		$(location).attr('href', server + '/pages/ui/personal/exam.jsp?examId="' + examId + '"&userId="' + userId + '"&type=' + type + '&isVr=' + isVr);
	}
}
//加载考试信息
function loadExamInfo(me, type) {
	$("#personal-exam-content-table-data").empty();
	$(".personal-exam-content-span").css("color", "#666666");
	$(me).css("color", "#CBA67C");
	var url = server + "/ui/tnewexaminfo/selectExamInfoByFilter.do";
	var data = {};
	data["loginUserId"] = userId;
	data["reviewStatus"] = type;
	utils.ajax.post(url, data, function(result){
		var examMap = result.data;
		var examData = examMap.data;
		var noExamNum = examMap.noExamNum;
		var examedNum = examMap.examedNum;
		for (var i = 0; i < examData.length; i++) {
			var examtime = "";
			if (examData[i].starttime != "") {
				examtime = examData[i].starttime + " - " + examData[i].endtime;
			}
			//判断考试类型是否为 vr 考试  0=否(网上考试) 1=是(VR考试)
			var examtype = "";
			var examScore = "";
			if(examData[i].isVr == 0) {
				examtype = fmtTheory;
				examScore = examData[i].score;
			}else{
				examtype = "VR";
				examScore = examData[i].score
				if(examScore == null) {
					examScore = 0;
				}
			}
			var scoreLabel = "";
			if (type == 1) {
				scoreLabel = '<div class="personal-exam-content-table-data-score">'
						+ '<span class="personal-exam-content-table-data-score-value">' + examScore + '</span>'
//						+ '<span class="personal-exam-content-table-data-score-label">' + fmtScoreSmall + '</span>'
					+' </div>';
			} else if (examData[i].flag == "before") {
				scoreLabel = '<div class="personal-exam-content-table-data-dk">'
						+ '<span class="personal-exam-content-table-data-dk-span">' + fmtToBeTest + '</span>'
					+' </div>';
			} else if (examData[i].flag == "after") {
				scoreLabel = '<div class="personal-exam-content-table-data-qk">'
					+ '<span class="personal-exam-content-table-data-qk-span">' + fmtTestAbsent + '</span>'
				+' </div>';
			} else {
				scoreLabel = '<div class="personal-exam-content-table-data-ks">'
						+ '<span class="personal-exam-content-table-data-ks-span">' + fmtStart + '</span>'
					+' </div>';
			}
			if ($("#personal-exam-content-table-data").children().length > 0) {
				$("#personal-exam-content-table-data").append('<div class="personal-exam-empty"></div>');
			}
			$("#personal-exam-content-table-data").append('<div id="' + examData[i].examid + '" flag="' + examData[i].flag + '" isVr="' + examData[i].isVr + '" class="personal-exam-content-table-data-div">'
						+ '<div class="personal-exam-content-table-data-empty"></div>'
						+ scoreLabel
						+ '<div class="personal-exam-content-table-data-detail">'
							+ '<div class="personal-exam-content-table-data-detail-header">'
								+ '<div class="personal-exam-content-table-data-detail-header-examname">&nbsp;&nbsp;' + examData[i].examname + '</div>'
								+ '<div class="personal-exam-content-table-data-detail-header-examtime">' + examtime + '&nbsp;&nbsp;&nbsp;</div>'
							+ '</div>'
							+ '<div class="personal-exam-content-table-data-detail-line"></div>'
							+ '<div class="personal-exam-content-table-data-detail-footer">&nbsp;&nbsp;&nbsp;' + fmtSort + '：' + examData[i].examkhzl + '</div>'
							//--------------↓增加考试类型-------------------------------------------------
							+ '<div class="personal-exam-content-table-data-detail-footer">&nbsp;&nbsp;&nbsp;' + fmtTestType + '：' + examtype + '</div>'	
						+ '</div>'
					+ '</div>');
		}
		$("#personal-exam-self-no").text(noExamNum + "/");
		$("#personal-exam-self-yes").text(examedNum);

		$(".personal-exam-content-table-data-dk").click(function () {
			loadExamDetail($(this), type);
		});
		$(".personal-exam-content-table-data-ks").click(function () {
			loadExamDetail($(this), type);
		});
		$(".personal-exam-content-table-data-qk").click(function () {
			loadExamDetail($(this), type);
		});
		$(".personal-exam-content-table-data-score").click(function () {
			loadExamDetail($(this), type);
		});
		$(".personal-exam-content-table-data-detail").click(function () {
			loadExamDetail($(this), type);
		});
	});
}
//我的消息
function myNews() {
	
}
function loadCourTypeDeta(type, num) {
	for (var i = 0; i < firstLength; i++) {
		var coursetypename = "";
		if (language.substring(0, 2) == "zh") {
			coursetypename = firstList[i].coursetypename;
		} else {
			coursetypename = firstList[i].coursetypenameEn;
		}
		if (type == firstList[i].id) {
			$("#personal-type-content-info").append('<div id="course-flag-' + type + '" class="personal-type-content-first"><div class="personal-type-content-first-title">' + coursetypename + '</div><div id="course-deta-' + type + '" class="personal-type-content-second"></div></div>');
		}
		if (num % 2 == 0) {
			$("#course-flag-" + type).css("background-color", "#f8f8f8");
		}
	}
	var course = resultData[type];
	if (course) {
		for (var i = 0; i < course.length; i++) {
			var courseTypeId = course[i].id;
			var imagename = course[i].imagename;
			var path = server + "/../lmsFiles/courseTypeImages/" + imagename;
			if (imagename == null) {
				path = server + "/images/noimage.jpg"; 
			}
			var coursetypename = "";
			if (language.substring(0, 2) == "zh") {
				coursetypename = course[i].coursetypename;
			} else {
				coursetypename = course[i].coursetypenameEn;
			}
			$("#course-deta-" + type).append('<div class="course-deta-content">'
						+ '<span class="personal-type-content-second-title">' + coursetypename + '</span>'
						+ '<br />'
						+ '<a href="javascript:void(loadCourTypeImage(' + courseTypeId + '))">'
							+ '<img class="personal-type-content-second-img" src="' + path + '" />'
						+ '</a>'
						+ '<br />'
						+ '<span class="personal-type-content-second-learn-num">' + course[i].count + '&nbsp;</span>'
						+ '<span class="personal-type-content-second-learn-label">' + fmtClassesHadLearned + '</span>'
					+ '</div>');
		}
	}
}
function loadCourTypeImage(courseTypeId) {
	$("#personal-type-content").empty();
	var url = server + "/ui/tcourseinfo/selectCourseInfoByFillter.do";
	var data = {};
	data["coursesectype"] = courseTypeId;
	data["userId"] = userId;
	utils.ajax.post(url, data, function(result){
		var courseData = result.data.list;
		espPath = result.data.espPath;
		for (var i = 0; i < courseData.length; i++) {
			var imagename = courseData[i].imagename;
			var praiseflag = courseData[i].praiseflag;
			var path = server + "/../lmsFiles/scormImages/" + imagename;
			if (imagename == null) {
				path = server + "/images/noimage.jpg";
			}
			var praiseClass = "";
			if (praiseflag == 1) {
				praiseClass = " praise"
			}
			var courseuploadtype = courseData[i].courseuploadtype;
			var courseuploadtypename = "";
			if (courseuploadtype == 4) {
				courseuploadtypename = "VR";
			} else if (courseuploadtype == 3) {
				courseuploadtypename = fmtVideo;
			} else if (courseuploadtype == 2) {
				courseuploadtypename = fmtThreeDimensional;
			} else {
				courseuploadtypename = fmtCourseware;
			}
			$("#personal-type-content").append('<div class="course-content-deta">'
						+ '<span class="course-content-deta-title">' + courseData[i].coursename + '</span>'
						+ '<span class="course-content-deta-label">' + courseuploadtypename + '</span>'
						+ '<br />'
						+ '<a href="javascript:void(loadCourseDeta(\'' + courseData[i].courseid + '\'))">'
							+ '<img class="course-content-deta-img" src="' + path + '" />'
						+ '</a>'
						+ '<br />'
						+ '<span class="course-type-content-second-comment">'
							+ '&nbsp;<span praise="1" id="praise-' + courseData[i].courseid + '" class="glyphicon glyphicon-thumbs-up hand' + praiseClass + '"></span>&nbsp;'
							+ '<span id="praiseNum-' + courseData[i].courseid + '" class="course-type-content-second-comment-spac">' + courseData[i].praisecount + '</span>'
							+ '<span class="glyphicon glyphicon-edit"></span>&nbsp;'
							+ '<span class="course-type-content-second-comment-spac">' + courseData[i].commentcount + '</span>'
						+ '</span>'
					+ '</div>');
		}
		$("span[praise='1']").click(function() {
			var thisCourseId = $(this).attr("id").substring(7);
			var praiseNum = $("#praiseNum-" + thisCourseId).text();
			if ($(this).is('.praise')) {
				praiseNum = parseInt(praiseNum) - 1;
				$("#praiseNum-" + thisCourseId).text(praiseNum);
				$(this).removeClass("praise");
				removePraise(thisCourseId, userId);
			} else {
				praiseNum = parseInt(praiseNum) + 1;
				$("#praiseNum-" + thisCourseId).text(praiseNum);
				$(this).addClass("praise");
				addPraise(thisCourseId, userId);
			}
		});
	});
}
function addPraise(thisCourseId, userId) {
	var url = server + "/ui/tcourseinfo/addPraise.do";
	var data = {};
	data["courseId"] = thisCourseId;
	data["userId"] = userId;
	utils.ajax.post(url, data);
}
function removePraise(thisCourseId, userId) {
	var url = server + "/ui/tcourseinfo/removePraise.do";
	var data = {};
	data["courseId"] = thisCourseId;
	data["userId"] = userId;
	utils.ajax.post(url, data);
}
function loadCourseDeta(courseid) {
//	if (type == "2" || type == "4") {
//		window.open(espPath + "startCourse?courseId=" + espkey, "startCourse");
//	} else {
		$(location).attr('href', server + '/pages/ui/learning/courseDeta.jsp?courseid="' + courseid + '"&userId="' + userId + '"');
//	}
}
function editPhotoFun() {
	$("#photoImage").click();
}
function updateNickName() {
	var nickName = $("#nick-input").val();
	var url = server + "/ui/tpermissionsstudentinfo/updateNickName.do";
	var data = {};
	data["nickname"] = nickName;
	utils.ajax.post(url, data, function(result){
		getLoginUser();
	});
}
function updatePassword() {
	var pwd = $("#pwd-input").val();
	if (pwd != "*******" && pwd != "") {
		var url = server + "/ui/tpermissionsstudentinfo/updatePassword.do";
		var data = {};
		data["password"] = pwd;
		utils.ajax.post(url, data);
	}
}
//上传图片
function imageUploadFun() {
	var me = $("#photoImage");
	// 图片类型
	var imageType = ",bmp,gif,jpeg,jpg,ico,png,tif,";
	// 判断是否为图片格式的文件
	if (imageType.indexOf("," + me.val().substring(me.val().lastIndexOf(".") + 1).toLowerCase() + ",") < 0) {
		utils.dialog.alert('lms.pleaseUploadPicture');
		me.val("");
	} else {
		Metronic.blockUI({
			animate : true,
			overlayColor : 'none'
		});
		// 执行上传文件操作的函数
		$.ajaxFileUpload({
			// 处理文件上传操作的服务器端地址
			url : server + '/ui/tpermissionsstudentinfo/uploadPhoto.do',
			secureuri : false, // 是否启用安全提交,默认为false
			fileElementId : "photoImage", // 文件选择框的id属性
			dataType : 'text', // 服务器返回的格式,可以是json或xml等
			success : function(result, status) {
				// 服务器响应成功时的处理函数
				result = result.replace(/<pre.*?>/g, ''); // ajaxFileUpload会对服务器响应回来的text内容加上<pre
														// style="....">text</pre>前后缀
				result = result.replace(/<PRE.*?>/g, '');
				result = result.replace("<PRE>", '');
				result = result.replace("</PRE>", '');
				result = result.replace("<pre>", '');
				result = result.replace("</pre>", '');
				result = JSON.parse(result);
				Metronic.unblockUI();
				if (result.flag == 1) {
					$("#personal-info-img").attr("src", server + "/../lmsFiles/userPhoto/" + result.data.imageName);
				}
			},
			error : function(result, status, e) { // 服务器响应失败时的处理函数
				Metronic.unblockUI();
				utils.dialog.toastr(result.msg, "lms0002", "error");
			}
		});
	}
}