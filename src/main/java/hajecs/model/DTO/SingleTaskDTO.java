package hajecs.model.DTO;

/**
 * Created by lucjan on 24.05.15.
 */
public class SingleTaskDTO {
    private String duration;    //  czas trwania np 13-14 w godzinach
    private String task;

    public SingleTaskDTO() {
    }

    public SingleTaskDTO(String duration, String task) {
        this.duration = duration;
        this.task = task;
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
}
