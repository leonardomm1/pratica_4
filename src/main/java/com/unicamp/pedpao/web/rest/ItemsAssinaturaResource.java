package com.unicamp.pedpao.web.rest;

import com.unicamp.pedpao.repository.ItemsAssinaturaRepository;
import com.unicamp.pedpao.service.ItemsAssinaturaService;
import com.unicamp.pedpao.service.dto.ItemsAssinaturaDTO;
import com.unicamp.pedpao.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.unicamp.pedpao.domain.ItemsAssinatura}.
 */
@RestController
@RequestMapping("/api")
public class ItemsAssinaturaResource {

    private final Logger log = LoggerFactory.getLogger(ItemsAssinaturaResource.class);

    private static final String ENTITY_NAME = "itemsAssinatura";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemsAssinaturaService itemsAssinaturaService;

    private final ItemsAssinaturaRepository itemsAssinaturaRepository;

    public ItemsAssinaturaResource(ItemsAssinaturaService itemsAssinaturaService, ItemsAssinaturaRepository itemsAssinaturaRepository) {
        this.itemsAssinaturaService = itemsAssinaturaService;
        this.itemsAssinaturaRepository = itemsAssinaturaRepository;
    }

    /**
     * {@code POST  /items-assinaturas} : Create a new itemsAssinatura.
     *
     * @param itemsAssinaturaDTO the itemsAssinaturaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemsAssinaturaDTO, or with status {@code 400 (Bad Request)} if the itemsAssinatura has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/items-assinaturas")
    public ResponseEntity<ItemsAssinaturaDTO> createItemsAssinatura(@Valid @RequestBody ItemsAssinaturaDTO itemsAssinaturaDTO)
        throws URISyntaxException {
        log.debug("REST request to save ItemsAssinatura : {}", itemsAssinaturaDTO);
        if (itemsAssinaturaDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemsAssinatura cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemsAssinaturaDTO result = itemsAssinaturaService.save(itemsAssinaturaDTO);
        return ResponseEntity
            .created(new URI("/api/items-assinaturas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /items-assinaturas/:id} : Updates an existing itemsAssinatura.
     *
     * @param id the id of the itemsAssinaturaDTO to save.
     * @param itemsAssinaturaDTO the itemsAssinaturaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemsAssinaturaDTO,
     * or with status {@code 400 (Bad Request)} if the itemsAssinaturaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemsAssinaturaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/items-assinaturas/{id}")
    public ResponseEntity<ItemsAssinaturaDTO> updateItemsAssinatura(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ItemsAssinaturaDTO itemsAssinaturaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemsAssinatura : {}, {}", id, itemsAssinaturaDTO);
        if (itemsAssinaturaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemsAssinaturaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemsAssinaturaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemsAssinaturaDTO result = itemsAssinaturaService.update(itemsAssinaturaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemsAssinaturaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /items-assinaturas/:id} : Partial updates given fields of an existing itemsAssinatura, field will ignore if it is null
     *
     * @param id the id of the itemsAssinaturaDTO to save.
     * @param itemsAssinaturaDTO the itemsAssinaturaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemsAssinaturaDTO,
     * or with status {@code 400 (Bad Request)} if the itemsAssinaturaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemsAssinaturaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemsAssinaturaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/items-assinaturas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemsAssinaturaDTO> partialUpdateItemsAssinatura(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ItemsAssinaturaDTO itemsAssinaturaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemsAssinatura partially : {}, {}", id, itemsAssinaturaDTO);
        if (itemsAssinaturaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itemsAssinaturaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemsAssinaturaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemsAssinaturaDTO> result = itemsAssinaturaService.partialUpdate(itemsAssinaturaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemsAssinaturaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /items-assinaturas} : get all the itemsAssinaturas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemsAssinaturas in body.
     */
    @GetMapping("/items-assinaturas")
    public ResponseEntity<List<ItemsAssinaturaDTO>> getAllItemsAssinaturas(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemsAssinaturas");
        Page<ItemsAssinaturaDTO> page = itemsAssinaturaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /items-assinaturas/:id} : get the "id" itemsAssinatura.
     *
     * @param id the id of the itemsAssinaturaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemsAssinaturaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/items-assinaturas/{id}")
    public ResponseEntity<ItemsAssinaturaDTO> getItemsAssinatura(@PathVariable Long id) {
        log.debug("REST request to get ItemsAssinatura : {}", id);
        Optional<ItemsAssinaturaDTO> itemsAssinaturaDTO = itemsAssinaturaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemsAssinaturaDTO);
    }

    /**
     * {@code DELETE  /items-assinaturas/:id} : delete the "id" itemsAssinatura.
     *
     * @param id the id of the itemsAssinaturaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/items-assinaturas/{id}")
    public ResponseEntity<Void> deleteItemsAssinatura(@PathVariable Long id) {
        log.debug("REST request to delete ItemsAssinatura : {}", id);
        itemsAssinaturaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
