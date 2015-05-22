package hajecs.model.DTO;

import hajecs.factories.TaskType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 21.05.15.
 */
public class TaskDTO {
    private TaskType taskType;
    private long nodeId;
    private long mileStoneId;

    private String name;
    private String describe;
    private String start;
    private String deadline;

    private Set<Long> workersId = new HashSet<>();
    private Set<Long> rolesId = new HashSet<>();
    private Set<SingleTaskDTO> singleTasks = new HashSet<>();

    public TaskDTO() {
    }

    public TaskDTO(TaskType taskType, long nodeId, long mileStoneId, String name, String describe, String start, String deadline, Set<Long> workersId, Set<Long> rolesId, Set<SingleTaskDTO> singleTasksId) {
        this.taskType = taskType;
        this.nodeId = nodeId;
        this.mileStoneId = mileStoneId;
        this.name = name;
        this.describe = describe;
        this.start = start;
        this.deadline = deadline;
        this.workersId = workersId;
        this.rolesId = rolesId;
        this.singleTasks = singleTasksId;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public long getMileStoneId() {
        return mileStoneId;
    }

    public void setMileStoneId(long mileStoneId) {
        this.mileStoneId = mileStoneId;
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

    public Set<Long> getWorkersId() {
        return workersId;
    }

    public void setWorkersId(Set<Long> workersId) {
        this.workersId = workersId;
    }

    public Set<Long> getRolesId() {
        return rolesId;
    }

    public void setRolesId(Set<Long> rolesId) {
        this.rolesId = rolesId;
    }

    public Set<SingleTaskDTO> getSingleTasks() {
        return singleTasks;
    }

    public void setSingleTasks(Set<SingleTaskDTO> singleTasks) {
        this.singleTasks = singleTasks;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "taskType=" + taskType +
                ", nodeId=" + nodeId +
                ", mileStoneId=" + mileStoneId +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", start=" + start +
                ", deadline=" + deadline +
                ", workersId=" + workersId +
                ", rolesId=" + rolesId +
                ", singleTasks=" + singleTasks +
                '}';
    }
}
