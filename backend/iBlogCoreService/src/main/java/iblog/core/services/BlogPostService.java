package iblog.core.services;

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
	public ResponseEntity<?> createNewBlogPost(BlogPostRequest blogPostRequest){
		Article blogPost = blogPostRepository.save(blogPostDomainService.createNewBlogPost(blogPostRequest)); 
		return new ResponseEntity<Article>(blogPost, HttpStatus.OK); 		
	}
	
	@Transactional
	public ResponseEntity<?> approveBlogPost(Long postId, Status status){
		
		Article blogPost = blogPostRepository.findById(postId).orElse(null);
		Article updateResult = null;
		if (blogPost == null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Customer not found!"), HttpStatus.BAD_REQUEST);
		}

		blogPost.setStatus(status);
		updateResult = blogPostRepository.save(blogPost);

		if (updateResult != null) {			
			if(status == Status.APPROVED) {
				try {
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
	
}
