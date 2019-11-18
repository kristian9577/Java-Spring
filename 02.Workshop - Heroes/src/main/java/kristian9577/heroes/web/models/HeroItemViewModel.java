package kristian9577.heroes.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroItemViewModel {
    private String name;
    private int stamina;
    private int strength;
    private int attack;
    private int defence;
}
