utils.string = {
	trim : function(str) {
		return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');
	},
	isEmpty : function(str) {
		if (str == null || typeof str == "undefined" || str.trim() == "") {
			return true;
		} else {
			return false;
		}
	},
	isNotEmpty : function(str) {
		return !this.isEmpty(str);
	},
	isBlank : function(str) {
		return (!str || 0 === this.trim(str).length);
	},
	isNotBlank : function(str) {
		return !this.isBlank(str);
	},
	startwith : function(data, str) {
		if (str == null || str == "" || data == null || data.length == 0
				|| str.length > data.length)
			return false;
		if (data.substr(0, str.length) == str)
			return true;
		else
			return false;
		return true;

	},
	endwith : function(data, str) {
		if (str == null || str == "" || data == null || data.length == 0
				|| str.length > data.length)
			return false;
		if (data.substring(data.length - str.length) == str)
			return true;
		else
			return false;
		return true;
	},
	/**
     * 填补字符串右边
     * @param   str        {String}  原字符串
     * @param   size       {Number}  填补后的长度
     * @param   character  {String}  填补的字符，如果不填则为空字符' '
     * @return  返回填补后的字符串
     */
    rightPad : function(str, size, character) {
        var result = '' + str;
        if(Object.prototype.toString.call(character) !== '[object String]'){
            character = ' ';
        }
        while (result.length < size) {
            result += character;
        }
        return result;
    }
};