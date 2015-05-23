package hajecs.services;

import hajecs.model.Actors.Manager;
import hajecs.model.Actors.Person;
import hajecs.model.DTO.DTOConverter;
import hajecs.model.DTO.NodeDTO;
import hajecs.model.DTO.TaskDTO;
import hajecs.model.Graph.AbstractNode;
import hajecs.model.Graph.MileStone;
import hajecs.model.Graph.TaskNode;
import hajecs.model.Task.AbstractTask;
import hajecs.repositories.DBGraphRepository;
import hajecs.repositories.DBNodeRepository;
import hajecs.repositories.PersonRepository;
import hajecs.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by lucjan on 21.05.15.
 */

@Service
public class MileStoneServiceImpl implements MileStoneService {


    private Logger logger = Logger.getLogger(String.valueOf(this));

    @Autowired
    private DBGraphRepository dbGraphRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DBNodeRepository dbNodeRepository;

    @Autowired
    private PersonRepository personRepository;


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


    @Override // przetestowac
    public void deleteNodes(long milestoneId, long... nodesId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        for (AbstractNode node : mileStone.findNodes(nodesId)) {
            dbNodeRepository.delete(node);
        }
        mileStone.removeNodes(nodesId);
        dbGraphRepository.save(mileStone);
    }

    @Override
    public void deleteMileStone(long milestoneId) {
        dbGraphRepository.delete(milestoneId);
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
    public void deleteTask(long milestoneId, long nodeId) {
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
    public void setStartTaskNode(long milestoneId, long nodeId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        TaskNode startTaskNode = (TaskNode) mileStone.findNode(nodeId);
        mileStone.setStartTaskNode(startTaskNode);
        dbGraphRepository.save(mileStone);
    }

    @Override
    public void setEndTaskNode(long milestoneId, long nodeId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        TaskNode endTaskNode = (TaskNode) mileStone.findNode(nodeId);
        mileStone.setEndTaskNode(endTaskNode);
        dbGraphRepository.save(mileStone);
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
    public void setManager(long milestoneId, long managerId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        mileStone.setManager((Manager) personRepository.findOne(managerId));
        dbGraphRepository.save(mileStone);
    }

    @Override
    public Manager getManager(long milestoneId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        return mileStone.getManager();
    }

//    @Override
//    public void addPersonToTask(long milestoneId, long taskId, long ... personsId) {
//        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
//        Person person = personRepository.findOne(personId);
//        for (AbstractNode node : mileStone.getNodeStorage()) {
//            TaskNode taskNode = (TaskNode) node;
//            if (taskNode.getTask().getId() == taskId ) {
//                taskNode.getTask().addWorkers(person);
//            }
//        }
//    }

    @Override
    public void addPersonToTask(long milestoneId, long taskId, long ... personsId) {
        MileStone mileStone = (MileStone) dbGraphRepository.findOne(milestoneId);
        for (long personId : personsId) {
            Person person = personRepository.findOne(personId);
            for (AbstractNode node : mileStone.getNodeStorage()) {
                TaskNode taskNode = (TaskNode) node;
                if (taskNode.getTask().getId() == taskId) {
                    taskNode.getTask().addWorkers(person);
                }
            }
        }
    }


}
