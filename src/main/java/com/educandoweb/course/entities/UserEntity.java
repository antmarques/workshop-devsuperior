package com.educandoweb.course.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_user")
@Data
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String password;

    public UserEntity(){
    }

    public UserEntity(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

}
