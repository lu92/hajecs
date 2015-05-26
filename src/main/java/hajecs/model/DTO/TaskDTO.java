package hajecs.model.DTO;

import hajecs.factories.TaskType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 24.05.15.
 */
public class TaskDTO {
    private TaskType taskType;
    private String name;
    private String describe;
    private String start;
    private String deadline;
    private Set<SingleTaskDTO> singleTasks = new HashSet<>();

    public TaskDTO() {
    }

    public TaskDTO(TaskType taskType, String name, String describe, String start, String deadline, Set<SingleTaskDTO> singleTasks) {
        this.taskType = taskType;
        this.name = name;
        this.describe = describe;
        this.start = start;
        this.deadline = deadline;
        this.singleTasks = singleTasks;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Set<SingleTaskDTO> getSingleTasks() {
        return singleTasks;
    }

    public void setSingleTasks(Set<SingleTaskDTO> singleTasks) {
        this.singleTasks = singleTasks;
    }
}
