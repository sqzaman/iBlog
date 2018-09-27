package iblog.core.model;



import java.util.Date;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document
public class Comment {
	@Id	
	private Long id;
	
	private String message;
	

	private Author author;	
	
	private Comment parent;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private Date commentDate = new Date();
	
	public Comment() {
		super();
	}

	public Comment(Long id, String message, Author author) {
		super();
		this.id = id;
		this.message = message;
		this.author = author;
	}

	
	public Comment(String comment, Author author, Comment parent) {
		super();
		this.message = comment;
		this.author = author;
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}


	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Comment getParent() {
		return parent;
	}

	public void setParent(Comment parent) {
		this.parent = parent;
	}

	
	
}
