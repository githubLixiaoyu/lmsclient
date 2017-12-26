var score = 0;
var imagePath;
var filename;
var courseuploadtype;
var espkey;
var espPath;
var coursename;
var language = navigator.userLanguage || navigator.language;
$(function() {
	var url = server + "/ui/tcourseinfo/selectCourseDetailByCourseId.do";
	var data = {};
	data["courseid"] = courseid;
	utils.ajax.post(url, data, function(result){
		var resultData = result.data;
		coursename = resultData.coursename;
		var scormImagePath = resultData.scormImagePath;
		var courseflagname = "";
		var coursesectypename = "";
		if (language.substring(0, 2) == "zh") {
			courseflagname = resultData.courseflagname;
			coursesectypename = resultData.courseSecTypeName;
		} else {
			courseflagname = resultData.courseflagnameEn;
			coursesectypename = resultData.coursesectypenameEn;
		}
		var imagename = resultData.imagename;
		var createtime = resultData.createtime;
		var commentcount = resultData.commentcount;
		courseuploadtype = resultData.courseuploadtype;
		espkey = resultData.espkey;
		espPath = resultData.espPath;
		if (courseuploadtype == '1' || courseuploadtype == '3') {
			filename = resultData.filename;
			if (filename.substring(filename.length - 4) == ".zip") {
				filename = filename.substring(0, filename.length - 4);
			}
		}
		imagePath = scormImagePath + imagename;
		if (imagename == "") {
			imagePath = server + "/images/noimage.jpg";
		}
		$("#course-detail-header-content").append('<img class="course-detail-header-content-img" src="' + imagePath + '" />'
				+ '<div class="course-detail-header-content-img-mask"></div>'
				+ '<div class="course-detail-header-content-img-play">'
					+ '<a href="javascript:void(startCourseScorm(\'' + filename + '\',\'' + courseuploadtype + '\',\'' + espkey + '\'))">'
						+ '<img src="' + server + '/images/img_play.png" />'
					+ '</a>'
				+ '</div>');
		$("#course-detail-header-coursename").append(coursename);
		$("#course-detail-header-coursetype").append(courseflagname + "&nbsp;>&nbsp;" + coursesectypename);
		$("#course-detail-header-createtime").append(createtime);
		$("#course-detail-comment-label").append('<span class="course-detail-comment-num">(' + commentcount + ')</span>');
	});

    window.API = new Object();  
    API.name = "hero";  
    API.LMSInitialize=function(param){ 
		return "";
    };  
    API.LMSFinish=function(param){  
	    setUserLearnCourseScore();
	    return "";
    };  
    API.LMSGetValue=function(element){  
        return "";
    };  
    API.LMSSetValue=function(element,value){  
        if (element.indexOf("raw") > -1) {
        	score = value;
        }
	    return "";
    };  
    API.LMSCommit=function(param){  
	    return "";
    };  
    API.LMSGetLastError=function(){  
        return "";
    };  
    API.LMSGetErrorString=function(errorCode){  
	    return "";
    };  
    API.LMSGetDiagnostic=function(errorCode){  
        return "";
    };
    
    $("#course-detail-comment-button").click(function() {
    	addComment();
    });

    getCourseComment();
});
function startCourseScorm(filename, type, espkey) {
	if (type == "2" || type == "4") {
        var requestParams = "?courseId=" + espkey + "&userId=" + userId + "&learnType=0&language=" + language;
        var courseWindow = window.open(espPath + "StartCourse/startEspCourse" + requestParams);
	} else {
		$("#course-detail-header-content").empty();
		$("#course-detail-header-content").append('<iframe onload="addStyle()" id="content" name="content" width="100%" height="100%" frameborder="0" scrolling="no" src="' + server + '/../lmsFiles/contents/' + filename + '/index_lms_html5.html"></iframe>');
	}
}
function addStyle() {
	$(window.frames["content"].document).find("head").append('<style type="text/css">'
			+ 'video::-internal-media-controls-download-button {'
				+ 'display: none;'
			+ '}'
			+ 'video::-webkit-media-controls-enclosure {'
				+ 'overflow: hidden;'
			+ '}'
			+ 'video::-webkit-media-controls-panel {'
				+ 'width: calc(100% + 30px);'
			+ '}'
			+ '</style>');
}
function setUserLearnCourseScore() {
	$("#course-detail-header-content").empty();
	$("#course-detail-header-content").append('<img class="course-detail-header-content-img" src="' + imagePath + '" />'
			+ '<div class="course-detail-header-content-img-mask"></div>'
			+ '<div class="course-detail-header-content-img-play">'
				+ '<a href="javascript:void(startCourseScorm(\'' + filename + '\',\'' + courseuploadtype + '\',\'' + espkey + '\'))">'
					+ '<img src="' + server + '/images/img_play.png" />'
				+ '</a>'
			+ '</div>');
	var url = server + "/ui/tcourseinfo/setUserLearnCourseScore.do";
	var data = {};
	data["courseid"] = courseid;
	data["userid"] = userId;
	data["credits"] = score;
	utils.ajax.post(url, data);
}
function addComment() {
	if ($("#course-detail-comment-textarea").val() != "") {
		var url = server + "/ui/tcoursecomment/addComment.do";
		var data = {};
		data["courseId"] = courseid;
		data["ownerUserId"] = userId;
		data["content"] = $("#course-detail-comment-textarea").val();
		utils.ajax.post(url, data, function(result){
			if (result.flag == 1) {
				getCourseComment();
			}
		});
	}
}
function getCourseComment() {
	$("#course-detail-comment-list").empty();
	var url = server + "/ui/tcoursecomment/getCourseCommentByCourseId.do";
	var data = {};
	data["courseId"] = courseid;
	utils.ajax.post(url, data, function(result){
		var resultData = result.data;
		for (var i = 0; i < resultData.length; i++) {
			var id = resultData[i].id;
			var ownerUserId = resultData[i].ownerUserId;
			var targetUserId = resultData[i].targetUserId;
			var content = resultData[i].content;
			var createDate = resultData[i].createDate;
			var parentId = resultData[i].parentId;
			var ownerName = resultData[i].ownerName;
			var ownerPhotoName = resultData[i].ownerPhotoName;
			
			var photoPath = server + "/../lmsFiles/userPhoto/" + ownerPhotoName;
			if (ownerPhotoName == null) {
				photoPath = server + "/images/nopersimage.jpg";
			}
			if (parentId != null) {
				if ($("#reply-" + parentId).children().length > 0) {
					$("#reply-" + parentId).prepend('<div class="course-detail-comment-list-line-sub"></div>');
				}
				$("#reply-" + parentId).prepend('<div class="course-detail-comment-list-data-sub">'
							+ '<div class="course-detail-comment-list-data-sub-img">'
								+ '<img src="' + photoPath + '" />'
							+ '</div>'
							+ '<div class="course-detail-comment-list-data-sub-content">'
								+ '<div>'
									+ '<span class="course-detail-comment-list-data-sub-content-ownerName">' + ownerName + '</span>'
									+ '<span class="course-detail-comment-list-data-sub-content-createDate">' + createDate + '</span>'
								+ '</div>'
								+ '<div class="course-detail-comment-list-data-sub-content-value">' + content + '</div>'
							+ '</div>'
						+ '</div>');
			} else {
				if ($("#course-detail-comment-list").children().length > 0) {
					$("#course-detail-comment-list").prepend('<div class="course-detail-comment-list-line"></div>');
				}
				$("#course-detail-comment-list").prepend('<div class="course-detail-comment-list-data">'
							+ '<div class="course-detail-comment-list-data-img">'
								+ '<img src="' + photoPath + '" />'
							+ '</div>'
							+ '<div class="course-detail-comment-list-data-content">'
								+ '<div>'
									+ '<span class="course-detail-comment-list-data-content-ownerName">' + ownerName + '</span>'
									+ '<span class="course-detail-comment-list-data-content-createDate">' + createDate + '</span>'
								+ '</div>'
								+ '<div class="course-detail-comment-list-data-content-value">' + content + '</div>'
								+ '<div id="' + id + '" class="course-detail-comment-list-data-content-reply">'
									+ '<a href="javascript:void(addReplyComment(\'' + id + '\',\'' + ownerUserId + '\'))">' + fmtReply + '</a>'
								+ '</div>'
								+ '<div id="reply-' + id + '"></div>'
							+ '</div>'
						+ '</div>');
			}
		}
	});
}
function addReplyComment(parentId, targetUserId) {
	$("#" + parentId).after('<div class="course-detail-comment-list-data-content-click">'
				+ '<input id="input-' + parentId + '" class="course-detail-comment-list-data-content-input" type="text" max=500 placeholder="' + fmtReplyYourOpinion + '..."/>'
				+ '<button id="button-' + parentId + '" targetUserId="' + targetUserId + '" class="course-detail-comment-button">' + fmtMakeComments + '</button>'
			+ '</div>');
	$("#button-" + parentId).click(function() {
		replyComment($(this).attr("id").substring(7), $(this).attr("targetUserId"));
	});
}
function replyComment(parentId, targetUserId) {
	if ($("#input-" + parentId).val() != "") {
		var url = server + "/ui/tcoursecomment/replyComment.do";
		var data = {};
		data["courseId"] = courseid;
		data["ownerUserId"] = userId;
		data["parentId"] = parentId;
		data["targetUserId"] = targetUserId;
		data["content"] = $("#input-" + parentId).val();
		utils.ajax.post(url, data, function(result){
			if (result.flag == 1) {
				getCourseComment();
			}
		});
	}
}