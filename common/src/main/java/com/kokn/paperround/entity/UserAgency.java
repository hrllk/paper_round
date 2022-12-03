package com.kokn.paperround.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="user_agency")
@DynamicInsert
@Setter
@Getter
public class UserAgency {
    @Id
    @GeneratedValue
    private Long userAgencyId;
    @Column
    private Long agencyId;

    @Column
    private Long userId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
