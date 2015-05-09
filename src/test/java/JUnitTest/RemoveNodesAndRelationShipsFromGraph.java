package JUnitTest;

import hajecs.model.Graph.AbstractGraph;
import hajecs.model.Graph.AbstractNode;
import hajecs.model.Graph.Graph;
import hajecs.model.Graph.Node;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
/**
 * Created by lucjan on 09.05.15.
 */
public class RemoveNodesAndRelationShipsFromGraph {

    private AbstractGraph graph;

    @Before
    public void createGraph() {
        graph = new Graph("simple graph", "graph description");
        Assert.assertNotNull(graph);

        graph.addNodes(
                new Node("A"), new Node("B"), new Node("C"), new Node("D"),
                new Node("E"), new Node("F"), new Node("G"));

        graph.addRelationShips("A", "B");
        graph.addRelationShips("B", "A", "D", "C");
        graph.addRelationShips("C", "B");
        graph.addRelationShips("E", "B");
        graph.addRelationShips("F", "A", "B", "C");
        graph.addRelationShips("G", "D");

        Assert.assertEquals(7, graph.getNumberOfNodes());
        Assert.assertEquals(10, graph.getNumberOfRelationShips());

        String[] nodeNames = {"A", "B", "C", "D", "E", "F", "G"};
        int[] outgoingRelationShips = {1, 3, 1, 0, 1, 3, 1};
        int[] incommingRelationShips = {2, 4, 2, 2, 0, 0, 0};
        int[] neighbours = {2, 5, 2, 2, 1, 3, 1};


        for (int i = 0; i < nodeNames.length; i++) {
            Assert.assertEquals(outgoingRelationShips[i], graph.findNode(nodeNames[i]).getNumberOfOutGoingRelationShips());
            Assert.assertEquals(incommingRelationShips[i], graph.findNode(nodeNames[i]).getNumberOfInCommingRelationShips());
            Assert.assertEquals(neighbours[i], graph.findNode(nodeNames[i]).getNumberOfNeighbours());
        }

    }

//    cohesion - spojnosc

    @Test
    public void cohesionOfNeighboursNodesAndRelationShips() {
        int [] neighbours = {2, 5, 2, 2, 1, 3, 1};
        String[] nodeNames = {"A", "B", "C", "D", "E", "F", "G"};

        Assert.assertEquals(neighbours[0], graph.findNode(nodeNames[0]).calculateNuberOfNeighBourNodes());
        Assert.assertEquals(neighbours[1], graph.findNode(nodeNames[1]).calculateNuberOfNeighBourNodes());
        Assert.assertEquals(neighbours[2], graph.findNode(nodeNames[2]).calculateNuberOfNeighBourNodes());
        Assert.assertEquals(neighbours[3], graph.findNode(nodeNames[3]).calculateNuberOfNeighBourNodes());
        Assert.assertEquals(neighbours[4], graph.findNode(nodeNames[4]).calculateNuberOfNeighBourNodes());
        Assert.assertEquals(neighbours[5], graph.findNode(nodeNames[5]).calculateNuberOfNeighBourNodes());
        Assert.assertEquals(neighbours[6], graph.findNode(nodeNames[6]).calculateNuberOfNeighBourNodes());

    }

    @Test
    public void removeManyRelationShips() {

        graph.addRelationShips("B", "G");

        graph.deleteRelationShipFromBeginNodeToEndNode("A", "B");
        graph.deleteRelationShipFromBeginNodeToEndNode("B", "A");
        graph.deleteRelationShipFromBeginNodeToEndNode("E", "B");
        graph.deleteRelationShipFromBeginNodeToEndNode("B", "D");
        graph.deleteRelationShipFromBeginNodeToEndNode("B", "G");
        graph.deleteRelationShipFromBeginNodeToEndNode("C", "B");
        graph.deleteRelationShipFromBeginNodeToEndNode("B", "C");
        graph.deleteRelationShipFromBeginNodeToEndNode("F", "B");

        Assert.assertEquals(3, graph.getNumberOfRelationShips());
        Assert.assertEquals(7, graph.getNumberOfNodes());

        String[] nodeNames = {"A", "B", "C", "D", "E", "F", "G"};
        int[] outgoingRelationShips = {0, 0, 0, 0, 0, 2, 1};
        int[] incommingRelationShips = {1, 0, 1, 1, 0, 0, 0};
        int[] neighbours = {1, 0, 1, 1, 0, 2, 1};

        Assert.assertEquals(outgoingRelationShips[0], graph.findNode(nodeNames[0]).getNumberOfOutGoingRelationShips());
        Assert.assertEquals(incommingRelationShips[0], graph.findNode(nodeNames[0]).getNumberOfInCommingRelationShips());
        Assert.assertEquals(neighbours[0], graph.findNode(nodeNames[0]).getNumberOfNeighbours());

        Assert.assertEquals(outgoingRelationShips[1], graph.findNode(nodeNames[1]).getNumberOfOutGoingRelationShips());
        Assert.assertEquals(incommingRelationShips[1], graph.findNode(nodeNames[1]).getNumberOfInCommingRelationShips());
        Assert.assertEquals(neighbours[1], graph.findNode(nodeNames[1]).getNumberOfNeighbours());

        Assert.assertEquals(outgoingRelationShips[2], graph.findNode(nodeNames[2]).getNumberOfOutGoingRelationShips());
        Assert.assertEquals(incommingRelationShips[2], graph.findNode(nodeNames[2]).getNumberOfInCommingRelationShips());
        Assert.assertEquals(neighbours[2], graph.findNode(nodeNames[2]).getNumberOfNeighbours());

        Assert.assertEquals(outgoingRelationShips[3], graph.findNode(nodeNames[3]).getNumberOfOutGoingRelationShips());
        Assert.assertEquals(incommingRelationShips[3], graph.findNode(nodeNames[3]).getNumberOfInCommingRelationShips());
        Assert.assertEquals(neighbours[3], graph.findNode(nodeNames[3]).getNumberOfNeighbours());

        Assert.assertEquals(outgoingRelationShips[4], graph.findNode(nodeNames[4]).getNumberOfOutGoingRelationShips());
        Assert.assertEquals(incommingRelationShips[4], graph.findNode(nodeNames[4]).getNumberOfInCommingRelationShips());
        Assert.assertEquals(neighbours[4], graph.findNode(nodeNames[4]).getNumberOfNeighbours());

        Assert.assertEquals(outgoingRelationShips[5], graph.findNode(nodeNames[5]).getNumberOfOutGoingRelationShips());
        Assert.assertEquals(incommingRelationShips[5], graph.findNode(nodeNames[5]).getNumberOfInCommingRelationShips());
        Assert.assertEquals(neighbours[5], graph.findNode(nodeNames[5]).getNumberOfNeighbours());

        Assert.assertEquals(outgoingRelationShips[6], graph.findNode(nodeNames[6]).getNumberOfOutGoingRelationShips());
        Assert.assertEquals(incommingRelationShips[6], graph.findNode(nodeNames[6]).getNumberOfInCommingRelationShips());
        Assert.assertEquals(neighbours[6], graph.findNode(nodeNames[6]).getNumberOfNeighbours());
    }

    @Test
    public void removeManyRelationShips2() {
        graph.deleteRelationShipBetweenTwoNodes("B", "D");
        graph.deleteRelationShipBetweenTwoNodes("B", "C");
        graph.deleteRelationShipBetweenTwoNodes("B", "A");
        graph.deleteRelationShipBetweenTwoNodes("B", "E");
        graph.deleteRelationShipBetweenTwoNodes("B", "F");

        Assert.assertEquals(3, graph.getNumberOfRelationShips());
        Assert.assertEquals(7, graph.getNumberOfNodes());

        String[] nodeNames = {"A", "B", "C", "D", "E", "F", "G"};
        int[] outgoingRelationShips = {0, 0, 0, 0, 0, 2, 1};
        int[] incommingRelationShips = {1, 0, 1, 1, 0, 0, 0};
        int[] neighbours = {1, 0, 1, 1, 0, 2, 1};


        Assert.assertEquals(outgoingRelationShips[0], graph.findNode(nodeNames[0]).getNumberOfOutGoingRelationShips());
        Assert.assertEquals(incommingRelationShips[0], graph.findNode(nodeNames[0]).getNumberOfInCommingRelationShips());
        Assert.assertEquals(neighbours[0], graph.findNode(nodeNames[0]).getNumberOfNeighbours());

        Assert.assertEquals(outgoingRelationShips[1], graph.findNode(nodeNames[1]).getNumberOfOutGoingRelationShips());
        Assert.assertEquals(incommingRelationShips[1], graph.findNode(nodeNames[1]).getNumberOfInCommingRelationShips());
        Assert.assertEquals(neighbours[1], graph.findNode(nodeNames[1]).getNumberOfNeighbours());

        Assert.assertEquals(outgoingRelationShips[2], graph.findNode(nodeNames[2]).getNumberOfOutGoingRelationShips());
        Assert.assertEquals(incommingRelationShips[2], graph.findNode(nodeNames[2]).getNumberOfInCommingRelationShips());
        Assert.assertEquals(neighbours[2], graph.findNode(nodeNames[2]).getNumberOfNeighbours());

        Assert.assertEquals(outgoingRelationShips[3], graph.findNode(nodeNames[3]).getNumberOfOutGoingRelationShips());
        Assert.assertEquals(incommingRelationShips[3], graph.findNode(nodeNames[3]).getNumberOfInCommingRelationShips());
        Assert.assertEquals(neighbours[3], graph.findNode(nodeNames[3]).getNumberOfNeighbours());

        Assert.assertEquals(outgoingRelationShips[4], graph.findNode(nodeNames[4]).getNumberOfOutGoingRelationShips());
        Assert.assertEquals(incommingRelationShips[4], graph.findNode(nodeNames[4]).getNumberOfInCommingRelationShips());
        Assert.assertEquals(neighbours[4], graph.findNode(nodeNames[4]).getNumberOfNeighbours());

        Assert.assertEquals(outgoingRelationShips[5], graph.findNode(nodeNames[5]).getNumberOfOutGoingRelationShips());
        Assert.assertEquals(incommingRelationShips[5], graph.findNode(nodeNames[5]).getNumberOfInCommingRelationShips());
        Assert.assertEquals(neighbours[5], graph.findNode(nodeNames[5]).getNumberOfNeighbours());

        Assert.assertEquals(outgoingRelationShips[6], graph.findNode(nodeNames[6]).getNumberOfOutGoingRelationShips());
        Assert.assertEquals(incommingRelationShips[6], graph.findNode(nodeNames[6]).getNumberOfInCommingRelationShips());
        Assert.assertEquals(neighbours[6], graph.findNode(nodeNames[6]).getNumberOfNeighbours());
    }

    @Test
    public void clearGraph() {
        graph.clear();
        Assert.assertNotNull(graph);
        Assert.assertEquals(0, graph.getNumberOfNodes());
        Assert.assertEquals(0, graph.getNumberOfRelationShips());
    }


    @Test(expected = IllegalArgumentException.class)
    public void removeExistedNode() {
        graph.clear();
        graph.removeNodes("A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeUnIsolatedNode() {
        graph.removeNodes("B");
    }

    @Test
    public void removeUnIsolatedSingleNode() {
        graph.addNodes(new Node("X"));
        Assert.assertEquals(8, graph.getNumberOfNodes());
        Assert.assertEquals(10, graph.getNumberOfRelationShips());

        graph.removeNodes("X");

        Assert.assertEquals(7, graph.getNumberOfNodes());
        Assert.assertEquals(10, graph.getNumberOfRelationShips());
    }

//    @Test
    public void removeConnectedNode() {
        graph.removeNodeRegardlessOfRelationShips("B");
        Assert.assertEquals(6, graph.getNumberOfNodes());
        Assert.assertEquals(3, graph.getNumberOfRelationShips());
    }

    @Test
    public void removeAllRelationShipsOnNode() {

//        graph.deleteRelationShipBetweenTwoNodes("B", "D");
//        graph.deleteRelationShipBetweenTwoNodes("B", "C");
//        graph.deleteRelationShipBetweenTwoNodes("B", "A");
//        graph.deleteRelationShipBetweenTwoNodes("B", "E");
//        graph.deleteRelationShipBetweenTwoNodes("B", "F");

        String [] names = {"D", "C", "A", "E", "F"};
//        for (int i=0; i<5; i++)
//            graph.deleteRelationShipBetweenTwoNodes("B", names[i]);

        AbstractNode currentNode = graph.findNode("B");
        for (int i=0; i<graph.findNode("B").getNeighbourNodeStorage().size(); i++) {
            String neighbour = currentNode.getNeighbourNodeStorage().get(i).getName();
            System.out.println(neighbour);
            graph.deleteRelationShipBetweenTwoNodes("B", names[i]);
        }

//        graph.deleteAllRelationShipsOnSelectedNode("B");

//        graph.deleteRelationShipBetweenTwoNodes("B", "C");
        System.out.println("neighbours = " + graph.findNode("B").calculateNuberOfNeighBourNodes());
        graph.findNode("B").showOutGoingRelationShips();
        graph.findNode("B").showInCommingRelationShips();
//        Assert.assertEquals(0, graph.findNode("B").calculateNuberOfNeighBourNodes());
//        Assert.assertTrue(graph.findNode("B").isIsolated());
    }

}
