package hajecs.repositories;

import hajecs.model.personalData.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lucjan on 08.05.15.
 */
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
