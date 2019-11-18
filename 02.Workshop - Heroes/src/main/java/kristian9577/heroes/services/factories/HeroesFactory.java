package kristian9577.heroes.services.factories;

import kristian9577.heroes.data.models.Hero;
import kristian9577.heroes.data.models.enums.Gender;

public interface HeroesFactory {
    Hero create(String name, Gender gender);
}