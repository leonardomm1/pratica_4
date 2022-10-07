package com.unicamp.pedpao.service.impl;

import com.unicamp.pedpao.domain.ItemsAssinatura;
import com.unicamp.pedpao.repository.ItemsAssinaturaRepository;
import com.unicamp.pedpao.service.ItemsAssinaturaService;
import com.unicamp.pedpao.service.dto.ItemsAssinaturaDTO;
import com.unicamp.pedpao.service.mapper.ItemsAssinaturaMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemsAssinatura}.
 */
@Service
@Transactional
public class ItemsAssinaturaServiceImpl implements ItemsAssinaturaService {

    private final Logger log = LoggerFactory.getLogger(ItemsAssinaturaServiceImpl.class);

    private final ItemsAssinaturaRepository itemsAssinaturaRepository;

    private final ItemsAssinaturaMapper itemsAssinaturaMapper;

    public ItemsAssinaturaServiceImpl(ItemsAssinaturaRepository itemsAssinaturaRepository, ItemsAssinaturaMapper itemsAssinaturaMapper) {
        this.itemsAssinaturaRepository = itemsAssinaturaRepository;
        this.itemsAssinaturaMapper = itemsAssinaturaMapper;
    }

    @Override
    public ItemsAssinaturaDTO save(ItemsAssinaturaDTO itemsAssinaturaDTO) {
        log.debug("Request to save ItemsAssinatura : {}", itemsAssinaturaDTO);
        ItemsAssinatura itemsAssinatura = itemsAssinaturaMapper.toEntity(itemsAssinaturaDTO);
        itemsAssinatura = itemsAssinaturaRepository.save(itemsAssinatura);
        return itemsAssinaturaMapper.toDto(itemsAssinatura);
    }

    @Override
    public ItemsAssinaturaDTO update(ItemsAssinaturaDTO itemsAssinaturaDTO) {
        log.debug("Request to update ItemsAssinatura : {}", itemsAssinaturaDTO);
        ItemsAssinatura itemsAssinatura = itemsAssinaturaMapper.toEntity(itemsAssinaturaDTO);
        itemsAssinatura = itemsAssinaturaRepository.save(itemsAssinatura);
        return itemsAssinaturaMapper.toDto(itemsAssinatura);
    }

    @Override
    public Optional<ItemsAssinaturaDTO> partialUpdate(ItemsAssinaturaDTO itemsAssinaturaDTO) {
        log.debug("Request to partially update ItemsAssinatura : {}", itemsAssinaturaDTO);

        return itemsAssinaturaRepository
            .findById(itemsAssinaturaDTO.getId())
            .map(existingItemsAssinatura -> {
                itemsAssinaturaMapper.partialUpdate(existingItemsAssinatura, itemsAssinaturaDTO);

                return existingItemsAssinatura;
            })
            .map(itemsAssinaturaRepository::save)
            .map(itemsAssinaturaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemsAssinaturaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemsAssinaturas");
        return itemsAssinaturaRepository.findAll(pageable).map(itemsAssinaturaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemsAssinaturaDTO> findOne(Long id) {
        log.debug("Request to get ItemsAssinatura : {}", id);
        return itemsAssinaturaRepository.findById(id).map(itemsAssinaturaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemsAssinatura : {}", id);
        itemsAssinaturaRepository.deleteById(id);
    }
}
