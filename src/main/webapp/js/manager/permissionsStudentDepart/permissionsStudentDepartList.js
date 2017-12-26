var grid = new Datatable();
$(function(){
	init(grid);
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
					"url":server+"/manager/tpermissionsstudentdepart/selectPermissionsStudentDepartList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.departid+"'/>";
					}
				},{
					"data":function(e){
						return replaceNull(e.departname);
				     }
				},{
					"data":function(e){
						return replaceNull(e.createtime);
					}
				},{
					"data":function(e){
						return replaceNull(e.remarks);
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
	var title = fmtCompanyAdd;
	var url = server + '/pages/manager/permissionsStudentDepart/permissionsStudentDepartEdit_dlg.jsp';
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
	var title = fmtCompanyEdit;
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.updateSelectMsg');
		return;
	}else{
		var url = server + '/pages/manager/permissionsStudentDepart/permissionsStudentDepartEdit_dlg.jsp';
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
	for(var i=0;i<rows.length;i++){
		if(rows[i]["parentid"] == "0"){
			utils.dialog.alert('lms.deleteRootNodeMsg');
			return;
		}
	}
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
				var url = server + "/manager/tpermissionsstudentdepart/deletePermissionsStudentDepart.do";
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

//分配课程
$("#assignCourseBtn").on("click", function(){
	var title = fmtAssignCourse;
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.updateSelectMsg');
		return;
	}else{
		var url = server + '/pages/manager/permissionsStudentDepart/permissionsStudentDepartAssignCourse_dlg.jsp';
		var options = {
				ctrlName:"portlet-upload-dlg",
				url:url,
				title:title,
				clear:"true",
				init:function(){
					initAssignCourse(rows[0]);
				},
				css:{"width":"large"},
				callback:function(){
					//saveUser(grid)
				}
		};
		utils.dialog.openDialog(options)
	}
});

//分配课程1测试
$("#assignCourseBtn2").on("click", function(){
	var title = fmtAssignCourse;
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.updateSelectMsg');
		return;
	}else{
		var url = server + '/pages/manager/permissionsStudentDepart/permissionsStudentDepartAssignCourse_dlg2.jsp';
		var options = {
				ctrlName:"portlet-upload-dlg",
				url:url,
				title:title,
				clear:"true",
				init:function(){
					initAssignCourse(rows[0]);
				},
				css:{"width":"large"},
				callback:function(){
					//saveUser(grid)
				}
		};
		utils.dialog.openDialog(options)
	}
});