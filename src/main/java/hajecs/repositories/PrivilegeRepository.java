package hajecs.repositories;

import hajecs.model.personalData.Privilege;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lucjan on 14.05.15.
 */
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    Privilege findById(long id);
    Privilege findByPrivilegeName(String name);
}
