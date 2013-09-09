package com.wtf.broker.jms.producer;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import com.wtf.broker.shared.Mensaje;

public class SimpleMessageProducer {
    
    private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageProducer.class);
    
    protected JmsTemplate jmsTemplate; 
    
    public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void sendMessages() throws JMSException {
        //final StringBuilder buffer = new StringBuilder(); 
        
        Mensaje message = new Mensaje();
        message.setText("Hola");
		jmsTemplate.convertAndSend(message);
		LOG.info("enviando mensaje....");      
    }
}
