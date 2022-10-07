package com.unicamp.pedpao.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AssinaturaMapperTest {

    private AssinaturaMapper assinaturaMapper;

    @BeforeEach
    public void setUp() {
        assinaturaMapper = new AssinaturaMapperImpl();
    }
}
