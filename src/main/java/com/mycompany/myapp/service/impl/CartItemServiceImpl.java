package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Cart;
import com.mycompany.myapp.domain.CartItem;
import com.mycompany.myapp.domain.Product;
import com.mycompany.myapp.repository.CartItemRepository;
import com.mycompany.myapp.repository.CartRepository;
import com.mycompany.myapp.repository.ProductRepository;
import com.mycompany.myapp.service.CartItemService;
import com.mycompany.myapp.service.dto.CartDTO;
import com.mycompany.myapp.service.dto.CartItemDTO;
import com.mycompany.myapp.service.mapper.CartItemMapper;
import com.mycompany.myapp.service.mapper.CartMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.CartItem}.
 */
@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

    private final Logger log = LoggerFactory.getLogger(CartItemServiceImpl.class);

    private final CartItemRepository cartItemRepository;

    private final CartItemMapper cartItemMapper;

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final CartMapper cartMapper;

    public CartItemServiceImpl(
        CartItemRepository cartItemRepository,
        CartItemMapper cartItemMapper,
        CartRepository cartRepository,
        ProductRepository productRepository,
        CartMapper cartMapper
    ) {
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public CartItemDTO save(CartItemDTO cartItemDTO) {
        log.debug("Request to save CartItem : {}", cartItemDTO);
        CartItem cartItem = cartItemMapper.toEntity(cartItemDTO);
        cartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    public CartItemDTO update(CartItemDTO cartItemDTO) {
        log.debug("Request to update CartItem : {}", cartItemDTO);
        CartItem cartItem = cartItemMapper.toEntity(cartItemDTO);
        cartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    public Optional<CartItemDTO> partialUpdate(CartItemDTO cartItemDTO) {
        log.debug("Request to partially update CartItem : {}", cartItemDTO);

        return cartItemRepository
            .findById(cartItemDTO.getId())
            .map(existingCartItem -> {
                cartItemMapper.partialUpdate(existingCartItem, cartItemDTO);

                return existingCartItem;
            })
            .map(cartItemRepository::save)
            .map(cartItemMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItemDTO> findAll() {
        log.debug("Request to get all CartItems");
        return cartItemRepository.findAll().stream().map(cartItemMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CartItemDTO> findOne(Long id) {
        log.debug("Request to get CartItem : {}", id);
        return cartItemRepository.findById(id).map(cartItemMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CartItem : {}", id);
        cartItemRepository.deleteById(id);
    }

    @Override
    public CartDTO save(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        if (product.getQuantity() >= quantity) {
            CartItem existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);
            if (existingCartItem != null) {
                int newQuantity = existingCartItem.getQuantity() + quantity;
                float newPriceNet = existingCartItem.getPriceNet() * quantity;
                float newPriceGross = existingCartItem.getPriceGross() * quantity;
                existingCartItem.setQuantity(newQuantity);
                existingCartItem.setTotalPriceNet(newPriceNet);
                existingCartItem.setTotalPriceGross(newPriceGross);
                cartItemRepository.save(existingCartItem);
            } else {
                CartItem newCartItem = new CartItem();
                newCartItem.setCart(cart);
                newCartItem.setProduct(product);
                newCartItem.setQuantity(quantity);
                newCartItem.setPriceGross(product.getPriceGross());
                newCartItem.setPriceNet(product.getPriceNet());
                newCartItem.setVat(product.getVat());
                newCartItem.setDescription(product.getDescription());
                newCartItem.setTotalPriceNet(product.getPriceNet() * quantity);
                newCartItem.setTotalPriceGross(product.getPriceGross() * quantity);
                cartItemRepository.save(newCartItem);
                cart.getCartItems().add(newCartItem);
            }
            int newProductQuantity = product.getQuantity() - quantity;
            product.setQuantity(newProductQuantity);
            productRepository.save(product);
            Cart updatedCart = cartRepository.save(cart);
            return cartMapper.toDto(updatedCart);
        } else {
            //            throw new BadRequestAlertException("Invalid", ENTITY_NAME, "no more Products can add");
            throw new IllegalArgumentException("Số lượng sản phẩm không đủ");
        }
    }
}
