package com.adrrriannn.store.order.mapper;

import com.adrrriannn.store.dto.CartDto;
import com.adrrriannn.store.dto.CartItemDto;
import com.adrrriannn.store.dto.OrderDto;
import com.adrrriannn.store.order.entity.Cart;
import com.adrrriannn.store.order.entity.CartItem;
import com.adrrriannn.store.order.entity.Order;
import com.adrrriannn.store.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    private CartItemMapper cartItemMapper;
    private UserService userService;

    @Autowired
    public CartMapper(CartItemMapper cartItemMapper,
                      UserService userService) {
        this.cartItemMapper = cartItemMapper;
        this.userService = userService;
    }
    public CartDto map(Cart cart) {
        if(cart == null) {
            return CartDto.builder().build();
        }

        List<CartItemDto> items = cart.getItems().stream()
                .map(cartItemMapper::map)
                .collect(Collectors.toList());

        return CartDto.builder()
                .id(cart.getId())
                .user(userService.getUser(cart.getUserId()))
                .items(items)
                .build();
    }

    public Cart map(CartDto cart) {

        List<CartItem> items = cart.getItems().stream()
                .map(cartItemMapper::map)
                .collect(Collectors.toList());

        return Cart.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .items(items)
                .build();
    }
}
