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
@Table(name = "tb_product")
@Data
public class ProductEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private String imgUrl;

    @ManyToMany
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "id_product"), inverseJoinColumns = @JoinColumn(name = "id_category"))
    private List<CategoryEntity> categories = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrderItemEntity> items = new ArrayList<>();

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    @JsonIgnore
    public Set<OrderEntity> getOrders() {
        Set<OrderEntity> set = new HashSet<>();
        for (OrderItemEntity oie: items){
            set.add(oie.getOrder());
        }
        return set;
    }
}
