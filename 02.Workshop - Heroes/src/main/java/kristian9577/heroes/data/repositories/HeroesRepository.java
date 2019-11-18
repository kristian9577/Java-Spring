package kristian9577.heroes.data.repositories;

import kristian9577.heroes.data.models.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroesRepository extends JpaRepository<Hero, Long> {
    Optional<Hero> getByNameIgnoreCase(String name);
}