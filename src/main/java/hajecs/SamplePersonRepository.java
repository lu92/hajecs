package hajecs;

/**
 * Created by lucjan on 14.05.15.
 */
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.CrudRepository;

public interface SamplePersonRepository extends GraphRepository<Person> {

    Person findByName(String name);

    Iterable<Person> findByTeammatesName(String name);

}
