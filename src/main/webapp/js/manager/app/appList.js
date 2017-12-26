var grid = new Datatable();
$(function(){
	init(grid);
	//平台类型
	var dataValue = [{"id":"android","text":"android"},{"id":"ios","text":"ios"}];
	createSelect2("platform", dataValue, "");
	//强制更新
	var dataValue = [{"id":"1","text":fmtUpdateYes},{"id":"0","text":fmtUpdateNo}];
	createSelect2("mustUpdate", dataValue, "");
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
					"url":server+"/manager/tapp/selectAppGroupByPlatformList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.id+"'/>";
					}
				},{
					"data":function(e){
						return "<a onclick='showListFun(this);'>" + replaceNull(e.platform) + "</a>";
				     }
				},{
					"data":function(e){
						return replaceNull(e.versionCode);
					}
				},{
					"data":function(e){
						return replaceNull(e.filename);
					}
				},{
					"data":function(e){
						if(e.mustUpdate == 1){
							return fmtUpdateYes;
						}else{
							return fmtUpdateNo;
						}
					}
				},{
					"data":function(e){
						return replaceNull(e.createDate);
					}
				},{
					"data":function(e){
						return replaceNull(e.createUser);
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
				var url = server + "/manager/tapp/deleteApp.do";
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

$("#addBtn").on("click", function(){
	var url = server + '/pages/manager/app/appEdit_dlg.jsp';
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title: fmtAddNewVersion,
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

function showListFun(obj){
	var rows = $('#listTable').DataTable().data();
	var index = $(obj).closest("tr").index();
	
	var url = server + '/pages/manager/app/appByPlatformList_dlg.jsp';
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title: fmtHistoryVersion,
			clear:"true",
			init:function(){
				initHistory(hisGrid, rows[index].platform)
			},
			css:{"width":"large"},
			callback:function(){
				//saveUser(grid)
			}
	};
	utils.dialog.openDialog(options)
}