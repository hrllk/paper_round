package com.kokn.paperround.repository;

import com.kokn.paperround.entity.UserKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserKeywordRepository extends JpaRepository<UserKeyword, Long> {

//    User findByEmail(String email);
    List<UserKeyword> findByUserId(Long userId);
//    UserKeyword findByUserIdAndKeyword(Long userId, String keyword);
    Optional<UserKeyword> findByUserIdAndKeyword(Long userId, String keyword);
    void deleteByUserIdAndKeyword(Long userId, String keywrod);
}
