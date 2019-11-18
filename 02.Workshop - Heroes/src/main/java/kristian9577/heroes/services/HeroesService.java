package kristian9577.heroes.services;

import kristian9577.heroes.data.models.Hero;
import kristian9577.heroes.services.models.HeroCreateServiceModel;
import kristian9577.heroes.services.models.HeroDetailsServiceModel;

public interface HeroesService {

    HeroDetailsServiceModel getByName(String name);

    Hero create(HeroCreateServiceModel serviceModel);
}