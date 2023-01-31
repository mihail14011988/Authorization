/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.product.demo.Controllers;

import app.product.demo.Services.ServiceAuth;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AuthControlUser {
 @Autowired
    private ServiceAuth authService;
 
 @GetMapping("/login")
 public String login(){
 return "login";
 }
 @GetMapping("/")
public String home() {
return "index";
	}
}
