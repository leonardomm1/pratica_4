package com.unicamp.pedpao.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.unicamp.pedpao.IntegrationTest;
import com.unicamp.pedpao.domain.Padaria;
import com.unicamp.pedpao.repository.PadariaRepository;
import com.unicamp.pedpao.service.dto.PadariaDTO;
import com.unicamp.pedpao.service.mapper.PadariaMapper;
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
 * Integration tests for the {@link PadariaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PadariaResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_FANTASIA = "AAAAAAAAAA";
    private static final String UPDATED_FANTASIA = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONE = "BBBBBBBBBB";

    private static final String DEFAULT_CNPJ = "AAAAAAAAAAAAAA";
    private static final String UPDATED_CNPJ = "BBBBBBBBBBBBBB";

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

    private static final String DEFAULT_CONTATO = "AAAAAAAAAA";
    private static final String UPDATED_CONTATO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/padarias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PadariaRepository padariaRepository;

    @Autowired
    private PadariaMapper padariaMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPadariaMockMvc;

    private Padaria padaria;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Padaria createEntity(EntityManager em) {
        Padaria padaria = new Padaria()
            .nome(DEFAULT_NOME)
            .fantasia(DEFAULT_FANTASIA)
            .telefone(DEFAULT_TELEFONE)
            .cnpj(DEFAULT_CNPJ)
            .cep(DEFAULT_CEP)
            .rua(DEFAULT_RUA)
            .bairro(DEFAULT_BAIRRO)
            .estado(DEFAULT_ESTADO)
            .numero(DEFAULT_NUMERO)
            .complemento(DEFAULT_COMPLEMENTO)
            .contato(DEFAULT_CONTATO);
        return padaria;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Padaria createUpdatedEntity(EntityManager em) {
        Padaria padaria = new Padaria()
            .nome(UPDATED_NOME)
            .fantasia(UPDATED_FANTASIA)
            .telefone(UPDATED_TELEFONE)
            .cnpj(UPDATED_CNPJ)
            .cep(UPDATED_CEP)
            .rua(UPDATED_RUA)
            .bairro(UPDATED_BAIRRO)
            .estado(UPDATED_ESTADO)
            .numero(UPDATED_NUMERO)
            .complemento(UPDATED_COMPLEMENTO)
            .contato(UPDATED_CONTATO);
        return padaria;
    }

    @BeforeEach
    public void initTest() {
        padaria = createEntity(em);
    }

    @Test
    @Transactional
    void createPadaria() throws Exception {
        int databaseSizeBeforeCreate = padariaRepository.findAll().size();
        // Create the Padaria
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);
        restPadariaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(padariaDTO)))
            .andExpect(status().isCreated());

        // Validate the Padaria in the database
        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeCreate + 1);
        Padaria testPadaria = padariaList.get(padariaList.size() - 1);
        assertThat(testPadaria.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPadaria.getFantasia()).isEqualTo(DEFAULT_FANTASIA);
        assertThat(testPadaria.getTelefone()).isEqualTo(DEFAULT_TELEFONE);
        assertThat(testPadaria.getCnpj()).isEqualTo(DEFAULT_CNPJ);
        assertThat(testPadaria.getCep()).isEqualTo(DEFAULT_CEP);
        assertThat(testPadaria.getRua()).isEqualTo(DEFAULT_RUA);
        assertThat(testPadaria.getBairro()).isEqualTo(DEFAULT_BAIRRO);
        assertThat(testPadaria.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testPadaria.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testPadaria.getComplemento()).isEqualTo(DEFAULT_COMPLEMENTO);
        assertThat(testPadaria.getContato()).isEqualTo(DEFAULT_CONTATO);
    }

    @Test
    @Transactional
    void createPadariaWithExistingId() throws Exception {
        // Create the Padaria with an existing ID
        padaria.setId(1L);
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        int databaseSizeBeforeCreate = padariaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPadariaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(padariaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Padaria in the database
        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = padariaRepository.findAll().size();
        // set the field null
        padaria.setNome(null);

        // Create the Padaria, which fails.
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        restPadariaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(padariaDTO)))
            .andExpect(status().isBadRequest());

        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFantasiaIsRequired() throws Exception {
        int databaseSizeBeforeTest = padariaRepository.findAll().size();
        // set the field null
        padaria.setFantasia(null);

        // Create the Padaria, which fails.
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        restPadariaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(padariaDTO)))
            .andExpect(status().isBadRequest());

        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTelefoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = padariaRepository.findAll().size();
        // set the field null
        padaria.setTelefone(null);

        // Create the Padaria, which fails.
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        restPadariaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(padariaDTO)))
            .andExpect(status().isBadRequest());

        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCnpjIsRequired() throws Exception {
        int databaseSizeBeforeTest = padariaRepository.findAll().size();
        // set the field null
        padaria.setCnpj(null);

        // Create the Padaria, which fails.
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        restPadariaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(padariaDTO)))
            .andExpect(status().isBadRequest());

        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCepIsRequired() throws Exception {
        int databaseSizeBeforeTest = padariaRepository.findAll().size();
        // set the field null
        padaria.setCep(null);

        // Create the Padaria, which fails.
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        restPadariaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(padariaDTO)))
            .andExpect(status().isBadRequest());

        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRuaIsRequired() throws Exception {
        int databaseSizeBeforeTest = padariaRepository.findAll().size();
        // set the field null
        padaria.setRua(null);

        // Create the Padaria, which fails.
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        restPadariaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(padariaDTO)))
            .andExpect(status().isBadRequest());

        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBairroIsRequired() throws Exception {
        int databaseSizeBeforeTest = padariaRepository.findAll().size();
        // set the field null
        padaria.setBairro(null);

        // Create the Padaria, which fails.
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        restPadariaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(padariaDTO)))
            .andExpect(status().isBadRequest());

        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = padariaRepository.findAll().size();
        // set the field null
        padaria.setEstado(null);

        // Create the Padaria, which fails.
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        restPadariaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(padariaDTO)))
            .andExpect(status().isBadRequest());

        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = padariaRepository.findAll().size();
        // set the field null
        padaria.setNumero(null);

        // Create the Padaria, which fails.
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        restPadariaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(padariaDTO)))
            .andExpect(status().isBadRequest());

        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPadarias() throws Exception {
        // Initialize the database
        padariaRepository.saveAndFlush(padaria);

        // Get all the padariaList
        restPadariaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(padaria.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].fantasia").value(hasItem(DEFAULT_FANTASIA)))
            .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE)))
            .andExpect(jsonPath("$.[*].cnpj").value(hasItem(DEFAULT_CNPJ)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].rua").value(hasItem(DEFAULT_RUA)))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].complemento").value(hasItem(DEFAULT_COMPLEMENTO)))
            .andExpect(jsonPath("$.[*].contato").value(hasItem(DEFAULT_CONTATO)));
    }

    @Test
    @Transactional
    void getPadaria() throws Exception {
        // Initialize the database
        padariaRepository.saveAndFlush(padaria);

        // Get the padaria
        restPadariaMockMvc
            .perform(get(ENTITY_API_URL_ID, padaria.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(padaria.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.fantasia").value(DEFAULT_FANTASIA))
            .andExpect(jsonPath("$.telefone").value(DEFAULT_TELEFONE))
            .andExpect(jsonPath("$.cnpj").value(DEFAULT_CNPJ))
            .andExpect(jsonPath("$.cep").value(DEFAULT_CEP))
            .andExpect(jsonPath("$.rua").value(DEFAULT_RUA))
            .andExpect(jsonPath("$.bairro").value(DEFAULT_BAIRRO))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.complemento").value(DEFAULT_COMPLEMENTO))
            .andExpect(jsonPath("$.contato").value(DEFAULT_CONTATO));
    }

    @Test
    @Transactional
    void getNonExistingPadaria() throws Exception {
        // Get the padaria
        restPadariaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPadaria() throws Exception {
        // Initialize the database
        padariaRepository.saveAndFlush(padaria);

        int databaseSizeBeforeUpdate = padariaRepository.findAll().size();

        // Update the padaria
        Padaria updatedPadaria = padariaRepository.findById(padaria.getId()).get();
        // Disconnect from session so that the updates on updatedPadaria are not directly saved in db
        em.detach(updatedPadaria);
        updatedPadaria
            .nome(UPDATED_NOME)
            .fantasia(UPDATED_FANTASIA)
            .telefone(UPDATED_TELEFONE)
            .cnpj(UPDATED_CNPJ)
            .cep(UPDATED_CEP)
            .rua(UPDATED_RUA)
            .bairro(UPDATED_BAIRRO)
            .estado(UPDATED_ESTADO)
            .numero(UPDATED_NUMERO)
            .complemento(UPDATED_COMPLEMENTO)
            .contato(UPDATED_CONTATO);
        PadariaDTO padariaDTO = padariaMapper.toDto(updatedPadaria);

        restPadariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, padariaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(padariaDTO))
            )
            .andExpect(status().isOk());

        // Validate the Padaria in the database
        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeUpdate);
        Padaria testPadaria = padariaList.get(padariaList.size() - 1);
        assertThat(testPadaria.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPadaria.getFantasia()).isEqualTo(UPDATED_FANTASIA);
        assertThat(testPadaria.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testPadaria.getCnpj()).isEqualTo(UPDATED_CNPJ);
        assertThat(testPadaria.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testPadaria.getRua()).isEqualTo(UPDATED_RUA);
        assertThat(testPadaria.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testPadaria.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testPadaria.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testPadaria.getComplemento()).isEqualTo(UPDATED_COMPLEMENTO);
        assertThat(testPadaria.getContato()).isEqualTo(UPDATED_CONTATO);
    }

    @Test
    @Transactional
    void putNonExistingPadaria() throws Exception {
        int databaseSizeBeforeUpdate = padariaRepository.findAll().size();
        padaria.setId(count.incrementAndGet());

        // Create the Padaria
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPadariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, padariaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(padariaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Padaria in the database
        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPadaria() throws Exception {
        int databaseSizeBeforeUpdate = padariaRepository.findAll().size();
        padaria.setId(count.incrementAndGet());

        // Create the Padaria
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPadariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(padariaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Padaria in the database
        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPadaria() throws Exception {
        int databaseSizeBeforeUpdate = padariaRepository.findAll().size();
        padaria.setId(count.incrementAndGet());

        // Create the Padaria
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPadariaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(padariaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Padaria in the database
        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePadariaWithPatch() throws Exception {
        // Initialize the database
        padariaRepository.saveAndFlush(padaria);

        int databaseSizeBeforeUpdate = padariaRepository.findAll().size();

        // Update the padaria using partial update
        Padaria partialUpdatedPadaria = new Padaria();
        partialUpdatedPadaria.setId(padaria.getId());

        partialUpdatedPadaria
            .telefone(UPDATED_TELEFONE)
            .cep(UPDATED_CEP)
            .bairro(UPDATED_BAIRRO)
            .estado(UPDATED_ESTADO)
            .contato(UPDATED_CONTATO);

        restPadariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPadaria.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPadaria))
            )
            .andExpect(status().isOk());

        // Validate the Padaria in the database
        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeUpdate);
        Padaria testPadaria = padariaList.get(padariaList.size() - 1);
        assertThat(testPadaria.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPadaria.getFantasia()).isEqualTo(DEFAULT_FANTASIA);
        assertThat(testPadaria.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testPadaria.getCnpj()).isEqualTo(DEFAULT_CNPJ);
        assertThat(testPadaria.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testPadaria.getRua()).isEqualTo(DEFAULT_RUA);
        assertThat(testPadaria.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testPadaria.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testPadaria.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testPadaria.getComplemento()).isEqualTo(DEFAULT_COMPLEMENTO);
        assertThat(testPadaria.getContato()).isEqualTo(UPDATED_CONTATO);
    }

    @Test
    @Transactional
    void fullUpdatePadariaWithPatch() throws Exception {
        // Initialize the database
        padariaRepository.saveAndFlush(padaria);

        int databaseSizeBeforeUpdate = padariaRepository.findAll().size();

        // Update the padaria using partial update
        Padaria partialUpdatedPadaria = new Padaria();
        partialUpdatedPadaria.setId(padaria.getId());

        partialUpdatedPadaria
            .nome(UPDATED_NOME)
            .fantasia(UPDATED_FANTASIA)
            .telefone(UPDATED_TELEFONE)
            .cnpj(UPDATED_CNPJ)
            .cep(UPDATED_CEP)
            .rua(UPDATED_RUA)
            .bairro(UPDATED_BAIRRO)
            .estado(UPDATED_ESTADO)
            .numero(UPDATED_NUMERO)
            .complemento(UPDATED_COMPLEMENTO)
            .contato(UPDATED_CONTATO);

        restPadariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPadaria.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPadaria))
            )
            .andExpect(status().isOk());

        // Validate the Padaria in the database
        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeUpdate);
        Padaria testPadaria = padariaList.get(padariaList.size() - 1);
        assertThat(testPadaria.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPadaria.getFantasia()).isEqualTo(UPDATED_FANTASIA);
        assertThat(testPadaria.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testPadaria.getCnpj()).isEqualTo(UPDATED_CNPJ);
        assertThat(testPadaria.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testPadaria.getRua()).isEqualTo(UPDATED_RUA);
        assertThat(testPadaria.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testPadaria.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testPadaria.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testPadaria.getComplemento()).isEqualTo(UPDATED_COMPLEMENTO);
        assertThat(testPadaria.getContato()).isEqualTo(UPDATED_CONTATO);
    }

    @Test
    @Transactional
    void patchNonExistingPadaria() throws Exception {
        int databaseSizeBeforeUpdate = padariaRepository.findAll().size();
        padaria.setId(count.incrementAndGet());

        // Create the Padaria
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPadariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, padariaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(padariaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Padaria in the database
        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPadaria() throws Exception {
        int databaseSizeBeforeUpdate = padariaRepository.findAll().size();
        padaria.setId(count.incrementAndGet());

        // Create the Padaria
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPadariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(padariaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Padaria in the database
        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPadaria() throws Exception {
        int databaseSizeBeforeUpdate = padariaRepository.findAll().size();
        padaria.setId(count.incrementAndGet());

        // Create the Padaria
        PadariaDTO padariaDTO = padariaMapper.toDto(padaria);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPadariaMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(padariaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Padaria in the database
        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePadaria() throws Exception {
        // Initialize the database
        padariaRepository.saveAndFlush(padaria);

        int databaseSizeBeforeDelete = padariaRepository.findAll().size();

        // Delete the padaria
        restPadariaMockMvc
            .perform(delete(ENTITY_API_URL_ID, padaria.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Padaria> padariaList = padariaRepository.findAll();
        assertThat(padariaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
