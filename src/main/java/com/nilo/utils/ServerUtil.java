package com.nilo.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class ServerUtil {
	private static Properties props;
	static {
		Resource resource = new ClassPathResource("/resources/global.properties");
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 获取propeties 内容
	 * @param key
	 * @return
     */
	public static String get(String key){
		return props.getProperty(key, "");
	}
}
