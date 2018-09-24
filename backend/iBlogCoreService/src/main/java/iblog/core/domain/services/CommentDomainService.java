package iblog.core.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iblog.core.model.Article;
import iblog.core.model.Author;
import iblog.core.model.Comment;
import iblog.core.model.Status;
import iblog.core.payload.BlogPostRequest;
import iblog.core.payload.CommentPostRequest;
import iblog.core.repository.BlogPostRepository;
import iblog.core.repository.CommentRepository;
import iblog.core.seq.exception.SequenceException;
import iblog.core.seq.model.SequenceId;
import iblog.security.UserPrincipal;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class CommentDomainService {
	private static final String HOSTING_SEQ_KEY = "sequence_comment";
	
	@Autowired
	BlogPostRepository blogPostRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
		
	@Autowired 
	private MongoOperations mongo;
	
	public Comment insertNewComment(CommentPostRequest commentPostRequest, UserPrincipal currentUser, Long postId) {
		Article blogPost = blogPostRepository.findById(postId).orElse(null);
		Author a = new Author(currentUser.getId(), currentUser.getName());	
		
		Long commentId = getNextSequenceId(HOSTING_SEQ_KEY);

		return new Comment(commentId, commentPostRequest.getComment(), blogPost, a, new Comment());
	}
	
	
	public Comment replyToComment(CommentPostRequest commentPostRequest, UserPrincipal currentUser, Long commentId) {
		
		Comment comment = commentRepository.findById(commentId).orElse(null);		
		
		Article blogPost = blogPostRepository.findById(comment.getBlogPost().getPostId()).orElse(null);
		Author a = new Author(currentUser.getId(), currentUser.getName());	
		
		Long newCommentId = getNextSequenceId(HOSTING_SEQ_KEY);
		
		return new Comment(newCommentId, commentPostRequest.getComment(), blogPost, a, comment);
	}
	
	
	@Transactional
	public Long getNextSequenceId(String seqName) throws SequenceException {
		SequenceId counter = mongo.findAndModify(
		            query(where("_id").is(seqName)),
		            new Update().inc("seq",1),
		            options().returnNew(true).upsert(true),
		            SequenceId.class);
		       return counter.getSeq();

	}

}
