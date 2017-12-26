package com.zxtech.support.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class Util {
	static String optingKey[] = {"","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	/**
	 * 导入excel时判断是否为空串 如果是返回null
	 */
	public static String isNull(String p) {
		if (p.endsWith("")) {
			return null;
		} else {
			return p;
		}

	}
	
	/**
	 * 获取显示的optingKey
	 * @param index
	 * @return
	 */
	public static String getOptingKey(int index) {
		if (optingKey[index] != null) {
			return optingKey[index];
		} else {
			return "";
		}

	}

	/**
	 * 实现string类型的时间加一天
	 * 
	 * @param date
	 * @return
	 */
	public static String StringTypeDateAddOneDay(String s) {

		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			/*** 加一天 */
			Date dd;
			dd = df.parse(s);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dd);
			calendar.add(Calendar.DAY_OF_MONTH, 1);// 加一天
			s = df.format(calendar.getTime());
			return s;
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return s;

	}

	/**
	 * 实现string类型的时间减一天
	 * 
	 * @param date
	 * @return
	 */
	public static String StringTypeDateMinusOneDay(String s) {

		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			long dif;
			dif = df.parse(s).getTime() - 86400 * 1000;
			Date date = new Date();
			date.setTime(dif);

			s = df.format(date);
			return s;
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return s;

	}

	/*
	 * @param date
	 * 
	 * @return 日期转字符串
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (null == date) {
			return "";
		}
		return sdf.format(date);
	}

	/**
	 * 获取当前时间 timestamp类型
	 * 
	 * @param date
	 * @return
	 */
	public static Timestamp getCurrentTimestamp() {

		Date date = new Date();// 获取当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);// 时间存储为字符串
		Timestamp ts = Timestamp.valueOf(str);// 转换时间字符串为Timestamp
		return ts;
	}

	/**
	 * 时间戳转到string yyyy-MM-dd HH:mm:ss
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static String timeStapToString(Timestamp timeStamp) {
		String tsStr = "";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (timeStamp != null) {
				tsStr = sdf.format(timeStamp);
				return tsStr;
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tsStr;
	}

	/**
	 * 时间戳转到string yyyy-MM-dd
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static String timeStapToStringYYYY_MM_DD(Timestamp timeStamp) {
		String tsStr = "";
		DateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd ");
		try {
			if (timeStamp != null) {
				tsStr = sdf.format(timeStamp);
				return tsStr;
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tsStr;
	}

	/**
	 * 日期转字符串 带时分秒
	 */
	public static String dateToStringByHMS(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null == date) {
			return "";
		}
		return sdf.format(date);
	}

	/**
	 * 
	 * @param date
	 * @return 字符串转日期
	 */
	public static Date stringToDate(String date) {
		try {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			return sim.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * // 获取当前时间 年-月-日
	 * 
	 * @return
	 */

	public static String refFormatNowDate() {
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		return retStrFormatNowDate;
	}

	/**
	 * // 获取当前时间 年-月-日 时：分：秒
	 * 
	 * @return
	 */

	public static String refFormatNowDateHhMmSs() {
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		return retStrFormatNowDate;
	}

	/**
	 * 转换到utf-8字符编码转换
	 * 
	 * @param s
	 * @return
	 */

	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 字符编码转换
	 * 
	 * @param empName
	 * @return
	 */
	public static String charEncording(String empName) {

		try {
			if (empName
					.equals(new String(empName.getBytes("GB2312"), "GB2312"))) {
				empName = new String(empName.getBytes("GB2312"), "utf-8");
			} else if (empName.equals(new String(
					empName.getBytes("iso-8859-1"), "iso-8859-1"))) {

				empName = new String(empName.getBytes("iso-8859-1"), "utf-8");

			} else if (empName.equals(new String(empName.getBytes("UTF-8"),
					"UTF-8"))) {
				empName = new String(empName.getBytes("UTF-8"), "utf-8");
			} else if (empName
					.equals(new String(empName.getBytes("GBK"), "GBK"))) {
				empName = new String(empName.getBytes("GBK"), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return empName;
	}

	/**
	 * 获取随即UUID
	 * 
	 * @return
	 */

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();

	}

	/**
	 * MD5加密
	 * 
	 * @param plainText
	 *            要加密的字符信息
	 * @return 已经通过JDBC加密后32位加密字符串
	 */
	public String MD5Purity(String plainText) {

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(plainText.getBytes());

			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");

			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			plainText = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return plainText.toUpperCase();
	}

	/**
	 * 获取客户端Ip
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 判断字符是否为整数或小数
	 * 
	 * @param str
	 * @return
	 */
	public boolean IsNumOrDecimal(String str) {
		// 声明charSequence
		CharSequence charSequence = ".";
		int count = 1;
		for (int i = str.length(); i > 0; i--) {
			// 只有一个点时
			if (i == 1 && String.valueOf(str.charAt(i - 1)).equals(".")) {
				return false;
			}
			// 最后一位为点时
			if (str.substring(str.length() - 1).equals(".")) {
				return false;
			}
			if (!Character.isDigit(str.charAt(i - 1))
					&& !String.valueOf(str.charAt(i - 1)).equals(".")) {
				return false;
			}
			if (String.valueOf(str.charAt(i - 1)).contains(charSequence)) {
				if (count > 1) {
					return false;
				} else {
					count++;
				}
			}
		}
		return true;
	}

	/**
	 * 获得格式化后的当前系统时间
	 * 
	 * @param type
	 *            = 1：yyyy-MM-dd hh:mm:ss
	 * @param type
	 *            = 2：yyyy-MM-dd
	 * @param type
	 *            = 3：hh:mm:ss
	 * @param type
	 *            = 4：yyyyMMddhhmmss
	 * @return 返回格式化后时间
	 */
	public static String getNowDate(int type) {

		String dateType = "";

		if (type == 1) {
			dateType = "yyyy-MM-dd HH:mm:ss";
		} else if (type == 2) {
			dateType = "yyyy-MM-dd";
		} else if (type == 3) {
			dateType = "HH:mm:ss";
		} else {
			dateType = "yyyyMMddHHmmss";
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateType);

		return simpleDateFormat.format(new Date());
	}

	/**
	 * 把字符串类型转换为Date类型
	 * 
	 * @param date
	 *            待转换的字符串
	 * @param type
	 *            = 1：yyyy-MM-dd hh:mm:ss
	 * @param type
	 *            = 2：yyyy-MM-dd
	 * @return Date
	 */
	public Date getDateByStr(String dateStr, int type) {

		Date date = null;

		String dateType = "";

		if (type == 1) {
			dateType = "yyyy-MM-dd HH:mm:ss";
		}
		if (type == 2) {
			dateType = "yyyy-MM-dd";
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateType);

		try {
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
		}

		return date;
	}

	/**
	 * 获得请求用户ip信息
	 * 
	 * @param request
	 * @return
	 */
	public String getIp(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");

		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");

		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();

		}

		return ip;
	}

	/**
	 * 判断指定字符串是否在某个字符串数组中
	 * 
	 * @param str
	 *            指定字符串
	 * @param strs
	 *            字符串数组
	 * @return
	 */
	public boolean strIsInStrs(String str, String[] strs) {

		boolean bResult = false;

		for (String temp : strs) {

			if (temp.equals(str)) {

				bResult = true;

				break;
			}
		}

		return bResult;

	}
	
	public static String readPropertiesValue(String key) {
		Properties props = new Properties();
		InputStream in = null;
		try {
			String filePath = Util.class.getClassLoader().getResource("")
					.getPath()
					+ "META-INF/props/config.properties";
			in = new BufferedInputStream(new FileInputStream(filePath));
			props.load(in);
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (props != null) {
				props.clear();
			}
			props = null;
			in = null;
		}
	}
	
	
	   public static boolean isEmpty(String str) {
	        return str == null || str.trim().length() == 0;
	    }
	   
	   
	   public static Timestamp getNowTimestamp() {
			return new Timestamp(new Date().getTime());
		}

	
	/*public static void main(String[] args) {
		System.out.println(readPropertiesValue("ScormImages"));
	}*/
	   
	   /**
	    * 日期转换 7天后原日期返回，7天前，超过小时按小时，以此类推
	    * @param time
	    * @return
	    */
	   public static String[] convertReplyDate(String time) {
		   String[] result = new String[]{"",""};
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					long m =   new Date().getTime()-sdf.parse(time).getTime();
					long ss = (m) / (1000); 
					int mm = (int) ss / 60;
					int hh = (int) ss / 3600; 
					int dd = (int) hh / 24; 
					if (dd >=7) {
						result[0] = time;
						result[1] = time;
					}else{
						if (dd >0) {
							result[0] =dd+"天前回复";
							result[1] = "Reply "+dd+" days ago";
						}else if (hh >0) {
							result[0] =hh+"小时前回复";
							result[1] = "Reply "+hh+" hours ago";
						}else if (mm > 0) {
							result[0] =mm+"小时前回复";
							result[1] = "Reply "+hh+" minutes  ago";
						}else{
							result[0] ="1分钟前回复";
							result[1] = "Reply 1 minutes  ago";
						}
					}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		   return result;
		}
		
	/**
	 * 按时间快速排序（倒序）
	 * @param list
	 * @param index
	 * @param start
	 * @param end
	 * @throws Exception
	 */
	public static void quickSortByDate(List<Map<String, Object>> list, String dateKey, int start, int end) throws Exception {
	    if (start < end) {
	    	Map<String, Object> map = list.get(start);
	    	Map<String, Object> mapTemp;
	        int i = start, j = end;
	        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        do {
	            while ((sdf.parse(String.valueOf(list.get(i).get(dateKey))).getTime() > sdf.parse(String.valueOf(map.get(dateKey))).getTime()) && (i < end))
	                i++;
	            while ((sdf.parse(String.valueOf(list.get(j).get(dateKey))).getTime() < sdf.parse(String.valueOf(map.get(dateKey))).getTime()) && (j > start))
	                j--;
	            if (i <= j) {
	            	mapTemp = list.get(i);
	            	list.set(i, list.get(j));
	            	list.set(j, mapTemp);
	                i++;
	                j--;
	            }
	        } while (i <= j);
	        if (start < j)
	        	quickSortByDate(list, dateKey, start, j);
	        if (end > i)
	        	quickSortByDate(list, dateKey, i, end);
	    }
	}

}