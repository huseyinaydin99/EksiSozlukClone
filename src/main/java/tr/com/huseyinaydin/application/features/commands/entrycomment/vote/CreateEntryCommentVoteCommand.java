package tr.com.huseyinaydin.application.features.commands.entrycomment.vote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.huseyinaydin.domain.models.VoteType;
import tr.com.huseyinaydin.infrastructure.mediator.Request;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEntryCommentVoteCommand implements Request<Boolean> {
    private UUID entryCommentId;
    private VoteType voteType;
    private UUID createdById;
}
