package com.unicamp.pedpao.service.mapper;

import com.unicamp.pedpao.domain.Assinatura;
import com.unicamp.pedpao.domain.Padaria;
import com.unicamp.pedpao.domain.User;
import com.unicamp.pedpao.service.dto.AssinaturaDTO;
import com.unicamp.pedpao.service.dto.PadariaDTO;
import com.unicamp.pedpao.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Assinatura} and its DTO {@link AssinaturaDTO}.
 */
@Mapper(componentModel = "spring")
public interface AssinaturaMapper extends EntityMapper<AssinaturaDTO, Assinatura> {
    @Mapping(target = "padaria", source = "padaria", qualifiedByName = "padariaId")
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    AssinaturaDTO toDto(Assinatura s);

    @Named("padariaId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PadariaDTO toDtoPadariaId(Padaria padaria);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
