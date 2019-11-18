package kristian9577.heroes.web.controllers;


import kristian9577.heroes.services.HeroesService;
import kristian9577.heroes.services.UsersService;
import kristian9577.heroes.services.models.HeroCreateServiceModel;
import kristian9577.heroes.services.models.HeroDetailsServiceModel;
import kristian9577.heroes.web.controllers.base.BaseController;
import kristian9577.heroes.web.models.HeroCreateModel;
import kristian9577.heroes.web.models.HeroDetailsViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/heroes")
public class HeroesController extends BaseController {
    private final HeroesService heroesService;
    private final ModelMapper mapper;
    private final UsersService usersService;

    public HeroesController(HeroesService heroesService, ModelMapper mapper, UsersService usersService) {
        this.heroesService = heroesService;
        this.mapper = mapper;
        this.usersService = usersService;
    }

    @GetMapping("/details/{name}")
    public ModelAndView getHeroDetails(@PathVariable String name, ModelAndView modelAndView) {
        HeroDetailsServiceModel serviceModel = heroesService.getByName(name);
        HeroDetailsViewModel viewModel = mapper.map(serviceModel, HeroDetailsViewModel.class);
        modelAndView.addObject("hero", viewModel);
        modelAndView.setViewName("heroes/hero-details.html");
        return modelAndView;
    }

    @GetMapping("/create")
    public String getCreateHeroForm(HttpSession session) {
        if(!isAuthenticated(session)) {
            return "redirect:/users/login";
        }
        return "heroes/create-hero.html";
    }

    @PostMapping("/create")
    public String createHero(@ModelAttribute HeroCreateModel hero, HttpSession session) {
        if(!isAuthenticated(session)) {
            return "/";
        }

        String username = getUsername(session);

        HeroCreateServiceModel serviceModel = mapper.map(hero, HeroCreateServiceModel.class);
        usersService.createHeroForUser(username, serviceModel);
        return "redirect:/heroes/details/" + hero.getName();
    }
}
