package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A ShipmentCart.
 */
@Entity
@Table(name = "shipment_cart")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ShipmentCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 500)
    @Column(name = "first_name", length = 500)
    private String firstName;

    @Size(max = 500)
    @Column(name = "last_name", length = 500)
    private String lastName;

    @Size(max = 500)
    @Column(name = "street", length = 500)
    private String street;

    @Size(max = 20)
    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Size(max = 500)
    @Column(name = "city", length = 500)
    private String city;

    @Size(max = 500)
    @Column(name = "country", length = 500)
    private String country;

    @Size(max = 30)
    @Column(name = "phone_to_the_receiver", length = 30)
    private String phoneToTheReceiver;

    @Size(max = 10000)
    @Column(name = "firm", length = 10000)
    private String firm;

    @Size(max = 50)
    @Column(name = "tax_number", length = 50)
    private String taxNumber;

    @JsonIgnoreProperties(value = { "shipmentCart", "user", "cartItems", "orderMain" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shipmentCart")
    private Cart cart;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ShipmentCart id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public ShipmentCart firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public ShipmentCart lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return this.street;
    }

    public ShipmentCart street(String street) {
        this.setStreet(street);
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public ShipmentCart postalCode(String postalCode) {
        this.setPostalCode(postalCode);
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return this.city;
    }

    public ShipmentCart city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public ShipmentCart country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneToTheReceiver() {
        return this.phoneToTheReceiver;
    }

    public ShipmentCart phoneToTheReceiver(String phoneToTheReceiver) {
        this.setPhoneToTheReceiver(phoneToTheReceiver);
        return this;
    }

    public void setPhoneToTheReceiver(String phoneToTheReceiver) {
        this.phoneToTheReceiver = phoneToTheReceiver;
    }

    public String getFirm() {
        return this.firm;
    }

    public ShipmentCart firm(String firm) {
        this.setFirm(firm);
        return this;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getTaxNumber() {
        return this.taxNumber;
    }

    public ShipmentCart taxNumber(String taxNumber) {
        this.setTaxNumber(taxNumber);
        return this;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {
        if (this.cart != null) {
            this.cart.setShipmentCart(null);
        }
        if (cart != null) {
            cart.setShipmentCart(this);
        }
        this.cart = cart;
    }

    public ShipmentCart cart(Cart cart) {
        this.setCart(cart);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShipmentCart)) {
            return false;
        }
        return getId() != null && getId().equals(((ShipmentCart) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShipmentCart{" +
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
