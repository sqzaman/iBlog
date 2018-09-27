package iblog.core.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class JmsSender {
	@Autowired
	JmsTemplate jmsTemplate;

	public void sendJMSMessage(String channel, String message) {
		System.out.println("Sending a JMS message:" + message);
		jmsTemplate.convertAndSend(channel, message);
	}
}
