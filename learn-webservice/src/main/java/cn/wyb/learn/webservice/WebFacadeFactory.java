/** 
 * Project Name:learn-webservice 
 * File Name:WebFacadeFactory.java 
 * Package Name:cn.wyb.learn.webservice 
 * Date:2015年10月23日上午9:51:47 
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved. 
 * 
 */

package cn.wyb.learn.webservice;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:WebFacadeFactory <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年10月23日 上午9:51:47 <br/>
 * 
 * @author wangyongbing
 * @version
 * @since JDK 1.6
 * @see
 */
public class WebFacadeFactory
{
	
	private static Logger logger = LoggerFactory.getLogger(WebFacadeFactory.class);
	
	private static Map<String, Object> wsdlFacadeMap = new ConcurrentHashMap<String, Object>();

	public static <T> Object newFacade(String wsdlUrl, Class<T> clazz)
	{
		if (wsdlFacadeMap.containsKey(wsdlUrl))
		{
			return wsdlFacadeMap.get(wsdlUrl);
		} else
		{
			Object obj = null;
			try
			{
				QName qName = JaxWsDynamicClientFactory.newInstance().createClient(wsdlUrl).getEndpoint().getService().getName();
				obj = Service.create(new URL(wsdlUrl), qName).getPort(clazz);
				if (obj != null)
				{
					wsdlFacadeMap.put(wsdlUrl, obj);
				}
			} catch (Exception e)
			{
				logger.error("create facade error.",e);
			}
			return obj;
		}
	}
}
