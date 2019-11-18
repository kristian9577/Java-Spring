package kristian9577.heroes.web.controllers;

import kristian9577.heroes.data.models.auth.RegisterUserServiceModel;
import kristian9577.heroes.services.AuthService;
import kristian9577.heroes.services.models.LoginUserServiceModel;
import kristian9577.heroes.web.models.RegisterUserModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class AuthController {

    private AuthService authService;
    private ModelMapper modelMapper;

    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "auth/login.html";
    }

    @GetMapping("/register")
    public String getRegisterForm() {
        return "auth/register.html";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterUserModel model) {
        RegisterUserServiceModel serviceModel = modelMapper.map(model, RegisterUserServiceModel.class);
        authService.register(serviceModel);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute RegisterUserModel model, HttpSession session) {
        RegisterUserServiceModel serviceModel = modelMapper.map(model, RegisterUserServiceModel.class);
        try {
          LoginUserServiceModel loginUserServiceModel = authService.login(serviceModel);
            session.setAttribute("user",loginUserServiceModel);
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/users/login";
        }

    }
}

