package tr.com.huseyinaydin.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tr.com.huseyinaydin.application.dto.UserDetailDto;
import tr.com.huseyinaydin.controller.base.BaseMvcController;
import tr.com.huseyinaydin.domain.models.User;

import java.util.stream.Collectors;

@Controller
public class UserMvcController extends BaseMvcController {

    @GetMapping("/profile/{userName}")
    public String profile(@PathVariable String userName, Model model) {
        User user = userService.getByUserName(userName);
        
        UserDetailDto userDetail = UserDetailDto.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .emailAddress(user.getEmailAddress())
                .entries(user.getEntries().stream()
                        .map(entryMapper::toDetailDto)
                        .collect(Collectors.toList()))
                .build();
        
        model.addAttribute("user", userDetail);
        return "user-profile";
    }
}
