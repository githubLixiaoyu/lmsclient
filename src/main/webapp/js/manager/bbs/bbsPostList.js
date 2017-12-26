var grid = new Datatable();
$(function(){
	init(grid);
	//状态
	var dataValue = [{"id":"1","text":fmtDataStatus1},{"id":"0","text":fmtDataStatus0}];
	createSelect2("enableFlag", dataValue, "");
	//类型
	var url = server + "/manager/tbbstype/selectBbsTypeList.do";
	var data = {};
	utils.ajax.post(url, data, function(result){
		createSelect2("type", result.data, "");
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
					"url":server+"/manager/tbbspost/selectBbsPostList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.id+"'/>";
					}
				},{
					"data":function(e){
						return replaceNull(e.subject);
				     }
				},{
					"data":function(e){
						return "<a onclick='viewDetailFun(this);'>" + replaceNull(e.body) + "</a>";
					}
				},{
					"data":function(e){
						return replaceNull(e.type_name);
					}
				},{
					"data":function(e){
						return replaceNull(e.look_num);
					}
				},{
					"data":function(e){
						return replaceNull(e.reply_num);
					}
				},{
					"data":function(e){
						return replaceNull(e.create_date);
					}
				},{
					"data":function(e){
						if(e.enable_flag == '0'){
							return fmtDataStatus0;
						}else{
							return fmtDataStatus1;
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
				var url = server + "/manager/tbbspost/deleteBbsPost.do";
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

//查看评论
$("#viewCommentBtn").on("click", function(){
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.viewSelectMsg');
		return;
	}else{
		var id = rows[0].id;
		var subject = rows[0].subject;
		var enableFlag = rows[0].enable_flag;
		if(enableFlag != "1"){
			utils.dialog.alert('lms.viewSelectValidDataMsg1');
			return;
		}
		var dataStr = "postId="+id+"&subject="+subject;
		window.location.href= server+"/pages/manager/bbs/bbsPostListReplyList.jsp?"+dataStr;
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
function viewDetailFun(obj){
	var index = $(obj).closest("tr").index();
	var row = $('#listTable').DataTable().data()[index];
	var url = server + '/pages/manager/bbs/viewDetail_dlg.jsp';
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title:fmtDetailInfo,
			clear:"true",
			init:function(){
				$("#title").html(row.subject);
				$("#comment").html(row.body);
			},
			css:{"width":"large"},
			callback:function(){
				//saveUser(grid)
			}
	};
	utils.dialog.openDialog(options)
}