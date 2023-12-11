package com.vidracariaCampos.controller;

import com.vidracariaCampos.dto.AuthenticationDTO;
import com.vidracariaCampos.entity.User;
import com.vidracariaCampos.securitService.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto){
        try{
            var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(),dto.password());
            var auth = authenticationManager.authenticate(userNamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok("{\"token\":\"" + token + "\"}");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    @PostMapping("/is-valid-token/{token}")
    public ResponseEntity isValidToken(@PathVariable String token){
        try{
            if(tokenService.isValidToken(token) == false) {
                return ResponseEntity.badRequest().body("token invalid");
            }
            else{
                return ResponseEntity.ok().build();
            }
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

}
