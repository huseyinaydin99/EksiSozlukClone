package tr.com.huseyinaydin.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tr.com.huseyinaydin.application.features.commands.entry.vote.CreateEntryVoteCommand;
import tr.com.huseyinaydin.application.features.commands.entrycomment.vote.CreateEntryCommentVoteCommand;
import tr.com.huseyinaydin.controller.base.BaseApiController;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.domain.models.VoteType;

import java.util.UUID;

@RestController
@RequestMapping("/api/votes")
public class VoteController extends BaseApiController {

    @PostMapping("/entry/{entryId}")
    public ResponseEntity<Boolean> createEntryVote(@PathVariable UUID entryId, @RequestParam(defaultValue = "UpVote") VoteType voteType, @AuthenticationPrincipal User user) {
        CreateEntryVoteCommand command = CreateEntryVoteCommand.builder()
                .entryId(entryId)
                .voteType(voteType)
                .createdById(user.getId())
                .build();
        return ResponseEntity.ok(mediator.send(command));
    }

    @PostMapping("/entry-comment/{entryCommentId}")
    public ResponseEntity<Boolean> createEntryCommentVote(@PathVariable UUID entryCommentId, @RequestParam(defaultValue = "UpVote") VoteType voteType, @AuthenticationPrincipal User user) {
        CreateEntryCommentVoteCommand command = CreateEntryCommentVoteCommand.builder()
                .entryCommentId(entryCommentId)
                .voteType(voteType)
                .createdById(user.getId())
                .build();
        return ResponseEntity.ok(mediator.send(command));
    }
}
