package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A CartItem.
 */
@Entity
@Table(name = "cart_item")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @DecimalMin(value = "0")
    @DecimalMax(value = "1000000")
    @Column(name = "price_net")
    private Float priceNet;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "vat")
    private Float vat;

    @Column(name = "price_gross")
    private Float priceGross;

    @Column(name = "total_price_net")
    private Float totalPriceNet;

    @Column(name = "total_price_gross")
    private Float totalPriceGross;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "shipmentCart", "user", "cartItems", "orderMain" }, allowSetters = true)
    private Cart cart;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CartItem id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public CartItem quantity(Integer quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPriceNet() {
        return this.priceNet;
    }

    public CartItem priceNet(Float priceNet) {
        this.setPriceNet(priceNet);
        return this;
    }

    public void setPriceNet(Float priceNet) {
        this.priceNet = priceNet;
    }

    public Float getVat() {
        return this.vat;
    }

    public CartItem vat(Float vat) {
        this.setVat(vat);
        return this;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }

    public Float getPriceGross() {
        return this.priceGross;
    }

    public CartItem priceGross(Float priceGross) {
        this.setPriceGross(priceGross);
        return this;
    }

    public void setPriceGross(Float priceGross) {
        this.priceGross = priceGross;
    }

    public Float getTotalPriceNet() {
        return this.totalPriceNet;
    }

    public CartItem totalPriceNet(Float totalPriceNet) {
        this.setTotalPriceNet(totalPriceNet);
        return this;
    }

    public void setTotalPriceNet(Float totalPriceNet) {
        this.totalPriceNet = totalPriceNet;
    }

    public Float getTotalPriceGross() {
        return this.totalPriceGross;
    }

    public CartItem totalPriceGross(Float totalPriceGross) {
        this.setTotalPriceGross(totalPriceGross);
        return this;
    }

    public void setTotalPriceGross(Float totalPriceGross) {
        this.totalPriceGross = totalPriceGross;
    }

    public String getDescription() {
        return this.description;
    }

    public CartItem description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CartItem product(Product product) {
        this.setProduct(product);
        return this;
    }

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public CartItem cart(Cart cart) {
        this.setCart(cart);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartItem)) {
            return false;
        }
        return getId() != null && getId().equals(((CartItem) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CartItem{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", priceNet=" + getPriceNet() +
            ", vat=" + getVat() +
            ", priceGross=" + getPriceGross() +
            ", totalPriceNet=" + getTotalPriceNet() +
            ", totalPriceGross=" + getTotalPriceGross() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
