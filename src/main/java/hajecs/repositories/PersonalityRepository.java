package hajecs.repositories;

import hajecs.model.personalData.Personality;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by lucjan on 14.05.15.
 */
public interface PersonalityRepository extends GraphRepository<Personality> {
    Personality findByNameAndLastname(String name, String lastname);
}
