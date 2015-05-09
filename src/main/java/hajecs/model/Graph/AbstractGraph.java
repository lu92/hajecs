package hajecs.model.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lucjan on 07.05.15.
 */
public abstract class AbstractGraph {
    protected Long id;
    protected String name;
    protected String describe;
    protected List<AbstractNode> nodeStorage = new ArrayList<AbstractNode>();
    protected List<RelationShip> graphRelationShipStorage = new ArrayList<RelationShip>();

    public AbstractGraph() {
    }

    public AbstractGraph(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }

    public AbstractGraph(Long id, String name, String describe) {
        this(name, describe);
        this.id = id;
    }

    public void addNodes(AbstractNode ... nodes) {
        for (AbstractNode node : nodes)
            nodeStorage.add(node);
    }


    public void deleteAllRelationShipsOnSelectedNode(String nodeName) {
        AbstractNode node = findNode(nodeName);
//        System.out.println("size neighbour " + node.getNumberOfNeighbours());
        for (int i=0; i< node.calculateNuberOfNeighBourNodes(); i++) {
            AbstractNode neighbour = node.getNeighbourNodeStorage().get(i);
//            System.out.println(neighbour.getName() + "*");
//            System.out.println("delete neighbour " + neighbour.getName());
            deleteRelationShipBetweenTwoNodes(node.getName(), neighbour.getName());
        }
    }

    public void removeNodeRegardlessOfRelationShips(String nodeName) {

        if (isEveryNodeExists(nodeName)) {
//            AbstractNode node = findNode(nodeName);

            removeNodes(nodeName);
        } else
            throw new IllegalArgumentException("Cannot remove doesn't exist node");

    }

    public void removeNodes(String ... nodeNames) throws IllegalArgumentException{

        if (isEveryNodeExists(nodeNames)) {
            if (isEveryNodeIsIsolated(nodeNames)) {
                getNodeStorage().removeAll(findNodes(nodeNames));
            } else
                throw new IllegalArgumentException("Cannot remove unisolated node");
        } else
            throw new IllegalArgumentException("Cannot remove doesn't exist node");
    }

    private boolean isEveryNodeExists(String ... nodeNames) {
        return findNodes(nodeNames).size() == nodeNames.length ? true : false;
    }

    private boolean isEveryNodeIsIsolated(String ... nodeNames) {
        for (AbstractNode node : findNodes(nodeNames)) {
            if (!node.isIsolated())
                return false;
        }
        return true;
    }

    public AbstractNode findNode(String nodeName) {
        for (AbstractNode node : nodeStorage)
            if (node.getName().equals(nodeName))
                return node;
        return null;
    }

    public List<AbstractNode> findNodes(String ... nodeNames) {
        List<AbstractNode> findNodes = new ArrayList<>();
        for (String s : nodeNames) {
            AbstractNode findedNode = findNode(s);
            if (findedNode != null)
                findNodes.add(findedNode);
        }
        return findNodes;
    }

    public void addRelationShips(String fromNodeName, String toNodeNames, String ... toOtherNodes) {

        addRelationShips(findNode(fromNodeName), findNode(toNodeNames));
        for (String node : toOtherNodes)
            addRelationShips(findNode(fromNodeName), findNode(node));
    }

    public void addRelationShips(AbstractNode currentNode, AbstractNode toNodeNames, AbstractNode ... toOtherNodes) {
        List<AbstractNode> toNodes = new ArrayList<>();
        toNodes.add(toNodeNames);
        Collections.addAll(toNodes, toOtherNodes);

        for(AbstractNode destinationNode : toNodes) {
            setNeighbourHoodBetweenNodes(currentNode, destinationNode);
        }
    }

    private void setNeighbourHoodBetweenNodes(AbstractNode beginNode, AbstractNode endNode) {
        RelationShip relationShip = new RelationShip(beginNode, endNode);

        if (!beginNode.getOutGoingRelationShipStorage().contains(relationShip) && !endNode.getInCommingRelationShipStorage().contains(relationShip)) {

            beginNode.addOutgoingRelationShip(relationShip);
            endNode.getInCommingRelationShipStorage().add(relationShip);

            if (!beginNode.getNeighbourNodeStorage().contains(endNode) && !endNode.getNeighbourNodeStorage().contains(beginNode)) {
                beginNode.addNeighBourNode(endNode);
                endNode.addNeighBourNode(beginNode);
            }

            this.graphRelationShipStorage.add(relationShip);
        }
    }

    private List<AbstractNode> getSelectedNodes(String toNode, String[] toOtherNodes) {
        List<AbstractNode> result = new ArrayList<>(1+toOtherNodes.length);
        result.addAll(findNodes(toNode));
        result.addAll(findNodes(toOtherNodes));
        return result;
    }

    public void deleteRelationShipFromBeginNodeToEndNode(String beginNode, String endNode) {
        deleteRelationShipFromBeginNodeToEndNode(findNode(beginNode), findNode(endNode));
    }

    public void deleteRelationShipFromBeginNodeToEndNode(AbstractNode beginNode, AbstractNode endNode) {
        for (int i=0; i<beginNode.getOutGoingRelationShipStorage().size(); i++) {
            RelationShip relationShip = beginNode.getOutGoingRelationShipStorage().get(i);
            if (relationShip.getEndNode().equals(endNode))
                beginNode.getOutGoingRelationShipStorage().remove(relationShip);
                endNode.getInCommingRelationShipStorage().remove(relationShip);     //
        }

        for (int i=0; i<beginNode.getInCommingRelationShipStorage().size(); i++) {
            RelationShip relationShip = beginNode.getInCommingRelationShipStorage().get(i);
            if (relationShip.getBeginNode().equals(endNode))
                beginNode.getInCommingRelationShipStorage().remove(relationShip);
                endNode.getOutGoingRelationShipStorage().remove(relationShip);      //
        }

        if (!beginNode.getInCommingRelationShipStorage().contains(new RelationShip(endNode, beginNode))) {
            beginNode.getNeighbourNodeStorage().remove(endNode);
            endNode.getNeighbourNodeStorage().remove(beginNode);
        }
        deleteRelationsShipBetweenTwoNodesFromGraph(beginNode, endNode);
    }

    public void deleteRelationShipBetweenTwoNodes(String beginNode, String endNode) {
        deleteRelationShipBetweenTwoNodes(findNode(beginNode), findNode(endNode));
    }

    public void deleteRelationShipBetweenTwoNodes(AbstractNode beginNode, AbstractNode endNode) {
        deleteRelationShipFromBeginNodeToEndNode(beginNode, endNode);
        deleteRelationShipFromBeginNodeToEndNode(endNode, beginNode);
    }

    public void deleteRelationsShipBetweenTwoNodesFromGraph(AbstractNode beginNode, AbstractNode endNode) {
        for (int i=0; i<graphRelationShipStorage.size(); i++) {
            RelationShip relationShip = this.graphRelationShipStorage.get(i);
            if (relationShip.getBeginNode().equals(beginNode) && relationShip.getEndNode().equals(endNode)) {
                this.graphRelationShipStorage.remove(relationShip);
            }
        }
    }



    public void showAllRelationShips() {
        for (int i=0; i<this.graphRelationShipStorage.size(); i++) {
            this.graphRelationShipStorage.get(i).show();
        }
    }

    public void clear() {
        deleteAllRelationShips();
        deleteAllNodes();
    }

    private void deleteAllNodes() {
        this.nodeStorage.clear();
    }

    private void deleteAllRelationShips() {
        this.graphRelationShipStorage.clear();
    }



//          SETTERS     AND     GETTERS

    public int getNumberOfNodes() {
        return this.getNodeStorage().size();
    }

    public int getNumberOfRelationShips() {
        return this.graphRelationShipStorage.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<AbstractNode> getNodeStorage() {
        return nodeStorage;
    }

    public void setNodeStorage(List<AbstractNode> nodeStorage) {
        this.nodeStorage = nodeStorage;
    }

    public List<RelationShip> getGraphRelationShipStorage() {
        return graphRelationShipStorage;
    }

    public void setGraphRelationShipStorage(List<RelationShip> graphRelationShipStorage) {
        this.graphRelationShipStorage = graphRelationShipStorage;
    }

}
