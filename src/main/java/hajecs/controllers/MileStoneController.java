package hajecs.controllers;

import hajecs.model.DTO.DTOConverter;
import hajecs.model.DTO.MileStoneDTO;
import hajecs.model.Graph.MileStone;
import hajecs.services.MileStoneService;
import hajecs.services.MileStoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String createMileStone(@RequestBody MileStoneDTO mileStoneDTO) {

//      {"name":"milestone name test","describe":"milestone describe test"}
        long newMileStoneId = mileStoneService.saveOrUpdateMileStone(DTOConverter.toMileStone(mileStoneDTO));
//        logger.info("created new MileStone("+mileStoneDTO.getName()+", "+ mileStoneDTO.getDescribe() +")");
        MileStone mileStoneDb =  mileStoneService.getMileStone(newMileStoneId);
//        logger.info(mileStoneDb.toString());
        return "MileStone was created";
    }

//    public String deleteMileStone() {
//        mileStoneService.de
//    }

    @RequestMapping(value = "/getFakeMilestown")
    public MileStoneDTO getFakeMilestownDTO(){
        return new MileStoneDTO("milestowwn name","milestow describe");
    }

//    @RequestMapping(value = "/{mileStoneName}", method = RequestMethod.GET)
//    public MileStone getMileStone(@PathVariable("mileStoneName") String mileStoneName) {
//        return mileStoneService.findMileStoneByName(mileStoneName);
//    }


//    @RequestMapping(value = "/print", method = RequestMethod.GET)
//    public void print() {
//        logger.info("print clicked");
//    }

}
