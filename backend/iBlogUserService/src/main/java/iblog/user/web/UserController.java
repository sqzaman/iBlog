package iblog.user.web;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iblog.security.CurrentUser;
import iblog.security.UserPrincipal;
import iblog.user.payload.UserUpdateRequest;
import iblog.user.payload.UserSignUpRequest;
import iblog.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/signup")
	public ResponseEntity<?> addCustomer(@Valid @RequestBody UserSignUpRequest authorSignUpRequest) {
		return userService.createAccount(authorSignUpRequest);
	}
	
    @GetMapping("/getRole")
    public ResponseEntity<?> getRole(@CurrentUser UserPrincipal currentUser) {
    	return new  ResponseEntity<Collection<? extends GrantedAuthority>>(currentUser.getAuthorities(), HttpStatus.OK);
    }
	
	@PostMapping(value = "/update")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateRequest customerUpdateRequest, @CurrentUser UserPrincipal currentUser) {
		return userService.updateUser(customerUpdateRequest, currentUser);
	}	

	@GetMapping("/get")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getUser(@CurrentUser UserPrincipal currentUser) {
		return userService.getUser(currentUser);
	}
}
