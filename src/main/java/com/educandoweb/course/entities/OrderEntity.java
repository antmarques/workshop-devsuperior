package com.educandoweb.course.entities;

import com.educandoweb.course.entities.enums.OrderStatusEnum;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_order")
@Data
public class OrderEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private UserEntity client;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaymentEntity payment;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItemEntity> items = new HashSet<>();

    public OrderEntity(){
    }

    public OrderEntity(Long id, Instant moment, OrderStatusEnum status, UserEntity client) {
        this.id = id;
        this.moment = moment;
        setStatus(status);
        this.client = client;
    }

    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.valueOf(status);
    }

    public void setStatus(OrderStatusEnum status) {
        if (status != null) {
            this.status = status.getId();
        }
    }

    public Double getTotal() {
        Double sum = 0.0;
        for (OrderItemEntity o: items) {
            sum += o.getSubTotal();
        }
        return sum;
    }
}
