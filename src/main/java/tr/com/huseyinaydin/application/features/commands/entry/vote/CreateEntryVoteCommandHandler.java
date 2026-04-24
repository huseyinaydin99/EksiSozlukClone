package tr.com.huseyinaydin.application.features.commands.entry.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.application.features.events.EntryVoteChangedEvent;
import tr.com.huseyinaydin.domain.models.Entry;
import tr.com.huseyinaydin.domain.models.EntryVote;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.domain.models.VoteType;
import tr.com.huseyinaydin.infrastructure.mediator.RequestHandler;
import tr.com.huseyinaydin.repository.EntryRepository;
import tr.com.huseyinaydin.repository.EntryVoteRepository;
import tr.com.huseyinaydin.repository.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateEntryVoteCommandHandler implements RequestHandler<CreateEntryVoteCommand, Boolean> {
    private final EntryVoteRepository entryVoteRepository;
    private final EntryRepository entryRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Boolean handle(CreateEntryVoteCommand request) {
        Optional<EntryVote> existingVote = entryVoteRepository.findByEntryIdAndCreatedById(request.getEntryId(), request.getCreatedById());

        if (existingVote.isPresent()) {
            EntryVote vote = existingVote.get();
            if (vote.getVoteType() == request.getVoteType()) {
                entryVoteRepository.delete(vote);
            } else {
                vote.setVoteType(request.getVoteType());
                entryVoteRepository.save(vote);
            }
        } else {
            Entry entry = entryRepository.findById(request.getEntryId()).orElseThrow();
            User user = userRepository.findById(request.getCreatedById()).orElseThrow();

            EntryVote newVote = new EntryVote();
            newVote.setEntry(entry);
            newVote.setCreatedBy(user);
            newVote.setVoteType(request.getVoteType());
            entryVoteRepository.save(newVote);
        }
        
        eventPublisher.publishEvent(new EntryVoteChangedEvent(request.getEntryId(), request.getVoteType(), request.getCreatedById()));
        
        return true;
    }
}
