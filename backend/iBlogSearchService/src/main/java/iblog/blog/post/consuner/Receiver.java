package iblog.blog.post.consuner;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import iblog.core.model.BlogPost;


public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "${kafka.topic.blogpost_topic}")
  public void receive(BlogPost blogPost) {
    LOGGER.info("received car='{}'", blogPost.toString());
    System.out.println("Received:"+ blogPost);
    latch.countDown();
  }
}
