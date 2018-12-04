package com.adrrriannn.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String email;
    private String productId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Setter
    private ProductDto productDto;
}
