package hajecs.model.Graph;


import hajecs.model.Task.AbstractTask;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Created by lucjan on 07.05.15.
 */
public class TaskNode extends AbstractNode {

    @Fetch
    @RelatedTo(type = "TASKNODE_ABSTRACTTASK_RELATION", direction = Direction.BOTH)
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

    @Override
    public String toString() {
        return "TaskNode{" +
                "abstractTask=" + abstractTask.getName() +
                "} " + super.toString();
    }
}
