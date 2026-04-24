package tr.com.huseyinaydin.application.features.queries.entry.getall;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.application.dto.EntrySummaryDto;
import tr.com.huseyinaydin.application.mapper.EntryMapper;
import tr.com.huseyinaydin.domain.models.Entry;
import tr.com.huseyinaydin.infrastructure.mediator.RequestHandler;
import tr.com.huseyinaydin.repository.EntryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllEntriesQueryHandler implements RequestHandler<GetAllEntriesQuery, List<EntrySummaryDto>> {
    private final EntryRepository entryRepository;
    private final EntryMapper entryMapper;

    @Override
    public List<EntrySummaryDto> handle(GetAllEntriesQuery request) {
        List<Entry> entries = entryRepository.findAll();
        return entries.stream()
                .map(entryMapper::toSummaryDto)
                .collect(Collectors.toList());
    }
}
