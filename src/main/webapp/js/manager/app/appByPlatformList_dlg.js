var hisGrid = new Datatable();

function initHistory(hisGrid, platform){
	if(!hisGrid.isInit()){
		hisGrid.setAjaxParam("platform", platform);
		hisGrid.init({
			src:$('#listHistoryTable'),
			onSuccess : function(hisGrid) {
				// execute some code after table records loaded

			},
			onError : function(hisGrid) {
				// execute some code on network or other general error
			},
			onDataLoad : function(hisGrid) {
				// execute some code on ajax data load
			},
			loadingMessage : 'Loading...',
			dataTable : {
				//"bStateSave" : true, 
				"pageLength" : 20, // default record count per page
				"ajax" : {
					"url":server+"/manager/tapp/selectAppList.do", // ajax source
				},
				"columns":[{
					"data":function(e){
						return replaceNull(e.platform);
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
		hisGrid.submitFilter($("#searchDiv"));
	}
}
function replaceNull(val){
	if(val == null){
		return ""
	}
	return val;
}

