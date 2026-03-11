package com.backend.apirest.repository;

import com.backend.apirest.model.Faction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactionRepository extends JpaRepository<Faction, Long> { }