package com.kokn.paperround.repository;

import com.kokn.paperround.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {

//    Optional<Agency> findByKeyword(String keyword);

}
