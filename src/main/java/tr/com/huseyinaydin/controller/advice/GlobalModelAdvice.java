package tr.com.huseyinaydin.controller.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import tr.com.huseyinaydin.application.dto.EntryDetailDto;
import tr.com.huseyinaydin.application.mapper.EntryMapper;
import tr.com.huseyinaydin.service.interfaces.EntryService;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = "tr.com.huseyinaydin.controller.mvc")
@RequiredArgsConstructor
public class GlobalModelAdvice {

    private final EntryService entryService;
    private final EntryMapper entryMapper;

    @ModelAttribute("entries")
    public List<EntryDetailDto> getSidebarEntries() {
        return entryService.getAll().stream()
                .map(entryMapper::toDetailDto)
                .collect(Collectors.toList());
    }
}
