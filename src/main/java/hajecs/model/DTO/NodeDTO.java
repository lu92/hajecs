package hajecs.model.DTO;

/**
 * Created by lucjan on 21.05.15.
 */
public class NodeDTO {
    /*
    do tworzenia wierzcholka w milestone lub project
     */
    private String name;
    private long graphId;


    public NodeDTO() {
    }

    public NodeDTO(String name, long graphId) {
        this.name = name;
        this.graphId = graphId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGraphId() {
        return graphId;
    }

    public void setGraphId(long graphId) {
        this.graphId = graphId;
    }

    @Override
    public String toString() {
        return "NodeDTO{" +
                "name='" + name + '\'' +
                ", graphId=" + graphId +
                '}';
    }
}
