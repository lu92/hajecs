package hajecs.model.Graph;


import hajecs.model.Task.AbstractTask;

/**
 * Created by lucjan on 07.05.15.
 */
public class TaskNode extends AbstractNode {
    private AbstractTask abstractTask;

    public TaskNode(String name) {
        super(name);
    }
}
