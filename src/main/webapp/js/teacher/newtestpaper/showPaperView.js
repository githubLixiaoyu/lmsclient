$(function(){
//	$(".page-sidebar-menu").addClass("page-sidebar-menu-closed");
	$(".page-content-wrapper").removeClass("page-content-wrapper");
	$(".page-sidebar-menu").hide();
	var url = server + "/auth/common/getPaperInfoById.do";
	var data = {};
	data["paperid"] = paperId;
	utils.ajax.post(url, data, function(result){
		//console.info(result)
		var list = result.data;
		var questionTypeMap = {};
		var addHtml = '<div id="ExamName">'+papername+'</div>';
		for(var i=0;i<list.length;i++){
			var question = list[i];
			var questionOption = question.data;
			var questionNum = question.questionNum;
			var questionScore = question.questionScore;
			var typeName = question.typeName;
			var questionContent = question.questionContent;
			var typeId = question.typeId;
			addHtml += '<div class="QBody">';
			var questionNoStr = fmtQuestionNo.replace("{0}", questionNum);
			addHtml += '<div class="QName"><span style="color:red;">'+questionNoStr+'</span>('+questionScore+fmtQuestionScore+') '+typeName+' '+questionContent+'</div>';
			for(var j=0;j<questionOption.length;j++){
				var optionContent = questionOption[j].optionContent;
				var optionKey = questionOption[j].optionKey;
				addHtml += '<div>';
				if(typeId == "T00000000000003"){
					addHtml += '<div class="QOption"><input name="rad" type="checkbox">&nbsp;'+optionKey +'&nbsp;&nbsp;'+optionContent+'</div>';
				}else{
					addHtml += '<div class="QOption"><input name="rad" type="radio">&nbsp;'+optionKey +'&nbsp;&nbsp;'+optionContent+'</div>';
				}
				addHtml += '</div>';
			}
			addHtml += '</div>';
			addHtml += '</div>';
		}
		$("#body").html(addHtml);
	});
});