package com.unicamp.pedpao.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.unicamp.pedpao.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemsAssinaturaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemsAssinaturaDTO.class);
        ItemsAssinaturaDTO itemsAssinaturaDTO1 = new ItemsAssinaturaDTO();
        itemsAssinaturaDTO1.setId(1L);
        ItemsAssinaturaDTO itemsAssinaturaDTO2 = new ItemsAssinaturaDTO();
        assertThat(itemsAssinaturaDTO1).isNotEqualTo(itemsAssinaturaDTO2);
        itemsAssinaturaDTO2.setId(itemsAssinaturaDTO1.getId());
        assertThat(itemsAssinaturaDTO1).isEqualTo(itemsAssinaturaDTO2);
        itemsAssinaturaDTO2.setId(2L);
        assertThat(itemsAssinaturaDTO1).isNotEqualTo(itemsAssinaturaDTO2);
        itemsAssinaturaDTO1.setId(null);
        assertThat(itemsAssinaturaDTO1).isNotEqualTo(itemsAssinaturaDTO2);
    }
}
