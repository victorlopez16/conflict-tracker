package com.backend.apirest.dto;

import java.time.LocalDate;
import java.util.List;

public class ConflictDto {
    private Long id;
    private String name;
    private LocalDate startDate;
    private String status;
    private String description;
    private List<CountryDto> countries;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<CountryDto> getCountries() { return countries; }
    public void setCountries(List<CountryDto> countries) { this.countries = countries; }
}