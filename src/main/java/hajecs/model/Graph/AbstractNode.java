package hajecs.model.Graph;

import java.util.ArrayList;
import java.util.List;

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

    public List<RelationShip> getIncommingRelationShipStorage() {
        return incommingRelationShipStorage;
    }

    public void setIncommingRelationShipStorage(List<RelationShip> incommingRelationShipStorage) {
        this.incommingRelationShipStorage = incommingRelationShipStorage;
    }

    protected List<AbstractNode> neighbourNodeStorage = new ArrayList<>();
    protected List<RelationShip> outgoingRelationShipStorage = new ArrayList<RelationShip>();
    protected List<RelationShip> incommingRelationShipStorage = new ArrayList<RelationShip>();

    public AbstractNode(String name) {
        this.name = name;
    }

    public AbstractNode(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addNeighBourNode(AbstractNode... neighbours) {
        for (AbstractNode node : neighbours)
            this.neighbourNodeStorage.add(node);
    }

    public void addIncommingRelationShip(RelationShip relationShip) {
        this.incommingRelationShipStorage.add(relationShip);
    }

    public void addOutgoingRelationShip(RelationShip relationShip) {
        this.outgoingRelationShipStorage.add(relationShip);
    }

    public int getNumberOfInCommingRelationShips() {
        return this.incommingRelationShipStorage.size();
    }

    public int getNumberOfOutGoingRelationShips() {
        return this.outgoingRelationShipStorage.size();
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

    public List<RelationShip> getOutgoingRelationShipStorage() {
        return outgoingRelationShipStorage;
    }

    public void setOutgoingRelationShipStorage(List<RelationShip> outgoingRelationShipStorage) {
        this.outgoingRelationShipStorage = outgoingRelationShipStorage;
    }
}
