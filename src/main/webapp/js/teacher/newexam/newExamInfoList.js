var grid = new Datatable();
$(function(){
	init(grid);
	
	//考试分类
	var url = server + "/auth/common/selectNewtestpaperCategoryList.do";
	var data = {};
	utils.ajax.post(url, data, function(result){
		createSelect2("examcategroy", result.data, "");
	});
	//考试类型
	var dataValue = [{"id":"0","text":fmtExamType0},{"id":"1","text":fmtExamType1},{"id":"2","text":fmtExamType2}];
	createSelect2("examtype", dataValue, "");
	//状态
	var dataValue = [{"id":"0","text":fmtDataStatus2},{"id":"2","text":fmtDataStatus3}];
	createSelect2("status", dataValue, "");
	
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
					"url":server+"/teacher/tnewexaminfo/selectNewExamInfoList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.examid+"'/>";
					}
				},{
					"data":function(e){
						return replaceNull(e.examname);
				     }
				},{
					"data":function(e){
						return replaceNull(e.cagetoryname);
					}
				},{
					"data":function(e){
						return replaceNull(e.papername);
					}
				},{
					"data":function(e){
						if(e.examtype == "0"){
							return fmtExamType0;
						}else if(e.examtype == "1"){
							return fmtExamType1;
						}else if(e.examtype == "2"){
							return fmtExamType2;
						}else{
							return e.examtype;
						}
					}
				},{
					"data":function(e){
						if(e.status == "0"){
							return fmtDataStatus2;
						}else if(e.status == "2"){
							return fmtDataStatus3;
						}else{
							return e.status;
						}
					}
				},{
					"data":function(e){
						return replaceNull(e.creatortime);
					}
				},{
					"data":function(e){
						return replaceNull(e.createUserName);
					}
				},{
					"data":function(e){
						var spanHtml = '';
						if(e.status == "2"){
							spanHtml += '<a href="#" onclick="javaScript:viewExamResultList(\''+e.examid+'\');">['+fmtViewExamResult+']</a>';
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
	var dataStr = "?examid=";
	window.location.href= server+"/pages/teacher/newexam/newExamInfoEdit.jsp"+dataStr;
});

//编辑
$("#editBtn").on("click", function(){
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.updateSelectMsg');
		return;
	}else{
		if(rows[0].status == "2"){
			utils.dialog.alert('lms.updateSelectValidDataMsg');
		}else{
			var dataStr = "?examid="+rows[0].examid;
			window.location.href= server+"/pages/teacher/newexam/newExamInfoEdit.jsp"+dataStr;
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
				var url = server + "/teacher/tnewexaminfo/deleteNewExamInfo.do";
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
			if(rows[i].status == "0"){
				params.push(rows[i].examid);
			}
		}
		if(params.length == 0){
			utils.dialog.alert('lms.publishSelectValidDataMsg');
		}else{
			var updateData = {idList:params.join(",")};
			var url = server + "/teacher/tnewexaminfo/sendNewExamInfo.do";
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
		var examid = $("input[name='checkName']:checked").val();
		var dataStr = "?examid="+examid;
		window.location.href= server+"/pages/teacher/newexam/newexamAuthoruserList.jsp"+dataStr;
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
function viewExamResultList(examid){
	var dataStr = "?examid="+examid;
	window.location.href= server+"/pages/teacher/newexam/examResultList.jsp"+dataStr;
}