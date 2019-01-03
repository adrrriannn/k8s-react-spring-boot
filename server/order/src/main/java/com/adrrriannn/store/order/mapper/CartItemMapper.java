package com.adrrriannn.store.order.mapper;

import com.adrrriannn.store.dto.CartItemDto;
import com.adrrriannn.store.order.entity.CartItem;
import com.adrrriannn.store.order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    private ProductService productService;

    @Autowired
    public CartItemMapper(ProductService productService) {
        this.productService = productService;
    }
    
    public CartItemDto map(CartItem cartItem) {
        if(cartItem == null) {
            return CartItemDto.builder().build();
        }

        return CartItemDto.builder()
                .id(cartItem.getId())
                .product(productService.getProductById(cartItem.getProductId()))
                .quantity(cartItem.getQuantity())
                .build();
    }

    public CartItem map(CartItemDto cartItem) {
        return CartItem.builder()
                .id(cartItem.getId())
                .productId(cartItem.getProduct().getId())
                .quantity(cartItem.getQuantity())
                .build();
    }
}
