package tr.com.huseyinaydin.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.huseyinaydin.application.dto.EntryDetailDto;
import tr.com.huseyinaydin.application.dto.EntrySummaryDto;
import tr.com.huseyinaydin.application.mapper.EntryMapper;
import tr.com.huseyinaydin.domain.models.Entry;
import tr.com.huseyinaydin.service.interfaces.EntryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/entries")
@RequiredArgsConstructor
public class EntryController {
    private final EntryService entryService;
    private final EntryMapper entryMapper;

    @GetMapping
    public ResponseEntity<List<EntrySummaryDto>> getAll() {
        List<EntrySummaryDto> entries = entryService.getAll().stream()
                .map(entryMapper::toSummaryDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntryDetailDto> getById(@PathVariable UUID id) {
        Entry entry = entryService.getById(id);
        return ResponseEntity.ok(entryMapper.toDetailDto(entry));
    }

    @GetMapping("/search")
    public ResponseEntity<List<EntrySummaryDto>> search(@RequestParam String subject) {
        List<EntrySummaryDto> entries = entryService.searchBySubject(subject).stream()
                .map(entryMapper::toSummaryDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(entries);
    }
}
