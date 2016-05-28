package cn.core.framework.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.core.framework.common.Configuration;
import cn.core.framework.common.SystemMenuXmlParser;

/**
 * 监听器：应用启动时监听器
 * @author JC
 * @date 2015-8-1
 */
public class StartUpListenner implements ServletContextListener{

	protected static Log log = LogFactory.getLog(StartUpListenner.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//加载管理系统导航菜单
		log.info("=================== loaded system menu resource =======================");
		SystemMenuXmlParser.loadMenuConfig();

		//加载全局配置文件
		log.info("=================== loaded global configruation =======================");
		Configuration.loadConfig();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}

}
