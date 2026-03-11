package com.backend.apirest.dto;

import java.time.LocalDate;
import java.util.List;

public class ConflictCreateDto {
    private String name;
    private LocalDate startDate;
    private String status;
    private String description;
    private List<String> countryCodes; // list of codes like "UKR"

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<String> getCountryCodes() { return countryCodes; }
    public void setCountryCodes(List<String> countryCodes) { this.countryCodes = countryCodes; }
}