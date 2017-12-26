var grid = new Datatable();
$(function(){
	init(grid);
	
	//所属部门
	var url = server + "/auth/common/selectOptionDepartList.do";
	var data = {};
	utils.ajax.post(url, data, function(result){
		createSelect2("departid", result.data, "");
	});
	
});

//检索
$("#searchBtn").on("click", function(){
	init(grid);
});

function initUserList(){
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
					"url":server+"/teacher/tnewexamauthoruser/selectNewexamAuthoruserList.do", // ajax source
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
						return replaceNull(e.nickname);
					}
				},{
					"data":function(e){
						return replaceNull(e.departname);
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

//返回
$("#returnBtn").on("click", function(){
	var dataStr = "";
	window.location.href= server+"/pages/teacher/newexam/newExamInfoList.jsp"+dataStr;
});

//新增
$("#addBtn").on("click", function(){
	var examid = $("#examid").val();
	var url = server+"/pages/teacher/newexam/newexamAuthoruserEdit_dlg.jsp";
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title: fmtCanAddUserList,
			clear:"true",
			init:function(){
				initEdit(examid);
			},
			css:{"width":"large"},
			callback:function(){
			}
	};
	utils.dialog.openDialog(options);
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
				var examid = $("#examid").val();
				var updateData = {"idList":params, "examid":examid};
				var url = server + "/teacher/tnewexamauthoruser/deleteNewexamAuthoruser.do";
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