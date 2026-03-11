package com.backend.apirest.mapper;

import com.backend.apirest.dto.ConflictCreateDto;
import com.backend.apirest.dto.ConflictDto;
import com.backend.apirest.model.Conflict;
import com.backend.apirest.model.entity.Country;
import com.backend.apirest.dto.CountryDto;

import java.util.List;
import java.util.stream.Collectors;

public class ConflictMapper {

    public static ConflictDto toDto(Conflict c) {
        if (c == null) return null;
        ConflictDto dto = new ConflictDto();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setStartDate(c.getStartDate());
        dto.setStatus(c.getStatus() != null ? c.getStatus().name() : null);
        dto.setDescription(c.getDescription());
        if (c.getCountries() != null) {
            List<CountryDto> countries = c.getCountries().stream().map(ConflictMapper::countryToDto).collect(Collectors.toList());
            dto.setCountries(countries);
        }
        return dto;
    }

    public static CountryDto countryToDto(Country c) {
        CountryDto cd = new CountryDto();
        cd.setId(c.getId());
        cd.setName(c.getName());
        cd.setCode(c.getCode());
        return cd;
    }
}