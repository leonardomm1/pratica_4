package com.unicamp.pedpao.service.impl;

import com.unicamp.pedpao.domain.Padaria;
import com.unicamp.pedpao.repository.PadariaRepository;
import com.unicamp.pedpao.service.PadariaService;
import com.unicamp.pedpao.service.dto.PadariaDTO;
import com.unicamp.pedpao.service.mapper.PadariaMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Padaria}.
 */
@Service
@Transactional
public class PadariaServiceImpl implements PadariaService {

    private final Logger log = LoggerFactory.getLogger(PadariaServiceImpl.class);

    private final PadariaRepository padariaRepository;

    private final PadariaMapper padariaMapper;

    public PadariaServiceImpl(PadariaRepository padariaRepository, PadariaMapper padariaMapper) {
        this.padariaRepository = padariaRepository;
        this.padariaMapper = padariaMapper;
    }

    @Override
    public PadariaDTO save(PadariaDTO padariaDTO) {
        log.debug("Request to save Padaria : {}", padariaDTO);
        Padaria padaria = padariaMapper.toEntity(padariaDTO);
        padaria = padariaRepository.save(padaria);
        return padariaMapper.toDto(padaria);
    }

    @Override
    public PadariaDTO update(PadariaDTO padariaDTO) {
        log.debug("Request to update Padaria : {}", padariaDTO);
        Padaria padaria = padariaMapper.toEntity(padariaDTO);
        padaria = padariaRepository.save(padaria);
        return padariaMapper.toDto(padaria);
    }

    @Override
    public Optional<PadariaDTO> partialUpdate(PadariaDTO padariaDTO) {
        log.debug("Request to partially update Padaria : {}", padariaDTO);

        return padariaRepository
            .findById(padariaDTO.getId())
            .map(existingPadaria -> {
                padariaMapper.partialUpdate(existingPadaria, padariaDTO);

                return existingPadaria;
            })
            .map(padariaRepository::save)
            .map(padariaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PadariaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Padarias");
        return padariaRepository.findAll(pageable).map(padariaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PadariaDTO> findOne(Long id) {
        log.debug("Request to get Padaria : {}", id);
        return padariaRepository.findById(id).map(padariaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Padaria : {}", id);
        padariaRepository.deleteById(id);
    }
}
