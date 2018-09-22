package iblog.search.post.consuner;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import iblog.search.model.Article;
import iblog.search.repository.ArticleRepository;
import iblog.search.service.SearchService;


public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
  @Autowired
  SearchService searchService;


  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "${kafka.topic.blogpost_topic}")
  public void receive(iblog.core.model.Article art) {
    LOGGER.info("received car='{}'", art.toString());
    System.out.println("Received:"+ art);

    //latch.countDown();
    searchService.putArticleToElasticSearchIndex(art);
  }

}
