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
				"columns":[{
					"data":function(e){
						return replaceNull(e.papername);
				     }
				},{
					"data":function(e){
						return replaceNull(e.cagetoryname);
					}
				},{
					"data":function(e){
						return replaceNull(e.difficultyName);
					}
				},{
					"data":function(e){
						return replaceNull(e.score);
					}
				},{
					"data":function(e){
						var spanHtml = '';
						spanHtml += '<a href="#" onclick="javaScript:selectPaper(\''+e.paperid+'\',\''+e.papername+'\',\''+e.categoryid+'\',\''+e.cagetoryname+'\',\''+e.score+'\');">['+fmtSelect+']</a>';
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