package com.vidracariaCampos.service;

import com.vidracariaCampos.model.dto.UserDTO;
import com.vidracariaCampos.model.entity.User;
import com.vidracariaCampos.model.enums.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public static User convertToUser(UserDTO registrerDTO) {
        System.out.println(registrerDTO);
        return new User(
                registrerDTO.id(),
                registrerDTO.email(),
                registrerDTO.name(),
                registrerDTO.role().equalsIgnoreCase("ADMIN") == false? Role.DEFAULT: Role.ADMIN,
                registrerDTO.password()
        );
    }

    public static UserDTO convertToUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                null,
                user.getRole().toString()
        );
    }
    public static List<UserDTO> convertToUserDTOList(List<User> users) {
        return users.stream()
                .map(UserConverter::convertToUserDTO)
                .collect(Collectors.toList());
    }
}
