
package app.product.demo.Services;

import app.product.demo.Models.User;
import app.product.demo.Repository.RepoAuth;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceActivationAccount {
   @Autowired
    private ServiceMail serviceMail;
   
   @Autowired
    private RepoAuth repo;
    
  
   public boolean getActivation(String activ){
   List<User> users=repo.findAll();
   for(User r : users){
   if(r.getActivationCode().equals(activ)){
        r.setActiveCode(true);
        repo.save(r); 
   }
  }     
   return true;      
  }
 
   
  public User getUserEmailSetActivation(String email){
 User user=null;    
 List<User> users=repo.findAll();
  for(User r : users){if
    (r.getEmail().equals(email)){
      r.setActivationCode(UUID.randomUUID().toString());
       String message=String.format("Hello!, %s \n"
                + "restored password: http://localhost:8080/formRestorePass/%s",
                r.getFirstname(),
                r.getActivationCode());
        serviceMail.sendActivationCode(r.getEmail(), "Activation code", message);
       user=r;
       repo.save(user);        
       }
  } return user;
 }
  
  
  public User changePass(String code){
   User user=null;    
     List<User> users=repo.findAll();
  for(User r : users){if
    (r.getActivationCode().equals(code))
      user=r;
  } return user; 
 } 



}