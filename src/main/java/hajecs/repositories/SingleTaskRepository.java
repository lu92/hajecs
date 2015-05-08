package hajecs.repositories;

import hajecs.model.Task.SingleTask;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lucjan on 08.05.15.
 */
public interface SingleTaskRepository extends JpaRepository<SingleTask, Long> {
}
