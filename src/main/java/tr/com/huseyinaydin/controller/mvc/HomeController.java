package tr.com.huseyinaydin.controller.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tr.com.huseyinaydin.application.dto.EntryDetailDto;
import tr.com.huseyinaydin.application.dto.EntrySummaryDto;
import tr.com.huseyinaydin.application.features.queries.entry.getall.GetAllEntriesQuery;
import tr.com.huseyinaydin.controller.base.BaseMvcController;
import tr.com.huseyinaydin.domain.models.Entry;

import java.util.UUID;

@Controller
public class HomeController extends BaseMvcController {

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
