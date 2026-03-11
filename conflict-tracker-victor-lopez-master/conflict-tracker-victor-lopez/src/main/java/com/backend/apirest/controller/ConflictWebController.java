package com.backend.apirest.controller;

import com.backend.apirest.dto.ConflictCreateDto;
import com.backend.apirest.model.Conflict;
import com.backend.apirest.model.ConflictStatus;
import com.backend.apirest.service.ConflictService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/web/conflicts")
public class ConflictWebController {

    private final ConflictService conflictService;

    public ConflictWebController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    // Convertir String buit a null i String a ConflictStatus enum
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(ConflictStatus.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isBlank()) {
                    setValue(null);
                } else {
                    setValue(ConflictStatus.valueOf(text));
                }
            }
        });
    }

    // GET /web/conflicts → llistat
    @GetMapping
    public String listConflicts(Model model) {
        model.addAttribute("conflicts", conflictService.findAll(null));
        return "conflicts-list";
    }

    // GET /web/conflicts/new → formulari buit
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("conflict", new Conflict());
        return "conflict-form";
    }

    // POST /web/conflicts/save → validació i desar
    @PostMapping("/save")
    public String saveConflict(
            @ModelAttribute("conflict") Conflict conflict,
            BindingResult bindingResult) {

        // Validació manual
        if (conflict.getName() == null || conflict.getName().isBlank()) {
            bindingResult.addError(new FieldError("conflict", "name", "El nom és obligatori"));
        }
        if (conflict.getStartDate() == null) {
            bindingResult.addError(new FieldError("conflict", "startDate", "La data d'inici és obligatòria"));
        }
        if (conflict.getStatus() == null) {
            bindingResult.addError(new FieldError("conflict", "status", "L'estat és obligatori"));
        }

        // Si hi ha errors, tornar al formulari amb els missatges
        if (bindingResult.hasErrors()) {
            return "conflict-form";
        }

        // Construir DTO i cridar servei
        ConflictCreateDto dto = new ConflictCreateDto();
        dto.setName(conflict.getName().trim());
        dto.setStartDate(conflict.getStartDate());
        dto.setDescription(conflict.getDescription());
        dto.setStatus(conflict.getStatus().name());

        conflictService.create(dto);

        // Redirigir al llistat després d'èxit
        return "redirect:/web/conflicts";
    }
}