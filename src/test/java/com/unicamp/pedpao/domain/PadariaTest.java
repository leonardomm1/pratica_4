package com.unicamp.pedpao.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.unicamp.pedpao.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PadariaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Padaria.class);
        Padaria padaria1 = new Padaria();
        padaria1.setId(1L);
        Padaria padaria2 = new Padaria();
        padaria2.setId(padaria1.getId());
        assertThat(padaria1).isEqualTo(padaria2);
        padaria2.setId(2L);
        assertThat(padaria1).isNotEqualTo(padaria2);
        padaria1.setId(null);
        assertThat(padaria1).isNotEqualTo(padaria2);
    }
}
