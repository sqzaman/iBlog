package iblog.core.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import iblog.core.domain.services.BlogPostDomainService;
import iblog.core.model.BlogPost;
import iblog.core.payload.BlogPostRequest;
import iblog.core.repository.BlogPostRepository;


@Service
public class BlogPostService {
	
	@Autowired
	BlogPostRepository blogPostRepository;
	@Autowired
	BlogPostDomainService blogPostDomainService;
	
	/**
	 * Create new blog post
	 * @param blogPostRequest
	 * @return
	 */
	public ResponseEntity<?> createNewBlogPost(BlogPostRequest blogPostRequest){
		BlogPost blogPost = blogPostRepository.save(blogPostDomainService.createNewBlogPost(blogPostRequest)); 
		return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK); 
		
	}
	
}
