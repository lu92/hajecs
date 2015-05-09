package JUnitTest;

import hajecs.model.Graph.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lucjan on 08.05.15.
 */
public class SetAndRemoveRelationShipsFromGraphTest {

    @Test
    public void setRelatonShipsOnNodes() {
        AbstractGraph graph = new Graph("simple graph", "graph description");
        Assert.assertNotNull(graph);

        graph.addNodes(
                new Node("A"),
                new Node("B"),
                new Node("C"));

        graph.addRelationShips("A", "B");
        graph.addRelationShips("B", "A");

        graph.addRelationShips(graph.findNode("A"), graph.findNode("B"));
        graph.addRelationShips(graph.findNode("B"), graph.findNode("A"));

        Assert.assertEquals(3, graph.getNumberOfNodes());
        Assert.assertEquals(2, graph.getNumberOfRelationShips());


//        graph.findNode("A").showOutGoingRelationShips();
//        graph.findNode("A").showInCommingRelationShips();
//
//        graph.findNode("B").showOutGoingRelationShips();
//        graph.findNode("B").showInCommingRelationShips();
//
//        graph.findNode("C").showOutGoingRelationShips();
//        graph.findNode("C").showInCommingRelationShips();


        Assert.assertEquals(1, graph.findNode("A").getNumberOfInCommingRelationShips());
        Assert.assertEquals(1, graph.findNode("A").getNumberOfOutGoingRelationShips());

        Assert.assertEquals(1, graph.findNode("B").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(1, graph.findNode("B").getNumberOfInCommingRelationShips());

        Assert.assertEquals(0, graph.findNode("C").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(0, graph.findNode("C").getNumberOfInCommingRelationShips());

        Assert.assertEquals(1, graph.findNode("A").getNumberOfNeighbours());
        Assert.assertEquals(1, graph.findNode("B").getNumberOfNeighbours());
        Assert.assertEquals(0, graph.findNode("C").getNumberOfNeighbours());
    }

    @Test
    public void removeRelatonShipsBetweenNodes() {
        AbstractGraph graph = new Graph("simple graph", "graph description");
        Assert.assertNotNull(graph);

        graph.addNodes(
                new Node("A"),
                new Node("B"),
                new Node("C"));

        graph.addRelationShips("A", "B");
        graph.addRelationShips("B", "A");

        Assert.assertEquals(3, graph.getNumberOfNodes());
        Assert.assertEquals(2, graph.getNumberOfRelationShips());

        graph.deleteRelationShipBetweenTwoNodes(graph.findNode("A"), graph.findNode("B"));

        Assert.assertEquals(0, graph.findNode("A").getNumberOfInCommingRelationShips());
        Assert.assertEquals(0, graph.findNode("A").getNumberOfOutGoingRelationShips());

        Assert.assertEquals(0, graph.findNode("B").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(0, graph.findNode("B").getNumberOfInCommingRelationShips());

        Assert.assertEquals(0, graph.findNode("C").getNumberOfOutGoingRelationShips());
        Assert.assertEquals(0, graph.findNode("C").getNumberOfInCommingRelationShips());

        Assert.assertEquals(0, graph.findNode("A").getNumberOfNeighbours());
        Assert.assertEquals(0, graph.findNode("B").getNumberOfNeighbours());
        Assert.assertEquals(0, graph.findNode("C").getNumberOfNeighbours());

        Assert.assertEquals(3, graph.getNumberOfNodes());
        Assert.assertEquals(0, graph.getNumberOfRelationShips());
    }



}
