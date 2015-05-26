package hajecs.services;

import hajecs.repositories.DBTaskNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lucjan on 24.05.15.
 */

@Service
public class RelationShipServiceImpl implements RelationShipService {

    @Autowired
    private DBTaskNodeRepository dbTaskNodeRepository;

    @Override
    public long setRelationShipBetweenTwoTaskNodes(long beginNodeId, long endNodeId) throws IllegalArgumentException {



        return -1;
    }

    @Override
    public void deleteRelationShip(long id) {

    }
}
