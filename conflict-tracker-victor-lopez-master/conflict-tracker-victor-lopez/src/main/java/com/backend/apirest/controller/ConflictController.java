package com.backend.apirest.controller;

import com.backend.apirest.dto.ConflictCreateDto;
import com.backend.apirest.dto.ConflictDto;
import com.backend.apirest.dto.ConflictUpdateDto;
import com.backend.apirest.service.ConflictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conflicts")
public class ConflictController {

    private final ConflictService conflictService;

    public ConflictController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    @GetMapping
    public ResponseEntity<List<ConflictDto>> getAll(@RequestParam(value = "status", required = false) String status) {
        return ResponseEntity.ok(conflictService.findAll(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConflictDto> getById(@PathVariable Long id) {
        return conflictService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConflictDto> create(@RequestBody ConflictCreateDto dto) {
        ConflictDto created = conflictService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConflictDto> update(@PathVariable Long id, @RequestBody ConflictUpdateDto dto) {
        return conflictService.update(id, dto).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return conflictService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}