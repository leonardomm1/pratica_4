package com.unicamp.pedpao.web.rest;

import com.unicamp.pedpao.repository.PadariaRepository;
import com.unicamp.pedpao.service.PadariaService;
import com.unicamp.pedpao.service.dto.PadariaDTO;
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
 * REST controller for managing {@link com.unicamp.pedpao.domain.Padaria}.
 */
@RestController
@RequestMapping("/api")
public class PadariaResource {

    private final Logger log = LoggerFactory.getLogger(PadariaResource.class);

    private static final String ENTITY_NAME = "padaria";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PadariaService padariaService;

    private final PadariaRepository padariaRepository;

    public PadariaResource(PadariaService padariaService, PadariaRepository padariaRepository) {
        this.padariaService = padariaService;
        this.padariaRepository = padariaRepository;
    }

    /**
     * {@code POST  /padarias} : Create a new padaria.
     *
     * @param padariaDTO the padariaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new padariaDTO, or with status {@code 400 (Bad Request)} if the padaria has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/padarias")
    public ResponseEntity<PadariaDTO> createPadaria(@Valid @RequestBody PadariaDTO padariaDTO) throws URISyntaxException {
        log.debug("REST request to save Padaria : {}", padariaDTO);
        if (padariaDTO.getId() != null) {
            throw new BadRequestAlertException("A new padaria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PadariaDTO result = padariaService.save(padariaDTO);
        return ResponseEntity
            .created(new URI("/api/padarias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /padarias/:id} : Updates an existing padaria.
     *
     * @param id the id of the padariaDTO to save.
     * @param padariaDTO the padariaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated padariaDTO,
     * or with status {@code 400 (Bad Request)} if the padariaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the padariaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/padarias/{id}")
    public ResponseEntity<PadariaDTO> updatePadaria(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PadariaDTO padariaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Padaria : {}, {}", id, padariaDTO);
        if (padariaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, padariaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!padariaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PadariaDTO result = padariaService.update(padariaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, padariaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /padarias/:id} : Partial updates given fields of an existing padaria, field will ignore if it is null
     *
     * @param id the id of the padariaDTO to save.
     * @param padariaDTO the padariaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated padariaDTO,
     * or with status {@code 400 (Bad Request)} if the padariaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the padariaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the padariaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/padarias/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PadariaDTO> partialUpdatePadaria(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PadariaDTO padariaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Padaria partially : {}, {}", id, padariaDTO);
        if (padariaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, padariaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!padariaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PadariaDTO> result = padariaService.partialUpdate(padariaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, padariaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /padarias} : get all the padarias.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of padarias in body.
     */
    @GetMapping("/padarias")
    public ResponseEntity<List<PadariaDTO>> getAllPadarias(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Padarias");
        Page<PadariaDTO> page = padariaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /padarias/:id} : get the "id" padaria.
     *
     * @param id the id of the padariaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the padariaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/padarias/{id}")
    public ResponseEntity<PadariaDTO> getPadaria(@PathVariable Long id) {
        log.debug("REST request to get Padaria : {}", id);
        Optional<PadariaDTO> padariaDTO = padariaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(padariaDTO);
    }

    /**
     * {@code DELETE  /padarias/:id} : delete the "id" padaria.
     *
     * @param id the id of the padariaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/padarias/{id}")
    public ResponseEntity<Void> deletePadaria(@PathVariable Long id) {
        log.debug("REST request to delete Padaria : {}", id);
        padariaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
