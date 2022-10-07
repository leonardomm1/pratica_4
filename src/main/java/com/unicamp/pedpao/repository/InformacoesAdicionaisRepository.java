package com.unicamp.pedpao.repository;

import com.unicamp.pedpao.domain.InformacoesAdicionais;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InformacoesAdicionais entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InformacoesAdicionaisRepository extends JpaRepository<InformacoesAdicionais, Long> {
    @Query(
        "select informacoesAdicionais from InformacoesAdicionais informacoesAdicionais where informacoesAdicionais.user.login = ?#{principal.username}"
    )
    List<InformacoesAdicionais> findByUserIsCurrentUser();
}
