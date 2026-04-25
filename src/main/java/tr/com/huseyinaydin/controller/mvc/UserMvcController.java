package tr.com.huseyinaydin.controller.mvc;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tr.com.huseyinaydin.application.dto.UserDetailDto;
import tr.com.huseyinaydin.application.features.commands.user.password.ChangeUserPasswordCommand;
import tr.com.huseyinaydin.application.features.commands.user.update.UpdateUserCommand;
import tr.com.huseyinaydin.application.features.queries.user.GetUserDetailQuery;
import tr.com.huseyinaydin.controller.base.BaseMvcController;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.infrastructure.exceptions.BusinessException;

import java.security.Principal;

@Controller
public class UserMvcController extends BaseMvcController {

    @GetMapping("/profile/{userName}")
    public String profile(@PathVariable String userName, Model model, Principal principal) {
        UserDetailDto userDetail = mediator.send(new GetUserDetailQuery(userName));
        model.addAttribute("user", userDetail);
        String currentUserName = (principal != null) ? principal.getName() : null;
        model.addAttribute("currentUserName", currentUserName);
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

    @PostMapping("/profile/change-password")
    @ResponseBody
    public ResponseEntity<?> changePassword(@RequestParam String oldPassword, 
                                            @RequestParam String newPassword, 
                                            @RequestParam String confirmNewPassword,
                                            Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body(java.util.Map.of("message", "Giriş yapmalısınız."));
        }
        
        User user = userService.getByUserName(principal.getName());
        if (user == null) {
            return ResponseEntity.status(404).body(java.util.Map.of("message", "Kullanıcı bulunamadı."));
        }
        
        mediator.send(new ChangeUserPasswordCommand(user.getId(), oldPassword, newPassword, confirmNewPassword));
        return ResponseEntity.ok(java.util.Map.of("message", "Şifre başarıyla değiştirildi."));
    }
}
