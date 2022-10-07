package com.unicamp.pedpao.web.rest;

import static com.unicamp.pedpao.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.unicamp.pedpao.IntegrationTest;
import com.unicamp.pedpao.domain.Assinatura;
import com.unicamp.pedpao.repository.AssinaturaRepository;
import com.unicamp.pedpao.service.dto.AssinaturaDTO;
import com.unicamp.pedpao.service.mapper.AssinaturaMapper;
import java.math.BigDecimal;
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
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link AssinaturaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AssinaturaResourceIT {

    private static final BigDecimal DEFAULT_VALOR = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALOR = new BigDecimal(2);

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTIDADE_DIAS = 1;
    private static final Integer UPDATED_QUANTIDADE_DIAS = 2;

    private static final Boolean DEFAULT_ATIVA = false;
    private static final Boolean UPDATED_ATIVA = true;

    private static final Long DEFAULT_PAGAMENTO_RECORRENCIA_ID = 1L;
    private static final Long UPDATED_PAGAMENTO_RECORRENCIA_ID = 2L;

    private static final byte[] DEFAULT_FOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_CONTENT_TYPE = "image/png";

    private static final String ENTITY_API_URL = "/api/assinaturas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    @Autowired
    private AssinaturaMapper assinaturaMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAssinaturaMockMvc;

    private Assinatura assinatura;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Assinatura createEntity(EntityManager em) {
        Assinatura assinatura = new Assinatura()
            .valor(DEFAULT_VALOR)
            .nome(DEFAULT_NOME)
            .quantidadeDias(DEFAULT_QUANTIDADE_DIAS)
            .ativa(DEFAULT_ATIVA)
            .pagamentoRecorrenciaId(DEFAULT_PAGAMENTO_RECORRENCIA_ID)
            .foto(DEFAULT_FOTO)
            .fotoContentType(DEFAULT_FOTO_CONTENT_TYPE);
        return assinatura;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Assinatura createUpdatedEntity(EntityManager em) {
        Assinatura assinatura = new Assinatura()
            .valor(UPDATED_VALOR)
            .nome(UPDATED_NOME)
            .quantidadeDias(UPDATED_QUANTIDADE_DIAS)
            .ativa(UPDATED_ATIVA)
            .pagamentoRecorrenciaId(UPDATED_PAGAMENTO_RECORRENCIA_ID)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE);
        return assinatura;
    }

    @BeforeEach
    public void initTest() {
        assinatura = createEntity(em);
    }

    @Test
    @Transactional
    void createAssinatura() throws Exception {
        int databaseSizeBeforeCreate = assinaturaRepository.findAll().size();
        // Create the Assinatura
        AssinaturaDTO assinaturaDTO = assinaturaMapper.toDto(assinatura);
        restAssinaturaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assinaturaDTO)))
            .andExpect(status().isCreated());

        // Validate the Assinatura in the database
        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeCreate + 1);
        Assinatura testAssinatura = assinaturaList.get(assinaturaList.size() - 1);
        assertThat(testAssinatura.getValor()).isEqualByComparingTo(DEFAULT_VALOR);
        assertThat(testAssinatura.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testAssinatura.getQuantidadeDias()).isEqualTo(DEFAULT_QUANTIDADE_DIAS);
        assertThat(testAssinatura.getAtiva()).isEqualTo(DEFAULT_ATIVA);
        assertThat(testAssinatura.getPagamentoRecorrenciaId()).isEqualTo(DEFAULT_PAGAMENTO_RECORRENCIA_ID);
        assertThat(testAssinatura.getFoto()).isEqualTo(DEFAULT_FOTO);
        assertThat(testAssinatura.getFotoContentType()).isEqualTo(DEFAULT_FOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void createAssinaturaWithExistingId() throws Exception {
        // Create the Assinatura with an existing ID
        assinatura.setId(1L);
        AssinaturaDTO assinaturaDTO = assinaturaMapper.toDto(assinatura);

        int databaseSizeBeforeCreate = assinaturaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssinaturaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assinaturaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Assinatura in the database
        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkValorIsRequired() throws Exception {
        int databaseSizeBeforeTest = assinaturaRepository.findAll().size();
        // set the field null
        assinatura.setValor(null);

        // Create the Assinatura, which fails.
        AssinaturaDTO assinaturaDTO = assinaturaMapper.toDto(assinatura);

        restAssinaturaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assinaturaDTO)))
            .andExpect(status().isBadRequest());

        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = assinaturaRepository.findAll().size();
        // set the field null
        assinatura.setNome(null);

        // Create the Assinatura, which fails.
        AssinaturaDTO assinaturaDTO = assinaturaMapper.toDto(assinatura);

        restAssinaturaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assinaturaDTO)))
            .andExpect(status().isBadRequest());

        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQuantidadeDiasIsRequired() throws Exception {
        int databaseSizeBeforeTest = assinaturaRepository.findAll().size();
        // set the field null
        assinatura.setQuantidadeDias(null);

        // Create the Assinatura, which fails.
        AssinaturaDTO assinaturaDTO = assinaturaMapper.toDto(assinatura);

        restAssinaturaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assinaturaDTO)))
            .andExpect(status().isBadRequest());

        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAtivaIsRequired() throws Exception {
        int databaseSizeBeforeTest = assinaturaRepository.findAll().size();
        // set the field null
        assinatura.setAtiva(null);

        // Create the Assinatura, which fails.
        AssinaturaDTO assinaturaDTO = assinaturaMapper.toDto(assinatura);

        restAssinaturaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assinaturaDTO)))
            .andExpect(status().isBadRequest());

        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPagamentoRecorrenciaIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = assinaturaRepository.findAll().size();
        // set the field null
        assinatura.setPagamentoRecorrenciaId(null);

        // Create the Assinatura, which fails.
        AssinaturaDTO assinaturaDTO = assinaturaMapper.toDto(assinatura);

        restAssinaturaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assinaturaDTO)))
            .andExpect(status().isBadRequest());

        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAssinaturas() throws Exception {
        // Initialize the database
        assinaturaRepository.saveAndFlush(assinatura);

        // Get all the assinaturaList
        restAssinaturaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(assinatura.getId().intValue())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(sameNumber(DEFAULT_VALOR))))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].quantidadeDias").value(hasItem(DEFAULT_QUANTIDADE_DIAS)))
            .andExpect(jsonPath("$.[*].ativa").value(hasItem(DEFAULT_ATIVA.booleanValue())))
            .andExpect(jsonPath("$.[*].pagamentoRecorrenciaId").value(hasItem(DEFAULT_PAGAMENTO_RECORRENCIA_ID.intValue())))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))));
    }

    @Test
    @Transactional
    void getAssinatura() throws Exception {
        // Initialize the database
        assinaturaRepository.saveAndFlush(assinatura);

        // Get the assinatura
        restAssinaturaMockMvc
            .perform(get(ENTITY_API_URL_ID, assinatura.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(assinatura.getId().intValue()))
            .andExpect(jsonPath("$.valor").value(sameNumber(DEFAULT_VALOR)))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.quantidadeDias").value(DEFAULT_QUANTIDADE_DIAS))
            .andExpect(jsonPath("$.ativa").value(DEFAULT_ATIVA.booleanValue()))
            .andExpect(jsonPath("$.pagamentoRecorrenciaId").value(DEFAULT_PAGAMENTO_RECORRENCIA_ID.intValue()))
            .andExpect(jsonPath("$.fotoContentType").value(DEFAULT_FOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto").value(Base64Utils.encodeToString(DEFAULT_FOTO)));
    }

    @Test
    @Transactional
    void getNonExistingAssinatura() throws Exception {
        // Get the assinatura
        restAssinaturaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAssinatura() throws Exception {
        // Initialize the database
        assinaturaRepository.saveAndFlush(assinatura);

        int databaseSizeBeforeUpdate = assinaturaRepository.findAll().size();

        // Update the assinatura
        Assinatura updatedAssinatura = assinaturaRepository.findById(assinatura.getId()).get();
        // Disconnect from session so that the updates on updatedAssinatura are not directly saved in db
        em.detach(updatedAssinatura);
        updatedAssinatura
            .valor(UPDATED_VALOR)
            .nome(UPDATED_NOME)
            .quantidadeDias(UPDATED_QUANTIDADE_DIAS)
            .ativa(UPDATED_ATIVA)
            .pagamentoRecorrenciaId(UPDATED_PAGAMENTO_RECORRENCIA_ID)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE);
        AssinaturaDTO assinaturaDTO = assinaturaMapper.toDto(updatedAssinatura);

        restAssinaturaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, assinaturaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(assinaturaDTO))
            )
            .andExpect(status().isOk());

        // Validate the Assinatura in the database
        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeUpdate);
        Assinatura testAssinatura = assinaturaList.get(assinaturaList.size() - 1);
        assertThat(testAssinatura.getValor()).isEqualByComparingTo(UPDATED_VALOR);
        assertThat(testAssinatura.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testAssinatura.getQuantidadeDias()).isEqualTo(UPDATED_QUANTIDADE_DIAS);
        assertThat(testAssinatura.getAtiva()).isEqualTo(UPDATED_ATIVA);
        assertThat(testAssinatura.getPagamentoRecorrenciaId()).isEqualTo(UPDATED_PAGAMENTO_RECORRENCIA_ID);
        assertThat(testAssinatura.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testAssinatura.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingAssinatura() throws Exception {
        int databaseSizeBeforeUpdate = assinaturaRepository.findAll().size();
        assinatura.setId(count.incrementAndGet());

        // Create the Assinatura
        AssinaturaDTO assinaturaDTO = assinaturaMapper.toDto(assinatura);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAssinaturaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, assinaturaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(assinaturaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Assinatura in the database
        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAssinatura() throws Exception {
        int databaseSizeBeforeUpdate = assinaturaRepository.findAll().size();
        assinatura.setId(count.incrementAndGet());

        // Create the Assinatura
        AssinaturaDTO assinaturaDTO = assinaturaMapper.toDto(assinatura);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssinaturaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(assinaturaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Assinatura in the database
        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAssinatura() throws Exception {
        int databaseSizeBeforeUpdate = assinaturaRepository.findAll().size();
        assinatura.setId(count.incrementAndGet());

        // Create the Assinatura
        AssinaturaDTO assinaturaDTO = assinaturaMapper.toDto(assinatura);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssinaturaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assinaturaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Assinatura in the database
        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAssinaturaWithPatch() throws Exception {
        // Initialize the database
        assinaturaRepository.saveAndFlush(assinatura);

        int databaseSizeBeforeUpdate = assinaturaRepository.findAll().size();

        // Update the assinatura using partial update
        Assinatura partialUpdatedAssinatura = new Assinatura();
        partialUpdatedAssinatura.setId(assinatura.getId());

        partialUpdatedAssinatura
            .valor(UPDATED_VALOR)
            .quantidadeDias(UPDATED_QUANTIDADE_DIAS)
            .ativa(UPDATED_ATIVA)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE);

        restAssinaturaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAssinatura.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAssinatura))
            )
            .andExpect(status().isOk());

        // Validate the Assinatura in the database
        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeUpdate);
        Assinatura testAssinatura = assinaturaList.get(assinaturaList.size() - 1);
        assertThat(testAssinatura.getValor()).isEqualByComparingTo(UPDATED_VALOR);
        assertThat(testAssinatura.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testAssinatura.getQuantidadeDias()).isEqualTo(UPDATED_QUANTIDADE_DIAS);
        assertThat(testAssinatura.getAtiva()).isEqualTo(UPDATED_ATIVA);
        assertThat(testAssinatura.getPagamentoRecorrenciaId()).isEqualTo(DEFAULT_PAGAMENTO_RECORRENCIA_ID);
        assertThat(testAssinatura.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testAssinatura.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateAssinaturaWithPatch() throws Exception {
        // Initialize the database
        assinaturaRepository.saveAndFlush(assinatura);

        int databaseSizeBeforeUpdate = assinaturaRepository.findAll().size();

        // Update the assinatura using partial update
        Assinatura partialUpdatedAssinatura = new Assinatura();
        partialUpdatedAssinatura.setId(assinatura.getId());

        partialUpdatedAssinatura
            .valor(UPDATED_VALOR)
            .nome(UPDATED_NOME)
            .quantidadeDias(UPDATED_QUANTIDADE_DIAS)
            .ativa(UPDATED_ATIVA)
            .pagamentoRecorrenciaId(UPDATED_PAGAMENTO_RECORRENCIA_ID)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE);

        restAssinaturaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAssinatura.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAssinatura))
            )
            .andExpect(status().isOk());

        // Validate the Assinatura in the database
        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeUpdate);
        Assinatura testAssinatura = assinaturaList.get(assinaturaList.size() - 1);
        assertThat(testAssinatura.getValor()).isEqualByComparingTo(UPDATED_VALOR);
        assertThat(testAssinatura.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testAssinatura.getQuantidadeDias()).isEqualTo(UPDATED_QUANTIDADE_DIAS);
        assertThat(testAssinatura.getAtiva()).isEqualTo(UPDATED_ATIVA);
        assertThat(testAssinatura.getPagamentoRecorrenciaId()).isEqualTo(UPDATED_PAGAMENTO_RECORRENCIA_ID);
        assertThat(testAssinatura.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testAssinatura.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingAssinatura() throws Exception {
        int databaseSizeBeforeUpdate = assinaturaRepository.findAll().size();
        assinatura.setId(count.incrementAndGet());

        // Create the Assinatura
        AssinaturaDTO assinaturaDTO = assinaturaMapper.toDto(assinatura);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAssinaturaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, assinaturaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(assinaturaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Assinatura in the database
        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAssinatura() throws Exception {
        int databaseSizeBeforeUpdate = assinaturaRepository.findAll().size();
        assinatura.setId(count.incrementAndGet());

        // Create the Assinatura
        AssinaturaDTO assinaturaDTO = assinaturaMapper.toDto(assinatura);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssinaturaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(assinaturaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Assinatura in the database
        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAssinatura() throws Exception {
        int databaseSizeBeforeUpdate = assinaturaRepository.findAll().size();
        assinatura.setId(count.incrementAndGet());

        // Create the Assinatura
        AssinaturaDTO assinaturaDTO = assinaturaMapper.toDto(assinatura);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssinaturaMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(assinaturaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Assinatura in the database
        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAssinatura() throws Exception {
        // Initialize the database
        assinaturaRepository.saveAndFlush(assinatura);

        int databaseSizeBeforeDelete = assinaturaRepository.findAll().size();

        // Delete the assinatura
        restAssinaturaMockMvc
            .perform(delete(ENTITY_API_URL_ID, assinatura.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Assinatura> assinaturaList = assinaturaRepository.findAll();
        assertThat(assinaturaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
