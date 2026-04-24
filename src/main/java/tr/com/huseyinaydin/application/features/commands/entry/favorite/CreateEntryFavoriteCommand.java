package tr.com.huseyinaydin.application.features.commands.entry.favorite;

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
public class CreateEntryFavoriteCommand implements Request<Boolean> {
    private UUID entryId;
    private UUID userId;
}
