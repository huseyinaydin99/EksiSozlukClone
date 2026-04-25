package tr.com.huseyinaydin.application.features.queries.topic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.infrastructure.mediator.RequestHandler;
import tr.com.huseyinaydin.repository.EntryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetTopicAutocompleteQueryHandler implements RequestHandler<GetTopicAutocompleteQuery, List<String>> {
    private final EntryRepository entryRepository;

    @Override
    public List<String> handle(GetTopicAutocompleteQuery request) {
        return entryRepository.findBySubjectContaining(request.getTerm())
                .stream()
                .map(e -> e.getSubject())
                .distinct()
                .limit(10)
                .collect(Collectors.toList());
    }
}
