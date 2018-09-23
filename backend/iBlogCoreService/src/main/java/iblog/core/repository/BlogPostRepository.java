package iblog.core.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import iblog.core.model.Article;
import iblog.core.model.Author;
import iblog.core.model.Status;


public interface BlogPostRepository extends MongoRepository<Article, Long> {
	
	List<Article> findAllByAuthorOrderByIdDesc(Author author);
	
	List<Article> findAllByStatusOrderByIdDesc(Status status);
	
	List<Article> findAllByOrderByIdDesc();

}
