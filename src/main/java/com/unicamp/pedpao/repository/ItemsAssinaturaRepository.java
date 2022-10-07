package com.unicamp.pedpao.repository;

import com.unicamp.pedpao.domain.ItemsAssinatura;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemsAssinatura entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemsAssinaturaRepository extends JpaRepository<ItemsAssinatura, Long> {}
