package app.product.demo.Config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class ConfigMail {
  @Value("${spring.mail.host}")
  private String host;
  @Value("${spring.mail.username}")
  private String username;
  @Value("${spring.mail.password}")
  private String password;
  @Value("${spring.mail.port}")
  private int port;
  @Value("${spring.mail.protocol}")
  private String protocol;
//  @Value("${spring.mail.debug}")
//  private String debug;
  @Bean
  public JavaMailSender getMailSender(){
       JavaMailSenderImpl impl=new JavaMailSenderImpl();
       impl.setHost(host);
       impl.setPort(port);
       impl.setUsername(username);
       impl.setPassword(password);
       
       Properties properties= impl.getJavaMailProperties();
       properties.setProperty("mail.transport.protocol", protocol);
//       properties.setProperty("mail.debug", debug);
       
       
       return impl;
  }
   
    
}
