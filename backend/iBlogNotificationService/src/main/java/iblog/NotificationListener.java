package iblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class NotificationListener {	
	//	private static final String blogpostCreatedChannel = "blogpostCreatedChannel";
	//private static final String blogpostApprovedChannel = "blogpostApprovedChannel";
	
	public NotificationListener() {
		System.out.println(".....listening jms messages......"); 
	}

	@JmsListener(destination= "blogpostCreatedChannel")
	public void receivedMessageForBlogPostCreated(final String message) {
		System.out.println("===================================");
		System.out.println("Received a new message: " + message);
		System.out.println("===================================");
	}
	
	@JmsListener(destination= "blogpostApprovedChannel")
	public void receivedMessageForBlogPostApproved(final String message) {
		System.out.println("===================================");
		System.out.println("Received a new message: " + message);
		System.out.println("===================================");
	}
}
