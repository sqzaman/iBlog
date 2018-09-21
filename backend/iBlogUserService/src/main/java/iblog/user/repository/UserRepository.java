package iblog.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import iblog.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Boolean existsByEmail(String email);
	
	User findByEmail(String email);


}
