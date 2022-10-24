package com.kokn.paperround.repository;

import com.kokn.paperround.entity.User;
import com.kokn.paperround.entity.UserKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserKeywordRepository extends JpaRepository<UserKeyword, Long> {

//    User findByEmail(String email);
    List<UserKeyword> findByUserId(Long userId);
}
