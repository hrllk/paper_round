package com.kokn.paperround.repository;

import com.kokn.paperround.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    String findEmailByEmail(String email);
    User findByEmail(String email);
}
