package kristian9577.heroes.services.impl;

import kristian9577.heroes.data.models.Hero;
import kristian9577.heroes.data.models.Item;
import kristian9577.heroes.data.models.enums.Slot;
import kristian9577.heroes.data.repositories.HeroesRepository;
import kristian9577.heroes.services.HeroesService;
import kristian9577.heroes.services.factories.HeroesFactory;
import kristian9577.heroes.services.models.HeroCreateServiceModel;
import kristian9577.heroes.services.models.HeroDetailsServiceModel;
import kristian9577.heroes.services.models.HeroItemServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroesServiceImpl implements HeroesService {
    private final HeroesRepository heroesRepository;
    private final HeroesFactory heroesFactory;
    private final ModelMapper mapper;

    public HeroesServiceImpl(HeroesRepository heroesRepository, HeroesFactory heroesFactory, ModelMapper mapper) {
        this.heroesRepository = heroesRepository;
        this.heroesFactory = heroesFactory;
        this.mapper = mapper;
    }

    @Override
    public HeroDetailsServiceModel getByName(String name) {
        Optional<Hero> heroOptional = heroesRepository.getByNameIgnoreCase(name);
        if (heroOptional.isEmpty()) {
            throw new NullPointerException("No such hero");
        }

        Hero hero = heroOptional.get();

        HeroDetailsServiceModel serviceModel = mapper.map(hero, HeroDetailsServiceModel.class);

        serviceModel.setWeapon(getItemBySlot(hero.getItems(), Slot.WEAPON));
        serviceModel.setGauntlets(getItemBySlot(hero.getItems(), Slot.GAUNTLETS));
        serviceModel.setHelmet(getItemBySlot(hero.getItems(), Slot.HELMET));
        serviceModel.setPads(getItemBySlot(hero.getItems(), Slot.PADS));
        serviceModel.setPauldrons(getItemBySlot(hero.getItems(), Slot.PAULDRON));

        return serviceModel;
    }

    @Override
    public Hero create(HeroCreateServiceModel serviceModel) {
        Hero hero = heroesFactory.create(serviceModel.getName(), serviceModel.getGender());
        heroesRepository.save(hero);
        return hero;
    }

    private HeroItemServiceModel getItemBySlot(List<Item> items, Slot slot) {
        Optional<Item> item = items
                .stream()
                .filter(x -> x.getSlot() == slot)
                .findFirst();

        return item.isPresent()
                ? mapper.map(item, HeroItemServiceModel.class)
                : null;
    }
}