utils.file={
		/*
		 * 1、页面添加
			<span class="btn green fileinput-button"> <i class="fa fa-plus"></i>
				<span> Add files... </span> <input type="file" id="fileUpload" name="file" class="input"/>
			</span>
			2、页面加载事件中;
		 	$('#fileUpload').on('change',function(){
				uploadFile();
			});
		 */
		uploadFile:function(url,fileId,callback){
			 //$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
			if (typeof (url) == "function"){
				callback = url;
				url = null;
			}
			if(!url){
				url = server + '/file/upload.do';
			}
			if(!fileId){
				fileId = 'fileUpload';
			}
			Metronic.blockUI({
		        animate: true,
		        overlayColor: 'none'
		    });
			//执行上传文件操作的函数  
		   $.ajaxFileUpload({  
		       //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)  
		       url:url,  
		       secureuri:false,                           //是否启用安全提交,默认为false   
		       fileElementId:fileId,               //文件选择框的id属性  
		       dataType:'text',                           //服务器返回的格式,可以是json或xml等  
		       success:function(data, status){            //服务器响应成功时的处理函数  
					data = data.replace(/<pre.*?>/g, '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre style="....">text</pre>前后缀  
					data = data.replace(/<PRE.*?>/g, '');  
					data = data.replace("<PRE>", '');  
					data = data.replace("</PRE>", '');  
					data = data.replace("<pre>", '');  
				   data = data.replace("</pre>", '');  
		           data = JSON.parse(data);
		           Metronic.unblockUI();
		           if("1" == data.flag){
		        	   if(callback){
			           		callback();
			           	}
		           } else {
		        	   utils.dialog.toastr(data.msg, "lms0010", "error");
		           }
		       },  
		       error:function(data, status, e){ //服务器响应失败时的处理函数  
		    	   Metronic.unblockUI();
		    	   utils.dialog.toastr(data.msg, "lms0010", "error");
		       }  
		   });
			$('#' + fileId).replaceWith('<input type="file" id="'+ fileId +'" name="file"/>');
			$('#' + fileId).on('change',function(){
				utils.file.uploadFile(url,fileId,callback);
			});
		},
		uploadAccessory:function(url,fileId,callback){
			 //$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
			if (typeof (url) == "function"){
				callback = url;
				url = null;
			}
			if(!url){
				url = server + '/file/upload.do';
			}
			if(!fileId){
				fileId = 'fileUpload';
			}
			Metronic.blockUI({
		        animate: true,
		        overlayColor: 'none'
		    });
			//执行上传文件操作的函数  
		   $.ajaxFileUpload({  
		       //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)  
		       url:url,  
		       secureuri:false,                           //是否启用安全提交,默认为false   
		       fileElementId:fileId,               //文件选择框的id属性  
		       dataType:'text',                           //服务器返回的格式,可以是json或xml等  
		       success:function(data, status){  
		    	   //服务器响应成功时的处理函数  
					data = data.replace(/<pre.*?>/g, '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre style="....">text</pre>前后缀  
					data = data.replace(/<PRE.*?>/g, '');  
					data = data.replace("<PRE>", '');  
					data = data.replace("</PRE>", '');  
					data = data.replace("<pre>", '');  
				   data = data.replace("</pre>", '');  
		           data = JSON.parse(data);
		           Metronic.unblockUI();
		           if("1" == data.flag){
		        	   if(callback){
			           		callback(data);
			           	}
		           } else {
		        	   utils.dialog.toastr(data.msg, "lms0010", "error");
		           }
		       },  
		       error:function(data, status, e){ //服务器响应失败时的处理函数  
		    	   Metronic.unblockUI();
		    	   utils.dialog.toastr(data.msg, "lms0010", "error");
		       }  
		   });
		},
		/**
		 * <iframe name="fileFrame" style="display:none;"></iframe>
			<form id="file" style="display:none;" target="fileFrame" method="post" action="">
				<input type="hidden" name="fileName">
			</form>
		 */
		downloadFile:function(fileName,url){
			if(!url){
				url = server + '/file/download.do';
			}
			$("#file").attr("action",url);
			$("#file input[name='fileName']").val(fileName);
			$("#file").submit();
		}
}