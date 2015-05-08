package IntegratedTests;

import hajecs.HajecsApplication;
import hajecs.model.Graph.Node;
import hajecs.model.Graph.RelationShip;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lucjan on 08.05.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HajecsApplication.class)
@WebAppConfiguration
@Transactional  //  IMPORTANT
@TransactionConfiguration(defaultRollback = true)
public class graphTest {

    public void createSingleRelationTest() {
        Node A = new Node("A");
        Node B = new Node("B");

        RelationShip A_B = new RelationShip(A, B);
    }

}
