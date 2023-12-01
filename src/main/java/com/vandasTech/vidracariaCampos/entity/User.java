package com.vandasTech.vidracariaCampos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name="users")
public class User implements UserDetails {

    @Id
    @NotBlank
    @Email(message = "Invalid email")
    private String email;

    public User(String email, String name, Role role, String password) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.password = password;
    }

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role == Role.ADMIN ?
                List.of(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("DEFAULT"))
                : List.of(new SimpleGrantedAuthority("DEFAULT"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
