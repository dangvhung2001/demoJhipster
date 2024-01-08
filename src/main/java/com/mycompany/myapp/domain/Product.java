package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.ProductCategoryEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_category_enum")
    private ProductCategoryEnum productCategoryEnum;

    @Size(min = 0, max = 5000)
    @Column(name = "name", length = 5000)
    private String name;

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

    @Min(value = 0)
    @Max(value = 1000000)
    @Column(name = "stock")
    private Integer stock;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_time")
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategoryEnum getProductCategoryEnum() {
        return this.productCategoryEnum;
    }

    public Product productCategoryEnum(ProductCategoryEnum productCategoryEnum) {
        this.setProductCategoryEnum(productCategoryEnum);
        return this;
    }

    public void setProductCategoryEnum(ProductCategoryEnum productCategoryEnum) {
        this.productCategoryEnum = productCategoryEnum;
    }

    public String getName() {
        return this.name;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Product quantity(Integer quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPriceNet() {
        return this.priceNet;
    }

    public Product priceNet(Float priceNet) {
        this.setPriceNet(priceNet);
        return this;
    }

    public void setPriceNet(Float priceNet) {
        this.priceNet = priceNet;
    }

    public Float getVat() {
        return this.vat;
    }

    public Product vat(Float vat) {
        this.setVat(vat);
        return this;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }

    public Float getPriceGross() {
        return this.priceGross;
    }

    public Product priceGross(Float priceGross) {
        this.setPriceGross(priceGross);
        return this;
    }

    public void setPriceGross(Float priceGross) {
        this.priceGross = priceGross;
    }

    public Integer getStock() {
        return this.stock;
    }

    public Product stock(Integer stock) {
        this.setStock(stock);
        return this;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return this.description;
    }

    public Product description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public Product createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public Product updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return getId() != null && getId().equals(((Product) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", productCategoryEnum='" + getProductCategoryEnum() + "'" +
            ", name='" + getName() + "'" +
            ", quantity=" + getQuantity() +
            ", priceNet=" + getPriceNet() +
            ", vat=" + getVat() +
            ", priceGross=" + getPriceGross() +
            ", stock=" + getStock() +
            ", description='" + getDescription() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
