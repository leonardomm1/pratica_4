package com.unicamp.pedpao.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.unicamp.pedpao.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InformacoesAdicionaisTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InformacoesAdicionais.class);
        InformacoesAdicionais informacoesAdicionais1 = new InformacoesAdicionais();
        informacoesAdicionais1.setId(1L);
        InformacoesAdicionais informacoesAdicionais2 = new InformacoesAdicionais();
        informacoesAdicionais2.setId(informacoesAdicionais1.getId());
        assertThat(informacoesAdicionais1).isEqualTo(informacoesAdicionais2);
        informacoesAdicionais2.setId(2L);
        assertThat(informacoesAdicionais1).isNotEqualTo(informacoesAdicionais2);
        informacoesAdicionais1.setId(null);
        assertThat(informacoesAdicionais1).isNotEqualTo(informacoesAdicionais2);
    }
}
