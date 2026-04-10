package com.backend.apirest.service;

import com.backend.apirest.dto.ConflictCreateDto;
import com.backend.apirest.dto.ConflictDto;
import com.backend.apirest.dto.ConflictUpdateDto;
import com.backend.apirest.mapper.ConflictMapper;
import com.backend.apirest.model.Conflict;
import com.backend.apirest.model.entity.Country;
import com.backend.apirest.model.ConflictStatus;
import com.backend.apirest.repository.ConflictRepository;
import com.backend.apirest.repository.CountryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConflictServiceImpl implements ConflictService {

    private final ConflictRepository conflictRepository;
    private final CountryRepository countryRepository;

    public ConflictServiceImpl(ConflictRepository conflictRepository, CountryRepository countryRepository) {
        this.conflictRepository = conflictRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<ConflictDto> findAll(String status) {
        List<Conflict> list;
        if (status == null || status.isBlank()) {
            list = conflictRepository.findAll();
        } else {
            try {
                ConflictStatus cs = ConflictStatus.valueOf(status.toUpperCase());
                list = conflictRepository.findByStatus(cs);
            } catch (IllegalArgumentException ex) {
                return Collections.emptyList();
            }
        }
        return list.stream().map(ConflictMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ConflictDto> findById(Long id) {
        return conflictRepository.findById(id).map(ConflictMapper::toDto);
    }

    @Override
    public ConflictDto create(ConflictCreateDto dto) {
        Conflict c = new Conflict();
        c.setName(dto.getName());
        c.setStartDate(dto.getStartDate());
        c.setDescription(dto.getDescription());
        c.setRegion(dto.getRegion());
        if (dto.getStatus() != null) {
            try { c.setStatus(ConflictStatus.valueOf(dto.getStatus().toUpperCase())); } catch (Exception ignored) {}
        }
        if (dto.getCountries() != null) {
            Set<Country> countries = dto.getCountries().stream()
                    .map(input -> countryRepository.findByCode(input.getCode())
                            .orElseGet(() -> {
                                Country nc = new Country();
                                nc.setCode(input.getCode());
                                nc.setName(input.getName());
                                return countryRepository.save(nc);
                            }))
                    .collect(Collectors.toSet());
            c.setCountries(countries);
        }
        return ConflictMapper.toDto(conflictRepository.save(c));
    }

    @Override
    public Optional<ConflictDto> update(Long id, ConflictUpdateDto dto) {
        Optional<Conflict> opt = conflictRepository.findById(id);
        if (opt.isEmpty()) return Optional.empty();
        Conflict c = opt.get();
        if (dto.getName() != null) c.setName(dto.getName());
        if (dto.getStartDate() != null) c.setStartDate(dto.getStartDate());
        if (dto.getStatus() != null) {
            try { c.setStatus(ConflictStatus.valueOf(dto.getStatus().toUpperCase())); } catch (Exception ignored) {}
        }
        if (dto.getDescription() != null) c.setDescription(dto.getDescription());
        if (dto.getCountryCodes() != null) {
            Set<Country> countries = dto.getCountryCodes().stream()
                    .map(code -> countryRepository.findByCode(code)
                            .orElseGet(() -> {
                                Country nc = new Country();
                                nc.setCode(code);
                                nc.setName(code);
                                return countryRepository.save(nc);
                            }))
                    .collect(Collectors.toSet());
            c.setCountries(countries);
        }
        return Optional.of(ConflictMapper.toDto(conflictRepository.save(c)));
    }

    @Override
    public boolean delete(Long id) {
        if (!conflictRepository.existsById(id)) return false;
        conflictRepository.deleteById(id);
        return true;
    }
}