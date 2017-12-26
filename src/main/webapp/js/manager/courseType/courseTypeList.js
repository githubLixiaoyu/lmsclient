var grid = new Datatable();
$(function(){
	init(grid);
	if($("#parentid").val() == 0){
		$("#returnBtn").hide();
	}else{
		$("#returnBtn").show();
	}
});

//function firstInit(){
//	init(grid);
////	var column =  $("#courseTypeTable").DataTable().column(2);
////	column.visible(false);
//}

//检索
$("#searchBtn").on("click", function(){
	init(grid);
});

function init(grid){
	if(!grid.isInit()){
		grid.initParam($("#searchDiv"));
		grid.init({
			src:$('#courseTypeTable'),
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
					"url":server+"/manager/tcoursetype/selectCourseTypeList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.id+"'/>";
					}
				},{
					"data":function(e){
						if(e.parentid == 0){
							return "<a onclick='clickFun(this);'>" + replaceNull(e.coursetypename) + "</a>";
						}else{
							return replaceNull(e.coursetypename);
						}
				     }
				},{
					"data":function(e){
						if(e.parentid == 0){
							return "<a onclick='clickFun(this);'>" + replaceNull(e.coursetypenameEn) + "</a>";
						}else{
							return replaceNull(e.coursetypenameEn);
						}
					}
				},{
					"data":function(e){
						return replaceNull(e.updatetime);
					}
				},{
					"data":function(e){
						return replaceNull(e.sort);
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

$('#courseTypeTable tbody').on('click', 'tr td:nth-child(1) input[type="checkbox"]', function() {
	$(this).parent().parent().parent().parent().toggleClass('selected');
	var rows = $('#courseTypeTable').DataTable().rows('.selected').data();
	if(rows.length == $('#courseTypeTable tbody tr').length){
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
		$('#courseTypeTable tbody').find("tr").each(function(){
			$(this).addClass('selected')
		});
	}else{
		$('#courseTypeTable tbody').find("tr").each(function(){
			$(this).removeClass('selected')
		});
	}
});

//新增
$("#addBtn").on("click", function(){
	var parentid = $("#parentid").val();
	var title = fmtFirstCategoryAdd;
	if(parentid != "0"){
		title = fmtSecondCategoryAdd;
	}
	var url = server + '/pages/manager/courseType/courseTypeEdit_dlg.jsp';
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title: title,
			clear:"true",
			init:function(){
				initCourseTypeEdit(parentid);
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
	var parentid = $("#parentid").val();
	var title = fmtFirstCategoryEdit;
	if(parentid != "0"){
		title = fmtSecondCategoryEdit;
	}
	var rows = $('#courseTypeTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.updateSelectMsg');
		return;
	}else{
		var url = server + '/pages/manager/courseType/courseTypeEdit_dlg.jsp';
		var options = {
				ctrlName:"portlet-upload-dlg",
				url:url,
				title:title,
				clear:"true",
				init:function(){
					initCourseTypeEdit(parentid, rows[0]);
				},
				css:{"width":"large"},
				callback:function(){
					//saveUser(grid)
				}
		};
		utils.dialog.openDialog(options)
	}
});

function clickFun(obj){
	var index = $(obj).closest("tr").index();
	var rows = $('#courseTypeTable').DataTable().data();
	var parentid = rows[index].id;
	$("#parentid").val(parentid);
	var coursetypename = rows[index].coursetypename;
	init(grid);
	$("#courseTypeTable thead tr").eq(0).find("th").eq(1).html(fmtCourseName);
	$("#typeCaption").html(fmtSecondCategoryList+'（'+coursetypename+'）');
	$("#returnBtn").show();
}

//删除
$("#removeBtn").on("click", function(){
	var rows = $('#courseTypeTable').DataTable().rows('.selected').data();
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
				var url = server + "/manager/tcoursetype/deleteCourseType.do";
				utils.ajax.post(url, updateData, function(result){
					if(result.flag == "1"){
						utils.dialog.alert("lms.deleteSuccess",function(){
							init(grid);
						});
					}else{
						utils.dialog.alert("lms.deleteFail");
					}
				});
			}
		});
	}
});

//返回
$("#returnBtn").on("click", function(){
	$("#typeCaption").html(fmtFirstCategoryList);
	$("#parentid").val(0);
	init(grid);
	$("#returnBtn").hide();
});