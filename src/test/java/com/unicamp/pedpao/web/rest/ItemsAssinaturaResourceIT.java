package com.unicamp.pedpao.web.rest;

import static com.unicamp.pedpao.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.unicamp.pedpao.IntegrationTest;
import com.unicamp.pedpao.domain.ItemsAssinatura;
import com.unicamp.pedpao.repository.ItemsAssinaturaRepository;
import com.unicamp.pedpao.service.dto.ItemsAssinaturaDTO;
import com.unicamp.pedpao.service.mapper.ItemsAssinaturaMapper;
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
 * Integration tests for the {@link ItemsAssinaturaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemsAssinaturaResourceIT {

    private static final Long DEFAULT_PADARIA_ID = 1L;
    private static final Long UPDATED_PADARIA_ID = 2L;

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VALOR = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALOR = new BigDecimal(2);

    private static final byte[] DEFAULT_FOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_CONTENT_TYPE = "image/png";

    private static final Integer DEFAULT_QUANTIDADE = 1;
    private static final Integer UPDATED_QUANTIDADE = 2;

    private static final String ENTITY_API_URL = "/api/items-assinaturas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemsAssinaturaRepository itemsAssinaturaRepository;

    @Autowired
    private ItemsAssinaturaMapper itemsAssinaturaMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemsAssinaturaMockMvc;

    private ItemsAssinatura itemsAssinatura;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemsAssinatura createEntity(EntityManager em) {
        ItemsAssinatura itemsAssinatura = new ItemsAssinatura()
            .padariaId(DEFAULT_PADARIA_ID)
            .nome(DEFAULT_NOME)
            .valor(DEFAULT_VALOR)
            .foto(DEFAULT_FOTO)
            .fotoContentType(DEFAULT_FOTO_CONTENT_TYPE)
            .quantidade(DEFAULT_QUANTIDADE);
        return itemsAssinatura;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemsAssinatura createUpdatedEntity(EntityManager em) {
        ItemsAssinatura itemsAssinatura = new ItemsAssinatura()
            .padariaId(UPDATED_PADARIA_ID)
            .nome(UPDATED_NOME)
            .valor(UPDATED_VALOR)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE)
            .quantidade(UPDATED_QUANTIDADE);
        return itemsAssinatura;
    }

    @BeforeEach
    public void initTest() {
        itemsAssinatura = createEntity(em);
    }

    @Test
    @Transactional
    void createItemsAssinatura() throws Exception {
        int databaseSizeBeforeCreate = itemsAssinaturaRepository.findAll().size();
        // Create the ItemsAssinatura
        ItemsAssinaturaDTO itemsAssinaturaDTO = itemsAssinaturaMapper.toDto(itemsAssinatura);
        restItemsAssinaturaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itemsAssinaturaDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemsAssinatura in the database
        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeCreate + 1);
        ItemsAssinatura testItemsAssinatura = itemsAssinaturaList.get(itemsAssinaturaList.size() - 1);
        assertThat(testItemsAssinatura.getPadariaId()).isEqualTo(DEFAULT_PADARIA_ID);
        assertThat(testItemsAssinatura.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testItemsAssinatura.getValor()).isEqualByComparingTo(DEFAULT_VALOR);
        assertThat(testItemsAssinatura.getFoto()).isEqualTo(DEFAULT_FOTO);
        assertThat(testItemsAssinatura.getFotoContentType()).isEqualTo(DEFAULT_FOTO_CONTENT_TYPE);
        assertThat(testItemsAssinatura.getQuantidade()).isEqualTo(DEFAULT_QUANTIDADE);
    }

    @Test
    @Transactional
    void createItemsAssinaturaWithExistingId() throws Exception {
        // Create the ItemsAssinatura with an existing ID
        itemsAssinatura.setId(1L);
        ItemsAssinaturaDTO itemsAssinaturaDTO = itemsAssinaturaMapper.toDto(itemsAssinatura);

        int databaseSizeBeforeCreate = itemsAssinaturaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemsAssinaturaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itemsAssinaturaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemsAssinatura in the database
        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPadariaIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemsAssinaturaRepository.findAll().size();
        // set the field null
        itemsAssinatura.setPadariaId(null);

        // Create the ItemsAssinatura, which fails.
        ItemsAssinaturaDTO itemsAssinaturaDTO = itemsAssinaturaMapper.toDto(itemsAssinatura);

        restItemsAssinaturaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itemsAssinaturaDTO))
            )
            .andExpect(status().isBadRequest());

        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemsAssinaturaRepository.findAll().size();
        // set the field null
        itemsAssinatura.setNome(null);

        // Create the ItemsAssinatura, which fails.
        ItemsAssinaturaDTO itemsAssinaturaDTO = itemsAssinaturaMapper.toDto(itemsAssinatura);

        restItemsAssinaturaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itemsAssinaturaDTO))
            )
            .andExpect(status().isBadRequest());

        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemsAssinaturaRepository.findAll().size();
        // set the field null
        itemsAssinatura.setValor(null);

        // Create the ItemsAssinatura, which fails.
        ItemsAssinaturaDTO itemsAssinaturaDTO = itemsAssinaturaMapper.toDto(itemsAssinatura);

        restItemsAssinaturaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itemsAssinaturaDTO))
            )
            .andExpect(status().isBadRequest());

        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllItemsAssinaturas() throws Exception {
        // Initialize the database
        itemsAssinaturaRepository.saveAndFlush(itemsAssinatura);

        // Get all the itemsAssinaturaList
        restItemsAssinaturaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemsAssinatura.getId().intValue())))
            .andExpect(jsonPath("$.[*].padariaId").value(hasItem(DEFAULT_PADARIA_ID.intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(sameNumber(DEFAULT_VALOR))))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))))
            .andExpect(jsonPath("$.[*].quantidade").value(hasItem(DEFAULT_QUANTIDADE)));
    }

    @Test
    @Transactional
    void getItemsAssinatura() throws Exception {
        // Initialize the database
        itemsAssinaturaRepository.saveAndFlush(itemsAssinatura);

        // Get the itemsAssinatura
        restItemsAssinaturaMockMvc
            .perform(get(ENTITY_API_URL_ID, itemsAssinatura.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemsAssinatura.getId().intValue()))
            .andExpect(jsonPath("$.padariaId").value(DEFAULT_PADARIA_ID.intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.valor").value(sameNumber(DEFAULT_VALOR)))
            .andExpect(jsonPath("$.fotoContentType").value(DEFAULT_FOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto").value(Base64Utils.encodeToString(DEFAULT_FOTO)))
            .andExpect(jsonPath("$.quantidade").value(DEFAULT_QUANTIDADE));
    }

    @Test
    @Transactional
    void getNonExistingItemsAssinatura() throws Exception {
        // Get the itemsAssinatura
        restItemsAssinaturaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingItemsAssinatura() throws Exception {
        // Initialize the database
        itemsAssinaturaRepository.saveAndFlush(itemsAssinatura);

        int databaseSizeBeforeUpdate = itemsAssinaturaRepository.findAll().size();

        // Update the itemsAssinatura
        ItemsAssinatura updatedItemsAssinatura = itemsAssinaturaRepository.findById(itemsAssinatura.getId()).get();
        // Disconnect from session so that the updates on updatedItemsAssinatura are not directly saved in db
        em.detach(updatedItemsAssinatura);
        updatedItemsAssinatura
            .padariaId(UPDATED_PADARIA_ID)
            .nome(UPDATED_NOME)
            .valor(UPDATED_VALOR)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE)
            .quantidade(UPDATED_QUANTIDADE);
        ItemsAssinaturaDTO itemsAssinaturaDTO = itemsAssinaturaMapper.toDto(updatedItemsAssinatura);

        restItemsAssinaturaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemsAssinaturaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemsAssinaturaDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemsAssinatura in the database
        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeUpdate);
        ItemsAssinatura testItemsAssinatura = itemsAssinaturaList.get(itemsAssinaturaList.size() - 1);
        assertThat(testItemsAssinatura.getPadariaId()).isEqualTo(UPDATED_PADARIA_ID);
        assertThat(testItemsAssinatura.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testItemsAssinatura.getValor()).isEqualByComparingTo(UPDATED_VALOR);
        assertThat(testItemsAssinatura.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testItemsAssinatura.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);
        assertThat(testItemsAssinatura.getQuantidade()).isEqualTo(UPDATED_QUANTIDADE);
    }

    @Test
    @Transactional
    void putNonExistingItemsAssinatura() throws Exception {
        int databaseSizeBeforeUpdate = itemsAssinaturaRepository.findAll().size();
        itemsAssinatura.setId(count.incrementAndGet());

        // Create the ItemsAssinatura
        ItemsAssinaturaDTO itemsAssinaturaDTO = itemsAssinaturaMapper.toDto(itemsAssinatura);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemsAssinaturaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemsAssinaturaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemsAssinaturaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemsAssinatura in the database
        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemsAssinatura() throws Exception {
        int databaseSizeBeforeUpdate = itemsAssinaturaRepository.findAll().size();
        itemsAssinatura.setId(count.incrementAndGet());

        // Create the ItemsAssinatura
        ItemsAssinaturaDTO itemsAssinaturaDTO = itemsAssinaturaMapper.toDto(itemsAssinatura);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemsAssinaturaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemsAssinaturaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemsAssinatura in the database
        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemsAssinatura() throws Exception {
        int databaseSizeBeforeUpdate = itemsAssinaturaRepository.findAll().size();
        itemsAssinatura.setId(count.incrementAndGet());

        // Create the ItemsAssinatura
        ItemsAssinaturaDTO itemsAssinaturaDTO = itemsAssinaturaMapper.toDto(itemsAssinatura);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemsAssinaturaMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itemsAssinaturaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemsAssinatura in the database
        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemsAssinaturaWithPatch() throws Exception {
        // Initialize the database
        itemsAssinaturaRepository.saveAndFlush(itemsAssinatura);

        int databaseSizeBeforeUpdate = itemsAssinaturaRepository.findAll().size();

        // Update the itemsAssinatura using partial update
        ItemsAssinatura partialUpdatedItemsAssinatura = new ItemsAssinatura();
        partialUpdatedItemsAssinatura.setId(itemsAssinatura.getId());

        partialUpdatedItemsAssinatura
            .padariaId(UPDATED_PADARIA_ID)
            .valor(UPDATED_VALOR)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE)
            .quantidade(UPDATED_QUANTIDADE);

        restItemsAssinaturaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemsAssinatura.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemsAssinatura))
            )
            .andExpect(status().isOk());

        // Validate the ItemsAssinatura in the database
        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeUpdate);
        ItemsAssinatura testItemsAssinatura = itemsAssinaturaList.get(itemsAssinaturaList.size() - 1);
        assertThat(testItemsAssinatura.getPadariaId()).isEqualTo(UPDATED_PADARIA_ID);
        assertThat(testItemsAssinatura.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testItemsAssinatura.getValor()).isEqualByComparingTo(UPDATED_VALOR);
        assertThat(testItemsAssinatura.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testItemsAssinatura.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);
        assertThat(testItemsAssinatura.getQuantidade()).isEqualTo(UPDATED_QUANTIDADE);
    }

    @Test
    @Transactional
    void fullUpdateItemsAssinaturaWithPatch() throws Exception {
        // Initialize the database
        itemsAssinaturaRepository.saveAndFlush(itemsAssinatura);

        int databaseSizeBeforeUpdate = itemsAssinaturaRepository.findAll().size();

        // Update the itemsAssinatura using partial update
        ItemsAssinatura partialUpdatedItemsAssinatura = new ItemsAssinatura();
        partialUpdatedItemsAssinatura.setId(itemsAssinatura.getId());

        partialUpdatedItemsAssinatura
            .padariaId(UPDATED_PADARIA_ID)
            .nome(UPDATED_NOME)
            .valor(UPDATED_VALOR)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE)
            .quantidade(UPDATED_QUANTIDADE);

        restItemsAssinaturaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemsAssinatura.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemsAssinatura))
            )
            .andExpect(status().isOk());

        // Validate the ItemsAssinatura in the database
        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeUpdate);
        ItemsAssinatura testItemsAssinatura = itemsAssinaturaList.get(itemsAssinaturaList.size() - 1);
        assertThat(testItemsAssinatura.getPadariaId()).isEqualTo(UPDATED_PADARIA_ID);
        assertThat(testItemsAssinatura.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testItemsAssinatura.getValor()).isEqualByComparingTo(UPDATED_VALOR);
        assertThat(testItemsAssinatura.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testItemsAssinatura.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);
        assertThat(testItemsAssinatura.getQuantidade()).isEqualTo(UPDATED_QUANTIDADE);
    }

    @Test
    @Transactional
    void patchNonExistingItemsAssinatura() throws Exception {
        int databaseSizeBeforeUpdate = itemsAssinaturaRepository.findAll().size();
        itemsAssinatura.setId(count.incrementAndGet());

        // Create the ItemsAssinatura
        ItemsAssinaturaDTO itemsAssinaturaDTO = itemsAssinaturaMapper.toDto(itemsAssinatura);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemsAssinaturaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemsAssinaturaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemsAssinaturaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemsAssinatura in the database
        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemsAssinatura() throws Exception {
        int databaseSizeBeforeUpdate = itemsAssinaturaRepository.findAll().size();
        itemsAssinatura.setId(count.incrementAndGet());

        // Create the ItemsAssinatura
        ItemsAssinaturaDTO itemsAssinaturaDTO = itemsAssinaturaMapper.toDto(itemsAssinatura);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemsAssinaturaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemsAssinaturaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemsAssinatura in the database
        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemsAssinatura() throws Exception {
        int databaseSizeBeforeUpdate = itemsAssinaturaRepository.findAll().size();
        itemsAssinatura.setId(count.incrementAndGet());

        // Create the ItemsAssinatura
        ItemsAssinaturaDTO itemsAssinaturaDTO = itemsAssinaturaMapper.toDto(itemsAssinatura);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemsAssinaturaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemsAssinaturaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemsAssinatura in the database
        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemsAssinatura() throws Exception {
        // Initialize the database
        itemsAssinaturaRepository.saveAndFlush(itemsAssinatura);

        int databaseSizeBeforeDelete = itemsAssinaturaRepository.findAll().size();

        // Delete the itemsAssinatura
        restItemsAssinaturaMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemsAssinatura.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemsAssinatura> itemsAssinaturaList = itemsAssinaturaRepository.findAll();
        assertThat(itemsAssinaturaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
