package kristian9577.heroes.data.repositories;

import kristian9577.heroes.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}