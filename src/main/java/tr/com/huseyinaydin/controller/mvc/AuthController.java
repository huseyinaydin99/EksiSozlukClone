package tr.com.huseyinaydin.controller.mvc;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.com.huseyinaydin.application.dto.auth.AuthenticationResponse;
import tr.com.huseyinaydin.application.dto.auth.LoginRequest;
import tr.com.huseyinaydin.application.dto.auth.RegisterRequest;
import tr.com.huseyinaydin.controller.base.BaseMvcController;
import tr.com.huseyinaydin.infrastructure.exceptions.BusinessException;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseMvcController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginRequest") LoginRequest loginRequest, 
                        BindingResult bindingResult, 
                        HttpServletResponse response,
                        Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        try {
            AuthenticationResponse authResponse = authService.login(loginRequest);
            Cookie cookie = new Cookie("jwt", authResponse.getToken());
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(86400); // 1 day
            response.addCookie(cookie);
            return "redirect:/";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", "Giriş başarısız.");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerRequest") RegisterRequest registerRequest, 
                           BindingResult bindingResult, 
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            authService.register(registerRequest);
            return "redirect:/auth/login?success";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
