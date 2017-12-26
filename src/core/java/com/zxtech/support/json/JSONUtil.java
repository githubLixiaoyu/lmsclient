package com.zxtech.support.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
	private static ObjectMapper mapper;
	
	/**
	 * 获取ObjectMapper实例 ObjectMapper本身为线程安全
	 * @param createNew 方式：true，新实例；false,存在的mapper实例
	 * @return
	 */
	public static synchronized ObjectMapper getMapperInstance(boolean createNew) {   
        if (createNew) {   
            return new ObjectMapper();   
        } else if (mapper == null) {   
            mapper = new ObjectMapper();   
        }   
        return mapper;   
    } 
	
	public static String writeObject(Object data) throws IOException{
		ObjectMapper objectMapper = getMapperInstance(false);
		return objectMapper.writeValueAsString(data);
	}
	
	public static<T> T readObject(String str,Class<T> cls) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = getMapperInstance(false);
		return objectMapper.readValue(str, cls);
	}
	
	public static<T> T readObject(String str,TypeReference<T> cls) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = getMapperInstance(false);
		return objectMapper.readValue(str, cls);
	}
	
}
