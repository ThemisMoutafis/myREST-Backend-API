package gr.aueb.cf.myrestbackendapi.dto;

import gr.aueb.cf.myrestbackendapi.core.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO {

    @Email(message = "Invalid username")
    private String username;
    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)(?=.*?[@$!%*?&]).{8,}$", message = "Invalid password")
    private String password;


    // always a user and never something else
    private Role role;
}
