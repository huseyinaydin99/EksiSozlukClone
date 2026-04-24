package tr.com.huseyinaydin.controller.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tr.com.huseyinaydin.application.dto.EntryDetailDto;
import tr.com.huseyinaydin.application.dto.EntrySummaryDto;
import tr.com.huseyinaydin.application.mapper.EntryMapper;
import tr.com.huseyinaydin.domain.models.Entry;
import tr.com.huseyinaydin.service.interfaces.EntryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final EntryService entryService;
    private final EntryMapper entryMapper;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/entry/{id}")
    public String entryDetail(@PathVariable UUID id, Model model) {
        Entry entry = entryService.getById(id);
        EntryDetailDto entryDetail = entryMapper.toDetailDto(entry);
        model.addAttribute("entry", entryDetail);
        return "entry-detail";
    }
}
