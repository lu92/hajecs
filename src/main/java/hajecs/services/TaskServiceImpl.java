package hajecs.services;

import hajecs.model.Actors.Manager;
import hajecs.model.Actors.Person;
import hajecs.model.DTO.DTOConverter;
import hajecs.model.DTO.Response;
import hajecs.model.DTO.TaskDTOInfo;
import hajecs.model.Graph.MileStone;
import hajecs.model.Graph.TaskNode;
import hajecs.model.Task.AbstractTask;
import hajecs.model.Task.SingleTask;
import hajecs.model.personalData.Role;
import hajecs.notificationVisitor.AllocationOfTask;
import hajecs.notificationVisitor.ExecutedTask;
import hajecs.repositories.PersonRepository;
import hajecs.repositories.RoleRepository;
import hajecs.repositories.SingleTaskRepository;
import hajecs.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 24.05.15.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SingleTaskRepository singleTaskRepository;


    @Override
    public long createTask(AbstractTask abstractTask) {
        return taskRepository.save(abstractTask).getId();
    }

    @Override
    public AbstractTask getTask(long taskId) {
        return taskRepository.findOne(taskId);
    }

    @Override
    public void deleteTask(long taskId) {
        taskRepository.delete(taskId);
    }

    @Override
    public Set<AbstractTask> getAllTasks() {
        Set<AbstractTask> tasks = new HashSet<>();
        for (AbstractTask task : taskRepository.findAll())
            tasks.add(task);
        return tasks;
    }

    @Override
    public Set<TaskDTOInfo> getAllTaskDtoInfos() {
        Set<TaskDTOInfo> taskDTOInfos = new HashSet<>();
        for (AbstractTask task : getAllTasks()) {
            taskDTOInfos.add(DTOConverter.toTaskDTOInfo(task));
        }
        return taskDTOInfos;
    }

    @Override
    public boolean addPersonToTask(long taskId, long personId) throws IllegalArgumentException{
        AbstractTask task = null;
        Person person = null;

        try {
            task = taskRepository.findOne(taskId);
        } catch (Exception e) {
            throw new IllegalArgumentException("task with id " + taskId + " doesn't exists");
        }

        try {
            person = personRepository.findOne(personId);
            person.setNewMessage(task.accept(new AllocationOfTask()));
//            person.setNewMessage("hello");
        } catch (Exception e) {
            throw new IllegalArgumentException("person with id " + personId + " doesn't exists");
        }

        task.addWorkers(person);
        taskRepository.save(task);

        return true;
    }

    @Override
    public boolean addRoleToTask(long taskId, long roleId) throws IllegalArgumentException{

        AbstractTask task = null;
        Role role = null;
        try {
            task = taskRepository.findOne(taskId);
        } catch (Exception e) {
            throw new IllegalArgumentException("task with id " + taskId + " doesn't exists");
        }

        try {
            role = roleRepository.findOne(roleId);
        } catch (Exception e) {
            throw new IllegalArgumentException("role with id " + roleId + " doesn't exists");
        }

        task.addRoles(role);
        taskRepository.save(task);
        return true;
    }

    @Override
    public boolean isTaskExecuted(long taskId) throws IllegalArgumentException{
        return taskRepository.findOne(taskId).isExecuted();
    }

    @Override
    public void executeTask(long taskId, long singleTaskToExecuteId) throws IllegalArgumentException {

        Manager manager = null;
        try {
            AbstractTask task = taskRepository.findOne(taskId);
            manager = ((MileStone)task.getTaskNode().getGraph()).getManager();
            String message = task.accept(new ExecutedTask());
            manager.setNewMessage(message);
            personRepository.save(manager);
        } catch (Exception e) {

        }

        try {
            AbstractTask task = taskRepository.findOne(taskId);
            for (SingleTask singleTask : task.getSingleTaskStorage()) {
                if (singleTask.getId() == singleTaskToExecuteId) {
                    System.out.println("executation");
                    singleTask.setExecuted(true);
                    singleTaskRepository.save(singleTask);
                }
            }
//            taskRepository.save(task);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Data");
        }
    }
}
