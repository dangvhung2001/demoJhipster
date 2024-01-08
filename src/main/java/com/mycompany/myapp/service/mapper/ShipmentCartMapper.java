package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.ShipmentCart;
import com.mycompany.myapp.service.dto.ShipmentCartDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShipmentCart} and its DTO {@link ShipmentCartDTO}.
 */
@Mapper(componentModel = "spring")
public interface ShipmentCartMapper extends EntityMapper<ShipmentCartDTO, ShipmentCart> {}
