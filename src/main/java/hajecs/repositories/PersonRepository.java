package hajecs.repositories;

import hajecs.model.Actors.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lucjan on 08.05.15.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
