package hajecs.repositories;

import hajecs.model.Task.AbstractTask;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by lucjan on 15.05.15.
 */
public interface TaskRepository extends GraphRepository<AbstractTask> {
}
