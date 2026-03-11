package com.backend.apirest.repository;

import com.backend.apirest.model.Conflict;
import com.backend.apirest.model.ConflictStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConflictRepository extends JpaRepository<Conflict, Long> {
    List<Conflict> findByStatus(ConflictStatus status);
    List<Conflict> findDistinctByCountries_Code(String code);
}