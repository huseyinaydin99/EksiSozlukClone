package tr.com.huseyinaydin.controller.advice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import tr.com.huseyinaydin.application.dto.EntrySummaryDto;
import tr.com.huseyinaydin.application.features.queries.entry.getall.GetAllEntriesQuery;
import tr.com.huseyinaydin.infrastructure.mediator.Mediator;

import java.util.List;

@ControllerAdvice(basePackages = "tr.com.huseyinaydin.controller.mvc")
@RequiredArgsConstructor
public class GlobalModelAdvice {

    private final Mediator mediator;

    @ModelAttribute("entries")
    public List<EntrySummaryDto> getSidebarEntries() {
        return mediator.send(new GetAllEntriesQuery());
    }

    @ModelAttribute("requestURI")
    public String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
