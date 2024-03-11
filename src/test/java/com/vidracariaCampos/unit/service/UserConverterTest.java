package com.vidracariaCampos.unit.service;

import com.vidracariaCampos.ConfigSpringTest;
import com.vidracariaCampos.model.dto.UserDTO;
import com.vidracariaCampos.model.enums.Role;
import com.vidracariaCampos.model.entity.User;
import com.vidracariaCampos.service.UserConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserConverterTest extends ConfigSpringTest {
    private UserDTO userDTO;
    private User user;

    @BeforeEach
    public void setUp() {
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = new UserDTO(userId, "testUser", "John", "password123","ADMIN");

        user = new User(userId, "testUser", "John", Role.ADMIN, "password123");
    }
    @Test
    public void testConvertToUser() {
        User result = UserConverter.convertToUser(userDTO);
        assertNotNull(result);
        assertEquals(userDTO.id(), result.getId());
        assertEquals(userDTO.email(), result.getEmail());
        assertEquals(userDTO.name(), result.getName());
        assertEquals(userDTO.password(), result.getPassword());
    }

    @Test
    public void testConvertToUserDTO() {
        UserDTO result = UserConverter.convertToUserDTO(user);
        assertNotNull(result);
        assertEquals(user.getId(), result.id());
        assertEquals(user.getEmail(), result.email());
        assertEquals(user.getName(), result.name());
        // Password in UserDTO is expected to be null
        assertEquals(null, result.password());
    }


}

