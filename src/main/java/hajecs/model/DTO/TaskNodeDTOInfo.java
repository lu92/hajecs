package hajecs.model.DTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 24.05.15.
 */
public class TaskNodeDTOInfo {
    private Long id;
    private String name;
    private Set<Long> neighbours = new HashSet<>();
    private Long taskId;

    public TaskNodeDTOInfo() {
    }

    public TaskNodeDTOInfo(Long id, String name, Set<Long> neighbours, Long taskId) {
        this.id = id;
        this.name = name;
        this.neighbours = neighbours;
        this.taskId = taskId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<Long> neighbours) {
        this.neighbours = neighbours;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
