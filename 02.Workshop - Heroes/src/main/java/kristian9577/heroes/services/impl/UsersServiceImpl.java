package kristian9577.heroes.services.impl;

import kristian9577.heroes.data.models.Hero;
import kristian9577.heroes.data.models.User;
import kristian9577.heroes.data.repositories.UsersRepository;
import kristian9577.heroes.services.HeroesService;
import kristian9577.heroes.services.UsersService;
import kristian9577.heroes.services.models.HeroCreateServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    private final HeroesService heroesService;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    public UsersServiceImpl(HeroesService heroesService, UsersRepository usersRepository, ModelMapper mapper) {
        this.heroesService = heroesService;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }

    @Override
    public void createHeroForUser(String username, HeroCreateServiceModel heroServiceModel) {
        User user = usersRepository.findByUsername(username);
        Hero hero = heroesService.create(heroServiceModel);
        user.setHero(hero);
        usersRepository.save(user);
    }
}
