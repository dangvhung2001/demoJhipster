package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;

class CartItemMapperTest {

    private CartItemMapper cartItemMapper;

    @BeforeEach
    public void setUp() {
        cartItemMapper = new CartItemMapperImpl();
    }
}
