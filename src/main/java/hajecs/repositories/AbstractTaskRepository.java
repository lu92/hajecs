package hajecs.repositories;

import hajecs.model.Task.AbstractTask;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lucjan on 08.05.15.
 */
public interface AbstractTaskRepository extends JpaRepository<AbstractTask, Long>{
}


