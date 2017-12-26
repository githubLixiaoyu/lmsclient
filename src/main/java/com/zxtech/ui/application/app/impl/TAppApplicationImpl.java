package com.zxtech.ui.application.app.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.zxtech.support.util.QRCodeUtil;
import com.zxtech.support.util.Util;
import com.zxtech.ui.application.app.TAppApplication;
import com.zxtech.ui.dao.app.TAppMapper;

@Named
public class TAppApplicationImpl implements TAppApplication{
	@Inject
	private TAppMapper tAppMapper;
	
	@Override
	public Map<String, Object> getQrcode(HttpServletRequest request) throws Exception{
		String qrcodePath = "http://" + request.getServerName();
		if (!"80".equals(request.getServerPort())) {
			qrcodePath += ":" + request.getServerPort();
		}
		qrcodePath += "/lmsFiles/app/";

		String QrcodeImagesPath = Util.readPropertiesValue("QrcodeImagesPath");
		
		String platform = "android";
		Map<String, Object> androidMap = tAppMapper.getAndroidApk(platform);
		String androidFileName = String.valueOf(androidMap.get("filename"));
		String androidImageName = androidFileName.substring(0, androidFileName.lastIndexOf(".")) + ".png";
		String androidPath = qrcodePath + "android_index.html";
		QRCodeUtil.encode(androidPath, "", new File(QrcodeImagesPath + "/" + androidImageName), false);
		
		platform = "ios";
		Map<String, Object> iosMap = tAppMapper.getIosIpa(platform);
		String iosFileName = String.valueOf(iosMap.get("filename"));
		String iosImageName = iosFileName.substring(0, iosFileName.lastIndexOf(".")) + ".png";
		String iosPath = qrcodePath + "index.html";
		QRCodeUtil.encode(iosPath, "", new File(QrcodeImagesPath + "/" + iosImageName), false);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("android", androidImageName);
		map.put("ios", iosImageName);
		map.put("androidPath", androidPath);
		map.put("iosPath", iosPath);
		
		return map;
	}
}
