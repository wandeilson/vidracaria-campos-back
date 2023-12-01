package com.vidracariaCampos.service;

import com.vidracariaCampos.dto.UserDTO;
import com.vidracariaCampos.entity.User;
import com.vidracariaCampos.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConverter {

    public static User convertToUser(UserDTO registrerDTO) {
        System.out.println(registrerDTO);
        return new User(
                registrerDTO.email(),
                registrerDTO.name(),
                registrerDTO.role().equalsIgnoreCase("ADMIN") == false? Role.DEFAULT: Role.ADMIN,
                registrerDTO.password()
        );
    }

    public static UserDTO convertToUserDTO(User user) {
        return new UserDTO(
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
