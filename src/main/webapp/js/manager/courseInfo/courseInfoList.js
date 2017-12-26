var grid = new Datatable();
$(function(){
	init(grid);
	
	//课程类型
	var url = server + "/manager/tcoursetype/selectCourseTypeListById.do";
	var data = {};
	data["parentid"] = "0";
	utils.ajax.post(url, data, function(result){
		createSelect2("flag", result.data, "");
	});
	//二级分类
	dataValue = [{"id":"","text":""}];
	createSelect2("coursesectype", dataValue, "");
	//课件类型
	dataValue = [{"id":"1","text":fmtCourseUploadType1},{"id":"2","text":fmtCourseUploadType2},{"id":"3","text":fmtCourseUploadType3},{"id":"4","text":"VR"}];
	createSelect2("courseuploadtype", dataValue, "");
	//状态
//	var dataValue = [{"id":"0","text":"正在创建"},{"id":"2","text":"审核未过"},{"id":"6","text":"已发布"}];
	var dataValue = [{"id":"0","text":fmtDataStatus2},{"id":"6","text":fmtDataStatus3}];
	createSelect2("active", dataValue, "");
});

//检索
$("#searchBtn").on("click", function(){
	init(grid);
});

$("#flag").on("change", function(){
	//课程类型
	var url = server + "/manager/tcoursetype/selectCourseTypeListById.do";
	var data = {};
	data["parentid"] = $("#flag").val();
	utils.ajax.post(url, data, function(result){
		createSelect2("coursesectype", result.data, "");
	});
	if($("#flag").val() == ""){
		var dataValue = [{"id":"","text":""}];
		createSelect2("coursesectype", dataValue, "");
	}
});

//课程类型
function changeCourseType(id){
	
}

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
					"url":server+"/manager/tcourseinfo/selectCourseInfoList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.courseid+"'/>";
					}
				},{
					"data":function(e){
						return replaceNull(e.coursename);
				     }
				},{
					"data":function(e){
						return replaceNull(e.period)+"(h)";
					}
				},{
					"data":function(e){
						return replaceNull(e.courseTypeName);
					}
				},{
					"data":function(e){
						return replaceNull(e.courseSecTypeName);
					}
				},{
					"data":function(e){
						if(e.courseuploadtype == '1'){
							return fmtCourseUploadType1;
						}else if(e.courseuploadtype == '2'){
							return fmtCourseUploadType2;
						}else if(e.courseuploadtype == '3'){
							return fmtCourseUploadType3;
						}else if(e.courseuploadtype == '4'){
							return 'VR';
						}else{
							return '';
						}
					}
				},{
					"data":function(e){
						return replaceNull(e.createtime);
					}
				},{
					"data":function(e){
						if(e.active == '0'){
							return fmtDataStatus2;
						}else if(e.active == '6'){
							return fmtDataStatus3;
						}else{
							return '';
						}
					}
				}]
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
	var title = fmtCourseAdd;
	var url = server + '/pages/manager/courseInfo/courseInfoEdit_dlg.jsp';
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title: title,
			clear:"true",
			init:function(){
				initEdit();
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
	var title = fmtCourseEdit;
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.updateSelectMsg');
		return;
	}else{
		var url = server + '/pages/manager/courseInfo/courseInfoEdit_dlg.jsp';
		var options = {
				ctrlName:"portlet-upload-dlg",
				url:url,
				title:title,
				clear:"true",
				init:function(){
					initEdit(rows[0]);
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
		for(var i=0;i<rows.length;i++){
			if(rows[i].active == "6"){
				utils.dialog.alert('lms.deleteSelectValidDataMsg');
				return;
			}
		}
		utils.dialog.confirm("lms.deleteConfirmMsg", function(data){
			if(data){
				var params = [];
				$("input[name='checkName']:checked").each(function(){
					params.push($(this).val());
				});
				var updateData = {idList:params.join(",")};
				var url = server + "/manager/tcourseinfo/deleteCourseInfo.do";
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

//发布
$("#sendBtn").on("click", function(){
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length == 0){
		utils.dialog.alert('lms.publishSelectMsg');
		return;
	}else{
		for(var i=0;i<rows.length;i++){
			if(rows[i].active == "6"){
				utils.dialog.alert('lms.publishSelectValidDataMsg');
				return;
			}
		}
		var params = [];
		$("input[name='checkName']:checked").each(function(){
			params.push($(this).val());
		});
		var updateData = {idList:params.join(",")};
		var url = server + "/manager/tcourseinfo/sendCourseInfo.do";
		utils.ajax.post(url, updateData, function(result){
			if(result.flag){
				utils.dialog.alert("lms.publishSuccess",function(){
					init(grid);
				});
			}else{
				utils.dialog.alert("lms.publishFail")
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

//查看评论
$("#viewCommentBtn").on("click", function(){
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.viewSelectMsg');
		return;
	}else{
		var courseid = rows[0].courseid;
		var coursename = rows[0].coursename;
		var active = rows[0].active;
		if(active != "6"){
			utils.dialog.alert('lms.viewSelectValidDataMsg');
			return;
		}
		var dataStr = "courseId="+courseid+"&coursename="+coursename;
		window.location.href= server+"/pages/manager/courseComment/courseCommentList.jsp?"+dataStr;
	}
});