package com.zxtech.support.interceptor;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.inject.Named;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxtech.auth.util.AuthDetailUtil;

/**
 * 参数公共部分处理
 * 
 * @author duomi 最终更新者 LAST_UPDATE_BY 最终更新日期 LAST_UPDATE_DATE 作成者 CREATED_BY
 *         作成日期 CREATION_DATE
 */
@Named
@Aspect
public class CommonParamInterceptor {
	private Logger logger = LoggerFactory.getLogger(CommonParamInterceptor.class);

	@Pointcut("execution(* com.zxtech.application..*.*insert*(..))")
	private void insertMethod() {
	}

	@Pointcut("execution(* com.zxtech.application..*.*update*(..))")
	private void updateMethod() {
	}

	@Pointcut("execution(* com.zxtech.application..*.*delete*(..))")
	private void deleteMethod() {
	}

	@Pointcut("execution(* com.zxtech.application..*.*save*(..))")
	private void saveMethod() {
	}

	@Before("insertMethod() || saveMethod()")
	public void doInsertBefore(JoinPoint jp) {
		logger.debug("insert公共参数处理start....");
		Object[] args = jp.getArgs();

		processParam(args, 1);
		logger.debug("insert公共参数处理end....");
	}

	@Before("updateMethod() || deleteMethod()")
	public void doModifyBefore(JoinPoint jp) {
		logger.debug("modify公共参数处理start....");
		Object[] args = jp.getArgs();

		processParam(args, 0);

		logger.debug("公共参数处理end....");
	}

	/**
	 * 
	 * @param args
	 * @param flag
	 *            0:modify,1:insert
	 */
	private void processParam(Object[] args, int flag) {
		String userId = AuthDetailUtil.getLoginUserId();
		// 遍历参数对象
		for (Object info : args) {

			if (info instanceof Map) {
				// 最终更新者 LAST_UPDATE_BY
				((Map) info).put("setLastUpdateBy", userId);
				// 最终更新日期 LAST_UPDATE_DATE
				((Map) info).put("setLastUpdateDate", new Date());
				if (flag != 0) {
					// 作成者 CREATED_BY
					((Map) info).put("setCreatedBy", userId);
					// 作成日期 CREATION_DATE
					((Map) info).put("setCreationDate", new Date());
				}

			} else {
				// 获取对象的所有方法
				Method[] methods = info.getClass().getDeclaredMethods();

				// 遍历方法，判断get方法
				for (Method method : methods) {

					String methodName = method.getName();
					// 判断是不是get方法
					if (methodName.indexOf("set") == -1) {// 不是get方法
						continue;// 不处理
					}

					try {
						// 最终更新者 LAST_UPDATE_BY
						if (methodName.equals("setLastUpdateBy")) {
							logger.debug("setLastUpdateBy==" + userId);
							// 调用set方法，设置参数值
							method.invoke(info, userId);
						}
						// 最终更新日期 LAST_UPDATE_DATE
						if (methodName.equals("setLastUpdateDate")) {
							logger.debug("setLastUpdateDate==" + DateFormatUtils.format(new Date(),
									DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()));
							// 调用set方法，设置参数值
							method.invoke(info, new Date());
						}
						if (flag != 0) {
							// 作成者 CREATED_BY
							if (methodName.equals("setCreatedBy")) {
								logger.debug("setCreatedBy==" + userId);
								// 调用set方法，设置参数值
								method.invoke(info, userId);
							}
							// 作成日期 CREATION_DATE
							if (methodName.equals("setCreationDate")) {
								logger.debug("setCreationDate==" + DateFormatUtils.format(new Date(),
										DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()));
								// 调用set方法，设置参数值
								method.invoke(info, new Date());
							}
						}

					} catch (Exception e) {
						continue;
					}
				}
			}
		}
	}
}
