package com.unicamp.pedpao.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PadariaMapperTest {

    private PadariaMapper padariaMapper;

    @BeforeEach
    public void setUp() {
        padariaMapper = new PadariaMapperImpl();
    }
}
