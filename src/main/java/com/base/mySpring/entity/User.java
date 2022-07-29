package com.base.mySpring.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "이름 입력하자")
    private String name;

    @Column(unique=true)
    @NotBlank(message = "전자우편 입력하자")
    private String email;
}
