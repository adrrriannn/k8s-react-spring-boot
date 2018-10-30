package com.adrrriannn.store.product.validator;

import com.adrrriannn.store.dto.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProductDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
