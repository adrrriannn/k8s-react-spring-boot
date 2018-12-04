package com.adrrriannn.store.order.controller;

import com.adrrriannn.store.dto.OrderDto;
import com.adrrriannn.store.order.exception.ProductNotFoundException;
import com.adrrriannn.store.order.service.OrderService;
import com.adrrriannn.store.order.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    private OrderService orderService;
    private OrderValidator orderValidator;

    @Autowired
    public OrderController(OrderService orderService,
                           OrderValidator orderValidator) {
        this.orderService = orderService;
        this.orderValidator = orderValidator;
    }

    @InitBinder("order")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(orderValidator);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody @Valid OrderDto order) throws ProductNotFoundException {
        return orderService.processOrder(order);
    }

}
