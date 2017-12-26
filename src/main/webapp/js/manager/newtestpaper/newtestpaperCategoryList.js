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
					"url":server+"/manager/tnewtestpapercategory/selectNewtestpaperCategoryList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.pointsid+"'/>";
					}
				},{
					"data":function(e){
//						if(e.pointsparentid == '0'){
//							return "<a onclick='clickFun(this);'>" + replaceNull(e.pointsname) + "</a>";
//						}else{
							return replaceNull(e.pointsname);
//						}
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
	var parentid = $("#pointsparentid").val();
	var title = fmtTestaperCategoryAdd;
	if(parentid != "0"){
		title = fmtTestaperCategorySecondLevelAdd;
	}
	var url = server + '/pages/manager/newtestpaper/newtestpaperCategoryEdit_dlg.jsp';
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
	var parentid = $("#pointsparentid").val();
	var title = fmtTestaperCategoryEdit;
	if(parentid != "0"){
		title = fmtTestaperCategorySecondLevelEdit;
	}
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.updateSelectMsg');
		return;
	}else{
		var url = server + '/pages/manager/newtestpaper/newtestpaperCategoryEdit_dlg.jsp';
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
				var url = server + "/manager/tnewtestpapercategory/deleteNewtestpaperCategory.do";
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
	var pointsid = rows[index].pointsid;
	$("#pointsparentid").val(pointsid);
	var pointsname = rows[index].pointsname;
	init(grid);
	$("#listTable thead tr").eq(0).find("th").eq(1).html(fmtTestaperCategoryName);
	$("#typeCaption").html(fmtTestaperCategorySecondLevelList+'（'+pointsname+'）');
	$("#returnBtn").show();
}

//返回
$("#returnBtn").on("click", function(){
	$("#typeCaption").html(fmtTestaperCategoryList);
	$("#pointsparentid").val('0');
	init(grid);
	$("#returnBtn").hide();
});