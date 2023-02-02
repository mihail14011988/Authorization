package app.product.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ServiceMail {
   
    @Autowired
    private JavaMailSender javaMailSender;
   @Value("${spring.mail.username}")
  private String username; 
    
    public void sendActivationCode(String emailTo, String body, String message){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(body);
        mailMessage.setText(message);
        
      javaMailSender.send(mailMessage);
    }
}
