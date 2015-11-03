/** 
 * Project Name:sble-mq 
 * File Name:ActiveMqService.java 
 * Package Name:cn.wyb.learn.mq 
 * Date:2015年11月3日下午2:17:50 
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved. 
 * 
 */  
  
package cn.wyb.learn.mq.service;  

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * ClassName:ActiveMqService mq启动停止 <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年11月3日 下午2:17:50 <br/> 
 * @author   wangyongbing 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class ActiveMqService
{

	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * service:启动mq的服务. 
	 * @author wangyongbing
	 */
	private BrokerService service;
	
	private static ActiveMqService activeMqService;
	
	private String name = "broker";
	
	private String url = "tcp://localhost:61666";
	
	/** 
	 * getInstance:(获取对象). <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author wangyongbing 
	 * @param name mq名称 eg：broker
	 * @param url mq地址 eg：tcp://localhost:61616
	 * @return 
	 * @since JDK 1.6 
	 */  
	public static ActiveMqService getInstance(){
		if(null == activeMqService){
			activeMqService = new ActiveMqService();
		}
		return activeMqService;
	}
	
	public void startMq(){
		if(null == service){
			createMq(name, url);
		}
		try
		{
			service.start();
			logger.info("启动mq成功，name={},url={}",name,url);
		} catch (Exception e)
		{
			logger.error("启动mq失败，name={},url={}",name,url,e);
			e.printStackTrace();
		}
	}
	
	/** 
	 * createMq:(创建mq启动Broker). <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author wangyongbing 
	 * @param name
	 * @param url 
	 * @since JDK 1.6 
	 */  
	private void createMq(String name,String url){
		String brokerURI = name +":" + url;//"broker:tcp://localhost:61616"
		try
		{
			service = BrokerFactory.createBroker(brokerURI);
			logger.info("创建mq启动Broker成功。name={},url={}",name,url);
		} catch (Exception e)
		{
			logger.error("创建mq启动Broker失败,name={},url={}",name,url,e);
			e.printStackTrace();
		} 
	}
	
	public void stopMq(){
		if(null == service){
			logger.info("mq服务未启动");
			return;
		}
		try
		{
			service.stop();
			logger.info("mq服务关闭，BrokerService = {}", service);
		} catch (Exception e)
		{
			logger.error("Mq服务关闭异常",e);
			e.printStackTrace();
		}
	}
}
  
