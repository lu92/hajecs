package hajecs.model.Graph;


import hajecs.model.Task.AbstractTask;

/**
 * Created by lucjan on 07.05.15.
 */
public class TaskNode extends AbstractNode {

    private AbstractTask abstractTask;

    public TaskNode() {
    }

    public TaskNode(String name) {
        super(name);
    }

    public TaskNode(long id, String name) {
        super(id, name);
    }

    public TaskNode(String name, AbstractTask abstractTask) {
        super(name);
        this.abstractTask = abstractTask;
    }

    public TaskNode(long id, String name, AbstractTask abstractTask) {
        super(id, name);
        this.abstractTask = abstractTask;
    }

    public AbstractTask getTask() {
        return abstractTask;
    }

    public void setTask(AbstractTask abstractTask) {
        this.abstractTask = abstractTask;
    }
}
