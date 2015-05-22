package IntegratedTests;

import hajecs.Neo4jTestApplication;
import hajecs.model.Graph.*;
import hajecs.repositories.DBGraphRepository;
import hajecs.repositories.DBNodeRepository;
import hajecs.repositories.DBRelationShipRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

/**
 * Created by lucjan on 15.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Neo4jTestApplication.class)
@Transactional
public class GraphTest {

    private Logger logger = Logger.getLogger(String.valueOf(this));

    @Autowired
    private DBNodeRepository dbNodeRepository;

    @Autowired
    private DBGraphRepository dbGraphRepository;

    @Autowired
    private DBRelationShipRepository dbRelationShipRepository;

    @Test @Rollback(true)
    public void nodeTest() {
        Assert.assertNotNull(dbNodeRepository);
        Assert.assertNotNull(dbGraphRepository);
        Assert.assertNotNull(dbRelationShipRepository);

        AbstractGraph mileStone = new MileStone("MileStone");
        mileStone.addNodes(
            new Node("A"),
            new Node("B"),
            new Node("C")
        );

        Assert.assertNotNull(mileStone);
        dbGraphRepository.save(mileStone);

        Assert.assertEquals(1, dbGraphRepository.count());
        Assert.assertEquals(3, dbNodeRepository.count());


        mileStone.addRelationShips("A", "B");
        mileStone.addRelationShips("B", "C");


        dbGraphRepository.save(mileStone);
        Assert.assertEquals(2, dbRelationShipRepository.count());
        for (RelationShip relationShip : dbRelationShipRepository.findAll()) {
            logger.info("relationShip : " + relationShip.getDiscribe());
        }

        Assert.assertFalse(dbNodeRepository.findNodeByName("A").isIsolated());
        Assert.assertFalse(dbNodeRepository.findNodeByName("B").isIsolated());
        Assert.assertFalse(dbNodeRepository.findNodeByName("C").isIsolated());


        dbGraphRepository.findByName("MileStone").showAllRelationShips();

        Assert.assertEquals(2, dbGraphRepository.findByName("MileStone").getNodesRelationShip().size());

//        Assert.assertEquals(3, mileStone.getNumberOfNodes());
//        Assert.assertEquals(2, mileStone.getNumberOfRelationShips());
//        Assert.assertEquals(0, mileStone.getNumberOfRelationShips());

//        dbGraphRepository.save(mileStone);

//        Assert.assertEquals(1, dbGraphRepository.count());
//        Assert.assertEquals(3, dbNodeRepository.count());
//
//        Assert.assertNotNull(dbGraphRepository.findByName("MileStone"));
//
//        Assert.assertEquals(2, dbRelationShipRepository.count());
//        Assert.assertEquals(2, dbGraphRepository.findByName("MileStone").getNumberOfRelationShips());

    }
}
