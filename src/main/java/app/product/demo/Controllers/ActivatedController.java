
package app.product.demo.Controllers;

import app.product.demo.Models.User;
import app.product.demo.Repository.RepoAuth;
import app.product.demo.Services.DTO.UserRegistrationDTO;
import app.product.demo.Services.ServiceActivationAccount;
import app.product.demo.Services.ServiceAuth;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor

public class ActivatedController {

    @Autowired
    ServiceActivationAccount activService;
    
    @Autowired
    ServiceAuth authDao;
    
    
   
    @GetMapping("/activate/{code}")
    public String isActiv(@PathVariable String code, Model model) {
        boolean isActiv = activService.getActivation(code);
        if (!isActiv) {
            return "exception";
        }
        model.addAttribute("message","");
        return "login";

    }
    
    
    @GetMapping("/restorePassword")
    public String restorePass() {      
   
        
        return "restorePassword";
   }

    @PostMapping("/restorePassword")
    public String restorePass(@RequestParam(name="email", required = false) String email,  User user) {
        user=activService.getUserEmailSetActivation(email);
        if(user==null){ 
        return "redirect:/restorePassword?error";                       
                } else{
      
        return "redirect:/restorePassword?success";
        }        
   }

    
//    @GetMapping("/restorePassword")
//   public String sendPass(@PathVariable String email,Model model) {
//       model.addAttribute(email, "email");
//       boolean isRestorePostMail=activService.getEmailForRestore(email);
//       if(!isRestorePostMail){return "redirect:/exception";}
//       
//      
//      return "redirect:/restorePassword";
//    }
//    
    
     @GetMapping("/formRestorePass/{code}")
    public String isRestorePass(@PathVariable String code, User user, Model model) {
         user=activService.changePass(code);
        if(user!=null){
            model.addAttribute("message", user.getEmail());
            model.addAttribute("code", user.getActivationCode());
            
        return "formRestorePass";}
      
        return "exception";}
        
   
 

    
 @PostMapping("/formRestorePass")
public String changePostPass(@ModelAttribute("userLog") UserRegistrationDTO userdto, String email, Model model)
        {
   boolean pos =authDao.getByEmail(email, userdto);
   if(pos){model.addAttribute("ok", "");
       return "formRestorePass";    
       }
  
return "exception"; 
   } 
    
}