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
	
	private String comment;
	
	private Article blogPost;
	
	private Author author;	
	
	private Comment parent;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private Date postDate;
	
	public Comment() {
		super();
	}

	public Comment(Long id, String comment, Article blogPost, Author author, Comment parent) {
		super();
		this.id = id;
		this.comment = comment;
		this.blogPost = blogPost;
		this.author = author;
		this.parent = parent;	
	}

	
	public Comment(String comment, Article blogPost, Author author, Comment parent) {
		super();
		this.comment = comment;
		this.blogPost = blogPost;
		this.author = author;
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Article getBlogPost() {
		return blogPost;
	}

	public void setBlogPost(Article blogPost) {
		this.blogPost = blogPost;
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

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
	
}
