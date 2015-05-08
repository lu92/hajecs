package hajecs.model.Graph;

import javax.persistence.Entity;

/**
 * Created by lucjan on 08.05.15.
 */
@Entity
public class Node extends AbstractNode {
//    only for tests

    public Node(String name) {
        super(name);
    }

    public Node(long id, String name) {
        super(id, name);
    }
}
