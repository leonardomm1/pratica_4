package com.unicamp.pedpao.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemsAssinaturaMapperTest {

    private ItemsAssinaturaMapper itemsAssinaturaMapper;

    @BeforeEach
    public void setUp() {
        itemsAssinaturaMapper = new ItemsAssinaturaMapperImpl();
    }
}
