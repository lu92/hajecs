package hajecs.services;

import hajecs.model.Actors.Manager;
import hajecs.model.Actors.Person;
import hajecs.model.DTO.NodeDTO;
import hajecs.model.DTO.TaskDTO;
import hajecs.model.Graph.MileStone;
import hajecs.model.Graph.TaskNode;
import hajecs.model.Task.AbstractTask;

import java.util.Set;

/**
 * Created by lucjan on 21.05.15.
 */
public interface MileStoneService {
    MileStone findMileStoneById(long id);
    MileStone findMileStoneByName(String name);

    void addNodes(long milestoneId, NodeDTO nodeDTO);
    void addNodes(String milestoneName, NodeDTO nodeDTO);

    MileStone getMileStone(long milestoneId);

    Set<TaskNode> getTaskNodeStorage(long milestoneId);
    Set<TaskNode> findNodes(long mileStoneId, long ... nodesId);


    void deleteNodes(long milestoneId, long... nodesId);
    void deleteMileStone(long milestoneId);

    void addRelationShipBetweenTaskNodes(long mileStoneId, long fromTaskNodeId, long ... destinationTaskNodesId);
    void deleteRelationShipBetweenTaskNodes(long milestoneId, long fromTaskNodeId, long ... destinationTaskNodesId);

    void addTask(long milestoneId, long nodeId, TaskDTO taskDTO);
    void deleteTask(long milestoneId, long nodeId);

    long saveOrUpdateMileStone(MileStone mileStone);

    void setStartTaskNode(long milestoneId, long nodeId);
    void setEndTaskNode(long milestoneId, long nodeId);



    boolean isTaskExecuted(long taskId);
    Set<AbstractTask> getExecutedTasks(long milestoneId);
    Set<AbstractTask> getNotExecutedTasks(long milestoneId);

    Set<Person> getAllWorkers(long milestoneId);

    void setManager(long milestoneId, long managerId);
    Manager getManager(long milestoneId);

    public void addPersonToTask(long milestoneId, long taskId, long ... personsId);

}
