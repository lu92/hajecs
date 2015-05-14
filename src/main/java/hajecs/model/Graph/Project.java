package hajecs.model.Graph;

/**
 * Created by lucjan on 07.05.15.
 */
public class Project extends AbstractGraph{
    public Project() {
    }

    public Project(String name, String describe) {
        super(name, describe);
    }

    public Project(Long id, String name, String describe) {
        super(id, name, describe);
    }
}
