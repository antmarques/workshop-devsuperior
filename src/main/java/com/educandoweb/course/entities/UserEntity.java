package com.educandoweb.course.entities;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String email;

    private String telefone;

    private String senha;

    public UserEntity(){
    }

    public UserEntity(Long id, String name, String email, String telefone, String senha) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

}
