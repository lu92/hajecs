package hajecs.repositories;

import hajecs.model.Graph.AbstractNode;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by lucjan on 15.05.15.
 */
public interface DBNodeRepository extends GraphRepository<AbstractNode> {
    AbstractNode findNodeByName(String name);
}
