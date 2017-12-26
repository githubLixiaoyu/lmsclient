var grid = new Datatable();
$(function(){
	init(grid);
	
	//试题分类
	var url = server + "/manager/tquestioncategory/selectOptionQuestionCategoryList.do";
	var data = {};
	utils.ajax.postJson(url, data, function(result){
		createSelect2("categoryid", result.data, "");
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
			src:$('#listTable'),
			onSuccess : function(grid) {
				// execute some code after table records loaded

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
					"url":server+"/manager/tknowledgepoint/selectKnowledgepointList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.point+"'/>";
					}
				},{
					"data":function(e){
//						if(e.parentpoint == 0){
//							return "<a onclick='clickFun(this);'>" + replaceNull(e.pointname) + "</a>";
//						}else{
							return replaceNull(e.pointname);
//						}
				     }
				},{
					"data":function(e){
						return replaceNull(e.categoryname);
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
	if($("#checkAll").attr("checked")){
		$('#listTable tbody').find("tr").each(function(){
			$(this).addClass('selected')
		});
	}else{
		$('#listTable tbody').find("tr").each(function(){
			$(this).removeClass('selected')
		});
	}
});

//新增
$("#addBtn").on("click", function(){
	var parentid = $("#parentpoint").val();
	var title = fmtKnowledgeAdd;
	if(parentid != "0"){
		title = fmtKnowledgeSecondLevelAdd;
	}
	var url = server + '/pages/manager/question/knowledgepointEdit_dlg.jsp';
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title: title,
			clear:"true",
			init:function(){
				initEdit(parentid);
			},
			css:{"width":"large"},
			callback:function(){
				//saveUser(grid)
			}
	};
	utils.dialog.openDialog(options)
});

//编辑
$("#editBtn").on("click", function(){
	var parentid = $("#parentpoint").val();
	var title = fmtKnowledgeEdit;
	if(parentid != "0"){
		title = fmtKnowledgeSecondLevelEdit;
	}
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.updateSelectMsg');
		return;
	}else{
		var url = server + '/pages/manager/question/knowledgepointEdit_dlg.jsp';
		var options = {
				ctrlName:"portlet-upload-dlg",
				url:url,
				title:title,
				clear:"true",
				init:function(){
					initEdit(parentid, rows[0]);
				},
				css:{"width":"large"},
				callback:function(){
					//saveUser(grid)
				}
		};
		utils.dialog.openDialog(options)
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
				var updateData = {idList:params.join(",")};
				var url = server + "/manager/tknowledgepoint/deleteKnowledgepoint.do";
				utils.ajax.post(url, updateData, function(result){
					if(result.flag){
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

function clickFun(obj){
	var index = $(obj).closest("tr").index();
	var rows = $('#listTable').DataTable().data();
	var parentid = rows[index].point;
	$("#parentpoint").val(parentid);
	var categoryname = rows[index].categoryname;
	init(grid);
	$("#listTable thead tr").eq(0).find("th").eq(1).html(fmtKnowledgeName);
	$("#typeCaption").html(fmtKnowledgeList+'（'+categoryname+'）');
	$("#returnBtn").show();
}

//返回
$("#returnBtn").on("click", function(){
	$("#typeCaption").html(fmtKnowledgeList);
	$("#parentpoint").val(0);
	init(grid);
	$("#returnBtn").hide();
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