package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Cart;
import com.mycompany.myapp.domain.OrderMain;
import com.mycompany.myapp.domain.ShipmentCart;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.service.dto.CartDTO;
import com.mycompany.myapp.service.dto.OrderMainDTO;
import com.mycompany.myapp.service.dto.ShipmentCartDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cart} and its DTO {@link CartDTO}.
 */
@Mapper(componentModel = "spring")
public interface CartMapper extends EntityMapper<CartDTO, Cart> {
    @Mapping(target = "shipmentCart", source = "shipmentCart", qualifiedByName = "shipmentCartId")
    @Mapping(target = "user", source = "user", qualifiedByName = "userLogin")
    @Mapping(target = "orderMain", source = "orderMain", qualifiedByName = "orderMainId")
    CartDTO toDto(Cart s);

    @Named("shipmentCartId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ShipmentCartDTO toDtoShipmentCartId(ShipmentCart shipmentCart);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);

    @Named("orderMainId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderMainDTO toDtoOrderMainId(OrderMain orderMain);
}
