package com.jobtang.sourcecompany.api.user.repository;

import com.jobtang.sourcecompany.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByNickname(String nickname);

//    Optional<User> findById(Long userId);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByNickname(String nickname);

}
