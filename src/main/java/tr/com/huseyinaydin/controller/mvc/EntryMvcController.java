package tr.com.huseyinaydin.controller.mvc;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tr.com.huseyinaydin.application.features.commands.entry.create.CreateEntryCommand;
import tr.com.huseyinaydin.controller.base.BaseMvcController;
import tr.com.huseyinaydin.domain.models.Entry;
import tr.com.huseyinaydin.domain.models.EntryComment;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.repository.UserRepository;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/entry")
public class EntryMvcController extends BaseMvcController {

    private final UserRepository userRepository;

    public EntryMvcController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/create")
    public String createEntryPage(Model model, Principal principal) {
        if (principal == null) return "redirect:/auth/login";
        model.addAttribute("command", new CreateEntryCommand());
        return "entry-create";
    }

    @PostMapping("/create")
    public String createEntry(@ModelAttribute CreateEntryCommand command, Principal principal) {
        if (principal == null) return "redirect:/auth/login";

        User user = userRepository.findByEmailAddress(principal.getName())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        command.setCreatedById(user.getId());
        UUID entryId = mediator.send(command);

        return "redirect:/entry/" + entryId;
    }

    @PostMapping("/comment")
    public String createComment(@RequestParam UUID entryId, 
                                @RequestParam String content, 
                                @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/auth/login";
        }
        
        Entry entry = entryService.getById(entryId);
        User user = (User) userDetails;
        
        EntryComment comment = new EntryComment();
        comment.setContent(content);
        comment.setEntry(entry);
        comment.setCreatedBy(user);
        
        entryCommentService.create(comment);
        
        return "redirect:/entry/" + entryId;
    }
}
