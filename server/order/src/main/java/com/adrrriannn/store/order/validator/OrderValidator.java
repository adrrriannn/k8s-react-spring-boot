package com.adrrriannn.store.order.validator;

import com.adrrriannn.store.dto.OrderDto;
import com.adrrriannn.store.order.service.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OrderValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return OrderDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
