package com.unicamp.pedpao.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InformacoesAdicionaisMapperTest {

    private InformacoesAdicionaisMapper informacoesAdicionaisMapper;

    @BeforeEach
    public void setUp() {
        informacoesAdicionaisMapper = new InformacoesAdicionaisMapperImpl();
    }
}
