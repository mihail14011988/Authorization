/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.product.demo.Services;

import app.product.demo.Models.Role;
import app.product.demo.Models.User;
import app.product.demo.Repository.RepoAuth;
import app.product.demo.Services.DTO.UserRegistrationDTO;
import app.product.demo.Services.Impl.ServiceAuthImpl;
import java.util.Arrays;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;


//без lombok
@Service
public class ServiceAuth implements ServiceAuthImpl{
    private RepoAuth repoAuth;

    public ServiceAuth(RepoAuth repoAuth) {
        this.repoAuth=repoAuth;
    }

       
    @Override
    public User save(UserRegistrationDTO registrationDTO) {
    User user=new User(registrationDTO.getName(), registrationDTO.getSurname(), registrationDTO.getEmail(), registrationDTO.getPassword(),Arrays.asList(new Role("ROLE_USER")));
    return repoAuth.save(user);
    }
    
}
