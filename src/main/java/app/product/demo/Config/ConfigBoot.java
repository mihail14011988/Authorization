/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.product.demo.Config;

import app.product.demo.Services.Impl.ServiceAuthImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ConfigBoot extends WebSecurityConfigurerAdapter{

    @Autowired
    private ServiceAuthImpl service;
    
    @Bean
    public BCryptPasswordEncoder psv(){
    return new BCryptPasswordEncoder();
    }
    
        
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
    auth.setUserDetailsService(service);
    auth.setPasswordEncoder(psv());
    return auth;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/registration**",
            "/js/**","/css/**","/img/**")
            .permitAll()
            .anyRequest()
            .authenticated().and()
            .formLogin()
            .loginPage("/login")
            .permitAll().and()
            .logout().invalidateHttpSession(true).clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login?logout").permitAll();
            
    }
    
}
