package br.com.pucgo.mudafacil.controller;

import br.com.pucgo.mudafacil.entity.EmailDTO;
import br.com.pucgo.mudafacil.entity.LoginDTO;
import br.com.pucgo.mudafacil.entity.User;
import br.com.pucgo.mudafacil.entity.UserDTO;
import br.com.pucgo.mudafacil.service.EmailService;
import br.com.pucgo.mudafacil.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        UserDTO userDTO = userService.login(loginDTO.email(), loginDTO.password());
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(new User(userDTO)));
    }

    @PostMapping("/email/password_recover")
    public ResponseEntity<Void> passwordRecovery(@RequestBody @Valid EmailDTO emailDTO) {
        userService.passwordRecovery(emailDTO.email());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable @Valid String id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<UserDTO>> getByName(@PathVariable @Valid String name) {
        return ResponseEntity.ok(userService.getByName(name));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable @Valid String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok(userService.update(new User(userDTO)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
