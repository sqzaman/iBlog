package iblog.user.payload;

import javax.validation.constraints.*;

import org.springframework.core.annotation.Order;


public class UserUpdateRequest {
	
    @NotBlank(message = "Firstname cannot be empty!")
    @Size(min = 3, max = 40)
    @Order(1)
    private String firstname;
    
    @NotBlank(message = "Lastname cannot be empty!")
    @Size(min = 4, max = 40)
    @Order(2)
    private String lastname;


	

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


}
