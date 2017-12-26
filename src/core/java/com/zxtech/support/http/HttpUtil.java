package com.zxtech.support.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxtech.support.json.JSONUtil;

public class HttpUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	public final static String FAIL_FLG = "0";
	public final static String SUCCESS_FLG = "1";
	/**
	 * 是否是异步请求
	 * 
	 * @param request
	 * @return 是返回true
	 */
	public static boolean isAsynRequest(HttpServletRequest request) {
		return (request.getHeader("accept").indexOf("application/json") != -1 || (request
				.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With")
				.indexOf("XMLHttpRequest") != -1));
	}
	
	public static<T> void outJson(HttpServletResponse response,T data){
		try{
			String result = JSONUtil.writeObject(data);
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		} catch(IOException ex){
			logger.debug(ex.getMessage());
		}
	}
	
	/**  
     * 设置下载文件中文件的名称  
     *     
     * @param filename  
     * @param request  
     * @return  
     */    
    public static String encodeFilename(String filename, HttpServletRequest request) {    
      /**  
       * 获取客户端浏览器和操作系统信息  
       * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)  
       * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6  
       */    
      String agent = request.getHeader("USER-AGENT");    
      try {    
        if ((agent != null) && ((-1 != agent.indexOf("MSIE") || (-1 != agent.indexOf("Trident"))))) {    
          String newFileName = URLEncoder.encode(filename, "UTF-8");    
          newFileName = StringUtils.replace(newFileName, "+", "%20");    
          if (newFileName.length() > 150) {    
            newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");    
            newFileName = StringUtils.replace(newFileName, " ", "%20");    
          }    
          return newFileName;    
        }    
        if ((agent != null) && (-1 != agent.indexOf("Mozilla")))    
//          return MimeUtility.encodeText(filename, "UTF-8", "B"); 
        	return new String(filename.getBytes("UTF-8"), "iso-8859-1");
      
        return filename;    
      } catch (Exception ex) {    
        return filename;    
      }    
    }  
}
