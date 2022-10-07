package com.unicamp.pedpao.service;

import com.unicamp.pedpao.service.dto.PadariaDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.unicamp.pedpao.domain.Padaria}.
 */
public interface PadariaService {
    /**
     * Save a padaria.
     *
     * @param padariaDTO the entity to save.
     * @return the persisted entity.
     */
    PadariaDTO save(PadariaDTO padariaDTO);

    /**
     * Updates a padaria.
     *
     * @param padariaDTO the entity to update.
     * @return the persisted entity.
     */
    PadariaDTO update(PadariaDTO padariaDTO);

    /**
     * Partially updates a padaria.
     *
     * @param padariaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PadariaDTO> partialUpdate(PadariaDTO padariaDTO);

    /**
     * Get all the padarias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PadariaDTO> findAll(Pageable pageable);

    /**
     * Get the "id" padaria.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PadariaDTO> findOne(Long id);

    /**
     * Delete the "id" padaria.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
