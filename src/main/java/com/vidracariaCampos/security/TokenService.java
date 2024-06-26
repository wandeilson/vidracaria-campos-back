package com.vidracariaCampos.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vidracariaCampos.exception.InternalErrorException;
import com.vidracariaCampos.exception.InternalLogicException;
import com.vidracariaCampos.exception.NotAuthorizeException;
import com.vidracariaCampos.model.dto.ResponseToken;
import com.vidracariaCampos.model.entity.User;
import com.vidracariaCampos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    @Autowired
    private UserRepository userRepository;


    public ResponseToken generateToken(User user){
        try {
            System.out.println(user);
            Algorithm algorithm =  Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirationToken())
                    .sign(algorithm);

            return new ResponseToken(token, genTokenRefresh(user));

        }catch (JWTCreationException e){
            throw new InternalErrorException("Erro generating token: " + e.getMessage());
        }
    }
    private Instant genExpirationToken(){
        return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm =  Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e){
            return "";
        }
    }
    public boolean isValidToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var teste = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token);
            System.out.println(teste);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
    public String genTokenRefresh(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            CryptService c = new CryptService();

            return JWT.create()
                    .withIssuer("auth-api")
                    .withClaim("user", user.getName())
                    .withClaim("codeAccess", CodeAccess.genCode(user.getId()).toString())
                    .withExpiresAt(genExpirationTokenForRefresh())
                    .withClaim("isRefreshToken", true)
                    .sign(algorithm);

        } catch (Exception e) {
            throw new InternalErrorException("Error generating refresh token: " + e.getMessage());
        }
    }

    public ResponseToken genNewToken(String refreshToken){
        try {
            isValidRefreshToken(refreshToken);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .withClaim("isRefreshToken", true)
                    .build()
                    .verify(refreshToken);

            String userName =  decodedJWT.getClaim("user").asString();
            UUID id =  CodeAccess.reverseCode(UUID.fromString(decodedJWT.getClaim("codeAccess").asString()));
            System.out.println(id);
            User user = userRepository.findById(id).orElseThrow();
            System.out.println(user);
            if(!user.getName().equals(userName)){
                throw new NotAuthorizeException("access denied");
            }

            return generateToken(user);
        } catch (Exception e) {
            throw new InternalLogicException("Error generating new token: " + e.getMessage());
        }
    }
    private Instant genExpirationTokenForRefresh(){
        return LocalDateTime.now().plusDays(15).toInstant(ZoneOffset.of("-03:00"));
    }
    public void isValidRefreshToken(String refreshToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .withClaim("isRefreshToken", true)
                    .build()
                    .verify(refreshToken);

        } catch (JWTVerificationException e) {
            throw new InternalLogicException("Token Refresh invalid");
        }
    }
}
