package com.vidracariaCampos.repository;
import com.vidracariaCampos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByEmail(String email);
    User findByNameOrEmail(String name, String email);
    Optional<User> findByname(String name);

}
