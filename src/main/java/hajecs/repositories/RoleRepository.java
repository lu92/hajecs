package hajecs.repositories;

import hajecs.model.personalData.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lucjan on 08.05.15.
 */
public interface RoleRepository extends JpaRepository<Role,Long>{
}
