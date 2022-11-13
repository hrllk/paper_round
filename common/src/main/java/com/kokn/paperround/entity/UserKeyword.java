package com.kokn.paperround.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="user_keyword")
@Setter
@Getter
public class UserKeyword implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY == 기본키생성을 DB에 위임함.
    private Long userKeywordId;

    @Column(unique = true)
    private Long keywordId; // keyword 마스터테이블에서 관리되는 키값

    @Column
    private Long userId;

    @Column
    private String keyword;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
