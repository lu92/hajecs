package hajecs.model.Task;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by lucjan on 07.05.15.
 */

public class SingleTask {

    private Long id;
    private String duration;    //  czas trwania np 13-14 w godzinach
    private String task;
    private boolean executed;


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
