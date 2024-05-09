package com.educandoweb.course.entities;

import com.educandoweb.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_order_item")
@Data
public class OrderItemEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderEntity order;

    private Integer quantity;

    private Double price;

    public OrderItemEntity() {
    }

    public OrderItemEntity(Long id, OrderEntity order, ProductEntity product, Integer quantity, Double price) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Double getSubTotal() {
        return price * quantity;
    }
}
