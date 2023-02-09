/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.product.demo.Controllers;

import app.product.demo.Models.User;
import app.product.demo.Repository.RepoAuth;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@AllArgsConstructor
@Controller

public class TestController {
    @Autowired
    private RepoAuth repo;
    
     @GetMapping("/test")
    public String isTestGet(@RequestParam(required = false) String email, Model model){
        User user = repo.findByEmail(email);
        if(user==null){
        model.addAttribute("message", "Такой почты не существует");
        
        }
        else model.addAttribute("message", email);
    return "test";
    }
    
    @PostMapping("/test/home")
    public String isTestPost(String email, Model model){
            model.addAttribute("message", email);
    return "redirect:/test";}
}
