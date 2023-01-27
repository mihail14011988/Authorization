package app.product.demo.Services;

import app.product.demo.Models.Product;
import app.product.demo.Repository.RepoProduct;
import app.product.demo.Services.Impl.ServiceProductImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//Сервис сделан с помощью lombok, внедрение конструктора с помощью анатоции @RequiredArgsConstructor
@Service
@RequiredArgsConstructor
public class ServiceProduct implements ServiceProductImpl{
   @Autowired 
   private RepoProduct repo;
    
    @Override
    public List<Product> getByProducts() {
    return repo.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
      return repo.save(product);
    }

    @Override
    public void delete(Long id) {
        for (Product p : getByProducts()){
        if(p.getId().equals(id)) repo.deleteById(id);
        }
    }

 
       
}
