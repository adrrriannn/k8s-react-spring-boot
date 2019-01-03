package com.adrrriannn.store.order.service;

import com.adrrriannn.store.dto.CartDto;
import com.adrrriannn.store.dto.CartItemDto;
import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.dto.UserDto;
import com.adrrriannn.store.order.entity.Cart;
import com.adrrriannn.store.order.entity.CartItem;
import com.adrrriannn.store.order.mapper.CartMapper;
import com.adrrriannn.store.order.repository.CartRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartMapper cartMapper;

    @InjectMocks
    private CartServiceImpl cartService;

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

    private UserDto user = UserDto.builder()
            .id("user_id")
            .email("name@mail.com")
            .name("name")
            .surname("surname")
            .build();

    private CartItemDto cartItemDto1 = CartItemDto.builder()
            .id("id1")
            .product(product1)
            .quantity(1)
            .build();

    private CartItemDto cartItemDto2 = CartItemDto.builder()
            .id("id2")
            .product(product2)
            .quantity(1)
            .build();

    private CartDto cartDto = CartDto.builder()
            .id("id")
            .items(Arrays.asList(cartItemDto1, cartItemDto2))
            .user(user)
            .build();

    private CartItem cartItem1 = CartItem.builder()
            .id("id1")
            .productId(product1.getId())
            .quantity(1)
            .build();

    private CartItem cartItem2 = CartItem.builder()
            .id("id2")
            .productId(product2.getId())
            .quantity(1)
            .build();

    private Cart cart = Cart.builder()
            .id("id")
            .items(Arrays.asList(cartItem1, cartItem2))
            .userId(user.getId())
            .build();


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void updateCartTest() {
        doReturn(cartDto).when(cartMapper).map(cart);
        doReturn(cart).when(cartMapper).map(cartDto);

        doReturn(cart).when(cartRepository).save(cart);

        CartDto result = cartService.updateCart(cartDto);
        assertEquals(cartDto, result);
    }

    @Test
    public void getCartByUserTest() {
        doReturn(cartDto).when(cartMapper).map(cart);
        doReturn(cart).when(cartRepository).findByUserId(anyString());

        CartDto result = cartService.getCartByUser("cartId");
        assertEquals(cartDto, result);
    }

}
