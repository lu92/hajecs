package hajecs.repositories;

import hajecs.model.test.AbstractObject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lucjan on 11.05.15.
 */
public interface ObjectRepository extends JpaRepository<AbstractObject, Long> {
}
