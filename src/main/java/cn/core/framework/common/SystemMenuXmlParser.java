package cn.core.framework.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.core.framework.model.Menu;

/**
 * 后台系统菜单XML文件解析器
 * @author JC
 * @date 2015-8-1
 */
public class SystemMenuXmlParser {
	
	protected static Log log = LogFactory.getLog(SystemMenuXmlParser.class);
	
	//菜单列表
	public static ArrayList<Menu> menus = new ArrayList<Menu>();
	
	/**
	 * 加载菜单资源
	 */
	public static void loadMenuConfig(){
		parseDocument("/system_menu_config.xml");
	}
	
	/**
	 * 解析xml文件
	 * @param filePath 文件路径
	 */
	@SuppressWarnings("unchecked")
	private static void parseDocument(String filePath) {
		log.debug("parse system menu xml file:"+filePath);
		SAXReader reader = new SAXReader(); 
		InputStream is = SystemMenuXmlParser.class.getResourceAsStream(filePath);
		try {
			Document document = reader.read(is);
			Element root = document.getRootElement(); 
			Iterator<Element> it = (Iterator<Element>)root.elementIterator();
			while (it.hasNext()) {
				Element e = (Element) it.next(); 
				parseElement(e);
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

	@SuppressWarnings("unchecked")
	private static void parseElement(Element element) {
		//一级菜单：<menu></menu>
		Menu menu = new Menu();
		menu.setName(element.attributeValue("name"));
		//一级菜单下的子菜单列表（二级菜单）
		ArrayList<Menu> list = new ArrayList<Menu>();
		Iterator<Element> item = (Iterator<Element>)element.elementIterator();
		while (item.hasNext()) {
			Element e = (Element)item.next(); 
			if ("item".equals(e.getName())) {
				//二级菜单：<item></item>
				Menu subMenu = new Menu();
				subMenu.setName(e.attributeValue("name"));
				subMenu.setAction(e.attributeValue("action"));
				subMenu.setDefaultMethod(e.attributeValue("default-method"));
				Iterator<Element> property = (Iterator<Element>)e.elementIterator();
				Map<String,String> props = new HashMap<String,String>();
				while (property.hasNext()){
					Element e1 = (Element)property.next(); 
					if("property".equals(e1.getName())){
						//二级菜单的属性值
						props.put(e1.attributeValue("name"), e1.attributeValue("method"));
						subMenu.setProperty(props);
					}
				}
				list.add(subMenu);
			}
		}
		menu.setSubMenus(list);
		menus.add(menu);
	}
}
