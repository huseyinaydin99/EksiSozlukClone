package tr.com.huseyinaydin.application.features.commands.entrycomment.favorite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.huseyinaydin.infrastructure.mediator.Request;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEntryCommentFavoriteCommand implements Request<Boolean> {
    private UUID entryCommentId;
    private UUID userId;
}
