package iblog.core.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document
public class BlogPost {
	@Id
	private Long postId;
	
	private String title;


	private String body;
	
	private Status status;
	
	private Blogger blogger;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss.SSS")
	private Date postDate = new Date();

	
	private boolean pushedToKafka = false;
	
	public BlogPost() {
		
	}

	/**
	 * @param title
	 * @param body
	 * @param status
	 * @param blogger
	 */
	public BlogPost(Long postId, String title, String body, Status status, Blogger blogger) {
		this.postId = postId;
		this.title = title;
		this.body = body;
		this.status = status;
		this.blogger = blogger;

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

	public Blogger getBlogger() {
		return blogger;
	}

	public void setBlogger(Blogger blogger) {
		this.blogger = blogger;
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

	public void setPostDate(Date date) {
		this.postDate = date;
	}

	public boolean isPushedToKafka() {
		return pushedToKafka;
	}

	public void setPushedToKafka(boolean pushedToKafka) {
		this.pushedToKafka = pushedToKafka;
	}


}
