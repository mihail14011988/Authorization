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
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;



@Service
public class ServiceAuth implements ServiceAuthImpl{
    
       private RepoAuth repoAuth;
   @Autowired
    private BCryptPasswordEncoder bCript;

   public ServiceAuth(RepoAuth repoAuth){
       super();
       this.repoAuth=repoAuth;
   
   }
   
       
    @Override
    public User save(UserRegistrationDTO registrationDTO) {
    
      
    User user=new User(registrationDTO.getName(), registrationDTO.getSurname(), registrationDTO.getEmail(),
            bCript.encode(registrationDTO.getPassword()),Arrays.asList(new Role("ROLE_USER")));
    User userDB=repoAuth.findByEmail(registrationDTO.getEmail());
    if(userDB!=null) return userDB;
    return repoAuth.save(user);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repoAuth.findByEmail(username);
    if(user==null){
    throw new UsernameNotFoundException("invalid username password");
    } return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapGrantedAuthoritys(user.getRoles()));
    }
    
    private Collection<? extends GrantedAuthority> mapGrantedAuthoritys(Collection<Role> mapRoles){
   return mapRoles.stream().map(role-> new SimpleGrantedAuthority(role.getNameRole())).collect(Collectors.toList());
        
        
     
    }

   
    
}
