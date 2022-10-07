package com.unicamp.pedpao.repository;

import com.unicamp.pedpao.domain.Padaria;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Padaria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PadariaRepository extends JpaRepository<Padaria, Long> {
    @Query("select padaria from Padaria padaria where padaria.user.login = ?#{principal.username}")
    List<Padaria> findByUserIsCurrentUser();
}
