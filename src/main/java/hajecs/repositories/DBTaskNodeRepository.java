package hajecs.repositories;

import hajecs.model.Graph.TaskNode;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.Set;

/**
 * Created by lucjan on 24.05.15.
 */
public interface DBTaskNodeRepository extends GraphRepository<TaskNode> {
    Set<TaskNode> findByName(String name);
}
