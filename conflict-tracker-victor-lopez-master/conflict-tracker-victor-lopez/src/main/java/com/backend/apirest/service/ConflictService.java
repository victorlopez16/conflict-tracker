package com.backend.apirest.service;

import com.backend.apirest.dto.ConflictCreateDto;
import com.backend.apirest.dto.ConflictDto;
import com.backend.apirest.dto.ConflictUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ConflictService {
    List<ConflictDto> findAll(String status);
    Optional<ConflictDto> findById(Long id);
    ConflictDto create(ConflictCreateDto dto);
    Optional<ConflictDto> update(Long id, ConflictUpdateDto dto);
    boolean delete(Long id);
}