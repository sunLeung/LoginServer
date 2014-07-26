package common.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import common.config.Config;
import common.db.C3P0Utils;

/**
 * 
 * @Description 启服关服控制器
 * @author liangyx
 * @date 2014-6-26
 * @version V1.0
 */
public class InitListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//清理c3p0
		C3P0Utils.destroy();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//初始化配置
		initConfig(arg0);
	}
	
	private void initConfig(ServletContextEvent sce){
		Config.CONFIG_DIR=sce.getServletContext().getRealPath("")+File.separator+"WEB-INF"+File.separator+File.separator+Config.CONFIG_DIR;
	}

}
