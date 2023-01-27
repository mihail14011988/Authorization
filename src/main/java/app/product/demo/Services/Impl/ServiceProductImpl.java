package app.product.demo.Services.Impl;

import app.product.demo.Models.Product;
import app.product.demo.Repository.RepoProduct;
import java.util.List;

public interface ServiceProductImpl {
    List<Product> getByProducts();
    Product saveProduct(Product product);
    void delete (Long id);
  
}
