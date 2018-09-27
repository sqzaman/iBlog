package iblog.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document
public class Article {
	@Id
	private Long id;
	
	private String title;

	private String body;
	
	private Status status;
	
	private Author author;
	
	List<Comment> comments = new ArrayList<Comment>();

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private Date postDate = new Date();

	@JsonIgnore
	private boolean pushedToKafka = false;
	
	public Article() {
		
	}

	/**
	 * @param title
	 * @param body
	 * @param status
	 * @param blogger
	 */
	public Article(Long id, String title, String body, Status status, Author author) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.status = status;
		this.author = author;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date date) {
		this.postDate = date;
	}

	public boolean isPushedToKafka() {
		return pushedToKafka;
	}

	public void setPushedToKafka(boolean pushedToKafka) {
		this.pushedToKafka = pushedToKafka;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	public void removeComment(Comment comment) {
		this.comments.remove(comment);
	}

}
