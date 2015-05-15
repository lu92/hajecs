package hajecs.repositories;

import hajecs.model.Graph.AbstractGraph;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by lucjan on 15.05.15.
 */
public interface DBGraphRepository extends GraphRepository<AbstractGraph> {
    AbstractGraph findByName(String name);
}
