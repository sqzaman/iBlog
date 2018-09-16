package iblog.core.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import iblog.core.model.BlogPost;


public interface BlogPostRepository extends MongoRepository<BlogPost, Long> {

}
