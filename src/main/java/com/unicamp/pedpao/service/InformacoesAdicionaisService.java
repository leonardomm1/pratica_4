package com.unicamp.pedpao.service;

import com.unicamp.pedpao.service.dto.InformacoesAdicionaisDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.unicamp.pedpao.domain.InformacoesAdicionais}.
 */
public interface InformacoesAdicionaisService {
    /**
     * Save a informacoesAdicionais.
     *
     * @param informacoesAdicionaisDTO the entity to save.
     * @return the persisted entity.
     */
    InformacoesAdicionaisDTO save(InformacoesAdicionaisDTO informacoesAdicionaisDTO);

    /**
     * Updates a informacoesAdicionais.
     *
     * @param informacoesAdicionaisDTO the entity to update.
     * @return the persisted entity.
     */
    InformacoesAdicionaisDTO update(InformacoesAdicionaisDTO informacoesAdicionaisDTO);

    /**
     * Partially updates a informacoesAdicionais.
     *
     * @param informacoesAdicionaisDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InformacoesAdicionaisDTO> partialUpdate(InformacoesAdicionaisDTO informacoesAdicionaisDTO);

    /**
     * Get all the informacoesAdicionais.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InformacoesAdicionaisDTO> findAll(Pageable pageable);

    /**
     * Get the "id" informacoesAdicionais.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InformacoesAdicionaisDTO> findOne(Long id);

    /**
     * Delete the "id" informacoesAdicionais.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
