package com.kokn.paperround.entity;

import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user")
@Setter
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


}
