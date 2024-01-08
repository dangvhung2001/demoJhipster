package com.mycompany.myapp.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ShipmentCart} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ShipmentCartDTO implements Serializable {

    private Long id;

    @Size(max = 500)
    private String firstName;

    @Size(max = 500)
    private String lastName;

    @Size(max = 500)
    private String street;

    @Size(max = 20)
    private String postalCode;

    @Size(max = 500)
    private String city;

    @Size(max = 500)
    private String country;

    @Size(max = 30)
    private String phoneToTheReceiver;

    @Size(max = 10000)
    private String firm;

    @Size(max = 50)
    private String taxNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneToTheReceiver() {
        return phoneToTheReceiver;
    }

    public void setPhoneToTheReceiver(String phoneToTheReceiver) {
        this.phoneToTheReceiver = phoneToTheReceiver;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShipmentCartDTO)) {
            return false;
        }

        ShipmentCartDTO shipmentCartDTO = (ShipmentCartDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, shipmentCartDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShipmentCartDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", street='" + getStreet() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", phoneToTheReceiver='" + getPhoneToTheReceiver() + "'" +
            ", firm='" + getFirm() + "'" +
            ", taxNumber='" + getTaxNumber() + "'" +
            "}";
    }
}
