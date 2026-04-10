package com.backend.apirest.dto;

import java.time.LocalDate;
import java.util.List;

public class ConflictCreateDto {
    private String name;
    private LocalDate startDate;
    private String status;
    private String description;
    private String region;
    private List<CountryInput> countries;

    public static class CountryInput {
        private String name;
        private String code;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
    }

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
    public List<CountryInput> getCountries() { return countries; }
    public void setCountries(List<CountryInput> countries) { this.countries = countries; }
}