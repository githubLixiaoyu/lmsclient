$(function() {
	var language = navigator.userLanguage || navigator.language;
	var url = server + "/auth/common/getFunctionMenu.do";
	utils.ajax.post(url, {}, function(result){
		//console.info(result);
		if(result.flag == "1" && result.data != null){
			var list = result.data;
			var str = '';
			for(var i=0;i<list.length;i++){
				var subList = list[i];
				var childList = subList.data;
				var subFunctionName = "";
				if (language.substring(0, 2) == "zh") {
					subFunctionName = subList["functionName"];
				} else {
					subFunctionName = subList["functionNameEn"];
				}
				str += '<li>'+
				'<a href="javascript:;"><span class="title">'+subFunctionName+'</span> '+
				'<span></span> <span class="arrow"></span>'+
				'</a>'+
				'<ul class="sub-menu">';
				for(var j=0;j<childList.length;childList != null && j++){
					var childFunctionName = "";
					if (language.substring(0, 2) == "zh") {
						childFunctionName = childList[j]["functionName"];
					} else {
						childFunctionName = childList[j]["functionNameEn"];
					}
					str += '<li>'+
					'<a href="'+server+childList[j]["resourceFile"]+'">'+childFunctionName+'</a>'+
					'</li>';
				}
				str += '</ul></li>'
			}
			$(".sidebar-search-wrapper").after(str);
			//console.info(str)
		}
		var pathname = location.pathname;
		$(".page-sidebar-menu").find("a").each(function(){
			if($(this).attr("href") == pathname){
				$(this).parent().addClass("active");
				$(this).parent().parent().parent().addClass("start");
				$(this).parent().parent().parent().addClass("active");
				$(this).parent().parent().parent().addClass("open");
				$(this).parent().parent().parent().find("a:eq(0) span:eq(1)").addClass("selected");
				$(this).parent().parent().parent().find("a:eq(0) span:eq(2)").addClass("open");
			}
		});
	});
});