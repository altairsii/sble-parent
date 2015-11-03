/** 
 * Project Name:sble-mq 
 * File Name:ActiveMqConsumertest.java 
 * Package Name:cn.wyb.learn.mq.test 
 * Date:2015年11月3日上午10:58:37 
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved. 
 * 
 */  
  
package cn.wyb.learn.mq.test;  

import static org.junit.Assert.*;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * ClassName:ActiveMqConsumertest <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年11月3日 上午10:58:37 <br/> 
 * @author   wangyongbing 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class ActiveMqConsumertest
{
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private String url = "tcp://localhost:61666";
	
	public static void main(String[] args)
	{
		new ActiveMqConsumertest().createCon();
	}
	
	@Test
	public void test()
	{
		createCon();
	}
	
	private void createCon(){
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
		
		Connection con;
		
		Session session;
		
		Destination destination;
		
		MessageConsumer mc;
		
		Message message;
		try
		{
			con = factory.createConnection();
			con.start();
			logger.info("创建连接并连接mq成功");
			
			session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			destination  = session.createQueue("mc");
			
			mc = session.createConsumer(destination);
			
			message = mc.receive(1000);
			if(message instanceof TextMessage){
				TextMessage tm = (TextMessage) message;
				String m = tm.getText();
				logger.info("接收到的消息是：{}。",m);
			}else{
				logger.info("接收到的消息是：{}",message);
			}
			
			
		} catch (JMSException e)
		{
			logger.error("创建连接并连接mq失败，url={}",url,e);
			e.printStackTrace();
		}
	}
	
}
  
