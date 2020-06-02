package net.talaatharb.jms.tibco.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class JmsSenderService {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${ems.queue}")
	private String queue;

	public void send(final String msg) {
		jmsTemplate.send(queue, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
                message.setText(msg);
                return message;
			}});
	}
}
