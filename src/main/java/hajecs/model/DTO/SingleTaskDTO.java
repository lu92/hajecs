package hajecs.model.DTO;

/**
 * Created by lucjan on 21.05.15.
 */
public class SingleTaskDTO {
    private Long id;
    private String duration;    //  czas trwania np 13-14 w godzinach
    private String task;

    public SingleTaskDTO() {
    }

    public SingleTaskDTO(Long id, String duration, String task) {
        this.id = id;
        this.duration = duration;
        this.task = task;
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


    @Override
    public String toString() {
        return "SingleTaskDTO{" +
                "id=" + id +
                ", duration='" + duration + '\'' +
                ", task='" + task + '\'' +
                '}';
    }
}
