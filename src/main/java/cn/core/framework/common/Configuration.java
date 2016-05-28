package cn.core.framework.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 全局配置文件解析
 * @author JC
 * @date 2015-8-19
 */
public class Configuration {
	
	protected static Log log = LogFactory.getLog(Configuration.class);
	
	private static Map<String, Object> conf = new HashMap<String, Object>();
	
	
	public static void loadConfig(){
		parseDocument("/configuration.xml");
	}

	@SuppressWarnings("unchecked")
	private static void parseDocument(String path) {
		log.debug("parse configuration xml file:"+path);
		SAXReader reader = new SAXReader(); 
		InputStream is = Configuration.class.getResourceAsStream(path);
		try {
			Document document = reader.read(is);
			Element root = document.getRootElement(); 
			Iterator<Element> it = (Iterator<Element>)root.elementIterator();
			while (it.hasNext()) {
				Element e = (Element) it.next(); 
				parseConfiguration(e);
			}
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private static void parseConfiguration(Element element) {
		String eName = element.getName();
		if ("property".equals(eName)) {
			String name = element.attributeValue("name");
			conf.put(name, element.getTextTrim());
		}
	}

	public static String getProperty(String name){
		return (String)conf.get(name);
	}
}
