package com.unicamp.pedpao.service.mapper;

import com.unicamp.pedpao.domain.Assinatura;
import com.unicamp.pedpao.domain.ItemsAssinatura;
import com.unicamp.pedpao.service.dto.AssinaturaDTO;
import com.unicamp.pedpao.service.dto.ItemsAssinaturaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemsAssinatura} and its DTO {@link ItemsAssinaturaDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemsAssinaturaMapper extends EntityMapper<ItemsAssinaturaDTO, ItemsAssinatura> {
    @Mapping(target = "assinatura", source = "assinatura", qualifiedByName = "assinaturaId")
    ItemsAssinaturaDTO toDto(ItemsAssinatura s);

    @Named("assinaturaId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AssinaturaDTO toDtoAssinaturaId(Assinatura assinatura);
}
