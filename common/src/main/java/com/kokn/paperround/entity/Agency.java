package com.kokn.paperround.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="agency")
@DynamicInsert
@Setter
@Getter
public class Agency {
    @Id
    @GeneratedValue
    private Long agencyId;

    @Column
    private String agencyName;

    @Column
    private byte enabled;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
