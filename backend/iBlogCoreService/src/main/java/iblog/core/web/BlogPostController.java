package iblog.core.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iblog.core.model.Status;
import iblog.core.payload.BlogPostRequest;
import iblog.core.services.BlogPostService;
import iblog.security.CurrentUser;
import iblog.security.UserPrincipal;

@RestController
public class BlogPostController {
	
	@Autowired
	BlogPostService blogPostService;
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createArticle(@Valid @RequestBody BlogPostRequest blogPostRequest, @CurrentUser UserPrincipal currentUser) {
		return blogPostService.createNewBlogPost(blogPostRequest, currentUser);
	}
	
	@PostMapping("/update/{postId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updateArticle(@Valid @RequestBody BlogPostRequest blogPostRequest, @CurrentUser UserPrincipal currentUser
			, @PathVariable(name = "postId", required = true) final Long postId) {
		return blogPostService.updateBlogPost(blogPostRequest, currentUser, postId);
	}
	
	
	
	@PostMapping("/approve/{postId}/{status}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> approveArticle(@PathVariable(name = "postId", required = true) final Long postId,
			@PathVariable(name = "status", required = true) final Integer status, @CurrentUser UserPrincipal currentUser) {
		return blogPostService.approveBlogPost(postId, status);
	}
	
	
}
