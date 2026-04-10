package com.backend.apirest.dto;

import java.time.LocalDate;
import java.util.List;

public class ConflictUpdateDto {
    private String name;
    private LocalDate startDate;
    private String status;
    private String description;
    private String region;
    private List<String> countryCodes;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public List<String> getCountryCodes() { return countryCodes; }
    public void setCountryCodes(List<String> countryCodes) { this.countryCodes = countryCodes; }
}