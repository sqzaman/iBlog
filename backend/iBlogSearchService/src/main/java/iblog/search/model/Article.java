package iblog.search.model;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName = "blog", type = "articles")
public class Article {
	@Id
    private Long id;
     
    private String title;
    
    private String body;

    private String author;
    
    private Date publishedDate;

	public Article() {
		
	}
	
	/**
	 * @param postId
	 * @param title
	 * @param author
	 * @param body
	 */
	public Article(Long id, String title, String body, String author, Date publishedDate) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.body = body;
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

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	

}
