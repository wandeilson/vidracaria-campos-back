package com.vidracariaCampos.service;

import com.vidracariaCampos.model.dto.UserDTO;
import com.vidracariaCampos.model.enums.Role;
import com.vidracariaCampos.model.entity.User;
import com.vidracariaCampos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByEmail(email);
        return user != null ? user: null;
    }

    public UserDTO getUserByEmail(String email) {
        User u = (User) userRepository.findByEmail(email);
        if(u == null){
            throw new RuntimeException("User not found");
        }
        return UserConverter.convertToUserDTO(u);
    }
    public UserDTO getUserById(UUID id) {
        User u =  userRepository.findById(id).get();

        if(u == null){
            throw new RuntimeException("User not found");
        }
        return  UserConverter.convertToUserDTO(u);
    }

    public List<UserDTO> getAllUsers() {
        List<User> u = userRepository.findAll();
        return u != null ? UserConverter.convertToUserDTOList(u): null;
    }

    public void saveUser(UserDTO userDTO) {
       if(userRepository.findByEmail(userDTO.email()) != null){
           throw new RuntimeException("User already exists");
       }

        User newUser = UserConverter.convertToUser(userDTO);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
    }

    public void deleteUser(UUID id) {
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteById(id);
    }

    public void update(UserDTO userDTO) {
        User user =  userRepository.findById(userDTO.id()).get();
        if(user == null){
            throw new RuntimeException("User not found");
        }
        if (userDTO.name() != null) { user.setName(userDTO.name());}
        if (userDTO.email() != null && !user.getEmail().equals(userDTO.email())) {
            if(userRepository.findByEmail(userDTO.email()) == null){
                user.setEmail(userDTO.email());
            }else {
                throw new RuntimeException("Email already found");
            }
        }
        if (userDTO.password() != null) {user.setPassword(passwordEncoder.encode(userDTO.password()));}
        if (userDTO.role() != null) {user.setRole(Role.valueOf(userDTO.role()));}
        userRepository.save(user);
    }
}
