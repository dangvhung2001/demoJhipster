package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.OrderMain;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.service.dto.OrderMainDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderMain} and its DTO {@link OrderMainDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderMainMapper extends EntityMapper<OrderMainDTO, OrderMain> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userLogin")
    OrderMainDTO toDto(OrderMain s);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);
}
