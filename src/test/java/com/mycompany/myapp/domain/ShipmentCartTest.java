package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.CartTestSamples.*;
import static com.mycompany.myapp.domain.ShipmentCartTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ShipmentCartTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShipmentCart.class);
        ShipmentCart shipmentCart1 = getShipmentCartSample1();
        ShipmentCart shipmentCart2 = new ShipmentCart();
        assertThat(shipmentCart1).isNotEqualTo(shipmentCart2);

        shipmentCart2.setId(shipmentCart1.getId());
        assertThat(shipmentCart1).isEqualTo(shipmentCart2);

        shipmentCart2 = getShipmentCartSample2();
        assertThat(shipmentCart1).isNotEqualTo(shipmentCart2);
    }

    @Test
    void cartTest() throws Exception {
        ShipmentCart shipmentCart = getShipmentCartRandomSampleGenerator();
        Cart cartBack = getCartRandomSampleGenerator();

        shipmentCart.setCart(cartBack);
        assertThat(shipmentCart.getCart()).isEqualTo(cartBack);
        assertThat(cartBack.getShipmentCart()).isEqualTo(shipmentCart);

        shipmentCart.cart(null);
        assertThat(shipmentCart.getCart()).isNull();
        assertThat(cartBack.getShipmentCart()).isNull();
    }
}
