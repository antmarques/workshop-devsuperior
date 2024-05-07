package com.educandoweb.course.entities.pk;

import com.educandoweb.course.entities.OrderEntity;
import com.educandoweb.course.entities.ProductEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class OrderItemPK {

    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity product;

}
