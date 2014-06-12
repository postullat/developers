package com.epam.lab.developers.setting;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.lab.developers.servlet.Login;

public class PropertyUtil {

	private static final String DEVELOPERS_PROPERTIES = "developers.properties";
    private static Map<String, String> props = new HashMap<String, String>();
    private static InputStream stream;
    static final Logger logger = Logger.getLogger(PropertyUtil.class);

	static {
		 PropertyConfigurator.configure(new PropertyUtil().getClass().getResource(
			    "/log4j.properties"));
	}
	
    public static void initialize() {
        try {
            ClassLoader loader = PropertyUtil.class.getClassLoader();
            InputStream stream = loader.getResourceAsStream(DEVELOPERS_PROPERTIES);
            Properties fileProp = new Properties();
            fileProp.load(stream);
            
            for(Entry<Object, Object> prop : fileProp.entrySet()) {
            	props.put((String)prop.getKey(), (String)prop.getValue());
            
            }
        } catch(Exception e) {
            e.printStackTrace();
            logger.error("Can't load file "+DEVELOPERS_PROPERTIES);
        } finally{
        	IOUtils.closeQuietly(stream);;
        }

    }
    
    public static String getProperty(String key){
    	return props.get(key);
    }
}
