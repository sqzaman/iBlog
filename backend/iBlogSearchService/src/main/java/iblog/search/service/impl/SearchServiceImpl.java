package iblog.search.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import iblog.search.model.Article;
import iblog.search.repository.ArticleRepository;
import iblog.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public Article save(Article article) {
		return articleRepository.save(article);
	}

	@Override
	public void delete(Article article) {
		articleRepository.delete(article);

	}

	@Override
	public Article findOne(Long id) {
		return articleRepository.findById(id).orElse(null);
	}

	@Override
	public Iterable<Article> findAll() {
		return articleRepository.findAll();
	}

	@Override
	public Page<Article> findByAuthor(String author, PageRequest pageRequest) {
		return articleRepository.findByAuthor(author, pageRequest);
	}

	@Override
	public List<Article> findByTitle(String title) {
		return articleRepository.findByTitle(title);
	}

}