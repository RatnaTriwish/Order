package com.ratna.Order.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@NamedQuery(name = "OrderModel.findAll", query = "SELECT a FROM OrderModel a")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_number")
    private Long id;

    @Column(name = "order_amount", nullable = false)
    private Long orderAmount;

    @Column(name = "advance_amount", nullable = false)
    private Long advanceAmount;

    @Column(name = "cust_code", nullable = false)
    private String customerCode;

    @Column(name = "ord_description", nullable = false)
    private String orderDescription;

    @Column(name = "agent_code", nullable = false)
    private String agentCode;

    @Column(name = "ord_date", nullable = true)
    private Timestamp date;

}


