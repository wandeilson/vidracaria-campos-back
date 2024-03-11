package com.vidracariaCampos.controller;

import com.vidracariaCampos.model.dto.UserDTO;
import com.vidracariaCampos.service.UserService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid UserDTO userDTO) {
        try {

            userService.saveUser(userDTO);
            return ResponseEntity.created(null).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable UUID id) {
        try {
            UserDTO user = userService.getUserById(id);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/email/{email}")
    public ResponseEntity findEmail(@PathVariable @Email String email) {
        try {
            UserDTO user = userService.getUserByEmail(email);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity< List<UserDTO>>findAll(){
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody @Valid UserDTO userDTO){
        try {
            userService.update(userDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.ok().build();

        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
