package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Cart.
 */
@Entity
@Table(name = "cart")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount_of_cart_net")
    private Float amountOfCartNet;

    @Column(name = "amount_of_cart_gross")
    private Float amountOfCartGross;

    @Column(name = "amount_of_shipment_net")
    private Float amountOfShipmentNet;

    @Column(name = "amount_of_shipment_gross")
    private Float amountOfShipmentGross;

    @Column(name = "amount_of_order_net")
    private Float amountOfOrderNet;

    @Column(name = "amount_of_order_gross")
    private Float amountOfOrderGross;

    @Column(name = "quantity")
    private Integer quantity;

    @JsonIgnoreProperties(value = { "cart" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private ShipmentCart shipmentCart;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
    @JsonIgnoreProperties(value = { "product", "cart" }, allowSetters = true)
    private Set<CartItem> cartItems = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "carts", "user" }, allowSetters = true)
    private OrderMain orderMain;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Cart id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAmountOfCartNet() {
        return this.amountOfCartNet;
    }

    public Cart amountOfCartNet(Float amountOfCartNet) {
        this.setAmountOfCartNet(amountOfCartNet);
        return this;
    }

    public void setAmountOfCartNet(Float amountOfCartNet) {
        this.amountOfCartNet = amountOfCartNet;
    }

    public Float getAmountOfCartGross() {
        return this.amountOfCartGross;
    }

    public Cart amountOfCartGross(Float amountOfCartGross) {
        this.setAmountOfCartGross(amountOfCartGross);
        return this;
    }

    public void setAmountOfCartGross(Float amountOfCartGross) {
        this.amountOfCartGross = amountOfCartGross;
    }

    public Float getAmountOfShipmentNet() {
        return this.amountOfShipmentNet;
    }

    public Cart amountOfShipmentNet(Float amountOfShipmentNet) {
        this.setAmountOfShipmentNet(amountOfShipmentNet);
        return this;
    }

    public void setAmountOfShipmentNet(Float amountOfShipmentNet) {
        this.amountOfShipmentNet = amountOfShipmentNet;
    }

    public Float getAmountOfShipmentGross() {
        return this.amountOfShipmentGross;
    }

    public Cart amountOfShipmentGross(Float amountOfShipmentGross) {
        this.setAmountOfShipmentGross(amountOfShipmentGross);
        return this;
    }

    public void setAmountOfShipmentGross(Float amountOfShipmentGross) {
        this.amountOfShipmentGross = amountOfShipmentGross;
    }

    public Float getAmountOfOrderNet() {
        return this.amountOfOrderNet;
    }

    public Cart amountOfOrderNet(Float amountOfOrderNet) {
        this.setAmountOfOrderNet(amountOfOrderNet);
        return this;
    }

    public void setAmountOfOrderNet(Float amountOfOrderNet) {
        this.amountOfOrderNet = amountOfOrderNet;
    }

    public Float getAmountOfOrderGross() {
        return this.amountOfOrderGross;
    }

    public Cart amountOfOrderGross(Float amountOfOrderGross) {
        this.setAmountOfOrderGross(amountOfOrderGross);
        return this;
    }

    public void setAmountOfOrderGross(Float amountOfOrderGross) {
        this.amountOfOrderGross = amountOfOrderGross;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Cart quantity(Integer quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ShipmentCart getShipmentCart() {
        return this.shipmentCart;
    }

    public void setShipmentCart(ShipmentCart shipmentCart) {
        this.shipmentCart = shipmentCart;
    }

    public Cart shipmentCart(ShipmentCart shipmentCart) {
        this.setShipmentCart(shipmentCart);
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart user(User user) {
        this.setUser(user);
        return this;
    }

    public Set<CartItem> getCartItems() {
        return this.cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        if (this.cartItems != null) {
            this.cartItems.forEach(i -> i.setCart(null));
        }
        if (cartItems != null) {
            cartItems.forEach(i -> i.setCart(this));
        }
        this.cartItems = cartItems;
    }

    public Cart cartItems(Set<CartItem> cartItems) {
        this.setCartItems(cartItems);
        return this;
    }

    public Cart addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
        cartItem.setCart(this);
        return this;
    }

    public Cart removeCartItem(CartItem cartItem) {
        this.cartItems.remove(cartItem);
        cartItem.setCart(null);
        return this;
    }

    public OrderMain getOrderMain() {
        return this.orderMain;
    }

    public void setOrderMain(OrderMain orderMain) {
        this.orderMain = orderMain;
    }

    public Cart orderMain(OrderMain orderMain) {
        this.setOrderMain(orderMain);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cart)) {
            return false;
        }
        return getId() != null && getId().equals(((Cart) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cart{" +
            "id=" + getId() +
            ", amountOfCartNet=" + getAmountOfCartNet() +
            ", amountOfCartGross=" + getAmountOfCartGross() +
            ", amountOfShipmentNet=" + getAmountOfShipmentNet() +
            ", amountOfShipmentGross=" + getAmountOfShipmentGross() +
            ", amountOfOrderNet=" + getAmountOfOrderNet() +
            ", amountOfOrderGross=" + getAmountOfOrderGross() +
            ", quantity=" + getQuantity() +
            "}";
    }
}
