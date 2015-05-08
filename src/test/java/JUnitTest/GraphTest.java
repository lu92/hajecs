package JUnitTest;

import hajecs.model.Graph.AbstractGraph;
import hajecs.model.Graph.Graph;
import hajecs.model.Graph.Node;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lucjan on 08.05.15.
 */
public class GraphTest {

    @Test
    public void createSingleThreeRelationShip() {
        AbstractGraph graph = new Graph("simple graph", "graph description");
        Assert.assertNotNull(graph);

        graph.addNodes(
                new Node("A"),
                new Node("B"),
                new Node("C"),
                new Node("D"));
        Assert.assertEquals(4, graph.getNumberOfNodes());

        graph.addRelationShips("A", "B", "C", "D");

        Assert.assertEquals(3, graph.getNumberOfRelationShips());

        Assert.assertEquals(3, graph.findNode("A").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(0, graph.findNode("A").getNumberOfInCommingRelationShips());

        String [] nodes = { "B", "C", "D" };
        for (String node : nodes) {
            Assert.assertEquals(0, graph.findNode(node).getNumberOfOutGoingRelationShips());
            Assert.assertEquals(1, graph.findNode(node).getNumberOfInCommingRelationShips());
        }

        graph.removeNode("D");

    }
}
