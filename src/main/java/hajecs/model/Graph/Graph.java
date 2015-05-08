package hajecs.model.Graph;

/**
 * Created by lucjan on 08.05.15.
 */
public class Graph extends AbstractGraph {

    public Graph() {
    }

    public Graph(String name, String describe) {
        super(name, describe);
    }

    public Graph(Long id, String name, String describe) {
        super(id, name, describe);
    }
}
