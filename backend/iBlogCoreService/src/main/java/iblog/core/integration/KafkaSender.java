package iblog.core.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import iblog.core.model.BlogPost;

@Service
public class KafkaSender {
	@Autowired
	private KafkaTemplate<String, BlogPost> kafkaTemplate;

	@Value("${app.topic.blogpost_topic}")
	private String topic;

	public void send(BlogPost blogPost) {
		System.out.println("sending blog =" + blogPost.getPostId() + " to topic=" + topic);
		kafkaTemplate.send(topic, blogPost);
	}
}
