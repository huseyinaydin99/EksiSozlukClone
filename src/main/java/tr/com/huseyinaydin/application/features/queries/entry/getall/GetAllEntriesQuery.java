package tr.com.huseyinaydin.application.features.queries.entry.getall;

import lombok.Data;
import tr.com.huseyinaydin.application.dto.EntrySummaryDto;
import tr.com.huseyinaydin.infrastructure.mediator.Request;

import java.util.List;

@Data
public class GetAllEntriesQuery implements Request<List<EntrySummaryDto>> {
}
