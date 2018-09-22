package iblog.core.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import iblog.core.domain.services.BlogPostDomainService;
import iblog.core.integration.Sender;
import iblog.core.model.Article;
import iblog.core.model.Status;
import iblog.core.payload.ApiResponse;
import iblog.core.payload.BlogPostRequest;
import iblog.core.repository.BlogPostRepository;
import iblog.core.util.Helper;
import iblog.security.UserPrincipal;


@Service
public class BlogPostService {
	
	@Autowired
	BlogPostRepository blogPostRepository;
	@Autowired
	BlogPostDomainService blogPostDomainService;
	@Autowired
	Sender kafkaSender;
	
	/**
	 * Create new blog post
	 * @param blogPostRequest
	 * @return
	 */
	@Transactional
	public ResponseEntity<?> createNewBlogPost(BlogPostRequest blogPostRequest, UserPrincipal currentUser){
		Article blogPost = blogPostRepository.save(blogPostDomainService.createNewBlogPost(blogPostRequest, currentUser)); 
		return new ResponseEntity<Article>(blogPost, HttpStatus.OK); 		
	}
	
	@Transactional
	public ResponseEntity<?> approveBlogPost(Long postId, Integer status){
		
		Article blogPost = blogPostRepository.findById(postId).orElse(null);
		Article updateResult = null;
		if (blogPost == null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Customer not found!"), HttpStatus.BAD_REQUEST);
		}
		
		

		blogPost.setStatus(Helper.fromIntStatus(status));
		updateResult = blogPostRepository.save(blogPost);

		if (updateResult != null) {			
			if(Helper.fromIntStatus(status) == Status.APPROVED) {
				try {
					System.out.println("...............Sending to kafka................");
					kafkaSender.send(blogPost);
					// changing the status
					blogPost.setPushedToKafka(true);
					blogPostRepository.save(blogPost);
				} catch (Exception ex) {
					System.out.println(ex.getStackTrace());
				}
			}	
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(true, "Article record is updated successfully!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(false,
							"Something problem in your data, please check! Update request failed!"),
					HttpStatus.BAD_REQUEST);
		}
	
	}
	@Transactional
	public ResponseEntity<?> updateBlogPost(@Valid BlogPostRequest blogPostRequest, UserPrincipal currentUser,
			Long postId) {
		
		Article blogPost = blogPostRepository.findById(postId).orElse(null);
		
		Article updateResult = null;
		
		if (blogPost == null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Post not found!"), HttpStatus.BAD_REQUEST);
		}	
		
		blogPost.setTitle(blogPostRequest.getTitle());
		blogPost.setBody(blogPostRequest.getBody());			

		updateResult = blogPostRepository.save(blogPost);
		
		
		// Not sure  - Samiul
		if (updateResult != null) {			
			if(blogPost.getStatus() == Status.APPROVED) {
				try {
					System.out.println("...............Updating in kafka................");
					kafkaSender.send(blogPost);	// Should there be an update method?				
				} catch (Exception ex) {
					System.out.println(ex.getStackTrace());
				}
			}			
			/*return new ResponseEntity<ApiResponse>(
					new ApiResponse(true, "Article record is updated successfully!"),
					HttpStatus.OK);*/
			
			return new ResponseEntity<Article>(blogPost, HttpStatus.OK); 	
			
		} else {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(false,
							"Something problem in your data, please check! Update request failed!"),
					HttpStatus.BAD_REQUEST);
		}
		
		
		/*Article blogPost = blogPostRepository.save(blogPostDomainService.createNewBlogPost(blogPostRequest, currentUser)); */
		//return new ResponseEntity<Article>(blogPost, HttpStatus.OK); 	
		
	}
	
}
