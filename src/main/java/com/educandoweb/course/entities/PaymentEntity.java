package com.educandoweb.course.entities;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class PaymentEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date moment;

    public PaymentEntity() {
    }

    public PaymentEntity(Long id, Date moment) {
        this.id = id;
        this.moment = moment;
    }
}
