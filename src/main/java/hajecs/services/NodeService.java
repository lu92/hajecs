package hajecs.services;


import hajecs.model.DTO.AllTaskNodeDTOInfo;
import hajecs.model.DTO.TaskNodeDTOInfo;
import hajecs.model.Graph.TaskNode;

import java.util.Set;

/**
 * Created by lucjan on 24.05.15.
 */
public interface NodeService {
    long createTaskNode(TaskNode taskNode) throws IllegalArgumentException;
    boolean setTaskToNode(long nodeId, long taskId) throws IllegalArgumentException;
    void deleteNode(long nodeId);
    TaskNode getTaskNode(long id) throws IllegalArgumentException;
    Set<TaskNode> getAllTaskNodes();
    Set<TaskNodeDTOInfo> getAllTaskNodeDtoInfos();
    AllTaskNodeDTOInfo getAllTaskNodeDtoInfo();
}
