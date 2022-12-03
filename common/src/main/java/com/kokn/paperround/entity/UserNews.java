package com.kokn.paperround.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="user_news")
@DynamicInsert
@Setter
@Getter
public class UserNews {
    @Id
    @GeneratedValue
    private Long userNewsId;

    @Column
    private Long userId;

    @Column
    private Long newsId;

//    @Column
//    private String agencyId;
//
//    @Column
//    private String title;
//
//    @Column
//    private String content;
//
//    @Column
//    private String orgLink;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
