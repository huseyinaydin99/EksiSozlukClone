package tr.com.huseyinaydin.application.features.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tr.com.huseyinaydin.domain.models.VoteType;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class EntryVoteChangedEvent {
    private UUID entryId;
    private VoteType voteType;
    private UUID userId;
}
