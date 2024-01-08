package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.CartItemTestSamples.*;
import static com.mycompany.myapp.domain.CartTestSamples.*;
import static com.mycompany.myapp.domain.OrderMainTestSamples.*;
import static com.mycompany.myapp.domain.ShipmentCartTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CartTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cart.class);
        Cart cart1 = getCartSample1();
        Cart cart2 = new Cart();
        assertThat(cart1).isNotEqualTo(cart2);

        cart2.setId(cart1.getId());
        assertThat(cart1).isEqualTo(cart2);

        cart2 = getCartSample2();
        assertThat(cart1).isNotEqualTo(cart2);
    }

    @Test
    void shipmentCartTest() throws Exception {
        Cart cart = getCartRandomSampleGenerator();
        ShipmentCart shipmentCartBack = getShipmentCartRandomSampleGenerator();

        cart.setShipmentCart(shipmentCartBack);
        assertThat(cart.getShipmentCart()).isEqualTo(shipmentCartBack);

        cart.shipmentCart(null);
        assertThat(cart.getShipmentCart()).isNull();
    }

    @Test
    void cartItemTest() throws Exception {
        Cart cart = getCartRandomSampleGenerator();
        CartItem cartItemBack = getCartItemRandomSampleGenerator();

        cart.addCartItem(cartItemBack);
        assertThat(cart.getCartItems()).containsOnly(cartItemBack);
        assertThat(cartItemBack.getCart()).isEqualTo(cart);

        cart.removeCartItem(cartItemBack);
        assertThat(cart.getCartItems()).doesNotContain(cartItemBack);
        assertThat(cartItemBack.getCart()).isNull();

        cart.cartItems(new HashSet<>(Set.of(cartItemBack)));
        assertThat(cart.getCartItems()).containsOnly(cartItemBack);
        assertThat(cartItemBack.getCart()).isEqualTo(cart);

        cart.setCartItems(new HashSet<>());
        assertThat(cart.getCartItems()).doesNotContain(cartItemBack);
        assertThat(cartItemBack.getCart()).isNull();
    }

    @Test
    void orderMainTest() throws Exception {
        Cart cart = getCartRandomSampleGenerator();
        OrderMain orderMainBack = getOrderMainRandomSampleGenerator();

        cart.setOrderMain(orderMainBack);
        assertThat(cart.getOrderMain()).isEqualTo(orderMainBack);

        cart.orderMain(null);
        assertThat(cart.getOrderMain()).isNull();
    }
}
