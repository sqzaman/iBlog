package iblog.core.model;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;



public class BlogPost {

	private Long postId;
	
	private String title;


	private String body;
	
	private Status status;
	
	private Author author;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private Date postDate = new Date();
	
	private boolean sentToSearchService = false;
	
	public BlogPost() {
		
	}

	/**
	 * @param title
	 * @param body
	 * @param status
	 * @param blogger
	 */
	public BlogPost(Long postId, String title, String body, Status status, Author author) {
		this.postId = postId;
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



	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getPostId() {
		return postId;
	}



	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public boolean isSentToSearchService() {
		return sentToSearchService;
	}

	public void setSentToSearchService(boolean sentToSearchService) {
		this.sentToSearchService = sentToSearchService;
	}

	@Override
	public String toString() {
		return "BlogPost [postId=" + postId + ", title=" + title + ", body=" + body + ", status=" + status
				+ ", author=" + author + ", date=" + postDate + ", sentToSearchService=" + sentToSearchService + "]";
	}
	
	

}
