package com.wtf.broker.jms.listener;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wtf.broker.shared.Mensaje;


public class AsyncTopicReceiver  {

	private static final Logger LOG = LoggerFactory.getLogger(AsyncTopicReceiver.class);

	public void handleMessage(Serializable  mensaje) {
		LOG.info("Received message: " , (Mensaje)mensaje  );
	}

}
