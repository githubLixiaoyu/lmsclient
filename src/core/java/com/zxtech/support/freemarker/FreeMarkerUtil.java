package com.zxtech.support.freemarker;


import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtil {

	public static void analysisTemplate(String templateName,Object root,Writer out) {
		try {
			Configuration config = new Configuration();
			URL url = Thread.currentThread().getContextClassLoader().getResource("templates");
			File file = new File(url.getFile());
			//设置要解析的模板所在的目录，并加载模板文件
			config.setDirectoryForTemplateLoading(file);
			//设置包装器，并将对象包装为数据模型
			config.setObjectWrapper(new DefaultObjectWrapper());
			
			config.setDefaultEncoding("UTF-8");
//			config.setDefaultEncoding("GBK");
			//获取模板
			Template template = config.getTemplate(templateName);
			//模板渲染
			template.process(root, out);
			
			out.flush();
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}

