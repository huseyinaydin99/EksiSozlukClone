package tr.com.huseyinaydin.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tr.com.huseyinaydin.application.features.commands.entry.favorite.CreateEntryFavoriteCommand;
import tr.com.huseyinaydin.application.features.commands.entrycomment.favorite.CreateEntryCommentFavoriteCommand;
import tr.com.huseyinaydin.controller.base.BaseApiController;
import tr.com.huseyinaydin.domain.models.User;

import java.util.UUID;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController extends BaseApiController {

    @PostMapping("/entry/{entryId}")
    public ResponseEntity<Boolean> createEntryFavorite(@PathVariable UUID entryId, @AuthenticationPrincipal User user) {
        CreateEntryFavoriteCommand command = CreateEntryFavoriteCommand.builder()
                .entryId(entryId)
                .userId(user.getId())
                .build();
        return ResponseEntity.ok(mediator.send(command));
    }

    @PostMapping("/entry-comment/{entryCommentId}")
    public ResponseEntity<Boolean> createEntryCommentFavorite(@PathVariable UUID entryCommentId, @AuthenticationPrincipal User user) {
        CreateEntryCommentFavoriteCommand command = CreateEntryCommentFavoriteCommand.builder()
                .entryCommentId(entryCommentId)
                .userId(user.getId())
                .build();
        return ResponseEntity.ok(mediator.send(command));
    }
}
