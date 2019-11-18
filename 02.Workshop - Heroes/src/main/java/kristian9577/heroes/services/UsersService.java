package kristian9577.heroes.services;

import kristian9577.heroes.services.models.HeroCreateServiceModel;

public interface UsersService {
    void createHeroForUser(String username, HeroCreateServiceModel hero);
}
