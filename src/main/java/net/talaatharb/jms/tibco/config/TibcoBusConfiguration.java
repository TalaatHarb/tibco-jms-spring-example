package net.talaatharb.jms.tibco.config;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import com.tibco.tibjms.TibjmsConnectionFactory;

@Configuration
@EnableJms
public class TibcoBusConfiguration {
	@Value("${ems.password}")
	private String password;

	@Value("${ems.port}")
	private String port;

	@Value("${ems.queue}")
	private String queue;

	@Value("${ems.server}")
	private String server;

	@Value("${ems.user}")
	private String user;

	@Bean(name = "jmsConnectionFactory")
	public ConnectionFactory jmsConnectionFactory() throws JMSException {
		final TibjmsConnectionFactory factory = new TibjmsConnectionFactory();

		factory.setServerUrl(serverURL());
		factory.setUserName(user);
		factory.setUserPassword(password);

		return factory;
	}

	@Bean
	public JmsTemplate jmsTemplate(
			@Autowired ConnectionFactory jmsConnectionFactory) {
		final JmsTemplate jmsTemplate = new JmsTemplate();

		jmsTemplate.setConnectionFactory(jmsConnectionFactory);
		jmsTemplate.setDefaultDestinationName(queue);
		jmsTemplate.setExplicitQosEnabled(true);
		jmsTemplate.setDeliveryMode(2);
		jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
		jmsTemplate.setSessionTransacted(false);

		return jmsTemplate;
	}

	private String serverURL() {
		return "tcp://" + server + ":" + port;
	}
}