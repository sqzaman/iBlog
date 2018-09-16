package iblog.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iblog.core.payload.BlogPostRequest;
import iblog.core.services.BlogPostService;



@RestController
public class BlogPostController {
	
	@Autowired
	BlogPostService blogPostService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createOrder(@RequestBody BlogPostRequest blogPostRequest) {
		return blogPostService.createNewBlogPost(blogPostRequest);
	}
	
	
}
