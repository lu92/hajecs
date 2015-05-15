package hajecs;

import org.springframework.data.repository.CrudRepository;

public interface PersonOldRepository extends CrudRepository<PersonOld, String> {

    PersonOld findByName(String name);

    Iterable<PersonOld> findByTeammatesName(String name);

}
