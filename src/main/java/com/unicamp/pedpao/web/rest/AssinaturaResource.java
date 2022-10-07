package com.unicamp.pedpao.web.rest;

import com.unicamp.pedpao.repository.AssinaturaRepository;
import com.unicamp.pedpao.service.AssinaturaService;
import com.unicamp.pedpao.service.dto.AssinaturaDTO;
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
 * REST controller for managing {@link com.unicamp.pedpao.domain.Assinatura}.
 */
@RestController
@RequestMapping("/api")
public class AssinaturaResource {

    private final Logger log = LoggerFactory.getLogger(AssinaturaResource.class);

    private static final String ENTITY_NAME = "assinatura";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AssinaturaService assinaturaService;

    private final AssinaturaRepository assinaturaRepository;

    public AssinaturaResource(AssinaturaService assinaturaService, AssinaturaRepository assinaturaRepository) {
        this.assinaturaService = assinaturaService;
        this.assinaturaRepository = assinaturaRepository;
    }

    /**
     * {@code POST  /assinaturas} : Create a new assinatura.
     *
     * @param assinaturaDTO the assinaturaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new assinaturaDTO, or with status {@code 400 (Bad Request)} if the assinatura has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/assinaturas")
    public ResponseEntity<AssinaturaDTO> createAssinatura(@Valid @RequestBody AssinaturaDTO assinaturaDTO) throws URISyntaxException {
        log.debug("REST request to save Assinatura : {}", assinaturaDTO);
        if (assinaturaDTO.getId() != null) {
            throw new BadRequestAlertException("A new assinatura cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssinaturaDTO result = assinaturaService.save(assinaturaDTO);
        return ResponseEntity
            .created(new URI("/api/assinaturas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /assinaturas/:id} : Updates an existing assinatura.
     *
     * @param id the id of the assinaturaDTO to save.
     * @param assinaturaDTO the assinaturaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assinaturaDTO,
     * or with status {@code 400 (Bad Request)} if the assinaturaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the assinaturaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/assinaturas/{id}")
    public ResponseEntity<AssinaturaDTO> updateAssinatura(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AssinaturaDTO assinaturaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Assinatura : {}, {}", id, assinaturaDTO);
        if (assinaturaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, assinaturaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!assinaturaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AssinaturaDTO result = assinaturaService.update(assinaturaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, assinaturaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /assinaturas/:id} : Partial updates given fields of an existing assinatura, field will ignore if it is null
     *
     * @param id the id of the assinaturaDTO to save.
     * @param assinaturaDTO the assinaturaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assinaturaDTO,
     * or with status {@code 400 (Bad Request)} if the assinaturaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the assinaturaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the assinaturaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/assinaturas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AssinaturaDTO> partialUpdateAssinatura(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AssinaturaDTO assinaturaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Assinatura partially : {}, {}", id, assinaturaDTO);
        if (assinaturaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, assinaturaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!assinaturaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AssinaturaDTO> result = assinaturaService.partialUpdate(assinaturaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, assinaturaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /assinaturas} : get all the assinaturas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assinaturas in body.
     */
    @GetMapping("/assinaturas")
    public ResponseEntity<List<AssinaturaDTO>> getAllAssinaturas(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Assinaturas");
        Page<AssinaturaDTO> page = assinaturaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /assinaturas/:id} : get the "id" assinatura.
     *
     * @param id the id of the assinaturaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assinaturaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assinaturas/{id}")
    public ResponseEntity<AssinaturaDTO> getAssinatura(@PathVariable Long id) {
        log.debug("REST request to get Assinatura : {}", id);
        Optional<AssinaturaDTO> assinaturaDTO = assinaturaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(assinaturaDTO);
    }

    /**
     * {@code DELETE  /assinaturas/:id} : delete the "id" assinatura.
     *
     * @param id the id of the assinaturaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/assinaturas/{id}")
    public ResponseEntity<Void> deleteAssinatura(@PathVariable Long id) {
        log.debug("REST request to delete Assinatura : {}", id);
        assinaturaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
