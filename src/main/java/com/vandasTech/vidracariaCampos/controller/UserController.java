package com.vandasTech.vidracariaCampos.controller;

import com.vandasTech.vidracariaCampos.dto.UserDTO;
import com.vandasTech.vidracariaCampos.entity.User;
import com.vandasTech.vidracariaCampos.service.UserService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody UserDTO userDTO) {
        if (userService.loadUserByUsername(userDTO.email()) != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        userService.saveUser(userDTO);
        return ResponseEntity.created(null).build();
    }


    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> find(@PathVariable @Email String email) {
            UserDTO user = userService.getUserByEmail(email);
            if(user == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(user);

    }


    @GetMapping("/all")
    public ResponseEntity< List<UserDTO>>findAll(){
        try{
            List<UserDTO> users = userService.getAllUsers();
            return ResponseEntity.ok().body(users);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody @Valid UserDTO userDTO){
        if(userService.getUserByEmail(userDTO.email()) == null){
            return ResponseEntity.notFound().build();
        }
        try {
            userService.update(userDTO);
            return ResponseEntity.ok().build();

        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity delete(@PathVariable String email){

        try{
            userService.deleteUser(email);
            return ResponseEntity.ok().build();

        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
