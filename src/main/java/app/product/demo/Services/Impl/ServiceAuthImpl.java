/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.product.demo.Services.Impl;

import app.product.demo.Models.User;
import app.product.demo.Services.DTO.UserRegistrationDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface ServiceAuthImpl extends UserDetailsService {
    User save(UserRegistrationDTO registrationDTO);
    @Override
    UserDetails loadUserByUsername(String username);
    boolean saveDTO(UserRegistrationDTO registrationDTO);
}
