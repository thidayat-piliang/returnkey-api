package com.returnkey.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "ORDERS")
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrdersEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "sku")
    private String sku;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "return_status")
    private String returnStatus;
}
