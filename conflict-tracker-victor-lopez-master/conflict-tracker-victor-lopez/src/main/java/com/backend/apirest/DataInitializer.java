package com.backend.apirest;

import com.backend.apirest.model.Conflict;
import com.backend.apirest.model.entity.Country;
import com.backend.apirest.model.Event;
import com.backend.apirest.model.Faction;
import com.backend.apirest.model.ConflictStatus;
import com.backend.apirest.repository.ConflictRepository;
import com.backend.apirest.repository.CountryRepository;
import com.backend.apirest.repository.EventRepository;
import com.backend.apirest.repository.FactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@Transactional
public class DataInitializer implements CommandLineRunner {

    private final CountryRepository countryRepository;
    private final ConflictRepository conflictRepository;
    private final FactionRepository factionRepository;
    private final EventRepository eventRepository;

    public DataInitializer(CountryRepository countryRepository,
                           ConflictRepository conflictRepository,
                           FactionRepository factionRepository,
                           EventRepository eventRepository) {
        this.countryRepository = countryRepository;
        this.conflictRepository = conflictRepository;
        this.factionRepository = factionRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(String... args) {
        Country ukr = getOrCreateCountry("UKR", "Ukraine");
        Country rus  = getOrCreateCountry("RUS", "Russia");
        Country esp  = getOrCreateCountry("ESP", "Spain");
        Country mor  = getOrCreateCountry("MOR", "Morocco");

        if (conflictRepository.count() == 0) {
            Conflict conflict = new Conflict();
            conflict.setName("Sample Conflict");
            conflict.setStartDate(LocalDate.of(2022, 2, 24));
            conflict.setStatus(ConflictStatus.ACTIVE);
            conflict.setDescription("Example conflict created by DataInitializer");

            Set<Country> countries = new HashSet<>();
            countries.add(ukr);
            countries.add(rus);
            conflict.setCountries(countries);

            conflict = conflictRepository.save(conflict);

            Faction faction = new Faction();
            faction.setName("Faction A");
            faction.setConflict(conflict);

            Set<Country> supporters = new HashSet<>();
            supporters.add(esp);
            faction.setSupportingCountries(supporters);

            factionRepository.save(faction);

            Event event = new Event();
            event.setEventDate(LocalDate.of(2022, 3, 1));
            event.setLocation("Kyiv");
            event.setDescription("First major event");
            event.setConflict(conflict);
            eventRepository.save(event);
        }
    }

    private Country getOrCreateCountry(String code, String name) {
        Optional<Country> opt = countryRepository.findByCode(code);
        if (opt.isPresent()) {
            return opt.get();
        }
        Country c = new Country();
        c.setCode(code);
        c.setName(name);
        return countryRepository.save(c);
    }
}