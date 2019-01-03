package com.adrrriannn.store.order.service;

import com.adrrriannn.store.dto.OrderDto;
import com.adrrriannn.store.dto.OrderItemDto;
import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.dto.UserDto;
import com.adrrriannn.store.order.entity.Order;
import com.adrrriannn.store.order.entity.OrderItem;
import com.adrrriannn.store.order.mapper.OrderMapper;
import com.adrrriannn.store.order.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private ProductService productService;

    @Mock
    private NotificationService notificationService;

    private OrderService orderService;

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

    private OrderItem orderItem1 = OrderItem.builder()
            .id("id1")
            .productId(product1.getId())
            .quantity(1)
            .build();

    private OrderItem orderItem2 = OrderItem.builder()
            .id("id2")
            .productId(product2.getId())
            .quantity(1)
            .build();

    private Order order = Order.builder()
            .id("id")
            .items(Arrays.asList(orderItem1, orderItem2))
            .userId(user.getId())
            .build();

    private OrderDto orderDto = OrderDto.builder()
            .id("id")
            .items(Arrays.asList(orderItemDto1, orderItemDto2))
            .user(user)
            .build();

    @Before
    public void setUp() {
        orderService = new OrderServiceImpl(orderRepository, orderMapper, productService, notificationService);
        doReturn(orderDto).when(orderMapper).map(order);
        doReturn(order).when(orderMapper).map(orderDto);
    }

    @Test
    public void processOrderTest() {

        doReturn(order).when(orderRepository).save(order);

        OrderDto result = orderService.processOrder(orderDto);
        assertEquals(orderDto, result);
    }

    @Test
    public void getOrderByIdTest() {
        doReturn(orderDto).when(orderMapper).map(order);
        doReturn(Optional.of(order)).when(orderRepository).findById(anyString());

        OrderDto result = orderService.getOrderById("orderId");
        assertEquals(orderDto, result);
    }

}
