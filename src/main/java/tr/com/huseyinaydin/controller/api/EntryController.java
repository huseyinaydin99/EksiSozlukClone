package tr.com.huseyinaydin.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tr.com.huseyinaydin.application.dto.EntryDetailDto;
import tr.com.huseyinaydin.application.dto.EntrySummaryDto;
import tr.com.huseyinaydin.application.features.commands.entry.favorite.CreateEntryFavoriteCommand;
import tr.com.huseyinaydin.application.features.commands.entry.vote.CreateEntryVoteCommand;
import tr.com.huseyinaydin.controller.base.BaseApiController;
import tr.com.huseyinaydin.domain.models.Entry;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.domain.models.VoteType;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/api/entries")
public class EntryController extends BaseApiController {

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

    @PostMapping("/{id}/vote")
    public ResponseEntity<Boolean> vote(@PathVariable UUID id, @RequestParam VoteType voteType, @AuthenticationPrincipal User user) {
        CreateEntryVoteCommand command = CreateEntryVoteCommand.builder()
                .entryId(id)
                .voteType(voteType)
                .createdById(user.getId())
                .build();
        return ResponseEntity.ok(mediator.send(command));
    }

    @PostMapping("/{id}/favorite")
    public ResponseEntity<Boolean> favorite(@PathVariable UUID id, @AuthenticationPrincipal User user) {
        CreateEntryFavoriteCommand command = CreateEntryFavoriteCommand.builder()
                .entryId(id)
                .userId(user.getId())
                .build();
        return ResponseEntity.ok(mediator.send(command));
    }
}
