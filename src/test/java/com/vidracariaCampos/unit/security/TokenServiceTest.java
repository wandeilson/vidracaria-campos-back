package com.vidracariaCampos.unit.security;
import com.vidracariaCampos.config.ConfigSpringTest;
import com.vidracariaCampos.model.entity.User;
import com.vidracariaCampos.repository.UserRepository;
import com.vidracariaCampos.security.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
public class TokenServiceTest implements ConfigSpringTest {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    void testGenerateToken() {
        User user = createUser("test@example.com", "password");
        String token = tokenService.generateToken(user);

        assertNotNull(token);
        assertTrue(token.contains("token"));
        assertTrue(token.contains("tokenRefresh"));
    }

    @Test
    @Transactional
    void testValidateToken() {
        User user = createUser("test@example.com", "password");
        String token = tokenService.generateToken(user);
        assertEquals("", tokenService.validateToken(token));
    }

    @Test
    @Transactional
    void testIsValidToken() {
        User user = createUser("test@example.com", "password");
        String tokenResponse = tokenService.generateToken(user);

        int startIndex = tokenResponse.indexOf("\"token\":") + 9;
        int endIndex = tokenResponse.indexOf("\",\"tokenRefresh\"");
        String tokenWithoutRefresh = tokenResponse.substring(startIndex, endIndex);

        assertNotNull(tokenWithoutRefresh);
        assertTrue(tokenService.isValidToken(tokenWithoutRefresh));
    }

    @Test
    @Transactional
    void testGenNewToken() {
        User user = createUser("test@example.com", "password");
        String tokenResponse = tokenService.generateToken(user);

        String tokenDelimiter = "\",\"tokenRefresh\":\"";
        int tokenDelimiterIndex = tokenResponse.indexOf(tokenDelimiter);
        String token = tokenResponse.substring(10, tokenDelimiterIndex);
        String refreshToken = tokenResponse.substring(tokenDelimiterIndex + tokenDelimiter.length(), tokenResponse.length() - 2); // Ignorando as últimas 2 caracteres ("}")

        assertNotNull(refreshToken);
    }

    @Test
    @Transactional
    void testGenerateToken_UserNotFound() {
        User user = null;
        assertThrows(RuntimeException.class, () -> tokenService.generateToken(user));
    }

    @Test
    void testIsValidToken_InvalidToken() {
        String invalidToken = "invalid-token";
        assertFalse(tokenService.isValidToken(invalidToken));
    }

    @Test
    void testGenNewToken_InvalidToken() {
        String invalidToken = "invalid-token";
        assertThrows(RuntimeException.class, () -> tokenService.genNewToken(invalidToken));
    }

    @Test
    void testGenNewToken_UserNotFound() {
        String tokenResponse = "valid-token-response";
        assertThrows(RuntimeException.class, () -> tokenService.genNewToken(tokenResponse));
    }


    private User createUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }
}
