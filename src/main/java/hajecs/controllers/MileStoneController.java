package hajecs.controllers;

import hajecs.model.Graph.MileStone;
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
    private MileStoneServiceImpl mileStoneService;

    @RequestMapping(value = "/{mileStoneName}", method = RequestMethod.GET)
    public MileStone getMileStone(@PathVariable("mileStoneName") String mileStoneName) {
        return mileStoneService.findMileStoneByName(mileStoneName);
    }


    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public void print() {
        logger.info("print clicked");
    }

}
