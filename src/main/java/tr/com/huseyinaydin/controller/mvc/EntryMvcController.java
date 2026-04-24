package tr.com.huseyinaydin.controller.mvc;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tr.com.huseyinaydin.controller.base.BaseMvcController;
import tr.com.huseyinaydin.domain.models.Entry;
import tr.com.huseyinaydin.domain.models.EntryComment;
import tr.com.huseyinaydin.domain.models.User;

import java.util.UUID;

@Controller
@RequestMapping("/entry")
public class EntryMvcController extends BaseMvcController {

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
