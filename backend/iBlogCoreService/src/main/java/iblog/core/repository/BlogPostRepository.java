package iblog.core.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import iblog.core.model.Article;


public interface BlogPostRepository extends MongoRepository<Article, Long> {

}
