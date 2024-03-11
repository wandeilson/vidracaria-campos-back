package com.vidracariaCampos.unit.security;
import com.vidracariaCampos.ConfigSpringTest;
import com.vidracariaCampos.security.CryptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CryptUserServiceTest extends ConfigSpringTest {

    @Autowired
    private CryptService cryptUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testEncodePassword() {
        String password = "myPassword";
        String encodedPassword = cryptUserService.encodePassword(password);

        assertTrue(passwordEncoder.matches(password, encodedPassword));
    }

    @Test
    void testMatchesPassword() {
        String password = "myPassword";
        String encodedPassword = passwordEncoder.encode(password);

        assertTrue(cryptUserService.matchesPassword(password, encodedPassword));
    }
}