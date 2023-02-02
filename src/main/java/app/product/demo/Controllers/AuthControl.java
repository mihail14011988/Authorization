package app.product.demo.Controllers;

import app.product.demo.Services.ServiceAuth;
import app.product.demo.Services.DTO.UserRegistrationDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/registration") //вход регистрации
public class AuthControl {

    @Autowired
    private final ServiceAuth authService;

// Представление формы на клиента, использование bootstrap
    @GetMapping
    public String showFormsRegistration() {
        return "registration";
    }

// Создаётся новый userDto с формы;
    @ModelAttribute("userLogin")
    public UserRegistrationDTO userDto() {
        return new UserRegistrationDTO();
    }

//Передача с формы данных успешной регистрации Entity(column= "userLogin")  
    @PostMapping
    public String registrAccount(@ModelAttribute("userLogin") UserRegistrationDTO userDTO) {
        if (authService.saveDTO(userDTO)) {
            return "redirect:/registration?success";
        }

        return "redirect:/registration?unsuccess";
    }

    

}
