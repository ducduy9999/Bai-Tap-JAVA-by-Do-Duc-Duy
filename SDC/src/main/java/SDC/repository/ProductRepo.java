package SDC.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import SDC.dto.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	@Query("SELECT u FROM Product u WHERE u.name LIKE :x")
	Page<Product> searchAll(@Param("x") String s, Pageable pageable);
}
