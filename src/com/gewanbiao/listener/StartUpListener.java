package com.gewanbiao.listener;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.ws.Endpoint;

import com.gewanbiao.service.impl.SystemInfoServiceImpl;
import com.gewanbiao.util.ProjectUtil;

import redis.clients.jedis.Jedis;


/**
 * 向redis注册system-manage服务器的ip,并发布webservice
 * @author wanbiao
 *
 */
public class StartUpListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContext) {
		
	}
    /**
     * 项目启动初始化
     */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		//连接redis服务
		Jedis edis = ProjectUtil.edis;
		String localIp = ProjectUtil.getRealIp();
		edis.set("system-manage-ip", localIp);
		//发布webservice服务
		Endpoint.publish("http://"+localIp+":456/systemInfo",new SystemInfoServiceImpl()); 
	}
	
}
