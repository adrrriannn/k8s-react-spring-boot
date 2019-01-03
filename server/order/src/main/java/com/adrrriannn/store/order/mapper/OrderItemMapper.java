package com.adrrriannn.store.order.mapper;

import com.adrrriannn.store.dto.OrderItemDto;
import com.adrrriannn.store.order.entity.OrderItem;
import com.adrrriannn.store.order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    private ProductService productService;

    @Autowired
    public OrderItemMapper(ProductService productService) {
        this.productService = productService;
    }
    
    public OrderItemDto map(OrderItem orderItem) {
        if(orderItem == null) {
            return OrderItemDto.builder().build();
        }

        return OrderItemDto.builder()
                .id(orderItem.getId())
                .product(productService.getProductById(orderItem.getProductId()))
                .quantity(orderItem.getQuantity())
                .build();
    }

    public OrderItem map(OrderItemDto orderItem) {
        return OrderItem.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .build();
    }
}
