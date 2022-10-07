package com.unicamp.pedpao.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.unicamp.pedpao.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AssinaturaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Assinatura.class);
        Assinatura assinatura1 = new Assinatura();
        assinatura1.setId(1L);
        Assinatura assinatura2 = new Assinatura();
        assinatura2.setId(assinatura1.getId());
        assertThat(assinatura1).isEqualTo(assinatura2);
        assinatura2.setId(2L);
        assertThat(assinatura1).isNotEqualTo(assinatura2);
        assinatura1.setId(null);
        assertThat(assinatura1).isNotEqualTo(assinatura2);
    }
}
