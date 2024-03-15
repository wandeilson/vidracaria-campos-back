package com.vidracariaCampos.unit.model;
import com.vidracariaCampos.model.entity.User;
import com.vidracariaCampos.model.enums.Role;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    void testUserBuilder() {
        UUID id = UUID.randomUUID();
        String email = "test@example.com";
        String name = "Test User";
        Role role = Role.ADMIN;
        String password = "password123";

        User user = User.builder()
                .id(id)
                .email(email)
                .name(name)
                .role(role)
                .password(password)
                .build();

        assertEquals(id, user.getId());
        assertEquals(email, user.getEmail());
        assertEquals(name, user.getName());
        assertEquals(role, user.getRole());
        assertEquals(password, user.getPassword());
    }

    @Test
    void testUserAuthorities() {
        User adminUser = User.builder()
                .email("admin@example.com")
                .name("Admin")
                .role(Role.ADMIN)
                .password("admin123")
                .build();

        assertEquals(2, adminUser.getAuthorities().size());
        assertTrue(adminUser.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN")));
        assertTrue(adminUser.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("DEFAULT")));

        User defaultUser = User.builder()
                .email("user@example.com")
                .name("User")
                .role(Role.DEFAULT)
                .password("user123")
                .build();

        assertEquals(1, defaultUser.getAuthorities().size());
        assertTrue(defaultUser.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("DEFAULT")));
    }

    @Test
    void testUserAccountStatus() {
        // Test isEnabled method
        User enabledUser = User.builder()
                .email("enabled@example.com")
                .name("Enabled User")
                .role(Role.DEFAULT)
                .password("enabled123")
                .build();

        assertTrue(enabledUser.isEnabled());
        assertTrue(enabledUser.isAccountNonExpired());
        assertTrue(enabledUser.isAccountNonLocked());
        assertTrue(enabledUser.isCredentialsNonExpired());
    }

}
