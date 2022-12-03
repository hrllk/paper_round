package com.kokn.paperround.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="news")
@DynamicInsert
@Setter
@Getter
public class News {
    @Id
    @GeneratedValue
    private Long newsId;

    @Column
    private Long keywordId;

    @Column
    private Long agencyId;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String orgLink;

    @Column
    private byte isProvide;

//    @CreatedDate
    @Column
    private String publishedAt;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
