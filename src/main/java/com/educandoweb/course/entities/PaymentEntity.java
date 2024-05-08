package com.educandoweb.course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_payment")
@Data
public class PaymentEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    @JsonIgnore
    @OneToOne
    @MapsId
    private OrderEntity order;

    public PaymentEntity() {
    }

    public PaymentEntity(Long id, Instant moment, OrderEntity order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
    }
}
