var rowDepart = null;
var grid = new Datatable();

function initAssignCourse(row) {
	$("#portlet-upload-dlg .col-md-12").css("height", $(window).height() * 0.7);
	if (row != null) {
		rowDepart = row;
		init(grid);
	}
}

function init(grid) {
//	if (!grid.isInit()) {
		// grid.initParam($("#searchDiv"));
		var postData = packData();
		alert(postData);
		grid.init({
					src : $('#listTable'),
					onSuccess : function(grid) {
						// execute some code after table records loaded
						alert("onSuccess");
					},
					onError : function(grid) {
						// execute some code on network or other general error
						alert("onError");
					},
					onDataLoad : function(grid) {
						// execute some code on ajax data load
						alert("onDataLoad");						
					},
					loadingMessage : 'Loading...',
					dataTable : {
						// "bStateSave" : true,
						"pageLength" : 20, // default record count per page
						"ajax" : {
							data : postData,
							"url" : server
									+ "/manager/tpermissionsstudentdepartcourse/getCourseByDepartId.do", // ajax source
						},
						"columns" : [
								{
									"data" : function(e) {
										return "<input type='checkbox' class='checkboxes' name='checkName' value='"
												+ e.courseid + "'/>";
									}
								},
								{
									"data" : function(e) {
										return replaceNull(e.coursename);
									}
								},
								{
									"data" : function(e) {
										return replaceNull(e.period) + "(h)";
									}
								},
								{
									"data" : function(e) {
										return replaceNull(e.courseTypeName);
									}
								},
								{
									"data" : function(e) {
										return replaceNull(e.courseSecTypeName);
									}
								}, {
									"data" : function(e) {
										if (e.courseuploadtype == '1') {
											return fmtCourseUploadType1;
										} else if (e.courseuploadtype == '2') {
											return fmtCourseUploadType2;
										} else if (e.courseuploadtype == '3') {
											return fmtCourseUploadType3;
										} else if (e.courseuploadtype == '4') {
											return 'VR';
										} else {
											return '';
										}
									}
								}, {
									"data" : function(e) {
										return replaceNull(e.createtime);
									}
								}, {
									"data" : function(e) {
										if (e.active == '0') {
											return fmtDataStatus2;
										} else if (e.active == '6') {
											return fmtDataStatus3;
										} else {
											return '';
										}
									}
								} ]
					}
				});
//	}
}
//
// $("#saveBtn")
// .on(
// "click",
// function() {
// if (checkData()) {
// var valideUrl = server
// + '/manager/tpermissionsstudentdepart/valideDepartName.do';
// var valideData = {};
// valideData["departname"] = $("#departname").val();
// valideData["departid"] = $("#departid").val();
// utils.ajax
// .post(
// valideUrl,
// valideData,
// function(result) {
// if (!result.data) {
// utils.dialog
// .alert("lms.departNameRepeat");
// } else {
// var data = packData();
// var url = server
// + '/manager/tpermissionsstudentdepart/savePermissionsStudentDepart.do';
// utils.ajax
// .post(
// url,
// data,
// function(result) {
// if (result.flag == "1"
// && result.data != 0) {
// utils.dialog
// .alert(
// "lms0006",
// function() {
// $(
// '#portlet-upload-dlg')
// .modal(
// 'hide');
// init(grid);
// });
// } else {
// utils.dialog
// .alert("lms.saveFail");
// }
// });
// }
// });
//
// }
// });

function packData() {
	var data = {};
	data["departid"] = rowDepart.departid;
	data["departname"] = rowDepart.departname;
	data["remarks"] = rowDepart.remarks;
	return data;
}