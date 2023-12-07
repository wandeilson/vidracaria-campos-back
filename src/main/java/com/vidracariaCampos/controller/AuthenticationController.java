package com.vidracariaCampos.controller;

import com.vidracariaCampos.dto.AuthenticationDTO;
import com.vidracariaCampos.entity.User;
import com.vidracariaCampos.securitService.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto){
        try{
            var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(),dto.password());
            var auth = this.authenticationManager.authenticate(userNamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok("{\"token\":\"" + token + "\"}");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Email or Password invalid");
        }
    }

}
