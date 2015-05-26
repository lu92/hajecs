package hajecs.services;

import hajecs.model.DTO.AllTaskNodeDTOInfo;
import hajecs.model.DTO.DTOConverter;
import hajecs.model.DTO.TaskNodeDTOInfo;
import hajecs.model.Graph.AbstractNode;
import hajecs.model.Graph.TaskNode;
import hajecs.model.Task.AbstractTask;
import hajecs.repositories.DBNodeRepository;
import hajecs.repositories.DBTaskNodeRepository;
import hajecs.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 24.05.15.
 */
@Service
@Transactional
public class NodeServiceImpl implements NodeService {

    @Autowired
    private DBTaskNodeRepository dbTaskNodeRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DBNodeRepository dbNodeRepository;

    @Override
    public long createTaskNode(TaskNode taskNode) throws IllegalArgumentException {
        return dbTaskNodeRepository.save(taskNode).getId();
    }

    @Override
    public boolean setTaskToNode(long nodeId, long taskId) throws IllegalArgumentException {
        TaskNode taskNode = null;
        AbstractTask task = null;
        try {
            taskNode = dbTaskNodeRepository.findOne(nodeId);
        } catch (Exception e) {
            throw  new IllegalArgumentException("Node with id "+ nodeId + " doesn't exist");
        }

        try {
            task = taskRepository.findOne(taskId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Task with id " + taskId + " doesn't exists");
        }

        taskNode.setTask(task);
        dbTaskNodeRepository.save(taskNode);
        return true;
    }


    @Override
    public void deleteNode(long nodeId) {
        dbTaskNodeRepository.delete(nodeId);
    }

    @Override
    public TaskNode getTaskNode(long id) throws IllegalArgumentException{
        return dbTaskNodeRepository.findOne(id);
    }

    @Override
    public Set<TaskNode> getAllTaskNodes() {
        Set<TaskNode> taskNodes = new HashSet<>();
        for (TaskNode taskNode : dbTaskNodeRepository.findAll())
            taskNodes.add(taskNode);
        return taskNodes;
    }

    @Override
    public Set<TaskNodeDTOInfo> getAllTaskNodeDtoInfos() {
        Set<TaskNodeDTOInfo> taskNodeDTOInfos = new HashSet<>();
        for (TaskNode taskNode : getAllTaskNodes())
            taskNodeDTOInfos.add(DTOConverter.toTaskNodeDTOInfo(taskNode));
        return taskNodeDTOInfos;
    }

    @Override
    public AllTaskNodeDTOInfo getAllTaskNodeDtoInfo() {
        AllTaskNodeDTOInfo allTaskNodeDTOInfo = new AllTaskNodeDTOInfo();
//        Result<TaskNode> taskNodes = dbTaskNodeRepository.findAll();
//        for (TaskNode taskNode : taskNodes) {
//            allTaskNodeDTOInfo.addTaskNodeDTOInfo(DTOConverter.toTaskNodeDTOInfo(taskNode));
//        }
        Result<AbstractNode> abstractTasks = dbNodeRepository.findAll();
        for (AbstractNode abstractNode : abstractTasks) {
            System.out.println(abstractNode.getName());
            allTaskNodeDTOInfo.addTaskNodeDTOInfo(DTOConverter.toTaskNodeDTOInfo((TaskNode) abstractNode));
        }
        return allTaskNodeDTOInfo;
    }
}
