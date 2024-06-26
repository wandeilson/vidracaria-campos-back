package com.vidracariaCampos.service;
import com.vidracariaCampos.exception.InternalLogicException;
import com.vidracariaCampos.model.dto.AuthenticationDTO;
import com.vidracariaCampos.model.dto.ResponseToken;
import com.vidracariaCampos.model.entity.User;
import com.vidracariaCampos.repository.UserRepository;
import com.vidracariaCampos.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private TokenService tokenService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public ResponseToken login(AuthenticationDTO authenticationDTO) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());
        try {
            var auth = authenticationManager.authenticate(userNamePassword);
            return tokenService.generateToken((User) auth.getPrincipal());

        }catch (Exception e){
            throw new InternalLogicException(e.getMessage());
        }
    }

    public void validateToken(String token) {
        if(!tokenService.isValidToken(token)) {
            throw new InternalLogicException("token invalid");
        }
    }

    public ResponseToken tokenRefresh(String tokenRefresh) {
        return tokenService.genNewToken(tokenRefresh);
    }
}
