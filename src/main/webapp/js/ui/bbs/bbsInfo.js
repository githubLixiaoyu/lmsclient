var bbsTypeList;
var bbsType;
var userId;
$(function() {
	//初始化论坛信息
	initBbsInfo();
});
//初始化论坛信息
function initBbsInfo() {
	var url = server + "/ui/tbbspost/selectBbsInfo.do";
	utils.ajax.post(url, {}, function(result){
		var resultData = result.data;
		userId = resultData.userId;
		var nickName = resultData.nickName;
		var photoName = resultData.photoName;
		bbsTypeList = resultData.bbsTypeList;
		var photoPath = server + "/../lmsFiles/userPhoto/" + photoName;
		if (photoPath == "") {
			photoPath = server + "/images/nopersimage.jpg"
		}
		$("#bbs-content-self-img").attr("src", photoPath);
//		$("#bbs-content-self-nickName").text(nickName);
		
		//加载论坛信息
		loadBbsInfo(resultData.postList);
	});
}
function addBbs() {
	showMask();
}
function showMask() {
	var bbsTypeSpan = '';
	var language = navigator.userLanguage || navigator.language;
	for (var i = 0; i < bbsTypeList.length; i++) {
		var typeName = "";
		if (language.substring(0, 2) == "zh") {
			typeName = bbsTypeList[i].type_name;
		} else {
			typeName = bbsTypeList[i].type_name_en;
		}
		bbsTypeSpan += '<span bbsType="' + bbsTypeList[i].id + '" class="bbs-content-add-type">' + typeName + '</span>'
	}
    $("#bbs-content-self-mask").css("height",$(document).height());
    $("#bbs-content-self-mask").css("width",$(document).width());
    $("#bbs-content-self-mask").show();
    $("#bbs-content-self-mask").after('<div id="bbs-content-add" class="bbs-content-add">'
    			+ '<div class="bbs-content-add-frame">'
    				+ '<div>'
    					+ '<textarea id="bbs-subject" class="bbs-content-add-subject-textarea" rows="3" cols="20" placeholder="' + fmtTitle + '：" maxlength=100></textarea>'
    				+ '</div>'
    				+ '<div class="bbs-content-add-line"></div>'
    				+ '<div>'
    					+ '<span class="bbs-content-add-type-span">' + fmtTags + '：</span>' + bbsTypeSpan
    				+ '</div>'
    				+ '<div class="bbs-content-add-line"></div>'
    				+ '<div>'
					+ '<textarea id="bbs-body" class="bbs-content-add-body-textarea" rows="3" cols="20" placeholder="' + fmtContent + '：" maxlength=500></textarea>'
				+ '</div>'
				+ '<div class="bbs-content-add-button">'
					+ '<button id="bbs-content-add-button-save" class="bbs-content-add-button-save">' + fmtMakeComments + '</button>'
					+ '<button id="bbs-content-add-button-close" class="bbs-content-add-button-close">' + fmtBack + '</button>'
				+ '</div>'
    		+ '</div>');
    
    bbsType = bbsTypeList[0].id;
    $("span[bbsType='" + bbsType + "']").css("background-color", "#cea47c");
    $(".bbs-content-add-type").click(function() {
    	$(".bbs-content-add-type").css("background-color", "#666666");
    	bbsType = $(this).attr("bbsType");
    	$(this).css("background-color", "#cea47c");
    });
    
    $("#bbs-content-add-button-save").click(function() {
    	var url = server + "/ui/tbbspost/saveBbsInfo.do";
    	var data = {};
    	data["type"] = bbsType;
    	data["subject"] = $("#bbs-subject").val();
    	data["body"] = $("#bbs-body").val();
    	data["createUser"] = userId;
    	utils.ajax.post(url, data, function(result){
        	$("#bbs-content-add").remove();
        	hideMask();
        	initBbsInfo();
    	});
    });
    
    $("#bbs-content-add-button-close").click(function() {
    	$("#bbs-content-add").remove();
    	hideMask();
    });
}  
//隐藏遮罩层  
function hideMask() {
    $("#bbs-content-self-mask").hide();
}
function loadBbsInfo(postList) {
	$("#bbs-content-data").empty();
	var language = navigator.userLanguage || navigator.language;
	for (var i = 0; i < postList.length; i++) {
		var photoName = postList[i].photoName;
		var photoPath = server + "/../lmsFiles/userPhoto/" + photoName;
		if (photoName == null) {
			photoPath = server + "/images/nopersimage.jpg";
		}
		var typeName = "";
		if (language.substring(0, 2) == "zh") {
			typeName = postList[i].typeName;
		} else {
			typeName = postList[i].typeNameEn;
		}
		$("#bbs-content-data").append('<div class="bbs-content-info">'
					+ '<div class="bbs-content-info-photo">'
						+ '<img class="bbs-content-info-photo-img" src="' + photoPath + '"/>'
					+ '</div>'
					+ '<div id="div-' + postList[i].id + '" class="bbs-content-info-body">'
						+ '<div class="bbs-content-info-detail">'
							+ '<div class="bbs-content-info-detail-title">'
								+ '<div class="bbs-content-info-detail-title-nickName">'
									+ postList[i].nickName
									+ '&nbsp;&nbsp;<span class="bbs-content-info-detail-title-line"></span>&nbsp;&nbsp;'
									+ postList[i].subject
								+ '</div>'
								+ '<div class="bbs-content-info-detail-title-createDate">' + postList[i].createDate + '</div>'
							+ '</div>'
							+ '<div class="bbs-content-info-detail-body">' + postList[i].body + '</div>'
							+ '<div class="bbs-content-info-detail-label">'
								+ '<span class="glyphicon glyphicon-tag"></span>'
								+ '<span class="bbs-content-info-detail-label-typeName">' + typeName + '</span>'
								+ '<span class="bbs-content-info-detail-label-space"></span>'
								+ '<span class="bbs-content-info-detail-label-look">' + fmtPageview + '（' + postList[i].lookNum + '）</span>'
								+ '<span class="bbs-content-info-detail-label-reply">' + fmtComment + '（' + postList[i].replyNum + '）</span>'
								+ '<span id="' + postList[i].id + '" open="1" class="glyphicon glyphicon-chevron-down hand"></span>'
							+ '</div>'
						+ '</div>'
					+ '</div>'
				+ '</div>');
	}
	
	$("span[open='1']").click(function() {
		if ($(this).is(".glyphicon-chevron-down")) {
			loadReplyInfo($(this).attr("id"));
			$(this).removeClass("glyphicon-chevron-down");
			$(this).addClass("glyphicon-chevron-up");
		} else if ($(this).is(".glyphicon-chevron-up")) {
			$(this).removeClass("glyphicon-chevron-up");
			$(this).addClass("glyphicon-chevron-down");
			$("#reply-" + $(this).attr("id")).remove();
		}
	});
}
//加载回复内容
function loadReplyInfo(postId) {
	var lookUrl = server + "/ui/tbbspost/addBbsLookNum.do";
	var lookData = {};
	lookData["id"] = postId;
	utils.ajax.post(lookUrl, lookData);
	
	var url = server + "/ui/tbbspost/selectBbsReplyInfoByPostId.do";
	var data = {};
	data["postId"] = postId;
	utils.ajax.post(url, data, function(result){
		var resultData = result.data;
		$("#div-" + postId).append('<div id="reply-' + postId + '" class="bbs-content-info-reply">'
					+ '<div class="bbs-content-info-reply-div">'
						+ '<input id="input-' + postId + '" class="bbs-content-info-reply-input" type="text" max=500 placeholder="' + fmtReplyYourOpinion + '..."/>'
						+ '<button id="button-' + postId + '" class="bbs-content-info-reply-button">' + fmtMakeComments + '</button>'
					+ '</div>'
					+ '<div id="bbs-' + postId + '" class="bbs-content-info-reply-detail"></div>'
				+ '</div>');
		
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
					$("#reply-" + parentId).prepend('<div class="bbs-content-info-reply-detail-list-line-sub"></div>');
				}
				$("#reply-" + parentId).prepend('<div class="bbs-content-info-reply-detail-list-data-sub">'
							+ '<div class="bbs-content-info-reply-detail-list-data-sub-img">'
								+ '<img src="' + photoPath + '" />'
							+ '</div>'
							+ '<div class="bbs-content-info-reply-detail-list-data-sub-content">'
								+ '<div>'
									+ '<span class="bbs-content-info-reply-detail-list-data-sub-content-ownerName">' + ownerName + '</span>'
									+ '<span class="bbs-content-info-reply-detail-list-data-sub-content-createDate">' + createDate + '</span>'
								+ '</div>'
								+ '<div class="bbs-content-info-reply-detail-list-data-sub-content-value">' + content + '</div>'
							+ '</div>'
						+ '</div>');
			} else {
				if ($("#bbs-" + postId).children().length > 0) {
					$("#bbs-" + postId).prepend('<div class="bbs-content-info-reply-detail-line"></div>');
				}
				$("#bbs-" + postId).prepend('<div class="bbs-content-info-reply-detail-list-data">'
							+ '<div class="bbs-content-info-reply-detail-list-data-img">'
								+ '<img src="' + photoPath + '" />'
							+ '</div>'
							+ '<div class="bbs-content-info-reply-detail-list-data-content">'
								+ '<div>'
									+ '<span class="bbs-content-info-reply-detail-list-data-content-ownerName">' + ownerName + '</span>'
									+ '<span class="bbs-content-info-reply-detail-list-data-content-createDate">' + createDate + '</span>'
								+ '</div>'
								+ '<div class="bbs-content-info-reply-detail-list-data-content-value">' + content + '</div>'
								+ '<div id="' + id + '" class="bbs-content-info-reply-detail-list-data-content-reply">'
									+ '<a href="javascript:void(addReply(\'' + id + '\',\'' + ownerUserId + '\',\'' + postId + '\'))">' + fmtReply + '</a>'
								+ '</div>'
								+ '<div id="reply-' + id + '"></div>'
							+ '</div>'
						+ '</div>');
			}
		}
		
		$("#reply-" + postId).append('<div class="bbs-content-info-reply-detail-floot"></div>');
		
		$("#button-" + postId).click(function() {
			addBbsReply(postId);
		});
	});
}
//回复帖子
function addBbsReply(postId) {
	if ($("#input-" + postId).val() != "") {
		var url = server + "/ui/tbbspost/addBbsReply.do";
		var data = {};
		data["postId"] = postId;
		data["ownerUserId"] = userId;
		data["content"] = $("#input-" + postId).val();
		utils.ajax.post(url, data, function(result){
			if (result.flag == 1) {
				$("#reply-" + postId).remove();
				loadReplyInfo(postId);
			}
		});
	}
}
function addReply(parentId, targetUserId, postId) {
	$("#" + parentId).after('<div class="bbs-content-info-reply-detail-list-data-content-click">'
				+ '<input id="input-' + parentId + '" class="bbs-content-info-reply-detail-list-data-content-input" type="text" max=500 placeholder="' + fmtReplyYourOpinion + '..."/>'
				+ '<button id="button-' + parentId + '" targetUserId="' + targetUserId + '" postId="' + postId + '" class="bbs-content-info-reply-button">' + fmtMakeComments + '</button>'
			+ '</div>');
	$("#button-" + parentId).click(function() {
		addReplyInfo($(this).attr("id").substring(7), $(this).attr("targetUserId"), $(this).attr("postId"));
	});
}
function addReplyInfo(parentId, targetUserId, postId) {
	if ($("#input-" + parentId).val() != "") {
		var url = server + "/ui/tbbspost/addBbsReply.do";
		var data = {};
		data["postId"] = postId;
		data["ownerUserId"] = userId;
		data["parentId"] = parentId;
		data["targetUserId"] = targetUserId;
		data["content"] = $("#input-" + parentId).val();
		utils.ajax.post(url, data, function(result){
			$("#reply-" + postId).remove();
			loadReplyInfo(postId);
		});
	}
}