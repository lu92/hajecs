package hajecs.repositories;

import hajecs.model.personalData.Role;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lucjan on 14.05.15.
 */
public interface RoleRepository extends GraphRepository<Role> {
    Role findByRoleName(String name);
}
