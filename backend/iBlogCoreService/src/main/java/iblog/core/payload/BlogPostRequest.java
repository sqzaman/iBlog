package iblog.core.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.core.annotation.Order;

import ch.qos.logback.core.status.Status;

public class BlogPostRequest {
	@NotBlank(message = "Title cannot be empty!")
	@Size(min = 15, max = 1000)
	@Order(1)
	private String title;

	@NotBlank(message = "Body cannot be empty!")
	@Size(min = 4, max = 40000)
	@Order(2)
	private String body;

	@NotBlank(message = "Status cannot be empty!")
	@Order(3)
	private Status status;
	
	public BlogPostRequest() {
		
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
	

}
