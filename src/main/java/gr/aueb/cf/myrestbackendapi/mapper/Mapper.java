package gr.aueb.cf.myrestbackendapi.mapper;

import gr.aueb.cf.myrestbackendapi.dto.UserInsertDTO;
import gr.aueb.cf.myrestbackendapi.dto.UserReadOnlyDTO;
import gr.aueb.cf.myrestbackendapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

    //todo private final PasswordEncoder passwordEncoder

    // not sure which methods will be required; adding some proactively

    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        var dto  = new UserReadOnlyDTO();
        dto.setUsername(user.getUsername());
        return dto;
    }

    public UserInsertDTO mapToUserInsertDTO(User user) {
        var dto = new UserInsertDTO();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }

    public User mapToUser(UserInsertDTO dto) {
        var user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }
}
