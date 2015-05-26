package hajecs.model.DTO;

/**
 * Created by lucjan on 24.05.15.
 */
public class RelationShipDTOInfo {
    private Long id;
    private Long beginNodeId;
    private Long endNodeId;

    public RelationShipDTOInfo() {
    }

    public RelationShipDTOInfo(Long id, Long beginNodeId, Long endNodeId) {
        this.id = id;
        this.beginNodeId = beginNodeId;
        this.endNodeId = endNodeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBeginNodeId() {
        return beginNodeId;
    }

    public void setBeginNodeId(Long beginNodeId) {
        this.beginNodeId = beginNodeId;
    }

    public Long getEndNodeId() {
        return endNodeId;
    }

    public void setEndNodeId(Long endNodeId) {
        this.endNodeId = endNodeId;
    }
}
