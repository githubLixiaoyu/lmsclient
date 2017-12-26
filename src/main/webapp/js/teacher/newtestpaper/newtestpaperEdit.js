$(function(){
//	paperId = '0DA7ED1C1614489383B4E99C4E3F6D5B';
	if (paperId == null || paperId == "") {
		$("#saveBtn").attr("type", "button");
		addPaperInfo();
		initQuestionType();
	} else {
		$("#editBtn").attr("type", "button");
		initEditPaperInfo(paperId);
		initQuestionType(paperId);
	}
	
	$("#saveBtn").click(function() {
		if ($("#papername").val() == "") {
			utils.dialog.alert("lms0010");
		} else {
			var url;
			var data = {};
			if (paperId == null || paperId == "") {
				url = server + "/teacher/tnewtestpaperinfo/addNewtestpaperInfo.do";
			} else {
				url = server + "/teacher/tnewtestpaperinfo/updateNewtestpaperInfo.do";
				data["paperid"] = paperId;
			}
			data["papername"] = $("#papername").val();
			data["categoryid"] = $("#categoryid").val();
			data["papertype"] = $("#papertype").val();
			data["categorytext"] = $("#categoryid").select2("data").text;
			data["difficult"] = $("#difficult").val();
			data["difficulttext"] = $("#difficult").select2("data").text;
			data["forpaper"] = $("#forpaper").val();
			data["remark"] = $("#remark").val();
			
			utils.ajax.post(url, data, function(result){
				if (paperId == null || paperId == "") {
					paperId = result.data.paperId;
					initQuestionType(paperId);
				}
				//加载试卷内容
				loadText(data);
				$("#saveBtn").attr("type", "hidden");
				$("#editBtn").attr("type", "button");
			});
		}
	});
	

	$("#editBtn").click(function() {
		editPaperInfo(paperId);
	});
});
function initQuestionType(paperId) {
	var url = server + "/teacher/trandompaperquestiontype/selectRandompaperQuestiontype.do";
	var data= {"paperid": paperId};
	utils.ajax.post(url, data, function(result){
		var papertype = $("#papertype").val();
//		console.info(result);
		$("#listTable").DataTable({
			"destroy": true,
			"data" : result.data,
			"columns" : [{
				"data" : function(e) {
					var inputHtml = "";
					if (e.checkstatus == "0"){
						inputHtml = "<input type='checkbox' id='" + e.paperid + "," + e.typeid + "' class='checkboxes' style='margin-left: 10px;'/>";
					} else {
						inputHtml = "<input type='checkbox' id='" + e.paperid + "," + e.typeid + "' checked='checked' class='checkboxes' style='margin-left: 10px;'/>";
					}
					return inputHtml;
				}
			}, {
				"data" : "name"
			}, {
				"data" : "totlequestions"
			}, {
				"data" : "titlescore"
			}, {
				"data" : function(e){
					var aHtml = "";
					if(papertype == 1){
						if (e.checkstatus == "0") {
							aHtml = "["+fmtAddPaperQuestion+"]&nbsp;&nbsp;["+fmtSetPaperQuestion+"]";
						} else {
							aHtml = "<a href='#' onclick='addQuestion(this)'>["+fmtAddPaperQuestion+"]</a>&nbsp;&nbsp;<a href='#' onclick='setQuestion(this)'>["+fmtSetPaperQuestion+"]</a>";
						}
					}else{
						if (e.checkstatus == "0") {
							aHtml = "["+fmtSetCondition+"]";
						} else {
							aHtml = "<a href='#' onclick='setRandom(this)'>["+fmtSetCondition+"]</a>";
						}
					}
					return aHtml;
				}
			}
			],
			"initComplete" : function(settings, json) {
			},
			"bPaginate" : false, //翻页功能
			"bLengthChange" : false, //改变每页显示数据数量
			"bFilter" : false, //过滤功能
			"bSort" : false, //排序功能
			"bInfo" : false,//页脚信息
			"bAutoWidth" : false//自动宽度
		});
	});
}
//加载试卷内容
function loadText(data) {
	//清空TD元素
	tdEmpty();
	$("#td_papername").append(data["papername"]);
	$("#td_categoryid").append(data["categorytext"]);
	$("#td_difficult").append(data["difficulttext"]);
	$("#td_forpaper").append(data["forpaper"]);
	$("#td_remark").append(data["remark"]);
}
//加载试卷编辑框
function loadInput() {
	//清空TD元素
	tdEmpty();
	$("#td_papername").append('<input class="form-control" id="papername" name="papername" type="text" maxlength=100 style="width: 100%;" />');
	$("#td_categoryid").append('<input class="form-control" id="categoryid" name="categoryid" type="text" style="width: 100%;" />');
	$("#td_difficult").append('<input class="form-control" id="difficult" name="difficult" type="text" style="width: 100%;" />');
	$("#td_forpaper").append('<input class="form-control" id="forpaper" name="forpaper" type="text" style="width: 100%;" />');
	$("#td_remark").append('<textarea id="remark" style="width: 100%; height: 100px;" class="form-control"></textarea>');
}
//清空TD元素
function tdEmpty() {
	$("#td_papername").empty();
	$("#td_categoryid").empty();
	$("#td_difficult").empty();
	$("#td_forpaper").empty();
	$("#td_remark").empty();
}
//试卷初始化内容
function addPaperInfo() {
	//加载试卷编辑框
	loadInput();
	//加载试卷分类下拉框
	loadCategorySelect();
	//加载试题难度下拉框
	loadDifficultSelect();
}
function loadDifficultSelect(difficultVal) {
	var url = server + "/auth/common/selectOptionQuestionDifficultyList.do";
	utils.ajax.post(url, {}, function(result){
		if (difficultVal != null) {
			$("#difficult").val(difficultVal);
		} else {
			$("#difficult").val(result.data[0].id);
		}
		$("#difficult").select2({
			data : result.data,
			minimumResultsForSearch: -1,
			formatResult : this.format,
			formatSelection : this.format,
			escapeMarkup : function(m) {
				return m;
			}
		});
	});
}
function loadCategorySelect(categoryidVal) {
	var url = server + "/auth/common/selectNewtestpaperCategoryList.do";
	utils.ajax.post(url, {}, function(result){
		if (categoryidVal != null) {
			$("#categoryid").val(categoryidVal);
		} else {
			$("#categoryid").val(result.data[0].id);
		}
		$("#categoryid").select2({
			data : result.data,
			minimumResultsForSearch: -1,
			formatResult : this.format,
			formatSelection : this.format,
			escapeMarkup : function(m) {
				return m;
			}
		});
	});
}
//试卷内容
function initEditPaperInfo(paperId) {
	var url = server + "/teacher/tnewtestpaperinfo/getNewtestpaperInfo.do";
	var data = {};
	data["paperid"] = paperId;
	utils.ajax.post(url, data, function(result){
		loadText(result.data);
	});
}
//试卷内容
function editPaperInfo(paperId) {
	var url = server + "/teacher/tnewtestpaperinfo/getNewtestpaperInfo.do";
	var data = {};
	data["paperid"] = paperId;
	utils.ajax.post(url, data, function(result){
		//加载输入框组件
		loadInput();
		var resultData = result.data;

		$("#papername").val(resultData.papername);
		$("#forpaper").val(resultData.forpaper);
		$("#remark").val(resultData.remark);
		
		//加载试卷分类下拉框
		loadCategorySelect(resultData.categoryid);
		//加载试题难度下拉框
		loadDifficultSelect(resultData.difficult);
		
		$("#saveBtn").attr("type", "button");
		$("#editBtn").attr("type", "hidden");
	});
}

$('#listTable tbody').on('click', 'tr td:nth-child(1) input[type="checkbox"]', function() {
	var papertype = $("#papertype").val();
	var aHtml = "";
	var obj = $(this);
	if(obj.attr("checked")){
		if(paperId == null || paperId == ""){
			utils.dialog.alert('lms.validatePaperQuestionSaveMsg', function(){
				obj.attr("checked", false);
			});
			return;
		}
		if(papertype == 1){
			aHtml = "<a href='#' onclick='addQuestion(this)'>["+fmtAddPaperQuestion+"]</a>&nbsp;&nbsp;<a href='#' onclick='setQuestion(this)'>["+fmtSetPaperQuestion+"]</a>";
		}else{
			aHtml = "<a href='#' onclick='setRandom(this)'>["+fmtSetPaperQuestion+"]</a>";
		}
		obj.parent().parent().find("td").eq(4).html(aHtml);
	}else{
		var index = obj.closest("tr").index();
		var row = $('#listTable').DataTable().data()[index];
		var paperid = row.paperid;
		var typeid = row.typeid;
		var totlequestions = row.totlequestions;
		if(totlequestions > 0){
			utils.dialog.confirm("lms.deletePaperQuestionConfirmMsg", function(data){
				if(data){
					var updateData = {"typeid":typeid, "paperid":paperid};
					//删除试题
					var url = "";
					if(papertype == 1){
						url = server + "/teacher/tnewtestpaperquestion/deleteNewtestpaperQuestion.do";
					}else{
						url = server + "/teacher/trandompaperdifficultysum/deleteRandompaperQuestion.do";
					}
					utils.ajax.post(url, updateData, function(result){
						if(result.flag){
							if(papertype == 1){
								aHtml = "["+fmtAddPaperQuestion+"]&nbsp;&nbsp;["+fmtSetPaperQuestion+"]";
							}else{
								aHtml = "["+fmtSetCondition+"]";
							}
							$(this).parent().parent().find("td").eq(4).html(aHtml);
							//刷新
							initQuestionType(paperid);
						}
					});
				}else{
					obj.attr("checked", true);
				}
			});
		}else{
			if(papertype == 1){
				aHtml = "["+fmtAddPaperQuestion+"]&nbsp;&nbsp;["+fmtSetPaperQuestion+"]";
			}else{
				aHtml = "["+fmtSetCondition+"]";
			}
			obj.parent().parent().find("td").eq(4).html(aHtml);
		}
	}
	
});

//添加试题
function addQuestion(obj){
	var index = $(obj).closest("tr").index();
	var row = $('#listTable').DataTable().data()[index];
	var typeid = row.typeid;
	var name = row.name;
	var url = server + '/pages/teacher/newtestpaper/newtestpaperQuestionList_dlg.jsp';
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title: "("+name+")"+fmtQuestionList,
			clear:"true",
			init:function(){
				initList(paperId, typeid);
			},
			css:{"width":"large"},
			callback:function(){
			}
	};
	utils.dialog.openDialog(options);
}

//题型设置
function setQuestion(obj){
	var index = $(obj).closest("tr").index();
	var row = $('#listTable').DataTable().data()[index];
	var typeid = row.typeid;
	var name = row.name;
	var url = server + '/pages/teacher/newtestpaper/newtestpaperQuestionEdit_dlg.jsp';
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title: "("+name+")"+fmtQuestionList,
			clear:"true",
			init:function(){
				initList(paperId, typeid);
			},
			css:{"width":"large"},
			callback:function(){
			}
	};
	utils.dialog.openDialog(options);
}

//条件设置
function setRandom(obj){
	var index = $(obj).closest("tr").index();
	var row = $('#listTable').DataTable().data()[index];
	var paperid = row.paperid;
	var typeid = row.typeid;
	var name = row.name;
	var dataStr = "?paperid="+paperid+"&typeid="+typeid+"&name="+name;
	var url = server+"/pages/teacher/newtestpaper/randompaperDifficultySumList.jsp"+dataStr;
	window.location.href= url;
}

//返回
$("#returnBut").on("click", function(){
	var url = server+"/pages/teacher/newtestpaper/newtestpaperList.jsp";
	window.location.href= url;
})