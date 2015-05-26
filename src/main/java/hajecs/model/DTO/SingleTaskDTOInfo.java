package hajecs.model.DTO;

/**
 * Created by lucjan on 21.05.15.
 */
public class SingleTaskDTOInfo {
    private Long id;
    private String duration;    //  czas trwania np 13-14 w godzinach
    private String task;
    private boolean executed;

    public SingleTaskDTOInfo() {
    }

    public SingleTaskDTOInfo(Long id, String duration, String task, boolean executed) {
        this.id = id;
        this.duration = duration;
        this.task = task;
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
}
