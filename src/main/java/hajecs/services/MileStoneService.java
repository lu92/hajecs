package hajecs.services;

import hajecs.model.Actors.Manager;
import hajecs.model.Actors.Person;
import hajecs.model.DTO.*;
import hajecs.model.Graph.AbstractNode;
import hajecs.model.Graph.MileStone;
import hajecs.model.Graph.RelationShip;
import hajecs.model.Graph.TaskNode;
import hajecs.model.Task.AbstractTask;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * Created by lucjan on 21.05.15.
 */
public interface MileStoneService {
    long createMileStone(MileStone mileStone);
    void deleteMileStone(long milestoneId);
    MileStone getMileStone(long milestoneId);
    MileStone findMileStoneByName(String milestoneName);
    Set<MileStone> getAllMileStones();
    Set<MileStoneDTOInfo> getAllMileStoneDtoInfos();

    boolean addTaskNodeToMileStone(long milestoneId, long nodeId) throws IllegalArgumentException;
    void removeTaskNodeFromMileStone(long milestoneId, long nodeId) throws IllegalArgumentException;
    Set<TaskNodeDTOInfo> getAllTaskNode(long milestoneId);

    void addRelationShipBetweenTaskNodes(long mileStoneId, long beginTaskNodeId, long endTaskNodeId) throws IllegalArgumentException;
//    void deleteRelationShipBetweenTaskNodes(long milestoneId, long relationShipId);

    Set<RelationShip> getAllRelationShips(long milestoneId);
    Set<RelationShipDTOInfo> getAllRelationShipDtoInfos(long milestoneId);
    void setStartTaskNode(long milestoneId, long nodeId) throws IllegalArgumentException;
    void setEndTaskNode(long milestoneId, long nodeId) throws IllegalArgumentException;

    Set<TaskDTOInfo> getExecutedTasks(long milestoneId);
    Set<TaskDTOInfo> getNotExecutedTasks(long milestoneId);

    Set<PersonDTOInfo> getAllWorkers(long milestoneId);

    boolean setManager(long milestoneId, long managerId) throws IllegalArgumentException;
    PersonDTOInfo getManager(long milestoneId);
}
