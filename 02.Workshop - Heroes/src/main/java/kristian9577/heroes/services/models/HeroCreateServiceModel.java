package kristian9577.heroes.services.models;

import kristian9577.heroes.data.models.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroCreateServiceModel {
    private String name;
    private Gender gender;
}