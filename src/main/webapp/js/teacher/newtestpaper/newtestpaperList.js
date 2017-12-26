var grid = new Datatable();
$(function(){
	init(grid);
	
	//试题难度
	var url = server + "/auth/common/selectOptionQuestionDifficultyList.do";
	var data = {};
	utils.ajax.post(url, data, function(result){
		createSelect2("difficult", result.data, "");
	});
	//试卷分类
	var url = server + "/auth/common/selectNewtestpaperCategoryList.do";
	var data = {};
	utils.ajax.post(url, data, function(result){
		createSelect2("categoryid", result.data, "");
	});
	
	//出题方式
	var dataValue = [{"id":"1","text":fmtPaperType1}, {"id":"2","text":fmtPaperType2}];
	createSelect2("papertype", dataValue, "");
	//状态
	var dataValue = [{"id":"0","text":fmtDataStatus2}, {"id":"2","text":fmtDataStatus3}];
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
					"url":server+"/teacher/tnewtestpaperinfo/selectNewTestpaperInfoList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "<input type='checkbox' class='checkboxes' name='checkName' value='"+e.paperid+"'/>";
					}
				},{
					"data":function(e){
						return replaceNull(e.papername);
				     }
				},{
					"data":function(e){
						if(e.papertype == 1){
							return fmtPaperType1;
						}else if(e.papertype == 2){
							return fmtPaperType2;
						}else {
							return e.papertype;
						}
					}
				},{
					"data":function(e){
						return replaceNull(e.cagetoryname);
					}
				},{
					"data":function(e){
						if(e.status == 0){
							return fmtDataStatus2;
						}else if(e.status == 2){
							return fmtDataStatus3;
						}else{
							return e.status;
						}
					}
				},{
					"data":function(e){
						return replaceNull(e.difficultyName);
					}
				},{
					"data":function(e){
						return replaceNull(e.createtime);
					}
				},{
					"data":function(e){
						return replaceNull(e.createUserName);
					}
				},{
					"data":function(e){
						var spanHtml = '';
						spanHtml += '<a href="#" onclick="javaScript:viewpaper(\''+e.paperid+'\',\''+e.papername+'\');">['+fmtPreview+']</a>';
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

function selectPaper(paperid, papername, categoryid, cagetoryname, score){
	$('#portlet-upload-dlg').modal('hide');
	winPar.changeSelectPaper(paperid, papername, categoryid, cagetoryname, score);
}

function viewpaper(paperid, papername){
	var dataStr = "?paperid='"+paperid+"'&papername='"+papername+"'";
	var url = server+"/pages/teacher/newtestpaper/showPaperView.jsp"+dataStr;
	window.open(url);
}

function replaceNull(val){
	if(val == null){
		return ""
	}
	return val;
}

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
	var url = server + '/pages/teacher/newtestpaper/changeNewtestpaperType_dlg.jsp';
	var options = {
			ctrlName:"portlet-upload-dlg",
			url:url,
			title: fmtAddTestpaperTitle,
			clear:"true",
			init:function(){
//				initEdit(id, content);
			},
			css:{"width":"small"},
			callback:function(){
			}
	};
	utils.dialog.openDialog(options);
});

//编辑
$("#editBtn").on("click", function(){
	var title = fmtTestpaperEdit;
	var rows = $('#listTable').DataTable().rows('.selected').data();
	if(rows.length != 1){
		utils.dialog.alert('lms.updateSelectMsg');
		return;
	}else{
		if(rows[0].status == "2"){
			utils.dialog.alert('lms.updateSelectValidDataMsg');
		}else{
			var dataStr = "?paperId='"+rows[0].paperid+"'&papertype="+rows[0].papertype;
			window.location.href= server+"/pages/teacher/newtestpaper/newtestpaperEdit.jsp"+dataStr;
		}
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
				params.push(rows[i].paperid);
			}
		}
		if(params.length == 0){
			utils.dialog.alert('lms.publishSelectValidDataMsg');
		}else{
			var msg = "";
			for(var i=0;i<rows.length;i++){
				if(rows[i].totalQuestions == 0){
					msg += "<b>"+ rows[i].papername+"</b> "+fmtTestpaperSizeMsg+";<br/>";
				}
			}
			if(msg != ""){
				msg += fmtTestpaperPublishValidDataMsg;
				utils.dialog.alert(msg);
			}else{
				var updateData = {idList:params.join(",")};
				var url = server + "/teacher/tnewtestpaperinfo/sendNewTestpaperInfo.do";
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
	}
});