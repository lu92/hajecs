package JUnitTest;

import hajecs.model.Graph.Graph;
import hajecs.model.Graph.GraphAlgorithms;
import hajecs.model.Graph.GraphAlgorithmsImpl;
import hajecs.model.Graph.Node;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lucjan on 27.05.15.
 */
public class GraphAlgorithmsTest {

    private GraphAlgorithms graphAlgorithms = new GraphAlgorithmsImpl();

    @Test
    public void checkDirectionOfGraphTRUE() {
        Graph graph = new Graph("graph", "graph");
        graph.addNodes(
                new Node("0"), new Node("1"),
                new Node("2"), new Node("3")
        );

        graph.addRelationShips("0", "1");
        graph.addRelationShips("1", "2");
        graph.addRelationShips("2", "3");


        Assert.assertEquals(4, graph.getNumberOfNodes());
//        Assert.assertEquals(4, graph.getNodeStorage().size());
//        Assert.assertEquals(3, graph.getGraphRelationShipStorage().size());
        Assert.assertEquals(3, graph.getNumberOfRelationShips());


        Assert.assertEquals(1, graph.findNode("0").getNumberOfNeighbours());
        Assert.assertEquals(2, graph.findNode("1").getNumberOfNeighbours());
        Assert.assertEquals(2, graph.findNode("2").getNumberOfNeighbours());
        Assert.assertEquals(1, graph.findNode("3").getNumberOfNeighbours());


        Assert.assertNotNull(graph);
        Assert.assertTrue(graphAlgorithms.checkDirected(graph));


    }


    @Test
    public void checkDirectionOfGraphFALSE() {
        Graph graph = new Graph("graph", "graph describe");
        graph.addNodes(
                new Node("0"), new Node("1"), new Node("2"), new Node("3")
        );

        graph.addRelationShips("0", "1");
        graph.addRelationShips("1", "0");
        graph.addRelationShips("1", "2");
        graph.addRelationShips("2", "1");
        graph.addRelationShips("2", "3");
        graph.addRelationShips("3", "2");


        Assert.assertNotNull(graph);

        Assert.assertEquals(4, graph.getNumberOfNodes());
        Assert.assertEquals(6, graph.getNumberOfRelationShips());
        Assert.assertFalse(graphAlgorithms.checkDirected(graph));
    }


    @Test
    public void DFSTest() {
//        https://www.youtube.com/watch?v=zLZhSSXAwxI

        Graph graph = new Graph("graph", "graph describe");
        graph.addNodes(
                new Node("A"), new Node("B"), new Node("C"), new Node("D"),
                new Node("E"), new Node("F"), new Node("G"), new Node("H")
        );

        graph.addUndirectedRelationShip("A", "B");
        graph.addUndirectedRelationShip("A", "D");
        graph.addUndirectedRelationShip("A", "G");
        graph.addUndirectedRelationShip("B", "E");
        graph.addUndirectedRelationShip("B", "F");
        graph.addUndirectedRelationShip("C", "H");
        graph.addUndirectedRelationShip("D", "A");
        graph.addUndirectedRelationShip("D", "F");
        graph.addUndirectedRelationShip("E", "B");
        graph.addUndirectedRelationShip("E", "G");
        graph.addUndirectedRelationShip("F", "B");
        graph.addUndirectedRelationShip("F", "C");
        graph.addUndirectedRelationShip("G", "A");
        graph.addUndirectedRelationShip("G", "E");
        graph.addUndirectedRelationShip("H", "C");


        Assert.assertEquals(8, graph.getNumberOfNodes());
        Assert.assertEquals(18, graph.getNumberOfRelationShips());  // razy 2 jesli nieskierowany
    }

    @Test
    public void makeUndirectedGraph() {
        Graph graph = new Graph("graph", "graph");
        graph.addNodes(
                new Node("0"), new Node("1"),
                new Node("2"), new Node("3")
        );

        graph.addRelationShips("0", "1");
        graph.addRelationShips("1", "2");
        graph.addRelationShips("2", "3");

        Assert.assertTrue(graphAlgorithms.checkDirected(graphAlgorithms.getUndirectedGraph(graph)));
    }


}
