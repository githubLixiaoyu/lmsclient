var resultData;
var firstList;
var firstLength;
var path;
var userId;
var language = navigator.userLanguage || navigator.language;
var courTypeFlag;
$(function() {
	courTypeFlag = 0;
	var languageflag = language.substring(0, 2);
	if (languageflag != "zh") {
		languageflag = "en";
	}
	$("#" + languageflag).css("color", "#CBA67C");
	$("#" + languageflag).attr("showflag", "1");
	showCourseType(languageflag);
});
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
	var url = server + "/ui/tcoursetype/selectAll.do";
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
		path = resultData["path"];
		$("#learning-type-key-info").empty();
		for (var i = 0; i < firstLength; i++) {
			var id = firstList[i].id;
			var coursetypename = "";
			if (language.substring(0, 2) == "zh") {
				coursetypename = firstList[i].coursetypename;
			} else {
				coursetypename = firstList[i].coursetypenameEn;
			}
			$("#learning-type-key-info").append('<span class="glyphicon glyphicon-hand-right"></span>&nbsp;&nbsp;<a class="learning-type-key-content-label" href="javascript:void(loadCourType(' + id + '))">' + coursetypename + '</a><br />');
		}
		loadCourType(courTypeFlag);
	});
}
function loadCourType(type) {
	courTypeFlag = type;
	var num = 1;
	$("#learning-type-content-info").empty();
	if (type == 0) {
		for (var i = 0; i < firstLength; i++) {
			var id = firstList[i].id;
			loadCourTypeDeta(id, num);
			num ++;
		}
	} else {
		loadCourTypeDeta(type, num);
	}
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
			$("#learning-type-content-info").append('<div id="course-flag-' + type + '" class="learning-type-content-first"><div class="learning-type-content-first-title">' + coursetypename + '</div><div id="course-deta-' + type + '" class="learning-type-content-second"></div></div>');
		}
		if (num % 2 == 0) {
			$("#course-flag-" + type).css("background-color", "#f8f8f8");
		}
	}
	var course = resultData[type];
	if (course) {
		for (var i = 0; i < course.length; i++) {
			var courseTypeId = course[i].id;
			var coursetypename = "";
			if (language.substring(0, 2) == "zh") {
				coursetypename = course[i].coursetypename;
			} else {
				coursetypename = course[i].coursetypenameEn;
			}
			$("#course-deta-" + type).append('<div class="course-deta-content">'
						+ '<span class="learning-type-content-second-title">' + coursetypename + '</span>'
						+ '<br />'
						+ '<a href="javascript:void(loadCourTypeImage(' + courseTypeId + '))">'
							+ '<img class="learning-type-content-second-img" src="' + path + course[i].imagename + '" />'
						+ '</a>'
					+ '</div>');
		}
	}
}
function loadCourTypeImage(courseTypeId) {
	$(location).attr('href', server + '/pages/ui/learning/courseInfo.jsp?courseTypeId=' + courseTypeId + '&userId="' + userId + '"');
}