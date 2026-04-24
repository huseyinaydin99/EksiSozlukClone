package tr.com.huseyinaydin.application.features.commands.entrycomment.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.domain.models.EntryComment;
import tr.com.huseyinaydin.domain.models.EntryCommentVote;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.infrastructure.mediator.RequestHandler;
import tr.com.huseyinaydin.repository.EntryCommentRepository;
import tr.com.huseyinaydin.repository.EntryCommentVoteRepository;
import tr.com.huseyinaydin.repository.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateEntryCommentVoteCommandHandler implements RequestHandler<CreateEntryCommentVoteCommand, Boolean> {
    private final EntryCommentVoteRepository entryCommentVoteRepository;
    private final EntryCommentRepository entryCommentRepository;
    private final UserRepository userRepository;

    @Override
    public Boolean handle(CreateEntryCommentVoteCommand request) {
        Optional<EntryCommentVote> existingVote = entryCommentVoteRepository.findByEntryCommentIdAndCreatedById(request.getEntryCommentId(), request.getCreatedById());

        if (existingVote.isPresent()) {
            EntryCommentVote vote = existingVote.get();
            if (vote.getVoteType() == request.getVoteType()) {
                entryCommentVoteRepository.delete(vote);
            } else {
                vote.setVoteType(request.getVoteType());
                entryCommentVoteRepository.save(vote);
            }
        } else {
            EntryComment comment = entryCommentRepository.findById(request.getEntryCommentId()).orElseThrow();
            User user = userRepository.findById(request.getCreatedById()).orElseThrow();

            EntryCommentVote newVote = new EntryCommentVote();
            newVote.setEntryComment(comment);
            newVote.setCreatedBy(user);
            newVote.setVoteType(request.getVoteType());
            entryCommentVoteRepository.save(newVote);
        }
        return true;
    }
}
