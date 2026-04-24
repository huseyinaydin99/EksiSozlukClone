package tr.com.huseyinaydin.application.features.commands.entrycomment.favorite;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.domain.models.EntryComment;
import tr.com.huseyinaydin.domain.models.EntryCommentFavorite;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.infrastructure.mediator.RequestHandler;
import tr.com.huseyinaydin.repository.EntryCommentFavoriteRepository;
import tr.com.huseyinaydin.repository.EntryCommentRepository;
import tr.com.huseyinaydin.repository.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateEntryCommentFavoriteCommandHandler implements RequestHandler<CreateEntryCommentFavoriteCommand, Boolean> {
    private final EntryCommentFavoriteRepository entryCommentFavoriteRepository;
    private final EntryCommentRepository entryCommentRepository;
    private final UserRepository userRepository;

    @Override
    public Boolean handle(CreateEntryCommentFavoriteCommand request) {
        Optional<EntryCommentFavorite> existingFavorite = entryCommentFavoriteRepository.findByEntryCommentIdAndCreatedUserId(request.getEntryCommentId(), request.getUserId());

        if (existingFavorite.isPresent()) {
            entryCommentFavoriteRepository.delete(existingFavorite.get());
        } else {
            EntryComment comment = entryCommentRepository.findById(request.getEntryCommentId()).orElseThrow();
            User user = userRepository.findById(request.getUserId()).orElseThrow();

            EntryCommentFavorite favorite = new EntryCommentFavorite();
            favorite.setEntryComment(comment);
            favorite.setCreatedUser(user);
            entryCommentFavoriteRepository.save(favorite);
        }
        return true;
    }
}
