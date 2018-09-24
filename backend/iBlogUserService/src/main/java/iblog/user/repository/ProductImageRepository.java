package iblog.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import iblog.user.domain.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	 //Boolean existsByName(String name);
}