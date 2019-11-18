package kristian9577.heroes.services.factories;

import kristian9577.heroes.config.Factory;
import kristian9577.heroes.data.models.Hero;
import kristian9577.heroes.data.models.enums.Gender;

@Factory
public class HeroesFactoryImpl implements HeroesFactory {
    @Override
    public Hero create(String name, Gender gender) {
        Hero hero = new Hero();
        hero.setName(name);
        hero.setGender(gender);
        hero.setAttack(1);
        hero.setDefence(1);
        hero.setLevel(1);
        hero.setStamina(1);
        hero.setStrength(1);
        return hero;
    }
}