package com.kokn.paperround.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user")
@Setter
@Getter
@DynamicInsert // dynamic insert + default value
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long userId;

    @Column(unique = true)
    private String email;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private byte isConfirm;

    @Column(columnDefinition = "varchar(256) default 'ROLE_USER'")
    private String authority;

}
