var grid = new Datatable();
function initList(paperId, typeid){
	$("#paperid").val(paperId);
	$("#typeid").val(typeid);
	init(grid);
}
$(function(){
	//试题难度
	var url = server + "/auth/common/selectOptionQuestionDifficultyList.do";
	var data = {};
	utils.ajax.post(url, data, function(result){
		createSelect2("difficulty", result.data, "");
	});
	//试题分类
	var url = server + "/auth/common/selectOptionQuestionCategoryList.do";
	var data = {};
	utils.ajax.post(url, data, function(result){
		createSelect2("pointsid", result.data, "");
	});
	//知识点
	var dataValue = [{"id":"","text":""}];
//	createSelect2("point", dataValue, "");
	
});

//试题分类 ,知识点 联动
$("#categoryid").on("change", function(){
	var categoryid = $(this).val();
	//知识点
	var url = server + "/auth/common/selectOptionKnowledgepointList.do";
	var data = {"categoryid" : categoryid};
	utils.ajax.post(url, data, function(result){
		if(result.data.length == 0 || categoryid == ""){
			var dataValue = [{"id":"","text":""}];
			createSelect2("point", dataValue, "");
		}else{
			createSelect2("point", result.data, "");
		}
	});
});

//检索
$("#searchBtn").on("click", function(){
	checkMap = {};
	init(grid);
});

function init(grid){
	if(!grid.isInit()){
		grid.initParam($("#searchDiv"));
		grid.init({
			src:$('#listQuestionTable'),
			onSuccess : function(grid) {
				// execute some code after table records loaded
				if (grid.flag == "1") {
					utils.dialog.toastr(grid.msg,"lms0009");
				} else {
				  	utils.dialog.toastr(grid.msg,"lms.searchFail","error");
				}
			},
			onError : function(grid) {
				// execute some code on network or other general error
			},
			onDataLoad : function(grid) {
				// execute some code on ajax data load
				$('#listQuestionTable tbody').find("tr").each(function(){
					var questionId = $(this).find("td:eq(0) input").val();
					if(checkMap[questionId]){
						$(this).addClass('selected')
					}
				});
				var rows = $('#listQuestionTable').DataTable().rows('.selected').data();
				if(rows.length == $('#listQuestionTable tbody tr').length){
					$("#checkAll").attr("checked", true);
					$("#checkAll").parent().addClass('checked');
				}else{
					$("#checkAll").attr("checked", false);
					$("#checkAll").parent().removeClass('checked');
				}
			},
			loadingMessage : 'Loading...',
			dataTable : {
				//"bStateSave" : true, 
				"pageLength" : 20, // default record count per page
				"ajax" : {
					"url":server+"/teacher/tquestioninfo/selectQuestionsinfoList.do", // ajax source
				},
				"columns":[{"data":function(e){
						if(checkMap[e.questionsid]){
							return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.questionsid+"' checked/>";
						}else{
							return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.questionsid+"'/>";
						}
					}
				},{
					"data":function(e){
						return replaceNull(e.content);
				     }
				},{
					"data":function(e){
						return replaceNull(e.categoryname);
					}
				},{
					"data":function(e){
						if(e.pointname == null){
							return fmtAll;
						}else{
							return replaceNull(e.pointname);
						}
					}
				},{
					"data":function(e){
						return replaceNull(e.difficultyName);
					}
				}
				]
			}
		});
		
	} else {
		grid.submitFilter($("#searchDiv"));
	}
}
function replaceNull(val){
	if(val == null){
		return ""
	}
	return val;
}

$('#listQuestionTable tbody').on('click', 'tr td:nth-child(1) input[type="checkbox"]', function() {
	$(this).parent().parent().parent().parent().toggleClass('selected');
	var rows = $('#listQuestionTable').DataTable().rows('.selected').data();
	if(rows.length == $('#listQuestionTable tbody tr').length){
		$("#checkAll").attr("checked", true);
		$("#checkAll").parent().addClass('checked');
	}else{
		$("#checkAll").attr("checked", false);
		$("#checkAll").parent().removeClass('checked');
	}
	var questionId = $(this).val();
	if($(this).attr("checked") == "checked"){
		checkMap[questionId] = true;
	}else{
		delete checkMap[questionId];
	}
});

//checkbox全选
$("#checkAll").on('click', function(){
	var questionId = "";
	if($("#checkAll").attr("checked")){
		$('#listQuestionTable tbody').find("tr").each(function(){
			$(this).addClass('selected')
			questionId = $(this).find("td:eq(0) input").val();
			checkMap[questionId] = true;
		});
	}else{
		$('#listQuestionTable tbody').find("tr").each(function(){
			$(this).removeClass('selected');
			questionId = $(this).find("td:eq(0) input").val();
			delete checkMap[questionId];
		});
	}
});

//确定添加
$("#selectBtn").on("click", function(){
	var idList = [];
	for(var id in checkMap){
		idList.push(id);
	}
	if(idList.length == 0){
		utils.dialog.alert('lms.addQuestiosSizeZeroMsg');
		return;
	}else{
		var typeid = $("#typeid").val();
		var paperid = $("#paperid").val();
		var updateData = {"idList":idList, "typeid":typeid, "paperid":paperid};
		var url = server + "/teacher/tnewtestpaperquestion/addNewtestpaperQuestion.do";
		utils.ajax.post(url, updateData, function(result){
			if(result.flag){
				$('#portlet-upload-dlg').modal('hide');
				winPar.initQuestionType(paperid);
			}
		});
	}
});

function createSelect2(key,dataVal,val){
	if(dataVal!=""){
    	var select2 = $("#" + key);
    	select2.val("");
    	select2.empty();
    	select2.select2({
			placeholder : fmtPleaseSelect,
			data:dataVal,
			allowClear : true,
			formatResult : this.format,
			formatSelection : this.format,
			escapeMarkup : function(m) {
				return m;
			}
		});
    	if(val != ""){
            select2.select2("val", val);	
    	}
	}
}

//刷新
function initUserList(){
	init(grid);
}