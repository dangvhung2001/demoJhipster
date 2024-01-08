package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ShipmentCartDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ShipmentCart}.
 */
public interface ShipmentCartService {
    /**
     * Save a shipmentCart.
     *
     * @param shipmentCartDTO the entity to save.
     * @return the persisted entity.
     */
    ShipmentCartDTO save(ShipmentCartDTO shipmentCartDTO);

    /**
     * Updates a shipmentCart.
     *
     * @param shipmentCartDTO the entity to update.
     * @return the persisted entity.
     */
    ShipmentCartDTO update(ShipmentCartDTO shipmentCartDTO);

    /**
     * Partially updates a shipmentCart.
     *
     * @param shipmentCartDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ShipmentCartDTO> partialUpdate(ShipmentCartDTO shipmentCartDTO);

    /**
     * Get all the shipmentCarts.
     *
     * @return the list of entities.
     */
    List<ShipmentCartDTO> findAll();

    /**
     * Get all the ShipmentCartDTO where Cart is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<ShipmentCartDTO> findAllWhereCartIsNull();

    /**
     * Get the "id" shipmentCart.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ShipmentCartDTO> findOne(Long id);

    /**
     * Delete the "id" shipmentCart.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
