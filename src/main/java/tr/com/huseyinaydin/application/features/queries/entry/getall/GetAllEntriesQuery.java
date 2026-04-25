package tr.com.huseyinaydin.application.features.queries.entry.getall;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.huseyinaydin.application.dto.EntrySummaryDto;
import tr.com.huseyinaydin.infrastructure.mediator.Request;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllEntriesQuery implements Request<List<EntrySummaryDto>> {
    private int page = 0;
    private int pageSize = 10;
}
