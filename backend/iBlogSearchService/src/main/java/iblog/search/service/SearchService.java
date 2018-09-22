package iblog.search.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import iblog.search.model.Article;

public interface SearchService {
	Article save(Article article);

    void delete(Article article);

    Article findOne(Long id);

    Iterable<Article> findAll();

    Page<Article> findByAuthor(String author, PageRequest pageRequest);

    List<Article> findByTitle(String title);
}
