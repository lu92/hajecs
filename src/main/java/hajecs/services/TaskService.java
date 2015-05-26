package hajecs.services;

import hajecs.model.DTO.TaskDTOInfo;
import hajecs.model.Task.AbstractTask;

import java.util.Set;

/**
 * Created by lucjan on 21.05.15.
 */
public interface TaskService {
    long createTask(AbstractTask abstractTask);
    AbstractTask getTask(long taskId);
    void deleteTask(long taskId);
    Set<AbstractTask> getAllTasks();
    Set<TaskDTOInfo> getAllTaskDtoInfos();
    boolean addPersonToTask(long taskId, long personId) throws IllegalArgumentException;
    boolean addRoleToTask(long taskId, long roleId) throws IllegalArgumentException;
    boolean isTaskExecuted(long taskId) throws IllegalArgumentException;
    void executeTask(long taskId, long singleTaskToExecuteId) throws IllegalArgumentException;
}
