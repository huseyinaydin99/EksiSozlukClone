package tr.com.huseyinaydin.application.features.commands.entry.favorite;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.domain.models.Entry;
import tr.com.huseyinaydin.domain.models.EntryFavorite;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.infrastructure.mediator.RequestHandler;
import tr.com.huseyinaydin.repository.EntryFavoriteRepository;
import tr.com.huseyinaydin.repository.EntryRepository;
import tr.com.huseyinaydin.repository.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateEntryFavoriteCommandHandler implements RequestHandler<CreateEntryFavoriteCommand, Boolean> {
    private final EntryFavoriteRepository entryFavoriteRepository;
    private final EntryRepository entryRepository;
    private final UserRepository userRepository;

    @Override
    public Boolean handle(CreateEntryFavoriteCommand request) {
        Optional<EntryFavorite> existingFavorite = entryFavoriteRepository.findByEntryIdAndCreatedUserId(request.getEntryId(), request.getUserId());

        if (existingFavorite.isPresent()) {
            entryFavoriteRepository.delete(existingFavorite.get());
        } else {
            Entry entry = entryRepository.findById(request.getEntryId()).orElseThrow();
            User user = userRepository.findById(request.getUserId()).orElseThrow();

            EntryFavorite favorite = new EntryFavorite();
            favorite.setEntry(entry);
            favorite.setCreatedUser(user);
            entryFavoriteRepository.save(favorite);
        }
        return true;
    }
}
