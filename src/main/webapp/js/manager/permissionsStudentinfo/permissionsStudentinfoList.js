var grid = new Datatable();
$(function(){
	init(grid);
	//状态
	var dataValue = [{"id":"0","text":fmtUserStatus0},{"id":"1","text":fmtUserStatus1},{"id":"2","text":fmtUserStatus2}];
	createSelect2("status", dataValue, "");
	//所属部门
	if(loginUserAuthId == 4){
		var dataValue = [{"id":loginUserDepartid,"text":loginUserDepartname}];
		createSelect2("departid", dataValue, "");
	}else{
		var url = server + "/manager/tpermissionsstudentdepart/selectAllPermissionsStudentDepartList.do";
		var data = {};
		utils.ajax.postJson(url, data, function(result){
			createSelect2("departid", result.data, "");
		});
	}
	//角色
	var url = server + "/manager/tauth/selectOptionAuthList.do";
	var data = {};
	utils.ajax.postJson(url, data, function(result){
		var dataValue = result.data;
		if(loginUserAuthId == 4){
			var resultList = [];
			for(var i=0;i<dataValue.length;i++){
				if(dataValue[i].id != "1"){
					resultList.push(dataValue[i]);
				}
			}
			createSelect2("authId", resultList, "");
		}else{
			createSelect2("authId", dataValue, "");
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
					"url":server+"/manager/tpermissionsstudentinfo/selectPermissionsStudentinfoList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.studentid+"'/>";
					}
				},{
					"data":function(e){
						return replaceNull(e.logincode);
				     }
				},{
					"data":function(e){
						return replaceNull(e.name);
					}
				},{
					"data":function(e){
						return replaceNull(e.nickname);
					}
				},{
					"data":function(e){
						return replaceNull(e.departname);
					}
				},{
					"data":function(e){
						return replaceNull(e.authName);
					}
				},{
					"data":function(e){
						return replaceNull(e.createtime);
					}
				},{
					"data":function(e){
						if(e.status == '0'){
							return fmtUserStatus0;
						}else if(e.status == '1'){
							return fmtUserStatus1;
						}else if(e.status == '2'){
							return fmtUserStatus2;
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
	var title = fmtUserAdd;
	var url = server + '/pages/manager/permissionsStudentinfo/permissionsStudentinfoEdit_dlg.jsp';
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title: title,
			clear:"true",
			init:function(){
				initCourseTypeEdit();
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
	var title = fmtUserEdit;
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.updateSelectMsg');
		return;
	}else{
		var url = server + '/pages/manager/permissionsStudentinfo/permissionsStudentinfoEdit_dlg.jsp';
		var options = {
				ctrlName:"portlet-upload-dlg",
				url:url,
				title:title,
				clear:"true",
				init:function(){
					initCourseTypeEdit(rows[0]);
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
				var url = server + "/manager/tpermissionsstudentinfo/deletePermissionsStudentinfo.do";
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