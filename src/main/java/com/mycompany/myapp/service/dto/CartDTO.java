package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Cart} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CartDTO implements Serializable {

    private Long id;

    private Float amountOfCartNet;

    private Float amountOfCartGross;

    private Float amountOfShipmentNet;

    private Float amountOfShipmentGross;

    private Float amountOfOrderNet;

    private Float amountOfOrderGross;

    private Integer quantity;

    private ShipmentCartDTO shipmentCart;

    private UserDTO user;

    private OrderMainDTO orderMain;

    private List<CartItemDTO> cartItems;

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAmountOfCartNet() {
        return amountOfCartNet;
    }

    public void setAmountOfCartNet(Float amountOfCartNet) {
        this.amountOfCartNet = amountOfCartNet;
    }

    public Float getAmountOfCartGross() {
        return amountOfCartGross;
    }

    public void setAmountOfCartGross(Float amountOfCartGross) {
        this.amountOfCartGross = amountOfCartGross;
    }

    public Float getAmountOfShipmentNet() {
        return amountOfShipmentNet;
    }

    public void setAmountOfShipmentNet(Float amountOfShipmentNet) {
        this.amountOfShipmentNet = amountOfShipmentNet;
    }

    public Float getAmountOfShipmentGross() {
        return amountOfShipmentGross;
    }

    public void setAmountOfShipmentGross(Float amountOfShipmentGross) {
        this.amountOfShipmentGross = amountOfShipmentGross;
    }

    public Float getAmountOfOrderNet() {
        return amountOfOrderNet;
    }

    public void setAmountOfOrderNet(Float amountOfOrderNet) {
        this.amountOfOrderNet = amountOfOrderNet;
    }

    public Float getAmountOfOrderGross() {
        return amountOfOrderGross;
    }

    public void setAmountOfOrderGross(Float amountOfOrderGross) {
        this.amountOfOrderGross = amountOfOrderGross;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ShipmentCartDTO getShipmentCart() {
        return shipmentCart;
    }

    public void setShipmentCart(ShipmentCartDTO shipmentCart) {
        this.shipmentCart = shipmentCart;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public OrderMainDTO getOrderMain() {
        return orderMain;
    }

    public void setOrderMain(OrderMainDTO orderMain) {
        this.orderMain = orderMain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartDTO)) {
            return false;
        }

        CartDTO cartDTO = (CartDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cartDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CartDTO{" +
            "id=" + getId() +
            ", amountOfCartNet=" + getAmountOfCartNet() +
            ", amountOfCartGross=" + getAmountOfCartGross() +
            ", amountOfShipmentNet=" + getAmountOfShipmentNet() +
            ", amountOfShipmentGross=" + getAmountOfShipmentGross() +
            ", amountOfOrderNet=" + getAmountOfOrderNet() +
            ", amountOfOrderGross=" + getAmountOfOrderGross() +
            ", quantity=" + getQuantity() +
            ", shipmentCart=" + getShipmentCart() +
            ", user=" + getUser() +
            ", orderMain=" + getOrderMain() +
            "}";
    }
}
