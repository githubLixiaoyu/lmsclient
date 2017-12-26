package com.zxtech.support.jaxb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;


public class JaxbUtil {
	/** 
     * JavaBean转换成xml 
     * 默认编码UTF-8 
     * @param obj 
     * @param writer 
     * @return  
     */  
    public static String convertToXml(Object obj) {  
        return convertToXml(obj, "UTF-8");  
    }  
  
    /** 
     * JavaBean转换成xml 
     * @param obj 
     * @param encoding  
     * @return  
     */  
    public static String convertToXml(Object obj, String encoding) {  
        String result = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
            Marshaller marshaller = context.createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);  

			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NamespacePrefixMapper() {
				@Override
				public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
					if (namespaceUri.equals("http://kone.com/XI/GiantKONE")) return "ns0";
					return suggestion;
				}
			});
  
            StringWriter writer = new StringWriter();  
            marshaller.marshal(obj, writer);  
            result = writer.toString();  
            result = result.replace(" standalone=\"yes\"", "");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return result;  
    }  
    
    /** 
     * JavaBean保存成xml
     * @param obj 
     * @param encoding  
     * @return  
     */  
    public static void writeToXml(Object obj, String fileName) {  
        try {  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
            Marshaller marshaller = context.createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  
  
            FileOutputStream outputStream = new FileOutputStream(fileName);
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
            marshaller.marshal(obj, streamWriter);  
        
        } catch (Exception e) {  
            e.printStackTrace();  
        }  

    }
    
    /** 
     * JavaBean保存成xml
     * @param obj 
     * @param encoding  
     * @return  
     */  
    public static void writeToXml(Object obj, String path,String fileName) {  
        try {  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
            Marshaller marshaller = context.createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  
  
            FileOutputStream outputStream = new FileOutputStream(path + "\\" + fileName);
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
            marshaller.marshal(obj, streamWriter);  
        
        } catch (Exception e) {  
            e.printStackTrace();  
        }  

    }
  
    /** 
     * xml转换成JavaBean 
     * @param xml 
     * @param c 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public static <T> T convertToJavaBean(String xml, Class<T> c) {  
        T t = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(c);  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            t = (T) unmarshaller.unmarshal(new StringReader(xml));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return t;  
    }  
    
    /** 
     * xml转换成JavaBean 
     * @param xml 
     * @param c 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public static <T> T readFromXml(String fileName, Class<T> c) {  
        T t = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(c);  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            
            FileInputStream inputStream = new FileInputStream(fileName);
//            InputStreamReader streamReader = new InputStreamReader(inputStream);
            t = (T) unmarshaller.unmarshal(inputStream);  
        } catch (FileNotFoundException e) {  
            System.out.println("数据源文件不存在"); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return t;  
    }
    
    /** 
     * xml转换成JavaBean 
     * @param xml 
     * @param c 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public static <T> T readFromXml(String path,String fileName, Class<T> c) {  
        T t = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(c);  
            Unmarshaller unmarshaller = context.createUnmarshaller();
            
            FileInputStream inputStream = new FileInputStream(path + "\\" + fileName);
//            InputStreamReader streamReader = new InputStreamReader(inputStream);
            t = (T) unmarshaller.unmarshal(inputStream);  
        } catch (FileNotFoundException e) {  
            System.out.println("数据源文件不存在"); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return t;  
    }
    
}
