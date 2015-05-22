package hajecs.FreeSamples;

import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by lucjan on 15.05.15.
 */

public class B extends Vertex {
    public B() {
    }

    public B(String name) {
        super(name);
    }
}
