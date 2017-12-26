package com.zxtech.support.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	private static Logger logger = LoggerFactory.getLogger(LogUtil.class);
	
	public static void info(Logger logger, String psMsg){
		logger.info(psMsg);
	}
	
	public static void debug(Logger logger, String psMsg){
		logger.debug(psMsg);
	}
	
	public static void error(Logger logger, String psMsg){
		logger.error(psMsg);
	}
	
	public static void error(Logger logger, String psMsg, Exception e){
		logger.error(psMsg, e);
	}
	
	public static void info(String psMsg){
		logger.info(psMsg);
	}
	
	public static void debug(String psMsg){
		logger.debug(psMsg);
	}
	
	public static void error(String psMsg){
		logger.error(psMsg);
	}
	
	public static void error(String psMsg, Exception e){
		logger.error(psMsg, e);
	}
}
