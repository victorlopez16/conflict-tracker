package com.backend.apirest.controller;

import com.backend.apirest.dto.ConflictCreateDto;
import com.backend.apirest.model.Conflict;
import com.backend.apirest.service.ConflictService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/conflicts")
public class ConflictWebController {

    private final ConflictService conflictService;

    public ConflictWebController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    @GetMapping
    public String listConflicts(Model model) {
        model.addAttribute("conflicts", conflictService.findAll(null));
        return "conflicts-list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("conflict", new Conflict());
        return "conflict-form";
    }

    @PostMapping("/save")
    public String saveConflict(@ModelAttribute("conflict") Conflict conflict) {
        ConflictCreateDto dto = new ConflictCreateDto();
        dto.setName(conflict.getName());
        dto.setStartDate(conflict.getStartDate());
        dto.setDescription(conflict.getDescription());
        if (conflict.getStatus() != null) {
            dto.setStatus(conflict.getStatus().name());
        }

        conflictService.create(dto);
        return "redirect:/web/conflicts";
    }
}