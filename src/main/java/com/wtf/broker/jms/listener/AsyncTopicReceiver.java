package com.wtf.broker.jms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.wtf.broker.shared.Mensaje;


public class AsyncTopicReceiver  {

	private static final Logger LOG = LoggerFactory.getLogger(AsyncTopicReceiver.class);

	public void receive(Mensaje mensaje) {
		LOG.info("Received message: {}", mensaje.getText());
	}

}
