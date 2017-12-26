var grid = new Datatable();
var params;
$(function() {
	var url = server + "/teacher/testinstancerequest/selectOptionStatusTypeList.do";
	var data = {};
	utils.ajax.post(url, data, function(result) {
		createSelect2("statusTypeId", result.data, "");
	});

	var url = server + "/teacher/tvrnewtestpaper/selectVRTestTemplateList.do";
	var data = {};
	utils.ajax.post(url, data, function(result) {
		createSelect2("testTemplateId", result.data, "");
	});
	
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
	createSelect2("openType", dataValue);
	init(grid);
	
});

// 检索
$("#searchBtn").on("click", function() {
	init(grid);
});

function createSelect2(key, dataVal, val) {
	if (dataVal != "") {
		var select2 = $("#" + key);
		select2.val("");
		select2.empty();
		select2.select2({
			minimumResultsForSearch : -1,
			placeholder : fmtPleaseSelect,
			data : dataVal,
			allowClear : true,
			formatResult : this.format,
			formatSelection : this.format,
			escapeMarkup : function(m) {
				return m;
			}
		});
		if (val != "") {
			select2.select2("val", val);
		}
	}
}

function init(grid) {
	if (!grid.isInit()) {
		grid.initParam($("#searchDiv"));
		grid.init({
					src : $('#listTable'),
					onSuccess : function(grid) {
						if (grid.flag == "1") {
							utils.dialog.toastr(grid.msg, "lms0009");
						} else {
							utils.dialog.toastr(grid.msg, "lms.searchFail","error");
						}
					},
					onError : function(grid) {},
					onDataLoad : function(grid) {},
					loadingMessage : 'Loading...',
					dataTable : {
						"pageLength" : 20,
						"ajax" : {
							"url" : server + "/teacher/tvrnewtestpaper/selectVRNewTestpaperInfoList.do"},
						"columns" : [{
									"data" : function(e) {
										return "<input type='checkbox' class='checkboxes' name='checkName' value='"
												+ e.id + "'/>";
									}
								}, {
									"data" : function(e) {
										return "<a href='javascript:void(0)' onclick='editPaperOperation("+ e.id +","+e.statusTypeId+")'>"
												+ e.mileStoneName + "</>";
									}
								}, {
									"data" : function(e) {
										return replaceNull(e.testName);
									}
								}, {
									"data" : function(e) {
										if (e.openType == 1) {
											return replaceNull(fmtPC);
										}
										if (e.openType == 2) {
											return replaceNull(fmtVR);
										}
										if (e.openType == 3) {
											return replaceNull(fmtHTCsimple);
										}
										if (e.openType == 4) {
											return replaceNull(fmtHTChigh);
										}
									}
								}, {
									"data" : function(e) {
										return replaceNull(e.logincode);
									}
								}, {
									"data" : function(e) {
										return replaceNull(e.createDate);
									}
								}, {
									"data" : function(e) {
										return replaceNull(e.allowMaximumTime);
									}
								}, {
									"data" : function(e) {
										return replaceNull(e.statusType);
									}
								}, 
							]}
				});
	} else {
		grid.submitFilter("#searchDiv");
	}
}

function replaceNull(val) {
	if (val == null) {
		return ""
	}
	return val;
}

$('#listTable tbody').on('click','tr td:nth-child(1) input[type="checkbox"]',function() {
	$(this).parent().parent().parent().parent().toggleClass('selected');
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if (rows.length == $('#listTable tbody tr').length) {
		$("#checkAll").attr("checked", true);
		$("#checkAll").parent().addClass('checked');
	} else {
		$("#checkAll").attr("checked", false);
		$("#checkAll").parent().removeClass('checked');
	}
});

// checkbox全选
$("#checkAll").on('click', function() {
	if ($("#checkAll").attr("checked")) {
		$('#listTable tbody').find("tr").each(function() {
			$(this).addClass('selected')
		});
	} else {
		$('#listTable tbody').find("tr").each(function() {
			$(this).removeClass('selected')
		});
	}
});

// 新增
$("#addBtn").on("click",function() {
	window.location.href = server + "/pages/teacher/vrnewtestpaper/newtestpaperEdit.jsp"
});

// 编辑
$("#editBtn").on("click",function() {
	var title = fmtTestpaperEdit;
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if (rows.length != 1) {
		utils.dialog.alert('lms.updateSelectMsg');
		return;
	} else {
		if (rows[0].statusTypeId == "1") {
			utils.dialog.alert('lms.updateSelectValidDataMsg');
		} else if(rows[0].statusTypeId == "2"){
			utils.dialog.alert('lms.removeSelectValidDataMsg');
		}else {
			var dataStr = "?paperId=" + rows[0].id;
			window.location.href = server + "/pages/teacher/vrnewtestpaper/newtestpaperEdit.jsp" + dataStr;
		}
	}
});

// 发布
$("#sendBtn").on("click",function() {
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if (rows.length == 0) {
		utils.dialog.alert('lms.publishSelectMsg');
		return;
	} else {
		params = [];
		var params1=[];
		var params2=[];
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].statusTypeId == "0") {
				params.push(rows[i].id);
			}else if(rows[i].statusTypeId == "1"){
				params1.push(rows[i].id);
			}else if(rows[i].statusTypeId == "2"){
				params2.push(rows[i].id);
			}
		}
		if (params1.length >= 1) {
			utils.dialog.alert('lms.publishSelectValidDataMsg');
		} else if(params2.length >= 1){
			utils.dialog.alert('lms.publishSelectValidDataMsg2');
		}else {
			checkSend(params);
			updataTestStatusType(params);	
		}
	}
});

function updataTestStatusType(params){
	if($("#checkSend").val() != 0){
		var msg = "";
		var updateData = {
			idList : params.join(","),
			statusTypeId : "1"
		};
		var url = server + "/teacher/tvrnewtestpaper/updataTestStatusType.do";
		$.ajax({
			type : 'POST',
			url : url,
			data:updateData,
			async:false,
			success: function(result){
				if (result.flag == "1") {
					utils.dialog.alert("lms.publishSuccess",function() {
								init(grid);
							});
				} else {
					utils.dialog.alert("lms.publishFail")
				}
			}
		})
	}else{
		utils.dialog.alert("lms.checkSend");
		$("#checkSend").val("1");
	}	
}

function checkSend(params){
	for(var i = 0; i< params.length ;i++){
		url = server + "/teacher/tvrnewtestpaper/selectTPaperOperation.do",
		$.ajax({
			type : 'POST',
			url : url,
			data : {
				"mileStoneId" : params[i]
			},
			async:false,
			success : function(result) {
				var data = result.data;
				if(data.length == 0){			
					$("#checkSend").val("0");
					return false;
				}				
			}
		});	
	}
}

// 删除
$("#removeBtn").on("click",function() {
	
	var rows = $('#listTable').DataTable().rows('.selected').data();
	for(var i = 0; i< rows.length; i++){
		if(rows[i].statusTypeId == 2){
			utils.dialog.alert(fmtCheckStatus4);
			return 
		}
	}
	if (rows.length == 0) {
		utils.dialog.alert('lms.disableSelectMsg');
		return;
	} else {
		
		utils.dialog.confirm("lms.disableConfirmMsg",function(data) {
			if (data) {
				var params = [];
				$("input[name='checkName']:checked").each(function() {
									params.push($(this).val());
								});
				var updateData = {
					idList : params.join(","),
					statusTypeId : "2"
				};
				var url = server + "/teacher/tvrnewtestpaper/updataTestStatusType.do";
				utils.ajax.post(url,updateData,function(result) {
					if (result.flag == "1") {
						utils.dialog.alert("lms.disableSuccess",function() {
							  init(grid);
							});
					} else {
						utils.dialog.alert("lms.disableFail");
					}
				});
			}
		});
	}
});

// 编辑试卷点
function editPaperOperation(paperId,statusTypeId) {
	if (statusTypeId == "1") {
		utils.dialog.alert('lms.updateSelectValidDataMsg');
	} else if(statusTypeId == "2"){
		utils.dialog.alert('lms.removeSelectValidDataMsg');
	} else{
		var dataStr = "?paperId=" + paperId;
		window.location.href = server + "/pages/teacher/vrnewtestpaper/newtestpaperoperationEdit.jsp" + dataStr;
	}	
}