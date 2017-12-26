var now = new Date();
var absnow = now;
var Win = navigator.userAgent.indexOf('Win') != -1;
var Mac = navigator.userAgent.indexOf('Mac') != -1;
var X11 = navigator.userAgent.indexOf('X11') != -1;
var Moz = navigator.userAgent.indexOf('Gecko') != -1;
var winflg = 1;
// wan xing add 2008/05/05 >>
//更新flag
var updateFlag = false;
// wan xing add 2008/05/05 <<

//popup
var popwin;
//the Return Name&Value of the popup
var popReturnName="";
var popReturnValue="";

//for Special Use
var popFlg="";
// wan xing add 2008/04/21 >>
var popReturnValue2 = "";
// wan xing add 2008/04/21 <<

var common_submitFlg = "0";

//设置更新flag
function setUpdateFlag() {
	updateFlag = true;
	top.updateFlag = true;
}

//离开页面时的处理.如页面已作过改动，则提示用户，按「确定」将放弃修改
function checkUpdate(flag) {
	var retValue = true;

	//程序进行中


	if (flag == '3') {
		return false;
	}

	if (flag) {
		retValue = confirm('数据已修改且未保存,是否放弃修改?');
	}
	return retValue;
}

function func_ShowCalendar(oj, arg1, arg2) {
	oj.blur();
	if (!arguments[1]) {
		arg1 = "dd/mmm/yy";
	} else if (arg1 == 0) {
		arg1 = "dd/mmm/yy";
	}
	if (!arguments[2]) {
		arg2 = 0;
	}
	if (!Moz) {
		if (arguments[2] || arguments[2] == 0) {
			winflg = 0;
		}
	}
	if (arg2 == 0) {
		now = new Date();
	}
	nowdate = now.getDate();
	nowmonth = now.getMonth();
	nowyear = now.getYear();
	if (nowmonth == 11 && arg2 > 0) {		//Dec & arg1 > 0
		nowmonth = -1 + arg2;
		nowyear++;			//month arg1-1;
	} else if (nowmonth == 0 && arg2 < 0) {	//Jan & arg1 < 0
		nowmonth = 12 + arg2;
		nowyear--;			//month arg1+12;
	} else {
		nowmonth += arg2;		//Feb - Nov ; Month =+ arg1
	}
	if (nowyear < 1900) {
		nowyear = 1900 + nowyear;
	}
	now = new Date(nowyear, nowmonth, 1);
	nowyyyymm = nowyear * 100 + nowmonth;
	nowtitleyyyymm = SDateFormat("mmm/yyyy", nowyear, (nowmonth + 1), nowdate);
//	week = new Array('SUN','MON','TUE','WED','THU','FRI','SAT');
	week = new Array('日','一','二','三','四','五','六');
	if(winflg) {
		var w = 289;
		var h = 190;
		//Size
		if (Moz) {
			w+=15;
			h+=40;
		} else if (Win) {
			w+=0;
			h+=0;
		} else if (Mac) {
			w+=8;
			h+=22;
		} else if (X11) {
			w+=5;
			h+=46;
		}
		//var x = 100;
		//var y = 20;
		//if (document.all) {
		//	x = window.event.screenX - 140;
		//	y = window.event.screenY + 16;
		//} else if (document.layers || document.getElementById) {
		//	x += window.screenX;
		//	y += window.screenY;
		//}
		y = (top.screen.availHeight - h) / 2;
		x = (top.screen.Width - w) / 2;
		var xxx = "WIN_ID" + eval(parseInt((new Date()).getTime())) + eval(parseInt(Math.random() * 100000));
		GRS_mkSubWin('', xxx, x, y, w, h);
	}
	//Base Date
	fstday = now;
	startday = fstday - ( fstday.getDay() * 1000 * 60 * 60 * 24 ); // First Sunday
	startday = new Date(startday);
	// HTML
	ddata = '';
	ddata += '<HTML>\n';
	ddata += '<HEAD>';
	ddata += '<META HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=UTF-8">\n';
	ddata += '<TITLE>Calendar</TITLE>\n';
	ddata += '<link rel="stylesheet" href="css/calendar.css" type="text/css">';
	ddata += '</HEAD>\n';
	ddata += '<SCRIPT language="JavaScript">\n';
	ddata += '<!--\n';
	ddata += 'function toToday() {\n';
	ddata += '    try {\n';
	ddata += ' 		  if (document.all.todayfocus != undefined){   \n';
	ddata += '        	setTimeout("document.all.todayfocus.focus()", 50);\n';
	ddata += '        }\n';
	ddata += '    } catch (e) {\n';
	ddata += '    }\n';
	ddata += '}\n';
	ddata += '-->\n';
	ddata += '</SCRIPT>\n';
	//add by liyg 添加禁止某些键
	ddata += '<script language="JavaScript" SRC="js/common/common.js"></script>';
	ddata += '<BODY class="page" onLoad="toToday();">\n';
	ddata += '<FORM>\n';
	ddata += '<div id="layer" class="calendarlayer">\n';
	ddata += '<TABLE width="289" BORDER=0 cellpadding="2" cellspacing="2">\n';
	//-MONTH
	ddata += '<TR id="trmonth">\n';
	ddata += '<TH COLSPAN=7 class="header">\n';
	ddata += '<table border=0 width="100%"><tr><td width="184" class="month"><NOBR>\n';
	ddata += nowtitleyyyymm;
	ddata += '</NOBR></td>\n';
	ddata += '<td width="87" ALIGN="right"><NOBR>';
	ddata += '<INPUT TYPE=button class="button" VALUE="<<" onClick="self.opener.func_ShowCalendar(self.opener.document.'+oj.form.name+'.'+oj.name+',\'' + arg1 + '\',-1)">\n';
	ddata += '<INPUT TYPE=button class="button" VALUE="o" onClick="self.opener.func_ShowCalendar(self.opener.document.'+oj.form.name+'.'+oj.name+',\'' + arg1 + '\',0)">\n';
	ddata += '<INPUT TYPE=button class="button" VALUE=">>" onClick="self.opener.func_ShowCalendar(self.opener.document.'+oj.form.name+'.'+oj.name+',\'' + arg1 + '\',1)">\n';
	ddata += '</NOBR></td></tr></table>\n';
	ddata += '</TH>\n';
	ddata += '</TR>\n';
	//-WEEK
	ddata += '<TR class="dayofweek">\n';
	for (i = 0 ; i < 7 ; i++) {
		ddata += '<TH width="35">\n';
		ddata += week[i];
		ddata += '</TH>\n';
	}
	ddata += '</TR>\n';
	//-DATE
	for (j = 0 ; j < 6 ; j++) {
	ddata += '<TR>\n';
		for( i2 = 0 ; i2 < 7 ; i2++) {
			nextday = startday.getTime() + (i2 * 1000 * 60 * 60 * 24);
			wrtday = new Date(nextday);
			wrtdate = wrtday.getDate();
			wrtmonth = wrtday.getMonth();
			wrtyear = wrtday.getYear();
			if (wrtyear < 1900) {
				wrtyear = 1900 + wrtyear;
			}
			wrtyyyymm = wrtyear * 100 + wrtmonth;
			wrtyyyymmdd = SDateFormat(arg1, wrtyear, (wrtmonth + 1), wrtdate);

			wrtdateA = '<A HREF="javascript:function v(){if ( self.opener.document.'+oj.form.name+'.'+oj.name+'.value != \''+wrtyyyymmdd+'\'){self.opener.updateFlag = true; self.opener.setUpdateFlag();}self.opener.document.'+oj.form.name+'.'+oj.name+'.value=(\''+wrtyyyymmdd+'\');self.close()};v()"';
			if (wrtyyyymm == nowyyyymm && wrtdate == absnow.getDate() && wrtmonth == absnow.getMonth() && wrtday.getYear() == absnow.getYear()) {
				wrtdateA += ' name="todayfocus">';
			} else {
				wrtdateA += '>';
			}
			wrtdateA += wrtdate;
			wrtdateA += '</A>\n';
			if (wrtyyyymm != nowyyyymm) {
				ddata += '<TD class="outday">\n';
				ddata += wrtdateA;
			} else if (wrtdate == absnow.getDate() && wrtmonth == absnow.getMonth() && wrtday.getYear() == absnow.getYear()) {
				ddata += '<TD class="today">\n';
				ddata += wrtdateA;
			} else {
				ddata += '<TD class="otherday">\n';
				ddata += wrtdateA;
			}
			 ddata += '</TD>\n';
		}
		ddata += '</TR>\n';
		startday = new Date(nextday);
		startday = startday.getTime() + (1000 * 60 * 60 * 24);
		startday = new Date(startday);
	}
	//Close Button for MAC
	if (Mac) {
		ddata += '<TR>\n';
		ddata += '<TD COLSPAN=7 ALIGN=center>\n';
		ddata += '<INPUT TYPE=button VALUE="CLOSE" onClick="self.close();return false">\n';
		ddata += '</TD>\n';
		ddata += '</TR>\n';
	}
	ddata += '</TABLE>\n';
	ddata += '</FORM>\n';
	ddata += '</BODY>\n';
	ddata += '</HTML>\n';
	calendarwin.document.write(ddata);
	calendarwin.document.close();
	calendarwin.focus();
	winflg = 1;
}

function SDateFormat(format, year, month, day) {
	var formstr;
	var ret;
	var i;
	var j;
	var c;
	var wks;
	var mnt;

	formstr = format.toUpperCase();
	ret = "";
//	mnt = new Array('Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec');
	mnt = new Array('1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月');
	i = 0;
	while (i < formstr.length) {
		c = formstr.charAt(i);
		j = 0;

		if (c == "Y") {
			wks = year + "";
			if (formstr.substring(i, i + 4) == "YYYY") {
				ret += wks;
				j = 4;
			} else if (formstr.substring(i, i + 2) == "YY") {
				ret += wks.substring(2, 4);
				j = 2;
			}
		} else if (c == "M") {
			wks = month + "";
			if (formstr.substring(i, i + 3) == "MMM") {
				ret += mnt[month - 1];
				j = 3;
			} else if (formstr.substring(i, i + 2) == "MM") {
				ret += "0".substring(0, 2 - wks.length) + wks;
				j = 2;
			} else if(formstr.substring(i, i + 1) == "M") {
				ret += wks;
				j = 1;
			}
		} else if (c == "D") {
			wks = day + "";
			if (formstr.substring(i, i + 2) == "DD") {
				ret += "0".substring(0, 2 - wks.length) + wks;
				j = 2;
			} else if(formstr.substring(i, i + 1) == "D") {
				ret += wks;
				j = 1;
			}
		}
		if (j != 0) {
			i += j;
		} else {
			ret += format.substring(i, i + 1);
			i++;
		}
	}
	return ret;
}

function SPerthDateString(format, datestr) {
	var formstr;
	var ret;
	var i;
	var j;
	var k;
	var c;
	var wks;
	var mnt;

	formstr = format.toUpperCase();
	ret = new Array('','','');
//	mnt = new Array('Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec');
	mnt = new Array('1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月');
	i = 0;
	while (i < formstr.length) {
		c = formstr.charAt(i);
		j = 0;

		if (c == "Y") {
			if (formstr.substring(i, i + 4) == "YYYY") {
				ret[0] = datestr.substring(i, i + 4);
				j = 4;
			} else if (formstr.substring(i, i + 2) == "YY") {
				ret[0] = "20" + datestr.substring(i, i + 2);
				j = 2;
			}
		} else if (c == "M") {
			if (formstr.substring(i, i + 3) == "MMM") {
				wks = datestr.substring(i, i + 3);
				k = 0;
				while (k < mnt.length) {
					if (mnt[k].toUpperCase() == wks.toUpperCase()) {
						ret[1] = "0".substring(0, 2 - ((k + 1) + "").length) + (k + 1);
						break;
					}
					k++;
				}
				j = 3;
			} else if (formstr.substring(i, i + 2) == "MM") {
				wks = datestr.substring(i, i + 2);
				if (isNaN(wks)) {
					wks = "0" + datestr.substring(i, i + 1);
				}
				ret[1] = wks;
				j = 2;
			} else if(formstr.substring(i, i + 1) == "M") {
				ret[1] = datestr.substring(i, i + 1);;
				j = 1;
			}
		} else if (c == "D") {
			if (formstr.substring(i, i + 2) == "DD") {
				wks = datestr.substring(i, i + 2);
				if (isNaN(wks)) {
					wks = "0" + datestr.substring(i, i + 1);
				}
				ret[2] = wks;
				j = 2;
			} else if(formstr.substring(i, i + 1) == "D") {
				ret[2] = datestr.substring(i, i + 1);
				j = 1;
			}
		}
		if (j != 0) {
			i += j;
		} else {
			i++;
		}
	}
	return ret;
}

/*
 * SubWindowOpen
 *  Syntax : GRS_mkSubWin(URL, winName, x, y, w, h)
 *  Exp : GRS_mkSubWin(winIndex, 'test.htm', 'win0', 100, 200, 150, 300)
 *
 */

var calendarwin;

function GRS_mkSubWin(URL, winName, x, y, w, h) {
    var para = ""
	+ " left=" + x
	+ ",screenX=" + x
	+ ",top=" + y
	+ ",screenY=" + y
	+ ",toolbar=" + 0
	+ ",location=" + 0
	+ ",directories=" + 0
	+ ",status=" + 0
	+ ",menubar=" + 0
	+ ",scrollbars=" + 0
	+ ",resizable=" + 0
	+ ",innerWidth=" + w
	+ ",innerHeight=" + h
	+ ",width=" + w
	+ ",height=" + h
	if (calendarwin == null) {
		//First Time
		calendarwin=window.open(URL, winName, para);
		calendarwin.focus();
	} else {
		if (calendarwin.closed) {
			calendarwin = window.open(URL, winName, para);
			calendarwin.focus();
		} else {
			calendarwin.focus();
		}
	}
}

//---WACsCustomize---

var win2;

// ------- yaogang change ---------
function isNumeric(element) {
	if ((!isNaN(element.value)) && !isSBCcase(element)) {
		return true;
	} else {
		element.focus();
		alert("请输入数值类型!");
		return false;
	}
}

//字符串首尾全角空格验证
function isSBCcase(element) {
	var s = element.value;

	//全角空格ASCII值为12288,半角空格ASCII值为32
	if (s.charAt(0).charCodeAt() == 12288 ||
		s.charAt(0).charCodeAt() == 32 ||
		s.charAt(s.length - 1).charCodeAt() == 12288 ||
		s.charAt(s.length - 1).charCodeAt() == 32){
		return true;
	}
	return false;
}

//检查数值类型按起止日期检索时,起始数值必须小于或等于终止数值


function checkNumericOrder(element_bgn,element_end) {
	if (isNumeric(element_bgn) && isNumeric(element_end)) {
		if (element_end.value < element_bgn.value &&
			element_bgn.value != "" && element_end.value != "" ) {
			alert("起始数值必须小于或等于终止数值,请重试!");
			return false;
		}
	}
}

function isInteger(element) {
	var s = element.value;
	for (var i = 0; i < s.length; i++) {
		var c = s.charAt(i);
		if (!((c >= "0") && (c <= "9"))) {
			element.focus();
			alert("请输入数字!");
			return false;
		}
	}
	return true;
}


function isFloat(elm) {
	if( window.event.keyCode != 13 ){
		return;
	}
	var mForm = document.MainForm;
	var num = parseFloat(mForm.elements[elm].value);
	if (!isNaN(num)) {
		return true;
	} else {
		return false;
	}
}

//去除逗号
function delComma(currencyDigits) {
	currencyDigits = currencyDigits.replace(/,/g, "");
	return currencyDigits;
}

//加逗号
function addComma(str) {
	//先去除逗号
	str = delComma(str);

	var length = str.length;
	var ret = "";
	var sign = "";  //符号
	var dec = "";   //小数部分

	var j = 0;

	if (length < 1 ) {
		return ret;
	}

	if (str.charAt(0) == "-") {
		sign = str.charAt(0);
		str = str.substring(1);
	}

	if (str.charAt(0) == "+") {
		str = str.substring(1);
	}


	if (str.indexOf(".") != -1) {
		dec = str.substring(str.indexOf("."));
		str = str.substring(0,str.indexOf("."));
	}

	for (var i=str.length - 1; i > -1; i--) {
		var letter = str.charAt(i);
		ret =  letter + ret ;
		j = j + 1;
		if (j % 3 == 0 && j != str.length) {
			ret = "," + ret;
		}
	}

	ret = sign + ret + dec;
	return ret;
}

//精度检查
function checkNumberLen(inString, maxIntLen, maxDecLen) {
	var rc = false;

	var intLen = 0;
	var decLen = 0;

	var wk = "" + parseFloat(inString);

	if (wk.indexOf(".") > -1) {
		intLen = wk.indexOf(".");
		decLen = wk.length - (wk.indexOf(".") + 1);
	} else {
		intLen = wk.length;
		decLen = 0;
	}
	if ((intLen <= maxIntLen) && (decLen <= maxDecLen)) {
		rc = true;
	}

	return rc;
}


function ScheckDigit(value) {
	for (var i = 0; i < value.length; i++) {
		var c = value.charAt(i);
		if (!((c >= "0") && (c <= "9"))) {
			return false;
		}
	}

	return true;
}

function checkDateFormat(element){
	if(isDate(element.value) == false){
		element.focus();
	}
	return true;

}

//检查按起止日期检索时,起始日期必须小于或等于终止日期
function checkDateOrder(element_bgn,element_end){
	var bgn = isDate(element_bgn.value);
	var end = isDate(element_end.value);
	if (!bgn) {
		element_bgn.focus();
		return false;
	}
	if (!end){
		element_end.focus();
		return false;
	}
	 if(bgn == "true" && end == "true"){
		//if (!checkDateEarlier(element_bgn.value,element_end.value)){
		alert("起始日期必须小于或等于终止日期,请重试!");
		return false;
	}
	//}
	return true;
}

function isDate(m_strDate){
	if(m_strDate==""){return true;}

	var Separator = "/";
	var m_arrDate = m_strDate.split(Separator);

	//判断是否为合法数字
	for (var i = 0; i < m_arrDate.length; i++) {
		var c = m_arrDate[i].charAt(i);
		for (var j = 0; j < m_arrDate[i].length; j++) {
			c = m_arrDate[i].charAt(j);
			//alert(c);

			if (!((c >= "0") && (c <= "9"))) {
				alert("日期只能输入数字并按照格式yyyy/mm/dd输入,请重试!");
				return false;
			}
		}

	}

	if(m_arrDate.length != 3){
		alert("请按照格式yyyy/mm/dd输入日期类型");
		return false;
	}

	var m_YEAR = m_arrDate[0];
	var m_MONTH = m_arrDate[1];
	var m_DAY = m_arrDate[2];

	if(m_YEAR.length != 4 || m_MONTH.length != 2 || m_DAY.length != 2){
		alert("请按照格式yyyy/mm/dd输入日期类型");
		return false;
	}

	m_strDate = m_YEAR + "/" + m_MONTH + "/" + m_DAY;
	var testDate=new Date(m_strDate);
	if(testDate.getMonth()+1==m_MONTH){
		return true;
	} else{
		alert("该日期不存在,请重试!");
		return false;
	}
}//end function

//弹出窗口居中
function moveCenter(mWin, mW, mH) {
    x = (screen.availWidth - mW) / 2;
    y = (screen.availHeight - mH) / 2;
    mWin.moveTo(x,y);
}

//四舍五入到指定小数位

function Round(a_Num , a_Bit){
      return( Math.round(a_Num * Math.pow (10 , a_Bit)) / Math.pow(10 , a_Bit));
}

//去空格



function trim(str) {
	return str.replace(/^\s+|\s+$/g, "")
}

// 弹出文件上传对话框
function showFiliale(Name,ValueDep,ValueSec,Classify){

	var temp = "selFiliale.do?action=Init&ctl="+ValueDep+"&ctl2="+Name+"&ctl3="+ValueSec+"&ctl4="+Classify;
	var popupwin = window.showModalDialog(temp,window,"dialogWidth:205px;dialogHeight:150px;resizable:no;status:no;help:no;center:yes;");
	if( popReturnName != "" || popReturnValue !="" || popReturnValue2 !=""){
		eval("document.all."+Name+".value='"+popReturnName+"';");
		eval("document.all."+ValueDep+".value='"+popReturnValue+"';");
		eval("document.all."+ValueSec+".value='"+popReturnValue2+"';");
		popReturnName = "";
		popReturnValue = "";
		popReturnValue2 = "";
		
		setUpdateFlag();
	}
}


//代理店选择弹出框，Name为需要显示的字段，Value为该字段对应的值
function showAgent(Name,Value,Disvision){
    var temp = "selAgent.do?action=entry&ctl="+Value+"&ctl1="+Name;
    if(Disvision!= undefined) {
	    temp = "selAgent.do?action=entry&ctl="+Value+"&ctl1="+Name+"&div="+Disvision;
    }
	
	var popupwin = window.showModalDialog(temp,window,"dialogWidth:515px;dialogHeight:385px;resizable:no;status:no;help:no;center:yes;");
	if( popReturnName != "" && popReturnValue !="" ){
		eval("document.all."+Name+".value='"+popReturnName+"';");
		eval("document.all."+Value+".value='"+popReturnValue+"';");
		popReturnName = "";
		popReturnValue = "";

		setUpdateFlag();
	}
}

//关闭主窗口时，需要同时关闭弹出框
function closewin(popWin){
	if(popWin){
		if (popWin.closed){

		}
		else{
			popWin.close();
		}
	}
}

//提交flag,防止二次提交
submitFlag = false;
//更新flag
updateFlag = false;

//设置更新flag
function setUpdateFlag() {
	updateFlag = true;
	top.updateFlag = true;
}

//提交处理
function submitForm(action) {
	if (submitFlag) { return false; }
	submitFlag = true;

	form_action = document.forms[0].action;
	if (form_action.indexOf('?') > 0) {
		form_action = form_action.substring(0, form_action.indexOf('?'));
	}
	document.forms[0].action = form_action + "?action=" + action;
	document.forms[0].submit();
	document.body.style.cursor="wait";
}


//离开页面时的处理.如页面已作过改动，则提示用户，按「确定」将放弃修改
function checkUpdate(flag) {
	var retValue = true;

	//程序进行中


	if (flag == '3') {
		return false;
	}

	if (flag) {
		retValue = confirm('数据已修改且未保存,是否放弃修改?');
	}
	return retValue;
}

//电话号码检查
function checkTEL(element)
{
 var i,j,strTemp;
 strTemp="0123456789-+()#* ";
 for (i=0;i< element.value.length;i++)
 {
  j=strTemp.indexOf(element.value.charAt(i));
  if (j==-1)
  {
   alert('输入的电话号码中含有非法字符');
   return false;
  }
 }
 return true;
}

/**
*校验字符串是否为日期型



*返回值：

*如果为空，定义校验通过，           返回true
*如果字串为日期型，校验通过，       返回true
*如果日期不合法，                   返回false    参考提示信息：输入域的时间不合法！（yyyy-MM-dd）

*/
function checkIsValidDate(str)
{
    //如果为空，则通过校验

    if(str == "") {
         return true;
    }

    var arrDate = str.split("/");
    if(parseInt(arrDate[0],10) < 100) {
         arrDate[0] = 2000 + parseInt(arrDate[0],10) + "";
    }

    var date =  new Date(arrDate[0],(parseInt(arrDate[1],10) -1)+"",arrDate[2]);
    // var date =  new Date(arrDate[0],arrDate[1],arrDate[2]);
     //alert(date+ '@'+date.getFullYear()+ 'Y' + date.getMonth() + 'M' + date.getDate() + '@@' + arrDate[0]+'Y'+arrDate[1]+'M'+arrDate[2]);
    if(date.getFullYear() == arrDate[0]
       && date.getMonth() == parseInt(arrDate[1],10) - 1
       && date.getDate() == arrDate[2]) {
         return true;
       } else {
      	 alert('日期格式错误！');
         return false;
       }

}
/**
*校验两个日期的先后
*返回值：
*如果其中有一个日期为空，校验通过,         返回true
*如果起始日期早于等于终止日期，校验通过，   返回true
*如果起始日期晚于终止日期，                返回false    参考提示信息： 起始日期不能晚于结束日期。
*/
function checkDateEarlier(strStart,strEnd)
{
    if(checkIsValidDate(strStart) == false || checkIsValidDate(strEnd) == false) {
        return false;
    }

    //如果有一个输入为空，则通过检验


    if (( strStart == "" ) || ( strEnd == "" )) {
        return true;
    }

    var arr1 = strStart.split("/");
    var arr2 = strEnd.split("/");
    var date1 = new Date(arr1[0],parseInt(arr1[1].replace(/^0/,""),10) - 1,arr1[2]);
    var date2 = new Date(arr2[0],parseInt(arr2[1].replace(/^0/,""),10) - 1,arr2[2]);

    if(arr1[1].length == 1)
        arr1[1] = "0" + arr1[1];
    if(arr1[2].length == 1)
        arr1[2] = "0" + arr1[2];
    if(arr2[1].length == 1)
        arr2[1] = "0" + arr2[1];
    if(arr2[2].length == 1)
        arr2[2]="0" + arr2[2];
    var d1 = arr1[0] + arr1[1] + arr1[2];
    var d2 = arr2[0] + arr2[1] + arr2[2];
    if(parseInt(d1,10) > parseInt(d2,10))
       return false;
    else
       return true;
}

//响应回车事件

function keyDown(element) {
	if(event.keyCode==13)
	{
	    element.click();
	}
}

function func_ShowCalendar2(oj, arg1, arg2) {
	oj.blur();
	if (!arguments[1]) {
		arg1 = "dd/mmm/yy";
	} else if (arg1 == 0) {
		arg1 = "dd/mmm/yy";
	}
	if (!arguments[2]) {
		arg2 = 0;
	}
	if (!Moz) {
		if (arguments[2] || arguments[2] == 0) {
			winflg = 0;
		}
	}
	if (arg2 == 0) {
		now = new Date();
	}
	nowdate = now.getDate();
	nowmonth = now.getMonth();
	nowyear = now.getYear();
	if (nowmonth == 11 && arg2 > 0) {		//Dec & arg1 > 0
		nowmonth = -1 + arg2;
		nowyear++;			//month arg1-1;
	} else if (nowmonth == 0 && arg2 < 0) {	//Jan & arg1 < 0
		nowmonth = 12 + arg2;
		nowyear--;			//month arg1+12;
	} else {
		nowmonth += arg2;		//Feb - Nov ; Month =+ arg1
	}
	if (nowyear < 1900) {
		nowyear = 1900 + nowyear;
	}
	now = new Date(nowyear, nowmonth, 1);
	nowyyyymm = nowyear * 100 + nowmonth;
	nowtitleyyyymm = SDateFormat("mmm/yyyy", nowyear, (nowmonth + 1), nowdate);
//	week = new Array('SUN','MON','TUE','WED','THU','FRI','SAT');
	week = new Array('日','一','二','三','四','五','六');
	if(winflg) {
		var w = 289;
		var h = 190;
		//Size
		if (Moz) {
			w+=15;
			h+=40;
		} else if (Win) {
			w+=0;
			h+=0;
		} else if (Mac) {
			w+=8;
			h+=22;
		} else if (X11) {
			w+=5;
			h+=46;
		}
		//var x = 100;
		//var y = 20;
		//if (document.all) {
		//	x = window.event.screenX - 140;
		//	y = window.event.screenY + 16;
		//} else if (document.layers || document.getElementById) {
		//	x += window.screenX;
		//	y += window.screenY;
		//}
		y = (top.screen.availHeight - h) / 2;
		x = (top.screen.Width - w) / 2;
		var xxx = "WIN_ID" + eval(parseInt((new Date()).getTime())) + eval(parseInt(Math.random() * 100000));
		GRS_mkSubWin('', xxx, x, y, w, h);
	}
	//Base Date
	fstday = now;
	startday = fstday - ( fstday.getDay() * 1000 * 60 * 60 * 24 ); // First Sunday
	startday = new Date(startday);
	// HTML
	ddata = '';
	ddata += '<HTML>\n';
	ddata += '<HEAD>';
	ddata += '<META HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=UTF-8">\n';
	ddata += '<TITLE>Calendar</TITLE>\n';
	ddata += '<link rel="stylesheet" href="css/calendar.css" type="text/css">';
	ddata += '</HEAD>\n';
	ddata += '<SCRIPT language="JavaScript">\n';
	ddata += '<!--\n';
	ddata += 'function toToday() {\n';
	ddata += '    try {\n';
	ddata += ' 		  if (document.all.todayfocus != undefined){   \n';
	ddata += '        	setTimeout("document.all.todayfocus.focus()", 50);\n';
	ddata += '        }\n';
	ddata += '    } catch (e) {\n';
	ddata += '    }\n';
	ddata += '}\n';
	ddata += '-->\n';
	ddata += '</SCRIPT>\n';
	ddata += '<BODY class="page" onLoad="toToday();">\n';
	ddata += '<FORM>\n';
	ddata += '<div id="layer" class="calendarlayer">\n';
	ddata += '<TABLE width="289" BORDER=0 cellpadding="2" cellspacing="2">\n';
	//-MONTH
	ddata += '<TR id="trmonth">\n';
	ddata += '<TH COLSPAN=7 class="header">\n';
	ddata += '<table border=0 width="100%"><tr><td width="184" class="month"><NOBR>\n';
	ddata += nowtitleyyyymm;
	ddata += '</NOBR></td>\n';
	ddata += '<td width="87" ALIGN="right"><NOBR>';
	ddata += '<INPUT TYPE=button class="button" VALUE="<<" onClick="self.opener.func_ShowCalendar(self.opener.document.'+oj.form.name+'.'+oj.name+',\'' + arg1 + '\',-1)">\n';
	ddata += '<INPUT TYPE=button class="button" VALUE="o" onClick="self.opener.func_ShowCalendar(self.opener.document.'+oj.form.name+'.'+oj.name+',\'' + arg1 + '\',0)">\n';
	ddata += '<INPUT TYPE=button class="button" VALUE=">>" onClick="self.opener.func_ShowCalendar(self.opener.document.'+oj.form.name+'.'+oj.name+',\'' + arg1 + '\',1)">\n';
	ddata += '</NOBR></td></tr></table>\n';
	ddata += '</TH>\n';
	ddata += '</TR>\n';
	//-WEEK
	ddata += '<TR class="dayofweek">\n';
	for (i = 0 ; i < 7 ; i++) {
		ddata += '<TH width="35">\n';
		ddata += week[i];
		ddata += '</TH>\n';
	}
	ddata += '</TR>\n';
	//-DATE
	for (j = 0 ; j < 6 ; j++) {
	ddata += '<TR>\n';
		for( i2 = 0 ; i2 < 7 ; i2++) {
			nextday = startday.getTime() + (i2 * 1000 * 60 * 60 * 24);
			wrtday = new Date(nextday);
			wrtdate = wrtday.getDate();
			wrtmonth = wrtday.getMonth();
			wrtyear = wrtday.getYear();
			if (wrtyear < 1900) {
				wrtyear = 1900 + wrtyear;
			}
			wrtyyyymm = wrtyear * 100 + wrtmonth;
			wrtyyyymmdd = SDateFormat(arg1, wrtyear, (wrtmonth + 1), wrtdate);

			wrtdateA = '<A HREF="javascript:function v(){if ( self.opener.document.'+oj.form.name+'.'+oj.name+'.value != \''+wrtyyyymmdd+'\'){}self.opener.document.'+oj.form.name+'.'+oj.name+'.value=(\''+wrtyyyymmdd+'\');self.close()};v()"';
			if (wrtyyyymm == nowyyyymm && wrtdate == absnow.getDate() && wrtmonth == absnow.getMonth() && wrtday.getYear() == absnow.getYear()) {
				wrtdateA += ' name="todayfocus">';
			} else {
				wrtdateA += '>';
			}
			wrtdateA += wrtdate;
			wrtdateA += '</A>\n';
			if (wrtyyyymm != nowyyyymm) {
				ddata += '<TD class="outday">\n';
				ddata += wrtdateA;
			} else if (wrtdate == absnow.getDate() && wrtmonth == absnow.getMonth() && wrtday.getYear() == absnow.getYear()) {
				ddata += '<TD class="today">\n';
				ddata += wrtdateA;
			} else {
				ddata += '<TD class="otherday">\n';
				ddata += wrtdateA;
			}
			 ddata += '</TD>\n';
		}
		ddata += '</TR>\n';
		startday = new Date(nextday);
		startday = startday.getTime() + (1000 * 60 * 60 * 24);
		startday = new Date(startday);
	}
	//Close Button for MAC
	if (Mac) {
		ddata += '<TR>\n';
		ddata += '<TD COLSPAN=7 ALIGN=center>\n';
		ddata += '<INPUT TYPE=button VALUE="CLOSE" onClick="self.close();return false">\n';
		ddata += '</TD>\n';
		ddata += '</TR>\n';
	}
	ddata += '</TABLE>\n';
	ddata += '</FORM>\n';
	ddata += '</BODY>\n';
	ddata += '</HTML>\n';
	calendarwin.document.write(ddata);
	calendarwin.document.close();
	calendarwin.focus();
	winflg = 1;
}
function func_ShowCalendar3(oj, arg1, arg2) {
	oj.blur();
	if (!arguments[1]) {
		arg1 = "dd/mmm/yy";
	} else if (arg1 == 0) {
		arg1 = "dd/mmm/yy";
	}
	if (!arguments[2]) {
		arg2 = 0;
	}
	if (!Moz) {
		if (arguments[2] || arguments[2] == 0) {
			winflg = 0;
		}
	}
	if (arg2 == 0) {
		now = new Date();
	}
	nowdate = now.getDate();
	nowmonth = now.getMonth();
	nowyear = now.getYear();
	if (nowmonth == 11 && arg2 > 0) {		//Dec & arg1 > 0
		nowmonth = -1 + arg2;
		nowyear++;			//month arg1-1;
	} else if (nowmonth == 0 && arg2 < 0) {	//Jan & arg1 < 0
		nowmonth = 12 + arg2;
		nowyear--;			//month arg1+12;
	} else {
		nowmonth += arg2;		//Feb - Nov ; Month =+ arg1
	}
	if (nowyear < 1900) {
		nowyear = 1900 + nowyear;
	}
	now = new Date(nowyear, nowmonth, 1);
	nowyyyymm = nowyear * 100 + nowmonth;
	nowtitleyyyymm = SDateFormat("mmm/yyyy", nowyear, (nowmonth + 1), nowdate);
//	week = new Array('SUN','MON','TUE','WED','THU','FRI','SAT');
	week = new Array('日','一','二','三','四','五','六');
	if(winflg) {
		var w = 289;
		var h = 190;
		//Size
		if (Moz) {
			w+=15;
			h+=40;
		} else if (Win) {
			w+=0;
			h+=0;
		} else if (Mac) {
			w+=8;
			h+=22;
		} else if (X11) {
			w+=5;
			h+=46;
		}
		//var x = 100;
		//var y = 20;
		//if (document.all) {
		//	x = window.event.screenX - 140;
		//	y = window.event.screenY + 16;
		//} else if (document.layers || document.getElementById) {
		//	x += window.screenX;
		//	y += window.screenY;
		//}
		y = (top.screen.availHeight - h) / 2;
		x = (top.screen.Width - w) / 2;
		var xxx = "WIN_ID" + eval(parseInt((new Date()).getTime())) + eval(parseInt(Math.random() * 100000));
		GRS_mkSubWin('', xxx, x, y, w, h);
	}
	//Base Date
	fstday = now;
	startday = fstday - ( fstday.getDay() * 1000 * 60 * 60 * 24 ); // First Sunday
	startday = new Date(startday);
	// HTML
	ddata = '';
	ddata += '<HTML>\n';
	ddata += '<HEAD>';
	ddata += '<META HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=UTF-8">\n';
	ddata += '<TITLE>Calendar</TITLE>\n';
	ddata += '<link rel="stylesheet" href="css/calendar.css" type="text/css">';
	ddata += '</HEAD>\n';
	ddata += '<SCRIPT language="JavaScript">\n';
	ddata += '<!--\n';
	ddata += 'function toToday() {\n';
	ddata += '    try {\n';
	ddata += ' 		  if (document.all.todayfocus != undefined){   \n';
	ddata += '        	setTimeout("document.all.todayfocus.focus()", 50);\n';
	ddata += '        }\n';
	ddata += '    } catch (e) {\n';
	ddata += '    }\n';
	ddata += '}\n';
	ddata += '-->\n';
	ddata += '</SCRIPT>\n';
	ddata += '<BODY class="page" onLoad="toToday();">\n';
	ddata += '<FORM>\n';
	ddata += '<div id="layer" class="calendarlayer">\n';
	ddata += '<TABLE width="289" BORDER=0 cellpadding="2" cellspacing="2">\n';
	//-MONTH
	ddata += '<TR id="trmonth">\n';
	ddata += '<TH COLSPAN=7 class="header">\n';
	ddata += '<table border=0 width="100%"><tr><td width="184" class="month"><NOBR>\n';
	ddata += nowtitleyyyymm;
	ddata += '</NOBR></td>\n';
	ddata += '<td width="87" ALIGN="right"><NOBR>';
	ddata += '<INPUT TYPE=button class="button" VALUE="<<" onClick="self.opener.func_ShowCalendar3(self.opener.document.getElementById(\'' + oj.id + '\'),\'' + arg1 + '\',-1)">\n';
	ddata += '<INPUT TYPE=button class="button" VALUE="o" onClick="self.opener.func_ShowCalendar3(self.opener.document.getElementById(\'' + oj.id + '\'),\'' + arg1 + '\',0)">\n';
	ddata += '<INPUT TYPE=button class="button" VALUE=">>" onClick="self.opener.func_ShowCalendar3(self.opener.document.getElementById(\'' + oj.id + '\'),\'' + arg1 + '\',1)">\n';
	ddata += '</NOBR></td></tr></table>\n';
	ddata += '</TH>\n';
	ddata += '</TR>\n';
	//-WEEK
	ddata += '<TR class="dayofweek">\n';
	for (i = 0 ; i < 7 ; i++) {
		ddata += '<TH width="35">\n';
		ddata += week[i];
		ddata += '</TH>\n';
	}
	ddata += '</TR>\n';
	//-DATE
	for (j = 0 ; j < 6 ; j++) {
	ddata += '<TR>\n';
		for( i2 = 0 ; i2 < 7 ; i2++) {
			nextday = startday.getTime() + (i2 * 1000 * 60 * 60 * 24);
			wrtday = new Date(nextday);
			wrtdate = wrtday.getDate();
			wrtmonth = wrtday.getMonth();
			wrtyear = wrtday.getYear();
			if (wrtyear < 1900) {
				wrtyear = 1900 + wrtyear;
			}
			wrtyyyymm = wrtyear * 100 + wrtmonth;
			wrtyyyymmdd = SDateFormat(arg1, wrtyear, (wrtmonth + 1), wrtdate);

			wrtdateA = '<A HREF="javascript:function v(){if ( self.opener.document.getElementById(\'' + oj.id + '\').value != \''+wrtyyyymmdd+'\'){self.opener.updateFlag = true; }self.opener.document.getElementById(\'' + oj.id + '\').value=(\''+wrtyyyymmdd+'\');self.close()};v()"';
			if (wrtyyyymm == nowyyyymm && wrtdate == absnow.getDate() && wrtmonth == absnow.getMonth() && wrtday.getYear() == absnow.getYear()) {
				wrtdateA += ' name="todayfocus">';
			} else {
				wrtdateA += '>';
			}
			wrtdateA += wrtdate;
			wrtdateA += '</A>\n';
			if (wrtyyyymm != nowyyyymm) {
				ddata += '<TD class="outday">\n';
				ddata += wrtdateA;
			} else if (wrtdate == absnow.getDate() && wrtmonth == absnow.getMonth() && wrtday.getYear() == absnow.getYear()) {
				ddata += '<TD class="today">\n';
				ddata += wrtdateA;
			} else {
				ddata += '<TD class="otherday">\n';
				ddata += wrtdateA;
			}
			 ddata += '</TD>\n';
		}
		ddata += '</TR>\n';
		startday = new Date(nextday);
		startday = startday.getTime() + (1000 * 60 * 60 * 24);
		startday = new Date(startday);
	}
	//Close Button for MAC
	if (Mac) {
		ddata += '<TR>\n';
		ddata += '<TD COLSPAN=7 ALIGN=center>\n';
		ddata += '<INPUT TYPE=button VALUE="CLOSE" onClick="self.close();return false">\n';
		ddata += '</TD>\n';
		ddata += '</TR>\n';
	}
	ddata += '</TABLE>\n';
	ddata += '</FORM>\n';
	ddata += '</BODY>\n';
	ddata += '</HTML>\n';
	calendarwin.document.write(ddata);
	calendarwin.document.close();
	calendarwin.focus();
	winflg = 1;
}


//得到文本的字节数
function strlength(str){
    var l=str.length;
    var n=l
    for (var i=0;i<l;i++)
    {
        if (str.charCodeAt(i)<0||str.charCodeAt(i)>255) n++
    }
    return n
}

//判断文本的字节数是否超出范围
function checkbyte(value,length){
    var l=strlength(value);
    if (l<=length) {
        return true;
    }
    else
    {
        alert('输入字数超出范围');
        return false;
    }
}


//窗口载入时调用，用于屏蔽一些鼠标和键盘操作

function init2(){
	//屏蔽鼠标右键
	document.body.attachEvent("oncontextmenu",function(){return false;});
	
	//window.status="ddd";
	//屏蔽特定键


	document.body.attachEvent("onkeydown",forbidKey);
}

//屏蔽特定键


function forbidKey() {
	  if ((window.event.altKey)&&
	      ((window.event.keyCode==37)||   //屏蔽 Alt+ 方向键 ←
	       (window.event.keyCode==39))){  //屏蔽 Alt+ 方向键 →
	     event.returnValue=false;
	     }
	  if (
	      (event.keyCode == 8 &&
			(event.srcElement.type != "text" &&
			event.srcElement.type != "textarea" &&
			event.srcElement.type != "password")) ||  //屏蔽退格删除键
	      (event.keyCode == 114)||               //屏蔽 F3
	      (event.keyCode == 116)||               //屏蔽 F5
	      (event.keyCode == 117)||               //屏蔽 F6
	      (event.keyCode == 122)||               //屏蔽 F11
	      (event.ctrlKey && event.keyCode==82)){ //Ctrl + R
	     event.keyCode=0;
	     event.returnValue=false;
	     }
	  if ((event.ctrlKey)&&(event.keyCode==78))   //屏蔽 Ctrl+n
	     event.returnValue=false;
	  if ((event.shiftKey)&&(event.keyCode==121)) //屏蔽 shift+F10
	     event.returnValue=false;
	  if (window.event.srcElement.tagName == "A" && window.event.shiftKey)
	      window.event.returnValue = false;  //屏蔽 shift 加鼠标左键新开一网页
	  //if ((window.event.altKey)&&(window.event.keyCode==115)){ //屏蔽Alt+F4
	  //    window.showModelessDialog("about:blank","","dialogWidth:1px;dialogheight:1px");
	  //    return false;}
}

window.attachEvent("onload", init2);


//当操作花费时间较长时，弹出提示信息，并禁止页面操作

function beginRun() {
	if (updateFlag != '3') {

		//var IfrRef2 = document.createElement("<iframe scrolling='no' frameborder='0'"+
        //                              "style='position:absolute; top:0px;"+
        //                              "left:0px;width:365px;height:60px;display:;margin-top:0px'></iframe>");
        //IfrRef2.id = "DivShim";
		//IfrRef2.src = "jsp/common/progressBar.jsp";
		//IfrRef2.runtimeStyle.position="absolute";

		//document.body.insertAdjacentElement("afterbegin",IfrRef2);
		//IfrRef2.style.pixelTop = (document.body.offsetHeight - 120) / 2 + document.body.scrollTop;
		//IfrRef2.style.pixelLeft = (document.body.offsetWidth - 360) / 2 + document.body.scrollLeft;
		var myWindow = window.showModelessDialog("jsp/common/progressBar.jsp",window,"dialogHeight:40px; dialogWidth: 370px; center: yes; help: no; resizable: no; status: no;scroll:no;");

		//设置程序进行中flag
		updateFlag = '3';
	}
}

function showloading()
{
	if (document.sbar.width>356)
	{
		document.sbar.width=1;
	}
	else
	{
		document.sbar.width += 2;
	}
}

//buttondisabled
function init3(){
  for (x=0; x < document.all.length; x++){
   	if(document.all[x].type == "button"){
      document.all[x].attachEvent("onclick", changeStyle);
      if (document.all[x].disabled) {
		document.all[x].className='btndisable2';
	  }
    }
  }
}

function changeStyle(){
  for (x=0; x < document.all.length; x++){
    if(document.all[x].type == "button"){
	  if (document.all[x].disabled) {
		document.all[x].className='btndisable2';
	  }
    }
  }
}

function showNextPageNS2(action,form,actionNm,targetNm)
{
	if(action =="previousPage"){
		eval(form + ".currentPage.value = parseInt(" + form + ".currentPage.value) -1;");

	}
	if(action =="nextPage"){
		eval(form + ".currentPage.value = parseInt(" + form + ".currentPage.value) + 1;");

	}

	var temp = "";

//	alert(form + ".action= \"" + actionNm + temp +"\";");
	eval(form + ".action= \"" + actionNm + temp +"\";");

//	alert(form + ".target= \"" + targetNm +"\";");
	eval(form + ".target= \"" + targetNm +"\";");

	eval(form + ".submit();");

}

function checkNumber(element){

	if (isInteger(element)) {
		setUpdateFlag();
		return true;
	} else {
		return false;
	}
}

function resetFields(fieldList) {
	var arr = fieldList.split(",");
	for (var i = 0; i < arr.length; i++) {
	    eval("document.forms[0]." + arr[i] + ".value = '';");
	}
}

//判断是否为正确的月份
function isMonth(m_strDate){
	if(m_strDate==""){return true;}

	var Separator = "/";
	var m_arrDate = m_strDate.split(Separator);

	//判断是否为合法数字

	for (var i = 0; i < m_arrDate.length; i++) {
		var c = m_arrDate[i].charAt(i);
		for (var j = 0; j < m_arrDate[i].length; j++) {
			c = m_arrDate[i].charAt(j);
			//alert(c);

			if (!((c >= "0") && (c <= "9"))) {
				alert("日期只能输入数字并按照格式yyyy/mm输入,请重试!");
				return false;
			}
		}

	}

	if(m_arrDate.length != 2){
		alert("请按照格式yyyy/mm输入日期类型");
		return false;
	}

	var m_YEAR = m_arrDate[0];
	var m_MONTH = m_arrDate[1];
//	var m_DAY = m_arrDate[2];

	if(m_YEAR.length != 4 || m_MONTH.length != 2){
		alert("请按照格式yyyy/mm输入日期类型");
		return false;
	}

	m_strDate = m_YEAR + "/" + m_MONTH + "/" +"01";
	var testDate=new Date(m_strDate);
	if(testDate.getMonth()+1==m_MONTH){
		return true;
	} else{
		alert("该日期不存在,请重试!");
		return false;
	}
}//end function

function checkSingleQuotes(input) {
	if(input == ''){return true;}

	var count = 0;

	for (i = 0; i < input.length; i++) {
		if ("'" == input.charAt(i)) {
			count++;
		}
	}
	
	if (count > 0) {
		alert('输入字符非法，不能输入单引号！');
		return false;
	} else {
		return true;
	}
	
}

function fDisplayDiv(src){
	var tr = document.getElementById("div_"+src);
	var image = document.getElementById("img_"+src);

	if (tr.style.display == 'none'){
		tr.style.display = '';
		image.src = "images/minus.gif";	
	}else{
		tr.style.display = 'none';
		image.src = "images/plus.gif";
	}
}

function setDisabledByName(propertyName){
    var tags = document.getElementsByName(propertyName);
    if(tags && tags.length > 1){
        for(var i=0 ; i < tags.length; i++){
            privateDisabled(tags.item(i));
        }
    }else{
        var t = tags.item(0);
        privateDisabled(t);
    }
}

function privateDisabled(property){
    var type = property.type;
    if(type == 'text'|| type == 'textarea' || type == 'password'){
        property.style.backgroundColor  = "#C6C3C6";
    }
    property.disabled = true;

    if(property.type == 'radio' || property.type == 'select-one' || property.type=='checkbox'){
        if(property.checked == false){
            return;
        }
        var copyHidden = document.createElement("input");
        copyHidden.type = "hidden";
        copyHidden.name = property.name;
        copyHidden.value = property.value;
        copyHidden.id = "FRAMEWORKCREATEHIDDEN" + property.name;

        var _f = document.getElementsByTagName("FORM");
        if(_f && _f.item(0)){
            var oldHidden = document.getElementById("FRAMEWORKCREATEHIDDEN" + property.name);
            if(oldHidden != null && oldHidden != 'undefined'){
                _f.item(0).removeChild(oldHidden);
            }
            _f.item(0).appendChild(copyHidden);

        }
    }
}

function tce_CheckSingleQuotes(input) {
	if(input == ''){return true;}

	var count = 0;

	for (i = 0; i < input.length; i++) {
		if ("'" == input.charAt(i)) {
			count++;
		}
	}
	
	if (count > 0) {
		return false;
	} else {
		return true;
	}
	
}

//检查日期大小
function tce_CheckDateEarlier(strStart,strEnd)
{

    var arr1 = strStart.split("/");
    var arr2 = strEnd.split("/");
    var date1 = new Date(arr1[0],parseInt(arr1[1].replace(/^0/,""),10) - 1,arr1[2]);
    var date2 = new Date(arr2[0],parseInt(arr2[1].replace(/^0/,""),10) - 1,arr2[2]);

    if(arr1[1].length == 1)
        arr1[1] = "0" + arr1[1];
    if(arr1[2].length == 1)
        arr1[2] = "0" + arr1[2];
    if(arr2[1].length == 1)
        arr2[1] = "0" + arr2[1];
    if(arr2[2].length == 1)
        arr2[2]="0" + arr2[2];
    var d1 = arr1[0] + arr1[1] + arr1[2];
    var d2 = arr2[0] + arr2[1] + arr2[2];
    if(parseInt(d1,10) > parseInt(d2,10))
       return false;
    else
       return true;
}

function tce_CheckIsValidDate(str)
{
    //如果为空，则通过校验

    if(str == "") {
         return true;
    }

    var arrDate = str.split("/");
    if(parseInt(arrDate[0],10) < 100) {
         arrDate[0] = 2000 + parseInt(arrDate[0],10) + "";
    }

    var date =  new Date(arrDate[0],(parseInt(arrDate[1],10) -1)+"",arrDate[2]);
    // var date =  new Date(arrDate[0],arrDate[1],arrDate[2]);
     //alert(date+ '@'+date.getFullYear()+ 'Y' + date.getMonth() + 'M' + date.getDate() + '@@' + arrDate[0]+'Y'+arrDate[1]+'M'+arrDate[2]);
    if(date.getFullYear() == arrDate[0]
       && date.getMonth() == parseInt(arrDate[1],10) - 1
       && date.getDate() == arrDate[2]) {
         return true;
       } else {
         return false;
       }

}

//检查日期格式:yyyy/mm/dd
function tce_IsDate(m_strDate){
	if(m_strDate==""){return true;}

	var Separator = "/";
	var m_arrDate = m_strDate.split(Separator);

	//判断是否为合法数字

	for (var i = 0; i < m_arrDate.length; i++) {
		var c = m_arrDate[i].charAt(i);
		for (var j = 0; j < m_arrDate[i].length; j++) {
			c = m_arrDate[i].charAt(j);
			//alert(c);

			if (!((c >= "0") && (c <= "9"))) {
				return false;
			}
		}

	}

	if(m_arrDate.length != 3){
		return false;
	}

	var m_YEAR = m_arrDate[0];
	var m_MONTH = m_arrDate[1];
	var m_DAY = m_arrDate[2];

	if(m_YEAR.length != 4 || m_MONTH.length != 2 || m_DAY.length != 2){
		return false;
	}

	m_strDate = m_YEAR + "/" + m_MONTH + "/" + m_DAY;
	var testDate=new Date(m_strDate);
	if(testDate.getMonth()+1==m_MONTH){
		return true;
	} else{
		return false;
	}
}//end function


function func_ShowCalendar4(oj, arg1, arg2) { // modified by wzm
	oj.blur();
	if (!arguments[1]) {
		arg1 = "dd/mmm/yy";
	} else if (arg1 == 0) {
		arg1 = "dd/mmm/yy";
	}
	if (!arguments[2]) {
		arg2 = 0;
	}
	if (!Moz) {
		if (arguments[2] || arguments[2] == 0) {
			winflg = 0;
		}
	}
	if (arg2 == 0) {
		now = new Date();
	}
	nowdate = now.getDate();
	nowmonth = now.getMonth();
	nowyear = now.getYear();
	if (nowmonth == 11 && arg2 > 0) {		//Dec & arg1 > 0
		nowmonth = -1 + arg2;
		nowyear++;			//month arg1-1;
	} else if (nowmonth == 0 && arg2 < 0) {	//Jan & arg1 < 0
		nowmonth = 12 + arg2;
		nowyear--;			//month arg1+12;
	} else {
		nowmonth += arg2;		//Feb - Nov ; Month =+ arg1
	}
	if (nowyear < 1900) {
		nowyear = 1900 + nowyear;
	}
	now = new Date(nowyear, nowmonth, 1);
	nowyyyymm = nowyear * 100 + nowmonth;
	nowtitleyyyymm = SDateFormat("mmm/yyyy", nowyear, (nowmonth + 1), nowdate);
//	week = new Array('SUN','MON','TUE','WED','THU','FRI','SAT');
	week = new Array('日','一','二','三','四','五','六');
	if(winflg) {
		var w = 289;
		var h = 190;
		//Size
		if (Moz) {
			w+=15;
			h+=40;
		} else if (Win) {
			w+=0;
			h+=0;
		} else if (Mac) {
			w+=8;
			h+=22;
		} else if (X11) {
			w+=5;
			h+=46;
		}
		//var x = 100;
		//var y = 20;
		//if (document.all) {
		//	x = window.event.screenX - 140;
		//	y = window.event.screenY + 16;
		//} else if (document.layers || document.getElementById) {
		//	x += window.screenX;
		//	y += window.screenY;
		//}
		y = (top.screen.availHeight - h) / 2;
		x = (top.screen.Width - w) / 2;
		var xxx = "WIN_ID" + eval(parseInt((new Date()).getTime())) + eval(parseInt(Math.random() * 100000));
		GRS_mkSubWin('', xxx, x, y, w, h);
	}
	//Base Date
	fstday = now;
	startday = fstday - ( fstday.getDay() * 1000 * 60 * 60 * 24 ); // First Sunday
	startday = new Date(startday);
	// HTML
	ddata = '';
	ddata += '<HTML>\n';
	ddata += '<HEAD>';
	ddata += '<META HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=UTF-8">\n';
	ddata += '<TITLE>Calendar</TITLE>\n';
	ddata += '<link rel="stylesheet" href="css/calendar.css" type="text/css">';
	ddata += '</HEAD>\n';
	ddata += '<SCRIPT language="JavaScript">\n';
	ddata += '<!--\n';
	ddata += 'function toToday() {\n';
	ddata += '    try {\n';
	ddata += ' 		  if (document.all.todayfocus != undefined){   \n';
	ddata += '        	setTimeout("document.all.todayfocus.focus()", 50);\n';
	ddata += '        }\n';
	ddata += '    } catch (e) {\n';
	ddata += '    }\n';
	ddata += '}\n';
	ddata += '-->\n';
	ddata += '</SCRIPT>\n';
	//add by liyg 添加禁止某些键

	ddata += '<script language="JavaScript" SRC="js/common/common.js"></script>';
	ddata += '<BODY class="page" onLoad="toToday();">\n';
	ddata += '<FORM>\n';
	ddata += '<div id="layer" class="calendarlayer">\n';
	ddata += '<TABLE width="289" BORDER=0 cellpadding="2" cellspacing="2">\n';
	//-MONTH
	ddata += '<TR id="trmonth">\n';
	ddata += '<TH COLSPAN=7 class="header">\n';
	ddata += '<table border=0 width="100%"><tr><td width="184" class="month"><NOBR>\n';
	ddata += nowtitleyyyymm;
	ddata += '</NOBR></td>\n';
	ddata += '<td width="87" ALIGN="right"><NOBR>';
	ddata += '<INPUT TYPE=button class="button" VALUE="<<" onClick="self.opener.func_ShowCalendar4(self.opener.document.'+oj.form.name+'.elements[\''+oj.name+'\'],\'' + arg1 + '\',-1)">\n';
	ddata += '<INPUT TYPE=button class="button" VALUE="o" onClick="self.opener.func_ShowCalendar4(self.opener.document.'+oj.form.name+'.elements[\''+oj.name+'\'],\'' + arg1 + '\',0)">\n';
	ddata += '<INPUT TYPE=button class="button" VALUE=">>" onClick="self.opener.func_ShowCalendar4(self.opener.document.'+oj.form.name+'.elements[\''+oj.name+'\'],\'' + arg1 + '\',1)">\n';
	ddata += '</NOBR></td></tr></table>\n';
	ddata += '</TH>\n';
	ddata += '</TR>\n';
	//-WEEK
	ddata += '<TR class="dayofweek">\n';
	for (i = 0 ; i < 7 ; i++) {
		ddata += '<TH width="35">\n';
		ddata += week[i];
		ddata += '</TH>\n';
	}
	ddata += '</TR>\n';
	//-DATE
	for (j = 0 ; j < 6 ; j++) {
	ddata += '<TR>\n';
		for( i2 = 0 ; i2 < 7 ; i2++) {
			nextday = startday.getTime() + (i2 * 1000 * 60 * 60 * 24);
			wrtday = new Date(nextday);
			wrtdate = wrtday.getDate();
			wrtmonth = wrtday.getMonth();
			wrtyear = wrtday.getYear();
			if (wrtyear < 1900) {
				wrtyear = 1900 + wrtyear;
			}
			wrtyyyymm = wrtyear * 100 + wrtmonth;
			wrtyyyymmdd = SDateFormat(arg1, wrtyear, (wrtmonth + 1), wrtdate);

			wrtdateA = '<A HREF="javascript:function v(){if ( self.opener.document.'+oj.form.name+'.elements[\''+oj.name+'\'].value != \''+wrtyyyymmdd+'\'){self.opener.updateFlag = true; self.opener.setUpdateFlag();}self.opener.document.'+oj.form.name+'.elements[\''+oj.name+'\'].value=(\''+wrtyyyymmdd+'\');self.close()};v()"';
			if (wrtyyyymm == nowyyyymm && wrtdate == absnow.getDate() && wrtmonth == absnow.getMonth() && wrtday.getYear() == absnow.getYear()) {
				wrtdateA += ' name="todayfocus">';
			} else {
				wrtdateA += '>';
			}
			wrtdateA += wrtdate;
			wrtdateA += '</A>\n';
			if (wrtyyyymm != nowyyyymm) {
				ddata += '<TD class="outday">\n';
				ddata += wrtdateA;
			} else if (wrtdate == absnow.getDate() && wrtmonth == absnow.getMonth() && wrtday.getYear() == absnow.getYear()) {
				ddata += '<TD class="today">\n';
				ddata += wrtdateA;
			} else {
				ddata += '<TD class="otherday">\n';
				ddata += wrtdateA;
			}
			 ddata += '</TD>\n';
		}
		ddata += '</TR>\n';
		startday = new Date(nextday);
		startday = startday.getTime() + (1000 * 60 * 60 * 24);
		startday = new Date(startday);
	}
	//Close Button for MAC
	if (Mac) {
		ddata += '<TR>\n';
		ddata += '<TD COLSPAN=7 ALIGN=center>\n';
		ddata += '<INPUT TYPE=button VALUE="CLOSE" onClick="self.close();return false">\n';
		ddata += '</TD>\n';
		ddata += '</TR>\n';
	}
	ddata += '</TABLE>\n';
	ddata += '</FORM>\n';
	ddata += '</BODY>\n';
	ddata += '</HTML>\n';
	calendarwin.document.write(ddata);
	calendarwin.document.close();
	calendarwin.focus();
	winflg = 1;
}
//金额格式化

function formatCurrency(element) {
	//值为空则时

	if (trim(element.value) == "") {
		element.value = trim(element.value);
		return true;
	}

	//去除逗号
	var str = delComma(trim(element.value));

	//判断是否是数字

	if (isNaN(str)) {
		alert("请输入金额类型!");
		return false;
	}

	//去除首位的零
	str = "" + parseFloat(str);

	//精度检查

	if (!checkNumberLen(str, 10, 2)) {
		alert("输入金额精度错误!");
		return false;
	}

	//四舍五入
	str = "" + Round(parseFloat(str), 2);

	//如果没有小数加 ".00"
	if (str.indexOf(".") == -1) {
		str = str + ".00";
	} else {
		//如果小数有一位加"0"
		temp = str.substring(str.indexOf(".") + 1);
		if (temp.length == 1) {
			str = str + "0";
		}
	}

	element.value =  addComma(str);
	return true;
}

//精度检查

function checkNumberLen(inString, maxIntLen, maxDecLen) {
	var rc = false;

	var intLen = 0;
	var decLen = 0;

	var wk = "" + parseFloat(inString);

	if (wk.indexOf(".") > -1) {
		intLen = wk.indexOf(".");
		decLen = wk.length - (wk.indexOf(".") + 1);
	} else {
		intLen = wk.length;
		decLen = 0;
	}
	if ((intLen <= maxIntLen) && (decLen <= maxDecLen)) {
		rc = true;
	}

	return rc;
}

// 佣金比例format
function formatRate(element) {

  // 值为空则时

  if (trim(element.value) == "") {
    return true;
  }

  var str = trim(element.value);

  // 判断是否是数字

  if (isNaN(str)) {
    alert("请输入数字类型!");
    return false;
  }

  // 去除首位的零
  str = "" + parseFloat(str);

  // 精度检查

	if (!checkNumberLen(str, 2, 1)) {
    alert("输入比例精度错误!");
    return false;
	}

  // 如果没有小数加".0"
  if (str.indexOf(".") == -1) {
    str = str + ".0";
  }

  element.value = str;

  return true;
}
function formatCurrencyValue(amount) {
	//值为空则时

	if (trim(amount) == "") {
		return trim(amount);
	}

	//去除逗号
	var str = delComma(trim(amount));

	//判断是否是数字

	if (isNaN(str)) {
		alert("请输入金额类型!");
		return false;
	}

	//去除首位的零
	str = "" + parseFloat(str);

	//四舍五入
	str = "" + Round(parseFloat(str), 2);
	
	//精度检查
	if (!checkNumberLen(str, 10, 2)) {
		alert("输入金额精度错误!");
		return false;
	}



	//如果没有小数加 ".00"
	if (str.indexOf(".") == -1) {
		str = str + ".00";
	} else {
		//如果小数有一位加"0"
		temp = str.substring(str.indexOf(".") + 1);
		if (temp.length == 1) {
			str = str + "0";
		}
	}

	return  addComma(str);
}
//add by zyw 090812
function changeMouseStatus()
{
	document.body.style.cursor="wait";
	document.all.hrefId1.style.cursor="wait";
	document.all.hrefId2.style.cursor="wait";
	if(document.all.showSalePriv.value == "true"){
		document.all.hrefId3.style.cursor="wait";
	}
	
	document.all.hrefId4.style.cursor="wait";
}
//add by chenhui 2012-10-25---start
function checkPwd(me,strLen){
	if(me.value.length<strLen){
		return false;
	}
	var _match = me.value.match(/[A-Z]/g);
	if(_match==null){
		return false;
	}
	var _match2 = me.value.match(/[a-z]/g);
	if(_match2==null){
		return false;
	}
	var _match3 = me.value.match(/[0-9]/g);
	if(_match3==null){
		return false;
	}
	return true; 
}
function checkInputPwd(me,pwdLen){
    var strLen = pwdLen.value;
	if(!checkPwd(me,strLen)){
		alert("密码必须包含大小写字母和数字且长度不小于"+strLen+"位！");
		me.focus();
		return false;
	}
	return true;
}
//add by chenhui 2012-10-25---end