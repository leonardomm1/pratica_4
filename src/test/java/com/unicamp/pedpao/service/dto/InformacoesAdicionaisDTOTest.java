package com.unicamp.pedpao.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.unicamp.pedpao.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InformacoesAdicionaisDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InformacoesAdicionaisDTO.class);
        InformacoesAdicionaisDTO informacoesAdicionaisDTO1 = new InformacoesAdicionaisDTO();
        informacoesAdicionaisDTO1.setId(1L);
        InformacoesAdicionaisDTO informacoesAdicionaisDTO2 = new InformacoesAdicionaisDTO();
        assertThat(informacoesAdicionaisDTO1).isNotEqualTo(informacoesAdicionaisDTO2);
        informacoesAdicionaisDTO2.setId(informacoesAdicionaisDTO1.getId());
        assertThat(informacoesAdicionaisDTO1).isEqualTo(informacoesAdicionaisDTO2);
        informacoesAdicionaisDTO2.setId(2L);
        assertThat(informacoesAdicionaisDTO1).isNotEqualTo(informacoesAdicionaisDTO2);
        informacoesAdicionaisDTO1.setId(null);
        assertThat(informacoesAdicionaisDTO1).isNotEqualTo(informacoesAdicionaisDTO2);
    }
}
