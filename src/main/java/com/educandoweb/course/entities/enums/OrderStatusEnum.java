package com.educandoweb.course.entities.enums;

import java.util.Objects;

public enum OrderStatusEnum {

    WAITING_PAYMENT(1, "Waiting Payment"),
    PAID(2, "Paid"),
    SHIPPED(3, "Shipped"),
    DELIVERED(4, "Delivered"),
    CANCELED(5, "Canceled");

    private final Integer id;

    private final String desc;

    OrderStatusEnum(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public static OrderStatusEnum valueOf(Integer id) {
        for (OrderStatusEnum value: OrderStatusEnum.values()) {
            if (Objects.equals(value.getId(), id)){
                return value;
            }
        }
        throw new IllegalArgumentException("This code is invalid!");
    }
}
