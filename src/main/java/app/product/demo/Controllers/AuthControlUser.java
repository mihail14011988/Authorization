package app.product.demo.Controllers;

import app.product.demo.Services.ServiceAuth;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AuthControlUser {

    @Autowired
    private ServiceAuth authService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
    //Потверждение учетной записи
    @GetMapping("/activate/{code}")
    public String getCodeActivate(Model model, @PathVariable String code){
    return "/login?activated";
        }
}
