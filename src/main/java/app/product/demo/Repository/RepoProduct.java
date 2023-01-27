package app.product.demo.Repository;

import app.product.demo.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoProduct extends JpaRepository<Product, Long>{
    
}
