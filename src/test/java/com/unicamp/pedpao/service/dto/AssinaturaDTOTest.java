package com.unicamp.pedpao.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.unicamp.pedpao.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AssinaturaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AssinaturaDTO.class);
        AssinaturaDTO assinaturaDTO1 = new AssinaturaDTO();
        assinaturaDTO1.setId(1L);
        AssinaturaDTO assinaturaDTO2 = new AssinaturaDTO();
        assertThat(assinaturaDTO1).isNotEqualTo(assinaturaDTO2);
        assinaturaDTO2.setId(assinaturaDTO1.getId());
        assertThat(assinaturaDTO1).isEqualTo(assinaturaDTO2);
        assinaturaDTO2.setId(2L);
        assertThat(assinaturaDTO1).isNotEqualTo(assinaturaDTO2);
        assinaturaDTO1.setId(null);
        assertThat(assinaturaDTO1).isNotEqualTo(assinaturaDTO2);
    }
}
