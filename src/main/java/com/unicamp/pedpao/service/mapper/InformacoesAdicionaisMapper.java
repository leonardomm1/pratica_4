package com.unicamp.pedpao.service.mapper;

import com.unicamp.pedpao.domain.InformacoesAdicionais;
import com.unicamp.pedpao.domain.User;
import com.unicamp.pedpao.service.dto.InformacoesAdicionaisDTO;
import com.unicamp.pedpao.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InformacoesAdicionais} and its DTO {@link InformacoesAdicionaisDTO}.
 */
@Mapper(componentModel = "spring")
public interface InformacoesAdicionaisMapper extends EntityMapper<InformacoesAdicionaisDTO, InformacoesAdicionais> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    InformacoesAdicionaisDTO toDto(InformacoesAdicionais s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
