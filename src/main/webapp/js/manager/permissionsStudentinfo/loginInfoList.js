var grid = new Datatable();
$(function(){
	init(grid);
	//所属部门
	if(loginUserAuthId == 4){
		var dataValue = [{"id":loginUserDepartid,"text":loginUserDepartname}];
		createSelect2("departid", dataValue, "");
	}else{
		var url = server + "/manager/tpermissionsstudentdepart/selectAllPermissionsStudentDepartList.do";
		var data = {};
		utils.ajax.postJson(url, data, function(result){
			createSelect2("departid", result.data, "");
		});
	}
	//角色
	var url = server + "/manager/tauth/selectOptionAuthList.do";
	var data = {};
	utils.ajax.postJson(url, data, function(result){
		var dataValue = result.data;
		if(loginUserAuthId == 4){
			var resultList = [];
			for(var i=0;i<dataValue.length;i++){
				if(dataValue[i].id != "1"){
					resultList.push(dataValue[i]);
				}
			}
			createSelect2("authId", resultList, "");
		}else{
			createSelect2("authId", dataValue, "");
		}
	});
	
	if (jQuery().datepicker) {
		$('#startDate').datepicker({
			rtl : Metronic.isRTL(),
			orientation : "right",
			format : 'yyyy/mm/dd',
			autoclose : true,
			language : 'zh-CN'
		}).on('changeDate',function(e){  
		    var startDate = e.date;  
		    $('#endDate').datepicker('setStartDate',startDate);  
		});
		$('#endDate').datepicker({
			rtl : Metronic.isRTL(),
			orientation : "right",
			format : 'yyyy/mm/dd',
			autoclose : true,
			language : 'zh-CN'
		});
	}
});

//检索
$("#searchBtn").on("click", function(){
	var startTimes = $("#startTimes").val();
	var endTimes = $("#endTimes").val();
	if((startTimes != "" && startTimes.search("^-?\\d+$") != 0) || (endTimes != "" && endTimes.search("^-?\\d+$") != 0)){
		utils.dialog.alert(fmtTimes+fmtCheckInteger);
	}else{
		init(grid);
	}
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
				var oTable = $('#listTable').dataTable();
		        var oSettings = oTable.fnSettings();
		        // 获取页码值
		        var pageStart = oSettings._iDisplayStart;
		        var allTimes = 0;
		        $("#listTable tbody tr").each(function(){
		        	var index = $(this).closest("tr").index()+1;
		        	var curIndex = pageStart+index;
		        	$(this).find("td").eq(0).html(curIndex);
		        	var times = $(this).find("td").eq(7).html();
		        	allTimes += parseInt(times);
		        });
		        $("#allTimes").html(allTimes);
			},
			loadingMessage : 'Loading...',
			dataTable : {
				//"bStateSave" : true, 
				"pageLength" : 20, // default record count per page
				"ajax" : {
					"url":server+"/manager/tlogininfo/selectLoginInfoList.do", // ajax source
				},
				"columns":[{"data":function(e){
						return "";
					}
				},{
					"data":function(e){
						return replaceNull(e.logincode);
				     }
				},{
					"data":function(e){
						return replaceNull(e.name);
					}
				},{
					"data":function(e){
						return replaceNull(e.nickname);
					}
				},{
					"data":function(e){
						return replaceNull(e.departname);
					}
				},{
					"data":function(e){
						return replaceNull(e.authName);
					}
				},{
					"data":function(e){
						return replaceNull(e.lastLoginDate);
					}
				},{
					"data":function(e){
						return replaceNull(e.loginNum);
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