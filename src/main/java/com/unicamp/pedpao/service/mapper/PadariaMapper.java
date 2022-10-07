package com.unicamp.pedpao.service.mapper;

import com.unicamp.pedpao.domain.Padaria;
import com.unicamp.pedpao.domain.User;
import com.unicamp.pedpao.service.dto.PadariaDTO;
import com.unicamp.pedpao.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Padaria} and its DTO {@link PadariaDTO}.
 */
@Mapper(componentModel = "spring")
public interface PadariaMapper extends EntityMapper<PadariaDTO, Padaria> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    PadariaDTO toDto(Padaria s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
