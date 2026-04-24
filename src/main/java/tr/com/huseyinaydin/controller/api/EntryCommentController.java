package tr.com.huseyinaydin.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tr.com.huseyinaydin.application.features.commands.entrycomment.favorite.CreateEntryCommentFavoriteCommand;
import tr.com.huseyinaydin.application.features.commands.entrycomment.vote.CreateEntryCommentVoteCommand;
import tr.com.huseyinaydin.controller.base.BaseApiController;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.domain.models.VoteType;

import java.util.UUID;

@RequestMapping("/api/comments")
public class EntryCommentController extends BaseApiController {

    @PostMapping("/{id}/vote")
    public ResponseEntity<Boolean> vote(@PathVariable UUID id, @RequestParam VoteType voteType, @AuthenticationPrincipal User user) {
        CreateEntryCommentVoteCommand command = CreateEntryCommentVoteCommand.builder()
                .entryCommentId(id)
                .voteType(voteType)
                .createdById(user.getId())
                .build();
        return ResponseEntity.ok(mediator.send(command));
    }

    @PostMapping("/{id}/favorite")
    public ResponseEntity<Boolean> favorite(@PathVariable UUID id, @AuthenticationPrincipal User user) {
        CreateEntryCommentFavoriteCommand command = CreateEntryCommentFavoriteCommand.builder()
                .entryCommentId(id)
                .userId(user.getId())
                .build();
        return ResponseEntity.ok(mediator.send(command));
    }
}
