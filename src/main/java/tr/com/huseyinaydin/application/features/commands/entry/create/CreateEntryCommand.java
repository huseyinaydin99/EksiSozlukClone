package tr.com.huseyinaydin.application.features.commands.entry.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.huseyinaydin.infrastructure.mediator.Request;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEntryCommand implements Request<UUID> {
    private String subject;
    private String content;
    private UUID createdById;

    public CreateEntryCommand(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
}
