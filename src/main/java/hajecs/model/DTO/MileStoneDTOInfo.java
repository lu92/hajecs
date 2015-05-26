package hajecs.model.DTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 23.05.15.
 */
public class MileStoneDTOInfo {
    private Long id;
    private String name;
    private String describe;
    private Set<NodeDTOInfo> nodes = new HashSet<>();
    private Set<RelationShipDTOInfo> relationShips = new HashSet<>();

    public MileStoneDTOInfo() {
    }

    public MileStoneDTOInfo(Long id, String name, String describe, Set<NodeDTOInfo> nodes, Set<RelationShipDTOInfo> relationShips) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.nodes = nodes;
        this.relationShips = relationShips;
    }


    public void addNodeDTOInfo(NodeDTOInfo ... nodeDTOInfos) {
        for (NodeDTOInfo nodeDTOInfo : nodeDTOInfos) {
            nodes.add(nodeDTOInfo);
        }
    }

    public void addRelationShipDTOInfo(RelationShipDTOInfo ... relationShipDTOInfos) {
        for (RelationShipDTOInfo relationShipDTOInfo : relationShipDTOInfos) {
            relationShips.add(relationShipDTOInfo);
        }
    }

    public long getId() {
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

    public Set<NodeDTOInfo> getNodes() {
        return nodes;
    }

    public void setNodes(Set<NodeDTOInfo> nodes) {
        this.nodes = nodes;
    }

    public Set<RelationShipDTOInfo> getRelationShips() {
        return relationShips;
    }

    public void setRelationShips(Set<RelationShipDTOInfo> relationShips) {
        this.relationShips = relationShips;
    }
}
