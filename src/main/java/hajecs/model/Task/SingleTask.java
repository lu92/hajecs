package hajecs.model.Task;

import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.Transient;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;


/**
 * Created by lucjan on 07.05.15.
 */

@NodeEntity
public class SingleTask {

    @GraphId
    private Long id;
    private String duration;    //  czas trwania np 13-14 w godzinach
    private String task;

    @Transient
    private boolean executed;


    @Fetch
    @RelatedTo(type = "SINGLE_TASK_RELATION", direction = Direction.BOTH)
    private AbstractTask abstractTask;

    public SingleTask() {
    }

    public SingleTask(String taskName) {
        this.duration = "";
        this.task = taskName;
        this.executed = false;
    }

    public SingleTask(String duration, String taskName) {
        this(taskName);
        this.duration = duration;
    }

    public SingleTask(Long id, String duration, String taskName, boolean executed) {
        this.id = id;
        this.duration = duration;
        this.task = taskName;
        this.executed = executed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public AbstractTask getAbstractTask() {
        return abstractTask;
    }

    public void setAbstractTask(AbstractTask abstractTask) {
        this.abstractTask = abstractTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleTask that = (SingleTask) o;

        if (!duration.equals(that.duration)) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!task.equals(that.task)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + duration.hashCode();
        result = 31 * result + task.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SingleTask{" +
                "id=" + id +
                ", duration='" + duration + '\'' +
                ", task='" + task + '\'' +
                ", executed=" + executed +
                '}';
    }
}
