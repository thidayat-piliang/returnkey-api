package com.returnkey.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "RETURNS")
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReturnsEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "refund_amount")
    private double refundAmount;

    @Column(name = "status")
    private String status;
}
