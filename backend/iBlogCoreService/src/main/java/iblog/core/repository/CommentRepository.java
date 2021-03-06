package iblog.core.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import iblog.core.model.Article;
import iblog.core.model.Comment;


public interface CommentRepository extends MongoRepository<Comment, Long> {

}
