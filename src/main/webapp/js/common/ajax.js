/*****************************************************************
                  ajax工具类       
*****************************************************************/
utils.ajax={
	
	post:function(url,data,callback){
		if (typeof (data) == "function"){
			callback = data;
			data = null;
		}
		$.ajax({
		      url: url,
		      data: data,
		      dataType:"json",
		      type: "POST",
		      beforeSend:function(XMLHttpRequest){
		    	  utils.tools.loading();
			  },
		      success: function(result){
		    	 utils.tools.unloading();
		    	 if((result.flag && result.flag == 1) || (result.status && result.status == 1)){
		    		 if(callback){
		    			 callback(result);
		    		 }
		    	 } else {
		    		 console.info("fail");
		    		 if(callback){
		    			 callback(result);
		    		 }
		    		 utils.dialog.toastr(result.msg, "lms0002", "error");
		    	 }
		      },
		      error:function(result){
		    	  ajaxError(result);
		      }
		   }
		);
	},
	get:function(url,data,callback){
		if (typeof (data) == "function"){
			callback = data;
			data = null;
		}
		$.ajax({
		      url: url,
		      data: data,
		      dataType:"json",
		      type: "GET",
		      beforeSend:function(XMLHttpRequest){
		    	  utils.tools.loading();
			  },
		      success: function(result){
		    	 utils.tools.unloading();
		    	 if((result.flag && result.flag == 1) || (result.status && result.status == 1)){
		    		 if(callback){
		    			  callback(result);
		    		 }
		    	 } else {
		    		 if(callback){
		    			 callback(result);
		    		 }
		    		 utils.dialog.toastr(result.msg, "lms0002", "error");
		    	 }
		      },
		      error:function(result){
		    	  ajaxError(result);
		      }
		   }
		);
	},
	posts:function(url,data,callback){
		if (typeof (data) == "function"){
			callback = data;
			data = null;
		}
		$.ajax({
		      url: url,
		      data: data,
		      dataType:"json",
		      async:false,
		      type: "POST",
		      beforeSend:function(XMLHttpRequest){
		    	  utils.tools.loading();
			  },
		      success: function(result){
		    	 utils.tools.unloading();
		    	 if(result.flag == 1){
		    		 if(callback){
		    			  callback(result);
		    		 }
		    	 } else {
		    		 utils.dialog.toastr(result.msg, "lms0002", "error");
		    	 }
		      },
		      error:function(result){
		    	  ajaxError(result);
		      }
		   }
		);
	},
	gets:function(url,data,callback){
		if (typeof (data) == "function"){
			callback = data;
			data = null;
		}
		$.ajax({
		      url: url,
		      data: data,
		      dataType:"json",
		      async:false,
		      type: "GET",
		      beforeSend:function(XMLHttpRequest){
		    	  utils.tools.loading();
			  },
		      success: function(result){
		    	 utils.tools.unloading();
		    	 if(result.flag == 1){
		    		 if(callback){
		    			  callback(result);
		    		 }
		    	 } else {
		    		 utils.dialog.toastr(result.msg, "lms0002", "error");
		    	 }
		      },
		      error:function(result){
		    	  ajaxError(result);
		      }
		   }
		);
	},
	postJson:function(url,data,callback){

		if (typeof (data) == "function"){
			callback = data;
			data = null;
		}
		$.ajax({
		      url: url,
		      data: data,
		      async:false,
		      type: "POST",
		      dataType:"json",    
		      beforeSend:function(XMLHttpRequest){
		    	  utils.tools.loading();
			  },
		      contentType:"application/json",  //这个需要             
		      data:JSON.stringify(data),//将json数组，格式化json串的方式提交
		      success: function(result){
		    	 utils.tools.unloading();
		    	 if((result.flag && result.flag == 1) || (result.status && result.status == 1)){
		    		 if(callback){
		    			  callback(result);
		    		 }
		    	 } else {
		    		 utils.dialog.toastr(result.msg, "lms0002", "error");
		    	 }
		      },
		      error:function(result){
		    	  ajaxError(result);
		      }
		   }
		);
	},
	getAjaxParam: function(form) {
		var ajaxParams = {};
        // get all typeable inputs
		var the = this;
        $('textarea, select, input:not([type="radio"],[type="checkbox"])', form).each(function() {
        	the.setAjaxParam(ajaxParams,$(this).attr("name"), $(this).val());
        });

        // get all checkboxes
        $('input[type="checkbox"]:checked', form).each(function() {
        	the.addAjaxParam(ajaxParams,$(this).attr("name"), $(this).val());
        });

        // get all radio buttons
        $('input[type="radio"]:checked', form).each(function() {
        	the.setAjaxParam(ajaxParams,$(this).attr("name"), $(this).val());
        });
        
        return ajaxParams;
    },
	setAjaxParam: function(ajaxParams,name, value) {
        ajaxParams[name] = value;
    },

    addAjaxParam: function(ajaxParams,name, value) {
        if (!ajaxParams[name]) {
            ajaxParams[name] = [];
        }

        skip = false;
        for (var i = 0; i < (ajaxParams[name]).length; i++) { // check for duplicates
            if (ajaxParams[name][i] === value) {
                skip = true;
            }
        }

        if (skip === false) {
            ajaxParams[name].push(value);
        }
    }
}
function ajaxError(result) {
	  utils.tools.unloading();
	  if(result.responseText.indexOf("前台页面") != -1){
		  location.href = server + "/login.jsp";
	  } else if(result.responseText.indexOf("后台页面") != -1) {
		  location.href = server + "/manager.jsp";
	  }
	  utils.dialog.toastr("lms0003", "lms0002", "error");
}