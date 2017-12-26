package com.zxtech.support.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.zxtech.support.exception.util.WebErrUtils;
import com.zxtech.support.http.HttpUtil;

public class ExceptionHandler extends SimpleMappingExceptionResolver {

	private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		String exceptionMsg = null;
		

		exceptionMsg = WebErrUtils.formatException(ex);

		Throwable ourCause = ex;
        while ((ourCause = ex.getCause()) != null) {
        	ex = (Exception) ourCause;
        }
        
        if(!(ex instanceof BaseException)){
        	//将原始异常信息记录在日志中和控制台上
        	logger.error(exceptionMsg);
        	logger.error(ExceptionUtils.getStackTrace(ex));
        } else {
        	//将原始异常信息记录在日志中和控制台上
        	logger.error(exceptionMsg);
        	logger.error(ExceptionUtils.getStackTrace(ex));
        }
        
		String viewName = determineViewName(ex, request);
		if (viewName != null) {
			
			if (!HttpUtil.isAsynRequest(request)) {
				Integer statusCode = determineStatusCode(request, viewName);
				if (statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode);
				}
				Map<String, String> result = new HashMap<String, String>();
				result.put("msg", exceptionMsg);
				return new ModelAndView(viewName, result);
			} else {
				Map<String, String> data = new HashMap<String, String>();
				data.put("flag", HttpUtil.FAIL_FLG);
				data.put("msg", exceptionMsg);
				
				HttpUtil.outJson(response, data);
				return null;
			}
		}
		return null;
	}

	/**
	 * 向页面输出JSON数据
	 * @param response	response对象
	 * @param key		JSON中的键
	 * @param value		JSON中的值
	 */
//	private void writeJSON(HttpServletResponse response, String key, String value) {
//		Writer writer = null;
//		try {
//			response.setContentType("text/x-json;charset=UTF-8");
//			writer = response.getWriter();
//			writer.write(String.format("{\"%s\":\"%s\"}", key , value));
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				writer.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	/**
	 * 是否是异步请求
	 * 
	 * @param request
	 * @return
	 */
//	private boolean isAsynRequest(HttpServletRequest request) {
//		return (request.getHeader("accept").indexOf("application/json") != -1 || (request
//				.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With")
//				.indexOf("XMLHttpRequest") != -1));
//	}

}
