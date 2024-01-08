package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.OrderMain;
import com.mycompany.myapp.domain.enumeration.OrderMainStatusEnum;
import com.mycompany.myapp.repository.OrderMainRepository;
import com.mycompany.myapp.service.OrderMainService;
import com.mycompany.myapp.service.dto.OrderMainDTO;
import com.mycompany.myapp.service.mapper.OrderMainMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link OrderMainResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class OrderMainResourceIT {

    private static final String DEFAULT_BUYER_LOGIN = "AAAAAAAAAA";
    private static final String UPDATED_BUYER_LOGIN = "BBBBBBBBBB";

    private static final String DEFAULT_BUYER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BUYER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BUYER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BUYER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BUYER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_BUYER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_BUYER_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_BUYER_PHONE = "BBBBBBBBBB";

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

    private static final OrderMainStatusEnum DEFAULT_ORDER_MAIN_STATUS = OrderMainStatusEnum.WaitingForBankTransfer;
    private static final OrderMainStatusEnum UPDATED_ORDER_MAIN_STATUS = OrderMainStatusEnum.PreparationForShipping;

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/order-mains";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OrderMainRepository orderMainRepository;

    @Mock
    private OrderMainRepository orderMainRepositoryMock;

    @Autowired
    private OrderMainMapper orderMainMapper;

    @Mock
    private OrderMainService orderMainServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderMainMockMvc;

    private OrderMain orderMain;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderMain createEntity(EntityManager em) {
        OrderMain orderMain = new OrderMain()
            .buyerLogin(DEFAULT_BUYER_LOGIN)
            .buyerFirstName(DEFAULT_BUYER_FIRST_NAME)
            .buyerLastName(DEFAULT_BUYER_LAST_NAME)
            .buyerEmail(DEFAULT_BUYER_EMAIL)
            .buyerPhone(DEFAULT_BUYER_PHONE)
            .amountOfCartNet(DEFAULT_AMOUNT_OF_CART_NET)
            .amountOfCartGross(DEFAULT_AMOUNT_OF_CART_GROSS)
            .amountOfShipmentNet(DEFAULT_AMOUNT_OF_SHIPMENT_NET)
            .amountOfShipmentGross(DEFAULT_AMOUNT_OF_SHIPMENT_GROSS)
            .amountOfOrderNet(DEFAULT_AMOUNT_OF_ORDER_NET)
            .amountOfOrderGross(DEFAULT_AMOUNT_OF_ORDER_GROSS)
            .orderMainStatus(DEFAULT_ORDER_MAIN_STATUS)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return orderMain;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderMain createUpdatedEntity(EntityManager em) {
        OrderMain orderMain = new OrderMain()
            .buyerLogin(UPDATED_BUYER_LOGIN)
            .buyerFirstName(UPDATED_BUYER_FIRST_NAME)
            .buyerLastName(UPDATED_BUYER_LAST_NAME)
            .buyerEmail(UPDATED_BUYER_EMAIL)
            .buyerPhone(UPDATED_BUYER_PHONE)
            .amountOfCartNet(UPDATED_AMOUNT_OF_CART_NET)
            .amountOfCartGross(UPDATED_AMOUNT_OF_CART_GROSS)
            .amountOfShipmentNet(UPDATED_AMOUNT_OF_SHIPMENT_NET)
            .amountOfShipmentGross(UPDATED_AMOUNT_OF_SHIPMENT_GROSS)
            .amountOfOrderNet(UPDATED_AMOUNT_OF_ORDER_NET)
            .amountOfOrderGross(UPDATED_AMOUNT_OF_ORDER_GROSS)
            .orderMainStatus(UPDATED_ORDER_MAIN_STATUS)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);
        return orderMain;
    }

    @BeforeEach
    public void initTest() {
        orderMain = createEntity(em);
    }

    @Test
    @Transactional
    void createOrderMain() throws Exception {
        int databaseSizeBeforeCreate = orderMainRepository.findAll().size();
        // Create the OrderMain
        OrderMainDTO orderMainDTO = orderMainMapper.toDto(orderMain);
        restOrderMainMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderMainDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderMain in the database
        List<OrderMain> orderMainList = orderMainRepository.findAll();
        assertThat(orderMainList).hasSize(databaseSizeBeforeCreate + 1);
        OrderMain testOrderMain = orderMainList.get(orderMainList.size() - 1);
        assertThat(testOrderMain.getBuyerLogin()).isEqualTo(DEFAULT_BUYER_LOGIN);
        assertThat(testOrderMain.getBuyerFirstName()).isEqualTo(DEFAULT_BUYER_FIRST_NAME);
        assertThat(testOrderMain.getBuyerLastName()).isEqualTo(DEFAULT_BUYER_LAST_NAME);
        assertThat(testOrderMain.getBuyerEmail()).isEqualTo(DEFAULT_BUYER_EMAIL);
        assertThat(testOrderMain.getBuyerPhone()).isEqualTo(DEFAULT_BUYER_PHONE);
        assertThat(testOrderMain.getAmountOfCartNet()).isEqualTo(DEFAULT_AMOUNT_OF_CART_NET);
        assertThat(testOrderMain.getAmountOfCartGross()).isEqualTo(DEFAULT_AMOUNT_OF_CART_GROSS);
        assertThat(testOrderMain.getAmountOfShipmentNet()).isEqualTo(DEFAULT_AMOUNT_OF_SHIPMENT_NET);
        assertThat(testOrderMain.getAmountOfShipmentGross()).isEqualTo(DEFAULT_AMOUNT_OF_SHIPMENT_GROSS);
        assertThat(testOrderMain.getAmountOfOrderNet()).isEqualTo(DEFAULT_AMOUNT_OF_ORDER_NET);
        assertThat(testOrderMain.getAmountOfOrderGross()).isEqualTo(DEFAULT_AMOUNT_OF_ORDER_GROSS);
        assertThat(testOrderMain.getOrderMainStatus()).isEqualTo(DEFAULT_ORDER_MAIN_STATUS);
        assertThat(testOrderMain.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testOrderMain.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createOrderMainWithExistingId() throws Exception {
        // Create the OrderMain with an existing ID
        orderMain.setId(1L);
        OrderMainDTO orderMainDTO = orderMainMapper.toDto(orderMain);

        int databaseSizeBeforeCreate = orderMainRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderMainMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderMainDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderMain in the database
        List<OrderMain> orderMainList = orderMainRepository.findAll();
        assertThat(orderMainList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOrderMains() throws Exception {
        // Initialize the database
        orderMainRepository.saveAndFlush(orderMain);

        // Get all the orderMainList
        restOrderMainMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderMain.getId().intValue())))
            .andExpect(jsonPath("$.[*].buyerLogin").value(hasItem(DEFAULT_BUYER_LOGIN)))
            .andExpect(jsonPath("$.[*].buyerFirstName").value(hasItem(DEFAULT_BUYER_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].buyerLastName").value(hasItem(DEFAULT_BUYER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].buyerEmail").value(hasItem(DEFAULT_BUYER_EMAIL)))
            .andExpect(jsonPath("$.[*].buyerPhone").value(hasItem(DEFAULT_BUYER_PHONE)))
            .andExpect(jsonPath("$.[*].amountOfCartNet").value(hasItem(DEFAULT_AMOUNT_OF_CART_NET.doubleValue())))
            .andExpect(jsonPath("$.[*].amountOfCartGross").value(hasItem(DEFAULT_AMOUNT_OF_CART_GROSS.doubleValue())))
            .andExpect(jsonPath("$.[*].amountOfShipmentNet").value(hasItem(DEFAULT_AMOUNT_OF_SHIPMENT_NET.doubleValue())))
            .andExpect(jsonPath("$.[*].amountOfShipmentGross").value(hasItem(DEFAULT_AMOUNT_OF_SHIPMENT_GROSS.doubleValue())))
            .andExpect(jsonPath("$.[*].amountOfOrderNet").value(hasItem(DEFAULT_AMOUNT_OF_ORDER_NET.doubleValue())))
            .andExpect(jsonPath("$.[*].amountOfOrderGross").value(hasItem(DEFAULT_AMOUNT_OF_ORDER_GROSS.doubleValue())))
            .andExpect(jsonPath("$.[*].orderMainStatus").value(hasItem(DEFAULT_ORDER_MAIN_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllOrderMainsWithEagerRelationshipsIsEnabled() throws Exception {
        when(orderMainServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOrderMainMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(orderMainServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllOrderMainsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(orderMainServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOrderMainMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(orderMainRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getOrderMain() throws Exception {
        // Initialize the database
        orderMainRepository.saveAndFlush(orderMain);

        // Get the orderMain
        restOrderMainMockMvc
            .perform(get(ENTITY_API_URL_ID, orderMain.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderMain.getId().intValue()))
            .andExpect(jsonPath("$.buyerLogin").value(DEFAULT_BUYER_LOGIN))
            .andExpect(jsonPath("$.buyerFirstName").value(DEFAULT_BUYER_FIRST_NAME))
            .andExpect(jsonPath("$.buyerLastName").value(DEFAULT_BUYER_LAST_NAME))
            .andExpect(jsonPath("$.buyerEmail").value(DEFAULT_BUYER_EMAIL))
            .andExpect(jsonPath("$.buyerPhone").value(DEFAULT_BUYER_PHONE))
            .andExpect(jsonPath("$.amountOfCartNet").value(DEFAULT_AMOUNT_OF_CART_NET.doubleValue()))
            .andExpect(jsonPath("$.amountOfCartGross").value(DEFAULT_AMOUNT_OF_CART_GROSS.doubleValue()))
            .andExpect(jsonPath("$.amountOfShipmentNet").value(DEFAULT_AMOUNT_OF_SHIPMENT_NET.doubleValue()))
            .andExpect(jsonPath("$.amountOfShipmentGross").value(DEFAULT_AMOUNT_OF_SHIPMENT_GROSS.doubleValue()))
            .andExpect(jsonPath("$.amountOfOrderNet").value(DEFAULT_AMOUNT_OF_ORDER_NET.doubleValue()))
            .andExpect(jsonPath("$.amountOfOrderGross").value(DEFAULT_AMOUNT_OF_ORDER_GROSS.doubleValue()))
            .andExpect(jsonPath("$.orderMainStatus").value(DEFAULT_ORDER_MAIN_STATUS.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingOrderMain() throws Exception {
        // Get the orderMain
        restOrderMainMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOrderMain() throws Exception {
        // Initialize the database
        orderMainRepository.saveAndFlush(orderMain);

        int databaseSizeBeforeUpdate = orderMainRepository.findAll().size();

        // Update the orderMain
        OrderMain updatedOrderMain = orderMainRepository.findById(orderMain.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOrderMain are not directly saved in db
        em.detach(updatedOrderMain);
        updatedOrderMain
            .buyerLogin(UPDATED_BUYER_LOGIN)
            .buyerFirstName(UPDATED_BUYER_FIRST_NAME)
            .buyerLastName(UPDATED_BUYER_LAST_NAME)
            .buyerEmail(UPDATED_BUYER_EMAIL)
            .buyerPhone(UPDATED_BUYER_PHONE)
            .amountOfCartNet(UPDATED_AMOUNT_OF_CART_NET)
            .amountOfCartGross(UPDATED_AMOUNT_OF_CART_GROSS)
            .amountOfShipmentNet(UPDATED_AMOUNT_OF_SHIPMENT_NET)
            .amountOfShipmentGross(UPDATED_AMOUNT_OF_SHIPMENT_GROSS)
            .amountOfOrderNet(UPDATED_AMOUNT_OF_ORDER_NET)
            .amountOfOrderGross(UPDATED_AMOUNT_OF_ORDER_GROSS)
            .orderMainStatus(UPDATED_ORDER_MAIN_STATUS)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);
        OrderMainDTO orderMainDTO = orderMainMapper.toDto(updatedOrderMain);

        restOrderMainMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orderMainDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orderMainDTO))
            )
            .andExpect(status().isOk());

        // Validate the OrderMain in the database
        List<OrderMain> orderMainList = orderMainRepository.findAll();
        assertThat(orderMainList).hasSize(databaseSizeBeforeUpdate);
        OrderMain testOrderMain = orderMainList.get(orderMainList.size() - 1);
        assertThat(testOrderMain.getBuyerLogin()).isEqualTo(UPDATED_BUYER_LOGIN);
        assertThat(testOrderMain.getBuyerFirstName()).isEqualTo(UPDATED_BUYER_FIRST_NAME);
        assertThat(testOrderMain.getBuyerLastName()).isEqualTo(UPDATED_BUYER_LAST_NAME);
        assertThat(testOrderMain.getBuyerEmail()).isEqualTo(UPDATED_BUYER_EMAIL);
        assertThat(testOrderMain.getBuyerPhone()).isEqualTo(UPDATED_BUYER_PHONE);
        assertThat(testOrderMain.getAmountOfCartNet()).isEqualTo(UPDATED_AMOUNT_OF_CART_NET);
        assertThat(testOrderMain.getAmountOfCartGross()).isEqualTo(UPDATED_AMOUNT_OF_CART_GROSS);
        assertThat(testOrderMain.getAmountOfShipmentNet()).isEqualTo(UPDATED_AMOUNT_OF_SHIPMENT_NET);
        assertThat(testOrderMain.getAmountOfShipmentGross()).isEqualTo(UPDATED_AMOUNT_OF_SHIPMENT_GROSS);
        assertThat(testOrderMain.getAmountOfOrderNet()).isEqualTo(UPDATED_AMOUNT_OF_ORDER_NET);
        assertThat(testOrderMain.getAmountOfOrderGross()).isEqualTo(UPDATED_AMOUNT_OF_ORDER_GROSS);
        assertThat(testOrderMain.getOrderMainStatus()).isEqualTo(UPDATED_ORDER_MAIN_STATUS);
        assertThat(testOrderMain.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testOrderMain.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingOrderMain() throws Exception {
        int databaseSizeBeforeUpdate = orderMainRepository.findAll().size();
        orderMain.setId(longCount.incrementAndGet());

        // Create the OrderMain
        OrderMainDTO orderMainDTO = orderMainMapper.toDto(orderMain);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderMainMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orderMainDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orderMainDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderMain in the database
        List<OrderMain> orderMainList = orderMainRepository.findAll();
        assertThat(orderMainList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOrderMain() throws Exception {
        int databaseSizeBeforeUpdate = orderMainRepository.findAll().size();
        orderMain.setId(longCount.incrementAndGet());

        // Create the OrderMain
        OrderMainDTO orderMainDTO = orderMainMapper.toDto(orderMain);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderMainMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orderMainDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderMain in the database
        List<OrderMain> orderMainList = orderMainRepository.findAll();
        assertThat(orderMainList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOrderMain() throws Exception {
        int databaseSizeBeforeUpdate = orderMainRepository.findAll().size();
        orderMain.setId(longCount.incrementAndGet());

        // Create the OrderMain
        OrderMainDTO orderMainDTO = orderMainMapper.toDto(orderMain);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderMainMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderMainDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderMain in the database
        List<OrderMain> orderMainList = orderMainRepository.findAll();
        assertThat(orderMainList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOrderMainWithPatch() throws Exception {
        // Initialize the database
        orderMainRepository.saveAndFlush(orderMain);

        int databaseSizeBeforeUpdate = orderMainRepository.findAll().size();

        // Update the orderMain using partial update
        OrderMain partialUpdatedOrderMain = new OrderMain();
        partialUpdatedOrderMain.setId(orderMain.getId());

        partialUpdatedOrderMain
            .buyerFirstName(UPDATED_BUYER_FIRST_NAME)
            .buyerPhone(UPDATED_BUYER_PHONE)
            .amountOfCartNet(UPDATED_AMOUNT_OF_CART_NET)
            .amountOfCartGross(UPDATED_AMOUNT_OF_CART_GROSS)
            .amountOfShipmentGross(UPDATED_AMOUNT_OF_SHIPMENT_GROSS)
            .amountOfOrderNet(UPDATED_AMOUNT_OF_ORDER_NET)
            .updateTime(UPDATED_UPDATE_TIME);

        restOrderMainMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderMain.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrderMain))
            )
            .andExpect(status().isOk());

        // Validate the OrderMain in the database
        List<OrderMain> orderMainList = orderMainRepository.findAll();
        assertThat(orderMainList).hasSize(databaseSizeBeforeUpdate);
        OrderMain testOrderMain = orderMainList.get(orderMainList.size() - 1);
        assertThat(testOrderMain.getBuyerLogin()).isEqualTo(DEFAULT_BUYER_LOGIN);
        assertThat(testOrderMain.getBuyerFirstName()).isEqualTo(UPDATED_BUYER_FIRST_NAME);
        assertThat(testOrderMain.getBuyerLastName()).isEqualTo(DEFAULT_BUYER_LAST_NAME);
        assertThat(testOrderMain.getBuyerEmail()).isEqualTo(DEFAULT_BUYER_EMAIL);
        assertThat(testOrderMain.getBuyerPhone()).isEqualTo(UPDATED_BUYER_PHONE);
        assertThat(testOrderMain.getAmountOfCartNet()).isEqualTo(UPDATED_AMOUNT_OF_CART_NET);
        assertThat(testOrderMain.getAmountOfCartGross()).isEqualTo(UPDATED_AMOUNT_OF_CART_GROSS);
        assertThat(testOrderMain.getAmountOfShipmentNet()).isEqualTo(DEFAULT_AMOUNT_OF_SHIPMENT_NET);
        assertThat(testOrderMain.getAmountOfShipmentGross()).isEqualTo(UPDATED_AMOUNT_OF_SHIPMENT_GROSS);
        assertThat(testOrderMain.getAmountOfOrderNet()).isEqualTo(UPDATED_AMOUNT_OF_ORDER_NET);
        assertThat(testOrderMain.getAmountOfOrderGross()).isEqualTo(DEFAULT_AMOUNT_OF_ORDER_GROSS);
        assertThat(testOrderMain.getOrderMainStatus()).isEqualTo(DEFAULT_ORDER_MAIN_STATUS);
        assertThat(testOrderMain.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testOrderMain.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateOrderMainWithPatch() throws Exception {
        // Initialize the database
        orderMainRepository.saveAndFlush(orderMain);

        int databaseSizeBeforeUpdate = orderMainRepository.findAll().size();

        // Update the orderMain using partial update
        OrderMain partialUpdatedOrderMain = new OrderMain();
        partialUpdatedOrderMain.setId(orderMain.getId());

        partialUpdatedOrderMain
            .buyerLogin(UPDATED_BUYER_LOGIN)
            .buyerFirstName(UPDATED_BUYER_FIRST_NAME)
            .buyerLastName(UPDATED_BUYER_LAST_NAME)
            .buyerEmail(UPDATED_BUYER_EMAIL)
            .buyerPhone(UPDATED_BUYER_PHONE)
            .amountOfCartNet(UPDATED_AMOUNT_OF_CART_NET)
            .amountOfCartGross(UPDATED_AMOUNT_OF_CART_GROSS)
            .amountOfShipmentNet(UPDATED_AMOUNT_OF_SHIPMENT_NET)
            .amountOfShipmentGross(UPDATED_AMOUNT_OF_SHIPMENT_GROSS)
            .amountOfOrderNet(UPDATED_AMOUNT_OF_ORDER_NET)
            .amountOfOrderGross(UPDATED_AMOUNT_OF_ORDER_GROSS)
            .orderMainStatus(UPDATED_ORDER_MAIN_STATUS)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restOrderMainMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderMain.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrderMain))
            )
            .andExpect(status().isOk());

        // Validate the OrderMain in the database
        List<OrderMain> orderMainList = orderMainRepository.findAll();
        assertThat(orderMainList).hasSize(databaseSizeBeforeUpdate);
        OrderMain testOrderMain = orderMainList.get(orderMainList.size() - 1);
        assertThat(testOrderMain.getBuyerLogin()).isEqualTo(UPDATED_BUYER_LOGIN);
        assertThat(testOrderMain.getBuyerFirstName()).isEqualTo(UPDATED_BUYER_FIRST_NAME);
        assertThat(testOrderMain.getBuyerLastName()).isEqualTo(UPDATED_BUYER_LAST_NAME);
        assertThat(testOrderMain.getBuyerEmail()).isEqualTo(UPDATED_BUYER_EMAIL);
        assertThat(testOrderMain.getBuyerPhone()).isEqualTo(UPDATED_BUYER_PHONE);
        assertThat(testOrderMain.getAmountOfCartNet()).isEqualTo(UPDATED_AMOUNT_OF_CART_NET);
        assertThat(testOrderMain.getAmountOfCartGross()).isEqualTo(UPDATED_AMOUNT_OF_CART_GROSS);
        assertThat(testOrderMain.getAmountOfShipmentNet()).isEqualTo(UPDATED_AMOUNT_OF_SHIPMENT_NET);
        assertThat(testOrderMain.getAmountOfShipmentGross()).isEqualTo(UPDATED_AMOUNT_OF_SHIPMENT_GROSS);
        assertThat(testOrderMain.getAmountOfOrderNet()).isEqualTo(UPDATED_AMOUNT_OF_ORDER_NET);
        assertThat(testOrderMain.getAmountOfOrderGross()).isEqualTo(UPDATED_AMOUNT_OF_ORDER_GROSS);
        assertThat(testOrderMain.getOrderMainStatus()).isEqualTo(UPDATED_ORDER_MAIN_STATUS);
        assertThat(testOrderMain.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testOrderMain.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingOrderMain() throws Exception {
        int databaseSizeBeforeUpdate = orderMainRepository.findAll().size();
        orderMain.setId(longCount.incrementAndGet());

        // Create the OrderMain
        OrderMainDTO orderMainDTO = orderMainMapper.toDto(orderMain);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderMainMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, orderMainDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(orderMainDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderMain in the database
        List<OrderMain> orderMainList = orderMainRepository.findAll();
        assertThat(orderMainList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOrderMain() throws Exception {
        int databaseSizeBeforeUpdate = orderMainRepository.findAll().size();
        orderMain.setId(longCount.incrementAndGet());

        // Create the OrderMain
        OrderMainDTO orderMainDTO = orderMainMapper.toDto(orderMain);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderMainMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(orderMainDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderMain in the database
        List<OrderMain> orderMainList = orderMainRepository.findAll();
        assertThat(orderMainList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOrderMain() throws Exception {
        int databaseSizeBeforeUpdate = orderMainRepository.findAll().size();
        orderMain.setId(longCount.incrementAndGet());

        // Create the OrderMain
        OrderMainDTO orderMainDTO = orderMainMapper.toDto(orderMain);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderMainMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(orderMainDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderMain in the database
        List<OrderMain> orderMainList = orderMainRepository.findAll();
        assertThat(orderMainList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOrderMain() throws Exception {
        // Initialize the database
        orderMainRepository.saveAndFlush(orderMain);

        int databaseSizeBeforeDelete = orderMainRepository.findAll().size();

        // Delete the orderMain
        restOrderMainMockMvc
            .perform(delete(ENTITY_API_URL_ID, orderMain.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderMain> orderMainList = orderMainRepository.findAll();
        assertThat(orderMainList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
