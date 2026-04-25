package tr.com.huseyinaydin.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tr.com.huseyinaydin.application.features.queries.topic.GetTopicAutocompleteQuery;
import tr.com.huseyinaydin.infrastructure.mediator.Mediator;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicApiController {
    private final Mediator mediator;

    @GetMapping("/search")
    public List<String> search(@RequestParam String term) {
        return mediator.send(new GetTopicAutocompleteQuery(term));
    }
}
