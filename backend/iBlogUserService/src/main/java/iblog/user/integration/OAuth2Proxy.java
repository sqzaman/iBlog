package iblog.user.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import iblog.user.dto.AccountDto;
import iblog.user.payload.AccountRequest;

@Component
public class OAuth2Proxy {
	
	@Autowired
	OAuth2FeignClient oauth2Client;
	
	public AccountDto registerUser(AccountRequest account) {
		AccountDto result = oauth2Client.createUser(account);
		return result;
	};
	
	public String getUserEmail(String email) {
		String result = oauth2Client.getUserEmail(email);
		return result;
	};
	
	@FeignClient("iBlogOAuth2Service")
	//@RibbonClient(name="iBlogOAuth2Service")
	//@HystrixCommand(fallbackMethod = "myFallBack")
	interface OAuth2FeignClient {
		
		@RequestMapping(value="/auth/signup",method=RequestMethod.POST)
		public AccountDto createUser(@RequestBody AccountRequest account);
		
		@RequestMapping(value="/auth/getemail/{username}",method=RequestMethod.GET)
		public String getUserEmail(@PathVariable("username") String username);
	}
}
