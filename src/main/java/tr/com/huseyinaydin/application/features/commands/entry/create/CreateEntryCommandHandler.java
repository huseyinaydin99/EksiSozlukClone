package tr.com.huseyinaydin.application.features.commands.entry.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.domain.models.Entry;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.infrastructure.mediator.RequestHandler;
import tr.com.huseyinaydin.repository.EntryRepository;
import tr.com.huseyinaydin.repository.UserRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateEntryCommandHandler implements RequestHandler<CreateEntryCommand, UUID> {
    private final EntryRepository entryRepository;
    private final UserRepository userRepository;

    @Override
    public UUID handle(CreateEntryCommand request) {
        User creator = userRepository.findById(request.getCreatedById())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Entry entry = new Entry();
        entry.setSubject(request.getSubject());
        entry.setContent(request.getContent());
        entry.setCreatedBy(creator);

        Entry savedEntry = entryRepository.save(entry);
        return savedEntry.getId();
    }
}
