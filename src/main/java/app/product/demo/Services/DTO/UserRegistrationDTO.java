package app.product.demo.Services.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegistrationDTO {

    private String name;
    private String surname;
    private String email;
    private String password;

}
