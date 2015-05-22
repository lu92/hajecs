package hajecs.FreeSamples;

import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.Set;

/**
 * Created by lucjan on 15.05.15.
 */
public interface VertexRepository extends GraphRepository<Vertex> {
    Set<Vertex> findByName(String name);
//    Vertex findVertexByName(String name);
}
