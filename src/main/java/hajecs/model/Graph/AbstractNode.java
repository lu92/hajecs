package hajecs.model.Graph;

import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.Transient;
import org.springframework.data.neo4j.annotation.*;

import java.util.*;

/**
 * Created by lucjan on 07.05.15.
 */
@NodeEntity
public abstract class AbstractNode {

    @GraphId
    protected Long id;
    protected String name;

    @Transient
    protected Set<AbstractNode> neighbourNodeStorage = new HashSet<>();

    @Transient
    protected Set<RelationShip> outGoingRelationShipStorage = new HashSet<>();

    @Transient
    protected Set<RelationShip> inCommingRelationShipStorage = new HashSet<>();


    @Fetch
    @RelatedTo(type = "GRAPH_NODE_RELATION", direction = Direction.BOTH)
    protected AbstractGraph graph;

//    @Fetch @RelatedToVia(type = "RELATED_TO")
//    private RelationShip relationShip;

    public AbstractNode() {
    }

    public AbstractNode(String name) {
        this.name = name;
    }

    public AbstractNode(long id, String name) {
        this.id = id;
        this.name = name;
    }



    public Set<AbstractNode> getNeighbourNodeStorage() {
        return neighbourNodeStorage;
    }

    public void setNeighbourNodeStorage(Set<AbstractNode> neighbourNodeStorage) {
        this.neighbourNodeStorage = neighbourNodeStorage;
    }

    public Set<RelationShip> getInCommingRelationShipStorage() {
        return inCommingRelationShipStorage;
    }
    public void setInCommingRelationShipStorage(Set<RelationShip> inCommingRelationShipStorage) {
        this.inCommingRelationShipStorage = inCommingRelationShipStorage;
    }




    public boolean isIsolated() {
        if (neighbourNodeStorage.isEmpty() &&
                outGoingRelationShipStorage.isEmpty() &&
                inCommingRelationShipStorage.isEmpty())
            return true;
        else return false;
    }


    public int calculateNumberOfNeighBourNodes() {
        List<RelationShip> allRelationShips = new ArrayList<>();
        allRelationShips.addAll(getInCommingRelationShipStorage());
        allRelationShips.addAll(getOutGoingRelationShipStorage());

        List<RelationShip> uniqRelationShips = new ArrayList<>();

        for (RelationShip relationShip : allRelationShips) {
            if (!uniqRelationShips.contains(relationShip) && !uniqRelationShips.contains(RelationShip.getReversedRelationShip(relationShip)) )
                uniqRelationShips.add(relationShip);
        }
        return uniqRelationShips.size();
    }

    public void addNeighBourNode(AbstractNode... neighbours) {
        for (AbstractNode node : neighbours)
            this.neighbourNodeStorage.add(node);
    }

    public void addIncommingRelationShip(RelationShip relationShip) {
        this.inCommingRelationShipStorage.add(relationShip);
    }

    public void addOutgoingRelationShip(RelationShip relationShip) {
        this.outGoingRelationShipStorage.add(relationShip);
    }

    public List<String> getNamesOfNeighbours() {
        List<String> names = new ArrayList<>();

        if (!this.isIsolated()) {
            for (AbstractNode neighbour : this.getNeighbourNodeStorage()) {
                names.add(neighbour.getName());
            }
        }
        return names;
    }

    public int getNumberOfInCommingRelationShips() {
        return this.inCommingRelationShipStorage.size();
    }

    public int getNumberOfOutGoingRelationShips() {
        return this.outGoingRelationShipStorage.size();
    }

    public int getNumberOfNeighbours() {
        return this.neighbourNodeStorage.size();
    }

    public void showOutGoingRelationShips() {
        System.out.println("outgoing relationships from " + getName());
        for (RelationShip relationShip : this.getOutGoingRelationShipStorage())
            System.out.println(relationShip.getBeginNode().getName() + " -> " + relationShip.getEndNode().getName());
    }

    public void showInCommingRelationShips() {
        System.out.println("incomming relationships to " + getName());
        for (RelationShip relationShip : this.getInCommingRelationShipStorage())
            System.out.println(relationShip.getBeginNode().getName() + " -> " + relationShip.getEndNode().getName());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractNode node = (AbstractNode) o;

        if (!name.equals(node.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
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

    public Set<RelationShip> getOutGoingRelationShipStorage() {
        return outGoingRelationShipStorage;
    }

    public RelationShip getOutGoingRelationShipAtPosition(int position) throws Exception {

        if (position >=0 && position<=outGoingRelationShipStorage.size()-1) {
            int i = 0;
            for (RelationShip relationShip : outGoingRelationShipStorage)
                if (i++ == position)
                    return relationShip;
            
        }
        else
            throw new Exception("Outgoing RelationShip from " + getName() + " at position "+ position+ " doesn't exists ");
        return null;
    }

    public RelationShip getInCommingRelationShipAtPosition(int position) throws Exception {

        if (position >=0 && position<=inCommingRelationShipStorage.size()-1) {
            int i = 0;
            for (RelationShip relationShip : inCommingRelationShipStorage)
                if (i++ == position)
                    return relationShip;
        }
        else
            throw new Exception("Outgoing RelationShip from " + getName() + " at position "+ position+ " doesn't exists ");
        return null;
    }

    public void setOutGoingRelationShipStorage(Set<RelationShip> outGoingRelationShipStorage) {
        this.outGoingRelationShipStorage = outGoingRelationShipStorage;
    }
}
