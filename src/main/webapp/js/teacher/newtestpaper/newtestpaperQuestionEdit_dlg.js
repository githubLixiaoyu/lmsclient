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
			},
			loadingMessage : 'Loading...',
			dataTable : {
				//"bStateSave" : true, 
				"pageLength" : 20, // default record count per page
				"ajax" : {
					"url":server+"/teacher/tnewtestpaperquestion/selectNewtestpaperQuestionList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.questionid+"'/>";
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
						return replaceNull(e.difficultyname);
					}
				},{
					"data":function(e){
						return "<input type='text' value='"+e.score+"' class='form-control' style='width:60px;' onchange='changeScore(this)'/>";
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
});

//checkbox全选
$("#checkAll").on('click', function(){
	var questionId = "";
	if($("#checkAll").attr("checked")){
		$('#listQuestionTable tbody').find("tr").each(function(){
			$(this).addClass('selected')
		});
	}else{
		$('#listQuestionTable tbody').find("tr").each(function(){
			$(this).removeClass('selected');
		});
	}
});

$("#score").on("change", function(){
	var rows = $('#listQuestionTable').DataTable().data();
	if(rows.length > 0){
		var score = $(this).val();
		var msg = changeSameScoreConfirmMsg.replace("{0}", score);
		utils.dialog.confirm(msg, function(data){
			if(data){
				var idList = [];
				$('#listQuestionTable tbody').find("tr").each(function(){
					var questionId = $(this).find("td:eq(0) input").val();
					idList.push(questionId);
				});
				var score = $("#score").val();
				updateScore(idList, score);
			}
		});
	}
})

function changeScore(obj){
	var index = $(obj).closest("tr").index();
	var row = $('#listQuestionTable').DataTable().data()[index];
	var questionid = row.questionid;
	var idList = [];
	idList.push(questionid);
	var score = $(obj).val();
	updateScore(idList, score);
}

function updateScore(idList, score){
	//修改试题分数
	var url = server + "/teacher/tnewtestpaperquestion/updateScore.do";
	var typeid = $("#typeid").val();
	var paperid = $("#paperid").val();
	var updateData = {"idList":idList, "typeid":typeid, "paperid":paperid, "score":score};
	utils.ajax.post(url, updateData, function(result){
		if(result.flag){
			//刷新
			init(grid);
			winPar.initQuestionType(paperid);
		}
	});
}

//删除
$("#removeBtn").on("click", function(){
	var rows = $('#listQuestionTable').DataTable().rows('.selected').data();
	if(rows.length == 0){
		utils.dialog.alert('lms.deleteSelectMsg');
		return;
	}else{
		utils.dialog.confirm("lms.deleteConfirmMsg", function(data){
			if(data){
				var params = [];
				$("input[name='checkName']:checked").each(function(){
					params.push($(this).val());
				});
				var typeid = $("#typeid").val();
				var paperid = $("#paperid").val();
				var updateData = {"idList":params.join(","), "typeid":typeid, "paperid":paperid};
				var url = server + "/teacher/tnewtestpaperquestion/deleteNewtestpaperQuestionById.do";
				utils.ajax.post(url, updateData, function(result){
					if(result.flag == "1"){
						utils.dialog.alert("lms.deleteSuccess",function(){
							init(grid);
							winPar.initQuestionType(paperid);
						});
					}else{
						utils.dialog.alert("lms.deleteFail")
					}
				});
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