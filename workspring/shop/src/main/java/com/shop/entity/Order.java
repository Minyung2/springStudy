package com.shop.entity;

import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Table(name = "orders")
@Getter @Setter
public class Order extends BaseEntity{
    @Id
    @Column(name="order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
    private LocalDateTime orderDate;//주문일자
    @Enumerated
    private OrderStatus orderStatus;//주문상태


    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

}
