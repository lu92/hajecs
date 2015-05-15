package hajecs.repositories;

import hajecs.model.Task.SingleTask;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by lucjan on 15.05.15.
 */
public interface SingleTaskRepository extends GraphRepository<SingleTask> {
}
