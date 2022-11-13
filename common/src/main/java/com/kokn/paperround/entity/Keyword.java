package com.kokn.paperround.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="keyword")
@DynamicInsert
@Setter
@Getter
public class Keyword implements Serializable {

    private static final long serialVersionUID = 1L;
//    @PrePersist
//    public void prePersist() {
//        this.useCnt = this.useCnt == null ? 0 : this.useCnt;
//    }

    @Id
    @GeneratedValue
    private Long keywordId;

    @Column
    private String keyword;

    @Column
    @ColumnDefault("1")
    private Integer useCnt;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
