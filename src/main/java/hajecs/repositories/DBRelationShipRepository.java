package hajecs.repositories;

import hajecs.model.Graph.RelationShip;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by lucjan on 15.05.15.
 */
public interface DBRelationShipRepository extends GraphRepository<RelationShip> {
}
