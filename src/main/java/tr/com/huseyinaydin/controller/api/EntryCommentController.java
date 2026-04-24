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

@RestController
@RequestMapping("/api/entry-comments")
public class EntryCommentController extends BaseApiController {
}
