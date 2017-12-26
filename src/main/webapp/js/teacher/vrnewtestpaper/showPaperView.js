$(function(){
//	$(".page-sidebar-menu").addClass("page-sidebar-menu-closed");
	$(".page-content-wrapper").removeClass("page-content-wrapper");
	$(".page-sidebar-menu").hide();
	var url = server + "/teacher/tvrnewtestpaper/selectTestPaperInfo.do";
	var data = {};
	data["mileStoneId"] = mileStoneId;
	utils.ajax.post(url, data, function(result){
		var list = result.data;
		var addHtml = '<div id="ExamName">'+mileStoneNameTitle+'</div>';
		$("#body").html(addHtml);
		var tableHtml = "";
		for(var i=0;i<list.length;i++){
			var row = list[i];
			var referenceMilestoneId = row.referenceMilestoneId;
			var mileStoneName = row.mileStoneName;
			var referenceOperationId = row.referenceOperationId;
			var operationName = row.operationName;
			var score = row.score;
			var rowNum = row.rowNum;
			tableHtml =''
				+'<tr id="'+referenceMilestoneId+'">'
				+'	<td width="5%">'+mileStoneName+'</td>'
				+'	<td width="7%">'+operationName+'</td>'
				+'	<td width="8%">'+score+'</td>'
				+'</tr>';
			$("#listTable tbody").append(tableHtml);
			if(rowNum != null){
				$("#"+referenceMilestoneId).find("td").eq(0).attr("rowspan", rowNum);
				if($("#listTable tbody tr:last td").eq(0).attr("rowspan") == undefined){
					$("#listTable tbody tr:last td").eq(0).remove();
				}
			}
		}
	});
});