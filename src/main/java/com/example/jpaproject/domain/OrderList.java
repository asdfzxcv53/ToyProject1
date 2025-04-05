package com.example.jpaproject.domain;

import javax.persistence.*;

@Entity
@Table(name = "ORDERSLIST")
public class OrderList {

    @Id
    @Column(name = "ORDEREDPRODUCT_ID") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Orders orders;

    @Column(name = "ORDER_AMOUNT")
    private Integer orderAmount;

    @Column(name = "TOTAL_PRICE")
    private Integer totalPrice;

    public OrderList() {}

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOrders (Orders orders) {
        this.orders = orders;
    }

    public void setOrderAmount (Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setTotalPrice (Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public Orders getOrders() {
        return orders;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }
}
