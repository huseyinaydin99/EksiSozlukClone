package tr.com.huseyinaydin.application.features.queries.topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.huseyinaydin.infrastructure.mediator.Request;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTopicAutocompleteQuery implements Request<List<String>> {
    private String term;
}
