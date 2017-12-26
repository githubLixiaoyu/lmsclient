var grid = new Datatable();
$(function(){
	init(grid);
	
	//考试分类
	var url = server + "/teacher/tvrnewtestpaper/selectVRTestTemplateList.do";
	var data = {};
	utils.ajax.post(url, data, function(result){
		createSelect2("testTemplateId", result.data, "");
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
					"url":server+"/teacher/tvrnewtestpaper/selectVRNewTestpaperInfoList.do", // ajax source
				},
				"columns":[{
					"data":function(e){
						return replaceNull(e.mileStoneName);
				     }
				},{
					"data":function(e){
						return replaceNull(e.testName);
					}
				},{
					"data":function(e){
						return replaceNull(e.paperScore);
					}
				},{
					"data":function(e){
						var spanHtml = '';
						spanHtml += '<a href="#" onclick="javaScript:selectPaper(\''+e.testTemplateId+'\',\''+e.mileStoneName+'\',\''+e.id+'\',\''+e.paperScore+'\',\''+e.allowMaximumTime+'\',\''+e.testName+'\');">['+fmtSelect+']</a>';
						spanHtml += '<a href="#" onclick="javaScript:viewpaper(\''+e.id+'\',\''+e.mileStoneName+'\');">['+fmtPreview+']</a>';
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

function selectPaper(testTemplateId, mileStoneName, testTemplateMileStoneId, paperScore, time, testName){
	$('#portlet-upload-dlg').modal('hide');
	winPar.changeSelectPaper(testTemplateId, mileStoneName, testTemplateMileStoneId, paperScore, time, testName);
}

function viewpaper(id, mileStoneName){
	var dataStr = "?id='"+id+"'&mileStoneName='"+mileStoneName+"'";
	var url = server+"/pages/teacher/vrnewtestpaper/showPaperView.jsp"+dataStr;
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