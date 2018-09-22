package iblog.blog;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import iblog.search.model.Article;
import iblog.search.service.SearchService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IBlogSearchServiceApplicationTests {
	 @Autowired
	    private SearchService searchService;
/*
	    @Autowired
	    private ElasticsearchTemplate esTemplate;

	    @Before
	    public void before() {
	        esTemplate.deleteIndex(Article.class);
	        esTemplate.createIndex(Article.class);
	        esTemplate.putMapping(Article.class);
	        esTemplate.refresh(Article.class);
	    }
*/
	    @Test
	    public void testSave() {

	    	Date date = new Date();
	    	Article article = new Article(1001L, "Java First Post", "Article body", "Syed Quamruzzaman", date);
	    	Article testArticle = searchService.save(article);

	        assertNotNull(testArticle.getId());
	        assertEquals(testArticle.getTitle(), article.getTitle());
	        assertEquals(testArticle.getBody(), article.getBody());
	        assertEquals(testArticle.getAuthor(), article.getAuthor());
	        assertEquals(testArticle.getPublishedDate(), article.getPublishedDate());

	    }

	    @Test
	    public void testFindOne() {
	    	Date date = new Date();
	    	Article article = new Article(1002L, "PHP First Post", "Article body of PHP post", "Syed Mahbub Rafael", date);
	    	searchService.save(article);

	    	Article testArticle = searchService.findOne(article.getId());

	        assertNotNull(testArticle.getId());
	        assertEquals(testArticle.getTitle(), article.getTitle());
	        assertEquals(testArticle.getBody(), article.getBody());
	        assertEquals(testArticle.getAuthor(), article.getAuthor());
	        assertEquals(testArticle.getPublishedDate(), article.getPublishedDate());

	    }

	    /*
	    @Test
	    public void testFindByTitle() {

	        Book book = new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
	        bookService.save(book);

	        List<Book> byTitle = bookService.findByTitle(book.getTitle());
	        assertThat(byTitle.size(), is(1));
	    }

	    @Test
	    public void testFindByAuthor() {

	        List<Book> bookList = new ArrayList<>();

	        bookList.add(new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
	        bookList.add(new Book("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
	        bookList.add(new Book("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));
	        bookList.add(new Book("1007", "Spring Data + ElasticSearch", "Rambabu Posa", "01-APR-2017"));
	        bookList.add(new Book("1008", "Spring Boot + MongoDB", "Mkyong", "25-FEB-2017"));

	        for (Book book : bookList) {
	            bookService.save(book);
	        }

	        Page<Book> byAuthor = bookService.findByAuthor("Rambabu Posa", new PageRequest(0, 10));
	        assertThat(byAuthor.getTotalElements(), is(4L));

	        Page<Book> byAuthor2 = bookService.findByAuthor("Mkyong", new PageRequest(0, 10));
	        assertThat(byAuthor2.getTotalElements(), is(1L));

	    }

	    @Test
	    public void testDelete() {

	        Book book = new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
	        bookService.save(book);
	        bookService.delete(book);
	        Book testBook = bookService.findOne(book.getId());
	        assertNull(testBook);
	    }
	    */
}
