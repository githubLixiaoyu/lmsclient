var grid = new Datatable();
var i = 0;
var testTemplateId = 0;

$(function() {
	if (paperId != null) {
		$("#saveBtn").attr("type", "button");
		init(grid);
		setTimeout("selPaperOperation(paperId)", "500");
		setTimeout("disinputCheck()", "1000");
	} else {
		utils.dialog.toastr(grid.msg, "lms.papererror", "error");
	}
	$("#saveBtn").click(function() {
		if (checkData()) {
			var dataObj = document.getElementsByName("checkName");
			var checkNameId = new Array();
			var checkSorce = new Array();
			var checkSequence = new Array();
			var m = 0;
			for (var i = 0; i < dataObj.length; i++) {
				if (dataObj[i].checked) {
					checkNameId[m] = dataObj[i].value;
					checkSorce[m] = $("#score" + dataObj[i].value).val();
					checkSequence[m] = $("#sequence" + dataObj[i].value).val()
					m++;
				}
			}
			var data = {
				"checkNameId" : checkNameId,
				"checkSorce" : checkSorce,
				"checkSequence" : checkSequence,
				"paperId" : paperId
			};
			var url = server + "/teacher/tvrnewtestpaper/saveTestPaperOperation.do";
			$.ajax({
				type : 'POST',
				url : url,
				data : data,
				traditional : true,
				success : function(result) {
					if (result.data >= 1) {
						utils.dialog.alert("lms0006");
					} else {
						utils.dialog.alert("lms.saveFail");
					}
				}
			})
		}
	});
});

// 返回
$("#returnBut").on("click", function() {
	var url = server + "/pages/teacher/vrnewtestpaper/newtestpaperList.jsp";
	window.location.href = url;
})

// 平均分配分数
$("#avgdistribution").on("click", function() {
	var tt = /^[0-9]+$/;
	var score = $("#aggregateScore").val();
	if (score == null || score == "") {
		utils.dialog.alert(fmtSumScore + fmtIsNotNull);
		return false;
	}
	if (!tt.test(score)) {
		utils.dialog.alert(fmtSumScore + fmtSignlessInteger);
		return false;
	}
	var dataObj = document.getElementsByName("checkName");
	var dataScore = new Array();
	var m = 0;
	for (var i = 0; i < dataObj.length; i++) {
		if (dataObj[i].checked) {
			dataScore[m] = "#score" + dataObj[i].value;
			m++;
		} else {
			$("#score" + dataObj[i].value).val("");
			$("#sequence" + dataObj[i].value).val("");
		}
	}
	var avgScore = Math.ceil(score / m);
	var avgScoreLast = score - avgScore * (m - 1);
	if (avgScoreLast < 1) {
		avgScore = parseInt(score / m);
		avgScoreLast = score - avgScore * (m - 1);
	}
	for (var i = 0; i < dataScore.length; i++) {
		if (i == (dataScore.length - 1)) {
			$(dataScore[i]).val(avgScoreLast);
		} else {
			$(dataScore[i]).val(avgScore);
		}
	}
});

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
	var tt = /^[0-9]+$/;
	var score = $("#aggregateScore").val();
	if (score == null || score == "") {
		utils.dialog.alert(fmtSumScore + fmtIsNotNull);
		return false;
	}
	if (!tt.test(score)) {
		utils.dialog.alert(fmtSumScore + fmtSignlessInteger);
		return false;
	}
	var dataObj = document.getElementsByName("checkName");
	var dataSeq = new Array();
	var sumScore = 0;
	var k = 0;
	for (var i = 0; i < dataObj.length; i++) {
		if (dataObj[i].checked) {
			k++;
			if ($("#score" + dataObj[i].value).val() == null || $("#score" + dataObj[i].value).val() == "") {
				utils.dialog.alert(fmtScore + fmtIsNotNull);
				return false;
			} else if (!tt.test($("#score" + dataObj[i].value).val())) {
				utils.dialog.alert(fmtScore + fmtSignlessInteger);
				return false;
			} else {
				sumScore = parseInt(sumScore) + parseInt($("#score" + dataObj[i].value).val());
			}
			if ($("#sequence" + dataObj[i].value).val() == null || $("#sequence" + dataObj[i].value).val() == "") {
				utils.dialog.alert(fmtSequence + fmtIsNotNull);
				return false;
			} else if (!tt.test($("#sequence" + dataObj[i].value).val())) {
				utils.dialog.alert(fmtSequence + fmtSignlessInteger);
				return false;
			} else {
				dataSeq.push($("#sequence" + dataObj[i].value).val());
			}
		}
	}
	if (k == 0) {
		utils.dialog.alert(fmtCheckError);
		return false;
	}
	if (sumScore != score) {
		utils.dialog.alert(fmtUnequalSumScore);
		return false;
	}
	var nary = dataSeq.sort();
	for (var i = 0; i < dataSeq.length; i++) {
		if (nary[i] == nary[i + 1]) {
			utils.dialog.alert(fmtSequenceEqual);
			return false;
		}
	}
	return true;
}

function init(grid) {
	if (!grid.isInit()) {
		grid.setAjaxParam("paperId", paperId);
		grid.init({
			src : $('#listTable'),
			onSuccess : function(grid) {
				if (grid.flag == "1") {
					utils.dialog.toastr(grid.msg, "lms0009");
				} else {
						utils.dialog.toastr(grid.msg, "lms.papererror","error");
				}
			 },
			onError : function(grid) {
			 },
			onDataLoad : function(grid) {
			 },
			loadingMessage : 'Loading...',
			dataTable : {
				"ajax" : {
					"url" : server + "/teacher/tvrnewtestpaper/selectTestPaperOperationByMilestoneId.do",
					"success" : function(e) {
						var data = e.data;
						var html = "";
						var pid = 0;
						var json = new Array();
						var num = 0;
						var j = 0;
						for (var i = 0; i < data.length; i++) {
							html += "<tr> ";
							if (pid != data[i].testTemplateId) {
								if (pid != 0) {
									json[j] = new Array();
									json[j][0] = pid;
									json[j][1] = num + 1;
									num = 0;
									j++;
								}
								html += "<td id= '" + data[i].testTemplateId + "'style='border-right:#cccccc solid 1px;'>" + data[i].mileStoneName
										+ "</td><td>&nbsp;&nbsp;&nbsp;<span>"
										+ "<input type='checkbox' class='checkboxes' onclick='disinput("
										+ data[i].id + ")' name='checkName' id='checkName" 	+ data[i].id
										+ "' value='" + data[i].id + "'/></span>&nbsp;&nbsp;&nbsp;"
										+ data[i].operationName + "</td>"
										+ "<td><input type='text' class='form-control' style='border-top-width:0px;" 
										+"border-bottom-width:0px; ' id='score"
										+ data[i].id + "'name='score" + data[i].id + "' maxlength='20'></td>"
										+ "<td><input type='text' class='form-control' style='border-left-width:0px;" 
										+ "border-top-width:0px;border-bottom-width:0px;'id='sequence" 
										+ data[i].id + "'name='sequence" + data[i].id + "'  maxlength='20'></td></tr>";
							} else {
								html += "<td>&nbsp;&nbsp;&nbsp;<span><input type='checkbox' onclick='disinput("
										+ data[i].id + ")' id='checkName" + data[i].id
										+ "' class='checkboxes' name='checkName' value='" + data[i].id
										+ "'/></span>&nbsp;&nbsp;&nbsp;" + data[i].operationName
										+ "</td><td><input type='text' class='form-control' style='border-top-width:0px;" 
										+ "border-bottom-width:0px;' id='score"
										+ data[i].id + "'name='score" + data[i].id + "' maxlength='20'></td>"										
										+ "<td><input type='text' class='form-control'style='border-left-width:0px; " 
										+ "border-top-width:0px;border-bottom-width:0px;' id='sequence"
										+ data[i].id + "'name='sequence" + data[i].id + "' maxlength='20'></td></tr>";	
								num++;
								if (i == (data.length - 1)) {
									json[j] = new Array();
									json[j][0] = pid;
									json[j][1] = num + 1;
									num = 0;
									j++;
								}
							}
							pid = data[i].testTemplateId;
						}

						$("#listTable tbody").append(html);
						for (var k = 0; k < json.length; k++) {
							$('#' + json[k][0]).attr("rowspan",json[k][1]);		
						}

				    	 utils.tools.unloading();
					}
				},

			},
		});
	} else {
		grid.submitFilter("");
	}
}

function selPaperOperation(mileStoneId) {
	url = server + "/teacher/tvrnewtestpaper/selectTPaperOperation.do",
	$.ajax({
		type : 'POST',
		url : url,
		data : {
			"mileStoneId" : mileStoneId
		},
		traditional : true,
		success : function(result) {
			var data = result.data;
			var sumScore = 0;
			for (var i = 0; i < data.length; i++) {
				sumScore = parseInt(sumScore) + parseInt(data[i].score);
				$("#score" + data[i].referenceOperationId).val(data[i].score);		
				$("#sequence" + data[i].referenceOperationId).val(data[i].sequenceNumber);	
				$("#checkName" + data[i].referenceOperationId).attr("checked", "true");		
			}
			if(sumScore != 0){
				$("#aggregateScore").val(sumScore);
			}
		}
	});
}

function replaceNull(val) {
	if (val == null) {
		return ""
	}
	return val;
}

// checkbox全选
$("#checkAll").on('click', function() {
	if ($("#checkAll").attr("checked")) {
		$("input[name^='score']").removeAttr("disabled");
		$("input[name^='sequence']").removeAttr("disabled");
		$("input[name='checkName']").each(function() {
			this.checked = true;
		});
	} else {
		$("input[name^='score']").attr("disabled", "ture");
		$("input[name^='sequence']").attr("disabled", "ture");
		$("input[name='checkName']").each(function() {this.checked = false;});
	}
});

function disinput(id) {
	if ($("#checkName" + id).is(':checked')) {
		$("#score" + id).removeAttr("disabled");
		$("#sequence" + id).removeAttr("disabled");
	} else {
		$("#score" + id).attr("disabled", "ture");
		$("#sequence" + id).attr("disabled", "ture");
	}
}

function disinputCheck() {
	var dataObj = document.getElementsByName("checkName");
	for (var i = 0; i < dataObj.length; i++) {
		if (!dataObj[i].checked) {		
			$("#score" + dataObj[i].value).attr("disabled", "ture");
			$("#sequence" + dataObj[i].value).attr("disabled", "ture");
		}
	}
}