package com.unicamp.pedpao.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.unicamp.pedpao.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemsAssinaturaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemsAssinatura.class);
        ItemsAssinatura itemsAssinatura1 = new ItemsAssinatura();
        itemsAssinatura1.setId(1L);
        ItemsAssinatura itemsAssinatura2 = new ItemsAssinatura();
        itemsAssinatura2.setId(itemsAssinatura1.getId());
        assertThat(itemsAssinatura1).isEqualTo(itemsAssinatura2);
        itemsAssinatura2.setId(2L);
        assertThat(itemsAssinatura1).isNotEqualTo(itemsAssinatura2);
        itemsAssinatura1.setId(null);
        assertThat(itemsAssinatura1).isNotEqualTo(itemsAssinatura2);
    }
}
