package hajecs.controllers;

import hajecs.model.DTO.*;
import hajecs.services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by lucjan on 24.05.15.
 */
@RestController
@RequestMapping(value = "/node")
public class NodeController {

    private Logger logger = Logger.getLogger(String.valueOf(this));

    @Autowired
    private NodeService nodeService;

    @ResponseBody
    @RequestMapping(value = "/createTaskNode", method = RequestMethod.POST)
    public Response createTaskNode(@RequestBody TaskNodeDTO taskNodeDTO) {
        DTOConverter.toTaskNode(taskNodeDTO);
        long newTaskNodeId = nodeService.createTaskNode(DTOConverter.toTaskNode(taskNodeDTO));
        return new Response("created new Task Node with id : " + newTaskNodeId);
    }


    @ResponseBody
    @RequestMapping(value = "/getAllTaskNodes", method = RequestMethod.GET)
    public Set<TaskNodeDTOInfo> getAllTaskNodes() {
        return nodeService.getAllTaskNodeDtoInfos();
    }

    @ResponseBody
    @RequestMapping(value = "/setTaskToNode/{nodeId}/{taskId}", method = RequestMethod.GET)
    public Response setTaskToNode(@PathVariable("nodeId") long nodeId, @PathVariable("taskId") long taskId) {
        try {
            nodeService.setTaskToNode(nodeId, taskId);
        } catch (Exception e) {
            return new Response("Invalid Data");
        }
        return new Response("Task was added to Node");
    }
    @ResponseBody
    @RequestMapping(value = "/deleteNode/{id}", method = RequestMethod.DELETE)
    public Response deleteNode(@PathVariable("id") long id) {
        nodeService.deleteNode(id);
        return new Response("Node was deleted");
    }

    @ResponseBody
    @RequestMapping(value = "/getTaskNode/{id}")
    public TaskNodeDTOInfo getTaskNode(@PathVariable("id") long id) {
        TaskNodeDTOInfo taskNodeDTOInfo = null;
        try {
            taskNodeDTOInfo = DTOConverter.toTaskNodeDTOInfo(nodeService.getTaskNode(id));
        } catch (Exception e) {
            taskNodeDTOInfo = new TaskNodeDTOInfo();
        }
        return taskNodeDTOInfo;
    }
}
