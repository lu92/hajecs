package hajecs.repositories;

import hajecs.model.Graph.TaskNode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lucjan on 08.05.15.
 */
public interface TaskNodeRepository extends JpaRepository<TaskNode, Long> {
}
