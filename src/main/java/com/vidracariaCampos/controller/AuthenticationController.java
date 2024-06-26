package com.vidracariaCampos.controller;
import com.vidracariaCampos.model.dto.AuthenticationDTO;
import com.vidracariaCampos.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        log.info("Login attempt for user: {}", authenticationDTO.email());
        return ResponseEntity.ok(authenticationService.login(authenticationDTO));
    }

    @PostMapping("/isValidToken")
    public ResponseEntity<Object> isValidToken(@RequestParam String token){
        log.info("Validating token: {}", token);
        authenticationService.validateToken(token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<Object> tokenRefresh(@RequestParam String token){
        log.info("Refreshing token for token: {}", token);
        return ResponseEntity.ok(authenticationService.tokenRefresh(token));
    }
}
