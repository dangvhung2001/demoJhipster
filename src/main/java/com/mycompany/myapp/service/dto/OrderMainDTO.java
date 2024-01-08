package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.OrderMainStatusEnum;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.OrderMain} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderMainDTO implements Serializable {

    private Long id;

    private String buyerLogin;

    private String buyerFirstName;

    private String buyerLastName;

    private String buyerEmail;

    private String buyerPhone;

    private Float amountOfCartNet;

    private Float amountOfCartGross;

    private Float amountOfShipmentNet;

    private Float amountOfShipmentGross;

    private Float amountOfOrderNet;

    private Float amountOfOrderGross;

    private OrderMainStatusEnum orderMainStatus;

    private Instant createTime;

    private Instant updateTime;

    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerLogin() {
        return buyerLogin;
    }

    public void setBuyerLogin(String buyerLogin) {
        this.buyerLogin = buyerLogin;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
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

    public OrderMainStatusEnum getOrderMainStatus() {
        return orderMainStatus;
    }

    public void setOrderMainStatus(OrderMainStatusEnum orderMainStatus) {
        this.orderMainStatus = orderMainStatus;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderMainDTO)) {
            return false;
        }

        OrderMainDTO orderMainDTO = (OrderMainDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, orderMainDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderMainDTO{" +
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
            ", user=" + getUser() +
            "}";
    }
}
