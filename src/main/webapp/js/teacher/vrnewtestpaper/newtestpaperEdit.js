

$(function() {
	var dataValue = [{
		"id" : "1",
		"text" : fmtPC
	}, {
		"id" : "2",
		"text" : fmtVR
	}, {
		"id" : "3",
		"text" : fmtHTCsimple
	}, {
		"id" : "4",
		"text" : fmtHTChigh
	} ];
	$("#saveBtn").attr("type", "button");
	if (paperId != null) {
		init(paperId);
	} else {
		var url = server + "/teacher/tvrnewtestpaper/selectVRTestTemplateList.do";
		var data = {};
		utils.ajax.post(url, data, function(result) {
			createSelect2("testtemplate", result.data, "");
		});
		createSelect2("openType", dataValue);
	}
	$("#saveBtn").click(function() {
		if (checkData()) {
			var data = {};
			if (paperId != null) {
				data["Id"] = paperId;
			}
			data["mileStoneName"] = $("#testpapername").val();
			data["testTemplateId"] = $("#testtemplate").val();
			data["allowMaximumTime"] = $("#testtime").val();
			data["openType"] = $("#openType").val();
			data["description"] = $("#description").val();
			var url = server + "/teacher/tvrnewtestpaper/saveTestTemplateMilestone.do";
			utils.ajax.post(url,data,function(result) {
				if (result.data == 1) {
					utils.dialog.alert("lms0006",function() {
						window.location.href = server + "/pages/teacher/vrnewtestpaper/newtestpaperList.jsp"
						});
					} else {
						utils.dialog.alert("lms.saveFail");
						}
				});
			}
		});
	$("#editBtn").click(function() {
		editPaperInfo(paperId);
	});
});

// 返回
$("#returnBut").on("click", function() {
	var url = server + "/pages/teacher/vrnewtestpaper/newtestpaperList.jsp";
	window.location.href = url;
})

function createSelect2(key, dataVal, val) {
	if (val != null && val != "") {
		$("#" + key).val(val);
	} else {
		$("#" + key).val(dataVal[0].id);
	}
	$("#" + key).select2({
		data : dataVal,
		minimumResultsForSearch : -1,
		formatResult : this.format,
		formatSelection : this.format,
		escapeMarkup : function(m) {
			return m;
		}
	});
}

// 验证输入的内容
function checkData() {
	var testpapername = $("#testpapername").val();
	var testtime = $("#testtime").val();
	var tt = /^[0-9]+$/;
	if (!tt.test(testtime)) {
		utils.dialog.alert(fmtTestTime + fmtSignlessInteger);
		return false;
	}
	if (testpapername == "") {
		utils.dialog.alert(fmtTestpaperName + fmtIsNotNull);
		return false;
	}
	if (testtime == "") {
		utils.dialog.alert(fmtTestTime + fmtIsNotNull);
		return false;
	}
	return true;
}

function init(paperId) {
	var dataValue = [{
		"id" : "1",
		"text" : fmtPC
	}, {
		"id" : "2",
		"text" : fmtVR
	}, {
		"id" : "3",
		"text" : fmtHTCsimple
	}, {
		"id" : "4",
		"text" : fmtHTChigh
	} ];

	var updateData = {
			paperId : paperId
			};
	var url = server + "/teacher/tvrnewtestpaper/selectVRNewTestpaperInfoListById.do";
	utils.ajax.post(url, updateData, function(result) {
		var testData = result.data;
		$("#testpapername").val(testData[0].mileStoneName);
		$("#testtemplate").val(testData[0].testName);
		var testTemplateId = testData[0].testTemplateId;
		var url = server + "/teacher/tvrnewtestpaper/selectVRTestTemplateList.do";
		var data = {};
		utils.ajax.post(url, data, function(result) {
			createSelect2("testtemplate", result.data, testTemplateId);
		});
		var openType = testData[0].openType;
		createSelect2("openType", dataValue, openType);
		$("#testtime").val(testData[0].allowMaximumTime);
		$("#description").val(testData[0].description);
	});
}