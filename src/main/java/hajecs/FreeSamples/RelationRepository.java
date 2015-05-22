package hajecs.FreeSamples;

import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.Set;

/**
 * Created by lucjan on 15.05.15.
 */
public interface RelationRepository extends GraphRepository<Relation> {
    Set<Relation> findByName(String name);
    Set<Relation> findByA(Vertex a);
}
