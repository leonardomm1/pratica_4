package com.unicamp.pedpao.service.impl;

import com.unicamp.pedpao.domain.InformacoesAdicionais;
import com.unicamp.pedpao.repository.InformacoesAdicionaisRepository;
import com.unicamp.pedpao.service.InformacoesAdicionaisService;
import com.unicamp.pedpao.service.dto.InformacoesAdicionaisDTO;
import com.unicamp.pedpao.service.mapper.InformacoesAdicionaisMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InformacoesAdicionais}.
 */
@Service
@Transactional
public class InformacoesAdicionaisServiceImpl implements InformacoesAdicionaisService {

    private final Logger log = LoggerFactory.getLogger(InformacoesAdicionaisServiceImpl.class);

    private final InformacoesAdicionaisRepository informacoesAdicionaisRepository;

    private final InformacoesAdicionaisMapper informacoesAdicionaisMapper;

    public InformacoesAdicionaisServiceImpl(
        InformacoesAdicionaisRepository informacoesAdicionaisRepository,
        InformacoesAdicionaisMapper informacoesAdicionaisMapper
    ) {
        this.informacoesAdicionaisRepository = informacoesAdicionaisRepository;
        this.informacoesAdicionaisMapper = informacoesAdicionaisMapper;
    }

    @Override
    public InformacoesAdicionaisDTO save(InformacoesAdicionaisDTO informacoesAdicionaisDTO) {
        log.debug("Request to save InformacoesAdicionais : {}", informacoesAdicionaisDTO);
        InformacoesAdicionais informacoesAdicionais = informacoesAdicionaisMapper.toEntity(informacoesAdicionaisDTO);
        informacoesAdicionais = informacoesAdicionaisRepository.save(informacoesAdicionais);
        return informacoesAdicionaisMapper.toDto(informacoesAdicionais);
    }

    @Override
    public InformacoesAdicionaisDTO update(InformacoesAdicionaisDTO informacoesAdicionaisDTO) {
        log.debug("Request to update InformacoesAdicionais : {}", informacoesAdicionaisDTO);
        InformacoesAdicionais informacoesAdicionais = informacoesAdicionaisMapper.toEntity(informacoesAdicionaisDTO);
        informacoesAdicionais = informacoesAdicionaisRepository.save(informacoesAdicionais);
        return informacoesAdicionaisMapper.toDto(informacoesAdicionais);
    }

    @Override
    public Optional<InformacoesAdicionaisDTO> partialUpdate(InformacoesAdicionaisDTO informacoesAdicionaisDTO) {
        log.debug("Request to partially update InformacoesAdicionais : {}", informacoesAdicionaisDTO);

        return informacoesAdicionaisRepository
            .findById(informacoesAdicionaisDTO.getId())
            .map(existingInformacoesAdicionais -> {
                informacoesAdicionaisMapper.partialUpdate(existingInformacoesAdicionais, informacoesAdicionaisDTO);

                return existingInformacoesAdicionais;
            })
            .map(informacoesAdicionaisRepository::save)
            .map(informacoesAdicionaisMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InformacoesAdicionaisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InformacoesAdicionais");
        return informacoesAdicionaisRepository.findAll(pageable).map(informacoesAdicionaisMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InformacoesAdicionaisDTO> findOne(Long id) {
        log.debug("Request to get InformacoesAdicionais : {}", id);
        return informacoesAdicionaisRepository.findById(id).map(informacoesAdicionaisMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete InformacoesAdicionais : {}", id);
        informacoesAdicionaisRepository.deleteById(id);
    }
}
