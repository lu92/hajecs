package hajecs.model.DTO;

import hajecs.factories.TaskType;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 21.05.15.
 */
public class TaskDTOInfo {
    private Long id;
    private TaskType taskType;
    private Long nodeId;
    private Long mileStoneId;

    private String name;
    private String describe;
    private String start;
    private String deadline;

    private Set<Long> workersId = new HashSet<>();
    private Set<Long> rolesId = new HashSet<>();
    private Set<SingleTaskDTOInfo> singleTasks = new HashSet<>();

    public TaskDTOInfo() {
    }

    public TaskDTOInfo(Long id, TaskType taskType, Long nodeId, Long mileStoneId, String name, String describe, String start, String deadline, Set<Long> workersId, Set<Long> rolesId, Set<SingleTaskDTOInfo> singleTasksId) {
        this.id = id;
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

    public void addWorker(Long ... persons) {
        for (Long personId : persons)
            workersId.add(personId);
    }

    public void addSingleTasksId(SingleTaskDTOInfo ... singleTaskIds) {
        for (SingleTaskDTOInfo singleTaskDTOInfo : singleTaskIds)
            singleTasks.add(singleTaskDTOInfo);
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getMileStoneId() {
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

    public Set<SingleTaskDTOInfo> getSingleTasks() {
        return singleTasks;
    }

    public void setSingleTasks(Set<SingleTaskDTOInfo> singleTasks) {
        this.singleTasks = singleTasks;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public void setMileStoneId(Long mileStoneId) {
        this.mileStoneId = mileStoneId;
    }
}
