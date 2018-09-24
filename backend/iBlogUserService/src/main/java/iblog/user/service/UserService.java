package iblog.user.service;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import iblog.security.UserPrincipal;
import iblog.user.domain.User;
import iblog.user.dto.AccountDto;
import iblog.user.integration.OAuth2Proxy;
import iblog.user.payload.AccountRequest;
import iblog.user.payload.ApiResponse;
import iblog.user.payload.CustomerUpdateRequest;
import iblog.user.payload.UserSignUpRequest;
import iblog.user.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	OAuth2Proxy oauth2Proxy;

	public ResponseEntity<?> createAccount(UserSignUpRequest authorSignUpRequest) {

		if (userRepository.existsByEmail(authorSignUpRequest.getEmail())) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}

		AccountRequest account = new AccountRequest(authorSignUpRequest.getUsername(),
				authorSignUpRequest.getEmail(),
				authorSignUpRequest.getFirstName() + " " + authorSignUpRequest.getLastName(),
				authorSignUpRequest.getPassword());
		AccountDto responseEntity = oauth2Proxy.registerUser(account);

		if (responseEntity.isSuccess()) {
			User user = new User(authorSignUpRequest.getFirstName(), authorSignUpRequest.getLastName(),
					authorSignUpRequest.getEmail());

			User result = userRepository.save(user);

			URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/user/" + result.getId()).buildAndExpand(result.getId()).toUri();

			return ResponseEntity.created(location).body(new ApiResponse(true, "The Account is created successfully!"));
		} else {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(false,
							"Something problem in your data, please check! May be username already in use!"),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> updateCustomert(CustomerUpdateRequest customerUpdateRequest, UserPrincipal currentUser) {
		User user = userRepository.findByEmail(currentUser.getEmail());
		User updateResult = null;
		if (user == null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "User not found!"), HttpStatus.BAD_REQUEST);
		}

		user.setFirstname(customerUpdateRequest.getFirstname());
		user.setLastname(customerUpdateRequest.getLastname());

		updateResult = userRepository.save(user);

		if (updateResult != null) {
			URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/customer/" + updateResult.getId()).buildAndExpand(updateResult.getId()).toUri();

			return ResponseEntity.created(location).body(new ApiResponse(true, "Customer record is updated successfully!"));

		} else {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(false,
							"Something problem in your data, please check! Update request failed!"),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> getCustomer(UserPrincipal currentUser) {

		User user = userRepository.findByEmail(currentUser.getEmail());
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Specified user is not available!"),
				HttpStatus.BAD_REQUEST);

	}
}
