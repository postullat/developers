package com.epam.lab.developers.setting;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;

public class PropertyUtil {

    private static Map<String, String> props = new HashMap<String, String>();
    private static InputStream stream;
    
    public static void initialize() {
        try {
            ClassLoader loader = PropertyUtil.class.getClassLoader();
            InputStream stream = loader.getResourceAsStream("developers.properties");
            Properties fileProp = new Properties();
            fileProp.load(stream);
            
            for(Entry<Object, Object> prop : fileProp.entrySet()) {
            	String key = (String)prop.getKey();
            	String value = (String)prop.getValue();
            	props.put(key, value);
            
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally{
        	IOUtils.closeQuietly(stream);;
        }

    }
    
    public static String getProperty(String key){
    	return props.get(key);
    }
}
