package IntegratedTests;

import hajecs.Neo4jTestApplication;
import hajecs.model.Graph.AbstractGraph;
import hajecs.model.Graph.MileStone;
import hajecs.model.Graph.Node;
import hajecs.repositories.DBGraphRepository;
import hajecs.repositories.DBNodeRepository;
import hajecs.repositories.DBRelationShipRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lucjan on 15.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Neo4jTestApplication.class)
@Transactional
public class GraphTest {

    @Autowired
    private DBNodeRepository dbNodeRepository;

    @Autowired
    private DBGraphRepository dbGraphRepository;

    @Autowired
    private DBRelationShipRepository dbRelationShipRepository;

    @Test
    public void nodeTest() {
        Assert.assertNotNull(dbNodeRepository);

        AbstractGraph mileStone = new MileStone("MileStone");
        mileStone.addNodes(
            new Node("A"),
            new Node("B"),
            new Node("C")
        );

        mileStone.addRelationShips("A", "B");
        mileStone.addRelationShips("B", "C");


        Assert.assertNotNull(mileStone);
        Assert.assertEquals(3, mileStone.getNumberOfNodes());
        Assert.assertEquals(2, mileStone.getNumberOfRelationShips());

        dbGraphRepository.save(mileStone);

        Assert.assertEquals(1, dbGraphRepository.count());
        Assert.assertEquals(3, dbNodeRepository.count());

        Assert.assertNotNull(dbGraphRepository.findByName("MileStone"));

        Assert.assertEquals(2, dbRelationShipRepository.count());
        Assert.assertEquals(2, dbGraphRepository.findByName("MileStone").getNumberOfRelationShips());

    }
}
