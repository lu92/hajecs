package hajecs.services;

/**
 * Created by lucjan on 24.05.15.
 */
public interface RelationShipService {
    public long setRelationShipBetweenTwoTaskNodes(long beginNodeId, long endNodeId) throws IllegalArgumentException;
    public void deleteRelationShip(long id);
}
