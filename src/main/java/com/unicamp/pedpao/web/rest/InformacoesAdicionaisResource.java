package com.unicamp.pedpao.web.rest;

import com.unicamp.pedpao.repository.InformacoesAdicionaisRepository;
import com.unicamp.pedpao.service.InformacoesAdicionaisService;
import com.unicamp.pedpao.service.dto.InformacoesAdicionaisDTO;
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
 * REST controller for managing {@link com.unicamp.pedpao.domain.InformacoesAdicionais}.
 */
@RestController
@RequestMapping("/api")
public class InformacoesAdicionaisResource {

    private final Logger log = LoggerFactory.getLogger(InformacoesAdicionaisResource.class);

    private static final String ENTITY_NAME = "informacoesAdicionais";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InformacoesAdicionaisService informacoesAdicionaisService;

    private final InformacoesAdicionaisRepository informacoesAdicionaisRepository;

    public InformacoesAdicionaisResource(
        InformacoesAdicionaisService informacoesAdicionaisService,
        InformacoesAdicionaisRepository informacoesAdicionaisRepository
    ) {
        this.informacoesAdicionaisService = informacoesAdicionaisService;
        this.informacoesAdicionaisRepository = informacoesAdicionaisRepository;
    }

    /**
     * {@code POST  /informacoes-adicionais} : Create a new informacoesAdicionais.
     *
     * @param informacoesAdicionaisDTO the informacoesAdicionaisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new informacoesAdicionaisDTO, or with status {@code 400 (Bad Request)} if the informacoesAdicionais has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/informacoes-adicionais")
    public ResponseEntity<InformacoesAdicionaisDTO> createInformacoesAdicionais(
        @Valid @RequestBody InformacoesAdicionaisDTO informacoesAdicionaisDTO
    ) throws URISyntaxException {
        log.debug("REST request to save InformacoesAdicionais : {}", informacoesAdicionaisDTO);
        if (informacoesAdicionaisDTO.getId() != null) {
            throw new BadRequestAlertException("A new informacoesAdicionais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InformacoesAdicionaisDTO result = informacoesAdicionaisService.save(informacoesAdicionaisDTO);
        return ResponseEntity
            .created(new URI("/api/informacoes-adicionais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /informacoes-adicionais/:id} : Updates an existing informacoesAdicionais.
     *
     * @param id the id of the informacoesAdicionaisDTO to save.
     * @param informacoesAdicionaisDTO the informacoesAdicionaisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated informacoesAdicionaisDTO,
     * or with status {@code 400 (Bad Request)} if the informacoesAdicionaisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the informacoesAdicionaisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/informacoes-adicionais/{id}")
    public ResponseEntity<InformacoesAdicionaisDTO> updateInformacoesAdicionais(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody InformacoesAdicionaisDTO informacoesAdicionaisDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InformacoesAdicionais : {}, {}", id, informacoesAdicionaisDTO);
        if (informacoesAdicionaisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, informacoesAdicionaisDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!informacoesAdicionaisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InformacoesAdicionaisDTO result = informacoesAdicionaisService.update(informacoesAdicionaisDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, informacoesAdicionaisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /informacoes-adicionais/:id} : Partial updates given fields of an existing informacoesAdicionais, field will ignore if it is null
     *
     * @param id the id of the informacoesAdicionaisDTO to save.
     * @param informacoesAdicionaisDTO the informacoesAdicionaisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated informacoesAdicionaisDTO,
     * or with status {@code 400 (Bad Request)} if the informacoesAdicionaisDTO is not valid,
     * or with status {@code 404 (Not Found)} if the informacoesAdicionaisDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the informacoesAdicionaisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/informacoes-adicionais/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InformacoesAdicionaisDTO> partialUpdateInformacoesAdicionais(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody InformacoesAdicionaisDTO informacoesAdicionaisDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update InformacoesAdicionais partially : {}, {}", id, informacoesAdicionaisDTO);
        if (informacoesAdicionaisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, informacoesAdicionaisDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!informacoesAdicionaisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InformacoesAdicionaisDTO> result = informacoesAdicionaisService.partialUpdate(informacoesAdicionaisDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, informacoesAdicionaisDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /informacoes-adicionais} : get all the informacoesAdicionais.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of informacoesAdicionais in body.
     */
    @GetMapping("/informacoes-adicionais")
    public ResponseEntity<List<InformacoesAdicionaisDTO>> getAllInformacoesAdicionais(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of InformacoesAdicionais");
        Page<InformacoesAdicionaisDTO> page = informacoesAdicionaisService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /informacoes-adicionais/:id} : get the "id" informacoesAdicionais.
     *
     * @param id the id of the informacoesAdicionaisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the informacoesAdicionaisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/informacoes-adicionais/{id}")
    public ResponseEntity<InformacoesAdicionaisDTO> getInformacoesAdicionais(@PathVariable Long id) {
        log.debug("REST request to get InformacoesAdicionais : {}", id);
        Optional<InformacoesAdicionaisDTO> informacoesAdicionaisDTO = informacoesAdicionaisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(informacoesAdicionaisDTO);
    }

    /**
     * {@code DELETE  /informacoes-adicionais/:id} : delete the "id" informacoesAdicionais.
     *
     * @param id the id of the informacoesAdicionaisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/informacoes-adicionais/{id}")
    public ResponseEntity<Void> deleteInformacoesAdicionais(@PathVariable Long id) {
        log.debug("REST request to delete InformacoesAdicionais : {}", id);
        informacoesAdicionaisService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
