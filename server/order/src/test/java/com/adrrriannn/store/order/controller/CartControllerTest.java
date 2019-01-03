package com.adrrriannn.store.order.controller;

import com.adrrriannn.store.dto.CartDto;
import com.adrrriannn.store.dto.CartItemDto;
import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.dto.UserDto;
import com.adrrriannn.store.order.service.CartService;
import com.adrrriannn.store.order.validator.CartValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {

    @Mock
    private CartService cartService;

    @Mock
    private CartValidator cartValidator;

    private CartController cartController;


    private ProductDto product1 = ProductDto.builder()
            .id("id_product1")
            .name("Product1")
            .description("Description")
            .build();

    private ProductDto product2 = ProductDto.builder()
            .id("id_product2")
            .name("Product2")
            .description("Description")
            .build();

    private CartItemDto cartItem1 = CartItemDto.builder()
            .id("id1")
            .product(product1)
            .quantity(1)
            .build();

    private CartItemDto cartItem2 = CartItemDto.builder()
            .id("id2")
            .product(product2)
            .quantity(1)
            .build();

    private UserDto user = UserDto.builder()
            .id("user_id")
            .email("name@mail.com")
            .name("name")
            .surname("surname")
            .build();

    private CartDto cart = CartDto.builder()
            .id("id")
            .items(Arrays.asList(cartItem1, cartItem2))
            .user(user)
            .build();

    @Before
    public void setUp() {
        cartController = new CartController(cartService, cartValidator);
    }

    @Test
    public void updateCartTest() throws Exception{
        doReturn(cart).when(cartService).updateCart(any());

        CartDto response = cartController.updateCart(cart);

        assertEquals(response, cart);
    }

    @Test
    public void getCartTest() {
        doReturn(cart).when(cartService).getCartByUser(any());

        CartDto response = cartController.getCart();

        assertEquals(response, cart);
    }

}
