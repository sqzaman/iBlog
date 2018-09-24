package iblog.user.service;

import java.net.URI;

import org.apache.commons.math.stat.descriptive.summary.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import iblog.customers.integration.OAuth2Proxy;
import iblog.customers.payload.AccountRequest;
import iblog.customers.payload.ApiResponse;
import iblog.customers.payload.UserSignUpRequest;
import iblog.customers.payload.CustomerUpdateRequest;
import iblog.security.UserPrincipal;
import iblog.user.domain.ProductImage;
import iblog.user.domain.User;
import iblog.user.dto.AccountDto;
import iblog.user.repository.ProductImageRepository;
import iblog.user.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private FileStorageService fileStorageService;
	@Autowired
	private ProductImageRepository productImageRepository;
	@Autowired
	private UserRepository productRepository;

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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<?> uploadImage(MultipartFile file, Long productId) {
		String fileName = fileStorageService.storeFile(file);

		String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/productImage/").path(fileName)
				.toUriString();
		//Product product = productRepository.findByProductId(productId).orElse(null);
		User product = productRepository.findById(productId).orElse(null);
		if (product != null) {
			ProductImage productImage = productImageRepository
					.save(new ProductImage());

			//return new ResponseEntity<UploadFileResponse>(new UploadFileResponse(productImage.getFileName(),
			//		productImage.getFileUri(), productImage.getFileType(), productImage.getSize()), HttpStatus.OK);
			return new ResponseEntity<ProductImage>(productImage, HttpStatus.OK);

		} else {
			return new ResponseEntity(new ApiResponse(false, "Specified product is not available!"),
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
