package utils;

import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 * @Description JSON工具类
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class JsonUtils {
	private static final Log log = LogFactory.getLog(JsonUtils.class);
	
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final byte[] EMPTY = new byte[0];
	
	public static String jsonFromObject(Object object) {
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, object);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {  
            log.error("Unable to serialize to json: " + object, e);
            return null;
        }
        return writer.toString();  
    }

	public static Object objectFromJson(String json, Class clazz) {  
        Object object;  
        try {  
            object = mapper.readValue(json, clazz);  
        } catch (RuntimeException e) {  
            log.error("Runtime exception during deserializing "  
                    + clazz.getSimpleName() + " from "  
                    + StringUtils.abbreviate(json, 80));  
            throw e;  
        } catch (Exception e) {  
            log.error("Exception during deserializing " + clazz.getSimpleName()  
                    + " from " + StringUtils.abbreviate(json, 80));  
            return null;  
        }  
        return object;  
    }
	
	public static byte[] encode(Object obj) {
		try {
			return mapper.writeValueAsBytes(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return EMPTY;
		}
	}
	
	public static String encode2Str(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static JsonNode decode(String jsonStr) {
		try {
			return mapper.readTree(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public static ObjectMapper getMapper() {
		return mapper;
	}
	
	public static int getInt(String key,JsonNode json){
		try {
			return json.get(key).asInt();
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
	}
	
	public static long getLong(String key,JsonNode json){
		try {
			return json.get(key).asLong();
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
	}
	
	public static String getString(String key,JsonNode json){
		try {
			return json.get(key).asText();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
