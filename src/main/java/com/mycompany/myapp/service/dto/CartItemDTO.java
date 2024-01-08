package com.mycompany.myapp.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.CartItem} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CartItemDTO implements Serializable {

    private Long id;

    private Integer quantity;

    @DecimalMin(value = "0")
    @DecimalMax(value = "1000000")
    private Float priceNet;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float vat;

    private Float priceGross;

    private Float totalPriceNet;

    private Float totalPriceGross;

    @Lob
    private String description;

    private ProductDTO product;

    private CartDTO cart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPriceNet() {
        return priceNet;
    }

    public void setPriceNet(Float priceNet) {
        this.priceNet = priceNet;
    }

    public Float getVat() {
        return vat;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }

    public Float getPriceGross() {
        return priceGross;
    }

    public void setPriceGross(Float priceGross) {
        this.priceGross = priceGross;
    }

    public Float getTotalPriceNet() {
        return totalPriceNet;
    }

    public void setTotalPriceNet(Float totalPriceNet) {
        this.totalPriceNet = totalPriceNet;
    }

    public Float getTotalPriceGross() {
        return totalPriceGross;
    }

    public void setTotalPriceGross(Float totalPriceGross) {
        this.totalPriceGross = totalPriceGross;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public CartDTO getCart() {
        return cart;
    }

    public void setCart(CartDTO cart) {
        this.cart = cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartItemDTO)) {
            return false;
        }

        CartItemDTO cartItemDTO = (CartItemDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cartItemDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CartItemDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", priceNet=" + getPriceNet() +
            ", vat=" + getVat() +
            ", priceGross=" + getPriceGross() +
            ", totalPriceNet=" + getTotalPriceNet() +
            ", totalPriceGross=" + getTotalPriceGross() +
            ", description='" + getDescription() + "'" +
            ", product=" + getProduct() +
            ", cart=" + getCart() +
            "}";
    }
}
