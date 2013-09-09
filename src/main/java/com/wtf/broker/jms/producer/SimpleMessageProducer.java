package com.wtf.broker.jms.producer;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.wtf.broker.shared.Mensaje;

public class SimpleMessageProducer {
    
    private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageProducer.class);
    
    protected JmsTemplate jmsTemplate; 
    
    protected int numberOfMessages = 1; 
    
    public void setNumberOfMessages(int numberOfMessages) {
        this.numberOfMessages = numberOfMessages;
    }

    public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void sendMessages() throws JMSException {
        final StringBuilder buffer = new StringBuilder(); 
        
        Mensaje message = new Mensaje();
        message.setText("Hola");
		jmsTemplate.convertAndSend("COMPUTADORES", message);
        
        /**
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                //TextMessage message = session.createTextMessage(payload); 
            	Message message=  session.createMessage();
               // message.setIntProperty("messageCount", count);
            	//message.setObjectProperty("mensaje", new Date());
                LOG.info("Sending message number '{}'", 0);
                return message;
            }
        });
        */
        
        for (int i = 0; i < numberOfMessages; ++i) {
            buffer.append("Message '").append(i).append("' sent at: ").append(new Date());
            
            final int count = i;
            final String payload = buffer.toString();
            
           
            
			/**
            jmsTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage(payload); 
                	//Message message=  session.createMessage();
                    message.setIntProperty("messageCount", count);
                	//message.setObjectProperty("mensaje", new Date());
                    LOG.info("Sending message number '{}'", count);
                    return message;
                }
            });
            */
        }
    }
}
