package com.shop.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Table(name = "cart_item")
@Setter @Getter
public class CartItem {
    @Id
    @GeneratedValue
    @Column(name="cart_item_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;
    private int count;
}
