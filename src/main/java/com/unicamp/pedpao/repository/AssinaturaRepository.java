package com.unicamp.pedpao.repository;

import com.unicamp.pedpao.domain.Assinatura;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Assinatura entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    @Query("select assinatura from Assinatura assinatura where assinatura.user.login = ?#{principal.username}")
    List<Assinatura> findByUserIsCurrentUser();
}
