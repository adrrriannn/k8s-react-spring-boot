package com.adrrriannn.store.order.controller;

import com.adrrriannn.store.dto.OrderDto;
import com.adrrriannn.store.dto.OrderItemDto;
import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.dto.UserDto;
import com.adrrriannn.store.order.service.OrderService;
import com.adrrriannn.store.order.validator.OrderValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private OrderValidator orderValidator;

    private OrderController orderController;

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

    private OrderItemDto orderItemDto1 = OrderItemDto.builder()
            .id("id1")
            .product(product1)
            .quantity(1)
            .build();

    private OrderItemDto orderItemDto2 = OrderItemDto.builder()
            .id("id2")
            .product(product2)
            .quantity(1)
            .build();

    private OrderDto order = OrderDto.builder()
            .id("id")
            .items(Arrays.asList(orderItemDto1, orderItemDto2))
            .user(user)
            .build();

    @Before
    public void setUp() {
        orderController = new OrderController(orderService, orderValidator);
    }

    @Test
    public void processOrderTest() throws Exception {
        doReturn(order).when(orderService).processOrder(any());

        OrderDto response = orderController.createOrder(order);

        assertEquals(response, order);
    }

    @Test
    public void getOrderTest() {
        doReturn(order).when(orderService).getOrderById(any());

        OrderDto response = orderController.getOrder("orderId");

        assertEquals(response, order);
    }

}
