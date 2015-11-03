/** 
 * Project Name:sble-mq 
 * File Name:ActiveMqTest.java 
 * Package Name:cn.wyb.learn.mq.test 
 * Date:2015年11月2日下午2:35:45 
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved. 
 * 
 */

package cn.wyb.learn.mq.test;

import static org.junit.Assert.*;

import java.net.URI;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.wyb.learn.mq.service.ActiveMqService;

/**
 * ClassName:ActiveMqTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年11月2日 下午2:35:45 <br/>
 * 
 * @author wangyongbing
 * @version
 * @since JDK 1.6
 * @see
 */
public class ActiveMqTest
{
	private Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args)
	{
		ActiveMqService.getInstance().startMq();
		ActiveMqService.getInstance().stopMq();
//		String name = "wyb";
//		String url = "tcp://localhost:61666";
//		//new ActiveMqTest().test();
//		new ActiveMqTest().stopMq(name,url);
	}
	
	@Test
	public void test()
	{
		
		String name = "wyb";
		String url = "tcp://localhost:61666";
		startMq(name, url);
		
		String message = "this is a test message.";
		//连接工厂
		ConnectionFactory connectionFactory;
		//连接
		Connection connection;
		
		Session session;
		//消息目的地
		Destination destination;
		
		//消息生产者
		MessageProducer messageProducer;
		
		try
		{
			connectionFactory = new ActiveMQConnectionFactory(url);//创建连接，需要activemq启动
			connection = connectionFactory.createConnection();
			connection.start();
			logger.info("连接到mq,url = {}",url);
			// 第一个参数是否使用事务:当消息发送者向消息提供者（即消息代理）发送消息时，消息发送者等待消息代理的确认，没有回应则抛出异常，消息发送程序负责处理这个错误。
            // 第二个参数消息的确认模式：
            // AUTO_ACKNOWLEDGE ： 指定消息提供者在每次收到消息时自动发送确认。消息只向目标发送一次，但传输过程中可能因为错误而丢失消息。
            // CLIENT_ACKNOWLEDGE ： 由消息接收者确认收到消息，通过调用消息的acknowledge()方法（会通知消息提供者收到了消息）
            // DUPS_OK_ACKNOWLEDGE ： 指定消息提供者在消息接收者没有确认发送时重新发送消息（这种确认模式不在乎接收者收到重复的消息）。
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            
            destination = session.createQueue("ActiveMqs queue name");
            messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            messageProducer.send(session.createTextMessage(message));
            session.commit();
		} catch (JMSException e)
		{
			logger.error("连接Mq失败",e);
			e.printStackTrace();
		}
	}
	
	/** 
	 * startMq:(创建mq启动代理，启动). <br/> 
	 * 
	 * @author wangyongbing 
	 * @param name
	 * @param url 
	 * @since JDK 1.6 
	 */  
	private void startMq(String name,String url){
		BrokerService brokerService = new BrokerService();
		brokerService.setBrokerName(name);
		try
		{
			brokerService.addConnector(url);
			brokerService.start();
			logger.info("mq启动成功 name = {},url = {}",name,url);
		} catch (Exception e)
		{
			logger.error("创建并启动mq代理失败name = {},url = {}。",name,url,e);
			e.printStackTrace();
		}
	}
	
	private void startMqFactory(String nameUrl){
		try
		{
			BrokerService service = BrokerFactory.createBroker(nameUrl);
			service.start();
		} catch (Exception e)
		{
			logger.error("通过工厂启动mq失败 nameUrl={}",nameUrl,e);
			e.printStackTrace();
		}
	}
	
	private void stopMq(String name,String url){
		BrokerService brokerService = new BrokerService();
		brokerService.setBrokerName(name);
		try
		{
			brokerService.addConnector(url);
			brokerService.stop();
			logger.info("mq关闭成功 name = {},url = {}",name,url);
		} catch (Exception e)
		{
			logger.error("关闭mq代理失败name = {},url = {}。",name,url,e);
			e.printStackTrace();
		}
	}
	
	@Test
	public void startMq(){
		ActiveMqService.getInstance().startMq();
	}
	
	@Test
	public void stopMq(){
		ActiveMqService.getInstance().stopMq();
	}
}
