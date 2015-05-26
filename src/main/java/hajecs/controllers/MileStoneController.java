package hajecs.controllers;

import hajecs.model.DTO.*;
import hajecs.repositories.DBGraphRepository;
import hajecs.repositories.DBNodeRepository;
import hajecs.repositories.TaskRepository;
import hajecs.services.MileStoneService;
import hajecs.services.NodeService;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by lucjan on 21.05.15.
 */

@RestController
@RequestMapping(value = "/milestone")
public class MileStoneController {


    Logger logger = Logger.getLogger(String.valueOf(this));

    @Autowired
    private MileStoneService mileStoneService;

    @ResponseBody
    @RequestMapping(value = "/createMileStone", method = RequestMethod.POST)
    public Response createMileStone(@RequestBody MileStoneDTO mileStoneDTO) {
//      {"name":"milestone name test","describe":"milestone describe test"}
        long newMileStoneId = mileStoneService.createMileStone(DTOConverter.toMileStone(mileStoneDTO));
        return new Response("created new MileStone with id = " + newMileStoneId);
    }


    @RequestMapping(value = "/deleteMileStone/{milestoneId}", method = RequestMethod.DELETE)
    public Response deleteMileStone(@PathVariable("milestoneId") long milestoneId) {
        Response response = null;
        try {
            mileStoneService.deleteMileStone(milestoneId);
            response = new Response("milestone was deleted");
        } catch (Exception e) {
            response = new Response(e.getMessage());
        }
        return response;
    }


    @ResponseBody
    @RequestMapping(value = "/getMileStone/{milestoneId}", method = RequestMethod.GET)
    public MileStoneDTOInfo getMileStone(@PathVariable("milestoneId") long milestoneId) {
        MileStoneDTOInfo mileStoneDTOInfo = null;
        try {
            mileStoneDTOInfo = DTOConverter.toMileStoneDTOInfo(mileStoneService.getMileStone(milestoneId));
        } catch (Exception e) {
            mileStoneDTOInfo = new MileStoneDTOInfo();
        }
        return mileStoneDTOInfo;
    }


    @ResponseBody
    @RequestMapping(value = "/addTaskNodeToMileStone/{milestoneId}/{tasknodeId}", method = RequestMethod.GET)
    public Response addTaskNodeToMileStone(@PathVariable("milestoneId") long milestoneId, @PathVariable("tasknodeId") long tasknodeId) {
        try {
            mileStoneService.addTaskNodeToMileStone(milestoneId, tasknodeId);
        } catch (Exception e) {
            return new Response("Invalid Data");
        }
        return new Response("TaskNode was added to MileStone");
    }

    @ResponseBody
    @RequestMapping(value = "/getAllMileStones", method = RequestMethod.GET)
    public Set<MileStoneDTOInfo> getAllMileStones() {
        return mileStoneService.getAllMileStoneDtoInfos();
    }



    @ResponseBody
    @RequestMapping(value = "/addRelationShipBetweenTaskNodes/{milestoneId}/{begintasknodeId}/{endtasknodeId}", method = RequestMethod.GET)
    public Response addRelationShipBetweenTaskNodes(@PathVariable("milestoneId") long milestoneId,
           @PathVariable("begintasknodeId") long beginNodeId, @PathVariable("endtasknodeId") long endNodeId) {
        try {
            mileStoneService.addRelationShipBetweenTaskNodes(milestoneId, beginNodeId, endNodeId);
        } catch (Exception e) {
            return new Response("Invalid Data");
        }
        return new Response("created new relationship");
    }


    @ResponseBody
    @RequestMapping(value = "/getRelationShipsOfSeletedMileStone/{milestoneId}", method = RequestMethod.GET)
    public Set<RelationShipDTOInfo> getRelationShipsOfSeletedMileStone(@PathVariable("milestoneId") long milestoneId) {
        return mileStoneService.getAllRelationShipDtoInfos(milestoneId);
    }

    @ResponseBody
    @RequestMapping(value = "/setManager/{milestoneId}/{managerId}", method = RequestMethod.GET)
    public Response setManager(@PathVariable("milestoneId") long milestoneId, @PathVariable("managerId") long managerId) {
        try {
            mileStoneService.setManager(milestoneId, milestoneId);
        } catch (Exception e) {
            return new Response("Invalid Data");
        }
        return new Response("set manager successful");
    }

}
