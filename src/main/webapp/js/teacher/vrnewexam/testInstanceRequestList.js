var grid = new Datatable();
$(function(){
	init(grid);
	
	//考试分类
	var url = server + "/teacher/tvrnewtestpaper/selectVRTestTemplateList.do";
	var data = {};
	utils.ajax.post(url, data, function(result){
		createSelect2("testTemplateId", result.data, "");
	});
	//考试类型
	var dataValue = [{"id":"0","text":fmtExamType0},{"id":"1","text":fmtExamType1},{"id":"2","text":fmtExamType2}];
	createSelect2("examType", dataValue, "");
	//状态
	var url = server + "/teacher/testinstancerequest/selectOptionStatusTypeList.do";
	var data = {};
	utils.ajax.post(url, data, function(result){
		createSelect2("statusTypeId", result.data, "");
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
					"url":server+"/teacher/testinstancerequest/selectTestInstanceRequestList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.id+"'/>";
					}
				},{
					"data":function(e){
						return replaceNull(e.mileStoneName);
				     }
				},{
					"data":function(e){
						return replaceNull(e.testName);
					}
				},{
					"data":function(e){
						if(e.examType == "0"){
							return fmtExamType0;
						}else if(e.examType == "1"){
							return fmtExamType1;
						}else if(e.examType == "2"){
							return fmtExamType2;
						}else{
							return replaceNull(e.examType);
						}
					}
				},{
					"data":function(e){
						return replaceNull(e.statusType);
					}
				},{
					"data":function(e){
						return replaceNull(e.createDate);
					}
				},{
					"data":function(e){
						return replaceNull(e.etpUserName);
					}
				},{
					"data":function(e){
						var spanHtml = '';
						if(e.statusTypeId != "0"){
							spanHtml += '<a href="#" onclick="javaScript:viewExamResultList(\''+e.id+'\');">['+fmtViewExamResult+']</a>';
						}else{
							spanHtml += '['+fmtViewExamResult+']';
						}
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
	var dataStr = "?id=";
	window.location.href = server+"/pages/teacher/vrnewexam/testInstanceRequestEdit.jsp"+dataStr;
});

//编辑
$("#editBtn").on("click", function(){
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.updateSelectMsg');
		return;
	}else{
		if(rows[0].statusTypeId == "0"){
			var dataStr = "?id="+rows[0].id;
			window.location.href = server+"/pages/teacher/vrnewexam/testInstanceRequestEdit.jsp"+dataStr;
		}else{
			utils.dialog.alert('lms.updateSelectValidDataMsg1');
		}
	}
});

//删除
$("#removeBtn").on("click", function(){
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length == 0){
		utils.dialog.alert('lms.disableSelectMsg');
		return;
	}else{
		var params = [];
		for(var i=0;i<rows.length;i++){
			if(rows[i].statusTypeId != "2"){
				params.push(rows[i].testTemplateMileStoneId);
			}
		}
		if(params.length == 0){
			utils.dialog.alert('lms.checkStatus4');
		}else{
			utils.dialog.confirm("lms.disableConfirmMsg", function(data){
				if(data){
					var updateData = {idList:params.join(",")};
					var url = server + "/teacher/testinstancerequest/deleteTestInstanceRequest.do";
					utils.ajax.post(url, updateData, function(result){
						if(result.flag == "1"){
							utils.dialog.alert("lms.disableSuccess",function(){
								init(grid);
							});
						}else{
							utils.dialog.alert("lms.disableFail")
						}
					});
				}
			});
		}
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
			if(rows[i].statusTypeId == "0"){
				params.push(rows[i].testTemplateMileStoneId);
			}
		}
		if(params.length == 0){
			utils.dialog.alert('lms.publishSelectValidDataMsg1');
		}else{
			var updateData = {idList:params.join(",")};
			var url = server + "/teacher/testinstancerequest/sendTestInstanceRequest.do";
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

//分配人员
$("#addUser").on("click", function(){
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.selectOneMsg');
		return;
	}else{
		if(rows[0].statusTypeId == "2"){
			utils.dialog.alert('lms.addUserSelectValidDataMsg');
		}else{
			var id = $("input[name='checkName']:checked").val();
			var dataStr = "?id="+id;
			window.location.href= server+"/pages/teacher/vrnewexam/testInstanceRequestUsersList.jsp"+dataStr;
		}
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

//查询考试结果
function viewExamResultList(id){
	var dataStr = "?id="+id;
	window.location.href= server+"/pages/teacher/vrnewexam/examResultList.jsp"+dataStr;
}