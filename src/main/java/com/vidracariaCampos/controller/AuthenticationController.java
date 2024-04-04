package com.vidracariaCampos.controller;

import com.vidracariaCampos.model.dto.AuthenticationDTO;
import com.vidracariaCampos.model.entity.User;
import com.vidracariaCampos.repository.UserRepository;
import com.vidracariaCampos.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    private static User userLogged;

    @PostMapping()
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto){
        try{
            var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(),dto.password());
            var auth = authenticationManager.authenticate(userNamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            userLogged = (User) userRepository.findByEmail(dto.email());
            return ResponseEntity.ok(token);
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("Login or Password invalid");
        }
    }


    @PostMapping("/isValidToken")
    public ResponseEntity isValidToken(@RequestParam String token){
        try{
            if(!tokenService.isValidToken(token)) {
                return ResponseEntity.badRequest().body("token invalid");
            }
            else{
                return ResponseEntity.ok().build();
            }
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().body(e);
        }
    }
    @PostMapping("/refreshToken")
    public ResponseEntity tokenRefresh(@RequestParam String token){
        try{
            return ResponseEntity.ok(tokenService.genNewToken(token));
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public static User getUserLogged() {
        return userLogged;
    }

}
