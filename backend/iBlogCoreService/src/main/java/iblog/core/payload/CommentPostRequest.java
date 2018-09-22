package iblog.core.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.core.annotation.Order;

public class CommentPostRequest {
	@NotBlank(message = "Comment cannot be empty!")
	@Size(min = 4, max = 40000)
	@Order(1)
	private String comment;

	public CommentPostRequest() {
		super();
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
