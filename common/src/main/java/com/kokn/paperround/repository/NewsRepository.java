package com.kokn.paperround.repository;

import com.kokn.paperround.entity.Keyword;
import com.kokn.paperround.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

//    Optional<Keyword> findByKeyword(String keyword);
    Optional<Keyword> findByTitle(String title);

}
