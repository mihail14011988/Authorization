package app.product.demo.Services;

import app.product.demo.Repository.*;
import app.product.demo.Services.Impl.ServiceAuthImpl;
import app.product.demo.Services.DTO.UserRegistrationDTO;
import app.product.demo.Models.*;
import java.util.*;
import java.util.stream.Collectors;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequiredArgsConstructor
@Service
public class ServiceAuth implements ServiceAuthImpl {

    @Autowired
    private RepoAuth repoAuth;
    @Autowired
    private BCryptPasswordEncoder bCript;
    @Autowired
    private ServiceMail serviceMail;

    @Override
    public User save(UserRegistrationDTO registrationDTO) {
        User userDB = repoAuth.findByEmail(registrationDTO.getEmail());
        if (userDB == null) {
            userDB = new User(registrationDTO.getName(), registrationDTO.getSurname(), registrationDTO.getEmail(),
                    bCript.encode(registrationDTO.getPassword()), Arrays.asList(new Role("ROLE_USER")), false);
            repoAuth.save(userDB);
        }
        return userDB;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repoAuth.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("invalid username password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapGrantedAuthoritys(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapGrantedAuthoritys(Collection<Role> mapRoles) {
        return mapRoles.stream().map(role -> new SimpleGrantedAuthority(role.getNameRole())).collect(Collectors.toList());

    }

    @Override
    public boolean saveDTO(UserRegistrationDTO registrationDTO) {
        User user = repoAuth.findByEmail(registrationDTO.getEmail());
        if (user != null) {
            return false;
        }
        if(!registrationDTO.getPassword().equals(registrationDTO.getRepeat())){        
         return false;}
        User user1 = new User(registrationDTO.getName(), registrationDTO.getSurname(), registrationDTO.getEmail(),
                bCript.encode(registrationDTO.getPassword()), Arrays.asList(new Role("ROLE_USER")), false);

        user1.setActivationCode(UUID.randomUUID().toString());

        String message = String.format("Hello!, %s \n"
                + "You activation code: http://localhost:8080/activate/%s",
                user1.getFirstname(),
                user1.getActivationCode());
        serviceMail.sendActivationCode(user1.getEmail(), "Activation code", message);
        repoAuth.save(user1);       
        return true;
    }

    @Override
    public boolean getByEmail(String email, UserRegistrationDTO dto) {
        User user = repoAuth.findByEmail(dto.getEmail());
        if(user==null) {return false;}
        if (dto.getPassword().equals(dto.getRepeat())){
            user.setPassword(bCript.encode(dto.getPassword()));
        repoAuth.save(user);
            return true;}
        
        return false;
    }
;

}
