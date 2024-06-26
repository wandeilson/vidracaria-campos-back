package com.vidracariaCampos.controller;
import com.vidracariaCampos.model.dto.UserDTO;
import com.vidracariaCampos.service.UserService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid UserDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable UUID id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Object> findEmail(@PathVariable @Email String email) {
        UserDTO user = userService.getUserByEmail(email);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    public ResponseEntity<Object>findAll(){
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody @Valid UserDTO userDTO){
        userService.update(userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
