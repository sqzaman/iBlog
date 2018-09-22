package iblog.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import iblog.search.model.Article;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
	Page<Article> findByAuthor(String author, Pageable pageable);
	List<Article> findByTitle(String title);
}
