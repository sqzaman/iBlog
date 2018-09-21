package iblog.core.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iblog.core.model.Status;
import iblog.core.payload.BlogPostRequest;
import iblog.core.services.BlogPostService;

@RestController
public class BlogPostController {
	
	@Autowired
	BlogPostService blogPostService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createArticle(@Valid @RequestBody BlogPostRequest blogPostRequest) {
		return blogPostService.createNewBlogPost(blogPostRequest);
	}
	
	@PostMapping("/approve/{postId}/{status}")
	public ResponseEntity<?> approveArticle(@PathVariable(name = "postId", required = true) final Long postId,
			@PathVariable(name = "status", required = true) final Status status) {
		return blogPostService.approveBlogPost(postId, status);
	}
	
	
}
