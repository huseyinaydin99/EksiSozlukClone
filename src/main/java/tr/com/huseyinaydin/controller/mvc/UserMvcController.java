package tr.com.huseyinaydin.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tr.com.huseyinaydin.application.dto.UserDetailDto;
import tr.com.huseyinaydin.application.features.commands.user.update.UpdateUserCommand;
import tr.com.huseyinaydin.application.features.queries.user.GetUserDetailQuery;
import tr.com.huseyinaydin.controller.base.BaseMvcController;

import java.security.Principal;

@Controller
public class UserMvcController extends BaseMvcController {

    @GetMapping("/profile/{userName}")
    public String profile(@PathVariable String userName, Model model) {
        UserDetailDto userDetail = mediator.send(new GetUserDetailQuery(userName));
        model.addAttribute("user", userDetail);
        return "user-profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Principal principal, Model model) {
        if (principal == null) return "redirect:/auth/login";
        UserDetailDto userDetail = mediator.send(new GetUserDetailQuery(principal.getName()));
        model.addAttribute("user", userDetail);
        return "user-profile-edit";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute UpdateUserCommand command, Principal principal) {
        if (principal == null) return "redirect:/auth/login";
        mediator.send(command);
        return "redirect:/profile/" + command.getUserName();
    }
}
