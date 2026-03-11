package com.backend.apirest.repository;

import com.backend.apirest.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> { }