package com.unicamp.pedpao.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.unicamp.pedpao.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PadariaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PadariaDTO.class);
        PadariaDTO padariaDTO1 = new PadariaDTO();
        padariaDTO1.setId(1L);
        PadariaDTO padariaDTO2 = new PadariaDTO();
        assertThat(padariaDTO1).isNotEqualTo(padariaDTO2);
        padariaDTO2.setId(padariaDTO1.getId());
        assertThat(padariaDTO1).isEqualTo(padariaDTO2);
        padariaDTO2.setId(2L);
        assertThat(padariaDTO1).isNotEqualTo(padariaDTO2);
        padariaDTO1.setId(null);
        assertThat(padariaDTO1).isNotEqualTo(padariaDTO2);
    }
}
