package com.unicamp.pedpao.service;

import com.unicamp.pedpao.service.dto.AssinaturaDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.unicamp.pedpao.domain.Assinatura}.
 */
public interface AssinaturaService {
    /**
     * Save a assinatura.
     *
     * @param assinaturaDTO the entity to save.
     * @return the persisted entity.
     */
    AssinaturaDTO save(AssinaturaDTO assinaturaDTO);

    /**
     * Updates a assinatura.
     *
     * @param assinaturaDTO the entity to update.
     * @return the persisted entity.
     */
    AssinaturaDTO update(AssinaturaDTO assinaturaDTO);

    /**
     * Partially updates a assinatura.
     *
     * @param assinaturaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AssinaturaDTO> partialUpdate(AssinaturaDTO assinaturaDTO);

    /**
     * Get all the assinaturas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AssinaturaDTO> findAll(Pageable pageable);

    /**
     * Get the "id" assinatura.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AssinaturaDTO> findOne(Long id);

    /**
     * Delete the "id" assinatura.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
