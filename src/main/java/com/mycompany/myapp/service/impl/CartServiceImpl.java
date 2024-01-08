package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Cart;
import com.mycompany.myapp.domain.CartItem;
import com.mycompany.myapp.repository.CartRepository;
import com.mycompany.myapp.service.CartService;
import com.mycompany.myapp.service.dto.CartDTO;
import com.mycompany.myapp.service.mapper.CartMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Cart}.
 */
@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

    private final CartRepository cartRepository;

    private final CartMapper cartMapper;

    public CartServiceImpl(CartRepository cartRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public CartDTO save(CartDTO cartDTO) {
        log.debug("Request to save Cart : {}", cartDTO);
        Cart cart = cartMapper.toEntity(cartDTO);
        cart = cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    @Override
    public CartDTO update(CartDTO cartDTO) {
        log.debug("Request to update Cart : {}", cartDTO);
        Cart cart = cartMapper.toEntity(cartDTO);
        cart = cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    @Override
    public Optional<CartDTO> partialUpdate(CartDTO cartDTO) {
        log.debug("Request to partially update Cart : {}", cartDTO);

        return cartRepository
            .findById(cartDTO.getId())
            .map(existingCart -> {
                cartMapper.partialUpdate(existingCart, cartDTO);

                return existingCart;
            })
            .map(cartRepository::save)
            .map(cartMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartDTO> findAll() {
        log.debug("Request to get all Carts");
        return cartRepository.findAll().stream().map(cartMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    public Page<CartDTO> findAllWithEagerRelationships(Pageable pageable) {
        return cartRepository.findAllWithEagerRelationships(pageable).map(cartMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CartDTO> findOne(Long id) {
        log.debug("Request to get Cart : {}", id);
        return cartRepository.findOneWithEagerRelationships(id).map(cartMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cart : {}", id);
        cartRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CartDTO updateCartInfo(Long cartId) {
        // Tìm Cart từ ID
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
        // Tính toán thông tin từ các CartItem
        Set<CartItem> cartItems = cart.getCartItems();
        float amountOfCartNet = 0.0f;
        float amountOfCartGross = 0.0f;
        int quantity = 0;
        // Lặp qua từng CartItem để tính toán
        for (CartItem cartItem : cartItems) {
            amountOfCartNet += (cartItem.getPriceNet() * cartItem.getQuantity());
            amountOfCartGross += (cartItem.getPriceGross() * cartItem.getQuantity());
            quantity += cartItem.getQuantity();
        }
        cart.setAmountOfCartNet(amountOfCartNet);
        cart.setAmountOfCartGross(amountOfCartGross);
        cart.setQuantity(quantity);

        Cart updatedCart = cartRepository.save(cart);

        return cartMapper.toDto(updatedCart);
    }
}
