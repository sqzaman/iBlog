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


import iblog.customers.payload.UserSignUpRequest;

import iblog.customers.payload.CustomerUpdateRequest;
import iblog.security.CurrentUser;
import iblog.security.UserPrincipal;
import iblog.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService customerService;
	
	@PostMapping(value = "/signup")
	public ResponseEntity<?> addCustomer(@Valid @RequestBody UserSignUpRequest authorSignUpRequest) {
		return customerService.createAccount(authorSignUpRequest);
	}
	
    @GetMapping("/getRole")
    public ResponseEntity<?> getRole(@CurrentUser UserPrincipal currentUser) {
    	return new  ResponseEntity<Collection<? extends GrantedAuthority>>(currentUser.getAuthorities(), HttpStatus.OK);
    }
	
	@PostMapping(value = "/update")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest, @CurrentUser UserPrincipal currentUser) {
		return customerService.updateCustomert(customerUpdateRequest, currentUser);
	}	

	@GetMapping("/get")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getCustomer(@CurrentUser UserPrincipal currentUser) {
		return customerService.getCustomer(currentUser);
	}
}
