package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.CartTestSamples.*;
import static com.mycompany.myapp.domain.OrderMainTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class OrderMainTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderMain.class);
        OrderMain orderMain1 = getOrderMainSample1();
        OrderMain orderMain2 = new OrderMain();
        assertThat(orderMain1).isNotEqualTo(orderMain2);

        orderMain2.setId(orderMain1.getId());
        assertThat(orderMain1).isEqualTo(orderMain2);

        orderMain2 = getOrderMainSample2();
        assertThat(orderMain1).isNotEqualTo(orderMain2);
    }

    @Test
    void cartTest() throws Exception {
        OrderMain orderMain = getOrderMainRandomSampleGenerator();
        Cart cartBack = getCartRandomSampleGenerator();

        orderMain.addCart(cartBack);
        assertThat(orderMain.getCarts()).containsOnly(cartBack);
        assertThat(cartBack.getOrderMain()).isEqualTo(orderMain);

        orderMain.removeCart(cartBack);
        assertThat(orderMain.getCarts()).doesNotContain(cartBack);
        assertThat(cartBack.getOrderMain()).isNull();

        orderMain.carts(new HashSet<>(Set.of(cartBack)));
        assertThat(orderMain.getCarts()).containsOnly(cartBack);
        assertThat(cartBack.getOrderMain()).isEqualTo(orderMain);

        orderMain.setCarts(new HashSet<>());
        assertThat(orderMain.getCarts()).doesNotContain(cartBack);
        assertThat(cartBack.getOrderMain()).isNull();
    }
}
