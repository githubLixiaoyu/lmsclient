var userGrid = new Datatable();
function initEdit(examid){
	$("#examid").val(examid);
	//所属部门
	var url = server + "/auth/common/selectOptionDepartList.do";
	var data = {};
	utils.ajax.post(url, data, function(result){
		createSelect2("departid", result.data, "");
	});
	initCanAddUserList(userGrid);
}

//检索
$("#searchBtn").on("click", function(){
	checkMap = {};
	initCanAddUserList(userGrid);
});

function initCanAddUserList(userGrid){
	if(!userGrid.isInit()){
		userGrid.initParam($("#searchDlgDiv"));
		userGrid.init({
			src:$('#listUserTable'),
			onSuccess : function(userGrid) {
				// execute some code after table records loaded

			},
			onError : function(userGrid) {
				// execute some code on network or other general error
			},
			onDataLoad : function(userGrid) {
				// execute some code on ajax data load
				$('#listUserTable tbody').find("tr").each(function(){
					var studentid = $(this).find("td:eq(0) input").val();
					if(checkMap[studentid]){
						$(this).find("td:eq(0) span").addClass('checked')
						$(this).addClass('selected')
					}
				});
				var rows = $('#listUserTable').DataTable().rows('.selected').data();
				if(rows.length == $('#listUserTable tbody tr').length){
					$("#checkAll").attr("checked", true);
					$("#checkAll").parent().addClass('checked');
				}else{
					$("#checkAll").attr("checked", false);
					$("#checkAll").parent().removeClass('checked');
				}
			},
			loadingMessage : 'Loading...',
			dataTable : {
				//"bStateSave" : true, 
				"pageLength" : 20, // default record count per page
				"ajax" : {
					"url":server+"/teacher/tnewexamauthoruser/selectCanSelectAuthoruserList.do", // ajax source
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
		userGrid.submitFilter($("#searchDlgDiv"));
	}
}
function replaceNull(val){
	if(val == null){
		return ""
	}
	return val;
}

$('#listUserTable tbody').on('click', 'tr td:nth-child(1) input[type="checkbox"]', function() {
	$(this).parent().parent().parent().parent().toggleClass('selected');
	var rows = $('#listUserTable').DataTable().rows('.selected').data();
	if(rows.length == $('#listUserTable tbody tr').length){
		$("#checkAll").attr("checked", true);
		$("#checkAll").parent().addClass('checked');
	}else{
		$("#checkAll").attr("checked", false);
		$("#checkAll").parent().removeClass('checked');
	}
	var studentid = $(this).val();
	if($(this).attr("checked") == "checked"){
		checkMap[studentid] = true;
	}else{
		delete checkMap[studentid];
	}
});

//checkbox全选
$("#checkAll").on('click', function(){
	var studentid = "";
	if($("#checkAll").attr("checked")){
		$('#listUserTable tbody').find("tr").each(function(){
			$(this).addClass('selected')
			studentid = $(this).find("td:eq(0) input").val();
			checkMap[studentid] = true;
		});
	}else{
		$('#listUserTable tbody').find("tr").each(function(){
			$(this).removeClass('selected')
			studentid = $(this).find("td:eq(0) input").val();
			delete checkMap[studentid];
		});
	}
});

//确定选择
$("#selectBtn").on("click", function(){
	var idList = [];
	for(var id in checkMap){
		idList.push(id);
	}
	if(idList.length == 0){
		utils.dialog.alert('lms.addUserValidateMsg');
		return;
	}else{
		var examid = $("#examid").val();
		var updateData = {"idList":idList, "examid":examid};
		var url = server + "/teacher/tnewexamauthoruser/saveNewexamAuthoruser.do";
		utils.ajax.post(url, updateData, function(result){
			if(result.flag){
				$('#portlet-upload-dlg').modal('hide');
				winPar.initUserList();
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