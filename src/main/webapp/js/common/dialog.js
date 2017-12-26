utils.dialog = {
	toastr : function(msg,title,type) {
		if(title == "" || title == undefined){
			title = "提示";
		}
		if(type == "" || type == undefined){
			type = "info";
		}
		toastr.options = {
			"closeButton" : true,
			"debug" : false,
			"positionClass" : "toast-top-right",
			"onclick" : null,
			"showDuration" : "1000",
			"hideDuration" : "1000",
			"timeOut" : "5000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		};
		toastr.clear();
		toastr[type](utils.msg.get(msg), utils.msg.get(title));
	},
	alert:function(msg,callback){
//		bootbox.setLocale("zh_CN");
		var language = navigator.userLanguage || navigator.language;
		language = language.replace("-", "_");
		bootbox.setLocale(language);
		bootbox.alert(utils.msg.get(msg),callback);
	},
	confirm:function(msg,callback){
//		bootbox.setLocale("zh_CN");
		var language = navigator.userLanguage || navigator.language;
		language = language.replace("-", "_");
		bootbox.setLocale(language);
		bootbox.confirm(utils.msg.get(msg), callback);
	},
	prompt:function(msg,callback){
//		bootbox.setLocale("zh_CN");
		var language = navigator.userLanguage || navigator.language;
		language = language.replace("-", "_");
		bootbox.setLocale(language);
		bootbox.prompt(utils.msg.get(msg), callback);
	},
	openwinModal:function(options){
		var css = options.css;
		var this_height,this_width;
		if(css&&css["height"])
            this_height=css["height"];
          else
//            this_height="750";
        	this_height=window.screen.availHeight;
		
		if(css&&css["width"])
			this_width=css["width"];
		else
			this_width=window.screen.availWidth;
			//this_width = "1024";
		
		if(css&&css["scroll"])
			this_scroll = "no";
		else
			this_scroll = "yes";
		
		var iTop = (window.screen.availHeight - 20 - this_height) / 2;
		var iLeft = (window.screen.availWidth - 10 - this_width) / 2;
		var params = 
			'menubar:no;dialogHeight:' + this_height + 'px;' +
			'dialogWidth:' + this_width + 'px;' +
			'dialogLeft:' + iLeft + 'px;' + 
			'dialogTop:' + iTop + 'px;' +
			'scrollbars:0;' + 
			'resizable:no;' +
			'center:yes;' +
			'location:yes;' + 
			'status:no;' + 
			'minimize:no;' + 
			'maximize:no;' + 
			'scroll:' + this_scroll + ';';
		
		if(options.data != "" && options.data != undefined){
			options.url = options.url + "?" + $.param(options.data); 
		}
		
		var idx = options.url.indexOf("?");
		var timestamp=new Date().getTime();
		if(idx != -1){
			options.url += "&version="+timestamp; 
		} else {
			options.url += "?version="+timestamp;
		}
		
		var retValue = window.showModalDialog(options.url,window,params);
		return retValue;
	},
	openWinDialog:function(options){
		var control;
		if(utils.string.isEmpty(options.ctrlName)){
			control = $('#portlet-window');
		} else {
			control = $('#' + options.ctrlName);
		}
		
		var css = options.css;
		var this_height,this_width;
		if(css&&css["height"])
            this_height=css["height"];
          else
            this_height="620px";
		
		if(css&&css["width"])
			this_width=css["width"];
		
		if(this_width == "full"){
			//control.addClass("container");
			$(".modal-dialog",control).addClass("modal-full");
		} else if(this_width == "samll"){
			$(".modal-dialog",control).addClass("modal-sm");
		} else if(this_width == "large"){
			$(".modal-dialog",control).addClass("modal-lg");
		} else if(this_width != "" && this_width != undefined){
			control.attr("data-width",this_width);
		} else {
			control.removeAttr("data-width");
			//control.removeClass("container");
			$(".modal-dialog",control).removeClass("modal-full");
			$(".modal-dialog",control).removeClass("modal-sm");
			$(".modal-dialog",control).removeClass("modal-lg");
		}
		
		var title;
		if(utils.string.isEmpty(options.title)){
			title = "对话框";
		} else {
			title = options.title;
		}
		
		if(options.data != "" && options.data != undefined){
			options.url = options.url + "?" + $.param(options.data); 
		}
		
		$("h4",$(".modal-header",$(".modal-dialog",control))).html(title);
		
		
		$("#iframe-body",$(".modal-body",$(".modal-dialog",control))).css({"height": this_height});
		$("#iframe-body",$(".modal-body",$(".modal-dialog",control))).attr("src", options.url);
		
		control.modal({backdrop:'static'});
	},
	openDialog:function(options){
		var control;
		if(utils.string.isEmpty(options.ctrlName)){
			control = $('#portlet-dialog');
		} else {
			control = $('#' + options.ctrlName);
		}
		
		var css = options.css;
		var this_width;
		if(css&&css["width"])
			this_width=css["width"];
		
		if(this_width == "full"){
			//control.addClass("container");
			$(".modal-dialog",control).addClass("modal-full");
		} else if(this_width == "samll"){
			$(".modal-dialog",control).addClass("modal-sm");
		} else if(this_width == "large"){
			$(".modal-dialog",control).addClass("modal-lg");
		} else if(this_width != "" && this_width != undefined){
			control.attr("data-width",this_width);
		} else {
			control.removeAttr("data-width");
			//control.removeClass("container");
			$(".modal-dialog",control).removeClass("modal-full");
			$(".modal-dialog",control).removeClass("modal-sm");
			$(".modal-dialog",control).removeClass("modal-lg");
		}
		
		var title;
		if(utils.string.isEmpty(options.title)){
			title = "对话框";
		} else {
			title = options.title;
		}
		$("h4",$(".modal-header",$(".modal-dialog",control))).html(title);
		
		
		if(utils.tools.isFunction(options.callback)){
			$(".blue",$(".modal-footer",$(".modal-dialog",control))).off("click");
			$(".blue",$(".modal-footer",$(".modal-dialog",control))).on("click",options.callback);
		}
		
		if(utils.string.isNotEmpty(options.clear)){
			this.clearDialog(control);
		} 
		
		if(utils.string.isNotEmpty(options.url)){
			$(".modal-body",$(".modal-dialog",control)).load(options.url, '', function(){
				if(utils.tools.isFunction(options.init)){
					options.init();
				}
				control.modal({backdrop:'static'});
				
				control.on('hidden.bs.modal',function(e){
					$(".modal-body",$(".modal-dialog",control)).empty();
				});
				
			});
		} else {
			if(utils.tools.isFunction(options.init)){
				options.init();
			}
			control.modal({backdrop:'static'});
			
			control.on('hidden.bs.modal',function(e){
				$(".modal-body",$(".modal-dialog",control)).empty();
			});
		}
		
	},
	clearDialog:function(control){
		$('textarea, select, input', control).each(function() {
            $(this).val("");
        });
        $('input[type="checkbox"]', control).each(function() {
            $(this).attr("checked", false);
        });
	}
}