package tr.com.huseyinaydin.controller.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tr.com.huseyinaydin.application.dto.EntryDetailDto;
import tr.com.huseyinaydin.application.dto.EntrySummaryDto;
import tr.com.huseyinaydin.application.features.queries.entry.getall.GetAllEntriesQuery;
import tr.com.huseyinaydin.controller.base.BaseMvcController;
import tr.com.huseyinaydin.domain.models.Entry;

import java.util.List;
import java.util.UUID;

@Controller
public class HomeController extends BaseMvcController {

    @GetMapping("/")
    public String index(Model model, 
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int pageSize) {
        List<EntrySummaryDto> entries = mediator.send(new GetAllEntriesQuery(page, pageSize));
        Page<EntrySummaryDto> pageData = new PageImpl<>(entries, PageRequest.of(page, pageSize), 100);
        
        model.addAttribute("entries", entries);
        model.addAttribute("pageData", pageData);
        return "index";
    }

    @GetMapping("/entry/{id}")
    public String entryDetail(@PathVariable UUID id, Model model) {
        Entry entry = entryService.getById(id);
        EntryDetailDto entryDetail = entryMapper.toDetailDto(entry);
        model.addAttribute("entry", entryDetail);
        
        var comments = entryCommentService.getByEntryId(id);
        model.addAttribute("comments", comments);
        
        return "entry-detail";
    }
}
