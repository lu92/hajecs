package hajecs.model.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lucjan on 07.05.15.
 */
public abstract class AbstractGraph {
    protected Long id;
    protected String name;
    protected String describe;
    protected List<AbstractNode> nodeStorage = new ArrayList<AbstractNode>();
    protected List<RelationShip> relationShipStorage = new ArrayList<RelationShip>();

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

    public void removeNodes(String ... nodeNames) {
        for (AbstractNode node : nodeStorage) {
            for (String s : nodeNames)
                if (node.getName().equals(s)) {
                    nodeStorage.remove(node);
                    break;
                }
        }
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
            RelationShip newRelationShip = new RelationShip(currentNode, destinationNode);
            currentNode.addNeighBourNode(destinationNode);
            destinationNode.addNeighBourNode(currentNode);
            this.relationShipStorage.add(newRelationShip);
            currentNode.addOutgoingRelationShip(newRelationShip);
            destinationNode.addIncommingRelationShip(newRelationShip);
        }
    }

    private List<AbstractNode> getSelectedNodes(String toNode, String[] toOtherNodes) {
        List<AbstractNode> result = new ArrayList<>(1+toOtherNodes.length);
        result.addAll(findNodes(toNode));
        result.addAll(findNodes(toOtherNodes));
        return result;
    }

    public void removeNode(String nodeName) throws IllegalArgumentException {
        AbstractNode findedNode = findNode(nodeName);
        if (findedNode == null)
            throw new IllegalArgumentException("Node not find, so cannot be removed");
        else {
            for (AbstractNode neighbourNode : findedNode.getNeighbourNodeStorage()) {
//                neighbourNode.get
            }
        }
    }

//          SETTERS     AND     GETTERS

    public int getNumberOfNodes() {
        return this.getNodeStorage().size();
    }

    public int getNumberOfRelationShips() {
        return this.relationShipStorage.size();
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

    public List<RelationShip> getRelationShipStorage() {
        return relationShipStorage;
    }

    public void setRelationShipStorage(List<RelationShip> relationShipStorage) {
        this.relationShipStorage = relationShipStorage;
    }

}
