package com.educandoweb.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_category")
@Data
public class CategoryEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private List<ProductEntity> products = new ArrayList<>();

    public CategoryEntity() {
    }

    public CategoryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
