package hajecs.model.DTO;

/**
 * Created by lucjan on 24.05.15.
 */
public class RelationShipDTO {
    private long beginNodeId;
    private long endNodeId;

    public RelationShipDTO() {
    }

    public RelationShipDTO(long beginNodeId, long endNodeId) {
        this.beginNodeId = beginNodeId;
        this.endNodeId = endNodeId;
    }

    public long getBeginNodeId() {
        return beginNodeId;
    }

    public void setBeginNodeId(long beginNodeId) {
        this.beginNodeId = beginNodeId;
    }

    public long getEndNodeId() {
        return endNodeId;
    }

    public void setEndNodeId(long endNodeId) {
        this.endNodeId = endNodeId;
    }
}
