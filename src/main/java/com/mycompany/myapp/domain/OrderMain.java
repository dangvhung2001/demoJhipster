package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.OrderMainStatusEnum;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A OrderMain.
 */
@Entity
@Table(name = "order_main")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderMain implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "buyer_login")
    private String buyerLogin;

    @Column(name = "buyer_first_name")
    private String buyerFirstName;

    @Column(name = "buyer_last_name")
    private String buyerLastName;

    @Column(name = "buyer_email")
    private String buyerEmail;

    @Column(name = "buyer_phone")
    private String buyerPhone;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "order_main_status")
    private OrderMainStatusEnum orderMainStatus;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_time")
    private Instant updateTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderMain")
    @JsonIgnoreProperties(value = { "shipmentCart", "user", "cartItems", "orderMain" }, allowSetters = true)
    private Set<Cart> carts = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OrderMain id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerLogin() {
        return this.buyerLogin;
    }

    public OrderMain buyerLogin(String buyerLogin) {
        this.setBuyerLogin(buyerLogin);
        return this;
    }

    public void setBuyerLogin(String buyerLogin) {
        this.buyerLogin = buyerLogin;
    }

    public String getBuyerFirstName() {
        return this.buyerFirstName;
    }

    public OrderMain buyerFirstName(String buyerFirstName) {
        this.setBuyerFirstName(buyerFirstName);
        return this;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public String getBuyerLastName() {
        return this.buyerLastName;
    }

    public OrderMain buyerLastName(String buyerLastName) {
        this.setBuyerLastName(buyerLastName);
        return this;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }

    public String getBuyerEmail() {
        return this.buyerEmail;
    }

    public OrderMain buyerEmail(String buyerEmail) {
        this.setBuyerEmail(buyerEmail);
        return this;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerPhone() {
        return this.buyerPhone;
    }

    public OrderMain buyerPhone(String buyerPhone) {
        this.setBuyerPhone(buyerPhone);
        return this;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public Float getAmountOfCartNet() {
        return this.amountOfCartNet;
    }

    public OrderMain amountOfCartNet(Float amountOfCartNet) {
        this.setAmountOfCartNet(amountOfCartNet);
        return this;
    }

    public void setAmountOfCartNet(Float amountOfCartNet) {
        this.amountOfCartNet = amountOfCartNet;
    }

    public Float getAmountOfCartGross() {
        return this.amountOfCartGross;
    }

    public OrderMain amountOfCartGross(Float amountOfCartGross) {
        this.setAmountOfCartGross(amountOfCartGross);
        return this;
    }

    public void setAmountOfCartGross(Float amountOfCartGross) {
        this.amountOfCartGross = amountOfCartGross;
    }

    public Float getAmountOfShipmentNet() {
        return this.amountOfShipmentNet;
    }

    public OrderMain amountOfShipmentNet(Float amountOfShipmentNet) {
        this.setAmountOfShipmentNet(amountOfShipmentNet);
        return this;
    }

    public void setAmountOfShipmentNet(Float amountOfShipmentNet) {
        this.amountOfShipmentNet = amountOfShipmentNet;
    }

    public Float getAmountOfShipmentGross() {
        return this.amountOfShipmentGross;
    }

    public OrderMain amountOfShipmentGross(Float amountOfShipmentGross) {
        this.setAmountOfShipmentGross(amountOfShipmentGross);
        return this;
    }

    public void setAmountOfShipmentGross(Float amountOfShipmentGross) {
        this.amountOfShipmentGross = amountOfShipmentGross;
    }

    public Float getAmountOfOrderNet() {
        return this.amountOfOrderNet;
    }

    public OrderMain amountOfOrderNet(Float amountOfOrderNet) {
        this.setAmountOfOrderNet(amountOfOrderNet);
        return this;
    }

    public void setAmountOfOrderNet(Float amountOfOrderNet) {
        this.amountOfOrderNet = amountOfOrderNet;
    }

    public Float getAmountOfOrderGross() {
        return this.amountOfOrderGross;
    }

    public OrderMain amountOfOrderGross(Float amountOfOrderGross) {
        this.setAmountOfOrderGross(amountOfOrderGross);
        return this;
    }

    public void setAmountOfOrderGross(Float amountOfOrderGross) {
        this.amountOfOrderGross = amountOfOrderGross;
    }

    public OrderMainStatusEnum getOrderMainStatus() {
        return this.orderMainStatus;
    }

    public OrderMain orderMainStatus(OrderMainStatusEnum orderMainStatus) {
        this.setOrderMainStatus(orderMainStatus);
        return this;
    }

    public void setOrderMainStatus(OrderMainStatusEnum orderMainStatus) {
        this.orderMainStatus = orderMainStatus;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public OrderMain createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public OrderMain updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Set<Cart> getCarts() {
        return this.carts;
    }

    public void setCarts(Set<Cart> carts) {
        if (this.carts != null) {
            this.carts.forEach(i -> i.setOrderMain(null));
        }
        if (carts != null) {
            carts.forEach(i -> i.setOrderMain(this));
        }
        this.carts = carts;
    }

    public OrderMain carts(Set<Cart> carts) {
        this.setCarts(carts);
        return this;
    }

    public OrderMain addCart(Cart cart) {
        this.carts.add(cart);
        cart.setOrderMain(this);
        return this;
    }

    public OrderMain removeCart(Cart cart) {
        this.carts.remove(cart);
        cart.setOrderMain(null);
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderMain user(User user) {
        this.setUser(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderMain)) {
            return false;
        }
        return getId() != null && getId().equals(((OrderMain) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderMain{" +
            "id=" + getId() +
            ", buyerLogin='" + getBuyerLogin() + "'" +
            ", buyerFirstName='" + getBuyerFirstName() + "'" +
            ", buyerLastName='" + getBuyerLastName() + "'" +
            ", buyerEmail='" + getBuyerEmail() + "'" +
            ", buyerPhone='" + getBuyerPhone() + "'" +
            ", amountOfCartNet=" + getAmountOfCartNet() +
            ", amountOfCartGross=" + getAmountOfCartGross() +
            ", amountOfShipmentNet=" + getAmountOfShipmentNet() +
            ", amountOfShipmentGross=" + getAmountOfShipmentGross() +
            ", amountOfOrderNet=" + getAmountOfOrderNet() +
            ", amountOfOrderGross=" + getAmountOfOrderGross() +
            ", orderMainStatus='" + getOrderMainStatus() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
