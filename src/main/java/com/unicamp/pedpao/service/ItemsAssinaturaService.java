package com.unicamp.pedpao.service;

import com.unicamp.pedpao.service.dto.ItemsAssinaturaDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.unicamp.pedpao.domain.ItemsAssinatura}.
 */
public interface ItemsAssinaturaService {
    /**
     * Save a itemsAssinatura.
     *
     * @param itemsAssinaturaDTO the entity to save.
     * @return the persisted entity.
     */
    ItemsAssinaturaDTO save(ItemsAssinaturaDTO itemsAssinaturaDTO);

    /**
     * Updates a itemsAssinatura.
     *
     * @param itemsAssinaturaDTO the entity to update.
     * @return the persisted entity.
     */
    ItemsAssinaturaDTO update(ItemsAssinaturaDTO itemsAssinaturaDTO);

    /**
     * Partially updates a itemsAssinatura.
     *
     * @param itemsAssinaturaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemsAssinaturaDTO> partialUpdate(ItemsAssinaturaDTO itemsAssinaturaDTO);

    /**
     * Get all the itemsAssinaturas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemsAssinaturaDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemsAssinatura.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemsAssinaturaDTO> findOne(Long id);

    /**
     * Delete the "id" itemsAssinatura.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
