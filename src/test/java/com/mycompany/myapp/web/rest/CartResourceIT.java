package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Cart;
import com.mycompany.myapp.repository.CartRepository;
import com.mycompany.myapp.service.CartService;
import com.mycompany.myapp.service.dto.CartDTO;
import com.mycompany.myapp.service.mapper.CartMapper;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CartResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class CartResourceIT {

    private static final Float DEFAULT_AMOUNT_OF_CART_NET = 1F;
    private static final Float UPDATED_AMOUNT_OF_CART_NET = 2F;

    private static final Float DEFAULT_AMOUNT_OF_CART_GROSS = 1F;
    private static final Float UPDATED_AMOUNT_OF_CART_GROSS = 2F;

    private static final Float DEFAULT_AMOUNT_OF_SHIPMENT_NET = 1F;
    private static final Float UPDATED_AMOUNT_OF_SHIPMENT_NET = 2F;

    private static final Float DEFAULT_AMOUNT_OF_SHIPMENT_GROSS = 1F;
    private static final Float UPDATED_AMOUNT_OF_SHIPMENT_GROSS = 2F;

    private static final Float DEFAULT_AMOUNT_OF_ORDER_NET = 1F;
    private static final Float UPDATED_AMOUNT_OF_ORDER_NET = 2F;

    private static final Float DEFAULT_AMOUNT_OF_ORDER_GROSS = 1F;
    private static final Float UPDATED_AMOUNT_OF_ORDER_GROSS = 2F;

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final String ENTITY_API_URL = "/api/carts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CartRepository cartRepository;

    @Mock
    private CartRepository cartRepositoryMock;

    @Autowired
    private CartMapper cartMapper;

    @Mock
    private CartService cartServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCartMockMvc;

    private Cart cart;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cart createEntity(EntityManager em) {
        Cart cart = new Cart()
            .amountOfCartNet(DEFAULT_AMOUNT_OF_CART_NET)
            .amountOfCartGross(DEFAULT_AMOUNT_OF_CART_GROSS)
            .amountOfShipmentNet(DEFAULT_AMOUNT_OF_SHIPMENT_NET)
            .amountOfShipmentGross(DEFAULT_AMOUNT_OF_SHIPMENT_GROSS)
            .amountOfOrderNet(DEFAULT_AMOUNT_OF_ORDER_NET)
            .amountOfOrderGross(DEFAULT_AMOUNT_OF_ORDER_GROSS)
            .quantity(DEFAULT_QUANTITY);
        return cart;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cart createUpdatedEntity(EntityManager em) {
        Cart cart = new Cart()
            .amountOfCartNet(UPDATED_AMOUNT_OF_CART_NET)
            .amountOfCartGross(UPDATED_AMOUNT_OF_CART_GROSS)
            .amountOfShipmentNet(UPDATED_AMOUNT_OF_SHIPMENT_NET)
            .amountOfShipmentGross(UPDATED_AMOUNT_OF_SHIPMENT_GROSS)
            .amountOfOrderNet(UPDATED_AMOUNT_OF_ORDER_NET)
            .amountOfOrderGross(UPDATED_AMOUNT_OF_ORDER_GROSS)
            .quantity(UPDATED_QUANTITY);
        return cart;
    }

    @BeforeEach
    public void initTest() {
        cart = createEntity(em);
    }

    @Test
    @Transactional
    void createCart() throws Exception {
        int databaseSizeBeforeCreate = cartRepository.findAll().size();
        // Create the Cart
        CartDTO cartDTO = cartMapper.toDto(cart);
        restCartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cartDTO)))
            .andExpect(status().isCreated());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeCreate + 1);
        Cart testCart = cartList.get(cartList.size() - 1);
        assertThat(testCart.getAmountOfCartNet()).isEqualTo(DEFAULT_AMOUNT_OF_CART_NET);
        assertThat(testCart.getAmountOfCartGross()).isEqualTo(DEFAULT_AMOUNT_OF_CART_GROSS);
        assertThat(testCart.getAmountOfShipmentNet()).isEqualTo(DEFAULT_AMOUNT_OF_SHIPMENT_NET);
        assertThat(testCart.getAmountOfShipmentGross()).isEqualTo(DEFAULT_AMOUNT_OF_SHIPMENT_GROSS);
        assertThat(testCart.getAmountOfOrderNet()).isEqualTo(DEFAULT_AMOUNT_OF_ORDER_NET);
        assertThat(testCart.getAmountOfOrderGross()).isEqualTo(DEFAULT_AMOUNT_OF_ORDER_GROSS);
        assertThat(testCart.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    void createCartWithExistingId() throws Exception {
        // Create the Cart with an existing ID
        cart.setId(1L);
        CartDTO cartDTO = cartMapper.toDto(cart);

        int databaseSizeBeforeCreate = cartRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cartDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCarts() throws Exception {
        // Initialize the database
        cartRepository.saveAndFlush(cart);

        // Get all the cartList
        restCartMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cart.getId().intValue())))
            .andExpect(jsonPath("$.[*].amountOfCartNet").value(hasItem(DEFAULT_AMOUNT_OF_CART_NET.doubleValue())))
            .andExpect(jsonPath("$.[*].amountOfCartGross").value(hasItem(DEFAULT_AMOUNT_OF_CART_GROSS.doubleValue())))
            .andExpect(jsonPath("$.[*].amountOfShipmentNet").value(hasItem(DEFAULT_AMOUNT_OF_SHIPMENT_NET.doubleValue())))
            .andExpect(jsonPath("$.[*].amountOfShipmentGross").value(hasItem(DEFAULT_AMOUNT_OF_SHIPMENT_GROSS.doubleValue())))
            .andExpect(jsonPath("$.[*].amountOfOrderNet").value(hasItem(DEFAULT_AMOUNT_OF_ORDER_NET.doubleValue())))
            .andExpect(jsonPath("$.[*].amountOfOrderGross").value(hasItem(DEFAULT_AMOUNT_OF_ORDER_GROSS.doubleValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCartsWithEagerRelationshipsIsEnabled() throws Exception {
        when(cartServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCartMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(cartServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCartsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(cartServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCartMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(cartRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getCart() throws Exception {
        // Initialize the database
        cartRepository.saveAndFlush(cart);

        // Get the cart
        restCartMockMvc
            .perform(get(ENTITY_API_URL_ID, cart.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cart.getId().intValue()))
            .andExpect(jsonPath("$.amountOfCartNet").value(DEFAULT_AMOUNT_OF_CART_NET.doubleValue()))
            .andExpect(jsonPath("$.amountOfCartGross").value(DEFAULT_AMOUNT_OF_CART_GROSS.doubleValue()))
            .andExpect(jsonPath("$.amountOfShipmentNet").value(DEFAULT_AMOUNT_OF_SHIPMENT_NET.doubleValue()))
            .andExpect(jsonPath("$.amountOfShipmentGross").value(DEFAULT_AMOUNT_OF_SHIPMENT_GROSS.doubleValue()))
            .andExpect(jsonPath("$.amountOfOrderNet").value(DEFAULT_AMOUNT_OF_ORDER_NET.doubleValue()))
            .andExpect(jsonPath("$.amountOfOrderGross").value(DEFAULT_AMOUNT_OF_ORDER_GROSS.doubleValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY));
    }

    @Test
    @Transactional
    void getNonExistingCart() throws Exception {
        // Get the cart
        restCartMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCart() throws Exception {
        // Initialize the database
        cartRepository.saveAndFlush(cart);

        int databaseSizeBeforeUpdate = cartRepository.findAll().size();

        // Update the cart
        Cart updatedCart = cartRepository.findById(cart.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCart are not directly saved in db
        em.detach(updatedCart);
        updatedCart
            .amountOfCartNet(UPDATED_AMOUNT_OF_CART_NET)
            .amountOfCartGross(UPDATED_AMOUNT_OF_CART_GROSS)
            .amountOfShipmentNet(UPDATED_AMOUNT_OF_SHIPMENT_NET)
            .amountOfShipmentGross(UPDATED_AMOUNT_OF_SHIPMENT_GROSS)
            .amountOfOrderNet(UPDATED_AMOUNT_OF_ORDER_NET)
            .amountOfOrderGross(UPDATED_AMOUNT_OF_ORDER_GROSS)
            .quantity(UPDATED_QUANTITY);
        CartDTO cartDTO = cartMapper.toDto(updatedCart);

        restCartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cartDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cartDTO))
            )
            .andExpect(status().isOk());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeUpdate);
        Cart testCart = cartList.get(cartList.size() - 1);
        assertThat(testCart.getAmountOfCartNet()).isEqualTo(UPDATED_AMOUNT_OF_CART_NET);
        assertThat(testCart.getAmountOfCartGross()).isEqualTo(UPDATED_AMOUNT_OF_CART_GROSS);
        assertThat(testCart.getAmountOfShipmentNet()).isEqualTo(UPDATED_AMOUNT_OF_SHIPMENT_NET);
        assertThat(testCart.getAmountOfShipmentGross()).isEqualTo(UPDATED_AMOUNT_OF_SHIPMENT_GROSS);
        assertThat(testCart.getAmountOfOrderNet()).isEqualTo(UPDATED_AMOUNT_OF_ORDER_NET);
        assertThat(testCart.getAmountOfOrderGross()).isEqualTo(UPDATED_AMOUNT_OF_ORDER_GROSS);
        assertThat(testCart.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    void putNonExistingCart() throws Exception {
        int databaseSizeBeforeUpdate = cartRepository.findAll().size();
        cart.setId(longCount.incrementAndGet());

        // Create the Cart
        CartDTO cartDTO = cartMapper.toDto(cart);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cartDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCart() throws Exception {
        int databaseSizeBeforeUpdate = cartRepository.findAll().size();
        cart.setId(longCount.incrementAndGet());

        // Create the Cart
        CartDTO cartDTO = cartMapper.toDto(cart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCart() throws Exception {
        int databaseSizeBeforeUpdate = cartRepository.findAll().size();
        cart.setId(longCount.incrementAndGet());

        // Create the Cart
        CartDTO cartDTO = cartMapper.toDto(cart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCartMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cartDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCartWithPatch() throws Exception {
        // Initialize the database
        cartRepository.saveAndFlush(cart);

        int databaseSizeBeforeUpdate = cartRepository.findAll().size();

        // Update the cart using partial update
        Cart partialUpdatedCart = new Cart();
        partialUpdatedCart.setId(cart.getId());

        partialUpdatedCart
            .amountOfCartNet(UPDATED_AMOUNT_OF_CART_NET)
            .amountOfShipmentGross(UPDATED_AMOUNT_OF_SHIPMENT_GROSS)
            .amountOfOrderGross(UPDATED_AMOUNT_OF_ORDER_GROSS);

        restCartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCart.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCart))
            )
            .andExpect(status().isOk());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeUpdate);
        Cart testCart = cartList.get(cartList.size() - 1);
        assertThat(testCart.getAmountOfCartNet()).isEqualTo(UPDATED_AMOUNT_OF_CART_NET);
        assertThat(testCart.getAmountOfCartGross()).isEqualTo(DEFAULT_AMOUNT_OF_CART_GROSS);
        assertThat(testCart.getAmountOfShipmentNet()).isEqualTo(DEFAULT_AMOUNT_OF_SHIPMENT_NET);
        assertThat(testCart.getAmountOfShipmentGross()).isEqualTo(UPDATED_AMOUNT_OF_SHIPMENT_GROSS);
        assertThat(testCart.getAmountOfOrderNet()).isEqualTo(DEFAULT_AMOUNT_OF_ORDER_NET);
        assertThat(testCart.getAmountOfOrderGross()).isEqualTo(UPDATED_AMOUNT_OF_ORDER_GROSS);
        assertThat(testCart.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    void fullUpdateCartWithPatch() throws Exception {
        // Initialize the database
        cartRepository.saveAndFlush(cart);

        int databaseSizeBeforeUpdate = cartRepository.findAll().size();

        // Update the cart using partial update
        Cart partialUpdatedCart = new Cart();
        partialUpdatedCart.setId(cart.getId());

        partialUpdatedCart
            .amountOfCartNet(UPDATED_AMOUNT_OF_CART_NET)
            .amountOfCartGross(UPDATED_AMOUNT_OF_CART_GROSS)
            .amountOfShipmentNet(UPDATED_AMOUNT_OF_SHIPMENT_NET)
            .amountOfShipmentGross(UPDATED_AMOUNT_OF_SHIPMENT_GROSS)
            .amountOfOrderNet(UPDATED_AMOUNT_OF_ORDER_NET)
            .amountOfOrderGross(UPDATED_AMOUNT_OF_ORDER_GROSS)
            .quantity(UPDATED_QUANTITY);

        restCartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCart.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCart))
            )
            .andExpect(status().isOk());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeUpdate);
        Cart testCart = cartList.get(cartList.size() - 1);
        assertThat(testCart.getAmountOfCartNet()).isEqualTo(UPDATED_AMOUNT_OF_CART_NET);
        assertThat(testCart.getAmountOfCartGross()).isEqualTo(UPDATED_AMOUNT_OF_CART_GROSS);
        assertThat(testCart.getAmountOfShipmentNet()).isEqualTo(UPDATED_AMOUNT_OF_SHIPMENT_NET);
        assertThat(testCart.getAmountOfShipmentGross()).isEqualTo(UPDATED_AMOUNT_OF_SHIPMENT_GROSS);
        assertThat(testCart.getAmountOfOrderNet()).isEqualTo(UPDATED_AMOUNT_OF_ORDER_NET);
        assertThat(testCart.getAmountOfOrderGross()).isEqualTo(UPDATED_AMOUNT_OF_ORDER_GROSS);
        assertThat(testCart.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    void patchNonExistingCart() throws Exception {
        int databaseSizeBeforeUpdate = cartRepository.findAll().size();
        cart.setId(longCount.incrementAndGet());

        // Create the Cart
        CartDTO cartDTO = cartMapper.toDto(cart);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cartDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCart() throws Exception {
        int databaseSizeBeforeUpdate = cartRepository.findAll().size();
        cart.setId(longCount.incrementAndGet());

        // Create the Cart
        CartDTO cartDTO = cartMapper.toDto(cart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCart() throws Exception {
        int databaseSizeBeforeUpdate = cartRepository.findAll().size();
        cart.setId(longCount.incrementAndGet());

        // Create the Cart
        CartDTO cartDTO = cartMapper.toDto(cart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCartMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(cartDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCart() throws Exception {
        // Initialize the database
        cartRepository.saveAndFlush(cart);

        int databaseSizeBeforeDelete = cartRepository.findAll().size();

        // Delete the cart
        restCartMockMvc
            .perform(delete(ENTITY_API_URL_ID, cart.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
