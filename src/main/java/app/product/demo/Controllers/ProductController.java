package app.product.demo.Controllers;

import app.product.demo.Models.Product;
import app.product.demo.Services.ServiceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private ServiceProduct service;
    
    @GetMapping("/")
    public String start(Model model){
         model.addAttribute("products",service.getByProducts());
    return "info";
    }
    @GetMapping("/product/new")
    public String saveS(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
    return "/create_student";
    }
    
    @PostMapping("/product")
    public String addProduct(@ModelAttribute("product") Product product){
        service.saveProduct(product);
    return "redirect:/";
    }
    
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(Model model, Product product){
        model.addAttribute("product", product.getId());
    return "/delete";
    }
    @GetMapping("/product/{id})")
    public String getByIdProd(Model model, Product product){
    model.addAttribute("product", service.saveProduct(product));
    return "redirect:/";
    }
    }
  
   
    

