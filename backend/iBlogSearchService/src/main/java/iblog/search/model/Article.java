package iblog.search.model;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName = "article_index", type = "article", shards = 1, replicas = 0, refreshInterval = "-1")
public class Article {
	@Id
    private Long id;
     
    private String title;
    
    private String body;

    private String author;
    
    private String publishedDate;

	public Article() {
		
	}
	
	/**
	 * @param postId
	 * @param title
	 * @param author
	 * @param body
	 */
	public Article(Long id, String title, String body, String author, String publishedDate) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.body = body;
		this.publishedDate = publishedDate;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	

}
