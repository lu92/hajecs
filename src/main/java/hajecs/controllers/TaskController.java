package hajecs.controllers;

import hajecs.model.DTO.DTOConverter;
import hajecs.model.DTO.Response;
import hajecs.model.DTO.TaskDTO;
import hajecs.model.DTO.TaskDTOInfo;
import hajecs.model.Task.AbstractTask;
import hajecs.resources.TaskResource;
import hajecs.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by lucjan on 24.05.15.
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @ResponseBody
    @RequestMapping(value = "/createTask", method = RequestMethod.POST)
    public Response createTask(@RequestBody TaskDTO taskDTO) {
        AbstractTask task = DTOConverter.toTask(taskDTO);
        taskService.createTask(task);
        return new Response("created new Task with id : " + task.getId());
    }


    @ResponseBody
    @RequestMapping(value = "/getTask/{id}", method = RequestMethod.GET)
    public TaskDTOInfo getTask(@PathVariable("id") long id) {
        TaskDTOInfo taskDTOInfo = null;
        try {
            taskDTOInfo = DTOConverter.toTaskDTOInfo(taskService.getTask(id));
        } catch (Exception e) {
            taskDTOInfo = new TaskDTOInfo();
        }
        return taskDTOInfo;
    }

    @ResponseBody
    @RequestMapping(value = "/addPersonToTask/{taskId}/{personId}", method = RequestMethod.GET)
    public Response addPersonToTask(@PathVariable("taskId") long taskId, @PathVariable("personId") long personId) {
//        try {
            taskService.addPersonToTask(taskId, personId);
//        } catch (Exception e) {
//            return new Response("Invalid Data");
//        }
        return new Response("person was added to task");
    }

    @ResponseBody
    @RequestMapping(value = "/addRoleToTask/{taskId}/{roleId}", method = RequestMethod.GET)
    public Response addRoleToTask(@PathVariable("taskId") long taskId, @PathVariable("roleId") long roleId) {
        try {
            taskService.addRoleToTask(taskId, roleId);
        } catch (Exception e) {
            return new Response("Invalid Data");
        }
        return new Response("role was added to task");
    }

    @ResponseBody
    @RequestMapping(value = "/getAllTasks", method = RequestMethod.GET)
    public Set<TaskDTOInfo> getAllTasks() {
        return taskService.getAllTaskDtoInfos();
    }

    @ResponseBody
    @RequestMapping(value = "/getFakeTask", method = RequestMethod.GET)
    public TaskDTOInfo getFakeTask() {
        return DTOConverter.toTaskDTOInfo(TaskResource.getDailyTask());
    }

    @ResponseBody
    @RequestMapping(value = "/isTaskExecuted/{taskId}", method = RequestMethod.GET)
    public Response isTaskExecuted(@PathVariable("taskId") long taskId) {
        boolean value;
        try {
            value = taskService.isTaskExecuted(taskId);
        } catch (Exception e) {
            return new Response("Invalid Task number");
        }
        return (value == true ) ? new Response("true") : new Response("false");
    }


    @ResponseBody
    @RequestMapping(value = "/executeTask/{taskId}/{singletaskToExecuteId}", method = RequestMethod.GET)
    public Response executeTask(@PathVariable("taskId") long taskId, @PathVariable("singletaskToExecuteId") long singletaskToExecudeId) {
//        try {
            taskService.executeTask(taskId, singletaskToExecudeId);
//        } catch (Exception e) {
//            return new Response("Invalid Data");
//        }
        return new Response("task was executed");
    }

/*
    {
        id: null
        taskType: "DAILY_TASK"
        nodeId: null
        mileStoneId: null
        name: "task's name"
        describe: "task's describe"
        start: "2016-08-05"
        deadline: "2016-08-05"
        workersId: [0]
        rolesId: [0]
        singleTasks: [1]
        0:  {
        id: null
        duration: ""
        task: "task's name"
    }-
            -
    }
    */
}
