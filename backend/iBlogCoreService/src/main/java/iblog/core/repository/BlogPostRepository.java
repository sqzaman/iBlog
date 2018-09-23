package iblog.core.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import iblog.core.model.Article;
import iblog.core.model.Author;


public interface BlogPostRepository extends MongoRepository<Article, Long> {
	
	List<Article> findAllByAuthorOrderByIdDesc(Author author);

}
