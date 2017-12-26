$(function(){
	//获取登录者信息
	getLoginUser();
});
function getLoginUser() {
	var url = server + "/ui/tpermissionsstudentinfo/selectLoginUser.do";
	utils.ajax.post(url, {}, function(result){
		if (result.data.flag == 1) {
			$("#menu-username").html(result.data.nickName + "&nbsp;/&nbsp;");
			$("#menu-login").css("display", "none");
			$("#menu-username").css("display", "inline");
			$("#menu-logout").css("display", "inline");
		} else {
			$("#menu-login").css("display", "inline");
			$("#menu-username").css("display", "none");
			$("#menu-logout").css("display", "none");
		}
	});
}