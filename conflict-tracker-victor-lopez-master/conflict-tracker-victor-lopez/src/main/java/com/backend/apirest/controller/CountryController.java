package com.backend.apirest.controller;

import com.backend.apirest.dto.ConflictDto;
import com.backend.apirest.service.ConflictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final ConflictService conflictService;

    public CountryController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    @GetMapping("/{code}/conflicts")
    public ResponseEntity<List<ConflictDto>> getConflictsByCountry(@PathVariable String code) {
        List<ConflictDto> list = conflictService.findAll(null).stream()
                .filter(c -> c.getCountries() != null && c.getCountries().stream().anyMatch(co -> code.equalsIgnoreCase(co.getCode())))
                .toList();
        return ResponseEntity.ok(list);
    }
}