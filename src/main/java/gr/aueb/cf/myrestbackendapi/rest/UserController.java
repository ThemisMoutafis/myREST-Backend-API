package gr.aueb.cf.myrestbackendapi.rest;


import gr.aueb.cf.myrestbackendapi.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.myrestbackendapi.core.exceptions.AppObjectInvalidArgumentException;
import gr.aueb.cf.myrestbackendapi.core.exceptions.AppServerException;
import gr.aueb.cf.myrestbackendapi.core.exceptions.ValidationException;
import gr.aueb.cf.myrestbackendapi.dto.UserInsertDTO;
import gr.aueb.cf.myrestbackendapi.dto.UserReadOnlyDTO;
import gr.aueb.cf.myrestbackendapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("users/register")
    public ResponseEntity<UserReadOnlyDTO> saveUser(@RequestBody @Valid UserInsertDTO user, BindingResult bindingResult) throws AppObjectInvalidArgumentException,
            AppObjectAlreadyExists, AppServerException, ValidationException,AppObjectInvalidArgumentException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        try {
            UserReadOnlyDTO savedUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (IOException e) {
            throw new AppServerException("Connection","Could not connect to server");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserReadOnlyDTO>> getAllUsers() {
        List<UserReadOnlyDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
