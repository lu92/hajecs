package hajecs.repositories;

import hajecs.model.Actors.Person;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by lucjan on 14.05.15.
 */
public interface PersonRepository extends GraphRepository<Person> {

}
