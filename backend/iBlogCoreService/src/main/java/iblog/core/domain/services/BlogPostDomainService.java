package iblog.core.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import iblog.core.model.BlogPost;
import iblog.core.model.Blogger;
import iblog.core.model.Status;
import iblog.core.payload.BlogPostRequest;
import iblog.core.seq.repository.SequenceRepository;

@Service
public class BlogPostDomainService {
	private static final String HOSTING_SEQ_KEY = "hosting";
	
	@Autowired
	private SequenceRepository sequenceDao;
	
	public BlogPost createNewBlogPost(BlogPostRequest blogPostRequest) {
		Blogger b = new Blogger(10001, "Zaman");
		return new BlogPost(sequenceDao.getNextSequenceId(HOSTING_SEQ_KEY), blogPostRequest.getTitle(), blogPostRequest.getBody(), Status.PENDING , b);
	}

}
