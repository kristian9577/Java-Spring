package kristian9577.heroes.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroItemServiceModel {
    private String name;
    private int stamina;
    private int strength;
    private int attack;
    private int defence;
}