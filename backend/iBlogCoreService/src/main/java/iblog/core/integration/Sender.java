package iblog.core.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import iblog.core.model.Article;

@Service
public class Sender {

  private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

  @Value("${kafka.topic.blogpost_topic}")
  private String blogPostTopic;

  @Autowired
  private KafkaTemplate<String, Article> kafkaTemplate;

  public void send(Article blogPost) {
    LOGGER.info("sending blogPost='{}'", blogPost.toString());
    kafkaTemplate.send(blogPostTopic, blogPost);
  }
}
