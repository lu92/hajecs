package hajecs.model.Graph;

import java.util.*;

/**
 * Created by lucjan on 07.05.15.
 */

public abstract class AbstractNode {

    protected Long id;
    protected String name;

    public List<AbstractNode> getNeighbourNodeStorage() {
        return neighbourNodeStorage;
    }

    public void setNeighbourNodeStorage(List<AbstractNode> neighbourNodeStorage) {
        this.neighbourNodeStorage = neighbourNodeStorage;
    }

    public List<RelationShip> getInCommingRelationShipStorage() {
        return inCommingRelationShipStorage;
    }

    public void setInCommingRelationShipStorage(List<RelationShip> inCommingRelationShipStorage) {
        this.inCommingRelationShipStorage = inCommingRelationShipStorage;
    }

    protected List<AbstractNode> neighbourNodeStorage = new ArrayList<>();
    protected List<RelationShip> outGoingRelationShipStorage = new ArrayList<RelationShip>();
    protected List<RelationShip> inCommingRelationShipStorage = new ArrayList<RelationShip>();

    public AbstractNode(String name) {
        this.name = name;
    }

    public AbstractNode(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean isIsolated() {
        if (neighbourNodeStorage.isEmpty() &&
                outGoingRelationShipStorage.isEmpty() &&
                inCommingRelationShipStorage.isEmpty())
            return true;
        else return false;
    }


    public int calculateNuberOfNeighBourNodes() {
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
        if (!neighbourNodeStorage.equals(node.neighbourNodeStorage)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + neighbourNodeStorage.hashCode();
        return result;
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

    public List<RelationShip> getOutGoingRelationShipStorage() {
        return outGoingRelationShipStorage;
    }

    public void setOutGoingRelationShipStorage(List<RelationShip> outGoingRelationShipStorage) {
        this.outGoingRelationShipStorage = outGoingRelationShipStorage;
    }
}
