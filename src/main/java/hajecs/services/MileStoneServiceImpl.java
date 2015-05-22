package hajecs.services;

import hajecs.model.Actors.Person;
import hajecs.model.DTO.DTOConverter;
import hajecs.model.DTO.NodeDTO;
import hajecs.model.DTO.TaskDTO;
import hajecs.model.Graph.AbstractNode;
import hajecs.model.Graph.MileStone;
import hajecs.model.Graph.TaskNode;
import hajecs.model.Task.AbstractTask;
import hajecs.repositories.DBGraphRepository;
import hajecs.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 21.05.15.
 */

@Service
public class MileStoneServiceImpl implements MileStoneService {

    @Autowired
    private DBGraphRepository dbGraphRepository;

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public MileStone findMileStoneById(long id) {
        return (MileStone) dbGraphRepository.findOne(id);
    }

    @Override
    public MileStone findMileStoneByName(String name) {
        return (MileStone) dbGraphRepository.findByName(name);
    }

    @Override
    public void addNodes(long milestoneId, NodeDTO nodeDTO) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        mileStone.addNodes(DTOConverter.toNode(nodeDTO));
        dbGraphRepository.save(mileStone);
    }

    @Override
    public void addNodes(String milestoneName, NodeDTO nodeDTO) {
        MileStone mileStone = (MileStone) dbGraphRepository.findByName(milestoneName);
        mileStone.addNodes(DTOConverter.toNode(nodeDTO));
        dbGraphRepository.save(mileStone);
    }

    @Override
    public MileStone getMileStone(long milestoneId) {
        return (MileStone) dbGraphRepository.findOne(milestoneId);
    }

    @Override
    public Set<TaskNode> getTaskNodeStorage(long milestoneId) {
        Set<TaskNode> taskNodes = new HashSet<>();
        for (AbstractNode node : ((MileStone) dbGraphRepository.findOne(milestoneId)).getNodeStorage()) {
            taskNodes.add((TaskNode) node);
        }
        return taskNodes;
    }

    @Override
    public Set<TaskNode> findNodes(long mileStoneId, long... nodesId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(mileStoneId);
        Set<TaskNode> taskNodes = new HashSet<>();
        for (AbstractNode abstractNode : mileStone.findNodes(nodesId)) {
            taskNodes.add((TaskNode) abstractNode);
        }
        return taskNodes;
    }


    @Override
    public void removeNodes(long milestoneId, long... nodesId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        mileStone.removeNodes(nodesId);
        dbGraphRepository.save(mileStone);
    }


    @Override
    public void addRelationShipBetweenTaskNodes(long mileStoneId, long fromTaskNodeId, long... destinationTaskNodesId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(mileStoneId);
        mileStone.addRelationShips(fromTaskNodeId, destinationTaskNodesId);
        dbGraphRepository.save(mileStone);
    }

    @Override
    public void deleteRelationShipBetweenTaskNodes(long milestoneId, long fromTaskNodeId, long... destinationTaskNodesId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        mileStone.deleteRelationShipsBetweenNodes(fromTaskNodeId, destinationTaskNodesId);
        dbGraphRepository.save(mileStone);
    }

    @Override
    public void addTask(long milestoneId, long nodeId, TaskDTO taskDTO) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        ((TaskNode) mileStone.findNodeById(nodeId)).setTask(DTOConverter.toTask(taskDTO));
        dbGraphRepository.save(mileStone);

    }

    @Override   // zrobic testy
    public void removeTask(long milestoneId, long nodeId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        taskRepository.delete(((TaskNode) mileStone.findNode(nodeId)).getTask());
        ((TaskNode) mileStone.findNode(nodeId)).setTask(null);  // usuwamy referencje
        dbGraphRepository.save(mileStone);
    }

    @Override
    public long saveOrUpdateMileStone(MileStone mileStone) {
        dbGraphRepository.save(mileStone);
        return dbGraphRepository.findByName(mileStone.getName()).getId();
    }

    @Override
    public boolean isTaskExecuted(long taskId) {
        return taskRepository.findOne(taskId).isExecuted();
    }

    @Override
    public Set<AbstractTask> getExecutedTasks(long milestoneId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        return mileStone.getPerformedTasks();
    }

    @Override
    public Set<AbstractTask> getNotExecutedTasks(long milestoneId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        return mileStone.getPerformedTasks();
    }

    @Override
    public Set<Person> getAllWorkers(long milestoneId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        return mileStone.getAllWorkers();
    }

    @Override
    public Person getManager(long milestoneId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        return mileStone.getManager();
    }
}
