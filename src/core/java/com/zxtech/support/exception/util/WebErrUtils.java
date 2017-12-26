/*
 * Copyright (c) OpenKoala 2011 All Rights Reserved
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.zxtech.support.exception.util;



import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.zxtech.support.exception.BaseException;
import com.zxtech.support.i18n.I18NManager;


/**
 * 类    名：org.openkoala.exception.base.utils.WebErrUtils<br />
 *   
 * 功能描述：web错误工具<br />
 *  
 * 创建日期：2013-1-21  <br />   
 * 
 * 版本信息：v 1.0<br />
 * 
 * 版权信息：Copyright (c) 2011 openkoala All Rights Reserved<br />
 * 
 * 作    者：<a href="mailto:jiangwei@openkoala.com">vakin jiang</a><br />
 * 
 * 修改记录： <br />
 * 修 改 者    修改日期     文件版本   修改说明
 */
public class WebErrUtils {
	
	public static final String DEFAULT_ERROR_MSG = "程序出错,请联系管理员";

    public static final String ERROR_KEY = "errorMsg";
    /**
     * 
     */
    public static final String RESULT_BUSS_EXCEPTION = "buss_exception";

	/**
	 * 格式化异常信息，用于友好响应用户
	 * @param e
	 * @return
	 */
    public static String formatException(Exception e){
		 String message = null;
		 //现在BaseException如果指定的了error，则错误码无效，想错误码生效删除下面while
//        Throwable ourCause = e;
//        while ((ourCause = e.getCause()) != null) {
//            e = (Exception) ourCause;
//        }
        String eClassName = e.getClass().getName();
        //一些常见异常提示
        if("java.lang.NumberFormatException".equals(eClassName)){
            message = WebErrUtils.getI18nMessage("NumberFormatException", "数字格式有问题，请联系统管理员");
        } else if(e instanceof SQLException){
            message = WebErrUtils.getI18nMessage("SQLException", "数据库出错,请联系管理员");
        } else if (e instanceof BaseException) {
            message = e.getMessage();
            if(StringUtils.isBlank(message))message = e.toString();
        }
        
        //获取默认异常提示
        if (StringUtils.isBlank(message)){
        	message = WebErrUtils.getDefaultMessage();
        	message = message+",错误码["+e.getClass().getSimpleName() + ":" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") +"]";
        }
        //替换特殊字符
        message = message.replaceAll("\"", "'");
        return message;
	}
    
    /**
     * 获取国际化异常信息
     * @param errorCode 错误码 对于i18n配置
     * @param defaultMsg  默认错误
     * @return
     */
	public static String getI18nMessage(String errorCode,String defaultMsg){
		String message = null;
		if(StringUtils.isNotBlank(errorCode)){
			message = I18NManager.getMessage(errorCode);
//			if(errorCode.equals(message))message = null;
		}
		if(StringUtils.isBlank(message)){
			message = StringUtils.isBlank(defaultMsg) ? getDefaultMessage() : defaultMsg;
		}
		
		return message+",错误码["+errorCode + ":" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") +"]";
//		return message;
	}
	
	 /* 获取国际化异常信息
     * @param errorCode 错误码 对于i18n配置
     * @param defaultMsg  默认错误
     * @return
     */
	public static String getI18nSuccessMessage(String errorCode,String defaultMsg){
		String message = null;
		if(StringUtils.isNotBlank(errorCode)){
			message = I18NManager.getMessage(errorCode);
			if(errorCode.equals(message))message = null;
		}
		if(StringUtils.isBlank(message)){
			message = StringUtils.isBlank(defaultMsg) ? getDefaultMessage() : defaultMsg;
		}
		return message;
//		return message;
	}
	
	/* 获取国际化异常信息
     * @param errorCode 错误码 对于i18n配置
     * @param defaultMsg  默认错误
     * @return
     */
	public static String getI18nSuccessMessage(String errorCode,Object[] args,String defaultMsg){
		String message = null;
		if(StringUtils.isNotBlank(errorCode)){
			message = I18NManager.getMessage(errorCode,args);
			if(errorCode.equals(message))message = null;
		}
		if(StringUtils.isBlank(message)){
			message = StringUtils.isBlank(defaultMsg) ? getDefaultMessage() : defaultMsg;
		}
		return message;
//		return message;
	}
	
	/**
	 * 获取默认异常
	 * @return
	 */
	public static String getDefaultMessage(){
		String defaultMessage = I18NManager.getMessage("default.exception.message");
		if (StringUtils.isBlank(defaultMessage) || defaultMessage.equals("default.exception.message")){
			defaultMessage = WebErrUtils.DEFAULT_ERROR_MSG;
        }
		return defaultMessage;
	}
	
}
