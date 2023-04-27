package com.jobtang.sourcecompany.api.user.repo;

import com.jobtang.sourcecompany.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
