package com.vandasTech.vidracariaCampos.controller;

import com.vandasTech.vidracariaCampos.dto.AuthenticationDTO;
import com.vandasTech.vidracariaCampos.dto.UserDTO;
import com.vandasTech.vidracariaCampos.entity.User;
import com.vandasTech.vidracariaCampos.securitService.TokenService;
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
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(),dto.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(token);
    }

}
