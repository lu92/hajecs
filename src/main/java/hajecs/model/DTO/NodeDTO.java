package hajecs.model.DTO;

/**
 * Created by lucjan on 21.05.15.
 */
public class NodeDTO {
    /*
    do tworzenia wierzcholka w milestone lub project
     */
    private long graphId;
    private String name;


    public NodeDTO() {
    }

    public NodeDTO(long graphId, String name) {
        this.graphId = graphId;
        this.name = name;
    }

    public long getGraphId() {
        return graphId;
    }

    public void setGraphId(long graphId) {
        this.graphId = graphId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
