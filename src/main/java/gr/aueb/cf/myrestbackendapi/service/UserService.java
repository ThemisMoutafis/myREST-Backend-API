package gr.aueb.cf.myrestbackendapi.service;

import gr.aueb.cf.myrestbackendapi.core.encryption.SecurityConfig;
import gr.aueb.cf.myrestbackendapi.core.enums.Role;
import gr.aueb.cf.myrestbackendapi.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.myrestbackendapi.core.exceptions.AppObjectInvalidArgumentException;
import gr.aueb.cf.myrestbackendapi.dto.UserInsertDTO;
import gr.aueb.cf.myrestbackendapi.dto.UserReadOnlyDTO;
import gr.aueb.cf.myrestbackendapi.mapper.Mapper;
import gr.aueb.cf.myrestbackendapi.model.User;
import gr.aueb.cf.myrestbackendapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final SecurityConfig securityConfig;



    @Transactional(rollbackOn = Exception.class)
    public UserReadOnlyDTO saveUser(UserInsertDTO userInsertDTO)
            throws AppObjectAlreadyExists, AppObjectInvalidArgumentException, IOException {


        if (userRepository.findByUsername(userInsertDTO.getUsername()).isPresent()) {
            throw new AppObjectAlreadyExists("User", "Already Exists : " +userInsertDTO.getUsername());
        }

        String hashedPassword = securityConfig.passwordEncoder().encode(userInsertDTO.getPassword());
        User user = new User();
        user.setUsername(userInsertDTO.getUsername());
        user.setPassword(hashedPassword);
        user.setRole(Role.valueOf("USER")); // probably not needed as default is USER already
        User savedUser = userRepository.save(user);
        return mapper.mapToUserReadOnlyDTO(savedUser);
    }

    public List<UserReadOnlyDTO> getAllUsers() {
        List<UserReadOnlyDTO> usersDTO = new ArrayList<>();

        List<User> users = userRepository.findAll();
        for (User user : users) {
            UserReadOnlyDTO dto = mapper.mapToUserReadOnlyDTO(user);
            usersDTO.add(dto);
        }
        return usersDTO;
    }
}
