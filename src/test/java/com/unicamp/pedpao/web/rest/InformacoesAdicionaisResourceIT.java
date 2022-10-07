package com.unicamp.pedpao.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.unicamp.pedpao.IntegrationTest;
import com.unicamp.pedpao.domain.InformacoesAdicionais;
import com.unicamp.pedpao.repository.InformacoesAdicionaisRepository;
import com.unicamp.pedpao.service.dto.InformacoesAdicionaisDTO;
import com.unicamp.pedpao.service.mapper.InformacoesAdicionaisMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link InformacoesAdicionaisResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InformacoesAdicionaisResourceIT {

    private static final String DEFAULT_TELEFONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONE = "BBBBBBBBBB";

    private static final String DEFAULT_CPF = "AAAAAAAAAAA";
    private static final String UPDATED_CPF = "BBBBBBBBBBB";

    private static final String DEFAULT_CEP = "AAAAAAAA";
    private static final String UPDATED_CEP = "BBBBBBBB";

    private static final String DEFAULT_RUA = "AAAAAAAAAA";
    private static final String UPDATED_RUA = "BBBBBBBBBB";

    private static final String DEFAULT_BAIRRO = "AAAAAAAAAA";
    private static final String UPDATED_BAIRRO = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AA";
    private static final String UPDATED_ESTADO = "BB";

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_COMPLEMENTO = "AAAAAAAAAA";
    private static final String UPDATED_COMPLEMENTO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/informacoes-adicionais";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InformacoesAdicionaisRepository informacoesAdicionaisRepository;

    @Autowired
    private InformacoesAdicionaisMapper informacoesAdicionaisMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInformacoesAdicionaisMockMvc;

    private InformacoesAdicionais informacoesAdicionais;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InformacoesAdicionais createEntity(EntityManager em) {
        InformacoesAdicionais informacoesAdicionais = new InformacoesAdicionais()
            .telefone(DEFAULT_TELEFONE)
            .cpf(DEFAULT_CPF)
            .cep(DEFAULT_CEP)
            .rua(DEFAULT_RUA)
            .bairro(DEFAULT_BAIRRO)
            .estado(DEFAULT_ESTADO)
            .numero(DEFAULT_NUMERO)
            .complemento(DEFAULT_COMPLEMENTO);
        return informacoesAdicionais;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InformacoesAdicionais createUpdatedEntity(EntityManager em) {
        InformacoesAdicionais informacoesAdicionais = new InformacoesAdicionais()
            .telefone(UPDATED_TELEFONE)
            .cpf(UPDATED_CPF)
            .cep(UPDATED_CEP)
            .rua(UPDATED_RUA)
            .bairro(UPDATED_BAIRRO)
            .estado(UPDATED_ESTADO)
            .numero(UPDATED_NUMERO)
            .complemento(UPDATED_COMPLEMENTO);
        return informacoesAdicionais;
    }

    @BeforeEach
    public void initTest() {
        informacoesAdicionais = createEntity(em);
    }

    @Test
    @Transactional
    void createInformacoesAdicionais() throws Exception {
        int databaseSizeBeforeCreate = informacoesAdicionaisRepository.findAll().size();
        // Create the InformacoesAdicionais
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);
        restInformacoesAdicionaisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InformacoesAdicionais in the database
        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeCreate + 1);
        InformacoesAdicionais testInformacoesAdicionais = informacoesAdicionaisList.get(informacoesAdicionaisList.size() - 1);
        assertThat(testInformacoesAdicionais.getTelefone()).isEqualTo(DEFAULT_TELEFONE);
        assertThat(testInformacoesAdicionais.getCpf()).isEqualTo(DEFAULT_CPF);
        assertThat(testInformacoesAdicionais.getCep()).isEqualTo(DEFAULT_CEP);
        assertThat(testInformacoesAdicionais.getRua()).isEqualTo(DEFAULT_RUA);
        assertThat(testInformacoesAdicionais.getBairro()).isEqualTo(DEFAULT_BAIRRO);
        assertThat(testInformacoesAdicionais.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testInformacoesAdicionais.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testInformacoesAdicionais.getComplemento()).isEqualTo(DEFAULT_COMPLEMENTO);
    }

    @Test
    @Transactional
    void createInformacoesAdicionaisWithExistingId() throws Exception {
        // Create the InformacoesAdicionais with an existing ID
        informacoesAdicionais.setId(1L);
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);

        int databaseSizeBeforeCreate = informacoesAdicionaisRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInformacoesAdicionaisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacoesAdicionais in the database
        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTelefoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = informacoesAdicionaisRepository.findAll().size();
        // set the field null
        informacoesAdicionais.setTelefone(null);

        // Create the InformacoesAdicionais, which fails.
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);

        restInformacoesAdicionaisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isBadRequest());

        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCpfIsRequired() throws Exception {
        int databaseSizeBeforeTest = informacoesAdicionaisRepository.findAll().size();
        // set the field null
        informacoesAdicionais.setCpf(null);

        // Create the InformacoesAdicionais, which fails.
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);

        restInformacoesAdicionaisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isBadRequest());

        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCepIsRequired() throws Exception {
        int databaseSizeBeforeTest = informacoesAdicionaisRepository.findAll().size();
        // set the field null
        informacoesAdicionais.setCep(null);

        // Create the InformacoesAdicionais, which fails.
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);

        restInformacoesAdicionaisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isBadRequest());

        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRuaIsRequired() throws Exception {
        int databaseSizeBeforeTest = informacoesAdicionaisRepository.findAll().size();
        // set the field null
        informacoesAdicionais.setRua(null);

        // Create the InformacoesAdicionais, which fails.
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);

        restInformacoesAdicionaisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isBadRequest());

        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBairroIsRequired() throws Exception {
        int databaseSizeBeforeTest = informacoesAdicionaisRepository.findAll().size();
        // set the field null
        informacoesAdicionais.setBairro(null);

        // Create the InformacoesAdicionais, which fails.
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);

        restInformacoesAdicionaisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isBadRequest());

        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = informacoesAdicionaisRepository.findAll().size();
        // set the field null
        informacoesAdicionais.setEstado(null);

        // Create the InformacoesAdicionais, which fails.
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);

        restInformacoesAdicionaisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isBadRequest());

        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = informacoesAdicionaisRepository.findAll().size();
        // set the field null
        informacoesAdicionais.setNumero(null);

        // Create the InformacoesAdicionais, which fails.
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);

        restInformacoesAdicionaisMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isBadRequest());

        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllInformacoesAdicionais() throws Exception {
        // Initialize the database
        informacoesAdicionaisRepository.saveAndFlush(informacoesAdicionais);

        // Get all the informacoesAdicionaisList
        restInformacoesAdicionaisMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(informacoesAdicionais.getId().intValue())))
            .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE)))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].rua").value(hasItem(DEFAULT_RUA)))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].complemento").value(hasItem(DEFAULT_COMPLEMENTO)));
    }

    @Test
    @Transactional
    void getInformacoesAdicionais() throws Exception {
        // Initialize the database
        informacoesAdicionaisRepository.saveAndFlush(informacoesAdicionais);

        // Get the informacoesAdicionais
        restInformacoesAdicionaisMockMvc
            .perform(get(ENTITY_API_URL_ID, informacoesAdicionais.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(informacoesAdicionais.getId().intValue()))
            .andExpect(jsonPath("$.telefone").value(DEFAULT_TELEFONE))
            .andExpect(jsonPath("$.cpf").value(DEFAULT_CPF))
            .andExpect(jsonPath("$.cep").value(DEFAULT_CEP))
            .andExpect(jsonPath("$.rua").value(DEFAULT_RUA))
            .andExpect(jsonPath("$.bairro").value(DEFAULT_BAIRRO))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.complemento").value(DEFAULT_COMPLEMENTO));
    }

    @Test
    @Transactional
    void getNonExistingInformacoesAdicionais() throws Exception {
        // Get the informacoesAdicionais
        restInformacoesAdicionaisMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInformacoesAdicionais() throws Exception {
        // Initialize the database
        informacoesAdicionaisRepository.saveAndFlush(informacoesAdicionais);

        int databaseSizeBeforeUpdate = informacoesAdicionaisRepository.findAll().size();

        // Update the informacoesAdicionais
        InformacoesAdicionais updatedInformacoesAdicionais = informacoesAdicionaisRepository.findById(informacoesAdicionais.getId()).get();
        // Disconnect from session so that the updates on updatedInformacoesAdicionais are not directly saved in db
        em.detach(updatedInformacoesAdicionais);
        updatedInformacoesAdicionais
            .telefone(UPDATED_TELEFONE)
            .cpf(UPDATED_CPF)
            .cep(UPDATED_CEP)
            .rua(UPDATED_RUA)
            .bairro(UPDATED_BAIRRO)
            .estado(UPDATED_ESTADO)
            .numero(UPDATED_NUMERO)
            .complemento(UPDATED_COMPLEMENTO);
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(updatedInformacoesAdicionais);

        restInformacoesAdicionaisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, informacoesAdicionaisDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isOk());

        // Validate the InformacoesAdicionais in the database
        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeUpdate);
        InformacoesAdicionais testInformacoesAdicionais = informacoesAdicionaisList.get(informacoesAdicionaisList.size() - 1);
        assertThat(testInformacoesAdicionais.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testInformacoesAdicionais.getCpf()).isEqualTo(UPDATED_CPF);
        assertThat(testInformacoesAdicionais.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testInformacoesAdicionais.getRua()).isEqualTo(UPDATED_RUA);
        assertThat(testInformacoesAdicionais.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testInformacoesAdicionais.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testInformacoesAdicionais.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testInformacoesAdicionais.getComplemento()).isEqualTo(UPDATED_COMPLEMENTO);
    }

    @Test
    @Transactional
    void putNonExistingInformacoesAdicionais() throws Exception {
        int databaseSizeBeforeUpdate = informacoesAdicionaisRepository.findAll().size();
        informacoesAdicionais.setId(count.incrementAndGet());

        // Create the InformacoesAdicionais
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInformacoesAdicionaisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, informacoesAdicionaisDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacoesAdicionais in the database
        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInformacoesAdicionais() throws Exception {
        int databaseSizeBeforeUpdate = informacoesAdicionaisRepository.findAll().size();
        informacoesAdicionais.setId(count.incrementAndGet());

        // Create the InformacoesAdicionais
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacoesAdicionaisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacoesAdicionais in the database
        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInformacoesAdicionais() throws Exception {
        int databaseSizeBeforeUpdate = informacoesAdicionaisRepository.findAll().size();
        informacoesAdicionais.setId(count.incrementAndGet());

        // Create the InformacoesAdicionais
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacoesAdicionaisMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InformacoesAdicionais in the database
        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInformacoesAdicionaisWithPatch() throws Exception {
        // Initialize the database
        informacoesAdicionaisRepository.saveAndFlush(informacoesAdicionais);

        int databaseSizeBeforeUpdate = informacoesAdicionaisRepository.findAll().size();

        // Update the informacoesAdicionais using partial update
        InformacoesAdicionais partialUpdatedInformacoesAdicionais = new InformacoesAdicionais();
        partialUpdatedInformacoesAdicionais.setId(informacoesAdicionais.getId());

        partialUpdatedInformacoesAdicionais.telefone(UPDATED_TELEFONE).cep(UPDATED_CEP).estado(UPDATED_ESTADO);

        restInformacoesAdicionaisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInformacoesAdicionais.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInformacoesAdicionais))
            )
            .andExpect(status().isOk());

        // Validate the InformacoesAdicionais in the database
        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeUpdate);
        InformacoesAdicionais testInformacoesAdicionais = informacoesAdicionaisList.get(informacoesAdicionaisList.size() - 1);
        assertThat(testInformacoesAdicionais.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testInformacoesAdicionais.getCpf()).isEqualTo(DEFAULT_CPF);
        assertThat(testInformacoesAdicionais.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testInformacoesAdicionais.getRua()).isEqualTo(DEFAULT_RUA);
        assertThat(testInformacoesAdicionais.getBairro()).isEqualTo(DEFAULT_BAIRRO);
        assertThat(testInformacoesAdicionais.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testInformacoesAdicionais.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testInformacoesAdicionais.getComplemento()).isEqualTo(DEFAULT_COMPLEMENTO);
    }

    @Test
    @Transactional
    void fullUpdateInformacoesAdicionaisWithPatch() throws Exception {
        // Initialize the database
        informacoesAdicionaisRepository.saveAndFlush(informacoesAdicionais);

        int databaseSizeBeforeUpdate = informacoesAdicionaisRepository.findAll().size();

        // Update the informacoesAdicionais using partial update
        InformacoesAdicionais partialUpdatedInformacoesAdicionais = new InformacoesAdicionais();
        partialUpdatedInformacoesAdicionais.setId(informacoesAdicionais.getId());

        partialUpdatedInformacoesAdicionais
            .telefone(UPDATED_TELEFONE)
            .cpf(UPDATED_CPF)
            .cep(UPDATED_CEP)
            .rua(UPDATED_RUA)
            .bairro(UPDATED_BAIRRO)
            .estado(UPDATED_ESTADO)
            .numero(UPDATED_NUMERO)
            .complemento(UPDATED_COMPLEMENTO);

        restInformacoesAdicionaisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInformacoesAdicionais.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInformacoesAdicionais))
            )
            .andExpect(status().isOk());

        // Validate the InformacoesAdicionais in the database
        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeUpdate);
        InformacoesAdicionais testInformacoesAdicionais = informacoesAdicionaisList.get(informacoesAdicionaisList.size() - 1);
        assertThat(testInformacoesAdicionais.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testInformacoesAdicionais.getCpf()).isEqualTo(UPDATED_CPF);
        assertThat(testInformacoesAdicionais.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testInformacoesAdicionais.getRua()).isEqualTo(UPDATED_RUA);
        assertThat(testInformacoesAdicionais.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testInformacoesAdicionais.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testInformacoesAdicionais.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testInformacoesAdicionais.getComplemento()).isEqualTo(UPDATED_COMPLEMENTO);
    }

    @Test
    @Transactional
    void patchNonExistingInformacoesAdicionais() throws Exception {
        int databaseSizeBeforeUpdate = informacoesAdicionaisRepository.findAll().size();
        informacoesAdicionais.setId(count.incrementAndGet());

        // Create the InformacoesAdicionais
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInformacoesAdicionaisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, informacoesAdicionaisDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacoesAdicionais in the database
        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInformacoesAdicionais() throws Exception {
        int databaseSizeBeforeUpdate = informacoesAdicionaisRepository.findAll().size();
        informacoesAdicionais.setId(count.incrementAndGet());

        // Create the InformacoesAdicionais
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacoesAdicionaisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacoesAdicionais in the database
        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInformacoesAdicionais() throws Exception {
        int databaseSizeBeforeUpdate = informacoesAdicionaisRepository.findAll().size();
        informacoesAdicionais.setId(count.incrementAndGet());

        // Create the InformacoesAdicionais
        InformacoesAdicionaisDTO informacoesAdicionaisDTO = informacoesAdicionaisMapper.toDto(informacoesAdicionais);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacoesAdicionaisMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(informacoesAdicionaisDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InformacoesAdicionais in the database
        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInformacoesAdicionais() throws Exception {
        // Initialize the database
        informacoesAdicionaisRepository.saveAndFlush(informacoesAdicionais);

        int databaseSizeBeforeDelete = informacoesAdicionaisRepository.findAll().size();

        // Delete the informacoesAdicionais
        restInformacoesAdicionaisMockMvc
            .perform(delete(ENTITY_API_URL_ID, informacoesAdicionais.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InformacoesAdicionais> informacoesAdicionaisList = informacoesAdicionaisRepository.findAll();
        assertThat(informacoesAdicionaisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
