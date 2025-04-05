package com.example.jpaproject.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Orders {

    @Id
    @Column(name = "ORDERS_ID") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORDERS_PRICE")
    private Integer totalPrice;

    @OneToMany(mappedBy = "orders")
    private List<OrderList> orderList;

    public Orders() {}

    public Orders(List<OrderedProduct> orderedProducts) {
        this.totalPrice = calculateTotalPrice(orderedProducts);
    }

    private Integer calculateTotalPrice(List<OrderedProduct> orderedProducts) {
        return orderedProducts
                .stream()
                .mapToInt(orderedProduct -> orderedProduct.getPrice() * orderedProduct.getAmount())
                .sum();
    }

    public Long getId() {
        return id;
    }

    public List<OrderList> getOrderList() {
        return orderList;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }
}
