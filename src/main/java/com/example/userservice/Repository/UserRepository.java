package com.example.userservice.Repository;

import com.example.userservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository <User, Long> {

    public Optional<User> findByEmailId(String email);

    public User save(User user);
}
