var espPath;
var language = navigator.userLanguage || navigator.language;
$(function() {
	var url = server + "/ui/tcourseinfo/selectByCourseType.do";
	var data = {};
	data["coursesectype"] = courseTypeId;
	data["userId"] = userId;
	utils.ajax.post(url, data, function(result){
		var resultData = result.data;
		var path = resultData.path;
		espPath = resultData.espPath;
		var coursetypename = "";
		if (language.substring(0, 2) == "zh") {
			coursetypename = resultData.coursetypename;
		} else {
			coursetypename = resultData.coursetypenameEn;
		}
		var courseData = resultData.list;
		$("#course-header-content-title").append(coursetypename);
		for (var i = 0; i < courseData.length; i++) {
			var imagename = courseData[i].imagename;
			var imagePath = path + imagename;
			if (imagename == null) {
				imagePath = server + "/images/noimage.jpg";
			}
			var learnState = courseData[i].learnState;
			var learnStateName = "";
			if (learnState == 0) {
				learnStateName = fmtNotApplied;
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
			$("#course-content").append('<div class="course-content-deta">'
						+ '<span class="course-content-deta-title">' + courseData[i].coursename + '</span>'
						+ '<span class="course-content-deta-label">' + courseuploadtypename + '</span>'
						+ '<br />'
						+ '<a href="javascript:void(loadCourseDeta(\'' + courseData[i].courseid + '\',' + learnState + '))">'
							+ '<img class="course-content-deta-img" src="' + imagePath + '" />'
						+ '</a>'
						+ '<br />'
						+ '<span class="course-type-content-second-comment">'
							+ '&nbsp;<span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;'
							+ '<span class="course-type-content-second-comment-spac">' + courseData[i].praisecount + '</span>'
							+ '<span class="glyphicon glyphicon-edit"></span>&nbsp;'
							+ '<span class="course-type-content-second-comment-spac">' + courseData[i].commentcount + '</span>'
							+ learnStateName
						+ '</span>'
					+ '</div>');
		}
	});
});
function loadCourseDeta(courseid, learnState) {
	if (learnState == 0) {
		var url = server + "/ui/tcourseinfo/addUserLearnCourse.do";
		var data = {};
		data["courseid"] = courseid;
		data["userid"] = userId;
		utils.ajax.post(url, data, function(result) {
//			if (type == "2" || type == "4") {
//				window.open(espPath + "startCourse?courseId=" + espkey, "startCourse");
//			} else {
				$(location).attr('href', server + '/pages/ui/learning/courseDeta.jsp?courseid="' + courseid + '"&userId="' + userId + '"');
//			}
		});
	} else {
//		if (type == "2" || type == "4") {
//			window.open(espPath + "startCourse?courseId=" + espkey, "startCourse");
//		} else {
			$(location).attr('href', server + '/pages/ui/learning/courseDeta.jsp?courseid="' + courseid + '"&userId="' + userId + '"');
//		}
	}
}