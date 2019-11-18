package kristian9577.heroes.services.models;

import kristian9577.heroes.data.models.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroDetailsServiceModel {
    private String name;
    private Gender gender;
    private int level;
    private int stamina;
    private int strength;
    private int attack;
    private int defence;

    private HeroItemServiceModel weapon;
    private HeroItemServiceModel pads;
    private HeroItemServiceModel gauntlets;
    private HeroItemServiceModel pauldrons;
    private HeroItemServiceModel helmet;
}