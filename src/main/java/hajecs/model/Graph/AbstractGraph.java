package hajecs.model.Graph;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.*;

/**
 * Created by lucjan on 07.05.15.
 */
@NodeEntity
public abstract class AbstractGraph {

    @GraphId
    protected Long id;
    protected String name;
    protected String describe;

    @JsonIgnore
    @Fetch @RelatedTo(type = "GRAPH_NODE_RELATION", direction = Direction.BOTH)
    protected Set<AbstractNode> nodeStorage = new HashSet<>();


    @JsonIgnore
    @Fetch @RelatedToVia(type = "RELATED_TO", direction = Direction.BOTH)
    protected Set<RelationShip> graphRelationShipStorage = new HashSet<>();

    public AbstractGraph() {
    }

    public AbstractGraph(String name) {
        this.name = name;
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
        for (AbstractNode node : nodes) {
            node.setGraph(this);
            nodeStorage.add(node);
        }
    }

    public void deleteAllRelationShipsOnSelectedNode(String nodeName) {
        AbstractNode selectedNode = findNode(nodeName);
        for (String neighbour : selectedNode.getNamesOfNeighbours()) {
            deleteRelationShipBetweenTwoNodes(selectedNode.getName(), neighbour);
        }
    }


    public void deleteAllRelationShipsOnSelectedNode(long nodeId) {
        AbstractNode selectedNode = findNode(nodeId);
        for (String neighbour : selectedNode.getNamesOfNeighbours()) {
            deleteRelationShipBetweenTwoNodes(selectedNode.getName(), neighbour);
        }
    }

    public void deleteRelationShipsBetweenNodes(long fromNodeId, long ... destinations) {
        AbstractNode fromNode = findNode(fromNodeId);
        for (AbstractNode destinationNode : findNodes(destinations)) {
            deleteRelationShipFromBeginNodeToEndNode(fromNode, destinationNode);
        }
    }


    public void removeNodeRegardlessOfRelationShips(String nodeName) {

        if (isEveryNodeExists(nodeName)) {
//            AbstractNode node = findNode(nodeName);
            this.deleteAllRelationShipsOnSelectedNode(nodeName);
            removeNodes(nodeName);
        } else
            throw new IllegalArgumentException("Cannot remove doesn't exist node");

    }

    public void removeNodeRegardlessOfRelationShips(long nodeId) {
        if (isEveryNodeExists(nodeId)) {
            this.deleteAllRelationShipsOnSelectedNode(nodeId);
            removeNodes(nodeId);
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

    public void removeNodes(long ... nodesId) throws IllegalArgumentException{

        if (isEveryNodeExists(nodesId)) {
            if (isEveryNodeIsIsolated(nodesId)) {
                getNodeStorage().removeAll(findNodes(nodesId));
            } else
                throw new IllegalArgumentException("Cannot remove unisolated node");
        } else
            throw new IllegalArgumentException("Cannot remove doesn't exist node");
    }

    private boolean isEveryNodeExists(String ... nodeNames) {
        return findNodes(nodeNames).size() == nodeNames.length ? true : false;
    }

    public boolean isEveryNodeExists(long ... nodesId) {
        return getNodeStorage().containsAll(findNodes(nodesId));
    }

    public boolean isEveryNodeIsIsolated(long ... nodesId) {
        for (AbstractNode node : findNodes(nodesId)) {
            if (!node.isIsolated())
                return false;
        }
        return true;
    }

    public AbstractNode findNodeById(long id) throws IllegalArgumentException{
        for (AbstractNode node : getNodeStorage()) {
           if (node.getId() == id)
               return node;
        }
        throw new IllegalArgumentException("node doesn't exists");
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

    public AbstractNode findNode(long nodeId) {
        for (AbstractNode node : nodeStorage)
            if (node.getId() == nodeId)
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

    public Set<AbstractNode> findNodes(long ... nodesId) {
        Set<AbstractNode> findNodes = new HashSet<>();
        for (long id : nodesId) {
            AbstractNode findedNode = findNode(id);
            if (findedNode != null)
                findNodes.add(findedNode);
        }
        return findNodes;
    }

    public void addRelationShips(String fromNodeName, String toNodeNames, String ... toOtherNodes) {

        AbstractNode fromNode = findNode(fromNodeName);
        AbstractNode toNode = findNode(toNodeNames);

        if (fromNode == null || toNode == null)
            System.out.println("Cannot find nodes to create relationship");
        else {

            addRelationShips(findNode(fromNodeName), findNode(toNodeNames));
            for (String node : toOtherNodes)
                addRelationShips(findNode(fromNodeName), findNode(node));
        }
    }

    public void addRelationShips(long fromNodeId, long ... toOtherNodesId) {

        AbstractNode fromNode = findNode(fromNodeId);
        Set<AbstractNode> toNodes = findNodes(toOtherNodesId);

        if (fromNode == null || toNodes == null || toNodes.size() == 0)
            System.out.println("Cannot find nodes to create relationship");
        else {

            for (long node : toOtherNodesId)
                addRelationShips(findNode(fromNodeId), findNode(node));
        }
    }

    public void addRelationShips(long fromNodeId, long toNodesId, long ... toOtherNodesId) {

        AbstractNode fromNode = findNode(fromNodeId);
        AbstractNode node = findNode(toNodesId);
        AbstractNode toNode = node;

        if (fromNode == null || toNode == null)
            System.out.println("Cannot find nodes to create relationship");
        else {

            addRelationShips(findNode(fromNodeId), findNode(toNodesId));
            for (long nodeId : toOtherNodesId)
                addRelationShips(findNode(fromNodeId), findNode(nodeId));
        }
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

    public Set<RelationShip> getNodesRelationShip(){
        Set<RelationShip> result = new HashSet<>();
        for(AbstractNode node: this.nodeStorage){
            for(RelationShip relationShip:node.getInCommingRelationShipStorage()){
                result.add(relationShip);
            }
            for (RelationShip relationShip:node.getOutGoingRelationShipStorage()){
                result.add(relationShip);
            }
        }

        return result;
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

    public void deleteRelationShipFromBeginNodeToEndNode(long beginNodeId, long endNodeId) {
        deleteRelationShipFromBeginNodeToEndNode(findNode(beginNodeId), findNode(endNodeId));
    }

    public void deleteRelationShipFromBeginNodeToEndNode(AbstractNode beginNode, AbstractNode endNode) {
        try {
            for (int i = 0; i < beginNode.getOutGoingRelationShipStorage().size(); i++) {
                RelationShip relationShip = beginNode.getOutGoingRelationShipAtPosition(i); //  tu zmieniono
                if (relationShip.getEndNode().equals(endNode))
                    beginNode.getOutGoingRelationShipStorage().remove(relationShip);
                endNode.getInCommingRelationShipStorage().remove(relationShip);     //
            }

            for (int i = 0; i < beginNode.getInCommingRelationShipStorage().size(); i++) {
                RelationShip relationShip = beginNode.getInCommingRelationShipAtPosition(i); //  tu zmienione
                if (relationShip.getBeginNode().equals(endNode))
                    beginNode.getInCommingRelationShipStorage().remove(relationShip);
                endNode.getOutGoingRelationShipStorage().remove(relationShip);      //
            }

            if (!beginNode.getInCommingRelationShipStorage().contains(new RelationShip(endNode, beginNode))) {
                beginNode.getNeighbourNodeStorage().remove(endNode);
                endNode.getNeighbourNodeStorage().remove(beginNode);
            }
        } catch (Exception e) {

        }
        deleteRelationsShipBetweenTwoNodesFromGraph(beginNode, endNode);
    }

    public void deleteRelationShipBetweenTwoNodes(String beginNode, String endNode) {
        deleteRelationShipBetweenTwoNodes(findNode(beginNode), findNode(endNode));
    }

    public void deleteRelationShipBetweenTwoNodes(long beginNodeId, long endNodeId) {
        deleteRelationShipBetweenTwoNodes(findNode(beginNodeId), findNode(endNodeId));
    }

    public void deleteRelationShipBetweenTwoNodes(AbstractNode beginNode, AbstractNode endNode) {
        deleteRelationShipFromBeginNodeToEndNode(beginNode, endNode);
        deleteRelationShipFromBeginNodeToEndNode(endNode, beginNode);
    }

    public void deleteRelationsShipBetweenTwoNodesFromGraph(AbstractNode beginNode, AbstractNode endNode) {
        try {
            for (int i = 0; i < graphRelationShipStorage.size(); i++) {
                RelationShip relationShip = getGraphRelationShipAtPosition(i);
                if (relationShip.getBeginNode().equals(beginNode) && relationShip.getEndNode().equals(endNode)) {
                    this.graphRelationShipStorage.remove(relationShip);
                }
            }
        } catch (Exception e) {

        }
    }

    private RelationShip getGraphRelationShipAtPosition(int position) throws Exception {
        if (position >= 0 && position <= graphRelationShipStorage.size()-1) {
            int i=0;
            for (RelationShip relationShip : graphRelationShipStorage) {
                if (i++ == position)
                    return relationShip;
            }
        }
        else
            throw new Exception("");
        return null;
    }



    public void showAllRelationShips() {
        for (RelationShip relationShip : graphRelationShipStorage) {
            relationShip.show();
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

    public Set<AbstractNode> getNodeStorage() {
        return nodeStorage;
    }

    public void setNodeStorage(Set<AbstractNode> nodeStorage) {
        this.nodeStorage = nodeStorage;
    }

    public Set<RelationShip> getGraphRelationShipStorage() {
        return graphRelationShipStorage;
    }

    public void setGraphRelationShipStorage(Set<RelationShip> graphRelationShipStorage) {
        this.graphRelationShipStorage = graphRelationShipStorage;
    }

    @Override
    public String toString() {
        return "AbstractGraph{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", nodeStorage=" + nodeStorage +
                ", graphRelationShipStorage=" + graphRelationShipStorage +
                '}';
    }
}
