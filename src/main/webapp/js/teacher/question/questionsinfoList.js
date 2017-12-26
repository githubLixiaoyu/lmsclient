var grid = new Datatable();
$(function(){
	init(grid);
	
	//题型
	var url = server + "/auth/common/selectOptionQuestionstypeList.do";
	var data = {};
	utils.ajax.post(url, data, function(result){
		createSelect2("typeid", result.data, "");
	});
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
	//状态
	var dataValue = [{"id":"0","text":fmtDataStatus2}, {"id":"2","text":fmtDataStatus3}];
	createSelect2("flowstatus", dataValue, "");
	
});

$(function() {
//	$("#searchForm #content").focus();
	$('#searchForm').keypress(function(e) {
		if (e.which == 13) {
			$("#searchBtn").click();
		}
	});
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
					"url":server+"/teacher/tquestioninfo/selectQuestionsinfoList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.questionsid+"'/>";
					}
				},{
					"data":function(e){
						return replaceNull(e.content);
				     }
				},{
					"data":function(e){
						return replaceNull(e.typename);
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
				},{
					"data":function(e){
						if(e.flowstatus == "0"){
							return fmtDataStatus2;
						}else if(e.flowstatus == "2"){
							return fmtDataStatus3;
						}
					}
				},{
					"data":function(e){
						return replaceNull(e.createtime);
					}
				},{
					"data":function(e){
						return replaceNull(e.createUserName);
					}
				},{
					"data":function(e){
						var spanHtml = '';
						spanHtml += '<a href="#" onclick="javaScript:viewDetail(\''+e.questionsid+'\');">['+fmtPreview+']</a>';
						return spanHtml;
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
	var dataStr = "?questionsid=";
	window.location.href= server+"/pages/teacher/question/questionsinfoEdit.jsp"+dataStr;
});

//编辑
$("#editBtn").on("click", function(){
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.updateSelectMsg');
		return;
	}else{
		if(rows[0].flowstatus == "2"){
			utils.dialog.alert('lms.updateSelectValidDataMsg');
		}else{
			var dataStr = "?questionsid="+rows[0].questionsid;
			window.location.href= server+"/pages/teacher/question/questionsinfoEdit.jsp"+dataStr;
		}
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
				var url = server + "/teacher/tquestioninfo/deleteQuestionsinfo.do";
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

//发布
$("#sendBtn").on("click", function(){
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length == 0){
		utils.dialog.alert('lms.publishSelectMsg');
		return;
	}else{
		var params = [];
		for(var i=0;i<rows.length;i++){
			if(rows[i].flowstatus == "0"){
				params.push(rows[i].questionsid);
			}
		}
		if(params.length == 0){
			utils.dialog.alert('lms.publishSelectValidDataMsg');
		}else{
			var updateData = {idList:params.join(",")};
			var url = server + "/teacher/tquestioninfo/sendQuestionsinfo.do";
			utils.ajax.post(url, updateData, function(result){
				if(result.flag == "1"){
					utils.dialog.alert("lms.publishSuccess",function(){
						init(grid);
					});
				}else{
					utils.dialog.alert("lms.publishFail")
				}
			});
		}
	}
});

//function clickFun(obj){
//	var index = $(obj).closest("tr").index();
//	var rows = $('#listTable').DataTable().data();
//	var parentid = rows[index].categoryid;
//	$("#parentid").val(parentid);
//	var categoryname = rows[index].categoryname;
//	init(grid);
//	$("#listTable thead tr").eq(0).find("th").eq(1).html("知识点名称");
//	$("#typeCaption").html('知识点列表（'+categoryname+'）');
//	$("#returnBtn").show();
//}

//返回
$("#returnBtn").on("click", function(){
	$("#parentid").val(0);
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

//预览
function viewDetail(questionsid){
	var dataStr = "?questionsid="+questionsid;
	var url = server + '/pages/teacher/question/questionDetail_dlg.jsp'+dataStr;
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title: fmtQuestionInfo,
			clear:"true",
			init:function(){
				initShowDetail();
			},
			css:{"width":"large"},
			callback:function(){
			}
	};
	utils.dialog.openDialog(options);
}