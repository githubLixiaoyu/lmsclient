var grid = new Datatable();
$(function(){
	init(grid);
	
	//所属部门
	var url = server + "/auth/common/selectOptionDepartList.do";
	var data = {};
	utils.ajax.post(url, data, function(result){
		createSelect2("departid", result.data, "");
	});
	
});

//检索
$("#searchBtn").on("click", function(){
	init(grid);
});

function initUserList(){
	init(grid);
}

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
					"url":server+"/teacher/testinstancerequestusers/selectExamResultList.do", // ajax source
				},
				"columns":[{
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
				},{
					"data":function(e){
						return replaceNull(e.userTestStateTypeId);
					}
//				},{
//					"data":function(e){
//						return replaceNull(e.papername);
//					}
				},{
					"data":function(e){
						return replaceNull(e.totalScore);
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

//返回
$("#returnBtn").on("click", function(){
	var dataStr = "";
	window.location.href= server+"/pages/teacher/vrnewexam/testInstanceRequestList.jsp"+dataStr;
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