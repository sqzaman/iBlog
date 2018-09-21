package iblog.blog.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import iblog.blog.model.Article;

public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
	Page<Article> findByAuthor(String author, Pageable pageable);
	List<Article> findByTitle(String title);
}
