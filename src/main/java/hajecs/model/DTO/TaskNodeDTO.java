package hajecs.model.DTO;


/**
 * Created by lucjan on 24.05.15.
 */
public class TaskNodeDTO {
    private String name;

    public TaskNodeDTO() {
    }

    public TaskNodeDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
