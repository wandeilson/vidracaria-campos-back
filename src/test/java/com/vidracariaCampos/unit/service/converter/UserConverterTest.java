package com.vidracariaCampos.unit.service.converter;
import com.vidracariaCampos.config.ConfigSpringTest;
import com.vidracariaCampos.model.dto.UserDTO;
import com.vidracariaCampos.model.entity.User;
import com.vidracariaCampos.model.enums.Role;
import com.vidracariaCampos.service.converter.UserConverter;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserConverterTest implements ConfigSpringTest {

    @Test
    void testConvertToUser() {
        UserDTO userDTO = new UserDTO(UUID.randomUUID(), "test@example.com", "Test User", "password", "ADMIN");
        User user = UserConverter.convertToUser(userDTO);

        assertNotNull(user);
        assertEquals(userDTO.id(), user.getId());
        assertEquals(userDTO.email(), user.getEmail());
        assertEquals(userDTO.name(), user.getName());
        assertEquals(userDTO.role(), user.getRole().toString());
        assertEquals(userDTO.password(), user.getPassword());
    }

    @Test
    void testConvertToUserDTO() {
        User user = new User(UUID.randomUUID(), "test@example.com", "Test User", Role.ADMIN, "password");
        UserDTO userDTO = UserConverter.convertToUserDTO(user);

        assertNotNull(userDTO);
        assertEquals(user.getId(), userDTO.id());
        assertEquals(user.getEmail(), userDTO.email());
        assertEquals(user.getName(), userDTO.name());
        assertEquals(user.getRole().toString(), userDTO.role());
    }
}
