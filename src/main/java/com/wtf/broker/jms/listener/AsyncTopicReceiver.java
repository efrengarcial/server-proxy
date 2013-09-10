package com.wtf.broker.jms.listener;

import java.io.Serializable;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wtf.broker.jms.ProducerApp;
import com.wtf.broker.jms.producer.SimpleMessageProducer;
import com.wtf.quotation.domain.SolicitudCotizacion;


public class AsyncTopicReceiver  {

	private static final Logger LOG = LoggerFactory.getLogger(AsyncTopicReceiver.class);

	public void handleMessage(Serializable  mensaje) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/producer-jms-context.xml", ProducerApp.class);
        SimpleMessageProducer producer = (SimpleMessageProducer) context.getBean("messageProducer");
        try {
        	
			producer.sendMessages((SolicitudCotizacion)mensaje);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			LOG.info("Ha ocurrido un error al enviar el mensaje ",e.toString());
		}
		LOG.info("Enviando cotizacion a gestor ");
	}

}
