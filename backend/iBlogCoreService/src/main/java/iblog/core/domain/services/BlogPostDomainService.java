package iblog.core.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iblog.core.model.BlogPost;
import iblog.core.model.Blogger;
import iblog.core.model.Status;
import iblog.core.payload.BlogPostRequest;
import iblog.core.seq.exception.SequenceException;
import iblog.core.seq.model.SequenceId;



import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Optional;

@Service
public class BlogPostDomainService {
	private static final String HOSTING_SEQ_KEY = "sequence";
	

	
	 @Autowired 
	 private MongoOperations mongo;
	
	public BlogPost createNewBlogPost(BlogPostRequest blogPostRequest) {
		Blogger b = new Blogger(1, "Zaman");
		Long blogId = getNextSequenceId(HOSTING_SEQ_KEY);

		return new BlogPost(blogId, blogPostRequest.getTitle(), blogPostRequest.getBody(), blogPostRequest.getStatus() , b);
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
