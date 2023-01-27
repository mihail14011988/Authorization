/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.product.demo.Services.Impl;

import app.product.demo.Models.User;
import app.product.demo.Services.DTO.UserRegistrationDTO;

/**
 *
 * @author Miha
 */
public interface ServiceAuthImpl {
    User save(UserRegistrationDTO registrationDTO);
}
