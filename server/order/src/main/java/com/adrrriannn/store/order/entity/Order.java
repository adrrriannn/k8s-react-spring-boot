package com.adrrriannn.store.order.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column
    private String id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private List<OrderItem> items = new ArrayList<>();

    @Column
    private String userId;

    @PrePersist
    private void setId() {
        id = UUID.randomUUID().toString();
    }
}
