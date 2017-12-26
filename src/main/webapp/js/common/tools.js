var utils = {}; 

utils.tools = {
	isFunction : function(str) {
		if (str != null && typeof (str) == "function"){
			return true;
		}
		return false;
	},
	loading:function(options){
		options = $.extend(true, {}, options);
		var html = '';
        if (options.animate) {
        	html += '<div class="blockUI blockOverlay"';
    		html += 'style="z-index: 10000000; border: medium none; margin: 0px; padding: 0px; width: 100%; height: 100%; top: 0px; left: 0px; opacity: 0.4; cursor: wait; backgroundColor: red; background:#555 none repeat scroll 0 0; position: fixed;"></div>';
    		html += '<div class="blockUI blockMsg blockPage"';
    		html += 'style="z-index: 10000011; position: fixed; padding: 0px; margin: 0px; width: 30%; top: 40%; left: 35%; text-align: center; color: rgb(0, 0, 0); border: 0px none; cursor: wait;">';
    		html += '<div class="loading-message ">';
    		html += '<div class="block-spinner-bar">';
    		html += '<div class="bounce1"></div>';
    		html += '<div class="bounce2"></div>';
    		html += '<div class="bounce3"></div>';
    		html += '</div>';
    		html += '</div>';
    		html += '</div>';
        } else {
			html += '<div class="blockUI blockOverlay"';
    		html += 'style="z-index: 10000000; border: medium none; margin: 0px; padding: 0px; width: 100%; height: 100%; top: 0px; left: 0px; opacity: 0.4; cursor: wait; background:#555 none repeat scroll 0 0; position: fixed;"></div>';
    		html += '<div class="blockUI blockMsg blockPage"';
    		html += 'style="z-index: 10000011; position: fixed; padding: 0px; margin: 0px; width: 30%; top: 40%; left: 35%; text-align: center; color: rgb(0, 0, 0); border: 0px none; cursor: wait;">';
			html += '<div class="loading-message loading-message-boxed">';
			html += '<img src="'+ server +'/bootstrap/assets/global/img/loading-spinner-grey.gif" align="">';
			html += '<span class="loading-message-span">&nbsp;&nbsp;'+ (options.message ? options.message : 'LOADING...') +'</span></div>';
			html += '</div>';
    		html += '</div>';
        }
        $("body").append(html);
	},
	unloading:function(){
		$(".blockUI").remove();
	},
	//金额格式化
	digCon:function(value, bit){
		var regVal=/^-?(0|[1-9]\d*)(\.\d*)?$/;
		var regBit=/^[1-9]\d*$/;
		if(regVal.exec(value)){
			if(!regBit.exec(bit)){
				bit = 2;
			}
			if(value == 0){
				value = parseFloat(value).toFixed(bit);
			}else{
				value = parseFloat(parseFloat(value).toFixed(bit)).toLocaleString();
				if(value.indexOf(".") == -1){
					value += ".";
				}
				var len = value.split(".")[1];
				while(len.split("").length<bit){
					len += "0";
				}
				value = value.split(".")[0] + "." + len;
			}
			return value;
		}else{
			return "";
		}
	},
	//toFixed()
	fixedTo:function (value, bit){
		var regVal=/^-?(0|[1-9]\d*)(\.\d*)?$/;
		var regBit=/^[1-9]\d*$/;
		if(regVal.exec(value)){
			if(!regBit.exec(bit)){
				bit = 2;
			}
			value = parseFloat(value).toFixed(bit)
			if(value.indexOf(".") == -1){
				value += ".";
			}
			var len = value.split(".")[1];
			while(len.split("").length<bit){
				len += "0";
			}
			value = value.split(".")[0] + "." + len;
			return value;
		}else{
			return "";
		}
	},
	//Math.round()
	mathRound:function (value, bit){
		var regVal=/^(0|[1-9]\d*)(\.\d*)?$/;
		var regBit=/^[1-9]\d*$/;
		if(regVal.exec(value)){
			if(!regBit.exec(bit)){
				bit = 2;
			}
			value = Math.round(value).toFixed(bit)
			if(value.indexOf(".") == -1){
				value += ".";
			}
			var len = value.split(".")[1];
			while(len.split("").length<bit){
				len += "0";
			}
			value = value.split(".")[0] + "." + len;
			return value;
		}else{
			return "";
		}
	}
};