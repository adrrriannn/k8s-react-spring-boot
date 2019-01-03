package com.adrrriannn.store.order.validator;

import com.adrrriannn.store.dto.OrderDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CartValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return OrderDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
