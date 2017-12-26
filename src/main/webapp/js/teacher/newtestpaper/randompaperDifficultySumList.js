var grid = new Datatable();
$(function(){
	//试题分类
	var url = server + "/auth/common/selectOptionQuestionCategoryList.do";
	var data = {};
	utils.ajax.post(url, data, function(result){
		createSelect2("categoryid", result.data, "");
	});
	//知识点
	var dataValue = [{"id":"","text":""}];
	createSelect2("knowledgepoint", dataValue, "");
	init(grid);
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
			createSelect2("knowledgepoint", dataValue, "");
		}else{
			createSelect2("knowledgepoint", result.data, "");
		}
	});
});

//检索
$("#searchBtn").on("click", function(){
	init(grid);
});

function refreshInit(){
	init(grid);
}

function init(grid){
	if(!grid.isInit()){
		grid.initParam($("#searchDiv"));
		grid.init({
			src:$('#listTable'),
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
					"url":server+"/teacher/trandompaperdifficultysum/selectRandompaperDifficultySumList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.id+"'/>";
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
						return replaceNull(e.remark);
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

$('#listTable tbody').on('click', 'tr td:nth-child(1) input[type="checkbox"]', function() {
	$(this).parent().parent().parent().parent().toggleClass('selected');
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length == $('#listTable tbody tr').length){
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
		$('#listTable tbody').find("tr").each(function(){
			$(this).addClass('selected')
		});
	}else{
		$('#listTable tbody').find("tr").each(function(){
			$(this).removeClass('selected');
		});
	}
});

//新增
$("#addBtn").on("click", function(){
	var paperid = $("#paperid").val();
	var typeid = $("#typeid").val();
	var url = server + '/pages/teacher/newtestpaper/randompaperDifficultyEdit_dlg.jsp';
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title: fmtSetCondition,
			clear:"true",
			init:function(){
				initEdit(paperid, typeid, "");
			},
			css:{"width":"large"},
			callback:function(){
			}
	};
	utils.dialog.openDialog(options);
});

//编辑
$("#editBtn").on("click", function(){
	var title = fmtQuestionEdit;
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.updateSelectMsg');
		return;
	}else{
		var id = rows[0].id;
		var paperid = $("#paperid").val();
		var typeid = $("#typeid").val();
		var url = server + '/pages/teacher/newtestpaper/randompaperDifficultyEdit_dlg.jsp';
		var options = {
				ctrlName:"portlet-upload-dlg",
				url:url,
				title: fmtSetCondition,
				clear:"true",
				init:function(){
					initEdit(paperid, typeid, id);
				},
				css:{"width":"large"},
				callback:function(){
				}
		};
		utils.dialog.openDialog(options);
	}
});

//删除
$("#removeBtn").on("click", function(){
	var rows = $('#listTable').DataTable().rows('.selected').data();
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
				var updateData = {"idList":params, "typeid":typeid, "paperid":paperid};
				var url = server + "/teacher/trandompaperdifficultysum/deleteRandompaperDifficultySum.do";
				utils.ajax.post(url, updateData, function(result){
					if(result.flag == "1"){
						utils.dialog.alert("lms.deleteSuccess",function(){
							init(grid);
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

//返回
$("#returnBtn").on("click", function(){
	var paperid = $("#paperid").val();
	var dataStr = "?paperId='"+paperid+"'";
	window.location.href= server+"/pages/teacher/newtestpaper/newtestpaperEdit.jsp"+dataStr;
});