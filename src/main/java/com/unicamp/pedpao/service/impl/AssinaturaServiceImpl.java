package com.unicamp.pedpao.service.impl;

import com.unicamp.pedpao.domain.Assinatura;
import com.unicamp.pedpao.repository.AssinaturaRepository;
import com.unicamp.pedpao.service.AssinaturaService;
import com.unicamp.pedpao.service.dto.AssinaturaDTO;
import com.unicamp.pedpao.service.mapper.AssinaturaMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Assinatura}.
 */
@Service
@Transactional
public class AssinaturaServiceImpl implements AssinaturaService {

    private final Logger log = LoggerFactory.getLogger(AssinaturaServiceImpl.class);

    private final AssinaturaRepository assinaturaRepository;

    private final AssinaturaMapper assinaturaMapper;

    public AssinaturaServiceImpl(AssinaturaRepository assinaturaRepository, AssinaturaMapper assinaturaMapper) {
        this.assinaturaRepository = assinaturaRepository;
        this.assinaturaMapper = assinaturaMapper;
    }

    @Override
    public AssinaturaDTO save(AssinaturaDTO assinaturaDTO) {
        log.debug("Request to save Assinatura : {}", assinaturaDTO);
        Assinatura assinatura = assinaturaMapper.toEntity(assinaturaDTO);
        assinatura = assinaturaRepository.save(assinatura);
        return assinaturaMapper.toDto(assinatura);
    }

    @Override
    public AssinaturaDTO update(AssinaturaDTO assinaturaDTO) {
        log.debug("Request to update Assinatura : {}", assinaturaDTO);
        Assinatura assinatura = assinaturaMapper.toEntity(assinaturaDTO);
        assinatura = assinaturaRepository.save(assinatura);
        return assinaturaMapper.toDto(assinatura);
    }

    @Override
    public Optional<AssinaturaDTO> partialUpdate(AssinaturaDTO assinaturaDTO) {
        log.debug("Request to partially update Assinatura : {}", assinaturaDTO);

        return assinaturaRepository
            .findById(assinaturaDTO.getId())
            .map(existingAssinatura -> {
                assinaturaMapper.partialUpdate(existingAssinatura, assinaturaDTO);

                return existingAssinatura;
            })
            .map(assinaturaRepository::save)
            .map(assinaturaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssinaturaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Assinaturas");
        return assinaturaRepository.findAll(pageable).map(assinaturaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AssinaturaDTO> findOne(Long id) {
        log.debug("Request to get Assinatura : {}", id);
        return assinaturaRepository.findById(id).map(assinaturaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Assinatura : {}", id);
        assinaturaRepository.deleteById(id);
    }
}
